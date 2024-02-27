/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 4
 */
import java.util.*;
import java.lang.*;

@SuppressWarnings("unchecked")
public class Sort <E extends Comparable<E>>
{
   private int[]counts1 = new int[6];
   private int[]counts2 = new int[2];
   private ArrayList<Integer> mergeCounts = new ArrayList<>();
   private ArrayList<Integer> quickCounts = new ArrayList<>();
      
   public Sort(E[]list){
      counts1[0] = selectionSort(list);
      counts1[1] = insertionSort(list);
      counts1[2] = bubbleSort(list);
      counts1[3] = getMergeCounts(list);
      counts1[4] = getQuickCount(list);
      counts1[5] = heapSort(list);
   }
   
   public Sort(Integer[]list,int disting){
      int dis = disting;
      counts2[0] = bucketSort(list,1000);
      counts2[1] = radixSort(list);
   }
   
   public int[] getCounts1(){
      return counts1;
   }
   
   public int[] getCounts2(){
      return counts2;
   }
   
   public int getMergeCounts(E[]list){
      mergeSort(list);
      int sum = 0;
      for(int i=0;i< mergeCounts.size();i++){
         sum += mergeCounts.get(i);
      } 
      return sum;
   }
      
   public int getQuickCount(E[]list){
      quickSort(list);
      int sum = 0;
      for(int i=0;i< quickCounts.size();i++){
         sum += quickCounts.get(i);
      } 
      return sum;
   }
   
   //selection sort
   public int selectionSort(E[] list){
      int count =0;
      int minIndex;
      for (int i=0; i<list.length-1; i++){
         count++;
         int[] tempArr = findMin(list, i);
         minIndex = tempArr[0];
         count += tempArr [1];
         
         //swap
         E temp = list[i];
         list[i] = list[minIndex];
         list[minIndex] = temp;
      }
      return count;
   }
   
   public int[] findMin(E[] list, int startIndex){
      int[]temp = new int[2];
      int co = 0;
      E min = list[startIndex]; 
      int index = startIndex;
      for (int i=startIndex; i<list.length; i++){
         co++;
         if (list[i].compareTo(min) < 0){
            min = list[i];
            index = i;
         }
      }
      temp[0] = index;
      temp[1] = co;
      return temp;
   }

   //insertion sort
   public int insertionSort(E[] list) {
      int count = 0;
      for (int i = 1; i < list.length;i++){
         count++;
         count += insert(list, i);
      }
      return count;
   }
   
   public int insert(E[] list, int i){
      int co = 0;
      E currentVal = list[i];
      while(i>0 && currentVal.compareTo((list[i-1]))<0){
         co++;
         list[i]=list[i-1];
         i--;
      }
      list[i]=currentVal;
      return co;
   }
   
   //bubble sort
   public int bubbleSort(E[] list) {
      int count = 0;
      int size = list.length; 
      for (int i = 0; i < size-1; i++) 
         for (int j = 0; j < size-i-1; j++){ 
            count++;
            if (list[j].compareTo(list[j+1])>0) {  
               E temp = list[j]; // swap
               list[j] = list[j+1]; 
               list[j+1] = temp; 
            }
         }       
      return count;
   }
   
   //merge sort
   public int mergeSort(E[]list) {
      int count = 0;
      if (list.length > 1) { // base case
         count++;
         Comparable[] firstHalf = new Comparable[list.length/2];
         Comparable[] secondHalf = new Comparable[list.length - list.length/2];
         System.arraycopy(list, 0, firstHalf, 0,list.length/2);
         System.arraycopy(list, list.length/2,secondHalf, 0,list.length-list.length/2);
         count += mergeSort((E[])firstHalf);
         count += mergeSort((E[])secondHalf);
         count += merge((E[])firstHalf, (E[])secondHalf, list);
      }
      mergeCounts.add(count); 
      return count;     
   }
   
