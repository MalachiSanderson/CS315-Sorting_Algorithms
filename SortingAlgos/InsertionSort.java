package SortingAlgos;
/**[TODO] explain what it does and have list with links to all methods and for each method put author.
 * @author Malachi Sanderson.
 * @since 10-19-22
 * 
*/
public class InsertionSort extends SortAlgo 
{
    /**
     * <p>
     * Main Loop starts at i = 1 -> i = length (arr = {0,1,2,3} --> 4) (note: means i[4] = out of bounds)
     * <p>
     * Inner loop starts at j = i -> (j = 0 AND found j less than j-1.)
     * <p>
     * Inner loop keeps iterating j--, to the left of i buffer 
     * until it reaches index 0 or does not find that j is less than the value to its left.
     * It swaps every step of inner loop.
     * 
     * @param a array of comparable items you desire to sort.
     * @author https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Insertion.java
     */
    public static void sort(Comparable[] a) 
    {
        // sort a[] into increasing order...
        int N = a.length;
        for (int i = 1; i < N; i++) 
        {
            // insert a[i] among a[i-1], a[i-2], a[i-3].....
            for (int j = i; j > 0 && SortAlgo.less(a, j, j - 1); j--)
                SortAlgo.swap(a, j, j - 1);

        }
    }
     /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
     */
    public static void sort(Comparable[] a, boolean printTrace) 
    {
        int i = 1;
        // sort a[] into increasing order...
        int N = a.length;
        for (i = 1; i < N; i++) 
        {
            System.out.println("I = " + i + "...............");
            if (printTrace) System.out.println("\t\t\t{"+SortAlgo.getArrayString(a)+"}");
            // insert a[i] among a[i-1], a[i-2], a[i-3].....
            int j = i;
            if(printTrace) System.out.println("\t\t\tJ START VALUE J = " + SortAlgo.getItemStr(a, j));
            for (j = i; j > 0 && SortAlgo.less(a, j, j - 1); j--) 
            {
                System.out.println("\t\tJ = " + SortAlgo.getItemStr(a, j)+ " ... SWAPPED WITH "+ SortAlgo.getItemStr(a, j-1)+ "");
                SortAlgo.swap(a, j, j - 1);
                if (printTrace) System.out.println("\t\t\t["+SortAlgo.getArrayString(a)+"]");
            }
            //Reached point where J <= 0 or J greater than all elements to its left.
            if(printTrace) System.out.println("\t\t\tJ FINAL VALUE J = " + SortAlgo.getItemStr(a, j));
        }
        if(printTrace) System.out.println("Final I = " + i + "...............");
    }
}
