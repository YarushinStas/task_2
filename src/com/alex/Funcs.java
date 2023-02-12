package com.alex;

import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.Math.sqrt;


public class Funcs {
    private static Funcs justPlaceForCreatingOfSingleton;

    private Funcs() {
    }

    public static Funcs LogClassInitialization() {

        if (justPlaceForCreatingOfSingleton == null) {
            justPlaceForCreatingOfSingleton = new Funcs();

            try (FileOutputStream fileOutputStream = new FileOutputStream("./output.txt", false)) {
                fileOutputStream.write(("").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return justPlaceForCreatingOfSingleton;
    }

    public void WriteLogs(String log) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("./output.txt", true)) {
            fileOutputStream.write(("" + log ).getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Givens (int N, double [][] A, double [] b) {
        double[][] Q = new double[N][N]; // первоначально это единичная
        double[][] R = new double[N][N]; // первоначально это А
        double[] aa = new double[N]; double[] bb = new double[N]; // это вместо текущей ортогональной
        double c_i; double s_i;


                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        R[i][j] = A[i][j];
                        if (i == j) Q[i][i] = 1;
                    }
                }                                       //инициализация

                for (int i = 0; i < N-1; i++){

                        for (int j = i; j < N-1; j++){
                            c_i =  R[i][i]  / (sqrt( R[i][i]*R[i][i] + R[j+1][i]*R[j+1][i] ));
                            s_i = R[j+1][i] / (sqrt( R[i][i]*R[i][i] + R[j+1][i]*R[j+1][i] ));
                                                                                //считаю n-1 раз коефы c и s

                            for (int p = 0; p < N; p++) {aa[p] = 0; bb[p] = 0;}
                            aa[i] = c_i;  aa[j+1] = s_i;
                            bb[i] = -s_i; bb[j+1] = c_i;
                                  //каждый посчитанный раз составляю матрицу и по позициям i j вставляю с и s


                            double numm1[] = new double[N]; double numm2[] = new double[N];
                            for (int k = 0; k < N; k++) {
                                double num1 = 0;
                                double num2 = 0;
                                for (int p = 0; p < N; p++) {
                                    num1 = num1 + R[p][k] * aa[p];
                                    num2 = num2 + R[p][k] * bb[p];
                                }
                                numm1[k] = num1;
                                numm2[k] = num2;
                            }
                            for (int p = 0; p < N; p++) {
                                R[i][p] = numm1[p];
                                R[j+1][p] = numm2[p];
                            }
                                          //умножаю свою А на полученную матрицу, получаю чучуть повернутую (R)

                            for (int k = 0; k < N; k++) {
                                double num1 = 0;
                                double num2 = 0;
                                for (int p = 0; p < N; p++) {
                                    num1 = num1 + Q[p][k] * aa[p];
                                    num2 = num2 + Q[p][k] * bb[p];
                                }
                                numm1[k] = num1;
                                numm2[k] = num2;
                            }
                            for (int p = 0; p < N; p++) {
                                Q[i][p] = numm1[p];
                                Q[j+1][p] = numm2[p];
                            }
                                                                                            //теперь Q умножаем

                                double num1 = 0;
                                double num2 = 0;
                                for (int p = 0; p < N; p++) {
                                    num1 = num1 + b[p] * aa[p];
                                    num2 = num2 + b[p] * bb[p];
                                }
                                b[i] = num1;
                                b[j+1] = num2;
                                                                                           //а теперь вектор b
                        }
                }

        WriteLogs("Матрица Q: \n");
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                String value = String.format("%4f", Q[j][i]);
                WriteLogs( value + "   ");
            }
            WriteLogs("\n");
        }

        WriteLogs("\nМатрица R: \n");
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                String value = String.format("%4f", R[i][j]);
                WriteLogs( value + "   ");
            }
                WriteLogs("\n");
        }

        WriteLogs("\nВектор b: \n");
        for (int i = 0; i < N; i++){
            String value = String.format("%4f", b[i]);
            WriteLogs(value + "\n");
        }


    }
}