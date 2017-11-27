package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class MiniGameScreen extends JPanel{
	BufferedImage fishImage;

    private static final Random r = new Random();
    private int n;
    private Color color = new Color(r.nextInt());

    public MiniGameScreen(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(Color.RED);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        //this.addMouseListener(new MouseHandler(this));
    }

    @Override
    public void paintComponent(Graphics g) {
    	BufferedImage bi = createBufferedImage("src/view/images/foodsmall.png");
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setColor(color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        //g2d.setColor(Color.black);
        //g2d.drawString(String.valueOf(n), 5, getHeight() - 5);
        g2d.drawImage(bi, 20, 20, this);
    }

    public void update() {
        color = new Color(r.nextInt());
        repaint();
    }
    
    public BufferedImage createBufferedImage(String fileLocation){
		BufferedImage img;
		try {
			img = ImageIO.read(new File(fileLocation));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

