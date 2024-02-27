/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 3
 */
import java.util.*;
import java.io.File;

public class Test {
   public static DoublyLinkedList<String> countries;
   
   @SuppressWarnings("unchecked") 
   public static void main(String[] args) throws Exception {
      String[]countryList = new String[240];
    
      File countryCollection = new File("countries.txt");
      Scanner read = new Scanner(countryCollection);
      
      for (int i = 0; read.hasNextLine(); i++){
            countryList[i] = read.nextLine();
      }
      read.close();
            
      countries = new DoublyLinkedList<String>(countryList);
      
      System.out.println("print forward:");
      printForward(countries);
      System.out.println("\nprint backward:");
      printBackward(countries);
   }
      
      
      public static void printForward(DoublyLinkedList<String> list){
         ListIterator<String> iter = list.iterator();  
         while(iter.hasNext()){
            String temp = iter.next();
            if(temp != null){
               System.out.println(temp);
            }
         }
      }
      
      public static void printBackward(DoublyLinkedList<String> list){
         ListIterator<String> iter = list.iterator(list.size()-1);
         while(iter.hasPrevious()){
            String temp = iter.previous();
            if(temp != null){
               System.out.println(temp);
            }
         }
      }
}