package com.erudition.dao;

import com.erudition.bean.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by sl on 16-6-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:conf/springmvc.xml")
})
public class ResourcesDaoTest {

    @Autowired
    @Qualifier("resourcesDao")
    private ResourcesDao resourcesDao;

    @Test
    public void testSaveFile() throws IOException {

        UserEntity user = new UserEntity();
        user.setUserName("123");
        user.setId(4);
        File file0 = new File("/home/sl/test/123.doc");
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "/home/sl/test/123.doc";
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 10000;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file0) throws IOException, IllegalStateException {

            }
        };
        file.transferTo(file0);
        String url = resourcesDao.saveFiles("1","2","3","12345",file.getName(),file,
                user);

        System.out.println(url);

    }

}
