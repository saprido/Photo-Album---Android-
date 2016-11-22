package util;

import model.AlbumList;

public class UserSession 
{
	public static String username;
	public static AlbumList albumList = new AlbumList(username);
}
