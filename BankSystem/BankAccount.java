/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *project 2
 */

import java.util.*;

public class BankAccount implements Comparable<BankAccount>, Filter
{
   private String name;
   private int account;
   protected double balance;
   static double minBalance;
  
  /*default constructor
   *initialize name,account number,balance
   */
  public BankAccount()
  {
   name = "";
   account = 0;
   balance = 0.0;
   minBalance = 0.0;
  }
  
  /*constructor
   *create an account with name,account number,and balance  
   *@param name:name of the account owner
   *@param account:account number
   *@param balance:amount of money in the bank account
   */
  public BankAccount(String name,int account, double balance)
  {
   this.name = name;
   this.account = account;
   this.balance = balance;
  }
  
  /*accessor
   *@return the String of owner's name
  */
  public String getName()
  {
   return name;
  }
  
  /*accessor
   *@return the account number
  */
  public int getAccount()
  {
   return account;
  }

  /*accessor
   *@return the value of the account balance
  */
  public double getBalance()
  {
   return balance;
  }
  
  /*mutator
   *@param:amount of money being added to the account
   *no return value
  */
  public void deposit(double amount)
  {
   balance = balance + amount;  
  }

  /*mutator:Subtracts amount from the account balance
   *@param:amount of money being added to the account
   *@return true if there is enough balance to be subtracted
   *@return false if there is not enough balance to be subtracted
  */
  public boolean withdraw(double amount)
  {
   if(balance >= amount){
      balance = balance - amount;
      return true;
   }else{
      return false;
   }
  }
  
  /*compares two BankAccount objects
   *@param: a bank account object
   *@return true if the two accounts have the same account number
   *@return false if the two accounts have different account number
   */
   public boolean equals (BankAccount bk)
   {
      if(bk.getAccount()== this.getAccount()){
         return true;
      }else return false;
   }
 
  /*compares two BankAccount objects
   *@param: a bank account
   *@return true if the two accounts have the same account number
   *@return false if the two accounts have different account number
   */
   public int compareTo(BankAccount bk)
   {
      if(getBalance() == bk.getBalance()) return 0;
      else if(getBalance() < bk.getBalance()) return -1;
      else return 1;
   } 
   
   public boolean accept()
   {
      if(balance >= minBalance)return true;
      else return false;
   }

  
  /*return a string of all the information of a certain account
  */
  public String toString()
  {
   String temp = String.format("%-20s%-20d$%-20.2f",getName(),getAccount(),getBalance());
   return temp;
  }
}