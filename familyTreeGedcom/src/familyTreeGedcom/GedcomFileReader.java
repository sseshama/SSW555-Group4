package familyTreeGedcom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				
				/* Don't need to worry about families for now */
				// Identify the lines about Families and store the information
				if(currentLine.startsWith("0") && currentLineSplits.length > 2 && currentLine.split(" ")[2].equals("FAM"))
				{
					addNewFamily(br, currentLine);
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

	private void addNewFamily(BufferedReader br, String currentLine)
			throws IOException {
		Family newFamily = new Family();
		String id = currentLine.split(" ")[1];
		
		// Read the next two lines to get the husband and wife
		String husbandInfo = br.readLine();
		String wifeInfo = br.readLine();
		
		newFamily.SetHusband(husbandInfo.substring(7));
		newFamily.SetWife(wifeInfo.substring(7));	
		
		
		String line = br.readLine();
		while(line.contains("CHIL")) {			
			newFamily.Children().add(line.substring(7));
			line = br.readLine();
		}
		
		this._families.put(id,  newFamily);		
	}

	private String getMonth(String mnth)
	{
		String month="0";
		switch(mnth)
			{
				case "JAN": month = "01";
				break;
				case "FEB": month = "02"; 
				break;
				case "MAR": month = "03"; 
				break;
				case "APR": month = "04"; 
				break;
				case "MAY": month = "05"; 
				break;
				case "JUN": month = "06"; 
				break;
				case "JUL": month = "07"; 
				break;
				case "AUG": month = "08"; 
				break;
				case "SEP": month = "09"; 
				break;
				case "OCT": month = "10"; 
				break;
				case "NOV": month = "11"; 
				break;
				case "DEC": month = "12"; 
				break;	
			}
		
		return month;
	}
	
	private DateObject convertToDate(String line)
	{
		String dateAsString;
		Date date = null;
		String line1 = line.substring(7);
		
		String [] splitDate = line1.split(" ");
		String year = splitDate[2];
		String mnth = splitDate[1];
		String month = getMonth(mnth);
		String day = splitDate[0];
		dateAsString = String.format("%s-%s-%s", year,month,day);
		
		/* For debugging
		System.out.println("Line1: "+line1);
		System.out.println("dateAsString: "+dateAsString);
		*/
		
		String errorMessage = "";
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"/*, Locale.ENGLISH*/);
		// if invalid dates, then this will throw an exception
		format.setLenient(false);
		try {
			date = format.parse(dateAsString);
			// Debugging: System.out.println("DAte: "+format.format(date));
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			errorMessage = line1;
		}
		
		return new DateObject(date, errorMessage);
	}
	
	private void addNewIndividual(BufferedReader br, String currentLine)
			throws IOException {
		
		String nextLine;
		
		// create new individual and set identifier
		String id = currentLine.split(" ")[1];
		Individual newIndividual = new Individual(id);	
		DateObject birth_date, death_date;
		
		
		do {
			// get name
			nextLine = br.readLine();
			
			if (nextLine.contains("NAME"))
				newIndividual.SetName(nextLine.substring(7).replace("/", ""));
			
			if(nextLine.contains("BIRT"))
			{
				nextLine = br.readLine();
				birth_date = convertToDate(nextLine);
				newIndividual.SetDateOfBirth(birth_date);
			}
				
			
			if(nextLine.contains("DEAT"))
			{
				nextLine = br.readLine();
				death_date = convertToDate(nextLine);
				newIndividual.SetDateOfDeath(death_date);
			}
			
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
