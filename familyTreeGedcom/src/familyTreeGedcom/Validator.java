package familyTreeGedcom;

import java.util.ArrayList;
import java.util.HashMap;

public class Validator {

	private HashMap<String, ArrayList<Individual>> _individuals;
	private ArrayList<String> _errorList;
	
	public Validator(HashMap<String, ArrayList<Individual>> individuals) {
		_individuals = individuals;
		_errorList = new ArrayList<String>();
	}

	public void ValidateIndividuals() {
		// TODO Auto-generated method stub
		
		// 
		for (String id : _individuals.keySet()) {
			ArrayList<Individual> array = _individuals.get(id);
			
			// 1) check for unique identifiers
			if (array.size() > 1)
				_errorList.add(String.format("Duplicate Identifer: There are multiple individuals with the identifier %s", id));
			
					
			// 2) check if Individual birth date is after death date
			// Individual individual = array.get(0);
			//if (individual.GetDateOfBirth() > individual.GetDateOfDeath())
		}		
		
		
	}

	public ArrayList<String> GetErrorList() {
		// TODO Auto-generated method stub
		return this._errorList;
	}
	

}
