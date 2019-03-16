package com.colson.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {
	public void dealListWithMutiThread(){
		List<Object> list = new ArrayList<Object>(10000);
		int index = 0;
		ExecutorService ex = Executors.newFixedThreadPool(5);
		int dealSize = 2000;
		List<Future<List<Object>>> futures = new ArrayList<Future<List<Object>>>(5);
		//分配
		for(int i=0;i<= 5;i++,index+=dealSize){
			int start = index;
			if(start>=list.size()) break;
			int end = start + dealSize;
			end = end>list.size() ? list.size() : end;
			futures.add(ex.submit(new Task(list,start,end)));
		}
		try {
			//处理
			List<Object>  result = new ArrayList<Object>();
			for(Future<List<Object>> future : futures){
				//合并操作
				result.addAll(future.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
