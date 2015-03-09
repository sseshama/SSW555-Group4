package familyTreeGedcom;

import java.util.Date;

class DateObject {
	Date _date;
	String _errorMessage;
	DateObject (Date date, String errorMessage) {_date = date; _errorMessage = errorMessage;}
	
	public Date Date() { return _date; }
	public String ErrorMessage() { return _errorMessage; }	
}

public class Individual {

	private String _identifier;
	
	private String _name;
	
	private DateObject _dateOfBirth;	
	
	private DateObject _dateOfDeath;
		
	public Individual ()
	{		
		this._dateOfBirth = new DateObject(null, "");
		this._dateOfDeath = new DateObject(null, "");
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
	
	public void SetDateOfBirth (DateObject dateOfBirth) {
		this._dateOfBirth = dateOfBirth;
	}
	
	public DateObject GetDateOfBirth () {
		return this._dateOfBirth;
	}
	
	public void SetDateOfDeath (DateObject dateOfDeath) {
		this._dateOfDeath = dateOfDeath;
	}
	
	public DateObject GetDateOfDeath () {
		return this._dateOfDeath;
	}
	
	
	public void SetName(String name) {
		this._name = name;		
	}
	
	public String GetName () {
		return this._name;
	}
}