   public int merge(E[]list1, E[]list2, E[]list) {
      int co = 0; 
      int list1Index = 0; 
      int list2Index = 0; 
      int listIndex = 0; 
      while( list1Index < list1.length && list2Index < list2.length){ 
         co++;
         if (list1[list1Index].compareTo(list2[list2Index])<0){
            list[listIndex++] = list1[list1Index++]; 
         }else{ 
            list[listIndex++] = list2[list2Index++]; 
         } 
      }
      while(list1Index < list1.length){
         co++;
         list[listIndex++] = list1[list1Index++];
      }
      while(list2Index < list2.length){
         co++;
         list[listIndex++] = list2[list2Index++]; 
      }
      return co;
   }
   
   //quick sort
   public void quickSort(E[] list){
      quickCounts.add(quickSort(list, 0, list.length-1));
   }  

   public int quickSort(E[] list,int first, int last){
      int count = 0;
      if (last > first){
         count ++;
         int[] temp = partition(list, first, last);
         int pivotIndex = temp[0];
         count += temp[1];
         count += quickSort(list, first, pivotIndex-1);
         count += quickSort(list, pivotIndex+1, last);
      }
      return count;
   }
   
   public int[] partition(E[] list,int first, int last){
      int[]temp = new int[2];
      E pivot;
      int co = 0;
      int index, smallIndex;
      pivot = list[first];// pivot is the first element
      smallIndex = first;
      for (index = first + 1;index <= last; index++){
         co++;
         if (list[index].compareTo(pivot)<0){
            smallIndex++;
            E e1 = list[smallIndex];
            list[smallIndex] = list[index];
            list[index] = e1;
         }}
         E e2 = list[first];
         list[first] = list[smallIndex];
         list[smallIndex] = e2;
      temp[0] = smallIndex;
      temp[1] = co;
      return temp;
   }
   
   //heap sort
   public int heapSort(E[] list){
      int count = 0;
      Heap<E> heap = new Heap<>(list);//add()
      for (int i=list.length-1; i>=0; i--) {
         count++;
         list[i] = heap.remove();
      }
      return count;
   }
   
   //bucket sort
   public int bucketSort(Integer[] list, int t) {
      int count = 0;
      ArrayList<ArrayList<Integer>> buckets;
      buckets = new ArrayList<>(t+1);
      for(int i=0; i<t+1; i++){
         count++;
         buckets.add(new ArrayList<>());// bucket i
      }
      //Distribute data on buckets
      for(int i=0; i<list.length; i++){
         count++;
         ArrayList<Integer> bucket = buckets.get(list[i]);
         bucket.add(list[i]);
         buckets.set(list[i], bucket);
      }
      // Move data from the buckets back to the list
      int k = 0;
      for(int i=0; i<buckets.size(); i++){
         ArrayList<Integer> bucket = buckets.get(i);
         for(int j=0; j<bucket.size(); j++){
            count++;
            list[k++] = bucket.get(j);
         }
      }
      return count;
   }
   
   //radix sort
   public int radixSort(Integer[] list) {
      int count = 0;
      ArrayList<ArrayList<Integer>> buckets;
      buckets = new ArrayList<>(10);// 10 buckets
      
      int[] temp =  max(list);
      Integer digits = temp[0];
      count += temp[1];
      digits = digits.toString().length();
      for(int i=0; i<digits; i++){
         for(int k=0; k<10; k++) { // create buckets for iteration I
            count ++;
            buckets.add(new ArrayList<>());
         }
         //Distribute data on buckets
         for(int j=0; j<list.length; j++){
            count++;
            int digit = list[i] % (10 * (i+1)) / (int)Math.pow(10,i);
            ArrayList<Integer> bucket = buckets.get(digit);
            bucket.add(list[i]);
            buckets.set(digit, bucket);
         }
         // Move data from the buckets back to the list
         int k = 0;
         for(int m=0; m<buckets.size(); m++){
            ArrayList<Integer> bucket = buckets.get(m);
            for(int j=0; j<bucket.size(); j++){
               count++;
               list[k++] = bucket.get(j);
            }
         }
         buckets.clear(); // for next iteration
      }
      return count;
   }
   
   public int[] max(Integer[] list){ 
      int[]temp = new int[2];
      int co = 0;
      int maxValue = list[0]; 
      for(int i=1;i < list.length;i++){
         co++; 
         if(list[i] > maxValue){ 
            maxValue = list[i]; 
         } 
      } 
      temp[0] = maxValue; 
      temp[1] = co;
      return temp;
   }
}