package Lesson3;

/**
 * Java. Level 3. Lesson 2. Homework. Task2.
 *
 * @author Egor Patrashkin
 * @version dated Nov 30, 2018
 */


/** Задание 2
 *  Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;


public class Task2 {
public static void main(String[] args) {

        SequenceInputStream seq = null;
        for (byte i = 1; i < 6; i++) {
            createFile("Task2_" + i+".txt", i);
        }


        FileOutputStream out = null;
        try {
            ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
            for (byte i = 1; i < 6; i++) {
                al.add(new FileInputStream("Task2_" + i+".txt"));
            }
            Enumeration<FileInputStream>  en = Collections.enumeration(al);

            seq = new SequenceInputStream(en);
            out = new FileOutputStream("Task2_Out.txt");
            int rb = seq.read();
            int count = 0;
            System.out.println("В файл " + "Task2_Out.txt " + "записано:");
            while(rb != -1){
                out.write(rb);
                count++;
                System.out.print((count) % 50 != 0 ? (rb + " ") : (rb + " \n"));
                rb = seq.read();
            }
            System.out.println("Всего " + count + " байт.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { seq.close(); } catch ( IOException e ) { };
            try { out.close(); } catch ( IOException e ) { };
        }
    }

    private static void createFile(String fileName, byte b) {
        byte[] bw = new  byte[100];
        for (byte i = 0; i < 100 ; i++) {
            bw[i]=b;
        }
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(fileName);
            out.write(bw);
            out.close();

            System.out.println("В файл " + fileName + " записано " + bw.length + " байт:");
            for (int i = 0; i <bw.length; i++) {
                System.out.print((i + 1) % (bw.length / 2) != 0 ? (bw[i] + " ") : (bw[i] + " \n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
