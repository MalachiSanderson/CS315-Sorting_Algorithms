import java.util.ArrayList;
import java.util.Scanner;
import SortingAlgos.*;

/** [TODO] explain what it does and have list with links to all methods and for each method put author.
 * 
 * @author Malachi Sanderson.
 * @since 10-19-22
*/
public class TestClient extends TestHelper
{
    
    private static final int defaultRandomArrSize = 5;
    /**
     * 
     * @param args
     * @author Malachi Sanderson
     */
    public static void main(String[] args) 
    {
        //sortTester(algorithmTypEnum.QuickSort,true);
        //sortTester(algorithmTypEnum.QuickSort,true, new Integer[] {3, 12, 44, 99, 72, 33, 11, 18, 65, 42});
        if(args.length>0) commandLineInputHandler(args);
        else commandLineInterface();
        System.out.println("\n\n--------------------------------------------------------------");
        System.out.println("\t\t\t\tGOODBYE!");
        System.out.println("--------------------------------------------------------------\n\n");
    }

    /**
     * Process to handle interactive command line input!
     * 
     * <p>
     * Note common input mapping and what {@link algorithmTypEnum sorting algorithm} they map to can be
     * found in {@link TestHelper#sortOptionsInputPairs}
     * <p>
     * <i> For good examples of how to do stuff like this look at: https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/</i>
     * @param args
     * @author Malachi Sanderson 
     */
    private static void commandLineInterface()
    {

        System.out.println("\n\n--------------------------------------------------------------\n\n");
        System.out.println("\t\t\t\tWELCOME TO MALACHI'S SORTING ALGORITHMS TEST CLIENT!");
        Scanner in = new Scanner(System.in);
        String inputStr;
        

        boolean printTrace = false;
        System.out.println("\n\t\tAre you going to want maximally detailed print tracing of your algorithm?");
        inputStr = in.nextLine().trim();
        if(inputStr.contains("t") ||inputStr.contains("T") ||inputStr.contains("rue") ||inputStr.contains("1")  ||inputStr.contains("Y") ||inputStr.contains("y")  ||inputStr.contains("es"))
        {
            printTrace = true;
        }
        System.out.println("\t\tYou selected "+printTrace+"...");

        System.out.println("\n\t\tPress ENTER to proceed and select a sorting algorithm!");
        inputStr = in.nextLine();

        System.out.println(SORTING_OPTIONS_STRING);
        algorithmTypEnum alg = null;
        while(alg == null)
        {
            inputStr = in.nextLine().trim();
            if(inputStr.contains("x") || inputStr.contains("X")) 
            {
                System.out.println("\n\t\t\tDETECTED AN X! EXITING PROGRAM!!!!");
                return;
            }
            alg = searchForSortType(inputStr);
            if(alg == null) 
            {
                System.out.println("\n\t\t\t[ERROR! - Bad Input: input "+inputStr+" is not a registered input!]\n\t...Press ENTER To retry.");
                inputStr = in.nextLine();
                System.out.println(SORTING_OPTIONS_STRING);
            }
            else 
            {
                System.out.println("\t\tYou selected "+alg.name()+"...");
                break;
            }
        }
        System.out.println("\t\tTo input an integer array to sort press ENTER, then type your first integer!\n\t\t\tBUT, if you want it to be randomized "+defaultRandomArrSize+" elements, type 'r' then press ENTER...");
        inputStr = in.nextLine();
        if(inputStr.contains("r") || inputStr.contains("R")) 
        {
            sortTester(alg,printTrace); 
        }
        else
        {
            System.out.println("\t\tBegin Inputting integer values for your array... ");
            Integer[] inputArr;
            ArrayList<Integer> inputIntList = new ArrayList<Integer>();
            while(true)
            {
                Integer acceptedVal =0;
                int enteredVal = 0;
                inputStr = in.nextLine().trim();
                if(inputStr.length()==0)
                {
                    System.out.println("\t\t\tDetected empty input (" + inputStr + ") so \n\t\t\tending array input and BEGINING ARR SORT: " +inputIntList.toString() + "...\n");
                    inputArr = new Integer[inputIntList.size()];
                    int index = 0;
                    for(Integer i : inputIntList)inputArr[index++] = i; //copy each item from list to arr
                    sortTester(alg,printTrace, inputArr);
                    break;
                }
                try 
                {
                    enteredVal = Integer.parseInt(inputStr);
                    acceptedVal = (Integer) enteredVal;
                } 
                catch (java.util.InputMismatchException e) 
                {
                    System.out.println("\n\t\t\t[ERROR! - Bad Input: input "+inputStr+" is not of type Integer!]\n\t...Press ENTER To retry.");
                    inputStr = in.nextLine();
                    System.out.println("\t\tEnter an integer to add to end of list... or Press Enter to try and sort current list:"+inputIntList.toString()+"...");
                    continue;
                }
                catch (NumberFormatException e1) 
                {
                    System.out.println("\n\t\t\t[ERROR! - Bad Input: input "+inputStr+" is not of type Integer!]\n\t...Press ENTER To retry.");
                    inputStr = in.nextLine();
                    System.out.println("\t\tEnter an integer to add to end of list... or Press Enter to try and sort current list:"+inputIntList.toString()+"...");
                    continue;
                }
                catch(Exception bigIssue)
                {
                    System.out.println("\n\n\t\t\tUNEXPECTED EXCEPTION TRIGGERED! EXITING PROGRAM!!!!\n\n");
                    bigIssue.printStackTrace();
                    return;
                }
                inputIntList.add((Integer)acceptedVal);
                System.out.println("\t\tAppended " + acceptedVal + " to list " +inputIntList.toString() + "...\n");
                System.out.println("\t\tEnter an integer to add to end of list... or Press ENTER to try and sort current list:"+inputIntList.toString()+"...");

                
            }
        }
       
        System.out.println("\n\t\t\tCOMPLETED SORT! Restarting Program...");
        commandLineInterface();
        
    }

