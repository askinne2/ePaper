/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;



/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class ePaperMenu implements ActionListener {
    JTextArea output;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, help, submenu;
    JMenuItem fileNew, fileOpen, fileSave, fileSaveAs;

    File selectedFile;

    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    JFrame frame;


    public JMenuBar createMenuBar() {

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(fileMenu);

        // for File > Open
        fileNew = new JMenuItem("New", KeyEvent.VK_T);

        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
        fileNew.getAccessibleContext().setAccessibleDescription(
                "New File Menu");
        fileNew.addActionListener(this);
        fileMenu.add(fileNew);

        // for File > New
        fileOpen = new JMenuItem("Open", KeyEvent.VK_T);

        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        fileOpen.getAccessibleContext().setAccessibleDescription(
                "New File Menu");
        fileOpen.addActionListener(this);
        fileMenu.add(fileOpen);

        /*a group of radio button menu items
        fileMenu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        fileMenu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        fileMenu.add(rbMenuItem);

        //a group of check box menu items
        fileMenu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        fileMenu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        fileMenu.add(cbMenuItem);

        //a submenu
        fileMenu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        fileMenu.add(submenu);
*/
        //Build second menu in the menu bar.
        editMenu = new JMenu("Another Menu");
        editMenu.setMnemonic(KeyEvent.VK_N);
        editMenu.getAccessibleContext().setAccessibleDescription(
                "This gives you edit options");
        menuBar.add(editMenu);

        return menuBar;
    }

    /**
     * This handles the action for the File/Open event, and demonstrates
     * the implementation of an ActionListener.
     * In a real-world program you'd handle this differently.
     */
    public void actionPerformed(ActionEvent ev)
    {
        fileDialog dialog = new ePaperMenu.fileDialog(ev);

        /*
        dialog.setModal(true);
        dialog.setVisible(true);
        */
    }


    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuLookDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        ePaperMenu demo = new ePaperMenu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    /**
     * This dialog is displayed when the user selects the File/Open menu item.
     */
    private class fileDialog extends JDialog implements ActionListener
    {

        private JButton okButton = new JButton("OK");

        private fileDialog(ActionEvent event)
        {

            System.out.println("getActionCmd()  " + event.getActionCommand());
            System.out.println("cmdString() " + event.paramString());

            TestFile testFile = new TestFile();
            if (event.getActionCommand() == "New") {
                selectedFile = testFile.createPDF();

            } else if (event.getActionCommand() == "Open") {
                selectedFile = testFile.selectPDF();


            }

        }

        public void actionPerformed(ActionEvent ev)
        {

            setVisible(false);
        }
    }
}