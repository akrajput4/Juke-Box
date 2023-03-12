package Music;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class playaudio {
	
	public void play(List<String>songList) throws IOException, LineUnavailableException
	{
		long currentTime = 0;
		Scanner sca = new Scanner(System.in);
		Clip clip ;
		int a ;
		
		try
		{
			for(int i=0;i<songList.size();i++)
			{
				File songFile = new File(songList.get(i));
				AudioInputStream audioinputstream =AudioSystem.getAudioInputStream(songFile);
				clip = AudioSystem.getClip();
				clip.open(audioinputstream);
				currentTime = clip.getMicrosecondPosition();
				
				do
				{
					System.out.println("1-play \t 2-Stop\t 3-Resume \t 4-Loop \t 5-PlayNext \t 6-PlayPrevious\t 7-Exit ");
					System.out.println("Enter Choice");
					a= sca.nextInt();
					switch(a)
					{
					case 1 :
					
						clip.start();
						break ;
					
					case 2 :
					
						clip.stop();
						break;
					
					case 3 :
					
						clip.setMicrosecondPosition(currentTime);
						clip.start();
						break ;
					
					case 4 :
					
						clip.loop(clip.LOOP_CONTINUOUSLY);
						break;
					
					case 5:
					
				 	clip.close();
				 	if(i==songList.size()-1)
				 	{
				 		i=-1;
				 	}
						break;
					case 6 :
						i=i-2;
						a=5;
						clip.close();
						break;
					case 7 :
						clip.close();
						a=5;
						i=songList.size();
						break;
						
				}
				
			}while(a!=5);
			
		}
		
	}catch (UnsupportedAudioFileException ex)
		{
		ex.printStackTrace();
		}
		

}
	public void playpodcast(List<String>songList) throws IOException, LineUnavailableException
	{
		long currentTime = 0;
		Scanner sca = new Scanner(System.in);
		Clip clip ;
		int a ;
		
		try
		{
			for(int i=0;i<songList.size();i++)
			{
				File songFile = new File(songList.get(i));
				AudioInputStream audioinputstream =AudioSystem.getAudioInputStream(songFile);
				clip = AudioSystem.getClip();
				clip.open(audioinputstream);
				currentTime = clip.getMicrosecondPosition();
				
				do
				{
					System.out.println("1-play \t 2-Stop\t 3-Resume \t 4-ExitPlayer ");
					System.out.println("Enter Choice");
					a= sca.nextInt();
					switch(a)
					{
					case 1 :
					
						clip.start();
						break ;
					
					case 2 :
					
						clip.stop();
						break;
					
					case 3 :
					
						clip.setMicrosecondPosition(currentTime);
						clip.start();
						break ;
					case 4 :
						
						clip.close();
						a=5;
					
				}
				
			}while(a!=5);
				System.out.println("--------------------------Podcast Stopped--------------------------------");
				
			
		}
		
	}catch (UnsupportedAudioFileException ex)
		{
		ex.printStackTrace();
		}
		

}
}
