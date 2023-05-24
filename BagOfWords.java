import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class BagOfWords {
     
    static int CheckListIndex=0;
    static int tem=0;
    static int repeatvalue=0;
    static List<String> checklist = new ArrayList<>();
    static int counter=0;
    static String filePath=null;
    static String filepath1=null;
    static String filepath2=null;
    static String filepath3=null;
    static String filepath4=null;
    static JTextArea textArea = new JTextArea();
    static JTextArea textArea1 = new JTextArea();
    static JTextArea textArea2 = new JTextArea();
    static JTextArea textArea3 = new JTextArea();
    static JTextArea textArea4 = new JTextArea();
    static Map<String, Double> similarityRatios = new TreeMap<>(Comparator.reverseOrder());
          
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
    public static List<String> splitTextIntoWordsIgnoreQuotes(String text) {
       
        String newText = text.replaceAll("\"([^\"]*)\"", "");
        
        Pattern pattern = Pattern.compile("[\\p{L}\\p{N}]+");
        Matcher matcher = pattern.matcher(newText);
        List<String> words = new ArrayList<>();
    
        while (matcher.find()) {
            words.add(matcher.group());
        }
    
        return words;
    }
    
    public static void calculateTermFrequency(List<String> words, JTextArea t,String path) {
        Map<String, Integer> termFrequency = new HashMap<>();
        
            
        for (String word : words) {
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        // Adding table headers
        t.append(path+"\nWord\tFrequency\n");
        for (Map.Entry<String, Integer> entry : termFrequency.entrySet()) {
            t.append(entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }
    

    public static String getword(List <String> file1 ,int i)
    {
        return file1.get(i);
    }

    private static List<String> setCheckList(List<String> file, int start, int subListLength) {
        return new ArrayList<>(file.subList(start, start + subListLength));
    }

      
    
    
    private static void sortphrasetable(Map<String, Double> similarityRatios2, JTextArea t) {
        t.append("Ranked highest overall matches\n");
    
        List<Double> sortlist = new ArrayList<>(similarityRatios2.values());
        Collections.sort(sortlist, Collections.reverseOrder());
    
        for (int i = 0; i < 4; i++) {
            Double value = sortlist.get(i);
            for (Map.Entry<String, Double> entry : similarityRatios2.entrySet()) {
                if (entry.getValue().equals(value)) {
                    t.append(value + "' \t " + entry.getKey() + "\n");
                    break;
                }
            }
        }
    }
    
    private static void checkTheWords(List<String> file1, List<String> file2, int subListLength,myframe f,JTextArea t,String path1,String path2) {
        t.append("................................\n"+path1+"\ncompare\n"+path2+"\n"+"Plagiarism part(Threshold:5)\n");
        List<String> re=new ArrayList<>();

        for (int i = 0; i < file1.size() - subListLength + 1; i++) { // 遍历file1的子串
            List<String> subList = setCheckList(file1, i, subListLength);
            
            for (int j = 0; j < file2.size() - subListLength + 1; j++) { // 遍历file2的子串
                List<String> targetSubList = setCheckList(file2, j, subListLength);

                boolean match = true;
                for (int k = 0; k < subListLength; k++) { // 对比两个子串
                    if (!subList.get(k).equals(targetSubList.get(k))) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    
                    for(int k=0;k<5;k++)
                    {
                         re.add(subList.get(k));
                    }
                    t.append(subList+"\n"); // 打印匹配的子串
                }
            }
        }
        Set <String> uni =new HashSet<String>(re);
        re.clear();
        re.addAll(uni);
        double similarity = (double)re.size()/ file1.size();
       similarityRatios.put(path1 + " vs " + path2, similarity);
       t.append("repeat number:"+re.size()+"ratios"+similarity+"\n");
    }
   
       
            public static void actionPerformed(myframe f) {
                   
                
               if (counter< 5) {
                    String input = ((JTextComponent) f.textField).getText();
                    ((JTextComponent) f.textField).setText("");

                    if (counter == 0) {
                     filePath=input;
                    } else if (counter == 1) {
                        filepath1=input;
                    } else if (counter == 2) {
                        filepath2=input;
                    } else  if (counter ==3){
                        filepath3=input;
                    }
                    else if(counter==4)
                    {
                        filepath4=input;
                    }

                    textArea.append("have setted " + input + "\n");
                    counter++;
                    
                } 
                else {
                    
                    try {
                        String content = readFileContent(filePath);
                        String content1 = readFileContent(filepath1);
                        String content2 = readFileContent(filepath2);
                        String content3 = readFileContent(filepath3);
                        String content4 = readFileContent(filepath4);
            
                        List<String> words =splitTextIntoWords(content);
                        List<String> words1=splitTextIntoWords(content1);
                        List<String> words2=splitTextIntoWords(content2);
                        List<String> words3=splitTextIntoWords(content3);
                        List<String> words4=splitTextIntoWords(content4);
                       
            
                        calculateTermFrequency(words,textArea,filePath);
                        calculateTermFrequency(words1,textArea1,filepath1);
                        calculateTermFrequency(words2,textArea2,filepath2);
                        calculateTermFrequency(words3,textArea3,filepath3);
                        calculateTermFrequency(words4,textArea4,filepath4);
                        words1.clear();
                        words2.clear();
                        words3.clear();
                        words4.clear();
                        words.clear();

                         words = splitTextIntoWordsIgnoreQuotes(content);
                        words1= splitTextIntoWordsIgnoreQuotes(content1);
                         words2= splitTextIntoWordsIgnoreQuotes(content2);
                         words3= splitTextIntoWordsIgnoreQuotes(content3);
                        words4= splitTextIntoWordsIgnoreQuotes(content4);
                       


                        
                        checkTheWords(words, words1, 5,f,textArea,filePath,filepath1);
                        checkTheWords(words, words2, 5,f,textArea,filePath,filepath2);
                        checkTheWords(words, words3, 5,f,textArea,filePath,filepath3);
                        checkTheWords(words, words4, 5,f,textArea,filePath,filepath4);

                        checkTheWords(words1, words, 5,f,textArea1,filepath1,filePath);
                        checkTheWords(words1, words2, 5,f,textArea1,filepath1,filepath2);
                        checkTheWords(words1, words3, 5,f,textArea1,filepath1,filepath3);
                        checkTheWords(words1, words4, 5,f,textArea1,filepath1,filepath4);

                        textArea.append("..............................\nPhrase Match:\nfile\tpharse match\n ");
                        for (Map.Entry<String, Double> entry : similarityRatios.entrySet()) {
                            textArea.append(entry.getKey() + "\t " + entry.getValue() + "\n");
                        }
                        
                        sortphrasetable(similarityRatios,textArea);
                       
                    } catch (IOException e) {
                        textArea.append("Error reading file: " + e.getMessage());
                    }
                }
            }
    public static void main(String[] args) {
        myframe frame = new myframe("similaray program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        textArea.append("enter five filepath" + "\n");
    }
}

