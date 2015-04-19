import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author John Foley <jpf7324@truman.edu>
 */
public class Word {

	private String word;
	private String partialDecryption;

	/**
	 * Constructor
	 * @param word String to read in
	 */
	public Word(String word) {

		this.word = word;
	}

	/**
	 * Constructor
	 * @param word String to read in
	 * @param partialDecryption Partial Decryption to remember
	 */
	public Word (String word, String partialDecryption) {

		this.word = word;
		this.partialDecryption = partialDecryption;
	}

	/**
	 * Returns whether or not this is a matched word
	 * @return boolean Is a found word
	 */
	public boolean isWord() {


		return true;
	}
	
	public Word clone ()
	{
		return new Word (word,partialDecryption);
	}
	/**
	 * Return a list of digraphs and trigraphs found in the word
	 * @return ArrayList of String patterns
	 */
	public ArrayList<String> getPatterns() {

		ArrayList<String> output = new ArrayList<String>();

		// TODO : find patterns in the word (digraphs and trigraphs)

		return output;
	}

	@Override
	public String toString() {
		return word;
	}
}
