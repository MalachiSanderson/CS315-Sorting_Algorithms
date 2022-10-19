package SortingAlgos;

public final class SelectionSort extends SortAlgorithm
{
    /** 
     * <p>
     * Main Loop starts at i = 0 -> i = length (arr = {0,1,2,3} --> 4) (note: means i[4] = out of bounds)
     * <p>
     * Inner loop starts at j = i+1 -> j = length.
     * <p>
     * Inner loop keeps iterating until it reaches final element in array.
     * <p>
     * Inner loop just starts after setting min = i's current index (meaning i is buffer).
     * Then it loops until it finds a value to right of i buffer that is less than min (initially i),
     * Then when it finds a value less than min, it updates min to be this new value,then continues
     * iterating j (constantly comparing for new min) until it finds true minimum value to right of 
     * i buffer. Then it swaps current i index with min. Then it exits inner loop,
     * iterates i and repeats inner loop. 
     * <p>
     * Basically, start at index 0, find minimum and swap with index 0 with minimum. 
     * Then go to index 1, find next minimum to the right of index 0 and swap with index 1...etc.
     * @param a array of comparable items you desire to sort.
     */
    public static void sort(Comparable[] a)
    {
        //sort a[] into increasing order...
        int N = a.length;
        for(int i = 0; i < N; i++)
        {
            ///exchange a[i] with smallest entry in a[i+1...N]
            int min = i;
            for(int j = i+1; j < N; j++)
                if(Sort_Interface.less(a,j,min)) min = j;
            Sort_Interface.swap(a, i, min);
        }
    }
     /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
     */
    public static void sort(Comparable[] a, boolean printTrace)
    {
        //sort a[] into increasing order...
        int N = a.length;
        int i = 0;
        for( i= 0; i < N; i++)
        {
            int min = i;
            int oldMin = min;
            System.out.println("I = " + i + ".....(Start of Loop Min: ["+oldMin+"]="+a[oldMin]+")..........");
            if (printTrace) System.out.println("\t\t\t["+Sort_Interface.getArrayString(a)+"]");
            ///exchange a[i] with smallest entry in a[i+1...N]
            int j = i+1;
            if(printTrace && (j<N)) System.out.println("\t\t\tJ START VALUE J = [" + j + "] =" + a[j]);
            for(j = i+1; j < N; j++)
            {
                if(Sort_Interface.less(a,j,min)) 
                {
                    System.out.println("\t\tJ = [" + j +"] ="+ a[j]+ " ...SET TO NEW MIN (less than prev min: (["+min+"]="+a[min]+")");
                    min = j;
                }
                else System.out.println("\t\tJ = [" + j +"] ="+ a[j]+ " ...ignored. ");
            }
            if(oldMin != min) System.out.println("\t[SWAPPED [" + i +"] = "+ a[i]+ " with [" + min +"] = "+ a[min] + " ]...");
            Sort_Interface.swap(a, i, min);
            if(printTrace) System.out.println("\t\t\t["+Sort_Interface.getArrayString(a)+"]");
            if(printTrace) System.out.println("\t\t\tJ FINAL VALUE J = [" + j + "] " );
        }
        if(printTrace) System.out.println("Final I = " + i + "...............");
    }
}