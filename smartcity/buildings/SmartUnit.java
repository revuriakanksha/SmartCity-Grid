package smartcity.buildings;

import smartcity.grid.GridOperable;
import smartcity.exceptions.OverloadException;

/**
 * A SmartUnit is a residential unit that can operate with the grid,
 * and will throw OverloadException if total usage exceeds a threshold.
 */
public class SmartUnit extends ResidentialUnit implements GridOperable {
    private boolean connected;

    public SmartUnit(String address, int unitCount, String[] residents) {
        super(address, unitCount, residents);
        this.connected = false;
    }

    @Override
    public void connectToGrid() {
        connected = true;
        System.out.println("Smart unit at " + getAddress() + " connected to grid.");
    }

    @Override
    public void transmitUsage() {
        if (!connected) {
            System.out.println("Cannot transmit usage: not connected to grid.");
            return;
        }
        System.out.println("Transmitting energy data from " + getAddress() + " to SmartCity Grid.");
    }

    @Override
    public void disconnectFromGrid() {
        connected = false;
        System.out.println("Smart unit at " + getAddress() + " disconnected from grid.");
    }

    /**
     * Check total usage against threshold; throws OverloadException when exceeded.
     */
    public void checkForOverload(double maxLimit) throws OverloadException {
        double total = totalUsage();
        if (total > maxLimit) {
            throw new OverloadException("Energy overload detected at " + getAddress()
                    + " (usage: " + total + " kWh, limit: " + maxLimit + " kWh)");
        }
    }
}
