import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Book {
    public int numberOfGroups = 0;

    private ArrayList<String []> lines = new ArrayList<>();
    private ArrayList<String> vowels = new ArrayList<>(Arrays.asList("а", "е", "ё", "и", "о", "у", "ы", "э", "ю", "я"));
    private int currentGroup = 0;


    public Book(List<String> lines) {
        if (lines != null) {
            this.lines.addAll(lines.stream().map(line -> line.split("    ")).collect(Collectors.toList()));
        }
    }

    public Book(List<String> lines, int numberOfGroups) {
        if (lines != null) {
            this.lines.addAll(lines.stream().map(line -> line.split("    ")).collect(Collectors.toList()));
        }
        if (numberOfGroups > 0){
            this.numberOfGroups = numberOfGroups;
        }
    }

    public ArrayList<ArrayList<String>> getGroups(){
        ArrayList<ArrayList<String>> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            groups.add(new ArrayList<>());
        }
        String notVowel = "([^аеёиоуыэюя])*";
        for (String v1: vowels) {
            for (String v2 : vowels) {
                Pattern twoVowels = Pattern.compile("^" + notVowel + v1 + notVowel + v2 + notVowel + "$");
                for (String[] line : lines) {
                    Matcher twoMatcher = twoVowels.matcher(line[2]);
                    if (twoMatcher.matches()) {
                        groups.get(currentGroup).add(line[2]);
                        if (currentGroup < numberOfGroups - 1) {
                            currentGroup++;
                        } else {
                            currentGroup = 0;
                        }
                    }
                }
                for (String v3: vowels) {
                    Pattern threeVowels = Pattern.compile("^" + notVowel + v1 + notVowel + v2 + notVowel + v3 + notVowel + "$");
                    for (String[] line : lines) {
                        Matcher threeMatcher = threeVowels.matcher(line[2]);
                        if (threeMatcher.matches()) {
                            groups.get(currentGroup).add(line[2]);
                            if (currentGroup < numberOfGroups - 1) {
                                currentGroup++;
                            } else {
                                currentGroup = 0;
                            }
                        }
                    }
                }
            }
        }

//        for (ArrayList<String> group: groups) {
//            Collections.shuffle(group);
//        }
        return groups;
    }


    public void deleteClones() {
        ArrayList<String[]> copyLines = new ArrayList<>();
        main:
        for(String [] line: lines){
            if(copyLines.isEmpty()){
                copyLines.add(line);
                continue;
            }
            for (String [] copyLine: copyLines){
                if(copyLine[2].equals(line[2])){
                    continue main;
                }
            }
            copyLines.add(line);
        }
        lines.clear();
        lines.addAll(copyLines);
    }

    public ArrayList<String []> getWordsWithOneVowel(){
        ArrayList<String []> wordsWithOneVowel = new ArrayList<>();
        for(String[] line: lines){
            int vowelCount = 0;
            String[] chars = line[2].split("");
            for(String c:chars){
                if(vowels.contains(c)){
                    vowelCount++;
                }
                if (vowelCount > 1){
                    break;
                }
            }
            if (vowelCount == 1){
                wordsWithOneVowel.add(line);
            }
        }
        return wordsWithOneVowel;
    }

    public void deleteWords(ArrayList<String []> toDelete){
        lines.removeAll(toDelete);
    }

    public ArrayList<String []> getWordsWithOneSymbol (String symbol) {
        if (symbol.length() != 1) {
            return null;
        }
        ArrayList<String[]> result = new ArrayList<>();
        for (String[] line : lines) {
            int symbolCount = 0;
            String[] chars = line[2].split("");
            for (String c : chars) {
                if (symbol.equals(c)) {
                    symbolCount++;
                }
                if (symbolCount > 1) {
                    break;
                }
            }
            if (symbolCount == 1) {
                result.add(line);
            }
        }
        return result;
    }

    public ArrayList<String[]> getLines() {
        return lines;
    }

    //NOT FINISHED YET
    public void writeJSONGroupsToFile (String fileName){
        JSONObject file = new JSONObject();
        JSONArray groupsJSON = new JSONArray();
        ArrayList<ArrayList<String>> groups = getGroups();
        for (ArrayList<String> group: groups) {
            JSONArray wordsJSON = new JSONArray();
            for (String word: group) {

            }
        }

    }

    private int getVowelsCount (String word){
        String[] symbols = word.split("");
        int vowelCounter = 0;
        for (String s : symbols) {
            if(vowels.contains(s)){
                vowelCounter++;
            }
        }
        return vowelCounter;
    }

    private Boolean isVowel (String c) {
        return c.length() == 1 && vowels.contains(c);
    }

    private String[] getStresses(String word){
        int i = 0;
        String [] stresses = new String[getVowelsCount(word)];
        String[] symbols = word.split("");
        for (int j = 0; j < symbols.length; j++) {
            if(isVowel(symbols[j])){
                String stressedWord = "";
                for (int k = 0; k < symbols.length; k++) {
                    if (k == j){
                        stressedWord+=symbols[k].toUpperCase();
                    }
                    else{
                        stressedWord+=symbols[k];
                    }
                }
                stresses[i++] = stressedWord;
            }
        }
        return stresses;
    }
}
