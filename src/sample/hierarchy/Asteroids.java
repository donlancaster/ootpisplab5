package sample.hierarchy;

public class Asteroids extends OtherGarbage {
    private int quantity;

    @Override
    public String toString() {
        return "Asteroids "+getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Asteroids(double mass, String name, double size, String form, String structure, int quantity) {
        super(mass, name, size, form, structure);
        this.quantity = quantity;
    }
}
