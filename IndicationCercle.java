import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class IndicationCercle extends JPanel {
    private float alpha = 0.6f; 
    
    public IndicationCercle() {
        setPreferredSize(new Dimension(50, 50));
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int size = Math.min(getWidth(), getHeight());
        int circleSize = (int)(size * 0.95);
        int x = (getWidth() - circleSize) / 2;
        int y = (getHeight() - circleSize) / 2;
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        
        Point2D center = new Point2D.Float(getWidth() / 2f, getHeight() / 2f);
        float radius = circleSize / 2f;
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(100, 200, 100, 200), new Color(50, 150, 50, 150)};
        RadialGradientPaint gradient = new RadialGradientPaint(center, radius, dist, colors);
        
        g2.setPaint(gradient);
        g2.fillOval(x, y, circleSize, circleSize);
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha * 0.8f));
        g2.setColor(new Color(80, 180, 80));
        g2.setStroke(new BasicStroke(2f));
        g2.drawOval(x, y, circleSize, circleSize);
    }
}