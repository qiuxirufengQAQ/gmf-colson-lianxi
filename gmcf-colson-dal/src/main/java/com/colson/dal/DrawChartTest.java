package com.colson.dal;

public class DrawChartTest {
    public static void main(String[] args) {
        aiXin();
    }
    /**
     *打印心形
     */
    public static void aiXin(){
        for(float y = (float) 1.5;y>-1.5;y -=0.1)  {
            for(float x= (float) -1.5;x<1.5;x+= 0.05){
                float a = x*x+y*y-1;
                if((a*a*a-x*x*y*y*y)<=0.0)  {
                    System.out.print("^");
                }
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        String a = "123424";
        System.out.println(a.substring(0,1));
    }
}
