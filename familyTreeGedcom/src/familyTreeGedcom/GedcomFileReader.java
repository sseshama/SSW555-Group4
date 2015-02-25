package familyTreeGedcom;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GedcomFileReader {

	/* Member variables */
	private String _inputFileName;
	private HashMap<String, ArrayList<Individual>> _individuals;
	private HashMap<String, Family>		_families;
	
	public HashMap<String, ArrayList<Individual>> GetIndividuals() {
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
		_individuals = new HashMap<String, ArrayList<Individual>>();
		_families = new HashMap<String, Family>();
	}

	
	/* Parse the GEDCOM file */
	public void GetIndividualAndFamilyInformation() {
		
		BufferedReader br = null;		
		
		try
		{
			String currentLine;
			br = new BufferedReader(new FileReader(_inputFileName));			
			
			// Read every line in the file
			while((currentLine = br.readLine()) != null)
			{
				
				// Identify the lines about Individuals and store information
				String [] currentLineSplits = currentLine.split(" ");
				if(currentLine.startsWith("0") && currentLineSplits.length > 2 && currentLine.split(" ")[2].equals("INDI"))
				{
					addNewIndividual(br, currentLine);
				}
				
				/* Don't need to worry about families for now 
				// Identify the lines about Families and store the information
				if(currentLine.startsWith("0") && currentLineSplits.length > 2 && currentLine.split(" ")[2].equals("FAM"))
				{
					addNewFamily(br, currentLine);
				}*/			
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

	private void addNewFamily(BufferedReader br, String currentLine)
			throws IOException {
		Family newFamily = new Family();
		String id = currentLine.split(" ")[1];
		
		// Read the next two lines to get the husband and wife
		String husbandInfo = br.readLine();
		String wifeInfo = br.readLine();
		
		newFamily.SetHusband(husbandInfo.substring(7));
		newFamily.SetWife(wifeInfo.substring(7));
		this._families.put(id,  newFamily);
	}

	private void addNewIndividual(BufferedReader br, String currentLine)
			throws IOException {
		
		String nextLine;
		
		// create new individual and set identifier
		String id = currentLine.split(" ")[1];
		Individual newIndividual = new Individual(id);			
		
		do {
			// get name
			nextLine = br.readLine();
			
			if (nextLine.contains("NAME"))
				newIndividual.SetName(nextLine.substring(7).replace("/", ""));
			
			// TODO: get date of birth
			
			// TODO: get date of death
			
		}while (!nextLine.contains("@F"));		
		
		
		// add new individual to the HashMap
		if (this._individuals.containsKey(id)) {
			ArrayList<Individual> array = this._individuals.get(id);
			array.add(newIndividual);
			this._individuals.put(id, array);
		} else {
			ArrayList<Individual> array = new ArrayList<Individual>();
			array.add(newIndividual);
			this._individuals.put(id, array);					
		}
	}
}
