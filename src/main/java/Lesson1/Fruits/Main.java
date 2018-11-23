package Lesson1.Fruits;

/**
 * Java. Level 3. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 24, 2018
 */


/**
 * 3. Большая задача:
 * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
 * нельзя сложить и яблоки, и апельсины;
 * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
 * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес
 * яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
 * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в
 * compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
 * сравнивать с коробками с апельсинами);
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
 * фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в
 * другую перекидываются объекты, которые были в этой коробке;
 * g. Не забываем про метод добавления фрукта в коробку.
 */


public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<Apple>(new Apple());
        Box<Apple> appleBox2 = new Box<Apple>(new Apple());

        Box<Orange> orangeBox1 = new Box<Orange>(new Orange());
        Box<Orange> orangeBox2 = new Box<Orange>(new Orange());


        for (int i =0; i<10; i++){
            appleBox1.putFruitIn(new Apple());
            orangeBox1.putFruitIn(new Orange());
        }

        for (int i =0; i<5; i++){
            appleBox2.putFruitIn(new Apple());
            orangeBox2.putFruitIn(new Orange());
        }


        System.out.println("Вес 1-ой коробки с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес 2-ой коробки с яблоками: " + appleBox2.getWeight());

        System.out.println("Вес 1-ой коробки с апельсинами: " + orangeBox1.getWeight());
        System.out.println("Вес 2-ой коробки с апельсинами: " + orangeBox2.getWeight());

        appleBox2.moveToAnother(appleBox1);
        orangeBox2.moveToAnother(orangeBox1);

        System.out.println("После пересыпки:");
        System.out.println("Вес 1-ой коробки с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес 2-ой коробки с яблоками: " + appleBox2.getWeight());

        System.out.println("Вес 1-ой коробки с апельсинами: " + orangeBox1.getWeight());
        System.out.println("Вес 2-ой коробки с апельсинами: " + orangeBox2.getWeight());

        System.out.print("1-я коробка с апельсинами и 1-я коробка с яблоками имеют ");
        System.out.println((appleBox1.compareWith(orangeBox1)?"одинаковый":"разный") + " вес");

        System.out.print("2-я коробка с апельсинами и 2-я коробка с яблоками имеют ");
        System.out.println((appleBox2.compareWith(orangeBox2)?"одинаковый":"разный") + " вес");



    }
}
