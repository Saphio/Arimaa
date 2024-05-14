package arimaa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

public class Board extends JFrame implements ActionListener, MouseListener {
	
	/////// SETUP ///////
	// organization
	JFrame frame = new JFrame("Arimaa");
	GameControl game = new GameControl();
	Container east = new Container();
	Container south = new Container();
	Container north = new Container();
	
	// buttons
	JButton rabbit = new JButton("Rabbit");
	JButton cat = new JButton("Cat");
	JButton dog = new JButton("Dog");
	JButton horse = new JButton("Horse");
	JButton camel = new JButton("Camel");
	JButton elephant = new JButton("Elephant");
	JButton push = new JButton("Push");
	JButton pull = new JButton("Pull");
	JButton endTurn = new JButton("End turn");
	JButton newGame = new JButton("New game");
	
	// labels
	JLabel curPlayerLbl = new JLabel("Gold's turn", SwingConstants.CENTER);
	JLabel movesLeftLbl = new JLabel("4 moves left", SwingConstants.CENTER);
	JLabel addPiecesLbl = new JLabel("Add pieces");
	
	/////// CONSTRUCTOR ///////
	public Board () {
		// frame
		frame.setSize(1000, 900);
		frame.setLayout(new BorderLayout());
		
		// center
		game.addMouseListener(this);
		frame.add(game, BorderLayout.CENTER);
		
		// east container
		east.setLayout(new GridLayout(7, 1));
		east.add(addPiecesLbl);
		east.add(elephant);
		east.add(camel);
		east.add(horse);
		east.add(dog);
		east.add(cat);
		east.add(rabbit);
		elephant.addActionListener(this);
		camel.addActionListener(this);
		horse.addActionListener(this);
		dog.addActionListener(this);
		cat.addActionListener(this);
		rabbit.addActionListener(this);
		frame.add(east, BorderLayout.EAST);
		
		// south container
		south.setLayout(new GridLayout(1, 2));
		south.add(push);
		south.add(pull);
		push.addActionListener(this);
		pull.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		// north container
		north.setLayout(new GridLayout(2, 2));
		north.add(endTurn);
		north.add(newGame);
		endTurn.addActionListener(this);
		newGame.addActionListener(this);
		north.add(curPlayerLbl);
		north.add(movesLeftLbl);
		frame.add(north, BorderLayout.NORTH);
		
		// frame functions
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// TODO!
	// mouse listeners
	public void mouseReleased(MouseEvent e) {
		
	}
	
	// action listeners
	public void actionPerformed(ActionEvent e) {
		
	}

	/////// MAIN ///////
	public static void main (String[] args) {
		new Board();
	}
	
	/////// UNUSED DEFAULT METHODS ///////
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
