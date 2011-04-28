package mc;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class MainMenuState implements GameState{

	GameStateMachine gameStateMachine;
	GameMaster gameMaster;	
	JFrame mainFrame;
	JComponent layeredPane;
	JPanel userMessageLayer;
	JPanel startGameMenu;
	JPanel selectNumPlayersMenu;
	
	public class StartGameMenu extends JPanel {
		
		public StartGameMenu() {
			setPreferredSize(new Dimension(450,200));
			// make the background transparent
			setOpaque(false);

			JButton newGameButton = new JButton("Start New Game");
			newGameButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							userMessageLayer.removeAll();
							selectNumberOfPlayers();
						}
					});
			newGameButton.setPreferredSize(new Dimension(200, 50));
			JButton exitGameButton = new JButton("Exit");
			exitGameButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.exit(0);
						}
					});
			exitGameButton.setPreferredSize(new Dimension(200, 50));
			
			// add our buttons to the game menu
			add(newGameButton);
			add(exitGameButton);
		}
	}		
	
	public class SelectNumPlayersMenu extends JPanel {
		
		public SelectNumPlayersMenu() {
			setBorder(BorderFactory.createLineBorder(Color.black, 1));
			setPreferredSize(new Dimension(450,200));
	    	
			// add a layout to the menu
			setLayout(new BorderLayout());
			
	    	JLabel message = new JLabel("<html>Select the number of Players.</html>", JLabel.CENTER);
	    	message.setFont(new Font("Serif", Font.BOLD, 24));
	    	message.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    	add(message, BorderLayout.NORTH);

			JPanel buttonPanel = new JPanel();
	    	int ii;
	    	for (ii=2; ii<=6; ii++) {
	    		JButton button = createNumPlayersButton(ii);
	    		button.setPreferredSize(new Dimension(75, 120));
	    		buttonPanel.add(button);
	    	}
	       	add(buttonPanel, BorderLayout.SOUTH);
	    	setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
		
		private JButton createNumPlayersButton(int num) {
			JButton button = new JButton(Integer.toString(num));
			button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                //Execute when button is pressed
	            	JButton btn = (JButton)e.getSource();
	            	System.out.println("Players Selected = " + btn.getText());
	            	gameMaster.setNumPlayers(Integer.parseInt(btn.getText()));
	            	gameStateMachine.setState(gameStateMachine.getGamePlayState());
	            }
			});
			return button;
		}	
	}
	
	public MainMenuState(GameStateMachine gsm, GameMaster gm, JFrame mf) {
		gameStateMachine = gsm;
		gameMaster = gm;
		mainFrame = mf;
		
		// create the main layered pane
		// this will allow our page elements to overlap
		layeredPane = new JLayeredPane();
		
		// add the background image to the layeredPane
		ImageIcon icon = new ImageIcon("images/mainmenu.png");
		JLabel background = new JLabel(icon);
		background.setOpaque(true);
		background.setBounds(0,0, 1100, 768);
		
		layeredPane.add(background, new Integer(0), 1);

		// create a layer for our menus to appear in
		// the GridBayLayout is crazy powerful, but for our simple purposes
		// it defaults to centering whatever element you place in it
		userMessageLayer = new JPanel(new GridBagLayout());
		
		// make the JPanel the size of the entire window to ensure whatever
		// element we place in it is centered
		userMessageLayer.setBounds(0, 0, 1100, 768);
		userMessageLayer.setOpaque(false);		
		layeredPane.add(userMessageLayer, new Integer(1), 0);
	}
	
	public void enter() {
		System.out.println("This is the MainMenuState");
		StartGameMenu startGameMenu = new StartGameMenu();
		userMessageLayer.add(startGameMenu);
		
		// replace the JFrame's main content pane with our layeredPane
		mainFrame.setContentPane(layeredPane);
		mainFrame.setVisible(true);				
	}

	public void selectNumberOfPlayers() {
		System.out.println("Select the Number of Players");
		SelectNumPlayersMenu selectNumPlayersMenu = new SelectNumPlayersMenu(); 
		//selectNumPlayersMenu.setVisible(true);
		userMessageLayer.add(selectNumPlayersMenu);
		userMessageLayer.revalidate();
	}
}






