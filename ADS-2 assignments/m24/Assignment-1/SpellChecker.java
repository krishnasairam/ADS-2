import java.util.Arrays;
/**
 * Class for spell checker.
 */
public class SpellChecker {
    public static void main(String[] args) {
        In in = new In("/Files/Dictionary.txt");
        String[] dict = in.readAllStrings();
        String asString = Arrays.toString(dict);
        dict = asString.toLowerCase().split(", ");
        In i = new In("/Files/war.txt");
        String[] text = i.readAllStrings();
        String aString = Arrays.toString(text);
        aString = aString.replaceAll("[^a-zA-Z ]", "");
        aString = aString.replaceAll(" ", ", ");
        text = aString.toLowerCase().split(", ");
        Check check = new Check();
        if (args[0].equals("TrieST")) {
            TrieST<String> dictionary = new TrieST<String>();
            for (String word : dict) {
                dictionary.put(word, word);
            }
            check = new Check(dictionary, text);

        } else if (args[0].equals("TST")) {
            TST<String> dictionary = new TST<String>();
            for (String word : dict) {
                dictionary.put(word, word);
            }
            check = new Check(dictionary, text);
        } else {
            System.out.println("wrong argument");
        }
        System.out.println(check.time());
    }
}
