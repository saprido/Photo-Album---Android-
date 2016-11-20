package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileHandler 
{
	private static final String usersFileName = "usernames.txt";
	
	public static void addUserNames(List<String> usernames)
	{
		FileReader fileReader;
		try 
		{
			fileReader = new FileReader(usersFileName);
			BufferedReader reader = new BufferedReader(fileReader);
			String line;
			line = reader.readLine();
			while (line != null)
			{
				usernames.add(line);
				line = reader.readLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean doesUserNameExist(String username) 
	throws IOException
	{
		FileReader fileReader = new FileReader(usersFileName);
		BufferedReader reader = new BufferedReader(fileReader);
		String line;
		line = reader.readLine();
		while (line != null)
		{
			if (line.equals(username))
			{
				reader.close();
				return true;
			}
			line = reader.readLine();
		}
		reader.close();
		return false;
	}
}