package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Administration {

    Map<String, List<Word>> map;

    public Administration(Map<String, List<Word>> map) {
        this.map = map;
    }


    //metoda ce cauta cuvantul word in lista de cuvinte si il intoarce
    Word searchWord(String word, String language){
        List<Word> words;
        words = map.get(language);
        for(Word word_iterator : words){
            if(word_iterator.getWord().equals(word))
                return word_iterator;
        }
        return null;
    }

    //metoda ce cauta cuvantul word in lista de forme de singular a fiecarui cuvant
    Word searchWordSingulars(String word, String language){
        List<Word> words;
        words = map.get(language);
        for(Word word_iterator : words){
            for(String singular_iterator : word_iterator.getSingular()){
                if(singular_iterator.equals(word))
                    return word_iterator;
            }
        }
        return null;
    }

    //metoda ce cauta cuvantul word in lista de forme de plural a fiecarui cuvant
    Word searchWordPlurals(String word, String language){
        List<Word> words;
        words = map.get(language);
        for(Word word_iterator : words){
            for(String plural_iterator : word_iterator.getPlural()){
                if(plural_iterator.equals(word))
                    return word_iterator;
            }
        }
        return null;
    }

    //metoda ce primeste un cuvant in engleza si intoarce cuvantul din limba language care are aceeasi traducere in engleza
    Word searchWordEn(String word, String language){
        List<Word> words;
        words = map.get(language);
        for(Word word_iterator : words){
            if(word_iterator.getWord_en().equals(word))
                return word_iterator;
        }
        return null;
    }

    //metoda ce adauga un cuvant intr un dictionar
    boolean addWord(Word word, String language){
        List<Word> words;
        words = map.get(language);
        if(searchWord(word.getWord(),language)==null){//daca cuvantul exista deja
            words.add(word);
            return true;
        }
        return false;
    }

    //metoda ce sterge un cuvant dintr-un dictionar
    boolean removeWord(String word, String language){
        List<Word> words;
        words = map.get(language);
        Word wordToDelete = searchWord(word,language);
        if (wordToDelete!=null) {
            words.remove(wordToDelete);
            return true;
        }
        return false;
    }


    //metoda ce adauga o definitie unui cuvant
    boolean addDefinitionForWord(String word, String language, Definition definition){
        Word wordToAddDefTo = searchWord(word,language);
        if (wordToAddDefTo!=null)
        {
            for(Definition def_iterator : wordToAddDefTo.getDefinitions() ){
                if(def_iterator.getDict().equals(definition.getDict()))
                    return false;
            }
            wordToAddDefTo.addDefinitions(definition);
            return true;
        }
        return false;
    }

    //metoda ce sterge definitia unui cuvant
    boolean removeDefinition(String word, String language, String dictionary){
        Word wordToRemoveDefFrom = searchWord(word,language);

        if (wordToRemoveDefFrom != null)
            for(Definition def_iterator : wordToRemoveDefFrom.getDefinitions() )
                if(def_iterator.getDict().equals(dictionary)) {
                    wordToRemoveDefFrom.getDefinitions().remove(def_iterator);
                    return true;
                }

        return false;
    }

    //metoda care primeste un cuvnant si il traduce in tolanguage
    String translatefromEn(Word word, String toLanguage){
        if(word != null){
            if(toLanguage.equals("en"))
                return word.getWord_en();
            else
            {
                Word wordTranslated = searchWordEn(word.getWord_en(),toLanguage);
                if(wordTranslated!=null)
                    return wordTranslated.getWord();
            }
        }
        return "Not found";
    }

    //metoda care traduce un cuvant din from language in tolanguage
    String translateWord(String word, String fromLanguage, String toLanguage){
        String result;

        //cautam in toate formele cuvantului
        Word wordToTranslate = searchWord(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return result;

        wordToTranslate = searchWordPlurals(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return result;

        wordToTranslate = searchWordSingulars(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return result;

        return "Not found";
    }

    //metoda care traduce o propozitie din from language in tolanguage
    String translateSentence(String sentence, String fromLanguage, String toLanguage){
        String result = new String();
        String[] lineContent = sentence.split(" ");
        for(int i = 0 ; i < lineContent.length ; i++){
            if(!translateWord(lineContent[i],fromLanguage,toLanguage).equals("Not found")) //punem cuvantul tradus
            {
                result += translateWord(lineContent[i],fromLanguage,toLanguage);
                result += " ";
            }
            else //punem cuvantul care era inainte, netradus
            {
                result += lineContent[i];
                result += " ";
            }
        }
        return result;
    }

    //metoda care primeste un cuvant si ii gaseste al nr-lea sinonim din dict de sinonime
    String findSynonym(String word, String language, int nr){
        Word result = searchWord(word,language);
        for(Definition def_iterator : result.getDefinitions())
        {
            int i = 0;
            if(def_iterator.getDictType().equals("synonyms"))
                for(String synonym_iterator : def_iterator.getText())
                {
                    if(i==nr)
                        return synonym_iterator;
                    i++;
                }
        }
        return word;

    }

    //metoda care gaseste al nr-lea sinonim pt orice forma a unui cuvant
    String translateSynonim(String word, String fromLanguage, String toLanguage, int nr){
        String result;

        Word wordToTranslate = searchWord(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return findSynonym(result,toLanguage,nr);

        wordToTranslate = searchWordPlurals(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return findSynonym(result,toLanguage,nr);

        wordToTranslate = searchWordSingulars(word,fromLanguage);
        result = translatefromEn(wordToTranslate,toLanguage);
        if(!result.equals("Not found"))
            return findSynonym(result,toLanguage,nr);
        return "Not found";
    }

    //metoda care traduce o propozitie folosind ale nr-lea sinonime
    String translateSentenceSynonim(String sentence, String fromLanguage, String toLanguage, int nr){
        String result = new String();
        String[] lineContent = sentence.split(" ");
        for(int i = 0 ; i < lineContent.length ; i++){
            if(!translateSynonim(lineContent[i],fromLanguage,toLanguage,nr).equals("Not found"))
            {
                result += translateSynonim(lineContent[i],fromLanguage,toLanguage,nr);
                result += " ";
            }
            else
            {
                result += lineContent[i];
                result += " ";
            }
        }
        return result;
    }

    //metoda ce traduce o propozitie in 3 feluri
    ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage){
        ArrayList<String> translations = new ArrayList<>();
        translations.add(translateSentence(sentence,fromLanguage,toLanguage));
        if(!translateSentenceSynonim(sentence,fromLanguage,toLanguage,0).equals(translations.get(0)))
            translations.add(translateSentenceSynonim(sentence,fromLanguage,toLanguage,0));
        if(!translateSentenceSynonim(sentence,fromLanguage,toLanguage,1).equals(translations.get(1))
                && !translateSentenceSynonim(sentence,fromLanguage,toLanguage,1).equals(translations.get(0)))
            translations.add(translateSentenceSynonim(sentence,fromLanguage,toLanguage,1));
        return translations;
    }


    //metoda ce intoarce definitiile unui cuvant, sortate
    ArrayList<Definition> getDefinitionsForWord(String word, String language){
        ArrayList<Definition> definitions = new ArrayList<>();
        int i,ok;

        for(Definition definition1 : searchWord(word,language).getDefinitions()){
            i=0;
            ok=0;
            if(definitions.size()==0){//inseram primul element in lista
                definitions.add(definition1);
                continue;
            }
            for (Definition definition2 : definitions) {//iteram prin definitiile cuvantului si comparam cu cele deja gasite
                if (definition2.getYear() >= definition1.getYear()) {
                    definitions.add(i, definition1);//adaugam inaintea primei definiitii cu an de publicare mai mare
                    ok = 1; //ok ne spune daca trebuie sa mai adaugam sau nu la final
                    break;
                }
                i++;
            }
            if(ok == 1)//daca ok e 1 nu mai adaugam
                continue;
            definitions.add(definition1);//adaugam la finalul listei pt ca e cel mai mare an
        }
        return definitions;
    }

}
