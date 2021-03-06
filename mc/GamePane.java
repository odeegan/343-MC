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
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import net.miginfocom.swing.MigLayout;


class GamePane extends JLayeredPane {

	private static JPanel baseLayer;
	private static PlayerTokensLayer playerTokensLayer;
	private static DistrictRollOverPanel districtRollOverPanel;
	private static JPanel mainHudPanel;
	
	private static JPanel messageLayer;
	private static MessagePanel messagePanel;
	private static JPanel dicePanel;
	
	private static JPanel hudButtonPanel;
	private static JTabbedPane hudPanel1;
	private static JPanel hudPanel3;
	private static NetworthPanel networthPanel;
	private static CurrentPlayerPanel currentPlayerPanel;
	private static DistrictsPanel districtsPanel;
	private static DistrictElementsPanel districtElementsPanel;
	private static StructureCountPanel	structureCountPanel;
	
	private static JButton rollDiceButton;
	private static JButton buildButton;
	private static JButton tradeButton;
	private static JButton endTurnButton;
	
	private static JButton getOutOfJailButton;
	private static JButton rentDodgeButton;
	private static JButton taxiButton;
	private static JButton taxDodgeButton;
	
	public int selectedDistrict;
	
	
	private static final GamePane GAMEPANE = new GamePane();

	
	public class NetworthPanel extends JPanel {
		
		public NetworthPanel() {
			setPreferredSize(new Dimension(432,120));
		}
		
		public void update() {
			GameMaster.getInstance().resumeTurn();
			removeAll();
			setBorder(BorderFactory.createTitledBorder("Networth"));
			ArrayList<Player> players = GameMaster.getInstance().getPlayers();
			double winning = 0.0;
			for (Player player: players) {
				if (winning < player.getNetWorth()) {
					winning = player.getNetWorth();
				}
			}
				
			for (Player player: players) {
				JTextArea text = new JTextArea();
				if (player.getNetWorth() == winning) {
					text.setForeground(Color.black);
				} else {
					text.setForeground(Color.gray);
				}
				text.setFont(new Font("Verdana", Font.BOLD, 16));
				text.setOpaque(false);
				text.setPreferredSize(new Dimension(100, 40));
				text.setBorder(BorderFactory.createTitledBorder(player.getName()));
				text.setText(Double.toString(player.getNetWorth()));
				add(text);
			}
		}
	}
	
	
	public class DistrictElementsPanel extends JPanel {
		
		ArrayList<Square> squares;
		
		public DistrictElementsPanel() {
			setLayout(null);
			setOpaque(false);
			setBounds(0, 0, 768, 768);
		}
		
