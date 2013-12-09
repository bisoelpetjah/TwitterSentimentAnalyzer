/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sentiment.program;

/**
 *
 * @author Kevin
 */
public class KMP implements IAlgorithm{
    @Override
    public int countWord(String text, String pattern) {
        return searchSubString(text.toCharArray(), pattern.toCharArray());
    }
    
    public int[] preProcessPattern(char[] ptrn) {
        int i = 0, j = -1;
        int ptrnLen = ptrn.length;
        int[] b = new int[ptrnLen + 1];

        b[i] = j;
        while (i < ptrnLen) {
            while (j >= 0 && ptrn[i] != ptrn[j]) {
                // if there is mismatch consider next widest border
                j = b[j];
            }
            i++;
            j++;
            b[i] = j;
        }
        // print pettern, partial match table and index
        return b;
    }

    /**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    public int searchSubString(char[] text, char[] ptrn) {
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length;
        int txtLen = text.length;

        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(ptrn);
        int ret = 0;
        while (i < txtLen) {
            while (j >= 0 && text[i] != ptrn[j]) {
                j = b[j];
            }
            i++;
            j++;

            // a match is found
            if (j == ptrnLen) {
                ret++;
                j = b[j];
            }
        }
        return ret;
    }

}
