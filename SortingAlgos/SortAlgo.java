package SortingAlgos;

import java.util.Arrays;
import java.time.Duration;
import java.time.Instant;
/**[TODO] explain what it does and have list with links to all methods and for each method put author.
 * Has most of the shared helper functions that diff sorting algos will use.
 * <p>
 * <b> Has Static Helper Methods...</b>
 * <p>
 * {@link #swap(Comparable[], int, int)}
 * <p>
 * {@link #less(Comparable[], int, int)}
 * <p>
 * {@link #less(Comparable, Comparable)}
 * <p>
 * {@link #isSorted(Comparable[])}
 * <p>
 * {@link #printArray(Comparable[])}
 * <p>
 * {@link #getArrayString(Comparable[])}
 * <p>
 *  {@link #getItemStr(Comparable[], int)}
 * <p>
 * {@link #arrRangePrintStr(Comparable[], int, int)}
 * <p>
 * @author Malachi Sanderson.
 * @since 10-19-22
 */
public abstract class SortAlgo 
{

    /**
     * Swaps the values within an array between two indices (indexX and indexY
     * @param a - array (pass by reference)
     * @param indexX - first index
     * @param indexY - second index
     * @author https://github.com/richss/SortingAlgorithms/blob/master/src/BubbleSort.java
     */
    public static void swap(Comparable[] a, int indexX, int indexY)
    {
        Comparable tmp = a[indexX];
        a[indexX] = a[indexY];
        a[indexY] = tmp;
    }

    /**
     * check if one element of a comparable array is less than another.
     * @param a array we are comparing
     * @param indexX index of the element we wish to check if is less than other index
     * @param indexY index of element we are comparing indexX to
     * @return true if a[indexX] < a[indexY]; else false.
     * @author https://github.com/kevin-wayne/algs4/tree/master/src/main/java/edu/princeton/cs/algs4
     */
    public static boolean less(Comparable[] a, int indexX, int indexY)
    {
        if(a[indexX].compareTo(a[indexY]) < 0 ) return true;
        return false;
    }
    /**
     * is one comparable v less than another w?
     * <p> Essentially same as {@link #less(Comparable[], int, int)} but easier to call for two comparables. 
     * @param v
     * @param w
     * @return true if v < w; else false.
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Quick.java
     */
    public static boolean less(Comparable v, Comparable w) 
    {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    /**
     * Prints the array on a single line. Just prints result of
     * {@link #getArrayString(Comparable[])}.
     * @param a array of comparable items you wish to print the contents of
     * @author Malachi Sanderson
     */
    public static void printArray(Comparable[] a)
    {
        System.out.println(getArrayString(a));
    }

    /**
     * Makes a string composed of string representation ({@link #toString() }) of 
     * every element of array, but each element has a space appended to it.
     * @param a array of elements of comparable type
     * @return string representation of array (with spaces appended to each element.)
     * @author Malachi Sanderson (inspired by other examples tho)
     */
    public static String getArrayString(Comparable[] a)
    {
        String arrStr = "";
        if(a.length <= 0) arrStr+="null";
        for(int i = 0;i < a.length; i++) 
        {
            arrStr += (a[i].toString() + " ");
            
        }
        return arrStr;
    }

    /**
     * Tests whether the array entries are "in order" or not.
     * @param a array of comparable elements.
     * @return if array entries are in order, return true; else false.
     * @author https://github.com/kevin-wayne/algs4/tree/master/src/main/java/edu/princeton/cs/algs4
     */
    public static boolean isSorted(Comparable[] a)
    {
        for(int i = 1; i < a.length; i++) 
            if(less(a, i, i-1)) return false;
        return true;
    }
    
    /**
     * Helper function just used to reduce repetition in print calls.
     * <p>
     * Creates a string that shows an item's index and value in my normal format. 
     * <p> 
     * Example:
     * <p>
     * <code>
     * a = {A,B,C,D};
     * <p>
     * getItemPrintString(a, 2);</code>
     * <p>
     * returns:<code>"[2] = C"</code>
     * 
     * @param a
     * @param index
     * @return a string formatted as I describe above.
     * @author Malachi Sanderson
     */
    public static String getItemStr(Comparable[] a, int index)
    {
        return "["+index+"]"+" = "+a[index];
    }

    /**
     * Helper function just used to reduce repetition in print calls.
     * <p>
     * Pass an array and the index of the lowest and highest index of
     * the items in the range you wish to get nicely formatted string for printing of.
     * <p> 
     * Example:
     * <p>
     * <code>
     * a = {A,B,C,D,E};
     * <p>
     * arrRangePrintStr(a, 0, 2);</code>
     * <p>
     * returns:<code>"[0->2]:{A B C }"</code>
     * @param a
     * @param lo
     * @param hi
     * @return
     * @author Malachi Sanderson
     */
    public static String arrRangePrintStr(Comparable[] a, int lo, int hi)
    {
        String str = "["+lo+"->"+hi +"]:{"; 
        if(lo != hi) str += getArrayString(Arrays.copyOfRange(a,lo,hi+1))+"}"; 
        else str += a[hi].toString() + " }"; //case to handle the issue when you get an arr of only one item. idk why this had to be done but it fixed issues I was getting.
        return str;
    }






    
    // private static <S extends SortAlgorithm> void sortTester(S c)
    // {
    //     System.out.println("\n\n-----------------------------\n\n "+c.class.getName()+" TEST\n\n ");
    //     Comparable<Integer>[] arr = TestInterface.getRandomArray(10);
    //     System.out.println("PRINTING NORMAL ARR: ");
    //     System.out.println(TestInterface.toArrayToString((Integer[])arr.clone()));
    //     c.sort(arr,true);
    //     System.out.println("PRINTING SORTED ARR: ");
    //     System.out.println(TestInterface.toArrayToString((Integer[])arr.clone()));
    // }
}
