package mridul;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
//run on hackerrank editor
public class Prims_MST {

    static int key[];
    static int min_dist[];
    static boolean visited[];
    static int nu=0,res=0;
    //finding min key
    static int minKey(int key[], boolean visited[]){
        int min=Integer.MAX_VALUE, minI=0;
        for(int i=1;i<nu+1;i++){
            if(visited[i]==false && key[i]<min){
                min=key[i];
                minI=i;
            }
        }
        return minI;
    }
    // Complete the prims function below.
    static int prims(int n, int[][] edges, int start) {
        nu=n;
        key=new int[n+1];
        min_dist=new int[n+1];
        visited=new boolean[n+1];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[start]=0;
        //starting node or root node
        min_dist[start]=-1;
        int prim_arr[][]=new int[n+1][n+1];
        for (int[] row : prim_arr) 
            Arrays.fill(row, -1); 
        for(int i=0;i<edges.length;i++){
            prim_arr[edges[i][0]][edges[i][1]]=edges[i][2];
            prim_arr[edges[i][1]][edges[i][0]]=edges[i][2];
        }
        for(int c=0;c<n;c++){
            int u = minKey(key, visited);
            visited[u]=true;

            for(int i=1;i<n+1;i++){
                if(prim_arr[u][i]!=-1 && visited[i]==false && prim_arr[u][i]<key[i]){
                    key[i]=prim_arr[u][i];
                    min_dist[i]=u;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            if(i==start){
                continue;
            }
            res=res+prim_arr[i][min_dist[i]];
        }
        // for(int i=0;i<n+1;i++){
        //     for(int j=0;j<n+1;j++){
        //         System.out.print(prim_arr[i][j]+" ");
        //     }
        //     System.out.println();
        // }

        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

