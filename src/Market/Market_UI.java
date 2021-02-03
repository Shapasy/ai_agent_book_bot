package Market;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Market_UI extends JFrame {
    private static List<Book> Books = new LinkedList<Book>();
    private JPanel panel;
    private Font global_font;
    private int num_books = 0;

    public Market_UI(){
        this.setTitle("Market");
        this.global_font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        this.panel = new JPanel();
        this.panel.setLayout(new GridLayout(this.Books.size()+1, 6,100,10));
        this.panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.panel.setAutoscrolls(true);
        this.panel.setBackground(Color.pink);
        getContentPane().add(this.panel, BorderLayout.CENTER);
        JLabel[] banner = {
                new JLabel("ID"),
                new JLabel("Type"),
                new JLabel("Name"),
                new JLabel("Price"),
                new JLabel("Condition"),
                new JLabel("Author"),
        };
        for(JLabel ui : banner){
            ui.setFont(this.global_font);
            ui.setOpaque(true);
            ui.setBackground(Color.white);
            this.panel.add(ui);
        }
//        used for testing...
//        add_book(new Book("dd","Comic","Batman Vs SuperMan",2000,"good","Tom Kappa"));
    }

    public Book get_book(int id){
        for(Book book : this.Books){
            if(book.id == id) return book;
        }
        return null;
    }

    public void add_book(Book new_book){
        this.add_book(this.panel,new_book,this.global_font);
    }

    private void add_book(JPanel panel,Book new_book,Font font){
        JLabel id_label = new JLabel(String.valueOf(new_book.id));
        new_book.set_ui_label(id_label);
        this.Books.add(new_book);
        JLabel[] new_book_ui = {
                id_label,
                new JLabel(new_book.type),
                new JLabel(new_book.name),
                new JLabel(String.valueOf(new_book.price)),
                new JLabel(new_book.condition),
                new JLabel(new_book.author),
        };
        for(JLabel ui : new_book_ui){
            ui.setFont(font);
            panel.add(ui);
        }
        System.out.println(new_book.name+" book is added to the market !");
        this.setSize(this.getWidth(),this.getHeight()+40);
        this.num_books++;
        rebuild_UI();
    }

    public void remove_book(int id){
        for(Book book : this.Books){
            if(book.id == id){
                System.out.println(book.name+" book sold !");
                book.ui_label.setText("X");
                book.ui_label.setOpaque(true);
                book.ui_label.setBackground(Color.red);
                this.Books.remove(book);
            }
        }
    }

    private void rebuild_UI(){
//        used for testing...
//        for(Book b : this.Books){
//            System.out.println(b.id);
//        }
        this.panel.setLayout(new GridLayout(this.num_books+1, 6,5,5));
        this.panel.revalidate();
        this.panel.repaint();
    }

    public void show_ui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setResizable(false);
        super.setVisible(true);
    }
}




