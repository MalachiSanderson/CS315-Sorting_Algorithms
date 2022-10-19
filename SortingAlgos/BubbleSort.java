package SortingAlgos;
public final class BubbleSort extends SortAlgorithm
{
    /**<p>
     * Main Loop starts at i = 0 -> i = length-1 (arr = {0,1,2,3} --> 3)
     * <p>
     * Inner loop starts at last element, counting backwards.
     * <p>
     * Inner loop keeps going until it reaches current buffer index i is sitting at.
     * <p>
     * Inner loop just loops thru each element to right of buffer, comparing if current 
     * j index is less than the element to left of it; and if so, swaps them and continues 
     * iterating left until reaching buffer.
     * 
     * @param a
     */
    public static void sort(Comparable[] a) 
    {
        boolean swapped;
        for(int i = 0; i < a.length-1; i++)
        {
            swapped = false;
            for(int j = a.length-1; j > i; --j)
            {
                if(Sort_Interface.less(a,j,j-1))
                {
                    Sort_Interface.swap(a,j,j-1);
                    swapped = true;
                }
            }
        }
    }
    /**
     * {@link #sort(Comparable[])} but with in-depth printing for tracing process.
     * @param a
     * @param printTrace
     */
    public static void sort(Comparable[] a, boolean printTrace)
    {
        int i = 0;
        boolean swapped;
        for(i = 0; i < a.length-1; i++)
        {
            System.out.println("I = " + i + "...............");
            if (printTrace) System.out.println("\t\t\t["+Sort_Interface.getArrayString(a)+"]");
            swapped = false;
            int j = a.length-1;
            if(printTrace) System.out.println("\t\t\tJ START VALUE J = [" + j + "] =" + a[j]);
            for(j = a.length-1; j > i; --j)
            {
                if(Sort_Interface.less(a,j,j-1))
                {
                    swapped = true;
                    if(printTrace) System.out.println("\tJ[" + j +"] = "+ a[j]+ " ....SWAPPED WITH: ["+(j-1) +"] = "+a[j-1]);
                    Sort_Interface.swap(a,j,j-1);
                }
                else if(printTrace)System.out.println("\tJ [" + j +"] = "+ a[j]+ " ....DID NOT SWAP!");
                if (printTrace) System.out.println("\t\t\t["+Sort_Interface.getArrayString(a)+"]");
            }
            if(printTrace) System.out.println("\t\t\tJ FINAL VALUE J = [" + j + "] =" + a[j]);
        }
        if(printTrace) System.out.println("Final I = " + i + "...............");
    }
}
