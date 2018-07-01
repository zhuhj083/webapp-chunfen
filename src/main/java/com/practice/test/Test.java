package com.practice.test;

import java.util.*;

/**
 * Created by zhj on 2018/6/27.
 */

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(12);
        list.add(4);
        list.add(31);
        list.add(1);

        Collections.sort(list);

        for (Integer integer: list) {
            System.out.println(integer + "\t");
        }

        Map<String ,String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        map.put("one","1");
        map.put("four","4");
        map.put("three","3");

        for (String key :map.keySet()) {
            System.out.println("key="+key+",value="+map.get(key));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);


    }

    public final TestComparator<Test> c = new TestComparator();



    static class TestComparator<Test> implements Comparator{
        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    }

}