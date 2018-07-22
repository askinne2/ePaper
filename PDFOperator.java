import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class PDFOperator {
   private File f;
  
   public PDFOperator() {
      f = null;      
   }
   public void createPDF(){
      f = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/TestPDF.pdf");
      Scanner scan = new Scanner(System.in);
      File dir = new File("NameOfFile");
      System.out.println("Choose A Name For Your File: ");
      String nL = scan.nextLine();
      File dirs = new File(nL);
      dir.mkdir();
      dir.renameTo(dirs);
      if (dirs.exists()) {
         System.out.println("File Created");
      }
      else {
         System.out.println("File Not Created");
         return;
      }
      
      Path movef = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/" + dirs);
      Path tar = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + dirs);


      /***** wanna check if new file exists *****/
      if (new File(tar.toString()).exists()) {
          File duplicateFile = new File(tar.toString());
          deleteDirectory(duplicateFile);
      }

      try {
         Files.move(movef, tar);
      } catch (IOException ex) {
         System.err.println(ex);
      }  
      for (int i = 0; i < 5; i++) {
         File e = new File((nL + (i + 1)) + ".pdf");
         FileInputStream in = null;
         FileOutputStream out = null;
      
         try{
         
            in = new FileInputStream(f);
            out = new FileOutputStream(e);
         
            byte[] buffer = new byte[1024];
         
            int length;
            while ((length = in.read(buffer)) > 0){
               out.write(buffer, 0, length);
            }
         
            in.close();
            out.close();
         
         }catch(IOException ex){
            ex.printStackTrace();
         }
         /**
           *NEW DIRECTORY FOR NEW DEVICE
         */
         Path movefrom = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/" + e);
         Path target = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + dirs + "/" + e);
         try {
            Files.move(movefrom, target);
         } catch (IOException ex) {
            System.err.println(ex);
         }
      }
      
   }
   public void selectPDF() {
      File f = null;
      File[] paths;
      Scanner scan = new Scanner(System.in);
      
      try {  
      
         // create new file
         f = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList");
         
         // returns pathnames for files and directory
         paths = f.listFiles();
         
         // for each pathname in pathname array
         for(File path1:paths) {
         
            // prints file and directory paths
            System.out.println(path1.toString().substring(46, path1.toString().length()));
         }
         
      } catch(Exception e) {
         
         // if any error occurs
         e.printStackTrace();
      
      }
      File pathway = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList");
      System.out.println("Choose a File");
      String s = scan.nextLine();
      File ss = new File(s);
      File newPath = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + ss + "/" + ss + "1.pdf");
      if (newPath.exists()) {
         if (Desktop.isDesktopSupported()) {
            try {
               Desktop.getDesktop().open(newPath);
            } catch (IOException ex) {
            }
         }
      }      
   }
   public void renamePDF() {
      File f = null;
      File[] paths;
      Scanner scan = new Scanner(System.in);
      
      try {  
      
         // create new file
         f = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList");
         
         // returns pathnames for files and directory
         paths = f.listFiles();
         
         // for each pathname in pathname array
         for(File path1:paths) {
         
            // prints file and directory paths
            System.out.println(path1.toString().substring(46, path1.toString().length()));
         }
         
      } catch(Exception e) {
         
         // if any error occurs
         e.printStackTrace();
      }
      File pathway = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList");
      System.out.println("Choose a File");
      String s = scan.nextLine();
      File ss = new File(s);
      File newPath = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + ss + "/" + ss + "1.pdf");
      if (newPath.exists()) {
         System.out.println("Rename File: ");
         File dir = new File("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + ss);
         String nL = scan.nextLine();
         File dirs = new File(nL);
         dir.renameTo(dirs);
         if (dirs.exists()) {
            System.out.println("File Succesfully Renamed!");
         }
         else {
            System.out.println("File Failed To Rename");
         }
         Path movefrom = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/" + dirs);
         Path target = FileSystems.getDefault().getPath("/Users/andrewskinner/IdeaProjects/ePaper/out/production/ePaper/PDFList/" + dirs);
         try {
            Files.move(movefrom, target);
         } catch (IOException ex) {
            System.err.println(ex);
         }
      
      }
   }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

          
}