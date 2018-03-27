package com.yong.mark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @acthor yong.a.liang
 * @date 2018/01/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private static final long serialVersionUID = -7599094140714524562L;
    private String name;
    private String roles;
}
