/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *HW 6
 */
import java.util.*;
@SuppressWarnings("unchecked")

public class LinkedList<E> implements List<E>{
   private Node<E> head, tail;
   private int size;
   
   public LinkedList() {
      head = tail = null;
      size = 0;
   }
   
   public LinkedList(E[] elements) {
      for(int i=0; i<elements.length; i++)
         add(elements[i]);
   }
   
   private class Node<E>{ //Inner class
      E value;
      Node<E> next;
      Node(E item){ //constructor
      value = item;
      next = null;
      }
   }
   
   public int size() {
      return size;
   }
   
   public E getFirst() {
      if(size == 0) 
         return null;
      else 
         return head.value;
   }
   
   public E getLast() {
      if (size == 0)
         return null;
      else
         return tail.value;
   }
   
   public void addFirst(E item) {
      Node<E> newNode = new Node<E>(item);
      newNode.next = head;
      head = newNode;
      size++;
      if (tail == null)
         tail = head;
   }

   public void addLast(E item) {
      Node<E> newNode = new Node<>(item);
      if(tail == null)
         head = tail = newNode;
      else {
         tail.next = newNode;
         tail = newNode;
      }
      size++;
   }
   
   public void add(int index, E item) {
      if(index == 0)
         addFirst(item);
      else if (index >= size)
         addLast(item);
      else {
         Node<E> current = head;
         for(int i=0; i<index-1; i++){//find index
            current = current.next;
         }
      Node<E> temp = current.next;
      Node<E> newNode = new Node<>(item);
      current.next = newNode;
      newNode.next = temp;
      size++;
      }
   }
   
   public E removeFirst() {
      if (size == 0) return null;
      Node<E> temp = head; head = head.next; size--;
      if(head == null) tail = null;
         return temp.value;
   }
   
   public E removeLast() {
      if (size == 0) return null;
      Node<E> current = head;
      for(int i=0; i<size-2; i++)
         current = current.next;
         Node<E> temp = tail;
         if(head == tail) {
            head = tail = null; size = 0;
         }
         else {
            tail = current; tail.next = null; size--;
         }
         return temp.value;
   }
   
   public E remove(int index) {//5
      if (index < 0 || index >= size) return null;
         Node<E> temp;
         Node<E> current = head;
      for (int i=0; i<index-1; i++)
         current = current.next;//current=4
         temp = current.next;//temp=5
         current.next = temp.next;//skip 5, 4 connect to 6
         Node<E>newNext = temp.next;
         newNext.previous = current;
         size--;
      return temp.value;
   }
   
   public void clear() {
      size = 0;
      head = tail = null;
   }
   
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
   
   private void checkIndex(int index) {
      if (index >= size || index < 0)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
   }
   
   public E get(int index) {
      if(size == 0)return null;
         checkIndex(index);
         Node<E> current = head;
      for(int i=0; i<index; i++)
         current = current.next;
         return current.value;
   }

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
   
   public int indexOf(Object e) {
      Node<E> current = head;
      for (int i=0; i<size; i++) {
         if (current.value.equals(e))
      return i;
      current = current.next;
      }
      return -1;
   }
   
   public int lastIndexOf(Object e) {
      for (int i=size-1; i>=0; i--) {
      if (get(i).equals(e))return i;
      }
      return -1;
   }
   
   public boolean contains(Object e) {
      Node<E> current = head;
      for(int i=0; i<size; i++)
         if(e.equals(current.value))return true;
      return false;
   }

   public Iterator<E> iterator(){
      return new LinkedListIterator();
   }
   
   private class LinkedListIterator implements Iterator<E>{
      private Node<E> current = head;
      
      public boolean hasNext() {
         return current != null;
      }

      public E next() {
         E temp = current.value;
         current = current.next;
         return temp;
      }
   
      public void remove() {
         if(current == null)
            throw new IllegalStateException();
         LinkedList.this.remove(indexOf(current.value));
      }
   }
   
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