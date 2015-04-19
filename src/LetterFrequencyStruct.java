import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class LetterFrequencyStruct
{
	private String letterSequence;
	private double frequency;
	public static final ArrayList <LetterFrequencyStruct> TRUE_ONE_LETTER_FREQUENCY = new ArrayList <LetterFrequencyStruct> ()
	{
		{
			try
			{
				Scanner reader = new Scanner(new File("res/oneLetterFrequency.txt"));
				
				for (int i = 0; i<26;i++)
				{
					String letterPattern = reader.nextLine();
					double freq =  Double.parseDouble (reader.nextLine());
					add (new LetterFrequencyStruct(letterPattern,freq));
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Could not open file.");
				e.printStackTrace();
				System.exit(-1);
			}
		}
	};
	public LetterFrequencyStruct (String letterSequence, double frequency)
	{
		this.letterSequence = letterSequence;
		this.frequency = frequency;
	}
	public static void sortByFrequency (ArrayList <LetterFrequencyStruct> list)
	{
		
		for (int outer = 0; outer < list.size()-1;outer++)
		{
			for (int inner = outer+1; inner< list.size();inner++)
			{
				if (list.get(outer).frequency<list.get(inner).frequency)
				{
					swap (list,outer,inner);
				}
			}
		}
	}
	private static void swap (ArrayList <LetterFrequencyStruct> list, int position1, int position2)
	{
		LetterFrequencyStruct temp  = list.get(position1).clone();
		list.set(position1, list.get(position2).clone());
		list.set(position2, temp);

	}
	public LetterFrequencyStruct clone ()
	{
		return new LetterFrequencyStruct(this.letterSequence, this.frequency); 
	}
	public String getLetterSequence ()
	{
		return letterSequence;
	}
	public double getFrequency ()
	{
		return frequency;
	}
}
