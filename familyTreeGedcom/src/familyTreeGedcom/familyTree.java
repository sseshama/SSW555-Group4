package familyTreeGedcom;

import java.util.ArrayList;
import java.util.HashMap;

public class familyTree {

	public static void main(String[] args) {
		
		// Specify the file name for reading
		String inputFileName = "Resources/My-Family-6-Feb-2015.ged";
		
		// Read the file and store the information in a HashMap
		GedcomFileReader reader = new GedcomFileReader(inputFileName);
		reader.GetIndividualAndFamilyInformation();
		HashMap<String, ArrayList<Individual>> individuals = reader.GetIndividuals();
		
		//HashMap<String, Family> families = reader.GetFamilies();		
		/* Print the information stored in the HashMap
		GedcomFileInfoPrinter printer = new GedcomFileInfoPrinter(individuals, families);
		printer.PrintIndividuals();
		System.out.println();
		printer.PrintHusbandsAndWives(); 
		*/
		
		Validator validator = new Validator(individuals);
		validator.ValidateIndividuals();
		
		if (validator.GetErrorList().isEmpty()) {
			System.out.println("No errors with your Gedcom file");
		} else {
			System.out.println("The following errors are present in your Gedcom file");
			
			for (String errorMessage : validator.GetErrorList()) {
				System.out.println(errorMessage);
			}
		}
		
	}
}
