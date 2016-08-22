package com.erudition.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by sl on 16-8-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:conf/springmvc.xml")
})
public class TextReadTest {

    @Test
    public void testGetStringFromWord(){
        TextRead textRead = new TextRead();
//        String res = textRead.getStringFromWord("/usr/local/erudition/测试/123/321/F81EFC269CA3F55BF41B69FD1D9A1D58.docx");
        String res = textRead.getStringFromWord("/home/sl/8E0AEA8A15473A935853BF83237EF5EE.doc");
//        3F55BF41B69

        System.out.println(res);
    }



}
