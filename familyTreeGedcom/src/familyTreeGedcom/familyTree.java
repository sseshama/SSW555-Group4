package familyTreeGedcom;

import java.util.HashMap;

public class familyTree {

	public static void main(String[] args) {
		
		// Specify the file name for reading
		String inputFileName = "Resources/My-Family-6-Feb-2015.ged";
		
		// Read the file and store the information in a HashMap
		GedcomFileReader reader = new GedcomFileReader(inputFileName);
		reader.GetIndividualAndFamilyInformation();
		HashMap<String,Individual> individuals = reader.GetIndividuals();
		HashMap<String, Family> families = reader.GetFamilies();
		
		// Print the information stored in the HashMap
		GedcomFileInfoPrinter printer = new GedcomFileInfoPrinter(individuals, families);
		printer.PrintIndividuals();
		System.out.println();
		printer.PrintHusbandsAndWives();
	}
}
