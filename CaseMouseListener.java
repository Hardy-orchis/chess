import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CaseMouseListener implements MouseListener {

    Case cases;

    CaseMouseListener(Case cases){
        this.cases = cases;
    }

    boolean rechercheDansLesMouvement(ArrayList<Position> mouvementDuPiece, Position position) {
        for(Position pos : mouvementDuPiece) {
            if(pos.x == position.x && pos.y == position.y) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.cases.piece != null) {
            if(Main.pieceSelectione == null) {
                Main.pieceSelectione = this.cases.piece;
                System.out.println("Position pion:"+ Main.pieceSelectione.position.x +", "+ Main.pieceSelectione.position.y+", Nom: "+ Main.pieceSelectione.nom);
            } 
            else if(this.cases.piece.couleur.equals(Main.pieceSelectione.couleur)) {
                Main.pieceSelectione = this.cases.piece;
            }
            else {
                ArrayList<Position> mouvementDuPiece = Main.pieceSelectione.listeDesMouvementPossible(this.cases.table.cases);
                if(rechercheDansLesMouvement(mouvementDuPiece, cases.position)) {
                    this.cases.remove(this.cases.piece);
                    
                    Case ancienneCase = this.cases.table.cases[Main.pieceSelectione.position.x][Main.pieceSelectione.position.y];
                    ancienneCase.remove(Main.pieceSelectione);
                    ancienneCase.piece = null;
                    
                    Main.pieceSelectione.position.x = cases.position.x;
                    Main.pieceSelectione.position.y = cases.position.y;
                    
                    this.cases.piece = Main.pieceSelectione;
                    this.cases.add(Main.pieceSelectione);
                    
                    ancienneCase.revalidate();
                    ancienneCase.repaint();
                    this.cases.revalidate();
                    this.cases.repaint();
                }
                Main.pieceSelectione = null;
            }
        } 
        else {
            if(Main.pieceSelectione != null) {
                ArrayList<Position> mouvementDuPiece = Main.pieceSelectione.listeDesMouvementPossible(this.cases.table.cases);
                if(rechercheDansLesMouvement(mouvementDuPiece, cases.position)) {
                    System.out.println("Position case:"+ this.cases.position.x+", "+ this.cases.position.y);
                    Case ancienneCase = this.cases.table.cases[Main.pieceSelectione.position.x][Main.pieceSelectione.position.y];
                    ancienneCase.remove(Main.pieceSelectione);
                    ancienneCase.piece = null;
                    
                    Main.pieceSelectione.position.x = cases.position.x;
                    Main.pieceSelectione.position.y = cases.position.y;
                    
                    this.cases.piece = Main.pieceSelectione;
                    Main.pieceSelectione.premierCout = false;
                    this.cases.add(Main.pieceSelectione);
                    
                    ancienneCase.revalidate();
                    ancienneCase.repaint();
                    this.cases.revalidate();
                    this.cases.repaint();
                }
                Main.pieceSelectione = null;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.cases.piece!=null) {
            ArrayList<Position> mouvementDuPiece = this.cases.piece.listeDesMouvementPossible(this.cases.table.cases);
            for(Position pos : mouvementDuPiece) {
                this.cases.table.cases[pos.x][pos.y].affichageIndication = true;
                this.cases.table.cases[pos.x][pos.y].afficheIndication();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.cases.piece!=null) {
             ArrayList<Position> mouvementDuPiece = this.cases.piece.listeDesMouvementPossible(this.cases.table.cases);
            for(Position pos : mouvementDuPiece) {
                    this.cases.table.cases[pos.x][pos.y].affichageIndication = false;
                    this.cases.table.cases[pos.x][pos.y].afficheIndication();
            }
        }
    }
}
