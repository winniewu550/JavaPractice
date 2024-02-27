/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Programming Project 1
 */
import java.util.*;

public class TestBank
{
   //main method
   public static void main(String[] args)throws InvalidAccountNumber
   {
      Bank.monthlyFee = 20.0;            
      Bank myBank = new Bank("MyBank","Bethlehem",500,"accounts.txt");
            
      Scanner keyboard = new Scanner(System.in);
      int operation = 0;
      
      do{
      operation = getMainOperation(keyboard);
         switch(operation){
            
            case 1:
               int accNum = 0;
               do{
                  System.out.println("Enter account number: ");
                  accNum = keyboard.nextInt();
                  if(myBank.findAccount(accNum)==null){
                     System.out.println("Account number not found. Try again.");
                     System.out.println();
                  }
                  else{
                     int subOperation = 0;
                        
                         do{
                           subOperation = getAccountOperation(keyboard);
                           switch(subOperation){
                           case 1:
                              System.out.println("Banlance is $"+ myBank.viewBalance(accNum));
                              System.out.println();
                              break;
                           case 2:
                              System.out.println("Enter amount to deposit:");
                              double deposit = keyboard.nextDouble();
                              myBank.deposit(accNum,deposit);
                              System.out.println("The new balance is "+ myBank.viewBalance(accNum));
                              System.out.println();
                              break;
                           case 3:
                              System.out.println("Enter amount to withdraw:");
                              double withdraw = keyboard.nextDouble();
                              boolean temp = myBank.withdraw(accNum,withdraw);
                              if(temp == true){
                                 System.out.println("The new balance is "+ myBank.viewBalance(accNum));
                                 System.out.println();
                              }
                              break;
                           case 4:
                              if(!((myBank.findAccount(accNum))instanceof SavingsAccount)) {
                                 System.out.println("it is not a saving account.");
                                 break;
                              }else{
                                 System.out.println((SavingsAccount)myBank.findAccount(accNum).applyMonthlyInterest());   
                              }
                              System.out.println();
                           }  
                        }while(subOperation != 4);                             
                     }
                
             }while(myBank.findAccount(accNum)==false);
               break;
               
            case 2:
               myBank.applyMonthlyFee();
               break;
            
            case 3:
               myBank.viewAllAccounts();
               break;
            
            case 4:
               System.out.println("Thank you for using myBank services.");
            }
        } while (operation !=4);
      }

   
   //Display a menu for the user to select one of the following operations
   public static void mainMenu()
   {
      System.out.println("Select an operation (Main Menu):");
      System.out.println("1: Manage Account");
      System.out.println("2: Apply Monthly Fee");
      System.out.println("3: View All Accounts");
      System.out.println("4: Exit");
   }
   
   //Display a menu for the user to select one of the following operations
   public static void accountMenu()
   {
      System.out.println("Select an operation (Account Menu):");
      System.out.println("1: View balance");
      System.out.println("2: Deposit");
      System.out.println("3: Withdraw");
      System.out.println("4: View the monthly interest");
      System.out.println("4: Exit Manage Account");
   }
   
   /** gets the operation in the main menu selected by the user
   * @param Scanner object to read the selected operation
   * @return value entered by the user (operation selection)
   */
   public static int getMainOperation(Scanner input) 
   {
        int op = 0;
        do {
            mainMenu();
            if (input.hasNextInt()) {
                op = input.nextInt();
                if (op >= 1 && op <= 4) 
                  break;
                else
                  System.out.println("Invalid operation (1-4)");
                  System.out.println();
            }else {
                input.nextLine();
                System.out.println("Invalid operation(must be integer)");
                System.out.println();
            }
        } while (true);
        return op;
    }
    
   /** gets the operation in the submenu under operation 1 in the main menu selected by the user
   * @param Scanner object to read the selected operation
   * @return value entered by the user (operation selection)
   */
    public static int getAccountOperation(Scanner input) 
    {
      int op = 0;
      do {
         accountMenu();
         if (input.hasNextInt()) {
            op = input.nextInt();
            if (op >= 1 && op <= 4) 
               break;
            else
               System.out.println("Invalid operation (1-4)");
               System.out.println();
            }else{
               input.nextLine();
               System.out.println("Invalid operation(must be integer)");
               System.out.println();
            }
      }while (true);
      return op;
    }

   /*count the digit of the account number inserted by the user
    *@param num:the account number
    *@return the number of the digits
   */
   public static int countDigit(int num)
   {
      int count = 0;
      while (num >= 1){
         num/=10;
         count++;
      }
      return count;
   }
}
