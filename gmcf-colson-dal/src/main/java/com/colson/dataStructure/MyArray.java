package com.colson.dataStructure;

/**	 ①、如何插入一条新的数据项

 　　②、如何寻找某一特定的数据项

 　　③、如何删除某一特定的数据项

 　　④、如何迭代的访问各个数据项，以便进行显示或其他操作
 */
public class MyArray {

	public static void main(String[] args) {
		//创建自定义封装数组结构，数组大小为4
		MyArray array = new MyArray(4);
		//添加4个元素分别是1,2,3,4
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		//显示数组元素
		array.display();
		//根据下标为0的元素
		int i = array.get(0);
		System.out.println(i);
		//删除4的元素
		array.delete(4);
		//将元素3修改为33
		array.modify(3, 33);
		array.display();
	}




	//定义一个数组
   private int [] intArray;
   //定义数组的实际有效长度
   private int elems;
   //定义数组的最大长度
   private int length;

    //默认构造一个长度为50的数组
    public MyArray(){
        elems = 0;
        length = 50;
        intArray = new int[length];
    }
    //构造函数，初始化一个长度为length 的数组
    public MyArray(int length){
        elems = 0;
        this.length = length;
        intArray = new int[length];
    }

    //获取数组的有效长度
    public int getSize(){
        return elems;
    }

    /**
     * 遍历显示元素
     */
    public void display(){
        for(int i = 0 ; i < elems ; i++){
            System.out.print(intArray[i]+" ");
        }
        System.out.println();
    }

    /**
     * 添加元素
     * @param value,假设操作人是不会添加重复元素的，如果有重复元素对于后面的操作都会有影响。
     * @return添加成功返回true,添加的元素超过范围了返回false
     */
    public boolean add(int value){
        if(elems == length){
            return false;
        }else{
            intArray[elems] = value;
            elems++;
        }
        return true;
    }

    /**
     * 根据下标获取元素
     * @param i
     * @return查找下标值在数组下标有效范围内，返回下标所表示的元素
     * 查找下标超出数组下标有效值，提示访问下标越界
     */
    public int get(int i){
        if(i<0 || i>elems){
            System.out.println("访问下标越界");
        }
        return intArray[i];
    }
    /**
     * 查找元素
     * @param searchValue
     * @return查找的元素如果存在则返回下标值，如果不存在，返回 -1
     */
    public int find(int searchValue){
        int i ;
        for(i = 0 ; i < elems ;i++){
            if(intArray[i] == searchValue){
                break;
            }
        }
        if(i == elems){
            return -1;
        }
        return i;
    }
    /**
     * 删除元素
     * @param value
     * @return如果要删除的值不存在，直接返回 false;否则返回true，删除成功
     */
    public boolean delete(int value){
        int k = find(value);
        if(k == -1){
            return false;
        }else{
            if(k == elems-1){
                elems--;
            }else{
                for(int i = k; i< elems-1 ; i++){
                    intArray[i] = intArray[i+1];

                }
                  elems--;
             }
             return true;
         }
     }
     /**
      * 修改数据
      * @param oldValue 原值
      * @param newValue 新值
      * @return修改成功返回true，修改失败返回false
      */
     public boolean modify(int oldValue,int newValue){
         int i = find(oldValue);
         if(i == -1){
             System.out.println("需要修改的数据不存在");
             return false;
         }else{
             intArray[i] = newValue;
             return true;
         }
     }
}
