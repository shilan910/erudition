package com.erudition.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sl on 16-4-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:conf/springmvc.xml")
})
public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetSecondCategory() throws Exception {
        mockMvc
                .perform(get("/category/getSecondCategory/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetThirdCategory() throws Exception {
        mockMvc
                .perform(get("/category/getThirdCategory/{fid}/{sid}",1,2))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testgetFirstCategory() throws Exception {
        mockMvc
                .perform(get("/category/firstcates"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testTest(){
        String ss="2,3,4,5";
        String[] s = ss.split(",");
        for(String i : s){
            if(i != ""){
                System.out.println("si: "+i);

            }
        }
    }



}
