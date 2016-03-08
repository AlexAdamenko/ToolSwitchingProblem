package toolswitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class KNST {
	
	int switches = 0;
	

	public int KNSTCalc(ArrayList<ArrayList<Integer>> jobs_tools, ArrayList<Integer> magazine, int length){
		
		
		
		for(ArrayList<Integer> currJob : jobs_tools){
			
			ArrayList<Integer> difference = new ArrayList<Integer>(currJob);
			ArrayList<Integer> notinMagazine = new ArrayList<Integer>(currJob);
			ArrayList<Integer> toReplace = new ArrayList<Integer>(magazine);
			HashMap<Integer, Integer> indexes = new HashMap< Integer, Integer>(); 
			List<ArrayList<Integer>> subList = new ArrayList<ArrayList<Integer>>();
			subList = jobs_tools.subList(jobs_tools.indexOf(currJob), length);
			
			difference.removeAll(magazine);
			
			
			if(difference.isEmpty()){
				//System.out.println(magazine.toString() + " " + currJob.toString());
			}else{
				notinMagazine.removeAll(magazine);
				toReplace.removeAll(currJob);
					for(int i : toReplace){
						int firstIndex = 0;
						firstIndex = firstOccurance(subList, i);
						indexes.put(i, firstIndex);
						
					}

					for(int j : notinMagazine){
						
						int currReplace = 0;
						int index = 0;
						
						int maxValueInMap=(int) (Collections.max(indexes.values()));
						for (Entry<Integer, Integer> entry : indexes.entrySet()) {  
				            if (entry.getValue()==-1) {
				                currReplace = entry.getKey();     
				                
				                break;
				                
				            }else{
				            	if (entry.getValue()==maxValueInMap) {
					                currReplace = entry.getKey();     
					                
					            }				            	
				            }
				        }
						
						index = magazine.indexOf(currReplace);
						System.out.println(indexes);
						magazine.set(index, j);
						indexes.remove(currReplace);
						switches++;
					    System.out.println("Magazine:" + " " +magazine + " " + "Job: " + " " +currJob.toString());
						
						
					}
					
					
				
			}
			
			
		}
		
		System.out.println("Total amount of switches: " + switches);
		System.out.println("---------------------------------------------- "+"\n");
		return switches;
	}
	
	public int firstOccurance(List<ArrayList<Integer>> array, int number){
		
		int firstIndex = -1;
		
		for(ArrayList<Integer> index : array){
			if(index.contains(number)){
				firstIndex = array.indexOf(index);
				break;
			}
		}
		
		return firstIndex;
		
	}

}
