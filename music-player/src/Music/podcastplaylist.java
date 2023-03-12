package Music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class podcastplaylist {
	Database obj1;
	public void printallpodcast(String category ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select podcastname ,podcastid from podcast where category = '"+category+"'";
		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
        {
            System.out.format("%5s %45s\n",rs.getInt("podcastid"),rs.getString("podcastname"));
        }
	
			
	}	
	
	
	public void createpodcastplayist(String category) throws ClassNotFoundException, SQLException
	{
		
		System.out.println("Enter The Playlist Name : ");
		Scanner sca = new Scanner(System.in);
		String  listname = sca.nextLine();
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		int ab;
		do
		{
			printallpodcast(category);
			System.out.println("Enter the podcastid to enter in the playlist : ");
			int sid = sca.nextInt();
			String query ="Insert into playlist values(null,"+obj1.userid+",'"+listname+"',"+sid+")";
			statement.execute(query);
			 System.out.println("Input any number to add more songs to your playlist or to exit enter 0");
			 ab = sca.nextInt();
			
		}while(ab!=0);
		
		System.out.println();
		System.out.println("-----------------------Podcast are successfully added-----------------------------------");
		System.out.println();
		
	}
	
	public void searchforpodcastplaylist() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select distinct(playlistname) from playlist where userid ="+obj1.userid+" and podcastid>0";
		ResultSet rs = statement.executeQuery(query);
		int count = 1;
		while(rs.next())
        {
            System.out.println(count+". "+rs.getString("playlistname"));
            count++;
        }
		
	}
	
	
	public List checkforpodcastplaylist() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select distinct(playlistname) from playlist where userid ="+obj1.userid+" and podcastid>0";
		ResultSet rs = statement.executeQuery(query);
		ArrayList<String> list1 = new ArrayList<String>();
		while(rs.next())
        {
            list1.add(rs.getString("playlistname"));
           
        }
		return list1;
		
	}
	public List addspodcasttolist(String id) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		 String query = "select podcastid from playlist where playlistname ='"+id+"'";
		 ResultSet rs = statement.executeQuery(query);
		 ArrayList<Integer> list1 = new ArrayList<Integer>();
		 while(rs.next())
		 {
			 list1.add(rs.getInt("podcastid"));
			 
		 }
		 return list1;
		 
		
	}
	
	
	public void printpodcastlist(List l) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();	
		 ResultSet rs = null;
		 String a ="";
		 for(int i=0;i<l.size();i++)
			{
				if(i<l.size()-1)
				{
				 a = a+l.get(i)+",";
				}else
					if(i==l.size()-1)
					{
						a=a+l.get(i);
					}	 
			}
			
			
			String query = "select podcastname from podcast where podcastid in("+a+")";
			 rs = statement.executeQuery(query);
		int count = 1;
		 while(rs.next())
		 {
			 System.out.println(count+" "+rs.getString("podcastname"));
			 count++;
			 
		 }
	}

	public List podcasturl(List l) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		ArrayList<String> list2 = new ArrayList<String>();
		 ResultSet rs = null;
		 String a ="";
		for(int i=0;i<l.size();i++)
		{
			if(i<l.size()-1)
			{
			 a = a+l.get(i)+",";
			}else
				if(i==l.size()-1)
				{
					a=a+l.get(i);
				}	 
		}
		
		//System.out.println(a);
		String query = "select url from podcastepisode where podcastid in("+a+")";
		//System.out.print(query);
		 rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			 list2.add(rs.getString("url"));
			 
		 }
		 return list2;
	}
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		podcastplaylist obj1 = new podcastplaylist();
		//obj1.printallpodcast("science");
		//obj1.createpodcastplayist("science");
		//obj1.searchforpodcastplaylist();
		obj1.addspodcasttolist("science");

	}

}
