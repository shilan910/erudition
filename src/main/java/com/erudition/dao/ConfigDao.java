package com.erudition.dao;

import com.erudition.bean.ConfigEntity;
import com.erudition.bean.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsj on 16-8-21.
 */
@Repository("configDao")
public class ConfigDao extends BaseDao{
    public ConfigEntity getById(int userid){
        return get(ConfigEntity.class,userid);
    }

    public void save(String key , String value ){              //直接保存在数据库中
        ConfigEntity config = new ConfigEntity();
        config.setKey(key);
        config.setValue(value);
        save(config);
    }

    public String getByKey(String key){ 
        String hql = "from ConfigEntity as config where config.key=?";
        Query query = query(hql);
        query.setString(0,key);
        List<ConfigEntity> configs = query.list();
        if(configs.isEmpty())return null;
        else return configs.get(0).getValue();
    }

    public void updateValue(String key,String value){
        String hql = "update ConfigEntity as config set config.value='"+value+"' where config.key='"+key+"'";
        Query query = query(hql);
        query.executeUpdate();
    }

}
