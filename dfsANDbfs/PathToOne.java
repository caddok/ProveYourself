package dfsANDbfs;

import java.util.ArrayList;
import java.util.Scanner;

public class PathToOne {
    public static int current = 0;
    public static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        solve(n);
        System.out.println(list);
    }public static void solve(int number){
        current++;
        if(number==1){
            list.add(current);
            return;
        }
        if(number<=0){
            return;
        }
        else{
            if(number%2==0){
                solve(number/2);
            }
            solve(number-1);
            solve(number+1);

        }
    }
}
