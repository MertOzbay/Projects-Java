
package Autocomplete;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public abstract class Autocomplete<T> extends Trie<T> {
    private final int max;


    public Autocomplete(String dict_path, int max) {
        this.max = max;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dict_path)));
            String line;

            while ((line = reader.readLine()) != null) {
                put(line.trim(), null);
            }

            System.out.println("Created dictionary!");
        } catch (IOException ignored) {}
    }

    public int getMax() {
        return max;
    }


    public abstract List<String> getCandidates(String prefix);


    public abstract void pickCandidate(String prefix, String candidate);
}