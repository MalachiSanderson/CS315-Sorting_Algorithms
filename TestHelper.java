import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import SortingAlgos.*;

/**[TODO] explain what it does and have list with links to all methods and for each method put author.
 * @author Malachi Sanderson.
 * @since 10-19-22
*/
public class TestHelper 
{
    /**
     * Generates an ascending order array from 0 to size - 1
     * @param size - size of array
     * @return ascending array
     * @author https://github.com/richss/SortingAlgorithms/blob/master/src/SortHelper.java
     */
    public static Integer[] getAscendingArray(int size) 
    {
        Integer [] arr = new Integer[size];
        for (int i=0; i < size; i++) arr[i] = i;
        return arr;
    }

    /**
     * Generates an array in descending order from size - 1 to 0
     * @param size - size of array
     * @return descending array
     * @author https://github.com/richss/SortingAlgorithms/blob/master/src/SortHelper.java
     */
    public static Integer [] getDescendingArray(int size) 
    {
        Integer [] arr = new Integer[size];
        for (int i=0; i < size; i++) arr[i] = (size - 1) - i;
        return arr;
    }


    /**
     * Generates an array of values from 0 to size - 1 that is
     * randomly shuffled.
     * @param size - size of the array
     * @return random array
     * @author https://github.com/richss/SortingAlgorithms/blob/master/src/SortHelper.java
     */
    public static Integer[] getRandomArray(int size) 
    {

        Integer [] arr = new Integer[size];

        for (int i=0; i < size; i++) arr[i] = (size - 1) - i;

        Collections.shuffle(Arrays.asList(arr));

        Integer[] outArr = new Integer[size];

        for (int i=0; i < size; i++) outArr[i] = arr[i];

        return outArr;
    }
    /**
     * Creates a string representation of an array
     * @param arr - array to cnvert to string
     * @return comma delimited string of array elements
     * @author https://github.com/richss/SortingAlgorithms/blob/master/src/SortHelper.java
     */
    public static String toArrayToString(Integer [] arr) 
    {
        String out = "";
        for (int i=0; i< arr.length; i++ ) 
        {
            out += Integer.toString(arr[i]);
            if (i != arr.length - 1) out += ", ";
        }
        return out;
    }
    
    /**
     * Enum that defines all types of PSAS's for arrays in performance experimentation...
     * <p>
     * 1. {@link #getRandomArray(int) Random}
     * <p>
     * 2. {@link #getAscendingArray(int) Ascending}
     * <p>
     * 3. {@link #getDescendingArray(int) Descending}
     * <p>
     * @author Malachi Sanderson
     * @since 10-29-22
     * @version M2-4 Programming Assignment Deliverable
     */
    public static enum ArraySortTypesEnum
    {
        Random,
        Ascending,
        Descending
    }

