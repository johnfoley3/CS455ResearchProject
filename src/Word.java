import java.util.ArrayList;

/**
 * @author John Foley <jpf7324@truman.edu>
 */
public class Word {

	private String word;

	/**
	 * Constructor
	 * @param word String to read in
	 */
	public Word(String word) {

		this.word = word;
	}

	/**
	 * Returns whether or not this is a matched word
	 * @return boolean Is a found word
	 */
	public boolean isWord() {

		return true;
	}

	public ArrayList<String> getPatterns() {

		ArrayList<String> output = new ArrayList<String>();

		// TODO : find patterns in the word (digraphs and trigraphs)

		return output;
	}
}
