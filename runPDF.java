import java.util.Scanner;


public class runPDF {


   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      PDFOperator pdf = new PDFOperator();
      String code = "";
       
      System.out.println("Menu\n"
                       + "C - Create New File\n"
                       + "S - Select From Files\n" 
                       + "R - Rename A File\n"
                       + "Q - Quit Application");
      do {
         System.out.print("Enter Code [C, S, R, Q]: ");
         code = scan.nextLine();
         if (code.length() == 0) {
            continue;
         }
         code = code.toUpperCase();
         char codeChar = code.charAt(0);
         switch(codeChar) {
            case 'C':
               pdf.createPDF();
               break;
            case 'S':
               pdf.selectPDF(); 
               break;
            case 'R':
               pdf.renamePDF(); 
               break;
            case 'Q':
               System.out.println("Quitting Application...");
               break;
            default:
               System.out.println("\t*** invalid code ***" + "\n");  
               break;
         }
      } 
               while (!code.equalsIgnoreCase("Q"));
   
               
               
               
            
               
   }
}

