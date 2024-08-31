package net.monitor.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.monitor.Filters.ProfanityFilter;

public class NetworkMonitor {
    private ProfanityFilter profanityFilter;

    public NetworkMonitor() {
        profanityFilter = new ProfanityFilter();
    }

    public void start() {
        try {
            System.out.println("Network Monitor started...");
            ProcessBuilder pb = new ProcessBuilder("tcpdump", "-A", "-i", "any", "port", "80", "-c", "10"); // tcpdump
                                                                                                            // -A -i any
                                                                                                            // port 80
                                                                                                            // -c 10 -
                                                                                                            // this
                                                                                                            // command
                                                                                                            // will
                                                                                                            // capture
                                                                                                            // 10
                                                                                                            // packets
                                                                                                            // on port
                                                                                                            // 80 and
                                                                                                            // display
                                                                                                            // ASCII
                                                                                                            // content
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("packet: " + line);
                if (profanityFilter.contains(line)) {
                    System.out.println("Profanity detected: " + profanityFilter.filter(line));

                    // block the search with custom message on browser side
                    System.out.println("HTTP/1.1 200 OK");
                    System.out.println("Content-Type: text/html");
                    System.out.println("Content-Length: 100");
                    System.out.println();
                    System.out.println("<html><body><h1>Profanity detected!</h1></body></html>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
