public class Main{

    public static Piece pieceSelectione;

    public static String tourDeRole = "blanc";

    public static boolean estPair(int nombre) {
        return nombre % 2 == 0;
    }

    private static Piece findPiece(int x, int y){
        Piece[] pieces = initialisationDesPieces();
        for (int i = 0; i < pieces.length; i++) {
            if(pieces[i].position.x==x && pieces[i].position.y==y){
                return pieces[i];
            }
        }
        return null;
    }

    private static Case[][] generationMatriceDesCase(Table table){
        Case[][] cases = new Case[8][8];
        Piece[] pieces = initialisationDesPieces();

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                if(estPair(i+j)){
                    cases[i][j] = new Case("blanc", findPiece(i, j), table, new Position(i, j));
                }else if(!estPair(i+j)){
                    cases[i][j] = new Case("marron", findPiece(i, j), table, new Position(i, j));
                }
            }
        }

        return cases;
    }

    private static Piece[] initialisationDesPieces() {
        Piece[] pieces = new Piece[32];
        try {
            pieces[0] = new Piece("images/tour_blanc.png", "Tour", new Position(0, 0), "blanc");
            pieces[1] = new Piece("images/cavalier_blanc.png", "Cavalier", new Position(0, 1), "blanc");
            pieces[2] = new Piece("images/fou_blanc.png", "Fou", new Position(0, 2), "blanc");
            pieces[3] = new Piece("images/roi_blanc.png", "Dame", new Position(0, 3), "blanc");
            pieces[4] = new Piece("images/dame_blanc.png", "Roi", new Position(0, 4), "blanc");
            pieces[5] = new Piece("images/fou_blanc.png", "Fou", new Position(0, 5), "blanc");
            pieces[6] = new Piece("images/cavalier_blanc.png", "Cavalier", new Position(0, 6), "blanc");
            pieces[7] = new Piece("images/tour_blanc.png", "Tour", new Position(0, 7), "blanc");
            for (int i = 0; i < 8; i++) {
                pieces[8 + i] = new Piece("images/pion_blanc.png", "Pion", new Position(1, i), "blanc");
            }
            pieces[16] = new Piece("images/tour_noir.png", "Tour", new Position(7, 0), "noir");
            pieces[17] = new Piece("images/cavalier_noir.png", "Cavalier", new Position(7, 1), "noir");
            pieces[18] = new Piece("images/fou_noir.png", "Fou", new Position(7, 2), "noir");
            pieces[19] = new Piece("images/dame_noir.png", "Dame", new Position(7, 3), "noir");
            pieces[20] = new Piece("images/roi_noir.png", "Roi", new Position(7, 4), "noir");
            pieces[21] = new Piece("images/fou_noir.png", "Fou", new Position(7, 5), "noir");
            pieces[22] = new Piece("images/cavalier_noir.png", "Cavalier", new Position(7, 6), "noir");
            pieces[23] = new Piece("images/tour_noir.png", "Tour", new Position(7, 7), "noir");
            for (int i = 0; i < 8; i++) {
                pieces[24 + i] = new Piece("images/pion_noir.png", "Pion", new Position(6, i), "noir");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pieces;
    }


    public static void main(String[] args){
        Table table = new Table(initialisationDesPieces());
        table.setCases(generationMatriceDesCase(table));
        new Fenetre(table);
    }
}