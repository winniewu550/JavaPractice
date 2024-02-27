/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *HW 6
 */
import java.util.*;

@SuppressWarnings("unchecked")
public interface List<E> extends Collection<E>
{
   void add(int index, E item);
   E get(int index);
   E set(int index, E item);
   int indexOf(Object e);
   int lastIndexOf(Object e);
   E remove(int index);
   
   default boolean add(E item) 
   {
      add(size(), item);
      return true;
   }
   
   default boolean isEmpty() 
   {
      return size()==0;
   }
 
   default boolean remove(Object e) 
   {
      if (indexOf(e) >= 0) {
         remove(indexOf(e));
         return true;
      }
      return false;
   } 
   
   default boolean containsAll(Collection<?> c) 
   {
      for(Object e: c)
      if (indexOf(e) == -1) return false;
      return true;
   }
   
   default boolean addAll(Collection<? extends E> c)
   {
      boolean changed = false;
      for(Object e: c)
         if (indexOf(e) < 0) {
            add((E) e);
            changed = true;
         }
      return changed;
   }
   
   default boolean removeAll(Collection<?> c) 
   {
      boolean changed = false;
      for(Object e: c)
         if (indexOf(e) >= 0) {
            remove(e);
            changed = true;
      }
      return changed;
 }
 
   default boolean retainAll(Collection<?> c) 
   {
      boolean changed = false;
      for(Object e: c)
         if (indexOf(e) < 0) {
            remove(e);
            changed = true;
         }
      return changed;
   } 
   
   default Object[] toArray() {
      Object[] objects = new Object[size()];
      for(int i=0; i<size(); i++)
         objects[i] = get(i);
      return objects;
   }
 
   default <T> T[] toArray(T[] array) 
   {
      for(int i=0; i<size(); i++)
         array[i] = (T) get(i);
      return array;
   }
   
}