    /**
     * Works by utilizing {@link ArraySortTypesEnum}
     * @param preSortType type of presorting done on the array.
     * @param size size of the array you wish to create
     * @return an Integer[] array built to those specifications of the parameters. (Returns null on error.)
     * @author Malachi Sanderson
     * @since 10-29-22
     * @version M2-4 Programming Assignment Deliverable
     */
    public static Integer[] chooseArrayPresortedBy(ArraySortTypesEnum preSortType, int size)
    {
        switch (preSortType) 
        {
            case Random:
                return getRandomArray(size);
            case Ascending:
                return getAscendingArray(size);
            case Descending:
                return getDescendingArray(size);
            default:
                try 
                {
                    throw new Exception("\n\n\n\n\t\t[USER ERROR: CANNOT IDENTIFY "+preSortType.name()+" AS AN ALGORITHM TO TEST]");
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
        }
        return null;
    }


    //#region STUFF FOR COMMAND LINE INTERFACING
    
    /**
     * Enum that defines types of algorithms I have implemented...
     * <p>
     * 1. {@link BubbleSort}
     * <p>
     * 2. {@link SelectionSort}
     * <p>
     * 3. {@link InsertionSort}
     * <p>
     * 4. {@link MergeSort}
     * <p>
     * 5. {@link QuickSort}
     * <p>
     * @author Malachi Sanderson
     */
    public static enum algorithmTypEnum
    {
        BubbleSort,
        SelectionSort,
        InsertionSort,
        MergeSort,
        QuickSort
    }

    /**
     * NOTE!!!!: This is initialized when you call {@link #searchForSortType(String)}. Before that, it's empty.
     * <p>
     * static public attribute that holds the mapping between sorting algorithms and all 
     * viable inputs for each. 
     * <p>
     * The actual inputs mapped to each can be seen in this method's {@link #initializeInterfaceOptionsMapping() initialization method}
     * <p>
     * All sorting {@link algorithmTypEnum sorting algos} are tied to an enum. 
     * To easily try and get the sorting algo via a search term, use {@link #searchForSortType(String)}.
     * @author Malachi Sanderson
     */
    private static HashMap<String,algorithmTypEnum> sortOptionsInputPairs;
    
    //#region Attributes: Acceptable command line inputs for each algorithm 
    protected static final String[] bubSort_Inputs = {"1", "b","bubble sort","bubble","bub","bs"};
    protected static final String[] selectSort_Inputs = {"2","s", "selection sort", "selection", "sel", "ss", "select"};
    protected static final String[] insertSort_Inputs = {"3","i", "insertion sort", "insertion", "insert", "in", "ins", "is"};
    protected static final String[] mergeSort_Inputs = {"4", "m", "merge sort", "merge", "ms", "mer" };
    protected static final String[] quickSort_Inputs = {"5", "q", "quick sort", "qs", "quick" };
    //#endregion

    /**
     * Searches {@link #sortOptionsInputPairs predefined input dictionary} 
     * to see if input string matches anything the dictonary has as set as
     * a {@link algorithmTypEnum possible sorting algorithm type}.
     * <p>
     * To see what some acceptable inputs I arbitrarily defined as acceptable and what they 
     * map to check the {@link #initializeInterfaceOptionsMapping() method where I initialize the dictionary}.
     * @param s string search term we will search for in the list. 
     * <p>
     * <i>Note - case doesn't matter. This method converts all elements to lowercase.</i> 
     * @return an {@link algorithmTypEnum algorithm} if found, or null if none matched the search term.
     * @author Malachi Sanderson
    */
    public static algorithmTypEnum searchForSortType(String s)
    {
        sortOptionsInputPairs = initializeInterfaceOptionsMapping();
        for(String key : sortOptionsInputPairs.keySet())
        {
            if(s.equalsIgnoreCase(key))
            {
                //System.out.println("\n\t[SEARCH SORT OPTIONS USING "+s+" (matched "+key+") FOUND: "+sortOptionsInputPairs.get(key).name()+"]\n");
                return sortOptionsInputPairs.get(key);
            }
        }
        //System.out.println("\n\t[SEARCH SORT OPTIONS USING "+s+" FAILED TO FIND ANY MATCH]\n");
        return null;
    }
    
    /**
     * Just assigns each of the diff string inputs 
     * <p>
     * (defined by {@link #bubSort_Inputs}, {@link #selectSort_Inputs}, {@link #insertSort_Inputs}, {@link #mergeSort_Inputs},{@link #quickSort_Inputs} )
     * <p>
     * to their respective {@link algorithmTypEnum}. 
     * <p>
     * <i>Note - I decided what these inputs should be arbitrarily so if you want to see them check out the attributes mentioned above.</i>
     * @return initialized {@link sortOptionsInputPairs} containing acceptable search terms (that I arbitrarily defined in this method)
     * @author Malachi Sanderson
     * <p> <i>Info on how to work with Maps: https://www.geeksforgeeks.org/map-interface-java-examples/ </i>
     */
    private static HashMap<String,algorithmTypEnum> initializeInterfaceOptionsMapping()
    {
        HashMap<String,algorithmTypEnum> tmpMap = new HashMap<String,algorithmTypEnum>();
        tmpMap = new HashMap<String,algorithmTypEnum>();

        //1. Bubble Sort...
        for(String str : bubSort_Inputs) tmpMap.put(str, algorithmTypEnum.BubbleSort);

        //2. Selection Sort...
        for(String str : selectSort_Inputs) tmpMap.put(str, algorithmTypEnum.SelectionSort);
        
        //3. Insertion Sort...
        for(String str : insertSort_Inputs) tmpMap.put(str, algorithmTypEnum.InsertionSort);

        //4. Merge Sort...
        for(String str : mergeSort_Inputs) tmpMap.put(str, algorithmTypEnum.MergeSort);

        //5. Quick Sort...
        for(String str : quickSort_Inputs) tmpMap.put(str, algorithmTypEnum.QuickSort);

        return tmpMap;
    }

    //#endregion



}
