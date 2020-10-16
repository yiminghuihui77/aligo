package com.huihui.aligo.collection;

/**
 * 自定义Map接口
 *
 * @author minghui.y
 * @create 2020-10-15 18:30
 **/
public interface ExtMap<K, V> {

    void put(K key, V value);

    V get(K key);

    int size();

    interface Entry<K, V> {
        K getKey();
        V getValue();
        V setValue(V value);
    }
}
