package familyTreeGedcom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class familyTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,String> family = new HashMap<String,String>();
		BufferedReader br = null;		
		
		try
		{
			String currentLine,nextLine;
			String currLine, newLine;
			String id;
			br = new BufferedReader(new FileReader("E:\\courses\\cs-555\\P02\\P02\\PatwaPrachi_P01.ged"));
			while((currentLine = br.readLine()) != null)
			{
				if(currentLine.startsWith("0") && currentLine.contains("@I"))
				{
				id = currentLine.substring(3, 6);
					newLine = br.readLine();
					name = newLine.substring(7);
					
					System.out.println("ID: " + id);
					System.out.println("Name of the individual: " + name);
					
					family.put(id,name);

				}
				
				
				if(currentLine.startsWith("0") && currentLine.contains("@F"))
				{
					nextLine = br.readLine();
					newLine= br.readLine();
					husb = nextLine.substring(7);
					wife= newLine.substring(7);
					System.out.println("Husb: " + husb);
					System.out.println("Wife: " + wife);
				}
			}
			
			
		
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
