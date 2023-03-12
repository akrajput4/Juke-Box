package Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class functions {
	Database obj1;
	public void searchbygenere() throws ClassNotFoundException, SQLException
	{
		Statement statement =  obj1.getConnection().createStatement();
		 String query = "select distinct(genere) from songs ";
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			
			 System.out.println(rs.getString("genere"));
		 }
	}
	
	

	public void searchbyartist() throws ClassNotFoundException, SQLException
	{
		Statement statement =  obj1.getConnection().createStatement();
		 String query = "select distinct(artist) from songs ";
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			
			 System.out.println(rs.getString("artist")+" ");
		 }
	}
	public void searchbyformat() throws ClassNotFoundException, SQLException
	{
		Statement statement =  obj1.getConnection().createStatement();
		String a = "mp3";
		 String query = "select tittle from songs where format = '"+a+"'";
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			
			 System.out.println(rs.getString("tittle")+" ");
		 }
	}
	
	public void printbygenere(String genere) throws ClassNotFoundException, SQLException
	{
		Statement statement =  obj1.getConnection().createStatement();
		 String query = "select tittle from songs where genere = '"+genere+"'";
		 ResultSet rs = statement.executeQuery(query);
		 int count = 1;
		 while(rs.next())
		 {
			
			 System.out.println(count+ " "+rs.getString("tittle"));
			 count++;
		 }
		
	}
	
	public void printbyartist(String artist) throws ClassNotFoundException, SQLException
	{
		Statement statement =  obj1.getConnection().createStatement();
		 String query = "select tittle from songs where artist = '"+artist+"'";
		 ResultSet rs = statement.executeQuery(query);
		 int count = 1;
		 while(rs.next())
		 {
			
			 System.out.println(count+ " "+rs.getString("tittle"));
			 count++;
		 }
		
	}
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		functions obj2 = new functions();
		//obj2.printbygenere("Item song");
		obj2.printbyartist("AP Dhillon");
	}
	
	
}
