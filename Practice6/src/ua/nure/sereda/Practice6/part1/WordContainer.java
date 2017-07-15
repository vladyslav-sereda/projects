package ua.nure.sereda.Practice6.part1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WordContainer {
    private List<Word> list;

    public WordContainer() {
        list = new ArrayList<>();
    }

    public void addWord(String word) {
        if (list.isEmpty()) {
            list.add(new Word(word));
        } else {
            for (Word aList : list) {
                if (aList.getWord().equals(word)) {
                    aList.addFrequency();
                    return;
                }
            }
            list.add(new Word(word));
        }
    }

    private void sortList() {
        Collections.sort(list);
    }

    public String toString() {
        sortList();
        StringBuilder sb = new StringBuilder();
        for (Word word : list) {
            sb.append(word.getWord());
            sb.append(": ");
            sb.append(word.getFrequency());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}





