package familyTreeGedcom;

import java.util.HashMap;

public class GedcomFileInfoPrinter {

	private HashMap<String, Family> _families;
	private HashMap<String, Individual> _individuals;

	/* Constructor */
	public GedcomFileInfoPrinter(HashMap<String, Individual> individuals, HashMap<String,Family> families) {
		_individuals = individuals;
		_families = families;		
	}

	/* Print the information about Individuals */
	public void PrintIndividuals() {
		System.out.println("INDIVIDUALS");
		for (String key : _individuals.keySet())
		{
			Individual individual = _individuals.get(key);
			System.out.println(individual.GetName());			
		}		
	}

	/* Print the information about husbands and wives */
	public void PrintHusbandsAndWives() {
		System.out.println("FAMILIES");		
		
		for (String key : _families.keySet())
		{
			Family family = _families.get(key);
			String husbandName = _individuals.get(family.GetHusband()).GetName();
			String wifeName = _individuals.get(family.GetWife()).GetName();
			System.out.printf("Husband: %s\t\tWife: %s", husbandName, wifeName);
			System.out.println();			
		}
	}
}
