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
		
		Date birthdate=null,deathdate=null;
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd"/*, Locale.ENGLISH*/);
		// 
		
		for (String id : _individuals.keySet()) {
			ArrayList<Individual> array = _individuals.get(id);
			
			// 1) check for unique identifiers
			if (array.size() > 1)
				_errorList.add(String.format("Duplicate Identifer: There are multiple individuals with the identifier %s", id));
			
					
			// 2) check if Individual birth date is after death date
			
			Individual individual = array.get(0);
			birthdate = individual.GetDateOfBirth();
			deathdate = individual.GetDateOfDeath();
			
			/* For debugging purposes
			System.out.println("birth: " + format.format(birthdate));
			
			if (deathdate != null) {
				System.out.println("death: " + format.format(deathdate));
			} else {
				System.out.println("death: ");
			}*/
			
			if(deathdate != null && birthdate.after(deathdate))
			{
				_errorList.add(String.format("Individual %s (%s) has death date (%s) before birth date (%s)",
						id, individual.GetName(), format.format(deathdate), format.format(birthdate)));
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
