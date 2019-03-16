package com.colson.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Task implements Callable<List<Object>> {

		private List<Object> list;
		private int start;
		private int end;

		public Task(List<Object> list,int start,int end){
			this.list = list;
			this.start = start;
			this.end = end;
		}

		public List<Object> call() throws Exception {
			Object obj = null;
			List<Object> retList = new ArrayList<Object>();
			for(int i=start;i<end;i++){
				obj = list.get(i);
				//你的处理逻辑
			}
			//返回处理结果
			return retList;
		}
}
