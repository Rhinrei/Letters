import java.util.ArrayList;

public class StringProcessor {
    public static int getDifferentSymbolsQuantity(String word1, String word2){
        int length = word1.length() < word2.length() ? word1.length() : word2.length();
        int differences = 0;
        for (int i = 0; i < length; i++) {
            if (word1.charAt(i) != word2.charAt(i)){
                differences++;
            }
        }
        return differences;
    }

    public static Boolean arrayListContainsSimilarWords(ArrayList<String []> arrays, int [] positions, String word, int differentSymbols){
        for(String [] array: arrays){
            for (int position: positions){
                if(getDifferentSymbolsQuantity(word, array[position]) < differentSymbols){
                    return true;
                }
            }
        }
        return false;
    }



}
