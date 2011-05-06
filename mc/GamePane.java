package mc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;


class GamePane extends JLayeredPane {

	private static JPanel baseLayer;
	private static PlayerTokensLayer playerTokensLayer;
	private static DistrictRollOverPanel districtRollOverPanel;
	private static JPanel mainHudPanel;
	
	private static JPanel messageLayer;
	private static MessagePanel messagePanel;

	
	private static JPanel hudButtonPanel;
	private static JPanel hudPanel1;
	private static JPanel hudPanel2;
	private static JPanel hudPanel3;
	private static CurrentPlayerPanel currentPlayerPanel;
	private static CurrentSquarePanel currentSquarePanel;
	
	private static JButton rollDiceButton;
	private static JButton buildButton;
	private static JButton endTurnButton;
	
	private static JButton getOutOfJailButton;
	private static JButton rentDodgeButton;
	private static JButton taxiButton;
	private static JButton taxDodgeButton;
	
	public int selectedDistrict;
	
	
	private static final GamePane GAMEPANE = new GamePane();

	public class DistrictRollOverPanel extends JPanel {
		
		ArrayList<Square> squares;		
		DistrictLabel label;
		
		public class DistrictLabel extends JLabel {
			
			String name;
			int position;			
			
			private class MouseHandler implements MouseListener {
				public void mouseClicked(MouseEvent event) {}
				
				public void mousePressed(MouseEvent e) {
					setIcon(new ImageIcon("images/buttonSelect.png"));
					int index = Integer.parseInt(getText());
					addMessagePanelText(
							GameMaster.getInstance().getBoard().getDistrict(index).getName());				
					setSelectedDistrict(index);
				}
				
				public void mouseReleased(MouseEvent e) {
					setIcon(new ImageIcon("images/buttonOver.png"));
				}
				
				public void mouseEntered(MouseEvent e) {
					setIcon(new ImageIcon("images/buttonOver.png"));
				}
				
				public void mouseExited(MouseEvent e) {
					setIcon(null);
				}
			}

			public DistrictLabel() {
				setOpaque(false);
				MouseHandler handler = new MouseHandler();
				addMouseListener(handler);	
			}

			public void setName(String name) {
				 this.name = name;
			}
			
			public void setIndex(int index) {
				this.position = index;
			}
		}
		
		public DistrictRollOverPanel() {
			setLayout(null);
			setOpaque(false);
			setBounds(0, 0, 768, 768);
			
			squares = GameMaster.getInstance().getBoard().getSquares();
			for (int i=0; i < squares.size(); i++) {
				if (squares.get(i).getType() == null) {
					District district = (District)squares.get(i);
					label = new DistrictLabel();
					label.setFont(new Font("Verdana", Font.ITALIC, 2));
					label.setText(Integer.toString(i));
					label.setForeground(Color.white);
					if (i > 0 && i < 10) {
						label.setBounds(district.getX(), district.getY()-40, 62, 104);
					}
					if (i > 10 && i < 20) {
						label.setBounds(district.getX(), district.getY()+20, 104, 62);
					}
					if (i > 20 && i < 30) {
						label.setBounds(district.getX(), district.getY(), 62, 104);
					}
					if (i > 30 && i <= 39) {
						label.setBounds(district.getX()-20, district.getY()+20, 104, 62);
					}
					add(label);
				}
			}
		}
	
	}
	
	public class PlayerTokensLayer extends JPanel {
		
		ArrayList<JLabel> playerTokens;
		
		public class PlayerToken extends JLabel {
			
			public PlayerToken() {
				setPreferredSize(new Dimension(20,20));
				setBounds(0,0,20,20);
				//setOpaque(true);
			}
		}
		
		public PlayerTokensLayer() {
			setLayout(null);
			//setPreferredSize(new Dimension(812,768));
			setOpaque(false);
			setBounds(0, 0, 768, 768);
			playerTokens = new ArrayList<JLabel>();

		}
		
