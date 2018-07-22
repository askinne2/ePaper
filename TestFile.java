
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.Scanner;

/*
 ****
 * Andrew added
 * */


public class TestFile {

    public String pdfFiles = "out/production/ePaper/PDFList";

    public TestFile() {

    }


    public File createPDF() {
        File f = null;
        File[] paths;
        Scanner scan = new Scanner(System.in);
        String output = "";
        File selectedFile = null;

        try {

            // create new file
            String path = "out/production/ePaper/PDFList";
            FileSystemView fsv = FileSystemView.getFileSystemView();
            f = new File(pdfFiles);
            JFileChooser jfc = new JFileChooser(pdfFiles, fsv);

            // returns pathnames for files and directory
            //paths = f.listFiles();

            int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
            } else {
                System.out.println("I didn't find a file\n");
                return null;
            }

        } catch (Exception c) {

            // if any error occurs
            c.printStackTrace();
        }
        return selectedFile;
    }

    public File selectPDF() {
        File f = null;
        File[] paths;
        Scanner scan = new Scanner(System.in);
        String output = "";
        File selectedFile = null;

        try {

            // create new file
            /*
             ****
             * Andrew added
             * */
            String path = "out/production/ePaper/PDFList";
            FileSystemView fsv = FileSystemView.getFileSystemView();
            f = new File(pdfFiles);
            JFileChooser jfc = new JFileChooser(pdfFiles, fsv);

            // returns pathnames for files and directory
            //paths = f.listFiles();

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
            } else {
                System.out.println("I didn't find a file\n");
                return null;
            }
/*
            // for each pathname in pathname array
            for (File path1 : paths) {

                output += path1.toString().substring(46, path1.toString().length());

                // prints file and directory paths
                //System.out.println(path1.toString().substring(46, path1.toString().length()));
            }
*/
        } catch (Exception e) {

            // if any error occurs
            e.printStackTrace();
        }
        return selectedFile;
    }


}