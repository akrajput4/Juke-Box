package Music;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
public class implementation2 {
	public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException, LineUnavailableException
	{
		Database obj1 = new Database();
		playaudio obj2 = new playaudio();
		functions obj3 = new functions();
		Podcast obj4 = new Podcast(); 
		playlist obj5 = new playlist();
		podcastplaylist obj6 = new podcastplaylist();
		Scanner sca =new Scanner(System.in);
		obj1.displaymenu1();
		int a = sca.nextInt();
		if(a==1)
		{
			obj1.newuser();
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			System.out.println("--------------Dear User You are Succesfully Registered Run Again and Login-------------------------------------");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
			System.exit(0);
			
		}
		else
			if(a==2)
			{
				obj1.checkdetails();
				int stop;
				do {
				obj1.displaymenu2();
				int b =sca.nextInt();
				if(b ==1)
				{
					Scanner sc = new Scanner(System.in);
					obj3.searchbygenere();
					System.out.println();
					System.out.println("Choose one genre");
					String ac = sc.nextLine();
					int ae = obj5.checkgenrepresent(ac);
					if(ae==2)
					{
					System.out.println();
					System.out.println("-----------------------------------------------------------    ");
					System.out.println("Selected Genre Playlist conatins Songs  : ");
					obj3.printbygenere(ac);
					System.out.println();
					List li = obj1.searchbygenre(ac);
					System.out.println();
					System.out.println("----------------Player---------------------------------------------");
					obj2.play(li);
					System.out.println("----------------------Playlist ends---------------------------");
					}
					else
						if(ae==1)
					{
						System.out.println();
						System.out.println("---------------Please Select the genere from the list provided------------------");
						System.out.println();
					}
					
				}
				else
					if(b==2)
					{
						Scanner sc = new Scanner(System.in);
						obj3.searchbyartist();
						System.out.println();
						System.out.println("Choose one artist : ");
						String ab= sc.nextLine();
						int ad = obj5.checkartistpresent(ab);
						if(ad==2)
						{
						System.out.println();
						System.out.println("-----------------------------------------------------------    ");
						System.out.println("Selected artist Playlist conatins Songs  : ");
						obj3.printbyartist(ab);
						System.out.println();
						List li = obj1.searchbyartist(ab);
						System.out.println();
						System.out.println("----------------Player---------------------------------------------");
						obj2.play(li);
						System.out.println("----------------------Playlist ends---------------------------");
						}
						else
							if(ad==1)
						{
							System.out.println();
							System.out.println("---------------Please Select the artist from the list provided------------------");
							System.out.println();
						}
						
					}
					else
						if(b==3)
						{
							Scanner sc = new Scanner(System.in);
							System.out.println();
							System.out.println("-------------------Podcast Categories-----------------------");
							obj4.displaypodcast();
							System.out.println("------------------------------------------------------------");
							System.out.println();
							System.out.println("Choose any one of the category mentioned :");
							String s = sc.nextLine();
							System.out.println();
							System.out.println("----------------------Availabe podcast on the Selected category-----------------");
							obj4.searchpodcast(s);
							System.out.println("--------------------------------------------------------------------------------");
							System.out.println();
							System.out.println("Input the podcast id to play a particular podcast");
							int id = sc.nextInt();
							List c = obj4.playpodcast(id);
							obj2.playpodcast(c);;
							
						}
						else
							if(b==4)
							{
								int ae;
								do {
									String category = "";
								System.out.println();
								System.out.println("Available playlist for the userid : "+obj1.userid);
								System.out.println();
								List l =obj5.searchforplaylists();
								List l1 = obj6.checkforpodcastplaylist();
								if(l.size()>0 || l1.size()>0)
								{
									Scanner scad = new Scanner(System.in);
									if(l.size()>0)
									{
										System.out.println("Available songs playlist are : ");
										obj5.searchforplaylist();
									}
									if(l1.size()>0)
									{
										System.out.println("Available podcast playlist are : ");
										obj6.searchforpodcastplaylist();
									}
									System.out.println();
									System.out.println("Input 0 to create a new Playlist or 1 to play the already present play list :");
									System.out.println("*********************************************************************************");
									System.out.println();
									int p = sca.nextInt();
									if(p==0)
									{
										System.out.println();
										System.out.println("Choose any one option : ");
										System.out.println("1.Create a Songs Playlist");
										System.out.println("2.Create a Podcast Playlist");
										Scanner io = new Scanner(System.in);
										int input = io.nextInt();										
										if(input == 1)
										{
											obj5.createplayist();
											
										}
										else
											if(input == 2)
											{
												obj4.displaypodcast();
												Scanner sc =new Scanner(System.in);
												 category = sc.next();
												obj6.printallpodcast(category);
												obj6.createpodcastplayist(category);
											}
									}
									else
										if(p==1)
										{
										
										System.out.println("Choose a category wants to play a playlist from songs or podcast :  ");
										System.out.println("1-Songs Playlist");
										System.out.println("2-Podcast Playlist");
										int q = sca.nextInt();
										if(q==1)
										{		
									System.out.println("Input the playlist Name You wanted 1 to play songs from the present lists : ");
									String playlistname = scad.nextLine();
									List songid = obj5.addsongidtolist(playlistname);
									System.out.println();
									System.out.println("------------------------------------------------------------------------------------------");
									System.out.println("The Available song in the Current Playlist : ");
									System.out.println();
									obj5.print(songid);
									List url = obj5.songsurl(songid);
									System.out.println();
									obj2.play(url);
									System.out.println("PLay Completed");
										}
										else
											if(q==2)
											{
												System.out.println("Input the playlist Name You wanted 1 to play songs from the present lists : ");
												String playlistname = scad.nextLine();
												List podcastid = obj6.addspodcasttolist("tech1");
												System.out.println();
												System.out.println("------------------------------------------------------------------------------------------");
												System.out.println("The Available Podcast in the Current Playlist : ");
												obj6.printpodcastlist(podcastid);
												System.out.println();
												List url = obj6.podcasturl(podcastid);
												System.out.println();
												obj2.play(url);
												System.out.println("PLay Completed");
												
												
												
											}
										}
											else {
												
											}
									
								}
								else
									if(l.size()==0 && l1.size()==0)
									{
										System.out.println("You Don't Have Any playlist on this userid : "+obj1.userid);
										System.out.println();
										System.out.println("Input 0 to create the playlist : ");
										int u = sca.nextInt();
										if(u==0)
										obj5.createplayist();
									}
								
								System.out.println("Input zero to exit the Playlist Section or any Number to play another play list or to create a new playlist ");
								ae=sca.nextInt();
								}while(ae!=0);
								
								
								
							}
							else
							{
								System.out.println("Input the correct choice");
								System.out.println("------------------------------------------------------------------------------------------");
							}
				System.out.println();
				System.out.println("Input any Number to continue Player  or to exit enter 0 ");
				stop = sca.nextInt();
				}while(stop!=0);
			}
				
			
			else
				if(a==3)
				{
					System.exit(0);
				}
				else
				{
					System.out.print("Input the correct choice and run the program again ");
					
					
				}
		
				}
			
		
	}



