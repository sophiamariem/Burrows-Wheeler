/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private final String input;
    private final int length;
    private int[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        this.input = s;
        this.length = s.length();
        this.index = new int[length];

        for (int i = 0; i < length; i++) {
            index[i] = i;
        }

        sort(0, length - 1, 0);
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length) {
            throw new IllegalArgumentException();
        }
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String in = args[0];
        if (in == null) {
            throw new IllegalArgumentException();
        }

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(in);
        for (int i = 0; i < circularSuffixArray.length(); i++) {
            StdOut.println(circularSuffixArray.index(i));
        }
    }

    private void sort(int left, int right, int idx) {
        if (right <= left || idx > length) {
            return;
        }
        int lt = left;
        int r = right;

        int leftChars = input.charAt(charIndex(left, idx));
        int i = left + 1;
        while (i <= r) {
            int rightChars = input.charAt(charIndex(i, idx));
            if (rightChars == leftChars) {
                i++;
            }
            else if (rightChars < leftChars) {
                swap(lt++, i++);
            }
            else {
                swap(i, r--);
            }
        }
        sort(left, lt - 1, idx);
        sort(lt, r, idx + 1);
        sort(r + 1, right, idx);
    }

    private void swap(int a, int b) {
        int temp = index[a];
        index[a] = index[b];
        index[b] = temp;
    }

    private int charIndex(int idx, int d) {
        if (index[idx] + d < length) {
            return index[idx] + d;
        }
        return index[idx] + d - length;
    }

}
