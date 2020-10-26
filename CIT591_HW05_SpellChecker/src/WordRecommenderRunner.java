
public class WordRecommenderRunner {

	// INSTANCE VARIABLES

	// CONSTRUCTORS

	// METHODS
	
	public static void main(String[] args) {
		// 1. Welcome user to program and ask for the file to spell-check.
		
		// 2. Check that the file name provided is valid; loop until it is.
		
		// 3. Read in file to ArrayList<String> = inputWords of words in the file.
		
		// 4. Instantiate WordRecommender with a dictionary and EITHER: a) inputWords as instance variable of WordRecommender
		//                                                          OR: b) inputWords remains in Runner
		
		// 5. Ask user for "N" in "topN" (and explain). Loop until input is valid.
		
		// 6. Create new ArrayList<String> = outputWords (empty).
		//    Then, EITHER: a) Iterate through inputWords in the WordRecommender object
		//               OR: b) Iterate through inputWords in Runner
		//
		//    In either case, for each word:
		//        1) Check if it is in the dictionary of WordRecommender.
		//        2) If it is, place in outputWords, then go to next word.
		//        3) If it is not, then:
		//            i) Call WordRecommender.getWordSuggestions
		//           ii) Prompt for user input; loop until input is valid.
		//          iii) Update input words with result of user input. (Maybe make ALL CAPS if updated?)
		
		// 7. Once loop is complete, have the results in outputWords. Get the output file name (using the input file name),
		//    and write outputWords to it.
		
		// 8. End program, tell user where to find new file.
		
	}

}
