package familyTreeGedcom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Validator {

	private HashMap<String, ArrayList<Individual>> _individuals;
	private ArrayList<String> _errorList;
	
	public Validator(HashMap<String, ArrayList<Individual>> individuals) {
		_individuals = individuals;
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
	

}
