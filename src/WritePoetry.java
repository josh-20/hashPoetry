import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WritePoetry {
    public WordFreqInfo pWrod = new WordFreqInfo("",0);
    public String WritePoem(String file1,String startWord, int length, boolean insCheck) throws FileNotFoundException {
        String firstWord = "";
        HashTable<WordFreqInfo,String> table = new HashTable<>();
        String preWord = "";
            File file = new File(file1);
            Scanner input = new Scanner(file);
            input.useDelimiter("[^A-Za-z]+");

            firstWord = input.next().toLowerCase();
            while(input.hasNext()){
                if(preWord.equals("")){
                    preWord = firstWord;
                }else{
                    String word1 = input.next();
                    word1 = word1.toLowerCase();
                    if (table.contains(preWord)) {
                        WordFreqInfo word = table.find(preWord);
                        word.occurCt += 1;
                        word.updateFollows(word1);
                    } else {
                        WordFreqInfo word = new WordFreqInfo(preWord, 1);
                        word.updateFollows(word1);
                        table.insert(word,preWord );
                    }
                    preWord = word1;
                }
            }
            int probCount = 0;
            int random = 0;
            String wrap = firstWord;
            for (int i = 0; i < length; i++){
                if(table.find(startWord) != null){
                    Random ran = new Random();
                    if (table.find(startWord).occurCt == 1) {
                        random = 1;
                    } else {
                        int rand = ran.nextInt(table.find(startWord).occurCt - 1) + 1;
                        random = rand;
                    }
                    int index = 0;
                    System.out.print(startWord + " ");
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
                }else{
                    System.out.print(startWord + " ");
                    startWord = wrap;
                }
            }
        System.out.println();

        return table.toString(20);
    }
}
