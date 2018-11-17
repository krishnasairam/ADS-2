import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
public class Solution {
	/**
	 * main.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash =
			 loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.

	/**
	 * toREadFile.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] toReadFile(final String file) {
		In in = new In(file);
		return in.readAllStrings();
	}
/**
 * Loads a dictionary.
 *
 * @param      file  The file
 *
 * @return     { description_of_the_return_value }
 */
	public static BinarySearchST<String, Integer>
	 loadDictionary(final String file) {
		BinarySearchST<String, Integer>  st =
		 new BinarySearchST<String, Integer>();
		String[] words = toReadFile(file);
		String asString = Arrays.toString(words);
        words = asString.toLowerCase().split(", ");
		for (String str : words) {
            if (st.contains(str)) {
                st.put(str, 1 + st.get(str));
            } else {
                st.put(str, 1);
            }
        }
        return st;
	}
}
/**
 * Class for t 9.
 */
class T9 {
	/**
	 * dict.
	 */
	private TST dict;
	/**
	 * Constructs the object.
	 *
	 * @param      st    { parameter_description }
	 */
	T9(final BinarySearchST<String, Integer> st) {
		// your code goes here
		dict = new TST();
        for (String word : st.keys()) {
            dict.put(word, st.get(word));
        }
	}

	// get all the prefixes that match with given prefix.

	/**
	 * Gets all words.
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(final String prefix) {
		return dict.keysWithPrefix(prefix);
	}
	/**
	 * potentialWords.
	 *
	 * @param      t9Signature  The t 9 signature
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> potentialWords(final String t9Signature) {
		// your code goes here
		return dict.keysThatMatch(t9Signature);
	}

	// return all possibilities(words), find top k with highest frequency.

	/**
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      { parameter_description }
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(final
	 Iterable<String> words, final int k) {
		// your code goes here
		int temp = k;
		MaxPQ<Integer> pq = new MaxPQ<Integer>();
		MinPQ<String> q = new MinPQ<String>();
		BinarySearchST<Integer, String>  ne =
		 new BinarySearchST<Integer, String>();
		for (String str : words) {
			pq.insert((Integer) (dict.get(str)));
			ne.put((Integer) (dict.get(str)), str);
		}
		while (temp > 0) {
			q.insert(ne.get(pq.delMax()));
			temp--;
		}
		return q;
	}

	/**
	 * t9.
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> t9(final String t9Signature, final int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}

