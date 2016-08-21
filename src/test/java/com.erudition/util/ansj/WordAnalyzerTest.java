package com.erudition.util.ansj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 16-8-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:conf/springmvc.xml")
})
public class WordAnalyzerTest {

        @Test
        public void testCount(){
                WordAnalyzer wordAnalyzer = new WordAnalyzer();
                List<String> words = new ArrayList<>();
                words.add("这是一个测试样例！");
                words.add("这也是一个测试样例！");
                words.add("this is a test case!");

                try {
                        List<String> word1 = wordAnalyzer.count(words,1);
                        for(String word : word1){
                                System.out.println(word);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }


}