		public void update() {
			removeAll();

			Player player = GameMaster.getInstance().getCurrentPlayer();
			Square currentSquare = GameMaster.getInstance().getBoard().getSquare(player.getPosition());
			Random r = new Random();			

			PlayerToken token = new PlayerToken();
			token.setToolTipText(player.getName());
			token.setIcon(new ImageIcon(player.getTokenFile()));
			// draw the player token with a random offset to allow 
			// pieces occupying the same square to be seen
			token.setBounds(currentSquare.getX() + r.nextInt(21) , 
							currentSquare.getY() + r.nextInt(18), 45, 45);
			if (playerTokens.size() == player.getIndex()) {
				playerTokens.add(token);

			} else {
				playerTokens.set(player.getIndex(), token);

			}
			

			for (JLabel label: playerTokens) {
					add(label);
			}
		}
		
	}
	
	public class GetOutOfJailButton extends JButton {
		
		public GetOutOfJailButton() {
			//setIcon(new ImageIcon("images/getOutOfJailFree.png"));
		    //setRolloverIcon(new ImageIcon("images/getOutOfJailFreeRollOver.png"));
		    //setRolloverEnabled(false);
		    setText("Get out of Jail");
			setPreferredSize(new Dimension(100, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("used Get Out Of Jail Card");
							//setEnabled(false);	
						}
					});
		}
	}
	
	public class TaxDodgeButton extends JButton {
		
		public TaxDodgeButton() {
			//setIcon(new ImageIcon("images/getOutOfJailFree.png"));
		    //setRolloverIcon(new ImageIcon("images/getOutOfJailFreeRollOver.png"));
		    //setRolloverEnabled(false);
		    setText("Tax Dodge");
			setPreferredSize(new Dimension(100, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("used Tax Dodge Card");
							//setEnabled(false);	
							//TODO: create logic for this
						}
					});
		}
	}
	
	public class RentDodgeButton extends JButton {
		
		public RentDodgeButton() {
		    setText("Rent Dodge Card");
		    //setIcon(new ImageIcon("images/rentDodge.png"));
		    //setRolloverIcon(new ImageIcon("images/rentDodgeRollOver.png"));
		    //setRolloverEnabled(true);
			setPreferredSize(new Dimension(100, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("used Rent Dodge Card");
							GameMaster.getInstance().useRentDodgeCard();
							//setEnabled(false);
						}
					});
		}
	}
	
	public class TaxiButton extends JButton {
		
		public TaxiButton() {
		    setText("Taxi Card");
			//setIcon(new ImageIcon("images/taxi.png"));
		    //setRolloverIcon(new ImageIcon("images/taxiRollOver.png"));
		    //setRolloverEnabled(true);
			setPreferredSize(new Dimension(100, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User used Taxi Card");
							GameMaster.getInstance().useTaxiCard();
						}
					});
		}
	}
	
	public class RollDiceButton extends JButton {
		
		public RollDiceButton() {
			setText("Roll Dice");
			setPreferredSize(new Dimension(120, 35));
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
							disableButton(getBuildButton());
							GameMaster.getInstance().startBuild();
							
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
							GameMaster.getInstance().endTurn();				
						}
					});
		}
	}	
	
	
	public class CurrentPlayerPanel extends JPanel {
		
		JTextArea playerDetails;
		JLabel playerToken;
		
		public CurrentPlayerPanel() {
			setLayout(new MigLayout());
			setPreferredSize(new Dimension(370,170));
			playerToken = new JLabel();
			playerDetails = new JTextArea();
			add(playerToken, "cell 0 0");
			add(playerDetails, "cell 1 0");			
		}
			
		public void update() {
			Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
			playerToken.setIcon(new ImageIcon(currentPlayer.getTokenFile()));
			playerDetails.setText(currentPlayer.getDetails());
			playerDetails.setFont(new Font("Verdana", Font.BOLD, 14));
			playerDetails.setLineWrap(true);
			playerDetails.setWrapStyleWord(true);
			playerDetails.setBounds(0, 0, 370, 170);
			playerDetails.setOpaque(false);
			revalidate();
			repaint();
		}
		
	}
	
	public class CurrentSquarePanel extends JPanel {
		
		JTextArea squareDetails;
		
		public CurrentSquarePanel() {
			setLayout(new MigLayout());
			squareDetails = new JTextArea();
			setPreferredSize(new Dimension(370,170));
			setOpaque(true);
			add(squareDetails, "cell 0 0");

		}
		
		public void update() {
			Board board = GameMaster.getInstance().getBoard();
			Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
			squareDetails.setText(board.getSquare(currentPlayer.getPosition()).toString());
			squareDetails.setFont(new Font("Verdana", Font.BOLD, 14));
			squareDetails.setLineWrap(true);
			squareDetails.setWrapStyleWord(true);
			squareDetails.setBounds(5, 5, 360, 160);
			squareDetails.setOpaque(false);
			revalidate();
			repaint();
		}
		
	}
	
	public class MessagePanel extends JPanel {
		
		JTextArea textArea;
		JPanel checkBoxPanel;
		JPanel buttonPanel;
		
		public MessagePanel() {
			setLayout(new MigLayout());
			
			setPreferredSize(new Dimension(400,300));
			setBorder(BorderFactory.createLineBorder(Color.black, 2));
			textArea = new JTextArea();
			textArea.setPreferredSize(new Dimension(400,100));
			textArea.setMargin(new Insets(10,10,10,10));
			textArea.setFont(new Font("Verdana", Font.BOLD, 16));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setOpaque(false);

			buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(400,100));

			checkBoxPanel = new JPanel();
			checkBoxPanel.setPreferredSize(new Dimension(400, 100));
			
			add(textArea, "cell 0 0");
			add(checkBoxPanel, "cell 0 1");
			add(buttonPanel, "cell 0 2");
		}
		
		public void setText(String str) {
			textArea.setText(str);
			textArea.revalidate();
			textArea.repaint();
		}
		
		
		public void addText(String str) {
			String oldString = textArea.getText();
			textArea.setText(oldString + "\n" + str);
			textArea.revalidate();
			textArea.repaint();
		}
		
		public void addButton(JButton btn) {
			buttonPanel.add(btn);
			buttonPanel.revalidate();
			buttonPanel.repaint();
		}
			
		public void addCheckBox(JCheckBox checkBox) {
			checkBox.setFont(new Font("Verdana", Font.BOLD, 16));
			checkBoxPanel.add(checkBox);
			checkBoxPanel.revalidate();
			checkBoxPanel.repaint();
		
		}
	}
	
	
	private GamePane() {
		
		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 1200, 800);
		baseLayer.setOpaque(true);

		
		ImageIcon icon = new ImageIcon("images/board-small.png");
		JLabel board = new JLabel(icon);
		board.setVerticalAlignment(SwingConstants.TOP);
		board.setPreferredSize(new Dimension(768,768));
		board.setOpaque(true);
			
		playerTokensLayer = new PlayerTokensLayer();
			
		mainHudPanel = new JPanel();
		mainHudPanel.setLayout(null);
		mainHudPanel.setPreferredSize(new Dimension(432,768));
		mainHudPanel.setOpaque(false);

		hudPanel1 = new JPanel();
		hudPanel1.setBounds(0, 0, 432, 200);
		hudPanel1.setBorder(BorderFactory.createTitledBorder("Current Square"));		
		currentSquarePanel = new CurrentSquarePanel();
		hudPanel1.add(currentSquarePanel);

		hudPanel2 = new JPanel();
		hudPanel2.setBounds(0, 201, 432, 200);
		hudPanel2.setBorder(BorderFactory.createTitledBorder("Current Player"));
		currentPlayerPanel = new CurrentPlayerPanel();
		hudPanel2.add(currentPlayerPanel);

		
		rollDiceButton = new RollDiceButton();
		buildButton = new BuildButton();
		endTurnButton = new EndTurnButton();
		
		hudButtonPanel = new JPanel();
		hudButtonPanel.setBounds(0, 401, 432, 50);
		hudButtonPanel.add(rollDiceButton);
		hudButtonPanel.add(buildButton);
		hudButtonPanel.add(endTurnButton);


		
		getOutOfJailButton = new GetOutOfJailButton();
		rentDodgeButton = new RentDodgeButton();
		taxiButton = new TaxiButton();
		taxDodgeButton = new TaxDodgeButton();
		
		hudPanel3 = new JPanel();
		hudPanel3.setBounds(0, 451, 432, 118);
		hudPanel3.setBorder(BorderFactory.createTitledBorder("Chance Cards"));
		hudPanel3.add(getOutOfJailButton);
		hudPanel3.add(rentDodgeButton);
		hudPanel3.add(taxDodgeButton);
		hudPanel3.add(taxiButton);
		
		mainHudPanel.add(hudPanel1);
		mainHudPanel.add(hudButtonPanel);
		mainHudPanel.add(hudPanel2);
		mainHudPanel.add(hudPanel3);
		
		
		messageLayer = new JPanel(new GridBagLayout());
		messageLayer.setBounds(0, 0, 768, 767);
		messageLayer.setOpaque(false);
			
		baseLayer.add(board, BorderLayout.WEST);	
		baseLayer.add(mainHudPanel, BorderLayout.EAST);
			
		add(baseLayer, new Integer(0));
		add(playerTokensLayer, new Integer(1));
		add(messageLayer, new Integer(2));	
	}
	

	
	public static GamePane getInstance() {
		return GAMEPANE;
	}
	
	public void clearMessageLayer() {
		System.out.println("clearing messageLayer");
		messageLayer.removeAll();
		messageLayer.revalidate();
		messageLayer.repaint();
	}
	
	public void setMessagePanelText(String str) {
		messagePanel = new MessagePanel();
		messagePanel.setText(str);
		messageLayer.add(messagePanel);
	}
	
	public void addMessagePanelText(String str) {
		System.out.println("adding text");
		messagePanel.addText(str);
	}
	
	public void addMessagePanelButton(JButton btn) {
		messagePanel.addButton(btn);
	}
	
	public void addMessagePanelCheckBox(JCheckBox checkBox) {
		messagePanel.addCheckBox(checkBox);
	}
	
	public void enableButton(JButton btn) {
		btn.setEnabled(true);
	}
	
	public void disableButton(JButton btn) {
		btn.setEnabled(false);
	}
	
	public void hideButton(JButton btn) {
		btn.setVisible(false);
	}
	
	public void showButton(JButton btn) {
		btn.setVisible(true);
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
	
	public JButton getGetOutOfJailButton() {
		return getOutOfJailButton;
	}
	
	public JButton getRentDodgeButton() {
		return rentDodgeButton;
	}
	
	public JButton getTaxDodgeButton() {
		return taxDodgeButton;
	}
	
	public JButton getTaxiButton() {
		return taxiButton;
	}
	
	public void addSelectionLayer() {
		districtRollOverPanel = new DistrictRollOverPanel();
		add(districtRollOverPanel, new Integer(3));
		System.out.println("adding selcetion layer");
		revalidate();
		repaint();
	}
	
	public void setSelectedDistrict(int index) {
		selectedDistrict = index;
	}
	
	public int getSelectedDistrict() {
		return selectedDistrict;
	}
	
	public void update() {
		currentPlayerPanel.update();
		currentSquarePanel.update();
		playerTokensLayer.update();
		revalidate();
		repaint();
	}
	
}