		public void update() {
			
			squares = GameMaster.getInstance().getBoard().getSquares();
						
			removeAll();
			
			for (int i=0; i < squares.size(); i++) {
				if (squares.get(i).getType() == SQUARETYPE.DISTRICT) {
					District district = (District)squares.get(i);
					
					if (district.getOwner() != null) {
						JLabel ownerLabel = new JLabel();
						ownerLabel.setIcon(new ImageIcon("images/dot" + district.getOwner().getIndex() + ".png"));
						ownerLabel.setVisible(true);
						if (i > 0 && i < 10) {
							ownerLabel.setBounds(district.getX()+4, district.getY()-50, 30, 30);
						}
						if (i > 10 && i < 20) {
							ownerLabel.setBounds(district.getX()+77, district.getY()+25, 30, 30);
						}
						if (i > 20 && i < 30) {
							ownerLabel.setBounds(district.getX()+5, district.getY()+75, 30, 30);
						}
						if (i > 30 && i <= 39) {
							ownerLabel.setBounds(district.getX()-48, district.getY()+28, 30, 30);
						}
						add(ownerLabel);
					}
					
					if (district.isRailroaded()) {
						JLabel railroad = new JLabel();
						railroad.setIcon(new ImageIcon("images/train.png"));
						railroad.setVisible(true);
						if (i > 0 && i < 10) {
							railroad.setBounds(district.getX()+4, district.getY()-80, 30, 30);
						}
						if (i > 10 && i < 20) {
							railroad.setBounds(district.getX()+108, district.getY()+16, 30, 30);
						}
						if (i > 20 && i < 30) {
							railroad.setBounds(district.getX()+18, district.getY()+100, 30, 30);
						}
						if (i > 30 && i <= 39) {
							railroad.setBounds(district.getX()-80, district.getY()+16, 30, 30);
						}
						add(railroad);
					}
					if (district.getResidentialBlockCount() > 0) {
						JLabel residential = new JLabel();
						residential.setIcon(new ImageIcon("images/house.png"));
						residential.setVisible(true);
						if (i > 0 && i < 10) {
							residential.setBounds(district.getX(), district.getY()+28, 30, 30);
						}
						if (i > 10 && i < 20) {
							residential.setBounds(district.getX(), district.getY()+30, 30, 30);
						}
						if (i > 20 && i < 30) {
							residential.setBounds(district.getX(), district.getY()+30, 30, 30);
						}
						if (i > 30 && i <= 39) {
							residential.setBounds(district.getX(), district.getY()+30, 30, 30);
						}
						add(residential);
					}
					if (district.getIndustrialBlockCount() > 0) {
						JLabel industrial = new JLabel();
						industrial.setIcon(new ImageIcon("images/factory.png"));
						industrial.setVisible(true);
						if (i > 0 && i < 10) {
							industrial.setBounds(district.getX(), district.getY(), 30, 30);
						}
						if (i > 10 && i < 20) {
							industrial.setBounds(district.getX(), district.getY(), 30, 30);
						}
						if (i > 20 && i < 30) {
							industrial.setBounds(district.getX(), district.getY(), 30, 30);
						}
						if (i > 30 && i <= 39) {
							industrial.setBounds(district.getX(), district.getY(), 30, 30);
						}
						add(industrial);
					}
					if (district.isHazarded()) {
						JLabel hazard = new JLabel();
						hazard.setIcon(new ImageIcon("images/hazard.png"));
						hazard.setVisible(true);
						if (i > 0 && i < 10) {
							hazard.setBounds(district.getX()+32, district.getY(), 30, 30);
						}
						if (i > 10 && i < 20) {
							hazard.setBounds(district.getX()+31, district.getY(), 30, 30);
						}
						if (i > 20 && i < 30) {
							hazard.setBounds(district.getX()+31, district.getY(), 30, 30);
						}
						if (i > 30 && i <= 39) {
							hazard.setBounds(district.getX()+32, district.getY(), 30, 30);
						}
						add(hazard);
					}
					if (district.isBonused()) {
						JLabel bonus = new JLabel();
						bonus.setIcon(new ImageIcon("images/heart.png"));
						bonus.setVisible(true);
						if (i > 0 && i < 10) {
							bonus.setBounds(district.getX()+32, district.getY()+30, 30, 30);
						}
						if (i > 10 && i < 20) {
							bonus.setBounds(district.getX()+31, district.getY()+30, 30, 30);
						}
						if (i > 20 && i < 30) {
							bonus.setBounds(district.getX()+31, district.getY()+30, 30, 30);
						}
						if (i > 30 && i <= 39) {
							bonus.setBounds(district.getX()+32, district.getY()+30, 30, 30);
						}
						add(bonus);
					}
					
					//TODO: Here you go Nick
					
/*					if (district.isSkyscrapered()) {
						JLabel skyscraper = new JLabel();
						skyscraper.setIcon(new ImageIcon("images/skyscraper.png"));
						skyscraper.setVisible(true);
						if (i > 0 && i < 10) {
							skyscraper.setBounds(district.getX()+4, district.getY()-80, 30, 30);
						}
						if (i > 10 && i < 20) {
							skyscraper.setBounds(district.getX()+108, district.getY()+16, 30, 30);
						}
						if (i > 20 && i < 30) {
							skyscraper.setBounds(district.getX()+18, district.getY()+100, 30, 30);
						}
						if (i > 30 && i <= 39) {
							skyscraper.setBounds(district.getX()-80, district.getY()+16, 30, 30);
						}
						add(skyscraper);
					}
					if (district.isMonopolyTowered()) {
						JLabel monopolyTower = new JLabel();
						monopolyTower.setIcon(new ImageIcon("images/skyscraper.png"));
						monopolyTower.setVisible(true);
						if (i > 0 && i < 10) {
							monopolyTower.setBounds(district.getX()+4, district.getY()-80, 30, 30);
						}
						if (i > 10 && i < 20) {
							monopolyTower.setBounds(district.getX()+108, district.getY()+16, 30, 30);
						}
						if (i > 20 && i < 30) {
							monopolyTower.setBounds(district.getX()+18, district.getY()+100, 30, 30);
						}
						if (i > 30 && i <= 39) {
							monopolyTower.setBounds(district.getX()-80, district.getY()+16, 30, 30);
						}
						add(monopolyTower);
					}
*/
				}
			}
		}
	}
	
