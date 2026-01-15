# SmartCity Grid – Powering a Java Future 🌆⚡

A simulation project built in **Java** to model smart residential units connected to a city-wide energy grid.  
It demonstrates **object-oriented design, exception handling, multithreading, and file I/O** in a real-world inspired case study.

---

## 📌 Features
- **Building & Residential Units**: Models residents and their energy usage.
- **Smart Units**: Connect to the grid, transmit usage, and detect overloads.
- **Custom Exceptions**: `OverloadException` to handle energy threshold breaches.
- **Multithreading**: `LoadBalancer` threads simulate balancing loads across zones.
- **Report Generation**: Daily energy usage reports written to timestamped files.
- **Simulation**: Randomized energy consumption per resident for realistic behavior.

---

## 🗂️ Project Structure
SmartCityProject/ ├── smartcity/app/Main.java    
# Entry point for simulation ├── smartcity/buildings/Building.java ├── smartcity/buildings/ResidentialUnit.java ├── smartcity/buildings/SmartUnit.java ├── smartcity/exceptions/OverloadException.java ├── smartcity/grid/GridOperable.java ├── smartcity/io/ReportWriter.java └── smartcity/threads/LoadBalancer.java
---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/SmartCityProject.git
   cd SmartCityProject
2. Compile the project:
javac smartcity/app/Main.java
3. Run the simulation:
java smartcity.app.Main

## SAMPLE OUTPUT
Smart unit at 12A Maple St connected to grid.
Smart unit at 88B Oak Ave connected to grid.
Smart unit at 101 Central Blvd connected to grid.

Transmitting energy data from 12A Maple St to SmartCity Grid.
Transmitting energy data from 88B Oak Ave to SmartCity Grid.
Transmitting energy data from 101 Central Blvd to SmartCity Grid.

Report written to smartcity_report_2025_10_29_13_21_39.txt
[Zone-1] Balancing load: 6.781 kWh
[Zone-2] Balancing load: 2.665 kWh
[Zone-3] Balancing load: 13.590 kWh
Simulation complete.
## 🛠️ Concepts Demonstrated
-- OOP Principles: Inheritance, interfaces, encapsulation.
- Error Handling: Custom checked exceptions.
- Concurrency: Thread-based load balancing.
- File I/O: Writing reports with timestamps.
- Practical Simulation: Energy usage tracking and overload detection.
## 📌 Future Enhancements
- Add GUI dashboard for visualizing energy usage.
- Integrate database storage for long-term reporting.
- Extend simulation to commercial buildings and renewable sources.


