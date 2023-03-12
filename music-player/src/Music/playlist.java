package Music;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

public class playlist {
	Database obj1;
	
	Scanner sc = new Scanner(System.in);
	
	public void createplayist() throws ClassNotFoundException, SQLException
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
			printallsongs();
			System.out.println("Enter the Song id to enter in the playlist : ");
			int sid = sca.nextInt();
			String query ="Insert into playlist values("+sid+","+obj1.userid+",'"+listname+"')";
			statement.execute(query);
			 System.out.println("Input any number to add more songs to your playlist or to exit enter 0");
			 ab = sca.nextInt();
			
		}while(ab!=0);
		
		System.out.println();
		System.out.println("----------------Songs are successfully added-----------------------------------");
		System.out.println();
		
	}
	
	public void printallsongs() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select tittle ,songid from songs";
		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
        {
            System.out.format("%5s %45s\n",rs.getInt("songid"),rs.getString("tittle"));
        }
	
			
	}	
	
	public List addsongidtolist(String id) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		 String query = "select songid from playlist where playlistname ='"+id+"'";
		 ResultSet rs = statement.executeQuery(query);
		 ArrayList<Integer> list1 = new ArrayList<Integer>();
		 while(rs.next())
		 {
			 list1.add(rs.getInt("songid"));
			 
		 }
		 return list1;
		 
		
	}
	
	public void print(List l) throws SQLException, ClassNotFoundException
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
			
			
			String query = "select tittle from songs where songid in("+a+")";
			 rs = statement.executeQuery(query);
		int count = 1;
		 while(rs.next())
		 {
			 System.out.println(count+" "+rs.getString("tittle"));
			 count++;
			 
		 }
	}
	
	public List songsurl(List l) throws SQLException, ClassNotFoundException
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
		String query = "select url from songs where songid in("+a+")";
		 rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			 list2.add(rs.getString("url"));
			 
		 }
		 return list2;
	}
	
	public void searchforplaylist() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select distinct(playlistname) from playlist where userid ="+obj1.userid;
		ResultSet rs = statement.executeQuery(query);
		int count = 1;
		while(rs.next())
        {
            System.out.println(count+". "+rs.getString("playlistname"));
            count++;
        }
	
		
	}
	
	public List searchforplaylists() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select distinct(playlistname) from playlist where userid ="+obj1.userid;
		ResultSet rs = statement.executeQuery(query);
		ArrayList<String> list2 = new ArrayList<String>();
		while(rs.next())
        {
            list2.add(rs.getString("playlistname"));
           
        }
		return list2;
	
		
	}
	
	public boolean checkidpresent(int a) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select userid from users";
		ResultSet rs = statement.executeQuery(query);
		while(rs.next())
        {
            if(a==rs.getInt("userid"))
            {
            	return true ;
            }
            
           
        }
		return false;
		
	}
	
	
	public int checkartistpresent(String a) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select artist from songs";
		ResultSet rs = statement.executeQuery(query);
		int count =1;
		while(rs.next())
        {
            if(a.equals(rs.getString("artist")))
            {
            	count++ ;
            }
            
           
        }
		return count;
		
	}
	
	public int checkgenrepresent(String a) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
		Statement statement =connection.createStatement();
		String query = "select genere from songs";
		ResultSet rs = statement.executeQuery(query);
		int count =1;
		while(rs.next())
        {
            if(a.equals(rs.getString("genere")))
            {
            	count++ ;
            }
            
           
        }
		return count;
		
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException, LineUnavailableException
	{
		playlist obj5 = new playlist();
		playaudio obj4 = new playaudio();
		
		
	}
	   

}
