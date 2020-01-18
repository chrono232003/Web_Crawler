package main;

import utils.EnumUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class RandomUrlBuilder {

    private final String FILE_PATH = "./words.txt";

    /**
     * gives back a url with a single word.
     * @return
     */
    public String getUrl() {
        try {
            ArrayList words = getWordList(FILE_PATH);
            String word = getRandomWord(words);
            return EnumUtils.LinkParts.FIRST_PART.part + word + EnumUtils.LinkParts.LAST_PART.part;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return "Failed to get a word";
        }
    }

    /**
     * gives back a url with a n number of words.
     * @return
     */
    public String getUrl(int n) {
        try {
            ArrayList words = getWordList(FILE_PATH);

            StringBuilder builder = new StringBuilder();
            while (n > 0) {
                builder.append(getRandomWord(words));
                n--;
            }
            return EnumUtils.LinkParts.FIRST_PART.part + builder.toString() + EnumUtils.LinkParts.LAST_PART.part;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return "Failed to get a word";
        }
    }


    public ArrayList getWordList(String filePath) {
        ArrayList<String> wordArray =  new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(word -> wordArray.add(word));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordArray;
    }

    public String getRandomWord(ArrayList<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
