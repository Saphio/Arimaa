package arimaa;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameControl extends JPanel {
	Image gb; 
	public GameControl () {
		super();
		try {
		gb = ImageIO.read(new File ("//home//saphio//Downloads//arimaaGameBoard.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 
		g.drawImage(gb, 0, 0, null); // paints the game board
		g.drawLine(0,0,500,500); // just testing things out
	}
}
