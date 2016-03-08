package toolswitch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class readInstance {
	
	 int data [][];
	
	
	ArrayList<ArrayList<Integer>> jobs_tools = new ArrayList<ArrayList<Integer>>();
	
	 public  void readFromFile(String path, int magazine, int jobs){
		 boolean flag=true;
			data  = new int[jobs][magazine];
			try (BufferedReader br = new BufferedReader(new FileReader(path)))
			{

				String sCurrentLine;
				// Initialisztion 
				for(int k = 0; k<jobs;k++)
					for(int m = 0; m<magazine;m++)
						data[k][m] = -1;
				
				int i = 0,j= 0;
				int counter = 0;
				while ((sCurrentLine = br.readLine()) != null) {
					if(counter < 10){
					sCurrentLine = sCurrentLine.substring(2,sCurrentLine.length()-1);
					counter++;
					}else{
						sCurrentLine = sCurrentLine.substring(3,sCurrentLine.length()-1);

					}
					{
						j=0;
						flag = true;
						for (char ch: sCurrentLine.toCharArray()) {
							if(ch != ','){
								if(flag == false){
								//	System.out.println("I want to add to " + 	data[i][j-1] + " new value " + Character.getNumericValue(ch) );
									String temp_string = Integer.toString(data[i][j-1]) + Character.getNumericValue(ch);
							
									data[i][j-1]= Integer.parseInt(temp_string);
							
								} else {
									data[i][j]=Character.getNumericValue(ch);
									j++;
									flag=false;
								}
							}else{
								flag=true;
								
							}
						}
						i++;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	 
	 public ArrayList<ArrayList<Integer>> twoDArrayToList(int [][] twoDArray) {
		 	ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		    for (int [] array : twoDArray) {
		        list.add((ArrayList<Integer>) Arrays.stream(array)
		    		      .boxed()
		    		      .collect(Collectors.toList()));
		    }
		    
		   
		    
		 for(ArrayList<Integer> arrayList : list){
			   for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext(); ) {
			    	  int px = iterator.next();
			    	  if(px == -1){
			    	    iterator.remove();
			    	  }
			    	}
		 }
		    	 
		    
		    return list;
		}
	 

}
