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
	private int [] oneLetterFrequency = new int [26];
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
	}
	/**
	 * Finds the one letter frequency of the wordList the Decryptor is currently using.
	 */
	public void findOneLetterFrequency ()
	{
		int totalLetters = 0;
		
		Utility.intitializeIntArrayToZero(oneLetterFrequency);
		
		for (int i = 0; i<wordList.size();i++)
		{
			String word = wordList.get(i).toString();
			for(int letterPosition = 0; letterPosition<word.length(); letterPosition++)
			{
				if (Character.isLetter(word.charAt(letterPosition)))
				{
					oneLetterFrequency[word.charAt(letterPosition)-'a']++;
					totalLetters++;
				}
			}
		}
		// test code will eventually be deleted
		for (int i = 0; i < oneLetterFrequency.length;i++)
		{
			System.out.println((char)('a'+ i) +": " + (double) oneLetterFrequency [i]/totalLetters);
		}
	}
}
