package net.monitor.Filters;

import java.util.HashSet;
import java.util.Set;

public class ProfanityFilter {
    private Set<String> profaneWords = new HashSet<>();

    public ProfanityFilter() {
        profaneWords.add("hell");
        profaneWords.add("damn");
        profaneWords.add("crap");
        profaneWords.add("sexy");
    }

    public boolean contains(String text) {
        for (String word : profaneWords) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String filter(String text) {
        for (String word : profaneWords) {
            text = text.replaceAll(word, "****");
        }
        return text;
    }

    public void addProfaneWord(String word) {
        profaneWords.add(word);
    }

    public void removeProfaneWord(String word) {
        profaneWords.remove(word);
    }

    public Set<String> getProfaneWords() {
        return profaneWords;
    }

    public void setProfaneWords(Set<String> profaneWords) {
        this.profaneWords = profaneWords;
    }

    public void clearProfaneWords() {
        profaneWords.clear();
    }

    public boolean isEmpty() {
        return profaneWords.isEmpty();
    }

    public int size() {
        return profaneWords.size();
    }

    public boolean containsProfaneWord(String word) {
        return profaneWords.contains(word);
    }

    public void printProfaneWords() {
        for (String word : profaneWords) {
            System.out.println(word);
        }
    }

    public void clear() {
        profaneWords.clear();
    }

    public void reset() {
        profaneWords.clear();
        profaneWords.add("hell");
        profaneWords.add("damn");
        profaneWords.add("crap");
        profaneWords.add("sexy");
    }

    public void monitor(String text) {
        if (contains(text)) {
            System.out.println("Profanity detected: " + text);
            // Send alert and block the search with custom message

        }
    }
}
