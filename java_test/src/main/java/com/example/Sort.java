package com.example;

/**
 * Created by DELL on 2016/10/10.
 */

public class Sort {
    public static void main(String[] args) {

        int[] arr = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 0 };

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        System.out.println("the max is " + arr[arr.length - 1]);
    }

}
