package util;
/*
	@author Syed Mahmood
    @author Sanjana Dodley
*/

import model.AlbumList;

/* Represents a Session for one user, after the session is over, data is persisted by being written a file representing
 * that user's album list.
 */
public class UserSession 
{
	public static String username;
	public static AlbumList albumList;
	public static AlbumList loadedAlbumList;
}
