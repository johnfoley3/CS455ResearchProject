import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Andrew Paulman
 * Decryptor
 */

public class Decryptor
{
	
	private ArrayList <Word> wordList= new ArrayList <Word> ();
	private ArrayList <LetterFrequencyStruct> oneLetterFrequency = new ArrayList <LetterFrequencyStruct> ();
	private Key theKey = new Key ();
	
	/**
	 * Finds how frequently letters are used in text.
	 * @param normalWords the word list to be analyzed
	 */
	
	public Decryptor (ArrayList <Word> wordList)
	{
		for (int i = 0; i < wordList.size();i++)
		{
			this.wordList.add(wordList.get(i).clone());
		}
		findOneLetterFrequency();
		generateFrequencyBasedKey ();
	}
	
	/**
	 * Finds the one letter frequency of the wordList the Decryptor is currently using.
	 */
	public void findOneLetterFrequency ()
	{
		int [] oneLetterTallySheet = new int [26];
		int totalLetters = 0;
		
		Utility.intitializeIntArrayToZero(oneLetterTallySheet);
		
		for (int i = 0; i<wordList.size();i++)
		{
			String word = wordList.get(i).toString();
			for(int letterPosition = 0; letterPosition<word.length(); letterPosition++)
			{
				if (Character.isLetter(word.charAt(letterPosition)))
				{
					oneLetterTallySheet[word.charAt(letterPosition)-'a']++;
					totalLetters++;
				}
			}
		}
		for (int i = 0; i < oneLetterTallySheet.length;i++)
		{
			oneLetterFrequency.add (new LetterFrequencyStruct ( Character.toString((char) ('a' +i)),(double) oneLetterTallySheet [i]/totalLetters));
		}
	}
	
	public void generateFrequencyBasedKey ()
	{
		LetterFrequencyStruct.sortByFrequency(oneLetterFrequency);
		for (int i = 0; i < oneLetterFrequency.size();i++)
		{
			theKey.charBind(LetterFrequencyStruct.TRUE_ONE_LETTER_FREQUENCY.get(i).getLetterSequence().charAt(0), oneLetterFrequency.get(i).getLetterSequence().charAt(0));
		}
	}
	public String getKeyGuess ()
	{
		return theKey.getKey();
	}
}
