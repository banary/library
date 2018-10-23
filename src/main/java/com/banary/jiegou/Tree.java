package com.banary.jiegou;

/**
 * @Description
 * @Author eden
 * @Date 2018/6/28 上午11:33
 */
public interface Tree<T extends Comparable<T>> {

    public Node<T> find(int key);

    //查找最大值
    public Node<T> findMax();
    //查找最小值
    public Node<T> findMin();

    //中序遍历
    public void infixOrder(Node<T> current);
    //前序遍历
    public void preOrder(Node<T> current);
    //后序遍历
    public void postOrder(Node<T> current);

    void insert(T t);

    void delete(T t);

    void find(T t);
}
