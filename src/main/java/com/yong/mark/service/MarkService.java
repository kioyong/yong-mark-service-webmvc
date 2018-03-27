package com.yong.mark.service;

import com.yong.mark.model.Mark;

import java.util.List;

/**
 * @author LiangYong
 * @date 2017/12/24
 */
public interface MarkService {


    Mark findOneMark(String id);
    Mark findOneMarkLatest(String id);
    List<Mark> findAllMark();
    Mark saveMark(Mark mark);
    Mark updateMark(Mark mark);

}
