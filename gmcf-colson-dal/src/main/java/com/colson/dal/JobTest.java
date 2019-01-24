package com.colson.dal;

import java.util.Date;

public class JobTest extends Thread{
        private Date date;
        public void run(){
            try{
                while(true){
                    Thread.sleep((int)(Math.random()*10000));
                    date = new Date();
                    System.out.println(date);
                    //定时执行任务
                }
            }catch(Exception e){
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        JobTest jobTest = new JobTest();
        jobTest.run();
    }
}
