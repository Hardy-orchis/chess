import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

        if(this.nom=="Cavalier"  && this.couleur == "noir"){
            Position[] positions = new Position[4];
            positions[0] = new Position(this.position.x+1, this.position.y+2);
            positions[1] = new Position(this.position.x+1, this.position.y-2);
            positions[2] = new Position(this.position.x-1, this.position.y+2);
            positions[3] = new Position(this.position.x+1, this.position.y-2);
            return positions;
        }
        return null;
    }

}