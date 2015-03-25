package familyTreeGedcom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Validator {

	private HashMap<String, ArrayList<Individual>> _individuals;
	private HashMap<String, Family> _families;
	private ArrayList<String> _errorList;
	
	DateFormat format =  new SimpleDateFormat("yyyy-MM-dd"/*, Locale.ENGLISH*/);
	
	public Validator(HashMap<String, ArrayList<Individual>> individuals, HashMap<String, Family> families) {
		_individuals = individuals;
		_families = families;
		_errorList = new ArrayList<String>();
	}

	public void ValidateIndividuals() {
		// TODO Auto-generated method stub
		
		for (String id : _individuals.keySet()) {
			ArrayList<Individual> array = _individuals.get(id);
			
			//US18 check for unique identifiers
			if (array.size() > 1)
				_errorList.add(String.format("DUPLICATE IDENTIFIER:\nThere are multiple individuals with the identifier %s", id));
			
			Individual individual = array.get(0);
			
			//US04, US12,US13,US14 Check for invalid dates			
			DateObject birthdate = individual.GetDateOfBirth();
			DateObject deathdate = individual.GetDateOfDeath();
			
			if (birthdate.Date() == null && !birthdate.ErrorMessage().isEmpty()) {
				_errorList.add(String.format("INVALID DATE:\nIndividual %s has an invalid birth date (%s)", id, birthdate.ErrorMessage()));
			}
			
			if (deathdate.Date() == null && !deathdate.ErrorMessage().isEmpty()) {
				_errorList.add(String.format("INVALID DATE:\nIndividual %s has an invalid death date (%s)", id, deathdate.ErrorMessage()));
			}
			
			//US01 "Death before birth" check if Individual birth date is after death date
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
			String wsex = wife.GetSex();
			String hsex = husband.GetSex();
			Date hdob = husband.GetDateOfBirth().Date();
			Date wdob = wife.GetDateOfBirth().Date();
			Date hdod = husband.GetDateOfDeath().Date();
			Date wdod = wife.GetDateOfDeath().Date();
			Date mdate = family.GetMarriageDate().Date();
			Date ddate = family.GetDivorceDate().Date();
			
			
			//US03 Marriage before birth
			if(hdob != null && mdate != null && mdate.before(hdob))
			{
				_errorList.add(String.format("Individual %s has marriage date (%s) before birth date (%s)", husband.GetIdentifier(),format.format(mdate),format.format(hdob)));
			}
			
			if(wdob != null && mdate != null && mdate.before(wdob))
			{
				_errorList.add(String.format("Individual %s has marriage date (%s) before birth date (%s)", wife.GetIdentifier(),format.format(mdate),format.format(wdob)));
			}
			
			//US02 Death before marriage
			if(hdod != null && mdate != null && mdate.after(hdod))
			{
				_errorList.add(String.format("Individual %s has date of death (%s) before marriage date (%s)", husband.GetIdentifier(),format.format(hdod),format.format(mdate)));
			}
			
			if(wdod != null && mdate != null && mdate.after(wdod))
			{
				_errorList.add(String.format("Individual %s has date of death (%s) before marriage date (%s)", wife.GetIdentifier(),format.format(wdod),format.format(mdate)));
			}
			
			//US15 Divorce before marriage
			if(ddate != null && mdate != null && mdate.after(ddate));
			{
				System.out.println("Divorce date " + format.format(ddate) + " " + format.format(mdate));
				_errorList.add("Divorce of an individual cannot happen before marriage");
			}
			
			
			
			//US06 Female Sex
			if(wsex != null && !wsex.equalsIgnoreCase("F"))
			{
				_errorList.add("Invalid choice of sex");
			}
			
			//US07 Male Sex
			if(hsex != null && !hsex.equalsIgnoreCase("M"))
			{
				_errorList.add("Invalid choice of sex");
			}
			
			for (String childKey : family.Children()) {
				if (!(_individuals.get(childKey)).isEmpty())
					children.add(_individuals.get(childKey).get(0));
			}				
			
			for (Individual child : children) { 
				//US09 "Father before child" check that child's DOB > husband's DOB
				Date cdob = child.GetDateOfBirth().Date();
				 if(cdob != null && hdob != null && cdob.before(hdob) )
				 {
					 _errorList.add(String.format("Father's age must be more than child's age"));
				 }
				
				//US08 "Mother before child" check that child's DOB > wife's DOB	
								 
				 if(cdob != null && wdob != null && cdob.before(wdob))
				 {
					 _errorList.add(String.format("Mother's age must be more than child's age")); 
				 }
			}
			
		}
		
	}
	

}
