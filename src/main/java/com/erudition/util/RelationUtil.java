package com.erudition.util;

import com.erudition.bean.FilesEntity;
import com.erudition.dao.ResourcesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by tsj on 16-5-30.
 */
public class RelationUtil {

    private RelationUtil(){
    }

    public static String[]  findRelation(int rule,String keyword,List<FilesEntity> files){
        //当用户点击文件上传的时候认为数据库中将添加新的文件数据，此时从数据库中取出所有文件信息备用
        //然后上传文件后调用该工具方法，根据设定好的规则设置关联文件
        //TODO 具体逻辑实现方案有待确定
        String relations[] = new String[100];
        for(FilesEntity f:files){
            //处理逻辑，循环判断数据库中的每一个文件，若符合规则则认为该文件与新上传的文件具有关联关系
        }

        return relations;//返回的是认为与该文件有关联关系的所有文件的id组成的数组
    }
}
