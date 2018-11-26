package Lesson2;

/**
 * Java. Level 3. Lesson 2. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 27, 2018
 */

public class Product {

    private int id;
    private  int prodid;
    private String title;
    private long cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodid=" + prodid +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
