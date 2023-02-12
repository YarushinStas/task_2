package com.alex;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream ( "./input.txt");
        Scanner scanner = new Scanner(fileInputStream);

//----------------------------------------------------------------------------------

        int N = scanner.nextInt();
        int [][] A = new int [N][2];

//----------------------------------------------------------------------------------
        for (int i = 0; i < N; i++){

            String a = scanner.next();
            String b = scanner.next();

            A[i][0] = Integer.parseInt(a);

            if (b.equals("-")){A[i][1] = 0;}
            if (b.equals("0+")) {A[i][1] = 1;}

        }
//----------------------------------------------------------------------------------

        int temp = 0;
        int zero = 0;
        boolean enter = false;

        for (int i = 0; i < N-1; i++){
            temp = temp + A[i][0];

            if ( (A[i][1] != A[i+1][1]) && (Math.abs(A[i+1][0]) == 1 ) ){
                enter = true;

                if (A[i][1] == 1){
                    zero = temp;
                } else {zero = temp + A[i+1][0];}

            }

        }
        temp = temp + A[N-1][0];


        if (!enter){
            System.out.println("inf");
        } else {
            int gr = temp - zero;
            System.out.println(gr);
        }
    }
}
