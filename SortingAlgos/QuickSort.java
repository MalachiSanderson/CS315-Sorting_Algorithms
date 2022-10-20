package SortingAlgos;

import java.util.Arrays;

/**[TODO] explain what it does and have list with links to all methods and for each method put author.
 * 
 * 
 * @author Malachi Sanderson 
 * @author Most of core algo implementation ripped from https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Quick.java
 * @since 10-19-22
*/
public final class QuickSort extends SortAlgo
{
    /** 
     * This algorithm is similar to {@link MergeSort#sort(Comparable[]) merge sort}
     * in how it recursively works. Difference is that this one heavily realies on 
     * a workhorse function called {@link #partition(Comparable[], int , int) partition()}
     * <p>
     * Essentially, the algorithm grabs first element of array and keeps splitting it then 
     * <p>
     * xxx.
     * <p>
     * [TODO]
     * <p>
     * 
     * @param a array of comparable items you desire to sort.
     * @author Documentation: Malachi. 
     * @author Code: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Quick.java
    */
    public static void sort(Comparable[] a)
    {
        subSort(a, 0, a.length - 1);
    }
    /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
     * @author Malachi Sanderson
    */
    public static void sort(Comparable[] a, boolean printTrace)
    {
        subSort(a, 0, a.length - 1, printTrace);
    }

    /**
     * Recursive part of {@link #sort(Comparable[])}
     * @param a
     * @param lo
     * @param hi
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Quick.java
     */
    private static void subSort(Comparable[] a, int lo, int hi) 
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        subSort(a, lo, j-1);
        subSort(a, j+1, hi);
        assert SortAlgo.isSorted(Arrays.copyOfRange(a,lo,hi));
    }
    /**
     * Advanced printing version of {@link #subSort(Comparable[], int, int)}
     * @param a
     * @param lo
     * @param hi
     * @param printTrace
     * @author Malachi Sanderson (but was built off of {@link #subSort(Comparable[], int, int)})
     */
    private static void subSort(Comparable[] a, int lo, int hi, boolean printTrace) 
    {
        if (hi <= lo)
        {
            if(printTrace)System.out.println("\t\tsubsort failed...");
            return;
        } 
        
        int j = partition(a, lo, hi, printTrace);

        int ssAHi = (j-1);
        if(true)
        {
            if (ssAHi > lo)
                System.out.println("\nSubsort A:"+arrRangePrintStr(a, lo, ssAHi));
            else System.out.println("\nSubsort A: attempted with values -> lo = "+lo+"; hi ="+ssAHi+". ");
        }
        subSort(a, lo, j-1, printTrace);

        int ssBlo = (j+1);
        if(true)
        {
            if (hi > ssBlo)
                System.out.println("\nSubsort B:"+arrRangePrintStr(a, ssBlo, hi));
            else System.out.println("\nSubsort A: attempted with values -> lo = "+ssBlo+"; hi ="+hi+". ");
        }
        subSort(a, j+1, hi, printTrace);
        assert SortAlgo.isSorted(Arrays.copyOfRange(a,lo,hi));
    }
    
    /**
     * <i>"partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
     * and return the index j."</i>
     * [TODO]
     * @param a
     * @param lo
     * @param hi
     * @return
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Quick.java
     */
    private static int partition(Comparable[] a, int lo, int hi) 
    {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) 
        {

            // find item on lo to swap
            //count going to the right of lo until you find a value less than lo. Set this index to i.
            while (SortAlgo.less(a[++i], v)) 
            {
                if (i == hi) break;
            }

            // find item on hi to swap
            //count going to the left of hi until you find a val greater than or equal to lo. Set this index to j
            while (SortAlgo.less(v, a[--j])) 
            {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel(?)
            }

            // check if pointers cross
            if (i >= j) break;

            //swap the value that's less than lo with the value greater than/equal to lo.
            SortAlgo.swap(a, i, j);
        }
        
        // swap lo with item at location of the value greater/equal to lo (j)
        SortAlgo.swap(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    /**
     * identical to {@link #partition(Comparable[], int, int)}, but just 
     * supports advanced printing.
     * @param a
     * @param lo
     * @param hi
     * @param printTrace
     * @return
     * @author Malachi Sanderson (but was built off of {@link #partition(Comparable[], int, int)} )
     */
    private static int partition(Comparable[] a, int lo, int hi, boolean printTrace) 
    {
        System.out.println("\tPartition called with range: "+arrRangePrintStr(a, lo, hi));
        System.out.println("\t\t*Current Array: ["+getArrayString(a)+"]*\n");
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        int loopCount = 1;
        while (true) 
        {
            // find item on lo to swap
            while (SortAlgo.less(a[++i], v)) 
            {
                if (i == hi) break;
            }
            if(printTrace)System.out.println("\t\t[Loop "+ loopCount+"] item less than "+getItemStr(a, lo)+" at i ="+ getItemStr(a, i));
            // find item on hi to swap
            while (SortAlgo.less(v, a[--j])) 
            {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }
            if(printTrace)System.out.println("\t\t[Loop "+ loopCount+"] item greater/equal to "+getItemStr(a, lo)+" at j ="+ getItemStr(a, j));
            
            // check if pointers cross
            if (i >= j)
            {
                //if(printTrace)System.out.println("\t\t\t[Loop "+ loopCount+"][ERR] Pointers cross --> i >= j");
                break;
            } 
            String str = ("\t\t[Loop "+ loopCount+"] i = " + getItemStr(a, i)+ " ...SWAPPED WITH j ="+ getItemStr(a, j));
            swap(a, i, j);
            str += " ->new arr= {"+getArrayString(a)+"}";
            System.out.println(str);
            loopCount++;
        }
        String str = ("\t\t[EXITED LOOP @ "+ loopCount+"] lo = " + getItemStr(a, lo)+ " ...SWAPPED WITH j ="+ getItemStr(a, j));
        // put partitioning item v at a[j]
        swap(a, lo, j);
        str += " ->new arr= {"+getArrayString(a)+"}\n\t\tRETURNING j = " +j+"\n";
        System.out.println(str);
        
        
        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    

}
