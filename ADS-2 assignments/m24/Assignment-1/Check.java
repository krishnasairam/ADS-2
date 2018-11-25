import java.util.Arrays;
import java.io.*;
public class Check {
    private Stopwatch time;
    public Check () {
    }
    public Check(TrieST dictionary, String[] text) {
        time = new Stopwatch();
        String wrongwords = "";
        for (String word : text) {
            if (!dictionary.contains(word)  && word.length() > 1) {
                wrongwords += word + "\n";
            }
        }
        try {
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            out.println(wrongwords);
            out.close();
        } catch (Exception e) {

        }
    }
    public Check(TST dictionary, String[] text) {
        time = new Stopwatch();
        String wrongwords = "";
        for (String word : text) {
            if (word.length() > 1) {
                if (!dictionary.contains(word)) {
                    wrongwords += word + "\n";
                }
            }
        }
        try {
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            out.println(wrongwords);
            out.close();
        } catch (Exception e) {

        }
    }
    public double time() {
        return time.elapsedTime();
    }
}
