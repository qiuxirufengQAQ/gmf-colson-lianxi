package com.colson.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Student a = new Student("a");
        Student b = new Student("b");
        swap(a,b);
        System.out.println("a:"+a.getName());
        System.out.println("b:"+b.getName());
    }

    public static void swap(Student a,Student b){
        Student temp;
        temp = a;
        a = b;
        b = temp;
    }

    @Test
    public void testJedis() throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        // string
        jedis.set("key1","wangweiguo");

        jedis.append("key1"," ni zhen shuai");

//        删除某个键
//        jedis.del("key1");
//        删除之后，查询的时候为null
//        System.out.println(jedis.get("key1"));
        jedis.set("s1", String.valueOf(1));
//        自增会转化string类型为数值
//        自增或自减
        System.out.println(jedis.incr("s1"));
        System.out.println(jedis.decr("s1"));
        System.out.println(jedis.incrBy("s1", 2));
        System.out.println(jedis.decrBy("s1", 2));

        System.out.println( jedis.getrange("key1", 0, 3));
        jedis.setrange("key1", 0, "zhang");
        System.out.println(jedis.get("key1"));

        jedis.setex("key2",10,"ceshi");
//        Thread.sleep(10000);
        jedis.setnx("key2","cheshi");
        System.out.println(jedis.get("key2"));

        jedis.mset("ceshi1","ceshi2");
        System.out.println(jedis.get("ceshi1"));

//        list
        jedis.lpush("l1", "list1");
        jedis.lpush("l2","list2");
        System.out.println(jedis.lpop("l2"));
        jedis.rpush("l3","list3");
        System.out.println(jedis.lpop("l3"));
        System.out.println(jedis.lindex("l1", 5));


    }
}
