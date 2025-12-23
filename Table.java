import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Table extends JPanel{
    Case[][] cases;
    Piece[] pieces;
    
    public void setCases(Case[][] cases) {
        this.cases = cases;
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                this.add(cases[i][j]);
            }
        }
    }

    Table(Piece[] pieces){
        this.pieces = pieces;
        setPreferredSize(new Dimension(1000, 1000));
    }
}