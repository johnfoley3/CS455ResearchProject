import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the main class to run.
 * @class Driver
 */

public class Driver
{

	// Typos on first letter are much more rare.  Max score 0.3
	public static final double MAX_SCORE_FOR_NO_FIRST_LETTER_MATCH = 0.3;

	/**
	 * Constructor
	 * @param args Command line arguments, one should be the file name
	 */
	public static void main(String[] args)
	{

		ArrayList<Word> normalWords = new ArrayList<Word>();
		ArrayList<String> apostropheList = new ArrayList<String>();
		// TODO: Read in dictionary file to arraylist of words
		Key theKey = new Key ();
		System.out.println(theKey.getKey());
		encryptText (theKey,"Source.txt","EncryptedSrc.txt");
		readTextFile("EncryptedSrc.txt", normalWords, apostropheList);
		Decryptor decrypt = new Decryptor(normalWords);
		theKey =  new Key (decrypt.getKeyGuess());
		decryptText (theKey,"EncryptedSrc.txt","Solution.txt");
		System.out.println(theKey.getKey());

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
	}

	/**
	 * Reads in the text file and outputs an encrypted version using the key to writeFile
	 * @param theKey The key that is being used to encrypt the text.
	 * @param srcFile The file text is being taken from
	 * @param writeFile The file the encrypted text is written to.
	 */
	public static void encryptText (Key theKey, final String srcFile, final String writeFile)
	{
		String encrypted = "";
		try {

			Scanner reader = new Scanner(new File(srcFile));

			String line;

			while (reader.hasNextLine()) {

				line = reader.nextLine().toLowerCase();
				encrypted += theKey.encrypt(line) + "\n";
			}


		} 
		catch (FileNotFoundException e) 
		{

			System.out.println("Could not open file.");
			e.printStackTrace();
			System.exit(-1);
		}

		try
		{
		    BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(new File(writeFile)));
		    writer.write (encrypted);

		    //Close writer
		    writer.close();
		    
		} 
		
		catch (IOException e)
		{
			System.out.println("Could not write to file.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	public static void decryptText (Key theKey, final String srcFile, final String writeFile)
	{
		String decrypted = "";
		try {

			Scanner reader = new Scanner(new File(srcFile));

			String line;

			while (reader.hasNextLine()) {

				line = reader.nextLine().toLowerCase();
				decrypted += theKey.decrypt(line) + "\n";
			}


		} 
		catch (FileNotFoundException e) 
		{

			System.out.println("Could not open file.");
			e.printStackTrace();
			System.exit(-1);
		}

		try
		{
		    BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(new File(writeFile)));
		    writer.write (decrypted);

		    //Close writer
		    writer.close();
		    
		} 
		
		catch (IOException e)
		{
			System.out.println("Could not write to file.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// TODO: make this work for Word searchWord instead of Strings
	/**
	 * Scores a word against a candidate word
	 * @param searchWord Base word
	 * @param candidateWord Word to score against
	 * @return score of the fuzzy matched word. Will be <= 1.0
	 */
	public double scoreWord(String searchWord, String candidateWord) {

		if (searchWord.equals(candidateWord)) {

			return 1.0;
		}

		int editDistance = StringUtils.getLevenshteinDistance(
				searchWord, candidateWord);

		// Normalize for length:
		double score =
				(candidateWord.length() - editDistance) / candidateWord.length();

		// Artificially reduce the score if the first letters don't match
		if (searchWord.charAt(0) != candidateWord.charAt(0)) {
			score = Math.min(score, MAX_SCORE_FOR_NO_FIRST_LETTER_MATCH);
		}

		// We may want to generate another score
		return Math.max(0.0, Math.min(score, 1.0));
	}
}
