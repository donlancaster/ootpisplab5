package sample.hierarchy;

public class Planet extends CosmicBody {
    private double rotationSpeed;
    private String color;
    private String structure;

    public Planet(double mass, String name, double size, String form, double rotationSpeed, String color, String structure) {
        super(mass, name, size, form);
        this.rotationSpeed = rotationSpeed;
        this.color = color;
        this.structure = structure;

    }

    public Planet() {
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return "Planet "+ getName();
    }
}
