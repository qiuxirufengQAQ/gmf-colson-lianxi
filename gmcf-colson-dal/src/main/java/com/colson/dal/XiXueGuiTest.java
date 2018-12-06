package com.colson.dal;

public class XiXueGuiTest {
    public static void main(String[]args){
        for(int i=1000;i<10000;i++){
            for(int j=10;j<100;j++){
                for(int k=j;k<100;k++){
                    if(i==j*k){
                        String j1=String.valueOf(j/10);
                        String j2=String.valueOf(j%10);
                        String k1=String.valueOf(k/10);
                        String k2=String.valueOf(k%10);
                        String i1=String.valueOf(i);
                        if(i1.equals(String.valueOf(j1+j2+k1+k2))||
                                i1.equals(String.valueOf(j1+j2+k2+k1))||
                                i1.equals(String.valueOf(j1+k1+j2+k2))||
                                i1.equals(String.valueOf(j1+k1+k2+j2))||
                                i1.equals(String.valueOf(j1+k2+j2+k1))||
                                i1.equals(String.valueOf(j1+k2+k1+j2))||

                                i1.equals(String.valueOf(j2+j1+k1+k2))||
                                i1.equals(String.valueOf(j2+j1+k2+k1))||
                                i1.equals(String.valueOf(j2+k1+j1+k2))||
                                i1.equals(String.valueOf(j2+k1+k2+j1))||
                                i1.equals(String.valueOf(j2+k2+j1+k1))||
                                i1.equals(String.valueOf(j2+k2+k1+j1))||

                                i1.equals(String.valueOf(k1+k2+j1+j2))||
                                i1.equals(String.valueOf(k1+k2+j2+j1))||
                                i1.equals(String.valueOf(k1+j1+k2+j2))||
                                i1.equals(String.valueOf(k1+j1+j2+k2))||
                                i1.equals(String.valueOf(k1+j2+k2+j1))||
                                i1.equals(String.valueOf(k1+j2+j1+k2))||

                                i1.equals(String.valueOf(k2+j1+k1+j2))||
                                i1.equals(String.valueOf(k2+j1+j2+k1))||
                                i1.equals(String.valueOf(k2+j2+j1+k1))||
                                i1.equals(String.valueOf(k2+j2+k1+j1))||
                                i1.equals(String.valueOf(k2+k1+j1+j2))||
                                i1.equals(String.valueOf(k2+k1+j2+j1))
                                ){
                            System.out.println(i1+"="+j+"*"+k);
                        }
                    }
                }
            }
        }
    }
}
