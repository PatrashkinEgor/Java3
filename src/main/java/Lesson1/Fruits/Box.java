package Lesson1.Fruits;

/**
 * Java. Level 3. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 24, 2018
 */

import java.util.ArrayList;
import java.util.List;

public class Box<F extends Fruit> {
    private List<F> fruitsList;

    public Box(F fruit){
        fruitsList = new ArrayList<F>();
        fruitsList.add(fruit);
    }

    public void putFruitIn(F fruit){
        fruitsList.add(fruit);
    }

    public F getOutFruitFrom(){
        if (!fruitsList.isEmpty()) {
            return fruitsList.remove(0);
        }
        return null;
    }

    public float getWeight(){
        float weight = 0;
        for (F f:fruitsList) {
            weight+=f.weight;
        }
        return weight;
    }

    public boolean compareWith(Box<?> another){
        return (this.getWeight() == another.getWeight());
    }

    public void moveToAnother(Box<F>destination){
        while (!fruitsList.isEmpty()){
            destination.putFruitIn(fruitsList.remove(0));
        }
    }
}
