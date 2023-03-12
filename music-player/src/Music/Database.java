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

public class Database {
	playaudio obj2 ;
	playlist obj6 = new playlist();
	 static int userid ;
	
	 public static Connection getConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "Amit@1234@");
	        return connection;

	    }
	 public void checkdetails() throws SQLException, ClassNotFoundException
	 {
		 System.out.println();
		 System.out.println("-------------Please Input the Login Credentials----------------");
		 System.out.println();
		 Scanner sca = new Scanner(System.in);
		 System.out.println("Input Your userid : ");
		 int id = sca.nextInt();
		 userid = id;
		 System.out.println("Input the Password : ");
		 String pass = sca.next();
		 Statement statement =  getConnection().createStatement();
		 String query = "select userid,password,username from users ";
		 ResultSet rs = statement.executeQuery(query);
		 int count = 0;
		 while(rs.next())
		 {
			 if(id==rs.getInt("userid")&&pass.equals(rs.getString("password")))
			 {
				 System.out.println();
				 System.out.println("Welcome "+rs.getString("username"));
				 //displaymenu2();
				 count++;
			 }
		 }
		 System.out.println();
		 if(count==0)
		 {
			 System.out.println("You are not a valid user or the Login credentials are Wrong Choose any option from below");
			 System.out.println("1-> Want to register         2-> Try login again      3->Play Music Directly");
			 int a = sca.nextInt();
			 if(a==1)
			 {
				 newuser();
			 }
			 else
				 if(a==2)
				 {
					 checkdetails();
				 }
				 else
					 if(a==3)
					 {
						displaymenu1();
					 }
		 }
	 }
	 
	 public void displaymenu1()
	 {
		 System.out.println("-------------------------------Welcome To The Jukebox Music Player------------------------------------------------");
		 System.out.println();
		 System.out.println("------------------------------------------------------------------------------------------------------------------");
		 System.out.print("1-New User Registeration        ");
		 System.out.print("2-Already a User Signup         ");
		 System.out.println("3-Exit");
		 System.out.println("----------------------------------------------------------------------------------------------------------------");
		 System.out.println();
		 
	 }
	 
	 public void newuser() throws ClassNotFoundException, SQLException
	 {
		 System.out.println();
		 System.out.println("-----------------Welcome to the registration process please enter the details asked below ---------------------- ");
		 Scanner sca = new Scanner(System.in);
		 Statement statement =  getConnection().createStatement();
		 System.out.println();
         System.out.println("Enter your userid 4 digits numeric value  :");
         int userid = sca.nextInt();
         boolean a = obj6.checkidpresent(userid);
         if(a==true)
         {
        	 System.out.println();
        	 System.out.println("*********************** WARNING MESSAGE ********************************************************************");
        	 System.out.println("userid Already present input the different user id and process will start again :");
        	 System.out.println();
        	 System.out.println();
        	 System.out.println("**************************************************************************************************************");
        	 newuser();
        	 
         }
         else
        	 if(a==false)
        	 {
         System.out.println("Enter your username : ");
         String username = sca.nextLine();
         System.out.println("Enter yout email :");
         String email = sca.nextLine();
         System.out.println("Create a Password :");
         String password = sca.nextLine();
         String query = "insert into users values('"+username+"','"+email+"',"+userid+",'"+password+"')";
         statement.executeUpdate(query);
        	 }
         
       
	 }
	 
	 public void displaymenu2()
	 {
		 System.out.println();
		 System.out.println("-----------------------------------Choose Options--------------------------------------");
		 System.out.println();
        System.out.print("1-Play music by Genere        ");
        System.out.print("2-Play music by Artist        ");
        System.out.print("3-Play Podcast                ");
        System.out.println("4-Play your playlist");
        System.out.println("---------------------------------------------------------------------------------------");
	 }
	 
	 public List searchbyartist(String artist) throws ClassNotFoundException, SQLException
	 {
		 Statement statement =  getConnection().createStatement();
		 String query = "select url from songs where artist ='"+artist+"'";
		 ResultSet rs = statement.executeQuery(query);
		 ArrayList<String> list1 = new ArrayList<String>();
		 while(rs.next())
		 {
			 list1.add(rs.getString("url"));
			// System.out.println(rs.getString("tittle"));
		 }
		 return list1;
		 
	 }
	 
	 public List searchbygenre(String genre) throws ClassNotFoundException, SQLException, IOException, LineUnavailableException
	 {
		 Statement statement =  getConnection().createStatement();
		 String query = "select url from songs where genere ='"+genre+"'";
		 ArrayList<String> list1 = new ArrayList<String>();
		 ResultSet rs = statement.executeQuery(query);
		 while(rs.next())
		 {
			 list1.add(rs.getString("url"));
		 }
		 
		 return list1;
		 
	 }
	 
	 public List playallsongs(String mp3) throws ClassNotFoundException, SQLException
	 {
		 Statement statement =  getConnection().createStatement();
		 String query = "select url from songs where format ='"+mp3+"'";
		 ResultSet rs = statement.executeQuery(query);
		 ArrayList<String> list1 = new ArrayList<String>();
		 while(rs.next())
		 {
			 list1.add(rs.getString("url"));
		 }
		 return list1;
		 
	 }
	 public static void main(String args[]) throws ClassNotFoundException, SQLException
	 {
		 Database obj = new Database();
		// obj.displaymenu1();
	      // obj.newuser();
		// obj.searchbyartist("kumar sanu");
		 //obj.searchbygenre("Item song");
	 }

}
