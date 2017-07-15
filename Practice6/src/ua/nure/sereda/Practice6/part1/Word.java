package ua.nure.sereda.Practice6.part1;

public class Word implements Comparable<Word> {

    private String word;

    private int frequency;

    public Word(String word) {
        this.word = word;
        frequency = 1;
    }

    public void addFrequency() {
        frequency++;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency(){
        return frequency;
    }

    @Override
    public int compareTo(Word w) {
        if (frequency == w.frequency) {
            return word.compareTo(w.word);
        } else {
            return w.frequency - frequency;
        }
    }
}

