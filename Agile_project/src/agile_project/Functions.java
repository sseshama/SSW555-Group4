package agile_project;

import java.util.HashMap;

public class Functions 
{
	public String unique_id(HashMap<Integer, String> file)
	{
		String err_msg = " ";
		int line = 1;
		String text,text1;
		String identifiers[];
		(line > 0 && line <file.size())
		{
			String data = file.get(line);
			//System.out.println(data);
			text = data.substring(2,4);
			//text1 = data.substring(6, 8);
			//System.out.println(text1);
	//		if(data.startsWith("1") && (data.substring(6, 8).equals("@I")))
				//System.out.println(data);
			if(data.startsWith("0") && (text.equals("@F")))
			{
				System.out.println( data.substring(7,9));
			}
			line++;
		}
		
		
		return err_msg;
	}
}
