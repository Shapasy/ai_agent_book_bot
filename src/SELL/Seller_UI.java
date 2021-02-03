package SELL;

import Market.Book;
import Market.Market_UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Seller_UI extends JFrame {
    private Seller_Agent agent;
    private JLabel money_amount;

    public Seller_UI(Seller_Agent agent, Market_UI market_ui){
        this.agent = agent;
        this.money_amount = new JLabel(String.valueOf(this.agent.amount_money));
        this.money_amount.setOpaque(true);
        this.money_amount.setBackground(Color.green);
        this.setTitle(this.agent.get_name());
        Font font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(14, 1,5,5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.yellow);
        getContentPane().add(panel, BorderLayout.CENTER);
        JLabel moeny_amount_label = new JLabel("Money Amount");
        moeny_amount_label.setFont(font);
        money_amount.setFont(font);
        panel.add(moeny_amount_label);
        panel.add(money_amount);
        JTextField type = this.add_field(panel,"Book Type",font);
        JTextField name = this.add_field(panel,"Book Name",font);
        JTextField price = this.add_field(panel,"Book Price",font);
        JTextField condition = this.add_field(panel,"Book Condition",font);
        JTextField author = this.add_field(panel,"Book Author",font);
        panel.add(new JLabel(""));
        JButton button = new JButton("Sell Book");
        button.setFont(font);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                      String type_txt = type.getText().trim();
                      String name_txt = name.getText().trim();
                      int price_txt = Integer.parseInt(price.getText().trim());
                      String condition_txt = condition.getText().trim();
                      String author_txt = author.getText().trim();
                      if(type_txt.length() == 0 || name_txt.length() == 0 ||
                              condition_txt.length() == 0 || author_txt.length() == 0){
                          JOptionPane.showMessageDialog(SELL.Seller_UI.this, "Input fields can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                          return;
                      }
                      type.setText("");
                      name.setText("");
                      price.setText("");
                      condition.setText("");
                      author.setText("");
//                      agent.sell_book(type_txt,name_txt,price_txt,condition_txt,author_txt);
                    String agent_id = agent.getAID().getLocalName();
                    Book new_book = new Book(agent_id,type_txt,name_txt,price_txt,condition_txt,author_txt);
                    market_ui.add_book(new_book);
                    JOptionPane.showMessageDialog(SELL.Seller_UI.this, name_txt+" is offered in the market.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(SELL.Seller_UI.this, "Invalid inputs value. ", "Error", JOptionPane.ERROR_MESSAGE);
//                    System.out.println("Error at "+agent.getName()+" :");
//                    System.out.println(e);
                }
            }
        });
        panel.add(button);
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

    public void show_sold_msg(String book_name,int book_price){
        String msg = book_name+" is sold, you made "+book_price+" profit.";
        JOptionPane.showMessageDialog(SELL.Seller_UI.this, msg, "Congrats", JOptionPane.INFORMATION_MESSAGE);
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

    public void refresh_amount_of_money(){
        this.money_amount.setText(String.valueOf(this.agent.amount_money));
//        this.panel.revalidate();
//        this.panel.repaint();
    }
}



