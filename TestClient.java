import SortingAlgos.*;
public class TestClient implements TestInterface
{
    private enum algorithmTypEnum
    {
        BubbleSort,
        SelectionSort,
        InsertionSort,
        MergeSort,
        QuickSort
    }
    private static final int defaultRandomArrSize = 5;
    public static void main(String[] args) 
    {
        //sortTester(algorithmTypEnum.QuickSort,false);
        sortTester(algorithmTypEnum.QuickSort,true, new Integer[] {3, 12, 44, 99, 72, 33, 11, 18, 65, 42});
    }


    private static void sortTester(algorithmTypEnum algo, boolean printAdvanced)
    {
        System.out.println("\n\n-----------------------------\n\n "+algo.name()+" TEST\n\n-----------------------------");
        Comparable<Integer>[] arr = TestInterface.getRandomArray(defaultRandomArrSize);
        System.out.println("PRINTING NORMAL ARR: "+Sort_Interface.getArrayString(arr)+"\n-----------------------------");
        chooseAlgoToTest(algo,arr,printAdvanced);
        System.out.println("\n-----------------------------\nPRINTING SORTED ARR: "+Sort_Interface.getArrayString(arr));
        System.out.println("***********ALGORITHM WORKED? "+Sort_Interface.isSorted(arr)+"!!!***********\n");
    }
    private static void sortTester(algorithmTypEnum algo, boolean printAdvanced, Integer[] inputArr)
    {
        System.out.println("\n\n-----------------------------\n\n "+algo.name()+" TEST\n\n-----------------------------");
        Comparable<Integer>[] arr = inputArr;
        System.out.println("PRINTING NORMAL ARR: "+Sort_Interface.getArrayString(arr)+"\n-----------------------------");
        chooseAlgoToTest(algo,arr,printAdvanced);
        System.out.println("\n-----------------------------\nPRINTING SORTED ARR: "+Sort_Interface.getArrayString(arr));
        System.out.println("***********ALGORITHM WORKED? "+Sort_Interface.isSorted(arr)+"!!!***********\n");
    }
    




    private static void chooseAlgoToTest(TestClient.algorithmTypEnum algo, Comparable<Integer>[] arr, boolean printTrace) 
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
                    throw new Exception("\n\n\n\n\t\t[ERROR: CANNOT IDENTIFY ALGORITHM TO TEST]");
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
        }
    }

}
