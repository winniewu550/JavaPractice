/**
 *Weining Wu
 *LIN 814985453
 *CSE017,010
 *project 2
 */

public class InvalidAccountNumber extends Exception
{
  public InvalidAccountNumber(){
    super("Invalid Account Number (must be 5 digits)");
  }

  public InvalidAccountNumber(String message){
    super(message);
  }
}