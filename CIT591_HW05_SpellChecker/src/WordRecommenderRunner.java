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
	 * Prompts the user repeatedly until a valid input file is given. A valid input file will end in .txt
	 * and must actually exist! Users input strings, but these strings represent {@link File} objects.
	 * @return A valid input file (a {@code File} object).
	 */
	public File getFileFromUser(Scanner userInputScanner) {
		
		// Initialize.
		boolean validInput = false;
		String currInput = "";
		File inputFile = null;
		
		// Prompt for input.
		System.out.println("Please enter the name of the file to be spell-checked. You must include the '.txt' extension.");
		
		// Validate that input is a valid .txt file name.
		while (!validInput) {
			
			// Make sure input is a String ending in .txt extension.
			currInput = userInputScanner.next();
			
			while (!currInput.endsWith(".txt")) {
				System.out.println("File name must include '.txt' extension.");
				currInput = userInputScanner.next();
			}
			
			// If input is a valid String, check that file exists.
			inputFile = new File(currInput);
			
			if (inputFile.exists()) {
				validInput = true;
			}
			else {
				System.out.println("File " + currInput + " not found. Please enter another file name.");
			}

		}
		
		return inputFile;
		
	}
	
	/**
	 * Parses a .txt file represented by a {@link File} object and returns an {@code ArrayList<String>} of all words found.
	 * @param inputFile The .txt file to be read from.
	 * @return An {@code ArrayList<String>} containing all the words in the input file.
	 */
	public ArrayList<String> readFileToList(File inputFile) {
		
		// Initialize result.
		ArrayList<String> wordsInFile = new ArrayList<String>();
		
		// Create a scanner for the file input, add all words in file to output list.
		try {
			Scanner fileInputScanner = new Scanner(inputFile);
			while (fileInputScanner.hasNext()) {
				wordsInFile.add(fileInputScanner.next());
			}
		} 
		
		// We handle FileNotFoundException in getFileFromUser, but catch-block included here.
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return wordsInFile;
	}
	
	/**
	 * Generates the name of the output file to be written to.
	 * @param inputFileName The name of the input file.
	 * @return The name of {@code inputFileName} with "_chk" appended to it (before ".txt").
	 */
	public String generateOutputFileName(String inputFileName) {
		return inputFileName.substring(0, (inputFileName.length() - 4)) + "_chk.txt";
	}
	
	/**
	 * Writes a list of words to a .txt file, separated by " ".
	 * @param fileName The name of the file to create. Assumed to be a .txt file. Note that the file is overwritten.
	 * @param outputWords An {@code ArrayList<String>} containing all the words to be written to the file.
	 */
	public void writeListToFile(String fileName, ArrayList<String> outputWords) {
		
		try {
			// Initialize FileWriter, PrintWriter objects.
			FileWriter fw = new FileWriter(fileName, false);
			PrintWriter pw = new PrintWriter(fw);
			
			// Write all words in outputWords to pw.
			for (String word : outputWords) {
				pw.print(word + " ");
			}
			
			// Flush pw.
			pw.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// MAIN METHOD
	public static void main(String[] args) {
		
		// Create WordRecommenderRunner object for use, selecting the dictionary for WordRecommender to use.
		WordRecommenderRunner runner = new WordRecommenderRunner(new WordRecommender("shortEngDictionary.txt"));
		
		// Create scanner object for user input.
		Scanner userInputScanner = new Scanner(System.in);

		// Welcome user to program.
		System.out.println("Welcome!");
		
		// Get file object to be spell-checked.
		File inputFile = runner.getFileFromUser(userInputScanner);
		
		// Read in file to ArrayList<String> of words in the file.
		ArrayList<String> inputWords = runner.readFileToList(inputFile);
		
		// Create new ArrayList<String> of words for output.
		ArrayList<String> outputWords = new ArrayList<String>();
		
		// Ask user for "N" in "topN" (and explain). Loop until input is valid.
		
		System.out.println("Reviewing words in " + inputFile.getName() + "...");
		// For each word in inputWords:
		for (String inputWord : inputWords) {
			// Check if inputWord is in wr.filename (dictionary).
			
			// If it is, place in outputWords and continue.
			outputWords.add(inputWord);
			// If it is not, then:		
			//     i) Call WordRecommender.getWordSuggestions
			//    ii) Prompt for user input; loop until input is valid.
			//   iii) Update input words with result of user input. (Maybe make ALL CAPS if updated?)
			
		}
		System.out.println("Review complete!");
		
		// Get name of output file
		String outputFileName = runner.generateOutputFileName(inputFile.getName());
		
		// Write outputWords to output file.
		runner.writeListToFile(outputFileName, outputWords);
		
		// End program, tell user where to find new file.
		System.out.println("Program complete! You can find your updated file in " + outputFileName + ".");
		
	}

}
