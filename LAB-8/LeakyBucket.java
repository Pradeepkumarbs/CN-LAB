/*Write a program for congestion control using leaky bucket algorithm 
and token bucket algorithm.*/

/*Leaky bucket algorithm*/


import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bucket capacity: ");
        int bucketCapacity = scanner.nextInt();
        System.out.print("Enter output rate (packets per second): ");
        int outputRate = scanner.nextInt();
        System.out.print("Enter the number of packets: ");
        int numPackets = scanner.nextInt();
        int[] packetSizes = new int[numPackets];
        System.out.println("Enter the packet sizes: ");
        for (int i = 0; i < numPackets; i++) {
            packetSizes[i] = scanner.nextInt();
        }

        int currentBucketSize = 0;

        System.out.println("\nPacket Size\tBucket Size\tSent\tRemaining\tStatus");
        for (int packetSize : packetSizes) {
            if (currentBucketSize + packetSize <= bucketCapacity) {
                currentBucketSize += packetSize;
                System.out.println(packetSize + "\t\t" + currentBucketSize + "\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0, currentBucketSize - outputRate)+"\t\tAccepted");
            } else {
                System.out.println(packetSize + "\t\t" + currentBucketSize+"\t\t"+Math.min(outputRate,currentBucketSize)+"\t\t"+Math.max(0, currentBucketSize - outputRate)+"\t\tDropped");
            }
            currentBucketSize = Math.max(0, currentBucketSize - outputRate);
        }
        scanner.close();
    }
}

/* OUTPUT 
Enter bucket capacity: 5
Enter output rate (packets per second): 3
Enter the number of packets: 2
Enter the packet sizes: 
2
4

Packet Size	Bucket Size	Sent	Remaining	Status
2		2		2		0		Accepted
4		4		3		1		Accepted

[OR]

Enter bucket capacity: 6
Enter output rate (packets per second): 2
Enter the number of packets: 4
Enter the packet sizes: 
2
4
6
7

Packet Size	Bucket Size	Sent	Remaining	Status
2		2		2		0		Accepted
4		4		2		2		Accepted
6		2		2		0		Dropped
7		0		0		0		Dropped

*/
