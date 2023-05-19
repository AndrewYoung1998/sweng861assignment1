package edu.psgv.sweng861;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AsciiReader {

	//main method that runs application
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please provide the filename as an argument.");
			return;
		}

		
		// Read the input file
		String filename = args[0];
		String fileContent = readTextFile(filename);

		// Ask the user for the maximum number of characters to read
        int maxCharacters = getMaxCharactersFromUser(fileContent.length());

        // Truncate the file content to the specified maximum characters
        fileContent = fileContent.substring(0, maxCharacters);
		// Print the statistics
		printStatistics(fileContent);

		// Print the content of the file in reverse order, converting all letters to lower case
		printReversedContent(fileContent);
	}
	//private method that reads the file and builds a string
	private static String readTextFile(String filename) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			System.out.println("Error reading the file: " + e.getMessage());
		}
		return content.toString();
	}
	
	//prompts user to enter to enter number of characters to be read from the fileLength passed in to method
	private static int getMaxCharactersFromUser(int fileLength) {
	        int maxCharacters = 0;
	        boolean invalidInput = true;

	        while (invalidInput) {
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
	                System.out.print("Enter the maximum number of characters to read (up to " + fileLength + "): ");
	                String input = reader.readLine();
	                maxCharacters = Integer.parseInt(input);

	                if (maxCharacters > fileLength) {
	                    System.out.println("Invalid input. The number exceeds the file length.");
	                } else {
	                    invalidInput = false;
	                }
	            } catch (IOException | NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a valid integer.");
	            }
	        }

	        return maxCharacters;
	    }
	   
	//private method that prints out all the stats returned from other methods 
	private static void printStatistics(String fileContent) {
		int totalWords = countWords(fileContent);
		int totalNumbers = countNumbers(fileContent);
		int totalCharacters = fileContent.length();
		int totalSpaces = countSpaces(fileContent);
		int totalPunctuation = countPunctuation(fileContent);
		int totalUpperCase = countUpperCase(fileContent);
		int totalLowerCase = countLowerCase(fileContent);

		System.out.println("Total Number of words in the file: " + totalWords);
		System.out.println("Total Number of numbers in the file: " + totalNumbers);
		System.out.println("Total Number of characters including spaces: " + totalCharacters);
		System.out.println("Total Number of spaces: " + totalSpaces);
		System.out.println("Total Number of punctuation characters: " + totalPunctuation);
		System.out.println("Total Number of characters in upper case: " + totalUpperCase);
		System.out.println("Total Number of characters in lower case: " + totalLowerCase);
	}
	
	//gets the total word count from the string passed in based on user entered number 
	private static int countWords(String text) {
		String[] words = text.split("\\W+");
		return words.length;
	}
	//gets the total number count from the string passed in based on user entered number
	private static int countNumbers(String text) {
		String[] numbers = text.split("\\D+");
		return numbers.length;
	}
	
	//gets the total number of spaces
	private static int countSpaces(String text) {
		int spaceCount = 0;
		for (char c : text.toCharArray()) {
			if (Character.isWhitespace(c)) {
				spaceCount++;
			}
		}
		return spaceCount;
	}

	//gets the total number of puncuations 
	private static int countPunctuation(String text) {
		int punctuationCount = 0;
		for (char c : text.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				continue;
			}
			if (Character.isWhitespace(c)) {
				continue;
			}
			punctuationCount++;
		}
		return punctuationCount;
	}

	//gets the total number of upper case letters
	private static int countUpperCase(String text) {
		int upperCaseCount = 0;
		for (char c : text.toCharArray()) {
			if (Character.isUpperCase(c)) {
				upperCaseCount++;
			}
		}
		return upperCaseCount;
	}

	//gets the total number of lowercase letters
	private static int countLowerCase(String text) {
		int lowerCaseCount = 0;
		for (char c : text.toCharArray()) {
			if (Character.isLowerCase(c)) {
				lowerCaseCount++;
			}
		}
		return lowerCaseCount;
	}
	//prints string of characters in reverse
	private static void printReversedContent(String text) {
		StringBuilder reversedContent = new StringBuilder();
		for (int i = text.length() - 1; i >= 0; i--) {
			char c = text.charAt(i);
			if (Character.isLetter(c)) {
				reversedContent.append(Character.toLowerCase(c));
			}
		}
		System.out.println("Reversed content of the file: " + reversedContent.toString());
	}
}