package Lesson1.Fruits;

/**
 * Java. Level 3. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 24, 2018
 */

public abstract class Fruit {

    protected float weight;
    public Fruit(float middleWeight){
        weight = middleWeight;
    }

    public float getWeight() {
        return weight;
    }
}
