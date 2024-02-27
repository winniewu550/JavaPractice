/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *Project 2
 */
import java.util.*;
import java.io.*;

public class Bank
{
   private String name;
   private String branch;
   private BankAccount[] bankAccounts;
   public static double monthlyFee;
   
  /*default constructor
   *initialize bank name,bank branch location,array of bank accounts
   */
   public Bank()
   {
      name = "";
      branch = "";
      bankAccounts = new BankAccount[10];
   }
   
  /*constructor
   *initialize bank name,bank branch location
   *make a copy of bank account list to array bankAccounts
   *@param name:bank name
   *@param branch:location of the bank
   *@param listAccounts:array of bank accounts
   */
   public Bank(String name,String branch,BankAccount[]listAccounts)
   {
      this.name = name;
      this.branch = branch;
      bankAccounts = new BankAccount[listAccounts.length];
      
      for(int i=0;i<listAccounts.length;i++){
         bankAccounts[i] = new BankAccount(listAccounts[i].getName(),listAccounts[i].getAccount(),listAccounts[i].getBalance());
      }
   }
   
   public Bank(String name,String branch,int maxAccounts,String filename)throws Exception
   {
      this.name = name;
      this.branch = branch;
      bankAccounts = new BankAccount[maxAccounts];
      readAccountsFromFile(filename);
   }
   
   private void readAccountsFromFile(String fname)throws Exception
   {     
      File myFile = new File(fname);
      Scanner output = new Scanner(fname);
      
      int i = 0;
      while(output.hasNextLine()){
         i++;
         String type = output.next();
         if(type.equals("Savings")){
            BankAccount ba = new SavingsAccount(output.next(),Integer.parseInt(output.next()),
                                               Double.parseDouble(output.next()),
                                               Double.parseDouble(output.next()));
            bankAccounts[i] = ba;

         }
         if(type.equals("Investmemt")){
            BankAccount ba = new InvestmentAccount(output.next(),Integer.parseInt(output.next()),
                                                   Double.parseDouble(output.next()),
                                                   output.next());
            bankAccounts[i] = ba;

         }else if(type.equals("Regular")){
            BankAccount ba = new BankAccount(output.next(),Integer.parseInt(output.next()),
                                             Double.parseDouble(output.next()));
            bankAccounts[i] = ba;
         }
      }
   }
   
   public void saveAccountsFromFile(String filename)throws Exception
   {
      File myFile = new File(filename);
      PrintWriter input = new PrintWriter(myFile);
      
      for(int i = 0; i < bankAccounts.length; i++){
         input.print(bankAccounts[i]);
      }
      input.println();
      input.close();
   }
   
   /*find an account
    *@param account:the target account
    *@return true if account is found in bankAccounts
    *@return false if account is not found in bankAccounts
    */
   public BankAccount findAccount(int account)
   {
      for(int i=0;i<bankAccounts.length;i++){
         if(bankAccounts[i].getAccount()==account){
            return bankAccounts[i];
         }
      }
      return null;
   }
   
   /*accessor: return the balance of an account
    *@param account:the target account
    *@return the value of the balance of the target account
    */
   public double viewBalance(int account)
   {
      double banl = 0.0;
      for(int i=0;i<bankAccounts.length;i++){
         if(bankAccounts[i].getAccount()==account){
            banl = bankAccounts[i].getBalance();
         }
      }
      return banl;
   } 
   
   /*mutator: deposit amount in the account
    *@param account:the target account
    *@param amount:the value of money being deposited
    *no return value
    */
   public void deposit(int account, double amount)
   {
      for(int i=0;i<bankAccounts.length;i++){
         if(bankAccounts[i].getAccount()==account){
            bankAccounts[i].deposit(amount);
         }
      }
      System.out.println("Deposit completed successfully.");
   }
   
   /*Withdraws amount from account
    *@param account:the target account
    *@param amount:the value of money being withdraw
    *@return true if the withdrawal is completed successfully (enough balance)
    *@return false if the withdrawal is not completed successfully (not enough balanc)
    */
   public boolean withdraw(int account, double amount)
   {
      boolean temp = false;
      for(int i=0;i<bankAccounts.length;i++){
         if(bankAccounts[i].getAccount()==account){
            temp = bankAccounts[i].withdraw(amount);
         }
      }
      if(temp == false){
         System.out.println("Withdrawal operation failed. Not enough credit.");
         System.out.println();
      }else{
         System.out.println("Withdrawal completed successfully.");
      }
      return temp;  
   }
   
   /*mutator: deducts the value monthlyFee from the balance of all the accounts
    *no return value
    */
   public void applyMonthlyFee()
   {
      for(int i=0;i<bankAccounts.length;i++){
         bankAccounts[i].withdraw(monthlyFee);
      }
   }
   
   /*Displays the information of all the accounts (name, account, balance)
    *no return value
    */
   public void viewAllAccounts()
   {
      for (int i = 0; i< bankAccounts.length; i++){
         System.out.println(bankAccounts[i].toString());
      }
   }
   
   public BankAccount[] filterAccounts() 
   {
      BankAccount[] temp = new BankAccount[500];
      int index = 0;
      
      for (int i = 0; i< bankAccounts.length; i++){
         if(bankAccounts[i].accept() == true){
            temp[index] = bankAccounts[i];
            index++;
         }
      }
      return temp;
   }
   
   public void sortAccounts()
   {
      java.util.Arrays.sort(bankAccounts);
   }

}
