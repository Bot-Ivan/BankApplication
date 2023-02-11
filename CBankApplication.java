import java.util.Scanner;
import java.io.*;

public class CBankApplication{
   
   public static void main(String args[]) {
      Scanner scan = new Scanner(System.in); //creating scanner
      System.out.println("Enter account id, 7 digit number only.");
      String acc_id = scan.nextLine();
      
      //going to check if the account id exists in the text file
      try{
      BufferedReader reader = new BufferedReader(new FileReader("acc_info.txt")); //to read file
      
      String line = reader.readLine();
      while(line != null){
         String[] tokens = line.split(",");
         if (tokens[0].equals(acc_id)) {
            System.out.println(("Test: acc_id has been found")); //account id not found in file
         }
         else{
            System.out.println("Test: acc_id has not been found"); //account id found in file
         }
            line = reader.readLine();     
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

