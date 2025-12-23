import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Case extends JPanel {
    String couleur;
    Piece piece;
    Table table;
    boolean affichageIndication;
    IndicationCercle indication = new IndicationCercle();
    Position position;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    Case(String couleur, Piece piece, Table table, Position position) {
        this.couleur = couleur;
        this.piece = piece;
        this.table = table;
        this.position = position;
        this.setLayout(new GridBagLayout());
        
        int size = 700/8;
        CaseMouseListener pieceMouseListener = new CaseMouseListener(this);
        addMouseListener(pieceMouseListener);
        setPreferredSize(new Dimension(size, size));
        
        if(couleur.equals("marron")){
            setBackground(Color.darkGray);
        }else{
            setBackground(Color.GRAY);
        }
        
        indication.setVisible(false);
        this.add(indication);
        
        if(this.piece != null){
            this.add(this.getPiece());
        }
    }

    public void afficheIndication(){
        if(this.affichageIndication){
            indication.setVisible(true);
            this.revalidate();
            this.repaint();
        }else{
            indication.setVisible(false);
            this.revalidate();
            this.repaint();
        }
    }

}