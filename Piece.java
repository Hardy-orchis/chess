import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piece extends JPanel{
    String image;
    String nom;
    Position position;
    String couleur;

    Piece(String image, String nom, Position position, String couleur) throws IOException {
        this.nom = nom;
        this.image = image;
        this.position = position;
        this.couleur = couleur;
        BufferedImage myPicture = ImageIO.read(new File(image));
        Image scaledImage = myPicture.getScaledInstance(50, 70, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        picLabel.setOpaque(false);
        picLabel.setBackground(new Color(0,0,0,0));
        add(picLabel);
        setOpaque(false);
        revalidate();              
        repaint();
    }

    private void ajouterSiValide(List<Position> positions, int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            positions.add(new Position(x, y));
        }
    }


    public Position[] listeDesMouvementPossible(){

        if(this.nom=="Pion" && this.couleur == "noir" ){
            Position[] positions = new Position[1];
            positions[0] = new Position(this.position.x-1, this.position.y);
            return positions;
        }
        if(this.nom=="Pion" && this.couleur == "blanc" ){
            Position[] positions = new Position[1];
            positions[0] = new Position(this.position.x+1, this.position.y);
            return positions;
        }

        if ("Cavalier".equals(this.nom)) {
            List<Position> positions = new ArrayList<>();
            ajouterSiValide(positions, this.position.x + 1, this.position.y + 2);
            ajouterSiValide(positions, this.position.x + 1, this.position.y - 2);
            ajouterSiValide(positions, this.position.x - 1, this.position.y + 2);
            ajouterSiValide(positions, this.position.x - 1, this.position.y - 2);

            return positions.toArray(new Position[0]);
        }

        if("Roi".equals(nom)){
            List<Position> positions = new ArrayList<>();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        ajouterSiValide(positions, this.position.x + dx, this.position.y + dy);
                    }
                }
            }
            return positions.toArray(new Position[0]);
        }

        if("Dame".equals(nom)){
            List<Position> positions = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                if (i != this.position.x) {
                    ajouterSiValide(positions, i, this.position.y);
                }
                if (i != this.position.y) {
                    ajouterSiValide(positions, this.position.x, i);
                }
            }
            for (int dx = -7; dx <= 7; dx++) {
                if (dx != 0) {
                    ajouterSiValide(positions, this.position.x + dx, this.position.y + dx);
                    ajouterSiValide(positions, this.position.x + dx, this.position.y - dx);
                }
            }
            return positions.toArray(new Position[0]);
        }

        if("Fou".equals(nom)){
            List<Position> positions = new ArrayList<>();
            for (int dx = -7; dx <= 7; dx++) {
                if (dx != 0) {
                    ajouterSiValide(positions, this.position.x + dx, this.position.y + dx);
                    ajouterSiValide(positions, this.position.x + dx, this.position.y - dx);
                }
            }
            return positions.toArray(new Position[0]);
        }

        if("Tour".equals(nom)){
            List<Position> positions = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                if (i != this.position.x) {
                    ajouterSiValide(positions, i, this.position.y);
                }
                if (i != this.position.y) {
                    ajouterSiValide(positions, this.position.x, i);
                }
            }
            return positions.toArray(new Position[0]);
        }

        return null;
    }
}