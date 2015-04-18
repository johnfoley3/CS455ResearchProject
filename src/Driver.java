import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the main class to run.
 * @class Driver
 */
public class Driver
{

	/**
	 * Constructor
	 * @param args Command line arguments, one should be the file name
	 */
	public static void main(String[] args)
	{

		ArrayList<Word> normalWords = new ArrayList<Word>();
		ArrayList<String> apostropheList = new ArrayList<String>();

		readTextFile(args[0], normalWords, apostropheList);
	}

	/**
	 * Reads in the text file and outputs an array list of Words
	 * @param filename The text file to read in
	 * @param normalWords Array list of Words for normal cipher text
	 * @param apostropheList Array list of Strings for cipher text after apostrophes
	 */
	private static void readTextFile(
			final String filename, ArrayList<Word> normalWords, ArrayList<String> apostropheList) {

			try {

				Scanner reader = new Scanner(new File(filename));

				String word;

				while (reader.hasNext()) {

					word = reader.next().toLowerCase();

					// Scan for punctuation that we want to skip
					if (word.contains(";") || word.contains(".") || word.contains(",") ||
							word.contains("!") || word.contains("?")) {

						// Skip
						continue;

						// Add apostrophes to a different list
					} else if (word.contains("'")) {

						apostropheList.add(word.split("'")[1]);

						// Now we have a regular word
					} else {

						normalWords.add(new Word(word));
					}
				}

			} catch (FileNotFoundException e) {

				System.out.println("Could not open file.");
				e.printStackTrace();
				System.exit(-1);
			}
	};
}
