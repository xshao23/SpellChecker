import java.util.*;
import java.io.*;


public class WordRecommender {

    // INSTANCE VARIABLES
    /**
     * File containing a dictionary of correct words.
     */
    String filename;
    
    /**
     * A list of all the words in the dictionary defined by {@code filename}.
     */
    ArrayList<String> wordsInDict;

    // CONSTRUCTORS
    /**
     * Create a WordRecommender object from a given dictionary. The dictionary is stored both
     * as a {@code filename} and with its words in an {@code ArrayList<String>}: {@code wordsInDict}.
     * @param fileName The file to be set as dictionary.
     */
    public WordRecommender(String fileName) {
    	this.filename = fileName;
    	
    	IOUtility ioHelper = new IOUtility();
    	this.wordsInDict = ioHelper.readFileToList(new File(filename));
    }

    // METHODS
    /**
     * Given two words, computes two measures of similarity (leftSimilarity &
     * rightSimilarity), and returns the average.
     * 
     * @param word1 The first word.
     * @param word2 The second word.
     * @return The average similarity score between {@code word1} and {@code word2}.
     */
    public double getSimilarity(String word1, String word2) {
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
	    if (lChar1 == lChar2)
		leftSimilarity++;

	    // Check right similarity.
	    char rChar1 = word1.charAt(word1.length() - 1 - i);
	    char rChar2 = word2.charAt(word2.length() - 1 - i);
	    if (rChar1 == rChar2)
		rightSimilarity++;
	}

	// Return average of similarity scores.
	return (leftSimilarity + rightSimilarity) / 2.0;
    }

    /**
     * Given an incorrect word, returns a list of legal word suggestions per given
     * algorithm.
     * 
     * @param word          A word not present in the dictionary {@code filename}.
     * @param tolerance     Defines the acceptable length of word suggestions to be
     *                      {@code word.length()} +/- {@code tolerance}.
     * @param commonPercent A {@code double} between 0.0 (0% common) and 1.0 (100%
     *                      common). Defined between two words w1 and w2 as (# of
     *                      chars in w1 AND w2 / # or chars in w1 OR w2).
     * @param topN          The number of legal words to return.
     * @return A list of {@code topN} word suggestions for the given incorrect word.
     *         Returned words must be within {@code tolerance} of
     *         {@code word.length()} and have at least {@code commonPercent} % of
     *         characters in common with {@code word}. Returned words are ranked by the similarity score given by {@code this.getSimilarity}.
     */
    
    public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {
	ArrayList<String> result = new ArrayList<String>();
	

	return result;
    }
    
    public ArrayList<String> getWordSuggestionsTemp(String word, int tolerance, double commonPercent, int topN) {
	ArrayList<String> result = new ArrayList<String>();
	result.add("correct");
	result.add("notWrong");
	result.add("bad");
	result.add("fake");
	return result;
    }
    
    

    /**
     * Formats a list of words for display purposes.
     * 
     * @param list A list of words.
     * @return A formatted string consisting of the input list elements in a
     *         numbered list.
     */
    public String prettyPrint(ArrayList<String> list) {
	// Initialize result.
	String result = "";

	// Print all strings with label.
	for (int i = 0; i < list.size(); i++) {
	    result = result + (i + 1) + ". " + list.get(i) + "\n";
	}
	return result;
    }
    
    /**
     * take in two words and calculate the similarity based on Jaccard Similarity 
     * @param word1 word of interest 
     * @param word2 word of interest 
     * @return double |intersection|/|union|
     */

    public double getJaccardSimilarity(String word1, String word2) {
	double similarity = 0.0;
	ArrayList<Character> charsInW1 = new ArrayList<>(); // a set of elements in word1
	ArrayList<Character> charsInW2 = new ArrayList<>(); // a set of elements in word2

	for (int i = 0; i < word1.length(); i++) {
	    char e = word1.charAt(i);
	    if (i == 0) {
		charsInW1.add(e);
	    } else {
		if (!charsInW1.contains(e)) {
		    charsInW1.add(e);
		}
	    }
	}

	for (int i = 0; i < word2.length(); i++) {
	    char e = word2.charAt(i);
	    if (i == 0) {
		charsInW2.add(e);
	    } else {
		if (!charsInW2.contains(e)) {
		    charsInW2.add(e);
		}
	    }
	}
	int intersection = 0;
	for (Character letter:charsInW1) {
	    if (charsInW2.contains(letter)){
		intersection++; 
	    } 
	}
	similarity = (intersection *1.0)/((charsInW1.size()+charsInW2.size()-intersection)*1.0);
	return similarity;
    }
    
}
