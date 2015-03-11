package familyTreeGedcom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {

	private HashMap<String, ArrayList<Individual>> _individuals;
	private HashMap<String, Family> _families;
	private ArrayList<String> _errorList;
	
	public Validator(HashMap<String, ArrayList<Individual>> individuals, HashMap<String, Family> families) {
		_individuals = individuals;
		_families = families;
		_errorList = new ArrayList<String>();
	}

	public void ValidateIndividuals() {
		// TODO Auto-generated method stub
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd"/*, Locale.ENGLISH*/);
		// 
		
		for (String id : _individuals.keySet()) {
			ArrayList<Individual> array = _individuals.get(id);
			
			// 1) check for unique identifiers
			if (array.size() > 1)
				_errorList.add(String.format("DUPLICATE IDENTIFIER:\nThere are multiple individuals with the identifier %s", id));
			
			Individual individual = array.get(0);
			
			// 2) Check for invalid dates			
			DateObject birthdate = individual.GetDateOfBirth();
			DateObject deathdate = individual.GetDateOfDeath();
			
			if (birthdate.Date() == null && !birthdate.ErrorMessage().isEmpty()) {
				_errorList.add(String.format("INVALID DATE:\nIndividual %s has an invalid birth date (%s)", id, birthdate.ErrorMessage()));
			}
			
			if (deathdate.Date() == null && !deathdate.ErrorMessage().isEmpty()) {
				_errorList.add(String.format("INVALID DATE:\nIndividual %s has an invalid death date (%s)", id, deathdate.ErrorMessage()));
			}
			
			// 3) check if Individual birth date is after death date
			if(deathdate.Date() != null && birthdate.Date() != null && birthdate.Date().after(deathdate.Date()))
			{
				_errorList.add(String.format("DATE OF DEATH PRECEDES DATE OF BIRTH:\nIndividual %s (%s) has death date (%s) before birth date (%s)",
						id, individual.GetName(), format.format(deathdate.Date()), format.format(birthdate.Date())));
			}
			// Individual individual = array.get(0);
			//if (individual.GetDateOfBirth() > individual.GetDateOfDeath())
		}		
		
		
	}

	public ArrayList<String> GetErrorList() {
		// TODO Auto-generated method stub
		return this._errorList;
	}

	public void ValidateFamilies() {
		
		for (String familyId : _families.keySet()){
			
			
			Family family = _families.get(familyId);			
			Individual husband = _individuals.get(family.GetHusband()).get(0);
			Individual wife = _individuals.get(family.GetWife()).get(0);
			ArrayList<Individual> children = new ArrayList<Individual>();
			
			for (String childKey : family.Children()) {
				if (!(_individuals.get(childKey)).isEmpty())
					children.add(_individuals.get(childKey).get(0));
			}				
			
			for (Individual child : children) { 
				// 1) check that child's DOB > husband's DOB
				
				
				// 2) check that child's DOB > wife's DOB	
				
				// 3) check that wife is female
				
				// 4) check that husband is male				
			}
			
		}
		
	}
	

}
