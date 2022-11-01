package SortingAlgos;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * [TODO] explain what it does and have list with links to all methods and for each method put author.
 * @author Malachi Sanderson.
 * @since 10-19-22
*/
public final class MergeSort extends SortAlgo
{
    /**
     * These variables are used only for printing and have no impact on the algorithm...
     */
    //#region PRINT VARIABLES
    private static int splitCalls = 0;
    private static int failedSplitCalls = 0;
    private static int mergeCalls = 0;
    //#endregion

    /** 
    * <i>"Rearranges the array in ascending order, using the natural order."</i>
    * <p>
    * Basic structure is split into two main functions:
    * <p>
    * 1. A {@link #splitArray(Comparable[], Comparable[], int, int) recursive split} of
    * the passed array into two partitions until it cannot be split any further. 
    * <p>
    * 2. A {@link #merge(Comparable[], Comparable[], int, int, int) ordering and merging} of split
    * partitions into one new ordered list then returning up one layer of the recursive split.
    * <p>
    * 
    * @param a array of comparable items you desire to sort.
    * @author Malachi Sanderson
    * @since 10-19-22
    */
    public static void sort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        splitArray(a, aux, 0, a.length-1);
    }

    /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process. 
     * @param a 
     * @param printTrace
     * @author Malachi Sanderson
     * @since 10-19-22
     */
    public static void sort(Comparable[] a, boolean printTrace)
    {
        splitCalls = 0;
        failedSplitCalls = 0;
        mergeCalls = 0;
        Comparable[] aux = new Comparable[a.length];
        splitArray(a, aux, 0, a.length-1, printTrace);
        if(printTrace)System.out.println("\nNumber of times split Called vs times split Failed: "+splitCalls + " - "+ failedSplitCalls);
        if(printTrace)System.out.println("\nNumber of Merge Calls: "+mergeCalls);
        splitCalls = 0;
        failedSplitCalls = 0;
        mergeCalls = 0;
    }
    /**
     * {@link #sort(Comparable[])} but returns a string showing the sort timing so you can print it.
     * @param a
     * @param digits [TODO] this needs to be changed, currently does nothing but does help compiler recognize what sort() you're calling.
     * @return string of the time taken
     * @author Malachi Sanderson
     * @since 10-29-22
     * @version M2-4 Programming Assignment Deliverable 
     */
    public static String sort(Comparable[] a, int digits)
    {
        Instant starts = Instant.now();

        Comparable[] aux = new Comparable[a.length];
        splitArray(a, aux, 0, a.length-1);

        Instant ends = Instant.now();
        
        return getExecutionTimeString(starts, ends);
    }


    /**
    * <i>"mergesort a[lo..hi] using auxiliary array aux[lo..hi]"</i>
    * <p>
    * Recursively split a passed array until it cannot be split anymore, 
    * then, using {@link #merge(Comparable[], Comparable[], int, int, int) merge()},
    * sort and merge the split arrays.
    * <p>
    * @param a
    * @param aux
    * @param lo
    * @param hi
    * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Merge.java
    */
    private static void splitArray(Comparable[] a, Comparable[] aux, int lo, int hi) 
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //recursively splits arr in half (split along "mid") until you cant anymore
        splitArray(a, aux, lo, mid); 
        splitArray(a, aux, mid + 1, hi);
        //merge the two split arrays in a newly sorted order.
        merge(a, aux, lo, mid, hi);
    }
    /**
     * Same as {@link #splitArray(Comparable[], Comparable[], int, int)} but
     * supports advanced printing.
     * @param a
     * @param aux
     * @param lo
     * @param hi
     * @param iterations
     * @author Malachi Sanderson
     * @since 10-19-22
     */
    private static boolean splitArray(Comparable[] a, Comparable[] aux, int lo, int hi, boolean printTrace) 
    {
        splitCalls++;
        if (hi <= lo) 
        {
            //splitCalls--;
            failedSplitCalls++; 
            return false;
        }
        
        int mid = (int)(lo + (hi - lo) / 2);
        

        boolean splitPass = false;
        String passedString;
        splitPass = splitArray(a, aux, lo, mid, printTrace); 
        if(splitPass) passedString = "PASSED";
        else passedString = "FAILED";
        if(printTrace)System.out.println("\t\t\t"+splitCalls+"-th Split "+passedString+"- Part A.{"+getArrayString(Arrays.copyOfRange(a,lo,mid+1)) + "}\n");
        

        splitPass = false;
        splitPass = splitArray(a, aux, mid + 1, hi, printTrace);
        if(splitPass) passedString = "PASSED";
        else passedString = "FAILED";
        if(printTrace)System.out.println("\t\t\t"+splitCalls+"-th Split "+passedString+"- Part B.{"+getArrayString(Arrays.copyOfRange(a,mid+1,hi+1)) + "}\n");

        mergeCalls++;
        if(printTrace)System.out.println("\t*Current Array: {"+SortAlgo.getArrayString(a)+"}*");
        System.out.println("   --Split for merge #"+mergeCalls+" "+
        getMergeSortPrintString(true, a, aux, lo, mid, hi, printTrace));
        
        merge(a, aux, lo, mid, hi, printTrace);
        System.out.println("\t*Current Array: ["+getArrayString(a)+"]*\n");
        return true;
    }



    /** 
     * <i>"stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]"</i>
     * <p>
     * Merge is basically the actual sorting component of the {@link MergeSort}.
     * <p>
     * Copy array. Array is split into two partitions, <b>A</b> and <b>B</b>, with first 
     * item in partition B being at index mid + 1. 
     * <p>
     * Go through the loop comparing first item in of A and
     * first item in B. 
     * This will be used to decide what to place in each element of {@code a[]}
     * <p>
     * If curr element of A > B add curr B to tail of {@code a[]} (starting at first index 0), 
     * and increment to next element in B.
     * <p>
     * If curr element of A < B add curr A to tail of {@code a[]} (starting at first index 0), 
     * and increment to next element in A.
     * <p>
     * Otherwise if reached end of A partition, just add rest of B partition to tail of {@code a[]}.
     * <p>
     * Otherwise if reached end of B partition, just add rest of A partition to tail of {@code a[]}.
     * <p>
     * @param a passed array to merge
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Merge.java
    */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) 
    {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(Arrays.copyOfRange(a,lo,mid));
        assert isSorted(Arrays.copyOfRange(a, mid+1, hi));

        // copy to aux[]
        for (int k = lo; k <= hi; k++) 
        {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) 
        {
            if      (i > mid)              a[k] = aux[j++];             //if reached end of partition A: add B to tail.
            else if (j > hi)               a[k] = aux[i++];             //else if reached end of partition B: add A to tail.
            else if (less(aux,j, i)) a[k] = aux[j++];    //else if element in partition B less one in A: add B to tail.
            else                           a[k] = aux[i++];             //else: (should mean element in A is less than B) add A to tail.
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(Arrays.copyOfRange(a,lo,hi));
    }
    /**
     * Same method as {@link #merge(Comparable[], Comparable[], int, int, int)} 
     * but supporting advanced printing.
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     * @param iterations
     * @author Malachi Sanderson
     * @since 10-19-22
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, boolean printTrace) 
    {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(Arrays.copyOfRange(a,lo,mid));
        assert isSorted(Arrays.copyOfRange(a, mid+1, hi));

        //System.out.println("\t\t++ [Start] Merge - Iter " +iteration+" \n\t\t"+ getMergeSortPrintString(true, a, aux, lo, mid, hi, iteration));
        
        // copy to aux[]
        for (int k = lo; k <= hi; k++) 
        {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) 
        {
            if      (i > mid)              a[k] = aux[j++];             //if reached end of partition A: add B to tail.
            else if (j > hi)               a[k] = aux[i++];             //else if reached end of partition B: add A to tail.
            else if (less(aux,j, i)) a[k] = aux[j++];    //else if element in partition B less one in A: add B to tail.
            else                           a[k] = aux[i++];             //else: (should mean element in A is less than B) add A to tail.
        }

        String str = "   ++Merge #"+mergeCalls+" ||  "+ getMergeSortPrintString(true, a, aux, lo, mid, hi, printTrace); 
        if(printTrace) str+=" = {"+getArrayString(Arrays.copyOfRange(a,lo,hi+1)) + "}";
        
        System.out.println(str);    
        // postcondition: a[lo .. hi] is sorted
        assert isSorted(Arrays.copyOfRange(a,lo,hi));
    }

    /**
     * Just a helper function to help make my prints a little smaller.
     * @param partitionPrint
     * @param a
     * @param aux
     * @param lo
     * @param mid
     * @param hi
     * @param printTrace
     * @return 
     * @author Malachi Sanderson
     * @since 10-19-22
     */
    private static String getMergeSortPrintString(boolean partitionPrint,Comparable[] a, Comparable[] aux, int lo, int mid, int hi, boolean printTrace)
    {
        if(partitionPrint)
        {
            String str = "A["+lo+"->"+mid +"]:{"; 
            if(lo != mid) str += getArrayString(Arrays.copyOfRange(a,lo,mid+1))+"}"; //idk why, but had to do mid+1 for it to print correctly... 
            else str += a[mid].toString() + " ]";

            str+="   B["+(mid+1)+"->"+hi +"]:{" ;

            if((mid+1) == hi) str += a[hi].toString() + " }";
            else str += getArrayString(Arrays.copyOfRange(a,mid+1,hi+1))+"}";

           return str;
        }
        else
        return ("a["+lo+"->"+hi +"] :{" + getArrayString(Arrays.copyOfRange(a,lo,hi+1))+"}");
    }

}
