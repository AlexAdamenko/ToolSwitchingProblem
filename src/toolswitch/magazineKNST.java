package toolswitch;
import java.util.ArrayList;

public class magazineKNST {
	
	int capacity;
	ArrayList<ArrayList<Integer>> jobs_tools = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> diff_magazine = new ArrayList<Integer>();
	ArrayList<Integer> tools = new ArrayList<Integer>();
	
	public magazineKNST(ArrayList<ArrayList<Integer>> jobs_tools, ArrayList<Integer> magazine, int capacity) {
		super();
		this.jobs_tools = jobs_tools;
		this.capacity = capacity;
	}
	
	
	public ArrayList<Integer> createFirstMagazine(ArrayList<ArrayList<Integer>> jobs_tools, int capacity){
		
		ArrayList<Integer> first_array = new ArrayList<Integer>();
		first_array = jobs_tools.get(0);
		ArrayList<Integer> magazine = new ArrayList<Integer>(first_array);
		
		while(magazine.size()<capacity){
			for(int i=1;i<jobs_tools.size();i++){
				for(int j=0;j<jobs_tools.get(i).size();j++){
					if(!magazine.contains(jobs_tools.get(i).get(j)) && magazine.size()<capacity)
						magazine.add(jobs_tools.get(i).get(j));
				}
			}
		}
		
		return magazine;
		
	}

}
