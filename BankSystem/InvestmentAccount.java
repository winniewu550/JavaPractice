/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *project 2
 */
 
public class InvestmentAccount extends BankAccount
{
   private String accountType;
   
   public InvestmentAccount()
   {
       accountType = "";
   }
   
   public InvestmentAccount(String name,int account, double balance,String accountType)
   {
      super(name,account,balance);
      this.accountType = accountType;
   }
   
  /*accessor
   *@return the value of the account balance
  */
  public String getType()
  {
   return accountType;
  }
  
  /*mutator
   *@param:amount of money being added to the account
   *no return value
  */
  public void setType(String type)
  {
    accountType = type;  
  }

  public double applyRisk()
  {
   double risk = Math.random();
   double profitLoss = Math.random();
   if(risk >= 0.5){
      balance += balance*profitLoss/10;
      return balance*profitLoss/10;
   }else{
      balance -= balance*profitLoss/10;
      return balance*profitLoss/10;
   }
  }
  
  /*return a string of all the information of a certain account
  */
  public String toString()
  {
   String temp = String.format("%-20s%-20d%-20.2f%-20.2f",getName(),getAccount(),getBalance(),applyRisk());
   return temp;
  }

   
}