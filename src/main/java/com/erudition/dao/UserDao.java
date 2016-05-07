package com.erudition.dao;

import com.erudition.bean.UserEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
@Repository("userDao")           //调用的接口
public class UserDao extends BaseDao {
    public UserEntity getById(int userid){
        return get(UserEntity.class,userid);
    }     //返回一个UserEntity对象？？这个是干嘛的

    public void save(String userName , String password ){              //直接保存在数据库中
        UserEntity user = new UserEntity();
        user.setUsername(userName);
        user.setPassword(password);
        save(user);
    }

    public UserEntity getByName(String userName){              //返回一个Bean对象>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>得到了一行
        String hql = "from UserEntity as user where user.username=?";
        Query query = query(hql);
        query.setString(0,userName);       //这是什么意思？
        List<UserEntity> member = query.list();
        return member.get(0);
    }

    /**
     * 判断用户名是否存在
     * @param
     * @return
     */
    public boolean isUserNameExit(String userName){

        if(HQuery("username",userName).size() == 0){          //用的是下面的函数
            return false;
        }

        return true;

    }

    /**
     * 查询
     * @param colume
     * @param value
     * @return
     */
    private List<UserEntity> HQuery(String colume , String value){
        String hql = "from UserEntity as user where user."+colume+"=?";     //user
        Query query = query(hql);
        query.setString(0, value);
        List<UserEntity> results = query.list();
        return results;
    }
}
