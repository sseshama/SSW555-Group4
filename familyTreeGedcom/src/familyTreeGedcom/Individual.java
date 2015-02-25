package familyTreeGedcom;

import java.util.Date;

public class Individual {

	private String _identifier;
	
	private String _name;
	
	private Date _dateOfBirth;	
	
	private Date _dateOfDeath;
		
	public Individual ()
	{		
	}
	
	public Individual (String identifier) {
		this();
		_identifier = identifier;
	}
	
	public void SetIdentifier (String identifier) {
		this._identifier = identifier;
	}
	
	public String GetIdentifier () {
		return this._identifier;
	}
	
	public void SetDateOfBirth (Date dateOfBirth) {
		this._dateOfBirth = dateOfBirth;
	}
	
	public Date GetDateOfBirth () {
		return this._dateOfBirth;
	}
	
	public void SetDateOfDeath (Date dateOfDeath) {
		this._dateOfBirth = dateOfDeath;
	}
	
	public Date GetDateOfDeath () {
		return this._dateOfBirth;
	}
	
	
	public void SetName(String name) {
		this._name = name;		
	}
	
	public String GetName () {
		return this._name;
	}
}
