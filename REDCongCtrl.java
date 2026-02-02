/*Write a program to implement random early detection (RED) 
congestion control algorithm*/

import java.util.Random;
import java.util.Scanner;

public class REDCongCtrl {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//number of packets to be sent
        System.out.println("Enter the maximum number of packets:");
        int maxPackets = sc.nextInt();
//size of  queue Where packets can be stored
        System.out.println("Enter the queue size:");
        int queueSize = sc.nextInt();

        System.out.println("Enter the maximum probability:");
        double maxProbability = sc.nextDouble();
//used to calculated the drop probabilty (max-min)
        System.out.println("Enter the minimum probability:");
        double minProbability = sc.nextDouble();
//the value after which the congestion control comes to action
        System.out.println("Enter the threshold value:"); 
        int threshold = sc.nextInt();

        simulateCongestion(maxPackets, queueSize, maxProbability, minProbability, threshold);
    }

    private static void simulateCongestion(int maxPackets, int queueSize, double maxProbability, double minProbability, int threshold) {
        Random rand = new Random(System.currentTimeMillis());
        int queueLength = 0;

        for (int i = 0; i < maxPackets; i++) {
            double dropProbability = calculateDropProbability(queueLength, queueSize, maxProbability, minProbability, threshold);

            if (queueLength >= threshold && rand.nextDouble() < dropProbability) {
                System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
                //checking the threshold value and the probabilty to check whether to accept or reject the packet
            } else {
                System.out.println("Packet accepted " + (i + 1));
                queueLength++;
            }
        }
    }

    private static double calculateDropProbability(int currentQueueLength, int queueSize, double maxProbability, double minProbability, int threshold) {
        double slope = (maxProbability - minProbability) / (queueSize - threshold);
        return minProbability + slope * (currentQueueLength - threshold);
    }
}

/*OUTPUT 
Enter the maximum number of packets:
10
Enter the queue size:
8
Enter the maximum probability:
2
Enter the minimum probability:
1
Enter the threshold value:
5
Packet accepted 1
Packet accepted 2
Packet accepted 3
Packet accepted 4
Packet accepted 5
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)
Packet dropped (CONGESTION AVOIDANCE)

*/
