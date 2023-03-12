package Music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Podcast {

	public void displaypodcast() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
	Statement statement =connection.createStatement();
	 String query = "select distinct(category) from podcast ";
		
		 ResultSet rs = statement.executeQuery(query);
		 int count = 1;
		 while(rs.next())
		 {
			
			 System.out.println(count+". "+rs.getString("category"));
			 count++;
		 }
	}
	
	public void searchpodcast(String category) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		 String query = "select podcastid,episodeno,artist,category from podcastepisode where category = '"+category+"'";
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			
			 System.out.println(rs.getInt("podcastid")+"  "+rs.getInt("episodeno")+"  "+rs.getString("artist")+"  "+rs.getString("category"));
		 }		 		 
	}
	
	public List playpodcast(int a) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		 String query = "select url from podcastepisode where podcastid = "+a;
		 ArrayList<String> list1 = new ArrayList<String>();
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			 list1.add(rs.getString("url"));
		 }
		 
		 return list1;
		 
			 
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		Podcast obj5 = new Podcast();
		obj5.searchpodcast("science");
	}
}
