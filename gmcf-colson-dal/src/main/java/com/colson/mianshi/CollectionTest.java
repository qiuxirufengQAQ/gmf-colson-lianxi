package com.colson.mianshi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionTest {

    /**
     * ArrayList理解
     */
    @Test
    public void testArrayList(){
        // 底层为数组，在初始化的时候，将该数组赋值为默认的空数组,默认大小为10
        List list = new ArrayList();
        List list2 = new ArrayList();
        list2.add("a");
        list2.add("b");
        // Appends the specified element to the end of this list
        // 添加这个元素到数组的最后,先确认现在的容器是否可以容的下新元素添加，如果不够，就进行扩容，扩容为1.5倍和传入size的较大值
        list.add("1234");
        list.add("5678");
        // 首先会看添加的index是不是在已经添加的list范围内，如果不是，就抛异常
        // 再检查容器是否能够容的下一个新元素的添加，不够就扩容，扩容为1.5倍
        // 用System.arraycopy()方法进行复制，使得中间空出一个位置来，最后将新元素添加到这个位置上
        list.add(1,'5');
        // 将被加的list转成array,计算原先的size+newSize够不够容器，不够的话，进行扩容，1.5倍和size+newSize最大值
        list.addAll(list2);
        // 直接返回size变量
        list.size();
        // 遍历所有的元素，都置为空，size=0
//        list.clear();
        // 遍历所有元素，如果存在相等的就为true,否则为false
        list.contains("a");
        list.equals("a");
        list.set(1,'c');
        System.out.println(list.get(1));

        // 删除是将第一个匹配的值给删掉，size-1，将数组index之后的挨个复制到前边
        list.remove(1);
        list.remove("1234");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("iterator"+iterator.toString());

        System.out.println(list.toString());

    }
}
