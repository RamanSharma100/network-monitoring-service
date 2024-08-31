package net.monitor.Traffic;

import net.monitor.Network.NetworkMonitor;

public class Main {
    public static void main(String[] args) {
        NetworkMonitor networkMonitor = new NetworkMonitor();
        System.out.println("Welcome to Network Monitoring Service!");
        networkMonitor.start();
    }
}