    /**
     * This is to be called in main, instead of {@link #commandLineInterface()}, if main detects that 
     * it was called with command line parameters.
     * 
     * @param args
     * @author Malachi Sanderson
     */
    private static void commandLineInputHandler(String[] args)
    {

        boolean printTrace = false;
        algorithmTypEnum alg = null;
        int index = 0;
        String str="";

        for(String i : args) str+=args[index++];
        String[] strArr = str.split(";");
        if(strArr.length < 2) 
        {
            System.out.println("\n\t[ERROR: Unacceptable input for sort test. Requires at least 2 diff sections.] \n\tCommand Line Parameters: (req) String - name of desired sorting algo';' (req) String - do you want max print detail? (y/n)';' (optional) Int[] - sequenced list of ints you want to sort (commma separated)"
            + "\n\tEx 1: merge sort; yes; 2, 0, 7, 27 "+"\n\tEx 2: q; t");
            return;
        }

        String sortAlgoSearch = "";
        String printTraceStr = "";
        try 
        {
            sortAlgoSearch = strArr[0];
            printTraceStr = strArr[1];
            alg = searchForSortType(sortAlgoSearch);
            if(alg == null) 
            {
                System.out.println("\n\t\t\t[ERROR! - Bad Input: input "+sortAlgoSearch+" is not a known reference to a sorting algorithm!]\n\t...quitting");
                return;
            }
            else 
            {
                System.out.println("\t\tYou selected "+alg.name()+"...");
            }
            if(printTraceStr.contains("t") ||printTraceStr.contains("T") ||printTraceStr.contains("rue") ||printTraceStr.contains("1")  ||printTraceStr.contains("Y") ||printTraceStr.contains("y")  ||printTraceStr.contains("es"))
            {
                printTrace = true;
            }
            
            
        } 
        catch (Exception e) 
        {
            System.out.println("\n\t[ERROR: Unacceptable input for sort test]. \n\tFormat Parameters in 2 (optional 3) sections, separated by semicolons (;) with optional int array separated by commas."+ 
            "\n\tEx 1: merge sort; yes; 2, 0, 7, 27 "+"\n\tEx 2: q; t");
            e.printStackTrace();
            return;
        }
        System.out.println("\t\tSelected detailed print: "+printTrace+"...");

        if(strArr.length > 2)
        {
            String[] tmpStr=strArr[2].trim().split(",");

            Integer[] intArr = new Integer[tmpStr.length];
            String printableArr ="";
            index = 0;
            try 
            {
                for(String i : tmpStr) 
                {
                    printableArr+= tmpStr[index].trim();
                    intArr[index] = Integer.parseInt(tmpStr[index++].trim());
                }
                sortTester(alg,printTrace, intArr);
                return;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("\n\t\t\t[ERROR! - Bad Array Input: "+printableArr+" is not able to be parsed to Integers!]\n\t...quitting");
                return;
            }
            catch (Exception e1) {e1.printStackTrace(); return;}
        }
        else sortTester(alg,printTrace);
    }

