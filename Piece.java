import java.awt.Color;
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


    public ArrayList<Position> listeDesMouvementPossible(Case[][] listeDesCase){
        List<Position> positions = new ArrayList<>();
        
        if("Pion".equals(this.nom) && "noir".equals(this.couleur)){
            if(listeDesCase[this.position.x - 1][this.position.y].piece==null){
                ajouterSiValide(positions, this.position.x - 1, this.position.y);
            }
            return new ArrayList<>(positions);
        }
        
        if("Pion".equals(this.nom) && "blanc".equals(this.couleur)){
            if(listeDesCase[this.position.x + 1][this.position.y].piece==null){
                ajouterSiValide(positions, this.position.x + 1, this.position.y);
            }
            return new ArrayList<>(positions);
        }
        
        if("Cavalier".equals(this.nom)){
            int[][] mouvements = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
            };
            
            for(int[] mouv : mouvements){
                int newX = this.position.x + mouv[0];
                int newY = this.position.y + mouv[1];
                
                if(newX >= 0 && newX < 8 && newY >= 0 && newY < 8){
                    if(listeDesCase[newX][newY].piece == null || 
                    !listeDesCase[newX][newY].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, newX, newY);
                    }
                }
            }
            return new ArrayList<>(positions);
        }
        
        if("Roi".equals(this.nom)){
            for(int dx = -1; dx <= 1; dx++){
                for(int dy = -1; dy <= 1; dy++){
                    if(dx != 0 || dy != 0){
                        int newX = this.position.x + dx;
                        int newY = this.position.y + dy;
                        if(newX >= 0 && newX < 8 && newY >= 0 && newY < 8){
                            if(listeDesCase[newX][newY].piece == null || 
                            !listeDesCase[newX][newY].piece.couleur.equals(this.couleur)){
                                ajouterSiValide(positions, newX, newY);
                            }
                        }
                    }
                }
            }
            return new ArrayList<>(positions);
        }
        
        if("Dame".equals(this.nom)){
            for(int i = this.position.x + 1; i < 8; i++){
                if(listeDesCase[i][this.position.y].piece == null){
                    ajouterSiValide(positions, i, this.position.y);
                } else {
                    if(!listeDesCase[i][this.position.y].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, i, this.position.y);
                    }
                    break; 
                }
            }
            for(int i = this.position.x - 1; i >= 0; i--){
                if(listeDesCase[i][this.position.y].piece == null){
                    ajouterSiValide(positions, i, this.position.y);
                } else {
                    if(!listeDesCase[i][this.position.y].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, i, this.position.y);
                    }
                    break;
                }
            }
            for(int i = this.position.y + 1; i < 8; i++){
                if(listeDesCase[this.position.x][i].piece == null){
                    ajouterSiValide(positions, this.position.x, i);
                } else {
                    if(!listeDesCase[this.position.x][i].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x, i);
                    }
                    break;
                }
            }
            for(int i = this.position.y - 1; i >= 0; i--){
                if(listeDesCase[this.position.x][i].piece == null){
                    ajouterSiValide(positions, this.position.x, i);
                } else {
                    if(!listeDesCase[this.position.x][i].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x, i);
                    }
                    break;
                }
            }
            
            for(int d = 1; this.position.x + d < 8 && this.position.y + d < 8; d++){
                if(listeDesCase[this.position.x + d][this.position.y + d].piece == null){
                    ajouterSiValide(positions, this.position.x + d, this.position.y + d);
                } else {
                    if(!listeDesCase[this.position.x + d][this.position.y + d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x + d, this.position.y + d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x + d < 8 && this.position.y - d >= 0; d++){
                if(listeDesCase[this.position.x + d][this.position.y - d].piece == null){
                    ajouterSiValide(positions, this.position.x + d, this.position.y - d);
                } else {
                    if(!listeDesCase[this.position.x + d][this.position.y - d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x + d, this.position.y - d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x - d >= 0 && this.position.y + d < 8; d++){
                if(listeDesCase[this.position.x - d][this.position.y + d].piece == null){
                    ajouterSiValide(positions, this.position.x - d, this.position.y + d);
                } else {
                    if(!listeDesCase[this.position.x - d][this.position.y + d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x - d, this.position.y + d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x - d >= 0 && this.position.y - d >= 0; d++){
                if(listeDesCase[this.position.x - d][this.position.y - d].piece == null){
                    ajouterSiValide(positions, this.position.x - d, this.position.y - d);
                } else {
                    if(!listeDesCase[this.position.x - d][this.position.y - d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x - d, this.position.y - d);
                    }
                    break;
                }
            }
            
            return new ArrayList<>(positions);
        }
        if("Fou".equals(this.nom)){
            for(int d = 1; this.position.x + d < 8 && this.position.y + d < 8; d++){
                if(listeDesCase[this.position.x + d][this.position.y + d].piece == null){
                    ajouterSiValide(positions, this.position.x + d, this.position.y + d);
                } else {
                    if(!listeDesCase[this.position.x + d][this.position.y + d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x + d, this.position.y + d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x + d < 8 && this.position.y - d >= 0; d++){
                if(listeDesCase[this.position.x + d][this.position.y - d].piece == null){
                    ajouterSiValide(positions, this.position.x + d, this.position.y - d);
                } else {
                    if(!listeDesCase[this.position.x + d][this.position.y - d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x + d, this.position.y - d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x - d >= 0 && this.position.y + d < 8; d++){
                if(listeDesCase[this.position.x - d][this.position.y + d].piece == null){
                    ajouterSiValide(positions, this.position.x - d, this.position.y + d);
                } else {
                    if(!listeDesCase[this.position.x - d][this.position.y + d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x - d, this.position.y + d);
                    }
                    break;
                }
            }
            for(int d = 1; this.position.x - d >= 0 && this.position.y - d >= 0; d++){
                if(listeDesCase[this.position.x - d][this.position.y - d].piece == null){
                    ajouterSiValide(positions, this.position.x - d, this.position.y - d);
                } else {
                    if(!listeDesCase[this.position.x - d][this.position.y - d].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x - d, this.position.y - d);
                    }
                    break;
                }
            }
            return new ArrayList<>(positions);
        }
        
        if("Tour".equals(this.nom)){
            for(int i = this.position.x + 1; i < 8; i++){
                if(listeDesCase[i][this.position.y].piece == null){
                    ajouterSiValide(positions, i, this.position.y);
                } else {
                    if(!listeDesCase[i][this.position.y].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, i, this.position.y);
                    }
                    break;
                }
            }
            for(int i = this.position.x - 1; i >= 0; i--){
                if(listeDesCase[i][this.position.y].piece == null){
                    ajouterSiValide(positions, i, this.position.y);
                } else {
                    if(!listeDesCase[i][this.position.y].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, i, this.position.y);
                    }
                    break;
                }
            }
            for(int i = this.position.y + 1; i < 8; i++){
                if(listeDesCase[this.position.x][i].piece == null){
                    ajouterSiValide(positions, this.position.x, i);
                } else {
                    if(!listeDesCase[this.position.x][i].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x, i);
                    }
                    break;
                }
            }
            for(int i = this.position.y - 1; i >= 0; i--){
                if(listeDesCase[this.position.x][i].piece == null){
                    ajouterSiValide(positions, this.position.x, i);
                } else {
                    if(!listeDesCase[this.position.x][i].piece.couleur.equals(this.couleur)){
                        ajouterSiValide(positions, this.position.x, i);
                    }
                    break;
                }
            }
            return new ArrayList<>(positions);
        }
        
        return new ArrayList<>();
    }
}