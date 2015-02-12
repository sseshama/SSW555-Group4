package familyTreeGedcom;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GedcomFileReader {

	/* Member variables */
	private String _inputFileName;
	private HashMap<String, Individual> _individuals;
	private HashMap<String, Family>		_families;
	
	public HashMap<String, Individual> GetIndividuals() {
		// TODO Auto-generated method stub
		return this._individuals;
	}

	public HashMap<String, Family> GetFamilies() {
		// TODO Auto-generated method stub
		return this._families;
	}
	
	
	/* Constructor */
	public GedcomFileReader(String inputFileName) {		
		_inputFileName = inputFileName;
		_individuals = new HashMap<String, Individual>();
		_families = new HashMap<String, Family>();
	}

	
	/* Parse the GEDCOM file */
	public void GetIndividualAndFamilyInformation() {
		
		BufferedReader br = null;		
		
		try
		{
			String currentLine,nextLine;
			br = new BufferedReader(new FileReader(_inputFileName));			
			
			// Read every line in the file
			while((currentLine = br.readLine()) != null)
			{
				
				// Identify the lines about Individuals and store information
				String [] currentLineSplits = currentLine.split(" ");
				if(currentLine.startsWith("0") && currentLineSplits.length > 2 && currentLine.split(" ")[2].equals("INDI"))
				{
					Individual newIndividual = new Individual();
					String id = currentLine.split(" ")[1];
					nextLine = br.readLine();					
					newIndividual.SetName(nextLine.substring(7).replace("/", ""));	
					this._individuals.put(id, newIndividual);
				}
				
				// Identify the lines about Families and store the information
				if(currentLine.startsWith("0") && currentLineSplits.length > 2 && currentLine.split(" ")[2].equals("FAM"))
				{
					Family newFamily = new Family();
					String id = currentLine.split(" ")[1];
					
					// Read the next two lines to get the husband and wife
					String husbandInfo = br.readLine();
					String wifeInfo = br.readLine();
					
					newFamily.SetHusband(husbandInfo.substring(7));
					newFamily.SetWife(wifeInfo.substring(7));
					this._families.put(id,  newFamily);
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
