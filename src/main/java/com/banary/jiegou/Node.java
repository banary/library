package com.banary.jiegou;

/**
 * @Description
 * @Author eden
 * @Date 2018/6/28 上午11:38
 */
public class Node<T extends Comparable<T>> {

    private T t;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    private boolean black = true;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }
}
