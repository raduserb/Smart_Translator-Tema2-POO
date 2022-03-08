package com.company;

import java.util.ArrayList;

public class Word {
    private String word, word_en, type;
    private ArrayList<String> singular;
    private ArrayList<String> plural;
    private ArrayList<Definition> definitions;

    public Word(String word, String word_en, String type) {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = null;
        this.plural = null;
        this.definitions = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_en() {
        return word_en;
    }

    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public void setSingular(ArrayList<String> singular) {
        this.singular = singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public void setPlural(ArrayList<String> plural) {
        this.plural = plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    public void addSingular(String text)
    {
        this.singular.add(text);
    }

    public void addPlural(String text)
    {
        this.plural.add(text);
    }

    public void addDefinitions(Definition text)
    {
        this.definitions.add(text);
    }

}
