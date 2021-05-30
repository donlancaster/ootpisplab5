package sample.hierarchy;

public class Comet extends OtherGarbage{
    private double tailLength;

    public double getTailLength() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }

    public Comet(double mass, String name, double size, String form, String structure, double tailLength) {
        super(mass, name, size, form, structure);
        this.tailLength = tailLength;

    }

    @Override
    public String toString() {
        return "Comet "+ getName();
    }
}
