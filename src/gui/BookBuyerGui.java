package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alex_
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import agents.BookBuyerAgent;

public class BookBuyerGui extends JFrame{
    private BookBuyerAgent buyer;
    private JTextField titleField, priceField;
	
    
    public BookBuyerGui(BookBuyerAgent buyer) {
        super(buyer.getLocalName());
        this.buyer = buyer;

        // Creating panel
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2));
        p.add(new JLabel("Book title:"));
        titleField = new JTextField(15);
        p.add(titleField);
        getContentPane().add(p, BorderLayout.CENTER);

        // Button action
        JButton buyButton = new JButton("Buy book");
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                        String title = titleField.getText().trim();
                        buyer.setBookTitle(title);
                        titleField.setText("");
                        buyer.start();
                }catch(Exception e) {
                        JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        p = new JPanel();
        p.add(buyButton);
        getContentPane().add(p, BorderLayout.SOUTH);

        // Closing action
        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            buyer.doDelete();
          }
        });

        setResizable(true);
    }

    public void showGui() {
      pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int centerX = (int)screenSize.getWidth() / 2;
      int centerY = (int)screenSize.getHeight() / 2;
      
      setSize(350,200);
      setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
      super.setVisible(true);
    }
}