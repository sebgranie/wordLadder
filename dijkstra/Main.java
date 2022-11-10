import java.io.*;
import java.util.*;

/**
 program to find word ladder with shortest distance for two words in a dictionary
 distance between elements of the word ladder is the absolute difference in the
 positions of the alphabet of the non-matching letter
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // dictionary
		String word1 = args[1]; // first word
		String word2 = args[2]; // second word
  
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		// read in the data here

		// create graph here

		reader.close();

		// do the work here

		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("\nElapsed time: " + (end - start) + " milliseconds");
	}

}
