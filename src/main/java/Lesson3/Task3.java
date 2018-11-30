package Lesson3;
/**
 * Java. Level 3. Lesson 2. Homework. Task2.
 *
 * @author Egor Patrashkin
 * @version dated Nov 30, 2018
 */


/** Задание 3
 *  Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 *  Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время
 *  выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 *  Чтобы не было проблем с кодировкой, используйте латинские буквы.
 */

import java.io.*;
import java.util.*;

public class Task3 {
    final static int PAGE_SIZE = 1800;          // Размер страницы
    final static int REQUIRED_SIZE = 1048576;   // количество байт в 10 мб

    public static void main(String[] args) {
        createBigFileFrom("Shakespeare_The_Comedy_of_Errors.txt");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите номер страницы для печати (Для выхода введите отрицательное число):");
            try {
                int numberOfPage = sc.nextInt();
                if (numberOfPage < 0) {
                    System.exit(0);
                }
                printPage("Task3.txt", numberOfPage);
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат номера страницы.");
            }
        }
    }

    /**
     * Метод создает выходной файл размером 10Мб путем записи входного файла необходимое количество раз.
     * @param nameOfFile Имя файла из которого будт создан "большой" файл.
     */
    private static void createBigFileFrom(String nameOfFile) {
        SequenceInputStream seq = null;
        FileOutputStream out = null;
        try {
            System.out.print("Создание файла >10 Мб...");
            ArrayList<FileInputStream> al = new ArrayList<>();
            File f = new File(nameOfFile);

            int numberOfIterations = (int) (REQUIRED_SIZE / f.length()) + 1;
            for (int i = 0; i < numberOfIterations; i++) {
                al.add(new FileInputStream(f));
            }

            Enumeration<FileInputStream> en = Collections.enumeration(al);
            seq = new SequenceInputStream(en);
            out = new FileOutputStream("Task3.txt");
            int rb = seq.read();
            int count = 0;

            while (rb != -1) {
                out.write(rb);
                count++;
                rb = seq.read();
            }
            System.out.println("Оk\nСоздан документ на " + count + " байт.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { seq.close(); } catch ( IOException e ) { };
            try { out.close(); } catch ( IOException e ) { };
        }
    }

    /**
     * Метод распечатывает страницу. Нумерация страниц с 0, размер страницы 1800 байт.
     *
     * @param fileName  Имя файла из которого будет производиться печать
     * @param numberOfPage номер печатаемой страницы
     */
    private static void printPage(String fileName, int numberOfPage) {
        long start = System.currentTimeMillis();

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            System.out.println("Содержимое " + numberOfPage + " страницы:");
            for (int i = numberOfPage * PAGE_SIZE; i < (numberOfPage + 1) * PAGE_SIZE; i++) {
                raf.seek(i);
                System.out.print((char) raf.read());
            }
            long finish = System.currentTimeMillis();
            System.out.println("\nПечать выполнена за " + (finish - start) + " мс.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

