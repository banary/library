package com.banary.jiegou;

/**
 * @Description
 * @Author eden
 * @Date 2018/6/28 下午4:58
 */
public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    // x节点的右子节点y
    public void leftRotate(Node<T> x){
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        Node<T> y = x.getRight();
        if(y.getLeft() != null){
            x.setRight(y.getLeft());
            y.getLeft().setParent(x);
        }
        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        //如果x的父节点为空，则把y设为父节点
        if(x.getParent() == null){
            root = y;
        }else{
            y.setParent(x.getParent());
            if(x == x.getParent().getLeft()){//如果x是左子节点
                x.getParent().setLeft(y);//则也将y设为左子节点
            }else{
                x.getParent().setRight(y);//否则将y设为右子节点
            }
        }

    }


    @Override
    public Node<T> find(int key) {
        return null;
    }

    @Override
    public Node<T> findMax() {
        return null;
    }

    @Override
    public Node<T> findMin() {
        return null;
    }

    @Override
    public void infixOrder(Node<T> current) {

    }

    @Override
    public void preOrder(Node<T> current) {

    }

    @Override
    public void postOrder(Node<T> current) {

    }

    @Override
    public void insert(T t) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void find(T t) {

    }
}
