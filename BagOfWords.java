import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BagOfWords {

   

    public static String readFileContent(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static List<String> splitTextIntoWords(String text) {
        Pattern pattern = Pattern.compile("[\\p{L}\\p{N}]+"); 
        Matcher matcher = pattern.matcher(text);
        List<String> words = new ArrayList<>();

        while (matcher.find()) {
            words.add(matcher.group());
        }

        return words;
    }
    public static Map<String, Integer> calculateTermFrequency(List<String> words,Map<String, Integer> termFrequency) {
        
        for (String word : words) {
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        return termFrequency;
    }
    public static void main(String[] args) {
        
            SwingUtilities.invokeLater(() -> frame.createAndShowGUI());
        
        String filePath = "C:\\Users\\erstehilfe\\Desktop\\110\\a.txt";
        String filepath1 = "C:\\Users\\erstehilfe\\Desktop\\110\\aa.txt";
        String filepath2 = "C:\\Users\\erstehilfe\\Desktop\\110\\aaa.txt";
        String filepath3 = "C:\\Users\\erstehilfe\\Desktop\\110\\aaaa.txt";
        try {
            String content = readFileContent(filePath);
            String content1 = readFileContent(filepath1);
            String content2 = readFileContent(filepath2);
            String content3 = readFileContent(filepath3);
            List<String> words = splitTextIntoWords(content);
            List<String> words1 = splitTextIntoWords(content1);
            List<String> words2 = splitTextIntoWords(content2);
            List<String> words3 = splitTextIntoWords(content3);
            Map<String, Integer> termFrequency = new HashMap<>();
            calculateTermFrequency(words, termFrequency);
            for (Map.Entry<String, Integer> entry : termFrequency.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
    }
}
