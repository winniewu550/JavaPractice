/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 4
 */
import java.util.*;
 
public class Testing
{
   @SuppressWarnings("unchecked")
   public static void main (String[] args) {
      //random
      Integer[]randomList = new Integer[10000];
      for(int i=0;i<randomList.length;i++){
         randomList[i] = (int)(Math.random()*1000);
      } 
            
      Sort random1 = new Sort(randomList);
      int[]randomCounts1 = random1.getCounts1();
      Sort random2 = new Sort(randomList,0);
      int[]randomCounts2 = random2.getCounts2();
   
   
      //sorted
      Integer[]sortedList = new Integer[10000];
      for(int i=0;i<sortedList.length;i++){
         sortedList[i] = randomList[i];
      }
      Arrays.sort(sortedList);
      
      Sort sorted1 = new Sort(sortedList);
      Sort sorted2 = new Sort(sortedList,0);
      int[]sortedCounts1 = sorted1.getCounts1();
      int[]sortedCounts2 = sorted2.getCounts2();

      //inverted
      Integer[]invertedList = new Integer[10000];
      for(int i=0;i<invertedList.length;i++){
         invertedList[i] = randomList[i];
      }
      Arrays.sort(invertedList,Collections.reverseOrder());

      Sort inverted1 = new Sort(invertedList);
      int[]invertedCounts1 = inverted1.getCounts1();
      Sort inverted2 = new Sort(invertedList,0);
      int[]invertedCounts2 = inverted2.getCounts2();
      
      //names of sorting methods
      String[]sortingNames = new String[]{"Selection Sort","Insertion Sort","Bubble Sort",
                                          "Merge Sort","Quick Sort","Heap Sort","Bucket Sort",
                                          "Radix Sort"};
      
      //     
      System.out.println("Comparing Sorting Algorithms for data set with 10000 integers\n");
      System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t","Sorting","Random","Sorted","Inverted");
      System.out.println();
      
      for(int i=0;i<6;i++){
         System.out.printf("%-20s\t%-20d\t%-20d\t%-20d\t",sortingNames[i],randomCounts1[i],
                                                          sortedCounts1[i],invertedCounts1[i]);
         System.out.println();
      }
      
      System.out.printf("%-20s\t%-20d\t%-20d\t%-20d\t","Bucket Sort",randomCounts2[0],
                                                       sortedCounts2[0],invertedCounts2[0]);
      System.out.println();

      System.out.printf("%-20s\t%-20d\t%-20d\t%-20d\t","Radix Sort ",randomCounts2[1],
                                                       sortedCounts2[1],invertedCounts2[1]);
   }
}

/*selection sort: in Iteration k (outer loop),
                 (n-k+1) iterations (inner loop)
/*insertion sort:It always takes n-1 iterations to build the final list.
/*bubble sort:Iteration k (outer loop)
              (n-k) iterations (inner loop)
              iteration = n(n-1)/2

It seems thta the insertion sort runs fastest in this case.
Only the iteration number of radix sort is affected by the original sort of arrays input; 
the number of interations of other sorts is only related to the number of elements in the input array.
If the array is already in ascending or descending order, fewer iterations are needed in radix sort.
*/
 