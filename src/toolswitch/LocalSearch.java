package toolswitch;

import java.util.ArrayList;
import java.util.Collections;

public class LocalSearch {
	
	int switches = 0;
	int steps_0 = 0;
	int steps_1 = 0;
	
	public int runLS(Greedy greedy, int capacity, int length){
		
		ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
		ArrayList<Integer> magazine;
		int cur_f_x=greedy.switches;
		ArrayList<ArrayList<Integer>> solution=greedy._solution;
		ArrayList<ArrayList<Integer>> nSolution=null;
		
		
		System.out.println(solution+" "+cur_f_x);
		
		//next improvement
		for(int i=1;i<solution.size();i++){
			nSolution=new ArrayList<ArrayList<Integer>>();
			KNST knst=new KNST();
			magazineKNST first_magazine = new magazineKNST(solution, diff_magazine, capacity);
			magazine = new ArrayList<Integer>();
			
			for(int j=i;j>=0;j--){
				nSolution.add(solution.get(j));
			}
			for(int j=i+1;j<solution.size();j++){
				nSolution.add(solution.get(j));
			}
			
			magazine = first_magazine.createFirstMagazine(nSolution, capacity);
			int new_f_x=knst.KNSTCalc(nSolution, magazine, length);
			System.out.println(nSolution+" "+new_f_x);
			steps_0++;
			
			if(new_f_x<cur_f_x){
				cur_f_x=new_f_x;
				break;
			}
		}
		//nsolution=best
		System.out.println("Best f(x): "+cur_f_x+"\n");
		System.out.println("Steps: "+steps_0+"\n");
		
		//best improvement
		cur_f_x=greedy.switches;
		ArrayList<ArrayList<Integer>> nbSolution=null;
		for(int i=0;i<solution.size()-1;i++){
			
			nSolution=new ArrayList<ArrayList<Integer>>();
			KNST knst=new KNST();
			magazineKNST first_magazine = new magazineKNST(solution, diff_magazine, capacity);
			magazine = new ArrayList<Integer>();
			
			for(int j=0;j<solution.size();j++){
				if(i==j)
					nSolution.add(solution.get(i+1));
				else if(j==i+1)
					nSolution.add(solution.get(i));
				else
					nSolution.add(solution.get(j));
			}
			
			magazine = first_magazine.createFirstMagazine(nSolution, capacity);
			int new_f_x=knst.KNSTCalc(nSolution, magazine, length);
			System.out.println(nSolution+" "+new_f_x);
			steps_1++;
			
			if(i==0)
				nbSolution=nSolution;
			
			if(new_f_x<cur_f_x){
				nbSolution=nSolution;
				cur_f_x=new_f_x;
			}
		}
		//nbsolution=best
		System.out.println("Best f(x): "+cur_f_x+"\n");
		System.out.println("Steps: "+steps_1+"\n");
		
		switches = cur_f_x;
		return switches;
	}
	
}
