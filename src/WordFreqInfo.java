import java.util.ArrayList;
import java.util.*;

public class WordFreqInfo {
    public String word;
    public int occurCt;
    ArrayList<Freq> followList;

    public WordFreqInfo(String word, int count) {
        this.word = word;
        this.occurCt = count;
        this.followList = new ArrayList<>();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( " | Word :  " + word+":");
        sb.append(" (" + occurCt + ") : -- Follow list:  ");
        for (Freq f : followList)
            sb.append(f.toString());

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void updateFollows(String follow) {
        boolean updated = false;
        //System.out.println("updateFollows " + word + " " + follow);
        for (Freq f : followList) {
            if (follow.compareTo(f.follow) == 0) {
                f.followCt++;
                updated = true;
            }
        }
        if (!updated)
            followList.add(new Freq(follow, 1));
    }

    public static class Freq {
        String follow;
        int followCt;

        public Freq(String follow, int ct) {
            this.follow = follow;
            this.followCt = ct;
        }

        public String toString() {
            return follow + " [" + followCt + "] ";
        }

        public boolean equals(Freq f2) {
            return this.follow.equals(f2.follow);
        }
    }
}
