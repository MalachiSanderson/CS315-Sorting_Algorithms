package SortingAlgos;

import java.util.Arrays;

public final class QuickSort extends SortAlgorithm
{
    /** 
     * This algorithm is similar to {@link MergeSort#sort(Comparable[]) merge sort}
     * in how it recursively works. Difference is that this one heavily realies on 
     * a workhorse function called {@link #partition(Comparable[], int , int) partition()}
     * <p>
     * Essentially, the algorithm grabs first element of array [TODO] 
     * <p>
     * xxx.
     * <p>
     * [TODO]
     * <p>
     * 
     * @param a array of comparable items you desire to sort.
    */
    public static void sort(Comparable[] a)
    {
        subSort(a, 0, a.length - 1);
    }
    

    /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
    */
    public static void sort(Comparable[] a, boolean printTrace)
    {
        subSort(a, 0, a.length - 1, printTrace);
    }


    // quicksort the subarray from a[lo] to a[hi]
    private static void subSort(Comparable[] a, int lo, int hi) 
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        subSort(a, lo, j-1);
        subSort(a, j+1, hi);
        assert Sort_Interface.isSorted(Arrays.copyOfRange(a,lo,hi));
    }
    /**
     * Advanced printing version of {@link #subSort(Comparable[], int, int)}
     * @param a
     * @param lo
     * @param hi
     * @param printTrace
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

        int ssBLow = (j+1);
        if(true)
        {
            if (hi > ssBLow)
                System.out.println("\nSubsort B:"+arrRangePrintStr(a, ssBLow, hi));
            else System.out.println("\nSubsort A: attempted with values -> lo = "+ssBLow+"; hi ="+hi+". ");
        }
        subSort(a, j+1, hi, printTrace);
        assert Sort_Interface.isSorted(Arrays.copyOfRange(a,lo,hi));
    }
    
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    /**
     * [TODO]
     * @param a
     * @param lo
     * @param hi
     * @return
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
            while (Sort_Interface.less(a[++i], v)) 
            {
                if (i == hi) break;
            }

            // find item on hi to swap
            //count going to the left of hi until you find a val greater than or equal to lo. Set this index to j
            while (Sort_Interface.less(v, a[--j])) 
            {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel(?)
            }

            // check if pointers cross
            if (i >= j) break;

            //swap the value that's less than lo with the value greater than/equal to lo.
            Sort_Interface.swap(a, i, j);
        }
        
        // swap lo with item at location of the value greater/equal to lo (j)
        Sort_Interface.swap(a, lo, j);

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
     */
    private static int partition(Comparable[] a, int lo, int hi, boolean printTrace) 
    {
        System.out.println("\tPartition called with range: "+arrRangePrintStr(a, lo, hi));
        System.out.println("\t\t*Current Array: ["+Sort_Interface.getArrayString(a)+"]*\n");
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        int loopCount = 1;
        while (true) 
        {
            // find item on lo to swap
            while (Sort_Interface.less(a[++i], v)) 
            {
                if (i == hi) break;
            }
            if(printTrace)System.out.println("\t\t[Loop "+ loopCount+"] item less than "+Sort_Interface.getItemStr(a, lo)+" at i ="+ Sort_Interface.getItemStr(a, i));
            // find item on hi to swap
            while (Sort_Interface.less(v, a[--j])) 
            {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }
            if(printTrace)System.out.println("\t\t[Loop "+ loopCount+"] item greater/equal to "+Sort_Interface.getItemStr(a, lo)+" at j ="+ Sort_Interface.getItemStr(a, j));
            
            // check if pointers cross
            if (i >= j)
            {
                if(printTrace)System.out.println("\t\t\t[Loop "+ loopCount+"][ERR] Pointers cross --> i >= j");
                break;
            } 
            String str = ("\t\t[Loop "+ loopCount+"] i = " + Sort_Interface.getItemStr(a, i)+ " ...SWAPPED WITH j ="+ Sort_Interface.getItemStr(a, j));
            Sort_Interface.swap(a, i, j);
            str += " ->new arr= {"+Sort_Interface.getArrayString(a)+"}";
            System.out.println(str);
            loopCount++;
        }
        String str = ("\t\t[EXITED LOOP @ "+ loopCount+"] lo = " + Sort_Interface.getItemStr(a, lo)+ " ...SWAPPED WITH j ="+ Sort_Interface.getItemStr(a, j));
        // put partitioning item v at a[j]
        Sort_Interface.swap(a, lo, j);
        str += " ->new arr= {"+Sort_Interface.getArrayString(a)+"}\n\t\tRETURNING j = " +j+"\n";
        System.out.println(str);
        
        
        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static String arrRangePrintStr(Comparable[] a, int lo, int hi)
    {
        String str = "["+lo+"->"+hi +"]:{"; 
        if(lo != hi) str += Sort_Interface.getArrayString(Arrays.copyOfRange(a,lo,hi))+"}"; 
        else str += a[hi].toString() + " }";
        return str;
    }
    

}
