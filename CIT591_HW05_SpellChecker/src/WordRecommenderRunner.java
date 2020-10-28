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
	 * Gets user input(s) to determine how to update a given word (when not found in dictionary). User decides whether to
	 * (1) replace word from a list of recommendations, (2) leave as-is, or (3) directly type replacement.
	 * Additional helper methods may be used depending on option selected.
	 * @param currWord The word from input file to be replaced (or kept) in output file.
	 * @param wr {@link WordRecommender} object to use.
	 * @param userInputScanner A {@link Scanner} object to get user input.
	 * @return The word to place in output file as replacement for input word.
	 */
	public String getOutputWord(String currWord, WordRecommender wr, Scanner userInputScanner) {
		
		// Initialize variable to track whether wr can come up with suggestions.
		boolean suggestionsAvailable = false;
		
		// Initialize options list.
		ArrayList<String> editOptions = new ArrayList<String>();
		editOptions.add("r");
		editOptions.add("a");
		editOptions.add("t");
		
		
		// Get suggestions from WordRecommender.
		ArrayList<String> suggestions = wr.getWordSuggestions(currWord, 3, 0.75, 5);

		// If there are suggestions available, show them, then options.
		if (suggestions.size() > 0) {
			suggestionsAvailable = true;
			System.out.println("The following suggestions are available as replacements:");
			wr.prettyPrint(suggestions);
			System.out.println("You can select one of the following options: 'r' to use a suggested replacement, 'a' to leave as-is, 't' to type in replacement.");
		}
		
		// Otherwise, report that there are no suggestions and remove option, then show options.
		else {
			System.out.println("Sorry. There are no suggestions are available as replacements in our dictionary!");
			editOptions.remove("r");
			System.out.println("You can select one of the following options: 'a' to leave as-is, 't' to type in replacement.");
		}
		
		// Get user choice: 'r' to replace word, 'a' to leave as-is, 't' to type directly.
		String selectedEditOption = IOUtility.getUserChoiceFromArrayList(editOptions, userInputScanner);
		
		// Handle user choice 'r': prompt for selection of recommended word options.
		if (selectedEditOption.equals("r")) {
			System.out.println("Please type in the number of the suggested word to use as replacement.");
			editOptions.clear();
			for (int i = 0; i < suggestions.size(); i ++) {
				editOptions.add(Integer.toString(i + 1));
			}
			selectedEditOption = IOUtility.getUserChoiceFromArrayList(editOptions, userInputScanner);
			return suggestions.get(Integer.parseInt(selectedEditOption) - 1);
		}

		// Handle user choice 'a': just return currWord unchanged.
		else if (selectedEditOption.equals("a")) {
			return currWord;
		}
		
		// Handle user choice 't': get user input via scanner and return.
		else if (selectedEditOption.equals("t")) {
			System.out.println("Please type in the desired replacement word.");
			String enteredReplacement = userInputScanner.next();
			return enteredReplacement;
		}
		
		// Otherwise, return null.
		else {
			return null;
		}
	}

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
				System.out.println("---------------------------------------------------------------------------");
				System.out.println("'" + inputWord + "' not found in dictionary.");
				
				// Update outputWords with result of user decision(s). (In ALL CAPS for review.)
				String updatedWord = runner.getOutputWord(inputWord, wr, userInputScanner);
				outputWords.add(updatedWord.toUpperCase());
				if (!updatedWord.equals(inputWord)) {
					System.out.println("'" + updatedWord + "' used as replacement for '" + inputWord + "'.");
				}
				else {
					System.out.println("'" + inputWord + "' left as-is.");
				}
				
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