	public class StructureCountPanel extends JPanel {
		
		ArrayList<Square> squares;
		
		public StructureCountPanel() {
			setLayout(null);
			setOpaque(false);
			setBounds(0, 0, 768, 768);
		}
		
		public void update() {
			
			squares = GameMaster.getInstance().getBoard().getSquares();
						
			removeAll();
			
			for (int i=0; i < squares.size(); i++) {
				if (squares.get(i).getType() == SQUARETYPE.DISTRICT) {
					District district = (District)squares.get(i);
					

					if (district.getResidentialBlockCount() > 0) {
						JLabel residential = new JLabel();
						residential.setText(Integer.toString(district.getResidentialBlockCount()));
						residential.setVisible(true);
						if (i > 0 && i < 10) {
							residential.setBounds(district.getX()+11, district.getY()+33, 30, 30);
						}
						if (i > 10 && i < 20) {
							residential.setBounds(district.getX()+11, district.getY()+35, 30, 30);
						}
						if (i > 20 && i < 30) {
							residential.setBounds(district.getX()+11, district.getY()+35, 30, 30);
						}
						if (i > 30 && i <= 39) {
							residential.setBounds(district.getX()+11, district.getY()+35, 30, 30);
						}
						add(residential);
					}
					if (district.getIndustrialBlockCount() > 0) {
						JLabel industrial = new JLabel();
						industrial.setText(Integer.toString(district.getIndustrialBlockCount()));
						industrial.setVisible(true);
						if (i > 0 && i < 10) {
							industrial.setBounds(district.getX()+17, district.getY()+3, 30, 30);
						}
						if (i > 10 && i < 20) {
							industrial.setBounds(district.getX()+17, district.getY()+3, 30, 30);
						}
						if (i > 20 && i < 30) {
							industrial.setBounds(district.getX()+17, district.getY()+3, 30, 30);
						}
						if (i > 30 && i <= 39) {
							industrial.setBounds(district.getX()+17, district.getY()+3, 30, 30);
						}
						add(industrial);
					}

				}
			}
		}
	}
	
	
	
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
			
		}
		
		public void update() {
			removeAll();
			squares = GameMaster.getInstance().getBoard().getSquares();
			for (int i=0; i < squares.size(); i++) {
				if (squares.get(i).getType() == SQUARETYPE.DISTRICT) {
					District district = (District)squares.get(i);
					label = new DistrictLabel();
					label.setToolTipText(district.getHTML());
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
		    setText("<html>Get out of Jail</html>");
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
		    setText("<html>Tax Dodge</html>");
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
		    setText("<html>Rent Dodge</html>");
		    //setIcon(new ImageIcon("images/rentDodge.png"));
		    //setRolloverIcon(new ImageIcon("images/rentDodgeRollOver.png"));
		    //setRolloverEnabled(true);
			setPreferredSize(new Dimension(100, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("used Rent Dodge Card");
							GameMaster.getInstance().useRentDodgeCard();
							GameMaster.getInstance().checkForRailroad();
						}
					});
		}
	}
	
	public class TaxiButton extends JButton {
		
		public TaxiButton() {
		    setText("<html>Taxi Card</html>");
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
			setPreferredSize(new Dimension(120, 50));
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
			setPreferredSize(new Dimension(120,50));
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
			setPreferredSize(new Dimension(120,50));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked EndTurn");
							GameMaster.getInstance().endTurn();				
						}
					});
		}
	}	
	
	public class TradeButton extends JButton {
		
		public TradeButton() {
			setText("Make a Deal");
			setPreferredSize(new Dimension(180,50));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked Trade Button");
							GameMaster.getInstance().startTrade();
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
			playerDetails.setFont(new Font("Verdana", Font.BOLD, 17));
			playerDetails.setLineWrap(true);
			playerDetails.setWrapStyleWord(true);
			playerDetails.setBounds(0, 0, 370, 170);
			playerDetails.setOpaque(false);
			revalidate();
			repaint();
		}
		
	}
	
	public class DistrictsPanel extends JPanel {
		
		JLabel text;
		
		public DistrictsPanel() {
			setLayout(new MigLayout("wrap"));
			text = new JLabel();
			text.setPreferredSize(new Dimension(50, 400));
			text.setOpaque(false);
		}
		
		public void setMessageText(String str) {
			String newString = "<html>" + str + "</br></html>";
			text = new JLabel(newString);
			add(text);
			repaint();
			revalidate();
		}
		
		public void update() {
			removeAll();
			final Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
			setMessageText("CLICK to Mortgage a district");
			ArrayList<District> districts = currentPlayer.getDistricts();
			Collections.sort(districts);
			for (District district: districts) {
				final District fDistrict = district;
				if (fDistrict.isMortgaged == true) {
					JToggleButton button = new JToggleButton(fDistrict.getName() + "  [ Pay " + fDistrict.getMortgageValue() + " to unmortgage ]");
					button.addItemListener(
							new ItemListener() {
						      public void itemStateChanged(ItemEvent itemEvent) {
						    	  System.out.println("checking if player has enough cash to mortgage");
						    	  if (currentPlayer.getCash() > fDistrict.getMortgageValue()) {
						    		  System.out.println("unmortgage district");
						    		  //should be in GameMaster
						    		  currentPlayer.pay(fDistrict.getMortgageValue());
							    	  System.out.println(Double.toString(currentPlayer.getCash()));
						    		  fDistrict.isMortgaged = false;
						    		  GamePane.getInstance().update();

						    	  } else {
						    		  districtsPanel.setMessageText("You cannot afford to unmortgage " + fDistrict.getName());
						    	  }
						      }
						    });
					button.getModel().isSelected();
					add(button);
				} else {
					JToggleButton button = new JToggleButton(fDistrict.getName() + "  [ Mortgage for " + Double.toString(fDistrict.getMortgageValue()) + " ]");
					button.addItemListener(
							new ItemListener() {
						      public void itemStateChanged(ItemEvent itemEvent) {
						    	  //should be in GameMaster
					    		  System.out.println("mortgage district");
						    	  currentPlayer.collect(fDistrict.getMortgageValue());
						    	  System.out.println(Double.toString(currentPlayer.getCash()));
						    	  fDistrict.isMortgaged = true;
						    	  GamePane.getInstance().update();
						      }
						    });	
					button.getModel();
					add(button);
					}
			}
		}
	}
	
	public class MessagePanel extends JPanel {
		
		JTextArea textArea;
		JPanel textPanel;
		JPanel checkBoxPanel;
		JPanel buttonPanel;
		
		public MessagePanel() {
			
			setVisible(false);

			setLayout(new MigLayout());
			
			setPreferredSize(new Dimension(350,250));
			setBorder(BorderFactory.createLineBorder(Color.black, 2));
			textArea = new JTextArea();
			textArea.setPreferredSize(new Dimension(400,100));
			textArea.setMargin(new Insets(10,10,10,10));
			textArea.setFont(new Font("Verdana", Font.BOLD, 16));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setOpaque(false);

			textPanel = new JPanel();
			textPanel.setPreferredSize(new Dimension(400,100));

			textPanel.add(textArea);
			
			buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(400,100));

			checkBoxPanel = new JPanel();
			checkBoxPanel.setPreferredSize(new Dimension(400, 50));
			
			add(textArea, "cell 0 0");
			add(checkBoxPanel, "cell 0 1");
			add(buttonPanel, "cell 0 2");
		}
		
		public void setText(String str) {
			clearAll();
			setVisible(true);
			textArea.setText(str);
			textArea.revalidate();
			textArea.repaint();
		}
		
		
		public void addText(String str) {
			String oldString = textArea.getText();
			textArea.setText(oldString + "\n\n" + str);
			textArea.revalidate();
			textArea.repaint();
		}
		
		public void addButton(JButton btn) {
			buttonPanel.setLayout(new GridBagLayout());
			btn.setPreferredSize(new Dimension(130, 25));
			if (buttonPanel.getComponents().length > 1) {
				buttonPanel.setLayout(new MigLayout("wrap 2"));
			}
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
		
		public void clearAll() {
			setVisible(false);
			checkBoxPanel.removeAll();
			buttonPanel.removeAll();
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

		networthPanel = new NetworthPanel();
		networthPanel.setBounds(0, 0, 426, 118);
		networthPanel.setBorder(BorderFactory.createTitledBorder("Networth"));		
		
		
		hudPanel1 = new JTabbedPane();
		hudPanel1.setBounds(0, 120, 426, 400);
		hudPanel1.setBorder(BorderFactory.createTitledBorder("Current Player"));		
		currentPlayerPanel = new CurrentPlayerPanel();
		hudPanel1.addTab("Player", (JComponent)currentPlayerPanel);
		districtsPanel = new DistrictsPanel();
		hudPanel1.addTab("Disricts", (JComponent)districtsPanel);
		
		
		rollDiceButton = new RollDiceButton();
		buildButton = new BuildButton();
		endTurnButton = new EndTurnButton();
		tradeButton = new TradeButton();
		
		hudButtonPanel = new JPanel();
		hudButtonPanel.setBounds(0, 521, 426, 120);
		hudButtonPanel.add(rollDiceButton);
		hudButtonPanel.add(buildButton);
		hudButtonPanel.add(endTurnButton);
		hudButtonPanel.add(tradeButton);

		
		getOutOfJailButton = new GetOutOfJailButton();
		rentDodgeButton = new RentDodgeButton();
		taxiButton = new TaxiButton();
		taxDodgeButton = new TaxDodgeButton();
		
		hudPanel3 = new JPanel();
		hudPanel3.setBounds(0, 652, 430, 118);
		hudPanel3.setBorder(BorderFactory.createTitledBorder("Chance Cards"));
		hudPanel3.add(getOutOfJailButton);
		hudPanel3.add(rentDodgeButton);
		hudPanel3.add(taxDodgeButton);
		hudPanel3.add(taxiButton);
		
		mainHudPanel.add(networthPanel);
		mainHudPanel.add(hudPanel1);
		mainHudPanel.add(hudButtonPanel);
		mainHudPanel.add(hudPanel3);
		
		
		messageLayer = new JPanel();
		messageLayer.setLayout(new GridBagLayout());
		messageLayer.setBounds(0, 0, 768, 767);
		messageLayer.setOpaque(false);
		
		messagePanel = new MessagePanel();
		dicePanel = new JPanel();
		dicePanel.setOpaque(false);
		dicePanel.setBounds(290, 150, 200, 100);
		
		messageLayer.add(messagePanel);
		
		baseLayer.add(board, BorderLayout.WEST);	
		baseLayer.add(mainHudPanel, BorderLayout.EAST);
		
		districtElementsPanel = new DistrictElementsPanel();
		districtRollOverPanel = new DistrictRollOverPanel();
		structureCountPanel = new StructureCountPanel();
		
		add(baseLayer, new Integer(0));
		add(messageLayer, new Integer(1));	
		add(districtElementsPanel, new Integer(2));	
		add(structureCountPanel, new Integer(3));
		add(playerTokensLayer, new Integer(4));
		add(districtRollOverPanel, new Integer(5));
		add(dicePanel, new Integer(6));
		
		//AwesomeSauce Feature X100
		
		@SuppressWarnings("serial")
		Action poppop = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				System.out.println("about");
				JOptionPane.showMessageDialog (messageLayer, "This Game is Brought to you by: \n\tNicholas \"The Complicatrix\" O'Deegan" +
						"\n\tCodi \"The Sunflower\" Bonney" +
						"\n\t and Daniel \"Won't Shut Up\" McGoldrick", "The Men Behind The Code", JOptionPane.INFORMATION_MESSAGE);
			}
		};
		
		messageLayer.getInputMap(messageLayer.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),"PopUpMenu");
		messageLayer.getActionMap().put("PopUpMenu",poppop);
	}
	

	
	public static GamePane getInstance() {
		return GAMEPANE;
	}
	
	public void setDice(JPanel dice) {
		clearDice();
		dicePanel.add(dice);
	}
	
	public void clearDice() {
		dicePanel.removeAll();
	}
	
	public void clearMessageLayer() {
		System.out.println("clearing messageLayer");
		messagePanel.clearAll();

	}
	
	public void setMessagePanelText(String str) {
		System.out.println("setting message panel text");
		messagePanel.setText(str);
	}
	
	public void addMessagePanelText(String str) {
		System.out.println("adding message panel text");
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
		
	public void setSelectedDistrict(int index) {
		selectedDistrict = index;
	}
	
	public void clearSelectedDistrict() {
		selectedDistrict = -1;
	}
	
	public int getSelectedDistrict() {
		return selectedDistrict;
	}
	
	public void update() {
		currentPlayerPanel.update();
		districtsPanel.update();
		districtElementsPanel.update();
		structureCountPanel.update();
		playerTokensLayer.update();
		districtRollOverPanel.update();
		networthPanel.update();
		revalidate();
		repaint();
	}
	
	
	
}