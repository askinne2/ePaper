import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javafx.collections.*;
import java.util.Iterator;
import javafx.scene.control.*;

/*
tree1 = new FileTree();
        panel3.add(tree1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }
 */
public class test {
    private JButton createPDF;
    private JPanel mainPanel;
    private JButton openPDF;
    private JButton renamePDF;
    private JTextArea mainText;
    private JPanel treePanel;
    private JPanel textPanel;
    private JScrollPane scrollPanel;
    private JPanel buttonPanel;
    private JPanel menuPanel;

    private static ePaperMenu ePaperMenu;

    private FileTree fileTree;
    private TestFile testfile;
    private PDFOperator operator;
    private String path = ".";

    public test() {
        testfile = new TestFile();
        operator = new PDFOperator();

        $$$setupUI$$$();
        createUIComponents();
        createPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ObservableList<CharSequence> paragraph = mainText.getParagraphs();
                //Iterator<CharSequence> iter = paragraph.iterator();

                String input = mainText.getText();
                try {
                    BufferedWriter bf = new BufferedWriter(new FileWriter(testfile.createPDF()));
                    /*
                    while (iter.hasNext()) {
                        CharSequence seq = iter.next();
                        bf.append(seq);
                        bf.newLine();
                    }
                    */
                    bf.write(input);
                    bf.flush();
                    bf.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }

            }
        });
        openPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                File file = testfile.selectPDF();
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(
                            new FileInputStream(file)));
                    mainText.read(input, "READING FILE :-)");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //Add a listener
        fileTree.tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                        .getPath().getLastPathComponent();
                System.out.println("You selected " + node);

                if (node.isLeaf()) {
                    String filename = fileTree.tree.getSelectionPath().toString();
                    filename = filename.substring(4, filename.length() - 1);
                    System.out.println(filename);
                    File file = new File(filename);

                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(
                                new FileInputStream(file)));
                        mainText.read(input, "READING FILE :-)");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fileTree = new FileTree(new File(path));
        treePanel.add(fileTree, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));

        //Create and set up the content pane.


    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            JFrame frame = new JFrame("ePaper");

            @Override
            public void run() {
                /* attach menu bar to system on macs */
                if (System.getProperty("os.name").startsWith("Mac OS X")) {
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Name");
                }
                ePaperMenu = new ePaperMenu();
                frame.setJMenuBar(ePaperMenu.createMenuBar());
                //frame.setContentPane(menu.createContentPane());
                frame.setContentPane(new test().mainPanel);


                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }


        });

    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(-13741716));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, gbc);
        createPDF = new JButton();
        createPDF.setText("Create");
        createPDF.setMnemonic('C');
        createPDF.setDisplayedMnemonicIndex(0);
        createPDF.setVerticalAlignment(0);
        buttonPanel.add(createPDF);
        openPDF = new JButton();
        openPDF.setText("Open");
        openPDF.setMnemonic('O');
        openPDF.setDisplayedMnemonicIndex(0);
        openPDF.putClientProperty("hideActionText", Boolean.FALSE);
        buttonPanel.add(openPDF);
        renamePDF = new JButton();
        renamePDF.setText("Rename");
        buttonPanel.add(renamePDF);
        textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(textPanel, gbc);
        scrollPanel = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        textPanel.add(scrollPanel, gbc);
        mainText = new JTextArea();
        mainText.setLineWrap(true);
        mainText.setMinimumSize(new Dimension(240, 50));
        mainText.setRows(25);
        mainText.setWrapStyleWord(true);
        scrollPanel.setViewportView(mainText);
        treePanel = new JPanel();
        treePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(treePanel, gbc);
        menuPanel = new JPanel();
        menuPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(menuPanel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}







