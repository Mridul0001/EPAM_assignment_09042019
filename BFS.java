package mridul;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
//run on hackerrank editor
public class BFS {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        int start=s;
        //System.out.println(s);
        int arr[]=new int[n+1];
        int res_arr[]=new int[n-1];
        int k,c=0;
        LinkedList<Integer> bfs_q=new LinkedList<Integer>();
        boolean visited[]=new boolean[n+1];
        Arrays.fill(arr, 0);
        ArrayList<Integer> al[]=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            al[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<edges.length;i++){
            al[edges[i][0]].add(edges[i][1]);
            al[edges[i][1]].add(edges[i][0]);
        }
        visited[s]=true;
        bfs_q.add(s);
        while(bfs_q.size()!=0){
            s=bfs_q.poll();
            //System.out.println(s+" ");
            Iterator<Integer> i=al[s].iterator();
            while(i.hasNext()){
                k=i.next();
                if(!visited[k]){
                    visited[k]=true;
                    bfs_q.add(k);
                    arr[k]=arr[s]+6;
                }
            }
        }
        for(int i=0;i<n+1;i++){
            //System.out.print(arr[i]+" ");
            if(i==start || i==0){
                //System.out.println(s);
                continue;
            }
            //System.out.println(i);
            //System.out.print(arr[i]+" ");
            if(arr[i]!=0){
            res_arr[c]=arr[i];
            }
            else{
                res_arr[c] = -1;
            }
            c++;
        }
        System.out.println();
        //System.out.println(al[1]);
        return res_arr;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
