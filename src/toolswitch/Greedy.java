package toolswitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.IntStream;

public class Greedy {
	
	int switches = 0;
	ArrayList<ArrayList<Integer>> _solution;
	
	public ArrayList<ArrayList<Integer>> runGreedy(ArrayList<ArrayList<Integer>> jobs_tools, int length, int toolsNum, int capacity){
		
		System.out.println("Greedy Alg");
		ArrayList<Integer> magazine = new ArrayList<Integer>();
		ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
		
		magazineKNST test = new magazineKNST(jobs_tools, diff_magazine, capacity);
		
		
		int swap_index = jobs_tools.indexOf(mostCommonTools(jobs_tools, toolsNum));
		Collections.swap(jobs_tools, 0, swap_index);
		magazine = test.createFirstMagazine(jobs_tools, capacity);
		
		solution.add(jobs_tools.get(0));
		//System.out.println(solution);
		for(int i=0; i<length-1;i++){
			
			ArrayList<ArrayList<Integer>> currentJobList = new ArrayList<ArrayList<Integer>>(jobs_tools);
			currentJobList.removeAll(solution);
			
			ArrayList<Integer> toChooseNext = compareSwitches(magazine, currentJobList);
			solution.add(toChooseNext);
			
			ArrayList<Integer> notinMagazine = new ArrayList<Integer>(toChooseNext);
			ArrayList<Integer> difference = new ArrayList<Integer>(toChooseNext);
			ArrayList<Integer> toReplace = new ArrayList<Integer>(magazine);
			
			difference.removeAll(magazine);
			notinMagazine.removeAll(magazine);
			toReplace.removeAll(toChooseNext);
			
		
			
			for(int j : notinMagazine){
				
				int currReplace = 0;
			 
			 	for(int numToReplace : toReplace){
			 		Random random = new Random();
			 		currReplace  = random.nextInt(toReplace.size());
			 		break;
			 	}
			 	
			 	//System.out.println(toChooseNext);
			 	magazine.set(magazine.indexOf(toReplace.get(currReplace)), j);
			 	//System.out.println(magazine);
			 	toReplace.remove(currReplace);
			 	switches++;
	
			}
			
			System.out.println("Step " + i + " " + solution + " " + "Magazine:" + magazine);
			
	
		}
		
		System.out.println("Total amount of switches: " + switches);
		System.out.println("---------------------------------------------- "+"\n");
		this._solution=solution;
		return solution;
		
	}
	
	
	public ArrayList<Integer> mostCommonTools(ArrayList<ArrayList<Integer>> jobs_tools, int tools){
		
		int indexMax = 0;
		int maxVal = 0;
		ArrayList<Integer> allSum = new ArrayList<Integer>();
		ArrayList<Integer> toolsArray = new ArrayList<Integer>();
		ArrayList<Integer> toolsUsage = new ArrayList<Integer>();
		for(int i =0; i<tools; i++)
		{
			toolsArray.add(i);
		}
		
		int index = 0;
		
		for(int i : toolsArray){
			int sum = 0;
			for(ArrayList<Integer> array : jobs_tools){
				if(array.contains(i)){
					sum++;
				}
				
			}
			allSum.add(sum);
		}
		
		for(ArrayList<Integer> array : jobs_tools){
			
			int toolsQ = 0;
			
			for(int i : array){
				
				toolsQ += allSum.get(i);	
			}
			toolsUsage.add(toolsQ);
		}
		
		for (int v : toolsUsage) {      
            
			if (v > maxVal) {
                maxVal = v;
            }
				
        }
		
		indexMax = toolsUsage.indexOf(maxVal);
		return jobs_tools.get(indexMax);
			
	}
	
	
	public ArrayList<Integer> compareSwitches(ArrayList<Integer> magazine, ArrayList<ArrayList<Integer>> currList){
		
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		
		for(ArrayList<Integer> array : currList){
			
			ArrayList<Integer> difference = new ArrayList<Integer>(array);
			difference.removeAll(magazine);
			int size = difference.size();
			sizes.add(size);
		}
		
		int min = Collections.min(sizes);
		int index = sizes.indexOf(min);
		return currList.get(index);
		
	}

}
