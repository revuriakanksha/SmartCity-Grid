package smartcity.buildings;

public class Building {
    protected String address;
    private int unitCount;

    public Building(String address, int unitCount) {
        this.address = address;
        this.unitCount = unitCount;
    }

    public String getAddress() {
        return address;
    }

    // accessor for unitCount as requested
    public int getUnitCount() {
        return unitCount;
    }

    public void reportStatus() {
        System.out.println("Building at " + address + " with " + unitCount + " units.");
    }
}
