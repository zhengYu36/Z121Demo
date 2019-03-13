package com.zy.tree.com.zy.tree.back;

import com.zy.tree.BinaryTree;

import java.util.ArrayList;

/**
 * <ul>
 * <li>文件包名 : com.zy.tree.com.zy.tree.back</li>
 * <li>创建时间 : 2018/8/7 10:23</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class BinaryTree0 implements Tree0{

    private Node0 root;

    public static void main(String[] args) {
        BinaryTree0 bt = new BinaryTree0();
        bt.insert(3);
        bt.insert(2);
        bt.insert(6);
        System.out.println(bt.find(3));

    }

    @Override
    public Node0 find(int key) {

        Node0 current = root;
        while(null != current){
            if(current.t > key ){
                current = current.leftChild;
            }else if(current.t < key){
                current = current.rightChild;
            }else{
                return current;
            }
        }
        return null;
    }

    @Override
    public boolean insert(int t) {

        Node0 newNode = new Node0(t);
        if(null == root){
            root = newNode;
            return true;
        }else{
            Node0 current = root;
            Node0 parent = null;
            while(current != null){
                parent = current;
                if(current.t > t){
                    current  = current.leftChild;
                    if(current == null){
                        parent.leftChild = newNode;
                        return true;
                    }
                }else{
                    current = current.rightChild;
                    if(current == null){
                        parent.rightChild = newNode;
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
