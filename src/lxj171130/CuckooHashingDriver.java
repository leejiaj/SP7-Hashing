package lxj171130;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class CuckooHashingDriver {
	public static void main(String[] args) throws FileNotFoundException {
		CuckooHashing<Long> cuckooHashing = new CuckooHashing<>();
        Scanner sc;
        if (args.length > 0)
        {
            File file = new File(args[0]);
            sc = new Scanner(file);
        }
        else
        {
            sc = new Scanner(System.in);
        }

        String operation = "";
        long operand = 0;
        long result = 0;
        int modValue = 999983;
        // Initialize the timer
        Timer timer = new Timer();

        while (!((operation = sc.next()).equals("End")))
        {
            switch (operation)
            {
            case "Add":
                operand = sc.nextLong();
                if (cuckooHashing.add(operand))
                {
                    result = (result + 1) % modValue;
                }
                break;
            case "Remove":
                operand = sc.nextLong();
                if (cuckooHashing.remove(operand) != null)
                {
                    result = (result + 1) % modValue;
                }
                break;
            case "Contains":
                operand = sc.nextLong();
                if (cuckooHashing.contains(operand))
                {
                    result = (result + 1) % modValue;
                }
                break;
            }
        }

        // End Time
        timer.end();

        System.out.println("Cuckoo Hashing Performance:");
        System.out.println(result);
        System.out.println(timer);
        System.out.println();
        
        // HashMap/Hashset
        if (args.length > 0)
        {
            File file = new File(args[0]);
            sc = new Scanner(file);
        }
        else
        {
            sc = new Scanner(System.in);
        }

        operation = "";
        operand = 0;
        result = 0;
        modValue = 999983;
        // Initialize the timer
        timer = new Timer();
        HashSet<Long> hs = new HashSet<>();
        while (!((operation = sc.next()).equals("End")))
        {
            switch (operation)
            {
            case "Add":
                operand = sc.nextLong();
                if (hs.add(operand))
                {
                    result = (result + 1) % modValue;
                }
                break;
            case "Remove":
                operand = sc.nextLong();
                if (hs.remove(operand))
                {
                    result = (result + 1) % modValue;
                }
                break;
            case "Contains":
                operand = sc.nextLong();
                if (hs.contains(operand))
                {
                    result = (result + 1) % modValue;
                }
                break;
            }
        }

        // End Time
        timer.end();
        
        System.out.println("Java Hashset Performance:");
        System.out.println(result);
        System.out.println(timer);
        
    }

}
