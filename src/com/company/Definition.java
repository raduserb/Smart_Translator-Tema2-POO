package com.company;

import java.util.ArrayList;

public class Definition {
    private String dict, dictType;
    private int year;
    private ArrayList<String> text = new ArrayList<>();

    public Definition(String dict, String dictType, int year) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public void addText(String text)
    {
        this.text.add(text);
    }

}
