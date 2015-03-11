package familyTreeGedcom;

import java.util.ArrayList;
import java.util.Date;

public class Family {

	private String _husband;
	private String _wife;
	private DateObject _marriageDate;
	private ArrayList<String> _children;
	
	public void SetHusband(String husband) {
		_husband = husband;
		
	}
	
	public String GetHusband () {
		// just adding a random comment here. Will remove later
		return _husband;
	}

	public void SetWife(String wife) {
		_wife = wife;		
	}
	
	public String GetWife () {
		return _wife;
	}
	
	public void SetMarriageDate(DateObject mDate) {
		_marriageDate = mDate;		
	}
	
	public DateObject GetMarriageDate () {
		if (_marriageDate == null)
		{
			_marriageDate = new DateObject(new Date(), "");
		}
	
		return _marriageDate;
	}
	
	public ArrayList<String> Children() {
		if (this._children == null)
			this._children = new ArrayList<String>();
		
		return this._children;
	}
}
