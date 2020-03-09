import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class WritePoetry {
    public Hashtable<String,WordFreqInfo> WritePoem(String file1,String startWord, int length, boolean insCheck) {
        Hashtable<String,WordFreqInfo> table = new Hashtable<>();
        String firstWord = "";
        String secondWord = "";
        try {
            File file = new File(file1);
            Scanner input = new Scanner(file);
            input.useDelimiter("[^A-Za-z]+");
            while(input.hasNextLine()){
                String word1 = input.next();
                firstWord = word1.toLowerCase();
                if(table.containsKey(word1)) {
                    WordFreqInfo word = new WordFreqInfo(word1, table.get(word1).occurCt + 1);
                    table.put(word1, word);
                }
                else {
                    WordFreqInfo word = new WordFreqInfo(word1, 1);
                    table.put(word1, word);
                }
            }
        } catch (Exception ex) {
            System.out.println("File not found");
        }
        System.out.println(table.get("i").occurCt);
        return table;

    }
}
