package com.huihui.aligo.collection;

/**
 * 自定义链表list （待完全实现）
 *
 * @author minghui.y
 * @create 2020-10-12 21:01
 **/
public class ExtLinkedList<T> {

    /**
     * 头节点
     */
    private Node<T> head;
    /**
     * 尾结点
     */
    private Node<T> tail;
    /**
     * 链表大小
     */
    private int size = 0;

    public ExtLinkedList() {
        //do nothing..
    }

    public ExtLinkedList(T data) {
        Node<T> newNode = new Node<>(null, null, data);
        head = newNode;
        tail = newNode;
    }

    /**
     * 向链表(尾部)添加一个节点
     * @param data
     * @return
     */
    public boolean add(T data) {
        Node<T> newNode = new Node<>(null, null, data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
        return true;
    }

    /**
     * 删除一个节点
     * @param object
     * @return
     */
    public boolean remove(Object object) {
        if (size == 0) {
            return false;
        }

        if (object == null) {
            //从头遍历链表，删除第一个为data为null的节点
            for (Node<T> pointer = head; pointer != null; pointer = pointer.next) {
                if (pointer.data == null) {
                    //unlink
                    unLink(pointer);
                    return true;
                }
            }
        } else {

        }
        return  false;
    }

    private T unLink(Node<T> pointer) {
        Node<T> prev = pointer.prev;
        Node<T> next = pointer.next;
        T data = pointer.data;

        if (prev == null) {
            head = next;
            next.prev = null;
            pointer.next = null;
        }
        if (next == null) {
            tail = prev;
            prev.next = null;
            pointer.prev = null;
        }
        pointer.data = null;
        size--;
        return data;
    }



    /**
     * 节点内部类
     * @param <T>
     */
    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T data;

        Node(Node<T> prev, Node<T> next, T data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }



}
