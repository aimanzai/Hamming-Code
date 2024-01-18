package Final_Project;

import java.util.Scanner;

public class HammingCode {
    public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter both word stored & fetched from right to left!");
        System.out.println("Enter the 8-bit data word stored in memory");
        int dStored[] = new int[8];

        for (int i = 0; i < 8; i++) {
            dStored[i] = sc.nextInt();
        }

        System.out.println("Enter the 8-bit data word fetched from the memory");
        int dFetched[] = new int[8];

        for (int i = 0; i < 8; i++) {
            dFetched[i] = sc.nextInt();
        }

        int[] cStored = calcWordStored(dStored);
        int[] cFetched = calcWordFetched(dFetched);

        System.out.print("\nStored Word with Check Bits: ");
        displayWord(cStored);
        
        System.out.println();
        
        System.out.print("Fetched Word with Check Bits: ");
        displayWord(cFetched);

        checkErrorPosition(cStored, cFetched);
    }

    public static int[] calcWordStored(int d[]) {
        int p[] = new int[4];

        p[0] = d[0] ^ d[1] ^ d[3] ^ d[4] ^ d[6] ^ d[7];
        p[1] = d[0] ^ d[2] ^ d[3] ^ d[5] ^ d[6];
        p[2] = d[1] ^ d[2] ^ d[3];
        p[3] = d[4] ^ d[5] ^ d[6] ^ d[7];

        int c[] = new int[12];

        c[0] = p[0];
        c[1] = p[1];
        c[2] = d[0];
        c[3] = p[2];
        c[4] = d[1];
        c[5] = d[2];
        c[6] = d[3];
        c[7] = p[3];
        c[8] = d[4];
        c[9] = d[5];
        c[10] = d[6];
        c[11] = d[7];

        return c;
    }

    public static int[] calcWordFetched(int d[]) {
        int p[] = new int[4];

        p[0] = d[0] ^ d[1] ^ d[3] ^ d[4] ^ d[6] ^ d[7];
        p[1] = d[0] ^ d[2] ^ d[3] ^ d[5] ^ d[6];
        p[2] = d[1] ^ d[2] ^ d[3];
        p[3] = d[4] ^ d[5] ^ d[6] ^ d[7];

        int c[] = new int[12];

        c[0] = p[0];
        c[1] = p[1];
        c[2] = d[0];
        c[3] = p[2];
        c[4] = d[1];
        c[5] = d[2];
        c[6] = d[3];
        c[7] = p[3];
        c[8] = d[4];
        c[9] = d[5];
        c[10] = d[6];
        c[11] = d[7];

        return c;
    }

    public static int checkErrorPosition(int cStored[], int cFetched[]) {
    int errorPosition = 0;

    // XOR check for each check bit
    for (int i = 0; i < 4; i++) {
        if (cStored[i] != cFetched[i]) {
            errorPosition += Math.pow(2, 3 - i); // Corrected the power calculation
        }
    }

    if (errorPosition != 0) {
        System.out.println("\nError at position: " + (errorPosition + 1));
    } else {
        System.out.println("No error detected.");
    }

    return errorPosition;
}

    /*public static void displayWord(int[] word) {
        for (int bit : word) {
            System.out.print(bit + " ");
        }
        System.out.println();
    }*/
    
    public static void displayWord(int[] word) {
        for (int i = word.length - 1; i >= 0; i--) {
            System.out.print(word[i] + " ");
        }
        System.out.println();
    }
}