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
    public ConfigEntity getById(int configid){
        return get(ConfigEntity.class,configid);
    }

    public void save(String key , String value ){              //直接保存在数据库中
        ConfigEntity config = new ConfigEntity();
        config.setConfigKey(key);
        config.setConfigValue(value);
        save(config);
    }

    public String getByKey(String key){ 
        String hql = "from ConfigEntity as config where config.configKey=?";
        Query query = query(hql);
        query.setString(0,key);
        List<ConfigEntity> configs = query.list();
        if(configs.isEmpty())return null;
        else return configs.get(0).getConfigValue();
    }

    public void updateValue(String key,String value){
//        String hql = "update ConfigEntity config set config.value=? where config.key=?";

        String hql = "from ConfigEntity as config where config.configKey=?";

//        String hql=”update User user set user.age=20 where user.age=18”;
        Query query = query(hql);
//        rule_relation
        query.setString(0,key);
//        query.setString(0,value);
        List<ConfigEntity> configEntity = query.list();
        configEntity.get(0).setConfigValue(value);
        update(configEntity.get(0));
    }

}
