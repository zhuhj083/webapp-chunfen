package com.practice.algorithms;

public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity){
        keys=(Key[])(new Comparable[capacity]);
        values= (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0 ;
    }

    public Value get(Key key){
        if (isEmpty()){
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0 ){
            return values[i];
        }else{
            return null;
        }
    }

    public void put(Key key,Value value){
        //查找键，找到则更新，否则创建新的元素
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }

        //将所有的更大的键向后移动一格来腾出位置（从后向前移动）
        for (int j = N ; j > i ; j -- ){
            keys[j] = keys[j-1];
            values[j] = values [j-1];
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    public int rank(Key key){
        int lo = 0 , hi = N-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0 ) {
                hi = mid - 1 ;
            }else if(cmp > 0 ){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        return keys[k];
    }

    /**
     * 大于等于key的最小键
     */
    public Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(key);
        if (i == N) return null;
        else return keys[i];

    }

    /**
     * 小于等于key的最大元素
     */
    public Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0)
            return keys[i];
        if (i == 0)
            return null;
        else
            return keys[i-1];
    }

    public Key delete(Key key){ {
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            if (isEmpty()) return null;

            // compute rank
            int i = rank(key);

            // key not in table
            if (i == N || keys[i].compareTo(key) != 0) {
                return null;
            }

            //
            for (int j = i; j < N-1; j++)  {
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }

            N--;
            keys[N] = null;  // to avoid loitering
            values[N] = null;

            return key;
        }
    }

    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo , Key hi){
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo);i < rank(hi) ; i ++){
            q.enqueue(keys[i]);
        }

        if (contains(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

}


