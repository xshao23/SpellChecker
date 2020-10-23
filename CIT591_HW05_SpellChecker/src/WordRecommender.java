import java.util.*;

public class WordRecommender {

	// INSTANCE VARIABLES
	/**
	 * File containing a dictionary of correct words.
	 */
	String filename; 
	
	// CONSTRUCTORS
	/**
	 * Create a WordRecommender object with a given dictionary.
	 * @param fileName The file to be set as dictionary.
	 */
	public WordRecommender(String fileName) {
		this.filename = fileName;
	}

	// METHODS
	/**
	 * Given two words, computes two measures of similarity (leftSimilarity & rightSimilarity),
	 * and returns the average.
	 * @param word1 The first word.
	 * @param word2 The second word.
	 * @return The average similarity score between {@code word1} and {@code word2}.
	 */
	public double getSimilarity (String word1, String word2) {
		// Determine shorter word length.
		int shorterLength = Math.min(word1.length(), word2.length());
		
		// Iterate through shorter length, calculating left and right similarity scores.
		// Similarity score +1 if the words have the same letter in the same "position".
		int leftSimilarity = 0;
		int rightSimilarity = 0;
		
		for (int i = 0; i < shorterLength; i++) {
			// Check left similarity.
			char lChar1 = word1.charAt(i);
			char lChar2 = word2.charAt(i);
			if (lChar1 == lChar2) leftSimilarity++;
			
			// Check right similarity.
			char rChar1 = word1.charAt(word1.length() - 1 - i);
			char rChar2 = word2.charAt(word2.length() - 1 - i);
			if (rChar1 == rChar2) rightSimilarity++;
		}
		
		// Return average of similarity scores.
		return (leftSimilarity + rightSimilarity) / 2.0;
	}
	
	/**
	 * Given an incorrect word, returns a list of legal word suggestions per given algorithm.
	 * @param word A word not present in the dictionary {@code filename}.
	 * @param tolerance Defines the acceptable length of word suggestions 
	 * to be {@code word.length()} +/- {@code tolerance}.
	 * @param commonPercent A {@code double} between 0.0 (0% common) and 1.0 (100% common). Defined between two
	 * words w1 and w2 as (# of chars in w1 AND w2 / # or chars in w1 OR w2).
	 * @param topN The number of legal words to return.
	 * @return A list of {@code topN} word suggestions for the given incorrect word. Returned words must be within
	 * {@code tolerance} of {@code word.length()} and have at least 
	 * {@code commonPercent} % of characters in common with {@code word}.
	 */
	public ArrayList<String> getWordSuggestions (String word, int tolerance, double commonPercent, int topN) {
		ArrayList<String> result = new ArrayList<String>();
		return result;
	}
	
	/**
	 * Formats a list of words for display purposes.
	 * @param list A list of words.
	 * @return A formatted string consisting of the input list elements in a numbered list.
	 */
	public String prettyPrint(ArrayList<String> list) {
		return "";
	}

}
