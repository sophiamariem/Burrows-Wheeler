/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] chars = new char[R];
        for (char ch = 0; ch < R; ch++) {
            chars[ch] = ch;
        }

        String s = BinaryStdIn.readString();
        for (char c : s.toCharArray()) {
            for (char ch : chars) {
                if (c == chars[ch]) {
                    BinaryStdOut.write(ch, 8);
                    System.arraycopy(chars, 0, chars, 1, ch);
                    chars[0] = c;
                    break;
                }
            }
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] chars = new char[R];
        for (char ch = 1; ch < R; ch++) {
            chars[ch] = ch;
        }

        String s = BinaryStdIn.readString();
        for (char c : s.toCharArray()) {
            BinaryStdOut.write(chars[c]);
            char temp = chars[c];
            System.arraycopy(chars, 0, chars, 1, c);
            chars[0] = temp;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        String input = args[0];

        if (input.equals("-")) {
            encode();
        }
        else if (input.equals("+")) {
            decode();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
