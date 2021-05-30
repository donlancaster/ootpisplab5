package sample.hierarchy;

import java.io.Serializable;

public class Galaxy extends CosmicBody implements Serializable {
    private String galaxyType;
    private int elements;
    private BlackHole centerBlackHole;

    public Galaxy(double mass, String name, double size, String form, String galaxyType, int elements, BlackHole centerBlackHole) {
        super(mass, name, size, form);
        this.galaxyType = galaxyType;
        this.elements = elements;
        this.centerBlackHole = centerBlackHole;
    }

    public Galaxy() {
    }

    public String getGalaxyType() {
        return galaxyType;
    }

    public void setGalaxyType(String galaxyType) {
        this.galaxyType = galaxyType;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public BlackHole getCenterBlackHole() {
        return centerBlackHole;
    }

    public void setCenterBlackHole(BlackHole centerBlackHole) {
        this.centerBlackHole = centerBlackHole;
    }

    @Override
    public String toString() {
        return "Galaxy " + getName();
    }

    public String forSerialization() {
        return "Name:" + getName() + "; Mass:" + getMass() + "; Size:" + getSize() + "; Form:" + getForm() + "; GalaxyType:" + getGalaxyType() +
                "; Elements:" + getElements() + "; BlackHole " + getCenterBlackHole().forSerialization();
    }
}
