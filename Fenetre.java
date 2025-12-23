import javax.swing.*;
public class Fenetre extends JFrame{
    Table table;
    Fenetre(Table table){
        this.table= table;
        setTitle("Chess");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.add(table);
        setVisible(true);
    }
}