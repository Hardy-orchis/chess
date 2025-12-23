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
    boolean premierCout;

    Piece(String image, String nom, Position position, String couleur) throws IOException {
        this.nom = nom;
        this.image = image;
        this.position = position;
        this.couleur = couleur;
        this.premierCout = true;
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


    private void ajouterMouvementLigne(Case[][] listeDesCase, List<Position> positions, int dx, int dy) {
        int x = this.position.x;
        int y = this.position.y;

        while (true) {
            x += dx;
            y += dy;

            if (x < 0 || x >= 8 || y < 0 || y >= 8) break;
            if (listeDesCase[x][y].piece == null) {
                ajouterSiValide(positions, x, y);
            } else {
                if (!listeDesCase[x][y].piece.couleur.equals(this.couleur)) {
                    ajouterSiValide(positions, x, y);
                }
                break;
            }
        }
    }

    private void ajouterMouvementDiagonale(Case[][] listeDesCase, List<Position> positions, int dx, int dy) {
        int x = this.position.x;
        int y = this.position.y;

        while (true) {
            x += dx;
            y += dy;

            if (x < 0 || x >= 8 || y < 0 || y >= 8) break;
            if (listeDesCase[x][y].piece == null) {
                ajouterSiValide(positions, x, y);
            } else {
                if (!listeDesCase[x][y].piece.couleur.equals(this.couleur)) {
                    ajouterSiValide(positions, x, y);
                }
                break;
            }
        }
    }

    public ArrayList<Position> listeDesMouvementPossible(Case[][] listeDesCase) {
        List<Position> positions = new ArrayList<>();

        if ("Pion".equals(this.nom)) {
            int direction = "noir".equals(this.couleur) ? -1 : 1;
            int startRow = this.position.x + direction;

            if (!this.premierCout) {
                if (listeDesCase[startRow][this.position.y].piece == null) {
                    ajouterSiValide(positions, startRow, this.position.y);
                }

                if (this.position.y + 1 < 8 && listeDesCase[startRow][this.position.y + 1].piece != null) {
                    ajouterSiValide(positions, startRow, this.position.y + 1);
                }
                if (this.position.y - 1 >= 0 && listeDesCase[startRow][this.position.y - 1].piece != null) {
                    ajouterSiValide(positions, startRow, this.position.y - 1);
                }
            } else {
                if (listeDesCase[startRow][this.position.y].piece == null) {
                    ajouterSiValide(positions, startRow, this.position.y);
                    int doubleMoveRow = this.position.x + 2 * direction;
                    if (listeDesCase[doubleMoveRow][this.position.y].piece == null) {
                        ajouterSiValide(positions, doubleMoveRow, this.position.y);
                    }
                }

                if (this.position.y + 1 < 8 && listeDesCase[startRow][this.position.y + 1].piece != null) {
                    ajouterSiValide(positions, startRow, this.position.y + 1);
                }
                if (this.position.y - 1 >= 0 && listeDesCase[startRow][this.position.y - 1].piece != null) {
                    ajouterSiValide(positions, startRow, this.position.y - 1);
                }
            }
        }

        if ("Cavalier".equals(this.nom)) {
            int[][] mouvements = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
            };

            for (int[] mouv : mouvements) {
                int newX = this.position.x + mouv[0];
                int newY = this.position.y + mouv[1];

                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    if (listeDesCase[newX][newY].piece == null || 
                    !listeDesCase[newX][newY].piece.couleur.equals(this.couleur)) {
                        ajouterSiValide(positions, newX, newY);
                    }
                }
            }
        }

        if ("Roi".equals(this.nom)) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        int newX = this.position.x + dx;
                        int newY = this.position.y + dy;
                        if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                            if (listeDesCase[newX][newY].piece == null || 
                            !listeDesCase[newX][newY].piece.couleur.equals(this.couleur)) {
                                ajouterSiValide(positions, newX, newY);
                            }
                        }
                    }
                }
            }
        }

        if ("Dame".equals(this.nom)) {
            ajouterMouvementLigne(listeDesCase, positions, 1, 0);  
            ajouterMouvementLigne(listeDesCase, positions, -1, 0); 
            ajouterMouvementLigne(listeDesCase, positions, 0, 1);  
            ajouterMouvementLigne(listeDesCase, positions, 0, -1); 
            ajouterMouvementDiagonale(listeDesCase, positions, 1, 1);  
            ajouterMouvementDiagonale(listeDesCase, positions, -1, 1); 
            ajouterMouvementDiagonale(listeDesCase, positions, 1, -1); 
            ajouterMouvementDiagonale(listeDesCase, positions, -1, -1); 
        }

        if ("Fou".equals(this.nom)) {
            ajouterMouvementDiagonale(listeDesCase, positions, 1, 1); 
            ajouterMouvementDiagonale(listeDesCase, positions, -1, 1); 
            ajouterMouvementDiagonale(listeDesCase, positions, 1, -1); 
            ajouterMouvementDiagonale(listeDesCase, positions, -1, -1); 
        }

        if ("Tour".equals(this.nom)) {
            ajouterMouvementLigne(listeDesCase, positions, 1, 0);  
            ajouterMouvementLigne(listeDesCase, positions, -1, 0); 
            ajouterMouvementLigne(listeDesCase, positions, 0, 1); 
            ajouterMouvementLigne(listeDesCase, positions, 0, -1);
        }

        return new ArrayList<>(positions);
    }

}