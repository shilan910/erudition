package com.erudition.util.others;

import com.erudition.bean.StatisticsEntity;

import java.util.Comparator;

/**
 * Created by sl on 16-8-21.
 */
public class CompareForStatistics implements Comparator<StatisticsEntity> {

    @Override
    public int compare(StatisticsEntity o1, StatisticsEntity o2) {
        if (o1.getDate().getTime() >= o2.getDate().getTime())
            return 1;
        else return -1;
    }

    @Override
    public Comparator<StatisticsEntity> reversed() {
        return null;
    }

}
