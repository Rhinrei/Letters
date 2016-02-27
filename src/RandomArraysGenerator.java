import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class RandomArraysGenerator {
    private ArrayList<SymbolizedLetter> symbols;
    private Random randomGenerator;

    public RandomArraysGenerator() {
        this.randomGenerator = new Random();
        this.symbols = new ArrayList<>();

        String [] symbols = ("☆❀○⟡♡⚐⧖♘⛗➳✄▱♧⬠□♰☼△◷❖".split(""));
        String [] letters = ("abcdefghijklmpqrstwx".split(""));
        for (int i = 0; i < symbols.length; i++) {
            this.symbols.add(new SymbolizedLetter(letters[i], symbols[i]));
        }
    }

//    public RandomArraysGenerator(ArrayList<String> symbols) {
//        this.symbols = symbols;
//        this.randomGenerator = new Random();
//    }

    public ArrayList<String []> generateArray(int length){
        ArrayList<SymbolizedLetter> anotherSymbols = new ArrayList<>(symbols);
        ArrayList<SymbolizedLetter> wasInWord = new ArrayList<>();
        ArrayList<String []> generatedArray = new ArrayList<>();
        Iterator iter = symbols.iterator();
        SymbolizedLetter randomSymbolWithout;
        SymbolizedLetter randomSymbolWith;
        while(iter.hasNext()){
            SymbolizedLetter symbol = (SymbolizedLetter) iter.next();
            anotherSymbols.remove(symbol);
            int indexWith;
            int indexWithout;
            String wordWith;
            String wordWithout;
            String wordLettered;

            for (int i = 0; i < length; i++) {
                do {
                    wordWith = "";
                    wordWithout = "";
                    wordLettered = "";
                    for (int j = 0; j < length; j++) {
                        if (i == j) {
                            wordWith += symbol.getSymbol();
                            wordLettered += symbol.getLetter();
                            indexWithout = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWithout = anotherSymbols.get(indexWithout);
                            wordWithout += randomSymbolWithout.getSymbol();
                            wasInWord.add(randomSymbolWithout);
                            anotherSymbols.remove(randomSymbolWithout);
                        } else {
                            indexWith = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWith = anotherSymbols.get(indexWith);
                            wordWith += randomSymbolWith.getSymbol();
                            wordLettered += randomSymbolWith.getLetter();
                            wasInWord.add(randomSymbolWith);
                            anotherSymbols.remove(randomSymbolWith);

                            indexWithout = randomGenerator.nextInt(anotherSymbols.size());
                            randomSymbolWithout = anotherSymbols.get(indexWithout);
                            wordWithout += randomSymbolWithout.getSymbol();
                            wasInWord.add(randomSymbolWithout);
                            anotherSymbols.remove(randomSymbolWithout);
                        }
                    }
                    anotherSymbols.addAll(wasInWord);
                    wasInWord.clear();
                }
                while (StringProcessor.arrayListContainsSimilarWords(generatedArray, new int [] {0, 5}, wordWith, 2) ||
                       StringProcessor.arrayListContainsSimilarWords(generatedArray, new int [] {0, 5}, wordWithout, 2));

                generatedArray.add(new String[]{wordWith, symbol.getSymbol(), wordLettered, symbol.getLetter(),
                        Integer.toString(i+1), wordWithout, symbol.getSymbol(), Integer.toString(-1)});

            }
            anotherSymbols.add(symbol);
        }

        return generatedArray;
    }

}

class SymbolizedLetter {
    private String letter;
    private String symbol;

    public SymbolizedLetter(String letter, String symbol) {
        this.letter = letter;
        this.symbol = symbol;
    }

    public String getLetter() {
        return letter;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymbolizedLetter that = (SymbolizedLetter) o;

        if (!letter.equals(that.letter)) return false;
        return symbol.equals(that.symbol);

    }

    @Override
    public int hashCode() {
        int result = letter.hashCode();
        result = 31 * result + symbol.hashCode();
        return result;
    }
}
