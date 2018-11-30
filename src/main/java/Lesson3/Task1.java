package Lesson3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Task1 {
    /**
     * Java. Level 3. Lesson 2. Homework. Task1.
     *
     * @author Egor Patrashkin
     * @version dated Nov 30, 2018
     */


    /** Задание 1
     *  Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
     */

    public static void main(String[] args) {
        byte[] bw = new byte[50];
        byte[] br = new byte[50];
        for (byte i = 0; i < 50 ; i++) {
            bw[i]=i;
        }

        FileOutputStream out = null;
        FileInputStream in = null;
        try {
            out = new FileOutputStream("task1.txt");
            out.write(bw);
            out.close();
            System.out.println("Записано " + bw.length + " байт");
            for (byte b:bw) {
                System.out.print( b +" ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in = new FileInputStream("task1.txt");
            int count = in.read(br);
            System.out.println("Прочитано " + count + " байт");
            for (byte b:br) {
                System.out.print( b +" ");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
