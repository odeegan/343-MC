package mc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GamePane extends JLayeredPane {

	private static JPanel baseLayer;
	private static JPanel playerTokensPanel;
	private static JPanel squaresPanel;
	private static JPanel mainHudPanel;
	
	private static JPanel messageLayer;
	
	private static JPanel hudButtonPanel;
	private static JPanel hudPanel1;
	private static JPanel hudPanel2;
	private static JPanel hudPanel3;
	
	private static JButton rollDiceButton;
	private static JButton buildButton;
	private static JButton endTurnButton;
	
	private static final GamePane GAMEPANE = new GamePane();
	
	public class RollDiceButton extends JButton {
		
		public RollDiceButton() {
			setText("Roll Dice");
			setPreferredSize(new Dimension(120,35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked RollDice");
							setEnabled(false);
							GameMaster.getInstance().roll();
							
						}
					});
		}
	}
	
	public class BuildButton extends JButton {
		
		public BuildButton() {
			setText("Build");
			setPreferredSize(new Dimension(120,35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked Build");
						}
					});
		}
	}
	
	public class EndTurnButton extends JButton {
		
		public EndTurnButton() {
			setText("End Turn");
			setPreferredSize(new Dimension(120,35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked EndTurn");
						}
					});
		}
	}	
	private GamePane() {
		
		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 1200, 800);
		//baseLayer.setBackground(Color.red);
		baseLayer.setOpaque(true);

		
		ImageIcon icon = new ImageIcon("images/board-small.png");
		JLabel board = new JLabel(icon);
		board.setVerticalAlignment(SwingConstants.TOP);
		board.setPreferredSize(new Dimension(812,768));
		board.setOpaque(true);
			
		playerTokensPanel = new JPanel();
		playerTokensPanel.setOpaque(false);
		
		mainHudPanel = new JPanel(new GridBagLayout());
		mainHudPanel.setLayout(null);
		mainHudPanel.setPreferredSize(new Dimension(388,767));
		mainHudPanel.setOpaque(false);

		
		hudButtonPanel = new JPanel();
		hudButtonPanel.setBounds(0, 200, 388, 50);
		rollDiceButton = new RollDiceButton();
		endTurnButton = new BuildButton();
		buildButton = new EndTurnButton();
		
		
		hudButtonPanel.add(rollDiceButton);
		hudButtonPanel.add(endTurnButton);
		hudButtonPanel.add(buildButton);


		
		messageLayer = new JPanel(new GridBagLayout());
		messageLayer.setBounds(0, 0, 812, 767);
		messageLayer.setOpaque(false);
		
		
		baseLayer.add(board, BorderLayout.WEST);
			
		mainHudPanel.add(hudButtonPanel);
		baseLayer.add(mainHudPanel, BorderLayout.EAST);
			
		add(baseLayer, new Integer(0));
		add(messageLayer, new Integer(1));	
		}
	
	public static GamePane getInstance() {
		return GAMEPANE;
	}
	
	
	public void clearMessageLayer() {
		System.out.println("clearing messageLayer");
		messageLayer.removeAll();
		//messageLayer.revalidate();
		//messageLayer.repaint();
	}
	
	public void setMessageLayer(JPanel jp) {
		clearMessageLayer();
		System.out.println("setting messageLayer");
		messageLayer.add(jp);
		messageLayer.revalidate();
		messageLayer.repaint();
	}
	
	public void enableButton(JButton btn) {
		btn.setEnabled(true);
	}
	
	public void disableButton(JButton btn) {
		btn.setEnabled(false);
	}
	
	public JButton getRollDiceButton() {
		return rollDiceButton;
	}
	
	public JButton getBuildButton() {
		return buildButton;
	}
	
	public JButton getEndTurnButton() {
		return endTurnButton;
	}
	
	
}