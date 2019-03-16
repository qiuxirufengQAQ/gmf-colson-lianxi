package com.colson.dal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Cube {
	long operationTimes=0;
	Calendar c = Calendar.getInstance();
	long t1 = System.currentTimeMillis();
	long t2,tmpTime;
	int intervalTime = 10000;//log展示间隔时间
	String startTime = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss z").format(new Date());
	Random rdm = new Random();
	String tmp1[] = {"tmp","tmp","tmp"};
	String tmp2[] = {"t","t","t"};
	String w[][] = {{"white","white","white"},{"white","white","white"},{"white","white","white"},{"white","white","white"}};
	String y[][] = {{"yellow","yellow","yellow"},{"yellow","yellow","yellow"},{"yellow","yellow","yellow"},{"yellow","yellow","yellow"}};
	String b[][] = {{"blue","blue","blue"},{"blue","blue","blue"},{"blue","blue","blue"},{"blue","blue","blue"}};
	String g[][] = {{"green","green","green"},{"green","green","green"},{"green","green","green"},{"green","green","green"}};
	String r[][] = {{"red","red","red"},{"red","red","red"},{"red","red","red"},{"red","red","red"}};
	String o[][] = {{"orange","orange","orange"},{"orange","orange","orange"},{"orange","orange","orange"},{"orange","orange","orange"}};
	String cube[][][] = {w,y,b,g,r,o};
	public boolean isInterval(){
		t2 = System.currentTimeMillis();
		if(t2-tmpTime >intervalTime){
			tmpTime =t2;
			return true;
		}else return false;
	}
	public void  showCostTime(){
		t2  = System.currentTimeMillis();
		c.setTimeInMillis(t2-t1);
		//          String endTime = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss z").format(c.getTime());
		String endTime = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss z").format(new Date());
		System.out.println("从"+ startTime  + "开始，到"+ endTime + "结束，共消耗"+ c.get((c.HOUR_OF_DAY)-9)   + "小时"+c.get(c.MINUTE)   +"分"+ c.get(c.SECOND)  +"秒");
	}

	public void log(long i,String action, int k){
		if(isInterval()){
			System.out.println("第"+i+"次随机操作，动作是"+action+"，执行"+(k+1)+"次");
		}
	}
	public void showCube(){
		for(int i=0;i<cube.length;i++){
			for(int j=0;j<cube[i].length;j++){
				for(int k=0;k<cube[i][j].length;k++){
					System.out.print(cube[i][j][k]);
					System.out.print(",");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	public void wF(int n){
		for(int j=0;j<=n;j++){
			tmp1 = w[0];
			tmp2 = r[0];
			for(int i=1;i<w.length;i++){
				w[i-1] = w[i];
			}
			w[w.length-1] = tmp1;
			r[0] = b[1];
			b[1] = o[2];
			o[2] = g[3];
			g[3] = tmp2;
		}
	}
	public void wB(int n){
		if(n==2){
			n = 0;
		}else if(n == 1){
			n =1;
		}else if(n == 0){
			n =2;
		}

		for(int j=0;j<n;j++){
			tmp1 = w[0];
			tmp2 = r[0];
			for(int i=1;i<w.length;i++){
				w[i-1] = w[i];
			}
			w[w.length-1] = tmp1;
			r[0] = b[1];
			b[1] = o[2];
			o[2] = g[3];
			g[3] = tmp2;
		}
	}
	//其他几个颜色转动的方法还没有补充。有兴趣可以自己补充。
	public void yF(int n){
	}
	public void yB(int n){
	}
	public void bF(int n){
		tmp1 = b[1];
		tmp2 = w[1];
		b[1] = b[2];
		b[2] = b[3];
		b[3] = b[0];
		b[0] = tmp1;

		w[1] = r[3];
		r[3] = y[1];
		y[1] = o[3];
		o[3] = tmp2;
	}

	public void bB(int n){
		if(n==0){
			n = 2;
		}else if(n == 1){
			n = 1;
		}else if(n == 2){
			n = 0;
		}

		for(int j=0;j<=n;j++){
			tmp1 = b[1];
			tmp2 = w[1];
			b[1] = b[2];
			b[2] = b[3];
			b[3] = b[0];
			b[0] = tmp1;

			w[1] = r[3];
			r[3] = y[1];
			y[1] = o[3];
			o[3] = tmp2;

		}
	}

	public void gF(int n){

	}

	public void gB(int n){

	}
	public void rF(int n){
		for(int j=0;j<=n;j++){
			tmp1 = r[0];
			tmp2 = w[0];
			r[0] = r[3];
			r[3] = r[2];
			r[2] = r[1];
			r[1] = r[0];
			r[0] = tmp1;
		}
		w[0] = g[2];
		g[2] = y[0];
		y[0] = b[2];
		b[2] = w[0];
		w[0] = tmp2;
	}
	public void rB(int n){
		if(n==0){
			n = 2;
		}else if(n == 1){
			n = 1;
		}else if(n == 2){
			n = 0;
		}
		for(int j=0;j<=n;j++){
			tmp1 = r[0];
			tmp2 = w[0];
			r[0] = r[3];
			r[3] = r[2];
			r[2] = r[1];
			r[1] = r[0];
			r[0] = tmp1;
		}
		w[0] = g[2];
		g[2] = y[0];
		y[0] = b[2];
		b[2] = w[0];
		w[0] = tmp2;
	}

	public void oF(int n){

	}

	public void oB(int n){

	}

	public boolean isSixFaceRecover(){
		for(int i=0;i<cube.length;i++){
			for(int j=0;j<cube[i].length;j++){
				for(int k=0;k<cube[i][j].length;k++){

					try{
						if(cube[i][j][k]!=cube[i][0][0]){
							//                              if(isInterval()){
							//                                  System.out.println(cube[i][j][k]+"->"+cube[i][0][0]);
							//                                  System.out.println("cube[i][j][k]:"+i+","+j+","+k+"与第一个颜色不一致");
							//                              }
							return false;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						//                          System.out.println("cube[i][j][k]:"+i+","+j+","+k);
						e.printStackTrace();

					}
				}
			}
		}
		return true;
	}
	public void rangePlay(){
		while(!isSixFaceRecover()){
			int rMethod = rdm.nextInt(12);
			int rTurn = rdm.nextInt(3);
			if(rMethod==0){
				this.wF(rTurn);
				operationTimes++;
				log(operationTimes,"wF",rTurn);
			}else if(rMethod==1){
				this.wB(rTurn);
				operationTimes++;
				log(operationTimes,"wB",rTurn);
			}else if(rMethod==2){
				this.yF(rTurn);
				operationTimes++;
				log(operationTimes,"yF",rTurn);
			}else if(rMethod==3){
				this.yB(rTurn);
				operationTimes++;
				log(operationTimes,"yB",rTurn);
			}else if(rMethod==4){
				this.bF(rTurn);
				operationTimes++;
				log(operationTimes,"bF",rTurn);
			}else if(rMethod==5){
				this.bB(rTurn);
				operationTimes++;
				log(operationTimes,"bB",rTurn);
			}else if(rMethod==6){
				this.gF(rTurn);
				operationTimes++;
				log(operationTimes,"gF",rTurn);
			}else if(rMethod==7){
				this.gB(rTurn);
				operationTimes++;
				log(operationTimes,"gB",rTurn);
			}
			else if(rMethod==8){
				this.rF(rTurn);
				operationTimes++;
				log(operationTimes,"rF",rTurn);
			}else if(rMethod==9){
				this.rB(rTurn);
				operationTimes++;
				log(operationTimes,"rB",rTurn);
			}else if(rMethod==10){
				this.oF(rTurn);
				operationTimes++;
				log(operationTimes,"oF",rTurn);
			}else if(rMethod==11){
				this.oB(rTurn);
				operationTimes++;
				log(operationTimes,"oB",rTurn);
			}
		}
		showCube();
		System.out.println("cube经过"+operationTimes+"次随机操作后复原");
	}
	public void rangePlay(int i){
		for(int m=0;m<i;m++){
			int rMethod = rdm.nextInt(12);
			int rTurn = rdm.nextInt(3);
			if(rMethod==0){
				this.wF(rTurn);
				operationTimes++;
				log(operationTimes,"wF",rTurn);
			}else if(rMethod==1){
				this.wB(rTurn);
				operationTimes++;
				log(operationTimes,"wB",rTurn);
			}else if(rMethod==2){
				this.yF(rTurn);
				operationTimes++;
				log(operationTimes,"yF",rTurn);
			}else if(rMethod==3){
				this.yB(rTurn);
				operationTimes++;
				log(operationTimes,"yB",rTurn);
			}else if(rMethod==4){
				this.bF(rTurn);
				operationTimes++;
				log(operationTimes,"bF",rTurn);
			}else if(rMethod==5){
				this.bB(rTurn);
				operationTimes++;
				log(operationTimes,"bB",rTurn);
			}else if(rMethod==6){
				this.gF(rTurn);
				operationTimes++;
				log(operationTimes,"gF",rTurn);
			}else if(rMethod==7){
				this.gB(rTurn);
				operationTimes++;
				log(operationTimes,"gB",rTurn);
			}
			else if(rMethod==8){
				this.rF(rTurn);
				operationTimes++;
				log(operationTimes,"rF",rTurn);
			}else if(rMethod==9){
				this.rB(rTurn);
				operationTimes++;
				log(operationTimes,"rB",rTurn);
			}else if(rMethod==10){
				this.oF(rTurn);
				operationTimes++;
				log(operationTimes,"oF",rTurn);
			}else if(rMethod==11){
				this.oB(rTurn);
				operationTimes++;
				log(operationTimes,"oB",rTurn);
			}
		}
		System.out.println("cube已经被操作"+i+"次");
	}
	public static void main(String[] args){
		Cube c = new Cube();
		c.rangePlay(10);
		c.showCube();
		c.rangePlay();
		c.showCostTime();

	}
}