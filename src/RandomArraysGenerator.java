import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class RandomArraysGenerator {
    private ArrayList<String> symbols = new ArrayList<>(Arrays.asList("☆❀○⟡♡⚐⧖♘⛗➳✄▱♧⬠□♰☼△◷❖".split("")));
    private Random randomGenerator;

    public RandomArraysGenerator() {
        this.randomGenerator = new Random();
    }

    public RandomArraysGenerator(ArrayList<String> symbols) {
        this.symbols = symbols;
        this.randomGenerator = new Random();
    }

    public ArrayList<String []> generateArray(int length){
        ArrayList<String> anotherSymbols = new ArrayList<>(symbols);
        ArrayList<String> wasInWord = new ArrayList<>();
        ArrayList<String []> generatedArray = new ArrayList<>();
        Iterator iter = symbols.iterator();
        String randomSymbolWithout;
        String randomSymbolWith;
        while(iter.hasNext()){
            String symbol = (String) iter.next();
            anotherSymbols.remove(symbol);
            int indexWith;
            int indexWithout;
            String wordWith;
            String wordWithout;

            for (int i = 0; i < length; i++) {
                do {
                    wordWith = "";
                    wordWithout = "";
                    for (int j = 0; j < length; j++) {
                        if (i == j) {
                            wordWith += symbol;
                            indexWithout = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWithout = anotherSymbols.get(indexWithout);
                            wordWithout += randomSymbolWithout;
                            wasInWord.add(randomSymbolWithout);
                            anotherSymbols.remove(randomSymbolWithout);
                        } else {
                            indexWith = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWith = anotherSymbols.get(indexWith);
                            wordWith += randomSymbolWith;
                            wasInWord.add(randomSymbolWith);
                            anotherSymbols.remove(randomSymbolWith);

                            indexWithout = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWithout = anotherSymbols.get(indexWithout);
                            wordWithout += randomSymbolWithout;
                            wasInWord.add(randomSymbolWithout);
                            anotherSymbols.remove(randomSymbolWithout);
                        }
                    }
                    anotherSymbols.addAll(wasInWord);
                    wasInWord.clear();
                }
                while (StringProcessor.arrayListContainsSimilarWords(generatedArray, new int [] {0, 3}, wordWith, 2) ||
                       StringProcessor.arrayListContainsSimilarWords(generatedArray, new int [] {0, 3}, wordWithout, 2));

                generatedArray.add(new String[]{wordWith, symbol, Integer.toString(i+1), wordWithout, symbol, Integer.toString(-1)});

            }
            anotherSymbols.add(symbol);
        }

        return generatedArray;
    }

}
