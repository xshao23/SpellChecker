import java.util.*;
import java.io.*;

public class WordRecommenderRunner {

	// INSTANCE VARIABLES
	/**
	 * The {@link WordRecommender} object to use in running the program.
	 */
	WordRecommender wr;

	// CONSTRUCTORS
	/**
	 * Creates a {@code WordRecommenderRunner} object with a given {@link WordRecommender} object.
	 * @param wr The {@code WordRecommender} object to use in running the program.
	 */
	WordRecommenderRunner(WordRecommender wr) {
		
		this.wr = wr;
		
	}

	// METHODS
	/**
	 * Generates the name of the output file to be written to.
	 * @param inputFileName The name of the input file.
	 * @return The name of {@code inputFileName} with "_chk" appended to it (before ".txt").
	 */
	public String generateOutputFileName(String inputFileName) {
		return inputFileName.substring(0, (inputFileName.length() - 4)) + "_chk.txt";
	}
	
	
	// MAIN METHOD
	public static void main(String[] args) {
		
		// Initialize objects.
		// - WordRecommender object, selecting the dictionary file.
		WordRecommender wr = new WordRecommender("shortEngDictionary.txt");
		// - WordRecommenderRunner object for use, selecting the dictionary for WordRecommender to use.
		WordRecommenderRunner runner = new WordRecommenderRunner(wr);		
		// - Create scanner object for user input.
		Scanner userInputScanner = new Scanner(System.in);
		// - IOUtility object to handle I/O.
		IOUtility ioHelper = new IOUtility();

		// Welcome user to program.
		System.out.println("Welcome!");
		
		// Get file object to be spell-checked.
		System.out.println("What file would you like to spell-check?");
		File inputFile = ioHelper.getTxtFileFromUser(userInputScanner);
		System.out.println("Reviewing words in " + inputFile.getName() + "...");
		
		// Read in file to ArrayList<String> of words in the file.
		ArrayList<String> inputWords = ioHelper.readFileToList(inputFile);
		
		// Create new ArrayList<String> of words for output.
		ArrayList<String> outputWords = new ArrayList<String>();		
		
		// For each word in inputWords:
		for (String inputWord : inputWords) {

			// Check if inputWord is in wr.filename (dictionary).
			if (wr.wordsInDict.contains(inputWord)) {
			
				// If it is, place in outputWords and continue.
				outputWords.add(inputWord);
				continue;
				
			}
			
			// If it is not, then:
			else {
				// Report to user.
				System.out.println("'" + inputWord + "' not found in dictionary.");
				
				// Get suggested replacements.
				ArrayList<String> suggestions = wr.getWordSuggestions(inputWord, 3, 0.75, 5);
				
				// Prompt for user input; loop until input is valid.
				
				// Update input words with result of user input. (Maybe make ALL CAPS if updated?)
				continue;
			}
			
		}

		System.out.println("Review complete!");
		
		// Get name of output file
		String outputFileName = runner.generateOutputFileName(inputFile.getName());
		
		// Write outputWords to output file.
		ioHelper.writeListToFile(outputFileName, outputWords);
		
		// End program, tell user where to find new file.
		System.out.println("Program complete! You can find your updated file in " + outputFileName + ".");
		
	}

}