    /**
     * Basic function that tests and prints results of a sorting algorithm. 
     * By default test runs on a randomized INTEGER array of size {@link #defaultRandomArrSize}.
     * @param algo what type of sorting algo do you want to use?
     * @param printAdvanced want to have maximally detailed print?
     * @author Malachi Sanderson
     */
    private static void sortTester(TestHelper.algorithmTypEnum algo, boolean printAdvanced)
    {
        System.out.println("\n\n-----------------------------\n\n "+algo.name()+" TEST\n\n-----------------------------");
        Comparable<Integer>[] arr = TestHelper.getRandomArray(defaultRandomArrSize);
        System.out.println("PRINTING NORMAL ARR: "+SortAlgo.getArrayString(arr)+"\n-----------------------------");
        chooseAlgoToTest(algo,arr,printAdvanced);
        System.out.println("\n-----------------------------\nPRINTING SORTED ARR: "+SortAlgo.getArrayString(arr));
        System.out.println("***********ALGORITHM WORKED? "+SortAlgo.isSorted(arr)+"!!!***********\n");
    }

    /**
     * Same as {@link #sortTester(algorithmTypEnum, boolean)} except it takes an inputted integer array and tests that instead of
     * a randomized array.
     * @param algo
     * @param printAdvanced
     * @param inputArr
     * @author Malachi Sanderson
     */
    private static void sortTester(TestHelper.algorithmTypEnum algo, boolean printAdvanced, Integer[] inputArr)
    {
        System.out.println("\n\n-----------------------------\n\n "+algo.name()+" TEST\n\n-----------------------------");
        Comparable<Integer>[] arr = inputArr;
        System.out.println("PRINTING NORMAL ARR: "+SortAlgo.getArrayString(arr)+"\n-----------------------------");
        chooseAlgoToTest(algo,arr,printAdvanced);
        System.out.println("\n-----------------------------\nPRINTING SORTED ARR: "+SortAlgo.getArrayString(arr));
        System.out.println("***********ALGORITHM WORKED? "+SortAlgo.isSorted(arr)+"!!!***********\n");
    }

    /**
     * Runs specified algorithm (identfied by relation to {@link algorithmTypEnum}) on a passed array.
     * <p>
     * If passed algo is not one defined in {@link algorithmTypEnum} throws an exception.
     * @param algo pass enum to a specific algorithm 
     * @param arr array you wish to sort using specified algo.
     * @param printTrace do you want maximally detailed print?
     * @author Malachi Sanderson
     */
    public static void chooseAlgoToTest(algorithmTypEnum algo, Comparable<Integer>[] arr, boolean printTrace) 
    {
        switch (algo) 
        {
            case BubbleSort:
                BubbleSort.sort(arr,printTrace);
                break;
            case SelectionSort:
                SelectionSort.sort(arr,printTrace);
                break;
            case InsertionSort:
                InsertionSort.sort(arr,printTrace);
                break;
                
            case MergeSort:
                MergeSort.sort(arr,printTrace);
                break;
                
            case QuickSort:
                QuickSort.sort(arr,printTrace);
                break;
            
        
            default:
                try 
                {
                    throw new Exception("\n\n\n\n\t\t[ERROR: CANNOT IDENTIFY "+algo.name()+"AS AN ALGORITHM TO TEST]");
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
        }
    }
    





    /**
     * Just a basic string that holds a formatted string for printing the 
     * options/default input the user has to select from when selecting a {@link algorithmTypEnum sorting algo}.
     * @author Malachi Sanderson
     */
    private static final String 
    SORTING_OPTIONS_STRING = 
            "\n\t\t\t"+
            "Sort Options..."       +"\n\t\t"+
        "Input:    |     Output: "  +"\n\t\t"+
        "__________|_____________"  +"\n\t\t"+
        "b         |    Bubble Sort"  +"\n\t\t"+
        "s         |   Selection Sort"  +"\n\t\t"+
        "i         |  Insertion Sort"  +"\n\t\t"+
        "m         |    Merge Sort"  +"\n\t\t"+
        "q         |  Quick Sort"  +"\n\t\t"+
        "x         |    [Quit  Program]   \n"
    ;

}
