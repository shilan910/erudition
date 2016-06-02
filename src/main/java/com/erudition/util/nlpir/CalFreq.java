package com.erudition.util.nlpir;

/**
 * Created by sl on 16-6-1.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CalFreq {

    static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        InputWord();
        FileReader fr = new FileReader("/home/sl/test/baike.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String outline[] = new String[2000];
        int count;
        int progress = 0;

        while (line != null) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line);
            progress++;
            System.out.println("now is processing the " + progress + "'s news");
            outline = line.split(" ", -1);
            for (int i = 0; i < outline.length; i++) {
                if (map.containsKey(outline[i]) != false) {
                    count = map.get(outline[i]) + 1;
                    map.put(outline[i], count);
                    System.out.println("test : "+outline[i]);
                }
            }

        }
        fr.close();
        br.close();
        WriteWord();

    }

    // put the word into the map
    public static void InputWord() throws IOException {
        System.out.println("begin get the word");
        FileReader fr = new FileReader("/home/sl/test/baike.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = new String();
        while (line != null) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
            map.put(line, 0);
        }
        br.close();
        fr.close();
        System.out.println("get the word is ok");
    }

    // write word and frequency into the file
    public static void WriteWord() throws IOException {
        System.out.println("begin write the word");
        File file = new File("/home/sl/test/wordfreq.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter("wordfreq.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            int value = entry.getValue();
            bw.write(key + " " + value);
            System.out.println(key + " : " + value);
            bw.newLine();
            bw.flush();
        }
        fw.close();
        bw.close();
        System.out.println("write the word is ok");
    }

}


