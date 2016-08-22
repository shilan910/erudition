package com.erudition.dao;

import com.erudition.bean.StatisticsEntity;
import com.erudition.entity.Statistics;
import com.erudition.util.others.CompareForStatistics;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sl on 16-8-21.
 */
@Repository("statisticsDao")
public class StatisticsDao extends BaseDao {


    public List<Statistics> getDataByWeek(){

        List<Statistics> statistics = new ArrayList<>();
        long time = System.currentTimeMillis();//这就是距离1970年1月1日0点0分0秒的毫秒数
        System.out.println(time);//与上面的相同
        long timeOfWeek = time - 1440000*7;

        List<StatisticsEntity> statisticsEntities = getAll("StatisticsEntity");

        CompareForStatistics cmp = new CompareForStatistics();
//        Arrays.sort(statistics,cmp);

//        for()

        return statistics;
    }

//
//    private List<StatisticsEntity> getAll(){
//        return getAll();
//    }


}
