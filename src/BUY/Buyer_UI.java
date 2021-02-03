package BUY;

import Market.Book;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Buyer_UI extends JFrame {
    private Buyer_Agent agent;
    private JLabel money_amount;

    public Buyer_UI(Buyer_Agent agent){
        this.agent = agent;
        this.money_amount = new JLabel(String.valueOf(this.agent.amount_money));
        this.money_amount.setOpaque(true);
        this.money_amount.setBackground(Color.green);
        this.setTitle(this.agent.get_name());
        Font font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 1,5,5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setAutoscrolls(true);
        panel.setBackground(Color.orange);
        getContentPane().add(panel, BorderLayout.CENTER);
        JLabel moeny_amount_label = new JLabel("Money Amount");
        moeny_amount_label.setFont(font);
        money_amount.setFont(font);
        panel.add(moeny_amount_label);
        panel.add(money_amount);
        JTextField Book_id = this.add_field(panel,"Book ID",font);
        JButton button = new JButton("Buy Book");
        button.setFont(font);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int book_id = Integer.parseInt(Book_id.getText().trim());
                    Book book = agent.getMarket().get_market_UI().get_book(book_id);
                    if(book == null){
                        JOptionPane.showMessageDialog(Buyer_UI.this, "Invalid book id", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(agent.amount_money < book.price){
                        JOptionPane.showMessageDialog(Buyer_UI.this, "No enough money to buy this book", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    agent.amount_money -= book.price;
                    JLabel new_book = new JLabel(book.name+" Book");
                    new_book.setFont(font);
                    new_book.setOpaque(true);
                    new_book.setBackground(Color.cyan);
                    panel.add(new_book);
                    money_amount.setText(String.valueOf(agent.amount_money));
                    agent.getMarket().get_market_UI().remove_book(book_id);
                    Book_id.setText("");
                    //Jade send message to this book seller agent -------------->>>>>
                    ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                    message.addReceiver(new AID(book.owner_id,AID.ISLOCALNAME));
                    message.setContent(book.name+" "+String.valueOf(book.price));
                    agent.send(message);
                    //--------------------------------
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(Buyer_UI.this, "Invalid inputs value. ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(button);
        JLabel owned_books = new JLabel("Owned Books");
        owned_books.setFont(font);
        panel.add(owned_books);
    }

    private JTextField add_field(JPanel panel,String label,Font font){
        JLabel label_field = new JLabel(label);
        JTextField text_field = new JTextField(15);
        label_field.setFont(font);
        text_field.setFont(font);
        panel.add(label_field);
        panel.add(text_field);
        return text_field;
    }

    public void show_ui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setResizable(false);
        super.setVisible(true);
        addWindowListener(new	WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                agent.doDelete();
            }
        } );
    }
}





