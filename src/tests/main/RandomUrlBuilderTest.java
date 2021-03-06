package tests.main;

import main.RandomUrlBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class RandomUrlBuilderTest {

    private RandomUrlBuilder builder;
    private String filePath;

    @Before
    public void setUp() {
        filePath = "main/words.txt";
        builder = new RandomUrlBuilder();
    }

    @Test
    public void testGetUrl() {
        String url = builder.getUrl();
        System.out.println(url);
        assert url != null;
    }

    @Test
    public void testGetUrlOverloaded() {
        String url = builder.getUrl(2);
        System.out.println(url);
        assert url != null;
    }

    @Test
    public void testGetWordList() {
        ArrayList wordList = builder.getWordList(filePath);
        System.out.println(wordList);
    }

    @Test
    public void writeToFile() {
        try {
            File file = new File("append.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println("data");
            pr.close();
            br.close();
            fr.close();
        } catch (
        IOException e) {
            e.printStackTrace();
        }
    }

}
