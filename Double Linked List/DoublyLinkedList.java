/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 3
 */

import java.util.*;
@SuppressWarnings("unchecked")

public class DoublyLinkedList<E> implements List<E>{
   private Node<E> head, tail;
   private int size;
   
   //O(1)
   public DoublyLinkedList() {
      head = tail = null;
      size = 0;
   }
   
   //O(n),elements'length 
   public DoublyLinkedList(E[] elements) {
      for(int i=0; i<elements.length; i++)
         add(elements[i]);
   }
   
   private class Node<E>{ //Inner class
      E value;
      Node<E> previous;//
      Node<E> next;
      
      Node(E item){ //constructor
      value = item;
      previous = null;
      next = null;
      }
   }
   
   //O(1)
   public int size() {
      return size;
   }
   
   //O（1）
   public E getFirst() {
      if(size == 0) 
         return null;
      else 
         return head.value;
   }
   
   //O（1）
   public E getLast() {
      if (size == 0)
         return null;
      else
         return tail.value;
   }
   
   //O(n)，search time:number of elements in the list
   public void addFirst(E item) {
      Node<E> newNode = new Node<E>(item);
      newNode.next = head;
      head = newNode;
      newNode.previous = null;
      size++;
      if (tail == null)//no element in the list at first
         tail = head;
   }
   
   //O(1) 
   public void addLast(E item) {
      Node<E> newNode = new Node<>(item);
      if(tail == null)//no element in the list at first
         head = tail = newNode;
      else {
         tail.next = newNode;
         newNode.previous = tail;//
         tail = newNode;
      }
      size++;
   }
   
   //O(n)，index - 1
   public void add(int index, E item) {
      if(index == 0)
         addFirst(item);
      else if (index >= size)
         addLast(item);
      else {
         Node<E> current = head;
         for(int i=0; i<index-1; i++){//find index
            current = current.next;//add at index, current:index - 2
         }
      Node<E> temp = current.next;//add before it
      Node<E> newNode = new Node<>(item);
      current.next = newNode;
      newNode.next = temp;
      newNode.previous = current;
      size++;
      }
   }
   
   //O(1)
   public E removeFirst() {
      if (size == 0) return null;
      Node<E> temp = head; head = head.next; size--;
      if(head == null) tail = null;
         return temp.value; //value of the removed E
   }
   
   //O（1）
   public E removeLast() {
      if (size == 0) return null;
      Node<E> temp = tail;
      if(head == tail) {
            head = tail = null; 
            size = 0;
      }
      else{
         Node<E>newTail = temp.previous;
         newTail.next = null;
         tail = newTail;
         size--;
      }
      return temp.value;//value of the removed E
   }
   
   //O(n), index-1
   public E remove(int index) {
      if (index < 0 || index >= size) return null;
      Node<E> temp;
      Node<E> current = head;
      for (int i=0; i<index-1; i++)
         current = current.next;
         temp = current.next;
         current.next = temp.next;
         Node<E>newNext = temp.next;
         newNext.previous = current;
         size--;
      return temp.value;
   }
   
   //O(1)
   public void clear() {
      size = 0;
      head = tail = null;
   }
   
   //O(n),equals size
   public String toString() {
      String output = "[";
      Node<E> current = head;
      for(int i=0; i<size; i++) {
         output += current.value;
         current = current.next;
      if (i < size-1)
         output += ", ";
      }
      output += "]";
      return output;
   }
   
   // O(1)
   private void checkIndex(int index) {
      if (index >= size || index < 0)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
   }
   
   //O(n),equals index
   public E get(int index) {
      if(size == 0)return null;
         checkIndex(index);
         Node<E> current = head;
      for(int i=0; i<index; i++)
         current = current.next;
         return current.value;
   }
   
   //O(n),equals index
   public E set(int index, E item) {
      if (index < 0 || index >= size)
         return null;
      Node<E> current = head;
      for (int i=0; i<index; i++)
         current = current.next;
         E temp = current.value;
         current.value = item;
      return temp;
   }
   
   //O(n),equals size
   public int indexOf(Object e) {
      Node<E> current = head;
      for (int i=0; i<size; i++) {
         if (current.value.equals(e))
      return i;
      current = current.next;
      }
      return -1;
   }
   
   //O(n),equals size 
   public int lastIndexOf(Object e) {
      for (int i=size-1; i>=0; i--) {
      if (get(i).equals(e))return i;
      }
      return -1;
   }
   
   //O(n),equals size
   public boolean contains(Object e) {
      Node<E> current = head;
      for(int i=0; i<size; i++)
         if(e.equals(current.value))return true;
      return false;
   }
   
   //O(1)
   public ListIterator<E> iterator(){
      return new DoublyLinkedListIterator();
   }
   
   //O(n),equals index-1
   public ListIterator<E> iterator(int index){
      return new DoublyLinkedListIterator(index);
   }
   
   private class DoublyLinkedListIterator implements  ListIterator<E>{
      private Node<E> current;
      
      //O(1)
      public DoublyLinkedListIterator(){
         current = head;
      }
      
      //O(n),equals index-1
      public DoublyLinkedListIterator(int index){
         if(index == size-1){
            current = tail;
         }else{
         for(int i=0; i<index-1; i++){
            current = current.next;
         }
         }
      }
      
      //O(1)
      public boolean hasNext() {
         return current != null;
      }
      
      //O(1)
      public boolean hasPrevious() {
         return current != null;
      }

      //O(1)
      public E next() {
         E temp = current.value;
         current = current.next;
         return temp;
      }
      
      //O(1)
      public E previous(){
         E temp = current.value;
         current = current.previous;
         return temp;
      }
   
      //O(n^2)
      public void remove() {
         if(current == null)
            throw new IllegalStateException();
         DoublyLinkedList.this.remove(indexOf(current.value));
      }
      
      //O(n),size - 1
      public int nextIndex(){
         int index = 0;
         while(this.hasPrevious()){
            current = current.next;
            index++;
         }
         
         return index;
      }
      
      //O(n),size - 1
      public int previousIndex(){
         int index = 0;
         while(this.hasPrevious()){
            current = current.previous;
            index++;
         }
         return index;
      }
      
      //O(1)
      public void set(E e){
         current.value = e;
      }
      
      //O(1)
      public void add(E e){
         addLast(e);
      }
   }
   
   //O(n),number of elements in the list
   public int searchComparisons(E item)
   {
      int times = 1;
      Iterator iter = iterator();
      while (iter.hasNext()) {
         E temp = (E)iter.next();
         if(temp.equals(item)) break;
         else times++;
      }
   return times;
   }


}