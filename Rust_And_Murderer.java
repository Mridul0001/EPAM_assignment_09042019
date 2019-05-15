package mridul;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Rust_And_Murderer {
    //The default main function of hackerrank has to be changed in this problem for simplicity
    static int[] rustMurderer(int rootNode, HashMap<Integer,HashSet<Integer>> map, int cities) {
        
         int ar[]=new int[cities];
         HashSet<Integer> visited=new HashSet<>();
         HashSet<Integer> notVisited=new HashSet<>();
         Queue<Integer> q=new LinkedList<>();
         //adding all non visited cities

         for(int i=1;i<=cities;i++){
             notVisited.add(i);
         }

         q.offer(rootNode);
         notVisited.remove(rootNode);
         ar[0]=0;
         while(!q.isEmpty()){
             int active=q.poll();
             HashSet<Integer> adjacentNodes=map.get(active);

             for(int an : notVisited){
                 if(adjacentNodes==null || !adjacentNodes.contains(an)){
                     q.offer(an);
                     ar[an-1]=ar[active-1]+1;
                     visited.add(an);
                 }
             }
             notVisited.removeAll(visited);
             visited=new HashSet<>();
         }
         return ar;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int testCases;
       //number of test cases
       testCases=scanner.nextInt();
       while(testCases!=0){
           int roads,cities,origin,destination,rootNode;
           HashMap<Integer,HashSet<Integer>> map=new HashMap<>();
           //number of cities and roads
           cities=scanner.nextInt();
           roads=scanner.nextInt();
           //scanning origin and destination city
           for(int i=0;i<roads;i++){
               origin=scanner.nextInt();
               destination=scanner.nextInt();

               //implementing the graph using HashMap 
               if(map.containsKey(origin)){
                   map.get(origin).add(destination);
               }else{
                   HashSet<Integer> set=new HashSet<>();
                   set.add(destination);
                   map.put(origin,set);
               }
               if(map.containsKey(destination)){
                   map.get(destination).add(origin);
               }else{
                   HashSet<Integer> set=new HashSet<>();
                   set.add(origin);
                   map.put(destination,set);
               }
           }
           
           //root node or current position of rust
           rootNode=scanner.nextInt();

           int ar[]=rustMurderer(rootNode, map, cities);
           for(int i=0;i<ar.length;i++){
                if(i == rootNode-1){
                    continue;
                }
                System.out.print(ar[i]+" ");
           }
           System.out.println();
           testCases--;
       }
    }
}

