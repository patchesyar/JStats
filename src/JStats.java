

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * JStats is a utility that (upon completion) pulls recent
 * results for a player from sizzlingstats.com and prints them to console
 * There is potential for it to find stats specific to the player as well,
 * I have not decided to prioritize this yet.
 * 
 * @author John Lavoie
 *
 */


public class JStats {

	/**
	 * 
	 * @param args In final version, contains the steam id (any format) of the inquired player
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.print("Insert the steam64 id: ");
		Scanner sc= new Scanner(System.in);
		String playerid= sc.next();
		System.out.printf("\n");
		//String sitename="http://sizzlingstats.com/player/"+playerid;
		//Not using sizzlingstats as I try to work my way into JSoup. Soon™ 
		//String sitename="https://www.cs.rit.edu/~csci142/Labs/06/Maze.html";
		String sitename="http://steamidconverter.com/"+playerid;
		String steam64=convert(sitename);
		System.out.println("sizzlingstats.com/player/"+steam64);
		sc.close();
	}

	/**
	 * getLinks is a function which returns an ArrayList of Strings representing
	 * all the links in the website
	 * 
	 * @param sitename url of the site to be visited
	 * @return ArrayList of all links in the target site
	 * @throws IOException
	 */
	private static ArrayList<String> getLinks(String sitename) throws IOException {
		ArrayList<String> foundLinks= new ArrayList<String>();
		Document doc=Jsoup.connect(sitename).get();
		Elements links=doc.select("a[href]");
		for (Element link: links){
			foundLinks.add(link.attr("abs:href"));
		}
		
		return foundLinks;
	}
	
	/**
	 * 
	 * @param sitename
	 * @return
	 * @throws IOException 
	 */
	private static String convert(String sitename) throws IOException{
		String steam64=new String();
		ArrayList<String> links= new ArrayList<String>();
		links=getLinks(sitename); 
		for(String link: links){
			System.out.println(link);
			if(link.contains("profiles")){
				steam64=link.substring(35); //this should be where the steam64 starts
			}
		}
		return steam64;
	}
}
