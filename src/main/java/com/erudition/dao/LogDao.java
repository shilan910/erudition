package com.erudition.dao;

import com.erudition.bean.ConfigEntity;
import com.erudition.bean.LogEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

/**
 * Created by tsj on 16-8-21.
 */
@Repository("logDao")
public class LogDao extends BaseDao{

    public LogEntity getById(int logid){
        return get(LogEntity.class,logid);
    }

    public void deteleLogByFileId(int fid){
        String hql = "from LogEntity as log where log.fileId="+fid;
        Query query = query(hql);
        List<LogEntity> logs = query.list();
        for(LogEntity log : logs){
            delete(log);
        }
    }

}
