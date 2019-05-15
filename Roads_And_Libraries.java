package mridul;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Roads_And_Libraries {
    static ArrayList<Integer> nodes[];
        //declaring necessary variables
        static int nodeCount=0,componentCount=0,lib,road,nu;
        static long roadCost,libCost;
     //applying depth first search to check for visited nodes
       static void dfs(int v, boolean visited[]){
            visited[v]=true;
            //System.out.println(v+" ");
            nodeCount++;
            //System.out.println(nodeCount);
            Iterator<Integer> i = nodes[v].iterator();
            while(i.hasNext()){
                int node=i.next();
                if(!visited[node]){
                    dfs(node,visited);
                }
            }
        }

        //finding components in graph(i.e. group of cities which can not be connected to other group of cities)
       static void components(){
             boolean visited[] = new boolean[nu+1];
             for(int i=1;i<=nu;i++){
                 if(!visited[i]){
                     componentCount++;
                     dfs(i,visited);
                     //System.out.println(nodeCount);
                     //calculating cost for road and libraries in each component
                     roadCost=roadCost+lib+((nodeCount-1)*road);
                     //System.out.println(roadCost);
                     libCost=libCost+(nodeCount*lib);
                     //System.out.println(nodeCount);
                     //System.out.println("");
                     nodeCount=0;
                 }
             }
        }

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        lib=c_lib;
        road=c_road;
        nu=n;
        roadCost=0;
        libCost=0;
        nodes=new ArrayList[n+1];
        for(int i=1;i<=n;++i){
            nodes[i]=new ArrayList();
        }
        //adding nodes
        for(int i=0;i<cities.length;i++){
            nodes[cities[i][0]].add(cities[i][1]);
            nodes[cities[i][1]].add(cities[i][0]);
        }
        components();
        if(roadCost<libCost){
            return roadCost;
        }
        else{
            return libCost;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

