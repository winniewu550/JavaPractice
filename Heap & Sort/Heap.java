/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 4
 */
import java.util.*;
 
public class Heap<E extends Comparable<E>>
{
   private ArrayList<E> list;
   
   public Heap(){
      list = new ArrayList<>();
   }
   
   public Heap(E[] data) {
      list = new ArrayList<>();
      for(int i=0; i<data.length; i++){
         add(data[i]);
      }
   }
   
   public void add(E item) {
      list.add(item); //append item to the heap
      int currentIndex = list.size()-1; //index of the last element
      while(currentIndex > 0) {
         int parentIndex = (currentIndex-1)/2;  //swap if current is greater than its parent
         E current = list.get(currentIndex);
         E parent = list.get(parentIndex);
         if(current.compareTo(parent) > 0) {
            list.set(currentIndex, parent);
            list.set(parentIndex, current);
         }
         else break; // the tree is a heap
         currentIndex = parentIndex;
      }
   }
   
   public E remove() {
      if(list.size() == 0) return null;
      E removedItem = list.get(0);
      list.set(0, list.get(list.size()-1));
      list.remove(list.size()-1);
      int currentIndex = 0;
      while (currentIndex < list.size()) {
         int left = 2 * currentIndex + 1;
         int right = 2 * currentIndex + 2;
      //find the maximum of the two children
         if (left >= list.size()) break;//reached the end
         int maxIndex = left;
         E max = list.get(maxIndex);
         if (right < list.size())
         if(max.compareTo(list.get(right)) < 0)
         maxIndex = right;
      // swap if current is less than the maximum
         E current = list.get(currentIndex);
         max = list.get(maxIndex);
         if(current.compareTo(max) < 0) {
            list.set(maxIndex, current);
            list.set(currentIndex, max);
            currentIndex = maxIndex;
         }
         else break; // the tree is a heap
      }
      return removedItem;
   }
   
   public static <E extends Comparable<E>> void heapSort(E[] list) {
      Heap<E> heap = new Heap<>(list);//add()
      for (int i=list.length-1; i>=0; i--) {
         list[i] = heap.remove();
      }
   }
}
   