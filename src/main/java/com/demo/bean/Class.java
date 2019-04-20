package com.demo.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <ul>
 * <li>文件包名 : com.demo.bean</li>
 * <li>创建时间 : 2019/4/17 16:37</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
@XmlRootElement
public class Class {

    private String name;

    private String no;

    public Class(){

    }

    public Class(String name, String no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
