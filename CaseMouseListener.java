import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            Position[] mouvementDuPiece = this.cases.piece.listeDesMouvementPossible();
            for (int i = 0; i < mouvementDuPiece.length; i++) {
                    this.cases.table.cases[mouvementDuPiece[i].x][mouvementDuPiece[i].y].affichageIndication = true;
                    this.cases.table.cases[mouvementDuPiece[i].x][mouvementDuPiece[i].y].afficheIndication();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.cases.piece!=null) {
            Position[] mouvementDuPiece = this.cases.piece.listeDesMouvementPossible();
            for (int i = 0; i < mouvementDuPiece.length; i++) {
                    this.cases.table.cases[mouvementDuPiece[i].x][mouvementDuPiece[i].y].affichageIndication = false;
                    this.cases.table.cases[mouvementDuPiece[i].x][mouvementDuPiece[i].y].afficheIndication();
            }
        }
    }
}
