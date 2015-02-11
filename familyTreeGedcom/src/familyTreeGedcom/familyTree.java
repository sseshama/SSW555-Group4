package familyTreeGedcom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class familyTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
					nextLine = br.readLine();
					System.out.println("Name of the individual: " + nextLine.substring(7));
				}
			}
			
			
		
			while((currLine = br.readLine()) != null)
			{
				if(currLine.startsWith("0") && currLine.contains("@F"))
				{
					newLine = br.readLine();
					id = newLine.substring(7);
					System.out.println("ID: " + id);
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
