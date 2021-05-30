package sample.hierarchy;

import java.io.Serializable;

public abstract class CosmicBody implements Serializable {
private double mass;
private String name;
private double size;
private String form;

    public CosmicBody(double mass, String name, double size, String form) {
        this.mass = mass;
        this.name = name;
        this.size = size;
        this.form = form;
    }

    public CosmicBody() {
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
