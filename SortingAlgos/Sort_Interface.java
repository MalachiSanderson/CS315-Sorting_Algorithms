package SortingAlgos;

/**
 * Interface I threw together for making sorting algorithms (basically
 * just made this to continue playing with interfaces).
 * <p>
 * <b> Has Static Helper Methods...</b>
 * <p>
 * {@link #swap(Comparable[], int, int)}
 * <p>
 * {@link #less(Comparable[], int, int)}
 * <p>
 * {@link #isSorted(Comparable[])}
 * <p>
 * {@link #printArray(Comparable[])}
 * <p>
 * {@link #getArrayString(Comparable[])}
 * <p>
 * If implementing this interface,
 * <b>Has abstract methods</b>
 * <p>
 * {@link #sort(Comparable[])}
 * <p>
 * 
 */
public abstract interface Sort_Interface 
{
    public abstract void sort(Comparable[] a);
    
    abstract class BUBBLE_SORT implements Sort_Interface
    {
        
    } 

    abstract class INSERTION_SORT implements Sort_Interface
    {

    }

    abstract class SELECTION_SORT implements Sort_Interface
    {
        
    }

    /**
     * Swaps the values within an array between two indices (indexX and indexY
     * @param a - array (pass by reference)
     * @param indexX - first index
     * @param indexY - second index
     * @author {@link https://github.com/richss/SortingAlgorithms/blob/master/src/BubbleSort.java}
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
     * @author not me. Stolen from either Stansbury or book. I forgot.
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
     */
    public static boolean isSorted(Comparable[] a)
    {
        for(int i = 1; i < a.length; i++) 
            if(less(a, i, i-1)) return false;
        return true;
    }
    
    /**
     * creates a string that shows an item's index and value in my normal format. 
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
     */
    public static String getItemStr(Comparable[] a, int index)
    {
        return "["+index+"]"+" = "+a[index];
    }

}
