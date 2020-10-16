package com.huihui.aligo.collection;

/**
 * 手写HashMap
 *
 * @author minghui.y
 * @create 2020-10-15 18:33
 **/
public class ExtHashMap<K, V> implements ExtMap<K, V> {

    /**
     * 默认初始容量
     */
    private final int DEFAULT_INIT_CAPACITY = 1 << 3;
    /**
     * 默认加载因子
     */
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 数组（在第一次执行put方法才初始化，实现懒加载）
     */
    private Node<K, V>[] table;
    /**
     * 节点数量
     */
    private int size;

    private float loadFactor = DEFAULT_LOAD_FACTOR;

    /**
     * 获取key的hashCode
     * @param key
     * @return
     */
    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }


    @Override
    public void put(K key, V value) {
        //1、判断table是否为null，为null则初始化数组
        //2、判断是否需要扩容
        //3、根据key的hashCode和数组长度，哈希算法后得出数组下标
        //4、指定下标的数组元素为null，则直接赋值
        //5、遍历从下标元素开始的链表，若key重复，则覆盖，否则在链表尾部追加节点

        if (table == null) {
            table = new Node[DEFAULT_INIT_CAPACITY];
        }
        if (size > loadFactor * table.length) {
            //数组扩容 + 重新哈希
            resize();
        }

        int index = getIndex(key, table.length);

        Node<K, V> newNode = new Node<>(key, value, null);

        if (table[index] == null) {
            table[index] = newNode;
            size++;
        } else {
            //哈希冲突
            for (Node<K, V> pointer = table[index];pointer != null;pointer = pointer.next) {
                if (pointer.getKey().equals(key)) {
                    //覆盖旧值
                    pointer.setValue(value);
                    break;
                }

                if (pointer.next == null) {
                    //尾部追加节点
                    pointer.next = newNode;
                    size++;
                    break;
                }
            }
        }

    }

    /**
     * 扩容 + 重新哈希
     * @return
     */
    private final Node<K,V>[] resize() {

        //数组容量扩容为原来的两倍
        int oldCapacity = table.length;
        int newCapacity = oldCapacity << 1;

        System.out.println("扩容前数组大小：" + oldCapacity);
        System.out.println("扩容后数组大小：" + newCapacity);

        Node<K,V>[] newTable = new Node[newCapacity];

        //旧数组中所有节点重新哈希后存入新的数组中
        for (int i = 0;i < oldCapacity; i++) {

            Node<K, V> pointer = table[i];

            while (pointer != null) {
                //暂存下个节点地址
                Node<K, V> next = pointer.next;
                //计算当前节点在新数组的下标
                int index = getIndex(pointer.getKey(), newCapacity);
                //遍历单向链表， 当前每个节点都放入新数组下标i处
                pointer.next = newTable[index];
                newTable[index] = pointer;

                pointer = next;
            }

            //gc
            table[i] = null;
        }

        table = newTable;
        return table;
    }

    /**
     * 根据key获取下标
     * @param key
     * @return
     */
    private int getIndex(K key, int capacity) {
        return  hash(key) % capacity;
    }

    /**
     * 打印map
     */
    void printMap() {
        StringBuffer buffer = null;
        for (int i = 0;i < table.length;i ++) {
            buffer = new StringBuffer("下标位置[" + i + "]");
            Node<K, V> node = table[i];
            while (node != null) {
                buffer.append("[key:" + node.getKey() + ", value:" + node.getValue() + "] ");
                node = node.next;
            }
            System.out.println(buffer.toString());
        }
    }

    @Override
    public V get(K key) {
        //根据key的hashCode和数组长度计算下标
        int index = hash(key) % table.length;

        for (Node<K, V> pointer = table[index]; pointer != null; pointer = pointer.next) {
            if (pointer.getKey().equals(key) || pointer.getKey() == key) {
                return pointer.getValue();
            }
        }

        return null;
    }

    @Override
    public int size() {
        return 0;
    }


    /**
     * 节点类
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements ExtMap.Entry<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
}
