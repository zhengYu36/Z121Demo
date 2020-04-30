package com.zy.domain;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * <ul>
 * <li>文件包名 : com.zy.domain</li>
 * <li>创建时间 : 2019/5/24 20:08</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class UserInfo implements Serializable {

    @Min(value = 12)
    private Integer id;

    private String name;
    private String realName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
