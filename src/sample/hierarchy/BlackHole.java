package sample.hierarchy;

public class BlackHole extends Star {
    private double evaporation;
    private double eventHorizonRadius;

    public BlackHole(double mass, String name, double size, String form, double rotationSpeed, String color,
                     String structure, double xrayCoefficient, double luminosity, double evaporation, double eventHorizonRadius) {
        super(mass, name, size, form, rotationSpeed, color, structure, xrayCoefficient, luminosity);
        this.evaporation = evaporation;
        this.eventHorizonRadius = eventHorizonRadius;
    }

    public BlackHole() {
    }



    public double getEvaporation() {
        return evaporation;
    }

    public void setEvaporation(double evaporation) {
        this.evaporation = evaporation;
    }

    public double getEventHorizonRadius() {
        return eventHorizonRadius;
    }

    public void setEventHorizonRadius(double eventHorizonRadius) {
        this.eventHorizonRadius = eventHorizonRadius;
    }

    @Override
    public String toString() {
        return "BlackHole " +getName();
    }

    public String forSerialization(){
        return "Name:" + getName() + "; Mass:" + getMass() + "; Size:" + getSize() + "; Form:" + getForm() + "; Color:" + getColor() +
                "; Structure:" + getStructure() + "; XRay:" + getXrayCoefficient()+"; Luminosity:"+getLuminosity() +"; Evaporation:"+getEvaporation()+
                "; EventHorizontRadius:"+getEventHorizonRadius();
    }

}
