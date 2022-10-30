package SortingAlgos;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**[TODO] explain what it does and have list with links to all methods and for each method put author.
 * @author Malachi Sanderson.
 * @since 10-19-22
 * 
*/
public final class SelectionSort extends SortAlgo
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
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Selection.java
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
                if(SortAlgo.less(a,j,min)) min = j;
            SortAlgo.swap(a, i, min);
        }
    }
     /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
     * @author Malachi Sanderson
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
            if (printTrace) System.out.println("\t\t\t["+SortAlgo.getArrayString(a)+"]");
            ///exchange a[i] with smallest entry in a[i+1...N]
            int j = i+1;
            if(printTrace && (j<N)) System.out.println("\t\t\tJ START VALUE J = [" + j + "] =" + a[j]);
            for(j = i+1; j < N; j++)
            {
                if(SortAlgo.less(a,j,min)) 
                {
                    System.out.println("\t\tJ = [" + j +"] ="+ a[j]+ " ...SET TO NEW MIN (less than prev min: (["+min+"]="+a[min]+")");
                    min = j;
                }
                else System.out.println("\t\tJ = [" + j +"] ="+ a[j]+ " ...ignored. ");
            }
            if(oldMin != min) System.out.println("\t[SWAPPED [" + i +"] = "+ a[i]+ " with [" + min +"] = "+ a[min] + " ]...");
            SortAlgo.swap(a, i, min);
            if(printTrace) System.out.println("\t\t\t["+SortAlgo.getArrayString(a)+"]");
            if(printTrace) System.out.println("\t\t\tJ FINAL VALUE J = [" + j + "] " );
        }
        if(printTrace) System.out.println("Final I = " + i + "...............");
    }
    /**
     * {@link #sort(Comparable[])} but prints timing.
     * @param a
     * @param starts input Instant.now() or just input null and it should be fine.
     * @return string of the time taken
     * @author Malachi Sanderson
     * @since 10-29-22
     */
    public static String sort(Comparable[] a, int digits)
    {
        Instant starts = null;
        starts = Instant.now();
        //Thread.sleep(10);
        
        //sort a[] into increasing order...
        int N = a.length;
        for(int i = 0; i < N; i++)
        {
            ///exchange a[i] with smallest entry in a[i+1...N]
            int min = i;
            for(int j = i+1; j < N; j++)
                if(SortAlgo.less(a,j,min)) min = j;
            SortAlgo.swap(a, i, min);
        }
        Instant ends = Instant.now();
        long milis = Duration.between(starts, ends).toMillis() % 1000;
        long seconds = Duration.between(starts, ends).minus(milis, ChronoUnit.MILLIS).getSeconds();
        long nanos = Duration.between(starts, ends).minus(seconds, ChronoUnit.SECONDS).minus(milis, ChronoUnit.MILLIS).getNano();

        //System.out.println("\tTotal SORTING TIME: "+Duration.between(starts, ends) +"\n\tSeconds: "+ seconds+ "\n\tMiliSecs: "+ milis+"\n\tNanosecs: "+nanos );
        return "Total SORTING TIME: "+Duration.between(starts, ends) +"    Seconds: "+ seconds+ "    MiliSecs: "+ milis+"    Nanosecs: "+nanos;
        //Duration.between(starts, ends).toNanos()

    }
        
}
