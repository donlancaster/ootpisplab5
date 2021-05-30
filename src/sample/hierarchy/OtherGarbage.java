package sample.hierarchy;

public abstract class OtherGarbage extends CosmicBody{
    private String structure;

    public OtherGarbage(double mass, String name, double size, String form, String structure) {
        super(mass, name, size, form);
        this.structure = structure;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
}
