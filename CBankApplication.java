import java.util.Scanner;
import java.io.*;

public class CBankApplication{
   
   public static void main(String args[]) {
      Scanner scan = new Scanner(System.in); //creating scanner
      System.out.println("Enter account id, 7 digit number only.");
      String acc_id = scan.nextLine(); //need input validation here
      
      
      //checking if the account id exists in the text file
      try{
      BufferedReader reader = new BufferedReader(new FileReader("acc_info.txt")); //to read file
      String line = reader.readLine();
      
     // System.out.println(line); //testing what line equals
     
      while(line != null){
         String[] tokens = line.split(",");
         //System.out.println(tokens); //testing what tokens equals
         
         if (tokens[0].equals(acc_id)) {//account id found in file
            System.out.println(("Test: acc_id has been found\n"));
            double bal = Double.parseDouble(tokens[2]);
            CBankAccount obj = new CBankAccount(acc_id, tokens[1], bal);
            obj.menu();
            break;
            
         }
            line = reader.readLine();
            //System.out.println("Enter account id, 7 digit number only.");
            //acc_id = scan.nextLine(); //need input validation here
            
            if (line == null){
            System.out.println("This account is not in our system, create new account with this id? Enter 'y' to create a new account or 'n' to try again."); // need to create 'n' case
            String prompt = scan.nextLine(); // need input validation here
            if (prompt.equals("y")) // create a new account
               System.out.println("Enter your name.");
               String cust_name = scan.nextLine(); // need input validation here
               CBankAccount obj = new CBankAccount(acc_id, cust_name, 0.00); // new CBankAccount obj that stores account id, name, and balance
                  
               //now lets append this new information into the textfile acc_info.txt
               obj.newCustomer();
               

            
            }
         }
         
         reader.close();
         
      }
      
      catch (FileNotFoundException e) {
         System.out.println("File not found: " + e.getMessage());
      }
      catch (IOException e) {
         System.out.println("Error reading file: " + e.getMessage());
      }
  
   }

}

class CBankAccount{
   double balance;
   String custName;
   String accID;
   
   CBankAccount(String accID,String custName, double balance){
      this.accID = accID;
      this.custName = custName;
      this.balance = balance;
   }
   
   void newCustomer(){
      try {
      File file = new File("acc_info.txt");
      PrintWriter pw = new PrintWriter(new FileWriter(file, true));
      pw.println(accID + "," + custName + "," + balance);
      pw.close();
      
      System.out.println("New account added:\n"+
         "Account id: " + accID + "\n"+
         "Name: " + custName + "\n"+
         "Balance: " + balance + "\n");
      }
      
      catch (FileNotFoundException e) {
         System.out.println("File not found: " + e.getMessage());
      }
      catch (IOException e) {
         System.out.println("Error reading file: " + e.getMessage());
      }
      
   }
   
   void menu(){
      System.out.println("Welcome " + custName + ", your current balance is " + balance + ".");
   
   }
   


}
