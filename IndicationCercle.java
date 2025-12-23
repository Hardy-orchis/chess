import javax.swing.*;
import java.awt.*;

public class IndicationCercle extends JPanel {

    public IndicationCercle() {
        setPreferredSize(new Dimension(50, 50));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setColor(Color.green);
        
        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        g2.fillOval(x, y, size, size);
    }
}
