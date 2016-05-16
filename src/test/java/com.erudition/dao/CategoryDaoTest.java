package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


/**
 * Created by sl on 16-2-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:conf/spring.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:conf/springmvc.xml")
})

public class CategoryDaoTest {

    @Autowired
    @Qualifier("categoryDao")
    private CategoryDao categoryDao;

    @Test
    public void testGetSecondCategory(){

        List<CategoryEntity> secondCategory = categoryDao.getSecondCategoryByFirst(1);

        Assert.assertNotNull(secondCategory);

        for(CategoryEntity cateBean : secondCategory){
            System.out.println(cateBean.getCategoryName());
            System.out.println();
        }
    }

    @Test
    public void testGetThirdCategory(){

        List<CategoryEntity> secondCategory = categoryDao.getThirdCategoryByFS(1,2);

        Assert.assertNotNull(secondCategory);

        for(CategoryEntity cateBean : secondCategory){
            System.out.println(cateBean.getCategoryName());
            System.out.println();
        }
    }
}
