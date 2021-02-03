package Market;

import javax.swing.*;

public class Book {
    private static int new_id = 1;
    public int id;
    public String owner_id;
    public String type;
    public String name;
    public int price;
    public String condition;
    public String author;
    public JLabel ui_label;

    public Book(String owner_id,String type,String name,int price,String condition,String author){
        this.id = new_id;
        this.new_id++;
        this.owner_id = owner_id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.condition = condition;
        this.author = author;
    }

    public void set_ui_label(JLabel label){
        this.ui_label = label;
    }

}





