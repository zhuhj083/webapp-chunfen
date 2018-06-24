package com.test.concurrency;

public class FinalExample {
    int i;
    final int j ;

    static FinalExample obj ;

    public FinalExample(){
        i = 1 ;
        j = 2 ;
    }

    public static void writer (){
        obj = new FinalExample();
    }

    public  static void reader(){
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
        System.out.println("a="+a+" b="+b);
    }

    public static  void  main(String args[]){
        Thread a = new Thread(new Runnable() {
            public void run() {
                writer();
            }
        });

        Thread b = new Thread(new Runnable() {
            public void run() {
                reader();
            }
        });

        a.start();
        b.start();


        System.out.println("end 11");
    }
}
