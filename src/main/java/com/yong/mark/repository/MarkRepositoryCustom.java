package com.yong.mark.repository;

import com.yong.mark.vo.HotPlayerPatron;
import com.yong.mark.vo.PatronValue;
import com.yong.mark.model.Mark;

import java.util.List;

/**
 * @author LiangYong
 * @date 2017/11/23
 */
public interface MarkRepositoryCustom {

    List<Mark> findAllActivityItems();
    List<HotPlayerPatron> findAggregateByCustom();
    List<PatronValue> findAggregateBuyReduce();
}
