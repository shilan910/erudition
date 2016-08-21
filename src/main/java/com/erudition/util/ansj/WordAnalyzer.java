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

    public String count(String words , int topNum) throws IOException {

        Map<String,Integer> wordCount = new HashMap<String, Integer>();

        System.out.println("开始进行分词...");

        loadAnsjDic();

        Set<String> stopWords = StopWord.getStopWord();


        List<Term> result = ToAnalysis.parse(words);

        for (Term term : result) {
            String name = term.getName();
            if (!stopWords.contains(name) && !name.equals(" ") && name!=null && !name.equals("") && !name.equals("\n")) {
                if (!wordCount.containsKey(name)) {
                    wordCount.put(name, 1);
                } else {
                    int it_count = wordCount.get(name);
                    wordCount.remove(name);
                    wordCount.put(name, it_count + 1);
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


//        List<String> resultKey = new ArrayList<String>();
        String resultKey = "";
        int maxn = temp.size()>topNum?topNum:temp.size();

//        for (int i=0; i<maxn; i++) {
//            String outString = temp.get(i).getKey()+"  "+
//                    String.format("%.6f", temp.get(i).getValue()/sum);
//            resultKey.add(outString);
//        }

        for (int i=0; i<maxn; i++) {
            resultKey += temp.get(i).getKey();
            resultKey += " ";
            System.out.println(temp.get(i).getKey()+"  "+
                    String.format("%.6f", temp.get(i).getValue()/sum));
        }

        System.out.println("统计结束！共统计词语"+temp.size()+"个（无重复词语）！");
        return resultKey;

    }





    private void loadAnsjDic() {
        for (int i = 0; i < Dictionary.dictionary.length; i++) {
//            System.out.println(Dictionary.dictionary[i]);
            UserDefineLibrary.insertWord(Dictionary.dictionary[i], "userDefine",1000);
        }
    }





}
