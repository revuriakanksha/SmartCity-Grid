package smartcity.threads;

public class LoadBalancer extends Thread {
    private final double loadValue;
    private final String zoneName;

    public LoadBalancer(String zoneName, double loadValue) {
        this.zoneName = zoneName;
        this.loadValue = loadValue;
        setName("LoadBalancer-" + zoneName);
    }

    @Override
    public void run() {
        System.out.printf("[%s] Balancing load: %.3f kWh%n", zoneName, loadValue);
        try {
            // Simulate balancing work proportional to load (capped)
            long sleep = Math.min(2000, (long) (Math.abs(loadValue) * 10));
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            System.out.println("Balancer interrupted for zone " + zoneName);
            Thread.currentThread().interrupt();
        }
        System.out.printf("[%s] Load balanced.%n", zoneName);
    }
}
