package com.zy.tree.com.zy.tree.back;

/**
 * <ul>
 * <li>文件包名 : com.zy.tree.com.zy.tree.back</li>
 * <li>创建时间 : 2018/8/7 10:19</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Node0 {

    int t;  //数据
    Node0 leftChild; //左节点
    Node0 rightChild; //右节点

    public Node0(int t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Node0{" +
                "t=" + t +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
