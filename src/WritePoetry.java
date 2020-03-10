import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WritePoetry {
   StringBuilder sb = new StringBuilder();
    public String WritePoem(String file1,String startWord, int length, boolean insCheck) throws FileNotFoundException {
        sb.setLength(0);
        String firstWord = "";
        HashTable<WordFreqInfo, String> table = new HashTable<>();
        String preWord = "";
        File file = new File(file1);
        Scanner input = new Scanner(file);
        input.useDelimiter("[^A-Za-z]+");
        firstWord = input.next().toLowerCase();
        while (input.hasNext()) {
            if (preWord.equals("")) {
                preWord = firstWord;
            } else {
                String word1 = input.next();
                word1 = word1.toLowerCase();
                if (table.contains(preWord)) {
                    WordFreqInfo word = table.find(preWord);
                    word.occurCt += 1;
                    word.updateFollows(word1);
                } else {
                    WordFreqInfo word = new WordFreqInfo(preWord, 1);
                    word.updateFollows(word1);
                    table.insert(word, preWord);
                }
                preWord = word1;
            }
        }
        int probCount = 0;
        int random = 0;
        String wrap = firstWord;
        for (int i = 0; i < length; i++) {
            if (table.find(startWord) != null) {
                Random ran = new Random();
                if (table.find(startWord).occurCt == 1) {
                    random = 1;
                } else {
                    int rand = ran.nextInt(table.find(startWord).occurCt - 1) + 1;
                    random = rand;
                }
                int index = 0;
                sb.append(startWord);
                sb.append(" ");
                probCount = 0;
                while (random > probCount) {
                    WordFreqInfo word = table.find(startWord);
                    WordFreqInfo.Freq follow1 = word.followList.get(index);
                    probCount += follow1.followCt;
                    if (probCount >= random) {
                        startWord = follow1.follow;
                    }
                    index++;
                }
            } else {
                sb.append(startWord);
                sb.append(" ");
                startWord = wrap;
            }
        }
        System.out.println();
        System.out.println("---------------- Poem of " + file + " -------------------");
        if (insCheck) {
            System.out.println(sb);
            sb.setLength(0);
            return table.toString(table.size());
        }
        return sb.toString();
    }
}
