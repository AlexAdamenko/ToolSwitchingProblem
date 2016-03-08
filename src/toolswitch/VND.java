package toolswitch;

import java.util.ArrayList;

public class VND {

	int iterations=0;
	int imax=100;
	
	
	public Solution next(Solution input, int length, int capacity){
		ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
		ArrayList<Integer> magazine;
		int cur_f_x=input.f_x;
		ArrayList<ArrayList<Integer>> solution=input.solution;
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
			
			if(new_f_x<cur_f_x){
				cur_f_x=new_f_x;
				break;
			}
		}
		
		iterations++;
		
		if(iterations<imax)
			return best(new Solution(nSolution,cur_f_x),length,capacity);
		else
			return new Solution(nSolution,cur_f_x);
			
	}
	
	public Solution best(Solution input, int length, int capacity){
		ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
		ArrayList<Integer> magazine;
		int cur_f_x=input.f_x;
		ArrayList<ArrayList<Integer>> solution=input.solution;
		ArrayList<ArrayList<Integer>> nSolution=null;
		ArrayList<ArrayList<Integer>> nbSolution=null;

		System.out.println(solution+" "+cur_f_x);
		
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
			
			if(i==0)
				nbSolution=nSolution;
			
			if(new_f_x<cur_f_x){
				nbSolution=nSolution;
				cur_f_x=new_f_x;
			}
		}
		
		iterations++;
		if(iterations<imax)
			return next(new Solution(nbSolution,cur_f_x),length,capacity);
		else
			return new Solution(nbSolution,cur_f_x);
	}
	
}
