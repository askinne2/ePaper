import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Operator {
 
   public static void main(String[] args) {

      TestFile tf = new TestFile();
      PDFOperator op = new PDFOperator();
      JFrame frame = new JFrame("CardLayout demo");
      JPanel panelCont = new JPanel();
      JPanel panelFirst = new JPanel();
      JPanel panelSecond = new JPanel();
      JButton buttonOne = new JButton("Create File");
      JButton buttonSecond = new JButton("Select File");
      JButton buttonThird = new JButton("Choose your File");
      JButton buttonFourth = new JButton("Rename File");
      
      CardLayout cl = new CardLayout();

      frame.setJMenuBar(new JMenuBar());

       panelCont.setLayout(cl);
   
      panelFirst.add(buttonOne);
      panelFirst.add(buttonSecond);
      panelSecond.add(buttonThird);
      panelFirst.setBackground(Color.gray);
      panelSecond.setBackground(Color.darkGray);
      panelFirst.add(buttonFourth);
   
      panelCont.add(panelFirst, "1");
      cl.show(panelCont, "1");
      panelCont.add(panelSecond, "2");
   	
      buttonOne.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                op.createPDF();
            }
         });
   	
      buttonSecond.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
               cl.show(panelCont, "2");
            }
         });
      buttonThird.addActionListener(
              new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent arg0) {
                    File chosenFile = tf.selectPDF();
                 }
              });
   	
      frame.add(panelCont);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setSize(1000, 1000);
      frame.setVisible(true);
   	
   
   }
}