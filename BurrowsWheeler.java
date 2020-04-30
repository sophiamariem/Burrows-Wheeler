/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        int length = input.length();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);
        char[] t = new char[length];

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            int index = circularSuffixArray.index(i);
            t[i] = shift(input, index);

            if (index == 0) {
                BinaryStdOut.write(i);
            }
        }

        for (char c : t) {
            BinaryStdOut.write(c, 8);
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int firstRow = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        int length = input.length();

        int[] count = new int[R + 1];
        for (int i = 0; i < length; i++) {
            count[input.charAt(i) + 1]++;
        }
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }

        int[] next = new int[length];
        for (int i = 0; i < length; ++i) {
            next[count[input.charAt(i)]++] = i;
        }
        for (int i = 0; i < length; ++i) {
            firstRow = next[firstRow];
            BinaryStdOut.write(input.charAt(firstRow));
        }

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        String in = args[0];

        if (in.equals("-")) {
            transform();
        }
        else if (in.equals("+")) {
            inverseTransform();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private static char shift(String input, int shift) {
        if (shift > 0) {
            return input.charAt(shift - 1);
        }
        return input.charAt(input.length() - 1);
    }
}
