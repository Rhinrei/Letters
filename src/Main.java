import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<String> lines = null;
//        try {
//            lines = Files.readAllLines(Paths.get("E:\\Programs\\JavaProjects\\GoogleForms\\src\\all_data.txt"),
//                    StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Book stat = new Book(lines, 9);
//        stat.deleteClones();
//        ArrayList<String []> withVowel = stat.getWordsWithOneVowel();
//        stat.deleteWords(withVowel);
//        Printer.toText("yo", stat.getWordsWithOneSymbol("ё"));
//        stat.deleteWords(stat.getWordsWithOneSymbol("ё"));
//        ArrayList<ArrayList<String>> groups = stat.getGroups();
//        for (int i = 0; i < groups.size(); i++) {
//            try {
//                Files.write(Paths.get("E:\\Programs\\JavaProjects\\GoogleForms\\src\\group_" + i + ".txt"), groups.get(i), StandardCharsets.UTF_8);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//        JSONObject obj = new JSONObject();
//        obj.put("name", "mkyong.com");
//        obj.put("age", 100);
//
//        JSONArray list = new JSONArray();
//        list.add("msg 1");
//        list.add("msg 2");
//        list.add("msg 3");
//
//        obj.put("messages", list);

        //Printer.toText("nodoubles_data", stat.getLines());
        //Printer.toText("vowel_data", withVowel);

        RandomArraysGenerator generator = new RandomArraysGenerator();
        //ArrayList<String []> arr = generator.generateArray(5);
        Printer.toExcel("testnew", generator.generateArray(5));


        int a = 0;
    }
}
