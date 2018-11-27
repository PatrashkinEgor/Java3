package Lesson2;

/**
 * Java. Level 3. Lesson 2. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 27, 2018
 */

/**
 * Задание
 * 1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения (подробнее -
 * в методическом пособии к уроку);
 * ** 2.** При запуске приложения очистить таблицу и заполнить 10000 товаров (подробнее-в методическом пособии к уроку);
 * ** 3.** Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо вывести сообщение
 * «Такого товара нет», если товар не обнаружен в базе. Консольная команда: «/цена товар545».
 * 4. Добавить возможность изменения цены товара. Указываем имя товара и новую цену. Консольная команда:
 * «/сменитьцену товар10 10000».
 * 5. Вывести товары в заданном ценовом диапазоне. Консольная команда: «/товарыпоцене 100 600».
 */

import java.sql.*;
import java.util.Scanner;


public class ProductDB {

    public static final String DEFAULT_PRICE = "1000";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find JDBC driver", e);
        }

        initDB();


        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите команду");
            String str = sc.nextLine();
            String[] cmd = str.split(" ", 3);
            if (cmd[0].equals("/цена")) {
                findPrice(cmd[1]);
            } else if (cmd[0].equals("/товарыпоцене")) {
                findProductsByPrice(cmd);
            } else if (cmd[0].equals("/сменитьцену")) {
                changePrice(cmd);
            } else if (cmd[0].equals("/конец")) {
                System.exit(0);
            } else {
                System.out.println("Неизвестная команда.");
            }
        }
    }

    /**
     * Метод выполняющий запрос на изменение цены на товар в базе данных
     * @param cmd параметры для выполнения SQL запроса, где: 1-й элемент - Наименование товара, 2-й Новая цена;
     * @throws SQLException
     */
    private static void changePrice(String[] cmd) throws SQLException {
        Connection connection = createConnection();
        try {
            Statement stmt = connection.createStatement();
            int rowCount = stmt.executeUpdate("UPDATE Products SET cost = " + cmd[2] +
                    " WHERE title = '" + cmd[1] + "'");
            if (rowCount == 0){
                System.out.println("Товар с таким названием отсутствует в базе данных.");
            }
            System.out.println("Rows updated: " + rowCount);
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    /**
     * Метод выполняющий поиск товара по цене в базе данных, цена и наименование найденных позиций выводятся в консоль.
     * @param cmd параметры для выполнения SQL запроса, где: 1-й и 2-й элемент границы поиска;
     * @throws SQLException
     */
    private static void findProductsByPrice(String[] cmd) throws SQLException {
        Connection connection = createConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title, cost FROM Products WHERE cost BETWEEN "+ cmd[1] +
                    " AND " + cmd[2]);
            if (!rs.next()){
                System.out.println("Нет товаров в таком диапазоне цен.");
            }
            while (rs.next()) {
                System.out.println("Товар: " + rs.getString(1) + " Цена: "+rs.getString(2));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    /**
     * Метод выполняющий поиск цены товара в базе данных, новая цена и наименование товара выводятся в консоль.
     * @param s параметр(новая цена)  для выполнения SQL запроса;
     * @throws SQLException
     */
    private static void findPrice(String s) throws SQLException {
        Connection connection = createConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title,cost FROM Products WHERE title = '" + s +"'");

            if (!rs.next()){
                System.out.println("Товар с таким названием отсутствует в базе данных.");
            } else {
                System.out.println("Товар: " + rs.getString(1) + " Цена: " + rs.getString(2));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    private static void initDB() throws SQLException {
        try {
            System.out.print("Database initialization....");
            Connection connection = createConnection();
            try {
                dropTB(connection);
                createTB(connection);
            } finally {
                connection.close();
            }
            System.out.println("Ok");
        } catch (SQLException e){
            System.out.println("Fail to initiate DataBase.");
        }


    }

    private static void createTB(Connection connection)throws SQLException  {
        connection.setAutoCommit(false);

        try {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    "prodid INTEGER NOT NULL, " +
                    "title TEXT NOT NULL, " +
                    "cost INTEGER NOT NULL); ");


            PreparedStatement psSt = connection.prepareStatement("INSERT INTO Products (prodid, title, cost) VALUES (?, ?, ?)");
            for (int i = 1; i <1001; i++){
                psSt.setString(1, String.valueOf(i));
                psSt.setString(2, "товар"+String.valueOf(i));
                psSt.setString(3, DEFAULT_PRICE);
                psSt.addBatch();
            }
            psSt.executeBatch();
        } catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }


    }

    private static void dropTB(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS Products");
    }


    private static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:Sample.db");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect db", e);
        }
    }


}
