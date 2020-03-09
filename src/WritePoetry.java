import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class WritePoetry {
    public Hashtable<String,WordFreqInfo> WritePoem(String file1,String startWord, int length, boolean insCheck) throws FileNotFoundException {
        Hashtable<String,WordFreqInfo> table = new Hashtable<>();
        String firstWord = "";
        String secondWord = "";
            File file = new File(file1);
            Scanner input = new Scanner(file);
            input.useDelimiter("[^A-Za-z]+");
            while(input.hasNext()){
                String word1 = input.next();
                if(table.containsKey(word1)) {
                    WordFreqInfo word = new WordFreqInfo(word1, table.get(word1).occurCt + 1);
                    table.put(word1, word);
                }
                else {
                    WordFreqInfo word = new WordFreqInfo(word1, 1);
                    table.put(word1, word);
                }
            }
        return table;

    }
}
