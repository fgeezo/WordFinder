/**
 * Lists all dictionary words containing a 
 * substring of user-inputed text in any position.
 * 
 * Usable in "Word-Bomb" style games
 * 
 * @author Mirahn Alshamry, Daniel Dowlin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;    

public class WordFinder {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("words.txt"));
		ArrayList<String> words = new ArrayList<String>();
		while (scanner.hasNext()) {
			String currentWord = scanner.nextLine();
			words.add(currentWord);
		}	
		
		while (scanner.hasNext()) {
			String currentWord = scanner.nextLine();
			words.add(currentWord);
			for (String str : words) {
				if (!str.contains(currentWord)); {
					words.add(currentWord.replace("'", ""));
				}
			}
		}		
		
		ArrayList<String> dump = new ArrayList<String>();
			
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		boolean sort = false;
		
		while(true) {
			try {
				ArrayList<String> matches = new ArrayList<String>();
				System.out.print("Find word with: ");
				String search = input.nextLine();
				
				if (search.contains("1")) {
					System.out.println("Now sorting from shortest to longest.");
					sort = true;
				}
				
				else if (search.contains("2")) {
					System.out.println("Now sorting from longest to shortest.");
					sort = false;
				}
				
				else if (search.contains(".remove")) {
					String word = (search.substring(search.indexOf("("), 
							search.indexOf(")")));
					dump.add(word);
					
					for (String s : words) {
						if(s.contains(word)) {
							words.remove(s);
						}
					}
					
					System.out.println("Removed " + word + " from list.");
				}
				
				else if (search.contains(".restore")) {
					words.addAll(dump);
				}
				
				else {
					for (String s : words) {
						if (s.contains(search)) {
							matches.add(s);
						}
					}
					
					Set<String> set = new HashSet<>(matches);
					matches.clear();
					matches.addAll(set);
					matches.sort(Comparator.comparingInt(String::length));
					
					if (!sort) {
						Collections.reverse(matches);
					}
					if (matches.size()==0) {
						System.out.println("No words found with " + search);
					}
					else {
						for (String s : matches) {
							System.out.println(s);
						}
					}
				}
			} 
			catch (Exception e) {}
		}
	}
}
