package java_hw.algorithmLi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class algLi {
    public static int startRaw;
    public static int startCol;
    public static int exitRaw;
    public static int exitCol;
    public static int wayLength;


    public static void main(String[] args) {
        int [][] field = createField();
        setStartPoint(field);
        setExit(field);
        setWalls(field);
        System.out.println("Initial maze");
        printArray(field);
        System.out.println("____________________");
        System.out.println("Work of algorithm:");
        algorithmLi(field);
        wayLength = field[exitRaw][exitCol];
        if (wayLength == -2) {
            System.out.println("There is no way");
        } else {
            System.out.println("Shortest way length is: " + wayLength);
            int [][] way = shortestWay(field);
            printArray(way);
        }

    }

    static int [][] createField(){
        Random random = new Random();
        Boolean flag = true;
        int raws = random.nextInt(20);;
        int cols = random.nextInt(20);;
        while (flag){
            if (raws == 0 | cols == 0){
                raws = random.nextInt(20);
                cols = random.nextInt(20);
            } else {
                flag = false;
            }
        }
        int [][] field = new int [raws][cols];
        return field;
    }

    static void printArray(int[][] arr){
        for (int i =0; i < arr.length; i ++){
            for (int j = 0; j<arr[0].length; j++){
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.print("\n");
        }
    }

    static int [][] setStartPoint (int[][]arr){
        Random random = new Random();
        int raw = random.nextInt(arr.length);
        int col = random.nextInt(arr[0].length);
        arr[raw][col] = 1;
        startRaw = raw;
        startCol = col;
        return arr;
    }

    static int[][] setExit(int[][]arr){     
        Random random = new Random();
        int i = 0;
        while (i < 1){
            int raw = random.nextInt(arr.length);
            int col = random.nextInt(arr[0].length);
            if (arr[raw][col] == 1){
                continue;
            }
            arr[raw][col] = -2;
            exitRaw = raw;
            exitCol = col;
            i ++;
        }
        
        return arr;
    }
    
    static int[][] setWalls(int[][]arr){
        Random random = new Random();
        int wallsCount = random.nextInt((arr.length*arr[0].length)/4);
        int i = 0;
        while (i < wallsCount){
            int raw = random.nextInt(arr.length);
            int col = random.nextInt(arr[0].length);
            if (arr[raw][col] == 1 || arr[raw][col] == -2 || arr[raw][col] == -1){
                continue;
            }
            arr[raw][col] = -1;
            i ++;
        }
        return arr;
    }


    static int[][] algorithmLi (int[][]arr){
        Boolean exit = true;
        int posRaw = startRaw;
        int posCol = startCol;
        Queue<Integer> queueRaw = new LinkedList<Integer>();
        Queue<Integer>queueCol = new LinkedList<Integer>();
        while (exit){
            if (posRaw - 1  >= 0 && (arr[posRaw-1][posCol] == 0 | arr[posRaw-1][posCol] == -2) ){
                arr[posRaw-1][posCol] = arr[posRaw][posCol] + 1;
                queueRaw.add(posRaw-1);
                queueCol.add(posCol);
            }
            if (posCol + 1 <= (arr[0].length-1) && arr[posRaw][posCol+1] == 0 |  arr[posRaw][posCol+1] == -2){
                arr[posRaw][posCol+1] = arr[posRaw][posCol] + 1;
                queueRaw.add(posRaw);
                queueCol.add(posCol+1);
            }

            if (posRaw + 1 <= arr.length-1 && arr[posRaw+1][posCol] == 0 | arr[posRaw+1][posCol] == -2){
                arr[posRaw+1][posCol] = arr[posRaw][posCol] + 1;
                queueRaw.add(posRaw+1);
                queueCol.add(posCol);
            }

            if (posCol -1 >= 0 && arr[posRaw][posCol-1] == 0 | arr[posRaw][posCol-1] == -2){
                arr[posRaw][posCol-1] = arr[posRaw][posCol] + 1;
                queueRaw.add(posRaw);
                queueCol.add(posCol-1);
            }

            if (queueCol.isEmpty() & queueRaw.isEmpty()){
                System.out.println("dead end");
                exit = false;
                continue;
            }

            printArray(arr);
            if(arr[exitRaw][exitCol] != -2){
                System.out.println("We found exit");
                exit = false;
                continue;
            }


            System.out.println("next iter");
            posRaw = queueRaw.poll();
            posCol = queueCol.poll();

        }

        return arr;

    }



    static int[][] shortestWay (int[][]arr){
        int [][] way = new int [arr.length][arr[0].length];
        way [exitRaw][exitCol] = arr[exitRaw][exitCol];
        while (way[startRaw][startCol] !=1 ){
            if (exitRaw - 1  >= 0 && (arr[exitRaw-1][exitCol] < arr[exitRaw][exitCol] & arr[exitRaw-1][exitCol] != -1 & arr[exitRaw-1][exitCol] != 0)){
                way[exitRaw-1][exitCol] = arr[exitRaw-1][exitCol];
                exitRaw = exitRaw-1;
                exitCol = exitCol;
            } else if(exitCol + 1 <= (arr[0].length-1) && (arr[exitRaw][exitCol+1] < arr[exitRaw][exitCol] & arr[exitRaw][exitCol+1] != -1 & arr[exitRaw][exitCol+1] != 0)){
                way[exitRaw][exitCol+1] = arr[exitRaw][exitCol+1];
                exitRaw = exitRaw;
                exitCol = exitCol+1;
            } else if (exitRaw + 1 <= arr.length-1 && (arr[exitRaw+1][exitCol] < arr[exitRaw][exitCol] & arr[exitRaw+1][exitCol] != -1  & arr[exitRaw+1][exitCol] != 0)){
                way[exitRaw+1][exitCol] = arr[exitRaw+1][exitCol];
                exitRaw = exitRaw+1;
                exitCol = exitCol;
            } else if (exitCol -1 >= 0 && (arr[exitRaw][exitCol-1] < arr[exitRaw][exitCol] & arr[exitRaw][exitCol-1] != -1 & arr[exitRaw][exitCol-1] != 0)){
                way[exitRaw][exitCol-1] = arr[exitRaw][exitCol-1];
                exitRaw = exitRaw;
                exitCol = exitCol-1;
            }
        }

        return way;
    }

}


