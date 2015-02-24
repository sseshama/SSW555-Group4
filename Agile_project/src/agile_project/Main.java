package agile_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) 
	{
		
		String inputFileName = "Resources/PatwaPrachi_P01.ged";
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		int id = 1;
		
		BufferedReader br = null;	
		Functions f1 = new Functions();
		
		try
		{
			String currentLine,nextLine;
			br = new BufferedReader(new FileReader(inputFileName));
			
			while((currentLine = br.readLine()) != null)
			{
				map.put(id, currentLine);
				id++;
			}
			
			String error = f1.unique_id(map);
			System.out.println(error);
		}
		
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				if(br != null)
					br.close();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}		

	}

}
