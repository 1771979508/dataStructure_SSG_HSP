package com.recursion;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月23日 下午8:27:39	
*/

/*
 * 
 * 		迷宫问题		
 * 
 * 
 * */

public class Maze {

	public static void main(String[] args) {
		
		// 先创建一个二维数组，模拟迷宫
		// 地图
		int[][] map = new int[8][7];
		
		// 使用1表示墙
		// 上下全部置为1
		for(int i=0;i<7;i++){
			map[0][i] = 1;  //  固定第一行，从第一列到第七列都置1
			map[7][i] = 1;  //  固定第八行，从第一列到第七列都置为1
		}
		
		// 左右全部置为1
		for(int i=0;i<8;i++){
			map[i][0] = 1;  //  固定第一列，从第一行到第八行都置为1
			map[i][6] = 1; //  固定第七列，从第一行到第八行都置为1
		}
		
		// 设置地图的挡板，用1表示
		map[3][1] = 1;
		map[3][2] = 1;
		
		/*测试回溯的情况开始*/
			//  设置挡板 将路堵死 - 以便观察回溯现象
//			map[1][2] = 1;
//			map[2][2] = 1;
		/*测试回溯的情况结束*/
		
		// 输出地图
		System.out.println("地图的初始情况：");
		for(int i=0;i<8;i++){
			for(int j=0;j<7;j++){
				System.out.print(map[i][j] + " ");
			}
			// 输出完一行就换一行
			System.out.println();
		}
		
		
		/*一、 测试更改路线->下、右、上、左 */
		/*
			//  使用递归回溯给小球找路
			setWay(map, 1, 1);
			
			System.out.println();
			
			// 输出新的地图，小球走过，并标识过的递归
			System.out.println("小球走过，并标识过的 地图的情况");
			for(int i=0;i<8;i++){
				for(int j=0;j<7;j++){
					System.out.print(map[i][j] + " ");
				}
				// 输出完一行就换一行
				System.out.println();
			}
		*/
		
		
		/*二、更改路线->上、右、下、左*/
	//  使用递归回溯给小球找路
		setWayChanged(map, 1, 1);
		
		System.out.println();
		
		// 输出新的地图，小球走过，并标识过的递归
		System.out.println("小球走过，并标识过的 地图的情况");
		for(int i=0;i<8;i++){
			for(int j=0;j<7;j++){
				System.out.print(map[i][j] + " ");
			}
			// 输出完一行就换一行
			System.out.println();
		}
		
		
		
	}
	
	// 使用递归回溯来给小球找路
	// 说明
		//1.map表示地图
		//2.i，j表示从地图的哪个位置开始出发(1,1)
		//3.如果小球能找到map[6][5]位置，则说明通路找到
		//4.约定：当map[i][j]为0表示该点没有走过 当为1时表示墙；2表示通路可以走；3.表示该点已经走过，但是走不通
		//5.在走迷宫时，需要确定一个策略(方法) 下->右->上->左，该点走不通，再回溯
	
	/*一、更改路线->下、右、上、左*/
	public static boolean setWay(int[][] map,int i,int j){
		if(map[6][5] == 2){  // 通路已经找到ok
			return true;
		}else{
			if(map[i][j] == 0){  // 如果当前这个点还没有走过
				//按照策略 下->右->上->左
				map[i][j] = 2; // 假定该点是可以走通
				if(setWay(map, i+1, j)){ // 向下走
					return true;
				}else if(setWay(map, i, j+1)){  // 向右走
					return true;
				}else if(setWay(map, i-1, j)){  // 向上
					return true;
				}else if(setWay(map, i, j-1)){  // 向左走
					return true;
				}else {
					// 说明该点是走不通，是死路 -> 给它初始赋的值进行更改
					map[i][j] = 3;
					return false;
				}
			}else{  // 如果map[i][j] != 0 , 可能是1，2，3
				return false;
			}
		}
	}
	
	/*二、更改路线->上、右、下、左*/
	public static boolean setWayChanged(int[][] map,int i,int j){
		if(map[6][5] == 2){  // 通路已经找到ok
			return true;
		}else{
			if(map[i][j] == 0){  // 如果当前这个点还没有走过
				//按照策略 下->右->上->左
				map[i][j] = 2; // 假定该点是可以走通
				if(setWayChanged(map, i-1, j)){ // 向上走
					return true;
				}else if(setWayChanged(map, i, j+1)){  // 向右走
					return true;
				}else if(setWayChanged(map, i+1, j)){  // 向下
					return true;
				}else if(setWayChanged(map, i, j-1)){  // 向左走
					return true;
				}else {
					// 说明该点是走不通，是死路 -> 给它初始赋的值进行更改
					map[i][j] = 3;
					return false;
				}
			}else{  // 如果map[i][j] != 0 , 可能是1，2，3
				return false;
			}
		}
	}
	
	
	
	
	
}
	