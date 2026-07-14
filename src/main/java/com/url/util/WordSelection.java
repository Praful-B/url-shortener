package com.url.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.Random;

public class WordSelection {
    private ArrayList<String> validWords;

    WordSelection() {
        populateValidWords();
    }

    /**
     * will generate a word combination to be used for generating short url code
     *
     * @param wordCount no of words required in the combination
     * @return          returns a word combination of specified length with '_' in between each word
     *                  will add a _ at the end of word combination string if '1' word count is given
     */
    public String generateWordCombination(int wordCount){
        ArrayList<String> words = this.validWords;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < wordCount; i++){
            sb.append(words.get(random.nextInt(words.size())));
            sb.append('_');
        }
        return sb.toString();
    }

    /**
     * this method takes in a ArrayList< String > words and returns a
     * hashmap to visualize the length of words in your array
     *
     * @param words a list of words whose length will be calculated
     * @return      a hashmap of < length, count of words with that length >
     * */
    private static HashMap<Integer, Integer> wordLengthCount(ArrayList<String> words){

        HashMap<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word.length(), map.getOrDefault(word.length(), 0) + 1);
        }

        return map;
    }

    /**
     * @param path for the text file
     * @return     a list of all the words in the file
     */
    private static ArrayList<String> convertWordsFromTxtToArrayList(String path){
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = br.readLine()) != null){
                words.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    /**
     * @param words     a list of words to perform actions on
     * @param minLength min characters for a word
     * @param maxLength max characters for a word
     * @return          a list of words that have length of at least min length and at most max length
     */
    private static ArrayList<String> discardWordsBasedOnLength(ArrayList<String> words, int minLength, int maxLength){
        ArrayList<String> res = new ArrayList<>();
        for(String word : words){
            if(word.length() >= minLength && word.length() <= maxLength){
                res.add(word);
            }
        }
        return res;
    }

    /**
     * many words needed to be filtered because they contain characters such as '.' will interfere with the url
     * and some words added non alpha characters at the end which conflicted with the length rules required by
     * other functions
     *
     * @param words a list of words to choose from
     * @return      returns a refined list of words with all the words containing alphabets only
     */
    private static ArrayList<String> discardWordsWithNonAlphaCharacters(ArrayList<String> words){
        ArrayList<String> res = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");

        for(String word : words){
            if(pattern.matcher(word).matches()){
                res.add(word);
            }
        }
        return res;
    }

    private void populateValidWords() {
//    src/main/java/com/url/miscellaneous/words.txt
        final String path = "src/main/java/com/url/miscellaneous/words.txt";
        ArrayList<String> res = new ArrayList<>();

        res = convertWordsFromTxtToArrayList(path);
        res = discardWordsBasedOnLength(res, 4, 6);
        res=  discardWordsWithNonAlphaCharacters(res);

        this.validWords = res;
    }


}
