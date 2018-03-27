package com.yong.mark.vo;

import lombok.Data;

import java.util.List;

/**
 * @acthor yong.a.liang
 * @date 2018/02/10
 */
@Data
public class HotPlayerSummary {

    private long buyIn;
    private long dd;
    private long hours;
    private List<HotPlayerPatron> patronList;
}
