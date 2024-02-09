
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 0) {
			return "";
		}
		else {
			return str.substring(1, str.length());
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int len1 = word1.length();
		int len2 = word2.length();
		if (len1 == 0) {
			return len2;
		// redundant else
		} else {
		if (len2 == 0) {
			return len1;
		// redundant else
		} else {
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		// redundant else
		} else {
			int min1 = levenshtein(tail(word1), word2);
			int min2 = levenshtein(word1, tail(word2));
			int min3 = levenshtein(tail(word1), tail(word2));
			return 1 + Math.min(min1, Math.min(min2, min3));
		}}}

	/* redundant else means that when you do return, the function stops,
 	so the only way to continue the function run is if the condition was false wich is what else stands for.
  	so there is no need for the else.	
   	*/
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) {
			String str = in.readLine();
			dictionary[i] = str;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String similar = dictionary[0];
		int min_edit_distance = levenshtein(word, dictionary[0]);
		int edit_distance = 0;
		for (int i = 1; i < dictionary.length; i++) {
			edit_distance = levenshtein(word, dictionary[i]);
			if (edit_distance < min_edit_distance) {
				min_edit_distance = edit_distance;
				similar = dictionary[i];
			}
		}
		if (min_edit_distance > threshold) {
			return word;
		} else {
			return similar;

		}
	}
}
