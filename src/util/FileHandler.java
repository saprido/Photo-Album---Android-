package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	

	public static boolean doesUserNameExist(String username)
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
	
	public static void addUsername(String username)
	throws IOException
	{
		File file = new File("usernames.txt");
		FileWriter fileWriter = new FileWriter(usersFileName, true);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		String write = file.length() == 0 ? username : "\n" + username;
		writer.write(write);
		writer.close();
	}
	
	public static void deleteUsername(String username)
	throws IOException
	{
		List<String> usernames = new ArrayList<String>();
		FileReader fileReader = new FileReader(usersFileName);
		BufferedReader reader = new BufferedReader(fileReader);
		String line;
		line = reader.readLine();
		while (line != null)
		{
			if (!line.equals(username)) usernames.add(line);
			line = reader.readLine();
		}
		System.out.println(usernames);
		File file1 = new File("usernames.txt");
		file1.delete();
		File file = new File("usernames.txt");
		file.createNewFile();
		
		FileWriter fileWriter = new FileWriter(usersFileName, true);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		if (usernames.size() == 0) return;
		writer.write(usernames.get(0));
		for (int i = 1; i < usernames.size(); i++)
		{
			writer.write("\n" + usernames.get(i));
		}
		writer.close();
		
	}
	
	//Creates a file for each user
	public static void createUserFile(String username) 
	throws IOException
	{
		File file = new File(username + "albums.dat");
		file.createNewFile();
	}
	
	//Deletes the file for a user
	public static void deleteUserFile(String username)
	{
		File file = new File(username + "albums.dat");
		file.delete();
	}
}