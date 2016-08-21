package com.erudition.util.ansj;

import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.IOException;
import java.util.*;

/**
 * Created by sl on 16-7-27.
 */
public class WordAnalyzer {

    public List<String> count(List<String> words , int topNum) throws IOException {

        Map<String,Integer> wordCount = new HashMap<String, Integer>();

        System.out.println("开始进行分词...");

        loadAnsjDic();

        Set<String> stopWords = StopWord.getStopWord();

        for(String word : words){
            List<Term> result = ToAnalysis.parse(word);

            for (Term term : result) {
                String name = term.getName();
                if (!stopWords.contains(name) && !name.equals(" ")) {

                    if (!wordCount.containsKey(name)) {
                        wordCount.put(name, 1);
                    } else {
                        int it_count = wordCount.get(name);
                        wordCount.remove(name);
                        wordCount.put(name, it_count + 1);
                    }

                }

            }
        }

        //统计词频
        double sum=0;
        for(Map.Entry<String,Integer> it : wordCount.entrySet()){
            sum += it.getValue();
        }
        System.out.println("分词结束！共分出"+(long)sum+"个词语（含重复词语）！");


        System.out.println("开始进行词频统计...");

        List<Map.Entry<String, Integer>> temp =
                new ArrayList<Map.Entry<String, Integer>>(wordCount.entrySet());

        Collections.sort(temp, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
                // return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });


        List<String> result = new ArrayList<String>();

        for (int i=0; i<temp.size(); i++) {
            String outString = temp.get(i).getKey()+"  "+
                    String.format("%.6f", temp.get(i).getValue()/sum);
            result.add(outString);
        }

        System.out.println("统计结束！共统计词语"+temp.size()+"个（无重复词语）！");
        return result;

    }





    private void loadAnsjDic() {
        for (int i = 0; i < Dictionary.dictionary.length; i++) {
            System.out.println(Dictionary.dictionary[i]);
            UserDefineLibrary.insertWord(Dictionary.dictionary[i], "userDefine",1000);
        }
    }





}
