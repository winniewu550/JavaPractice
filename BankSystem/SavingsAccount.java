/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *project 2
 */

public class SavingsAccount extends BankAccount
{
   private double yearlyInterestRate;
   
   public SavingsAccount()
   {
      yearlyInterestRate = 0.0;
   }
   
   public SavingsAccount(String name,int account, double balance,double yearlyInterest)
   {
      super(name,account,balance);
      this.yearlyInterestRate = yearlyInterest;
   }
   
  /*accessor
   *@return the value of the account balance
  */
  public double getInterestRate()
  {
   return yearlyInterestRate;
  }
  
  /*mutator
   *@param:amount of money being added to the account
   *no return value
  */
  public void setInterestRate(double annalRate)
  {
    yearlyInterestRate = annalRate;  
  }
  
  public double applyMonthlyInterest()
  {
    double interest = balance*yearlyInterestRate/100.0;
    balance += interest;
    return interest;
  }
  
  /*return a string of all the information of a certain account
  */
  public String toString()
  {
   String temp = String.format("%-20s%-20d%-20.2f%-20.2f",getName(),getAccount(),getBalance(),applyMonthlyInterest());
   return temp;
  }


}