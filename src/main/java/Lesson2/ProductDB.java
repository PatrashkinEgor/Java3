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



public class ProductDB {

    public static final String DEFAULT_PRICE = "1000";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find JDBC driver", e);
        }
        Connection connection = createConnection();


        try {
            dropTB(connection);
            createTB(connection);
        } finally {
            connection.close();
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
