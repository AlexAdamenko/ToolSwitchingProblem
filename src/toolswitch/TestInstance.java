package toolswitch;

import java.util.ArrayList;
import java.util.Arrays;

public class TestInstance {
	
	public static void main(String [] args)	{
		
		
		 ArrayList<Integer> magazine = new ArrayList<Integer>();
		 ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
		 ArrayList<Integer> tools = new ArrayList<Integer>();
		 
		 
		 int capacity = 4;
		 int length = 10;
		 int toolsNumber = 10;
		 ArrayList<ArrayList<Integer>> jobs_tools = new ArrayList<ArrayList<Integer>>();
		 
		 readInstance readFromFile = new readInstance();
		 readFromFile.readFromFile("matrix_10j_10to_NSS_0.txt", capacity, length);
		 jobs_tools = readFromFile.twoDArrayToList(readFromFile.data);
		 
		 magazineKNST test = new magazineKNST(jobs_tools, diff_magazine, capacity);
		 magazine = test.createFirstMagazine(jobs_tools, capacity);
		 
		 //System.out.println(readFromFile.twoDArrayToList(readFromFile.data));
		 
		
		 // Uncomment the code, to see the results
		 
		 //-------------------------------------------
		 //KNST knst = new KNST();
		// knst.KNSTCalc(jobs_tools, magazine, length);
		 
		 //-------------------------------------------
		 //Greedy greedy = new Greedy();
		 //greedy.runGreedy(jobs_tools, length, toolsNumber, capacity);
		 //-------------------------------------------

		 //-------------------------------------------
		 //LocalSearch ls = new LocalSearch(); 
		 //Greedy greedy = new Greedy();
		 //greedy.runGreedy(jobs_tools, length, toolsNumber, capacity);
		 //ls.runLS(greedy, capacity, length);
		 //-------------------------------------------
		 
		 VND vnd=new VND();
		 Greedy greedy = new Greedy();
		 greedy.runGreedy(jobs_tools, length, toolsNumber, capacity);
		 Solution last=vnd.next(new Solution(greedy._solution,greedy.switches),length,capacity);
		 System.out.print("BEST SOLUTION: " + last.solution+" f(x):"+last.f_x);
		 
	}

}
