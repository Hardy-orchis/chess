import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CaseMouseListener implements MouseListener {

    Case cases;

    CaseMouseListener(Case cases){
        this.cases = cases;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
