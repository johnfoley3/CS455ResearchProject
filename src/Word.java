import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author John Foley <jpf7324@truman.edu>
 */
public class Word {

	private String word;
	private String partialDecryption;

	// Typos on first letter are much more rare.  Max score 0.3
	public static final double MAX_SCORE_FOR_NO_FIRST_LETTER_MATCH = 0.3;


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

	@Override
	public String toString()
	{
		return  word;
	}
	
}
