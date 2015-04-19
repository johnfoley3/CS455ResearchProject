import java.util.ArrayList;
import java.util.Random;


public class Key
{
	private static final ArrayList <String> alphabet = new ArrayList <String> (26)
	{
		{
			for (int i = 0; i<26;i++)
			{
				add(Character.toString((char)('a'+i)));
			}
		}
	};
	public ArrayList <String> theKey = new ArrayList <String> (26);
	/**
	 * Constructor
	 */
	Key ()
	{
		generateKey();
	}
	/**
	 * Constructor
	 * @param key uses a predefined key set by user
	 */
	Key (String key)
	{
		for (int i = 0; i < key.length();i++)
		{
			theKey.add(Character.toString(key.charAt(i)));
		}
	}
	
	/**
	 * Creates a randomly generated key
	 */
	public void generateKey()
	{
		ArrayList <Boolean> letterUsed = new ArrayList <Boolean> ();
		for (int i = 0;i<26; i++)
		{
			letterUsed.add(false);
		}
		Random randomGenerator = new Random();
		int lettersAdded = 0;
		while (lettersAdded <26)
		{
			int testNum = randomGenerator.nextInt(26);
			if(letterUsed.get(testNum) == false)
			{
				letterUsed.set(testNum,true);
				theKey.add(alphabet.get(testNum));
				lettersAdded++;
			}
		}

	}
	private String getEncryptedSubstiution (char unencrypted)
	{
		if (Character.isLetter(unencrypted))
		{
			int index = (int)unencrypted -(int)'a';
			return theKey.get(index);
		}
		return Character.toString(unencrypted);
	}
	
	private String getDecryptedSubstiution (char encrypted)
	{
		if (Character.isLetter(encrypted))
		{
			boolean hasFoundLetter = false;
			int i = 0;
			while (!hasFoundLetter)
			{
				if (theKey.get(i).equals(Character.toString(encrypted)))
				{
					hasFoundLetter = true;
				}
				else
				{
					i++;
				}
			}
			return alphabet.get(i);
		}
		else
		{
			return Character.toString(encrypted);
		}
	}
	
	/**
	 * Encrypts a string of text.  It encrypts all alphabetical data and preserves
	 * all other characters.
	 * @param unencrypted the text that is supposed to be encrypted
	 * @return the encrypted text
	 */
	public String encrypt (String unencrypted)
	{
		String encrypted = "";
		for (int i= 0;i <unencrypted.length();i++)
		{
			encrypted += getEncryptedSubstiution (unencrypted.charAt(i));
		}
		return encrypted;
	}
	
	public String decrypt (String encrypted)
	{
		String unencrypted = "";
		for (int i= 0;i <encrypted.length();i++)
		{
			unencrypted += getDecryptedSubstiution (encrypted.charAt(i));
		}
		return unencrypted;
	}
	
	public void charBind (char trueLetter, char encryptedLetter)
	{
		String temp = theKey.get((int) (trueLetter)-'a');
		boolean hasFoundLetter = false;
		int i = 0;
		while (!hasFoundLetter)
		{
			if (theKey.get(i).equals(temp))
			{
				hasFoundLetter = true;
			}
			else
			{
				i++;
			}
		}
		theKey.set(i, temp);
		theKey.set((int) trueLetter- 'a', Character.toString(encryptedLetter));
	}
	
	public String getKey ()
	{
		String temp = "";
		for (int i = 0; i< theKey.size();i++)
		{
			temp += theKey.get(i);
		}
		return temp;
	}
}
