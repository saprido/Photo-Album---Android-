package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler 
{
	private String fileName;
	//private BufferedReader bufferedReader;
	
	public FileHandler(String fileName)
	{
		this.fileName = fileName;
	}
	
	public boolean doesUserNameExist(String username) 
	throws IOException
	{
		FileReader fileReader = new FileReader(fileName);
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