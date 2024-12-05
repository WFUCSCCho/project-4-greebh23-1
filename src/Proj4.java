/**********************************************************************
 * @file Proj4.java
 * @brief This program implements the Proj4 class. It
 * takes in two command line arguments and then uses the hash table with insert, search,
 * and delete operations. Its input is already sorted, shuffled, and reversed datasets.
 * It finds the time(ns) for the insert, search, and delete operation for each input.
 * @author Blythe Greene
 * @date: December 5, 2024
 ***********************************************************************/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }
        //filename
        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);//num of lines
        SeparateChainingHashTable<RemoteWork> hashTable = new SeparateChainingHashTable<>();//Hashtable of remoteWork obj's.
        ArrayList<RemoteWork> remoteList = new ArrayList<RemoteWork>(); //Create an arrayList to store the data of every element.

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        try {
            // Open the input file
            inputFileNameStream = new FileInputStream(inputFileName);
            inputFileNameScanner = new Scanner(inputFileNameStream);

            // ignore first line
            inputFileNameScanner.nextLine();

            // FINISH ME
            int count = 0;//tracks line count
            //traverse numLines with while loop

            while (count < numLines) {
                //scan line
                String line = inputFileNameScanner.nextLine();
                String[] parts = line.split(","); // split the string into multiple parts
                RemoteWork r;//Data stored in r
                r = new RemoteWork(Integer.parseInt(parts[0]), parts[1],
                        Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]));
                remoteList.add(r);
                count++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        //Make sure file is closed.
        finally {
            if (inputFileNameStream != null) {
                //Input file is closed.
                inputFileNameStream.close();
            }
        }
        //Create File Writer to open the output file.
        FileWriter output = null;
        try {
            //Create/open the file to write to it.
            output = new FileWriter("analysis.txt", true);

            //Find execution time by using start and time.
            //Find difference between start and time.

            //Do the operations on the sorted list.
            Collections.sort(remoteList);
            //Calculate insert time. Go through the list and insert the elements into the hashtable.
            long insertStart = System.nanoTime();//start
            int i;//use i to go through loop
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.insert(remoteList.get(i));
            }
            long insertTime = System.nanoTime();//time

            //Calculate search time.
            // Iterate through list to find elements in the hashtable.
            long searchStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                boolean search = hashTable.contains(remoteList.get(i));
            }
            long searchTime = System.nanoTime();

            //Calculate deletion time.
            // Iterate through the list and delete elements in the hashtable.
            long deletionStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.remove(remoteList.get(i));
            }
            long deletionTime = System.nanoTime();


            //Print out data.
            //Write to the output file.
            System.out.println("Sorted List: Number of Lines = " + numLines + ", Insert Time = " + (insertTime - insertStart)
                    + ", Search Time = " + (searchTime - searchStart) + ", Remove Time = " + (deletionTime - deletionStart));
            output.write(numLines + "," + (insertTime - insertStart) + "," + (searchTime - searchStart)
                    + "," + (deletionTime - deletionStart) + "\n");


            //Do the same steps but with the shuffled list.
            Collections.shuffle(remoteList);

            //Calculate insert time.
            // Iterate through the list and insert elements into the hashtable.
            insertStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.insert(remoteList.get(i));
            }
            insertTime = System.nanoTime();

            //Calculate search time.
            // Iterate through the list and search for elements in the hashtable.
            searchStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                boolean search = hashTable.contains(remoteList.get(i));
            }
            searchTime = System.nanoTime();


            //Calculate deletion time.
            // Iterate through the list and delete elements in the hashtable.
            deletionStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.remove(remoteList.get(i));
            }
            deletionTime = System.nanoTime();


            //Print out results
            //Write to the output file.
            //times in ns
            System.out.println("Shuffled List: Number of Lines = " + numLines + ", Insert Time = " + (insertTime - insertStart)
                    + ", Search Time = " + (searchTime - searchStart) + ", Remove Time = " + (deletionTime - deletionStart));
            output.write(numLines + "," + (insertTime - insertStart) + "," + (searchTime - searchStart)
                    + "," + (deletionTime - deletionStart) + "\n");


            //Do same operations on the reversed list.
            Collections.sort(remoteList, Collections.reverseOrder());


            //Calculate insert time.
            //Iterate through the list and insert the elements into a hashtable.
            insertStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.insert(remoteList.get(i));
            }
            insertTime = System.nanoTime();


            //Calculate search time.
            //Iterate through the list and search for elements in the hashtable.
            searchStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                boolean search = hashTable.contains(remoteList.get(i));
            }
            searchTime = System.nanoTime();


            //Calculate deletion time.
            //Iterate through the list and delete elements in the hashtable.
            deletionStart = System.nanoTime();
            for (i = 0; i < remoteList.size(); i++) {
                hashTable.remove(remoteList.get(i));
            }
            deletionTime = System.nanoTime();

            //Print out the data
            //Write to the output file.
            System.out.println("Reversed List: Number of Lines = " + numLines + ", Insert Time = " + (insertTime - insertStart)
                    + ", Search Time = " + (searchTime - searchStart) + ", Remove Time = " + (deletionTime - deletionStart));
            output.write(numLines + "," + (insertTime - insertStart) + "," + (searchTime - searchStart)
                    + "," + (deletionTime - deletionStart) + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            output.close();//close the file
        }

    }

}
