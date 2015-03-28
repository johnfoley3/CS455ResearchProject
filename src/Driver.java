import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver
{

	/**
	 * Constructor
	 * @param args Command line arguments, one should be the file name
	 */
	public static void main(String[] args)
	{

		ArrayList<Word> text = new ArrayList<Word>();
	}

	/**
	 * Reads in the text file and outputs an array list of Words
	 * @param filename The text file to read in
	 * @return Array List of words
	 */
	private ArrayList<Word> readTextFile(final String filename) {

		ArrayList<Word> output = new ArrayList<Word>();

		try {

			Scanner reader = new Scanner(new File(filename));

			while (reader.hasNext()) {

			}

		} catch (FileNotFoundException e) {

			System.out.println("Could not open file.");
			e.printStackTrace();
			System.exit(-1);
		}

		return output;
	};
}
