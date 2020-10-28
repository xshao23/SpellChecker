import java.util.*;
import java.io.*;

/**
 * A class that has I/O methods used by both {@link WordRecommender} and {@link WordRecommenderRunner}.
 * @author Jackson Golden
 */
public class IOUtility {

	// INSTANCE VARIABLES
	// No instance variables.
	
	// CONSTRUCTORS
	// Default constructor used.

	// METHODS
	/**
	 * Prompts the user repeatedly until a valid input file is given. A valid input file will end in .txt
	 * and must actually exist! Users input strings, but these strings represent {@link File} objects.
	 * @param userInputScanner A {@link Scanner} object to get user input.
	 * @return A valid input file (a {@code File} object).
	 */
	public File getTxtFileFromUser(Scanner userInputScanner) {
		
		// Initialize.
		boolean validInput = false;
		String currInput = "";
		File inputFile = null;
		
		// Prompt for input.
		System.out.println("Please enter the name of the file. You must include the '.txt' extension.");
		
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
	 * @param f The .txt file to be read from.
	 * @return An {@code ArrayList<String>} containing all the words in the input file.
	 */
	public ArrayList<String> readFileToList(File f) {
		
		// Initialize result.
		ArrayList<String> wordsInFile = new ArrayList<String>();
		
		// Create a scanner for the file input, add all words in file to output list. Then close scanner.
		try {
			Scanner fileInputScanner = new Scanner(f);
			while (fileInputScanner.hasNext()) {
				wordsInFile.add(fileInputScanner.next());
			}
			fileInputScanner.close();
		} 
		
		// We handle FileNotFoundException in getTxtFileFromUser, but catch-block included here for compiler.
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Return ArrayList<String>.
		return wordsInFile;
	}
	
	/**
	 * Writes a list of words to a .txt file, separated by " ".
	 * @param fileName The name of the file to create. Assumed to be a .txt file. Note that the file is overwritten if it already exists.
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
			pw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompts users to select from an array of options by typing in the option to select.
	 * @param choices The {@code ArrayList} of options.
	 * @param userInputScanner A {@link Scanner} object to get user input.
	 * @return The selected element from the list. {@null} if list is empty.
	 */
	public static String getUserChoiceFromArrayList (ArrayList<String> choices, Scanner userInputScanner) {
		
		// Initialize.
		boolean validInput = false;
		String choice = null;
		
		// Check that there are choices.
		if (!(choices.size() == 0)) {
			
			// Prompt user for input.
			System.out.println("Please enter your selection.");
			
			// Validate that input is in the list of choices.
			while (!validInput) {
				
				choice = userInputScanner.next();
				
				// If it is, return choice.
				if (choices.contains(choice)) {
					validInput = true;
				}
				
				// Otherwise, prompt for new input.
				else {
					System.out.println("Please select only from the options given.");
				}
			}
			
			return choice;
		}
		
		// If no choices, return null.
		else {
			return choice;
		}
	
	}
	
}
