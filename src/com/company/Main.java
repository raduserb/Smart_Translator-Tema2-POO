package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, List<Word>> map = new HashMap<>();//in map tinem informatiile din dictionare
        Gson g = new Gson();
        Administration var;
        final Type gson_type = new TypeToken<List<Word>>() {
        }.getType();

        File folder = new File("src\\com\\dictionaries");
        File[] listOfFiles = folder.listFiles();


        assert listOfFiles != null;
        for(File file: listOfFiles){
            String fileName = "src\\com\\dictionaries\\"+file.getName();
            String[] language = file.getName().split("_");
            map.put(language[0],g.fromJson(new FileReader(fileName), gson_type));
        }

        var = new Administration(map);

        System.out.println("Task 2");
        Word cuvant = new Word("branza","cheese","noun");
        System.out.println(var.addWord(cuvant,"ro"));

        for(Word cuvant_iterator : map.get("ro"))
            System.out.println(cuvant_iterator.getWord()+ " " + cuvant_iterator.getWord_en() + " " + cuvant_iterator.getType());
        System.out.println(var.addWord(cuvant,"ro"));
        for(Word cuvant_iterator : map.get("ro"))
            System.out.println(cuvant_iterator.getWord()+ " " + cuvant_iterator.getWord_en() + " " + cuvant_iterator.getType());

        System.out.println("Task 3");
        System.out.println(var.removeWord("branza","ro"));
        for(Word cuvant_iterator : map.get("ro"))
            System.out.println(cuvant_iterator.getWord()+ " " + cuvant_iterator.getWord_en() + " " + cuvant_iterator.getType());
        System.out.println(var.removeWord("branza","ro"));
        for(Word cuvant_iterator : map.get("ro"))
            System.out.println(cuvant_iterator.getWord()+ " " + cuvant_iterator.getWord_en() + " " + cuvant_iterator.getType());

        System.out.println();
        System.out.println("Task 4");
        System.out.println();

        Definition definitie = new Definition("dictionarul zeilor","sinonime zeice",2002);
        definitie.addText("Zeus");
        definitie.addText("Poseidon");
        definitie.addText("Hefaistos");
        System.out.println(var.addDefinitionForWord("pisică","ro",definitie));
        Word cuvant_task4 = var.searchWord("pisică","ro");
        for(Definition definition_iterator : cuvant_task4.getDefinitions()){
            System.out.println(definition_iterator.getDict());
            for(String text : definition_iterator.getText())
                System.out.println(text);
            System.out.println();
        }
        System.out.println(var.addDefinitionForWord("pisică","ro",definitie));
        for(Definition definition_iterator : cuvant_task4.getDefinitions()){
            System.out.println(definition_iterator.getDict());
            for(String text : definition_iterator.getText())
                System.out.println(text);
            System.out.println();
        }

        System.out.println();
        System.out.println("Task 5");
        System.out.println();

        System.out.println(var.removeDefinition("pisică","ro","dictionarul zeilor"));
        for(Definition definition_iterator : cuvant_task4.getDefinitions()){
            System.out.println(definition_iterator.getDict());
            for(String text : definition_iterator.getText())
                System.out.println(text);
            System.out.println();
        }
        System.out.println(var.removeDefinition("pisică","ro","dictionarul zeilor"));
        for(Definition definition_iterator : cuvant_task4.getDefinitions()){
            System.out.println(definition_iterator.getDict());
            for(String text : definition_iterator.getText())
                System.out.println(text);
            System.out.println();
        }

        System.out.println();
        System.out.println("Task 6");
        System.out.println();

        System.out.println(var.translateWord("chats","fr","ro"));
        System.out.println(var.translateWord("pisicute","ro","fr"));
        System.out.println(var.translateWord("leu","ro","fr"));

        System.out.println();
        System.out.println("Task 7");
        System.out.println();
        System.out.println(var.translateSentence("chat est belle","fr","ro"));
        System.out.println(var.translateSentence("chats manger jeux","fr","en"));

        System.out.println();
        System.out.println("Task 8");
        System.out.println();

        System.out.println(var.translateSentences("chat est belle","fr","ro"));
        System.out.println(var.translateSentences("pisicuta mananca paine","ro","fr"));

        System.out.println();
        System.out.println("Task 9");
        System.out.println();

        for(Definition definition: (var.getDefinitionsForWord("merge", "ro"))) {
            System.out.println(definition.getYear() + " " + definition.getDict());
        }
        System.out.println();
        for(Definition definition: (var.getDefinitionsForWord("câine", "ro"))) {
            System.out.println(definition.getYear() + " " + definition.getDict());
        }
    }
}
