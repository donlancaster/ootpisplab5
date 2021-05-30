package sample.hierarchy;

import java.io.*;

public class Star extends Planet implements Serializable {
    private double XrayCoefficient;
    private double Luminosity;

    public Star(double mass, String name, double size, String form, double rotationSpeed, String color, String structure, double xrayCoefficient, double luminosity) {
        super(mass, name, size, form, rotationSpeed, color, structure);
        XrayCoefficient = xrayCoefficient;
        Luminosity = luminosity;
    }

    public Star() {
    }

    public double getXrayCoefficient() {
        return XrayCoefficient;
    }

    public void setXrayCoefficient(double xrayCoefficient) {
        XrayCoefficient = xrayCoefficient;
    }

    public double getLuminosity() {
        return Luminosity;
    }

    public void setLuminosity(double luminosity) {
        Luminosity = luminosity;
    }

    @Override
    public String toString() {
        return "Star " + getName();
    }

    public static void serialize(Star ser) {
        try {
            FileOutputStream out = new FileOutputStream("star.out");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(ser);
            oos.flush();
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
    }

    public void deserialize(Star ser) throws IOException, ClassNotFoundException {
        try {
            FileInputStream in = new FileInputStream("star.out");
            ObjectInputStream ois = new ObjectInputStream(in);
            ser = (Star) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }

        System.out.println("Card read is: " + ser);
    }
}
