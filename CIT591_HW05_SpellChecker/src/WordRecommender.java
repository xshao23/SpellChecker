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
		return 0.0;
	}
	
	/**
	 * Given an incorrect word, returns a list of legal word suggestions per given algorithm.
	 * @param word A word not present in the dictionary {@code filename}.
	 * @param tolerance Defines the acceptable length of word suggestions 
	 * to be {@code word.length()} +/- {@code tolerance}.
	 * @param commonPercent A {@code double} between 0.0 (0% common) and 1.0 (100% common). Defined between two
	 * words w1 and w2 as (# of chars in w1 AND w2 / # or chars in w1 OR w2).
	 * @param topN The number of legal words to return.
	 * @return A list of {@code topN} word suggestions for the given incorrect word.
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
