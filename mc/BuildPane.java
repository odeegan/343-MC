package mc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;



class BuildPane extends JLayeredPane {

	private static JPanel baseLayer;
	
	
	private static JPanel buildShopPanel;
	
	private static JLabel selectDistrictLabel;
	private static JComboBox ownedDistrictsComboBox;
	
	
	private static JPanel buildTypePanel;
	
	private static JRadioButton residentialRadioButton;
	private static JLabel residentialTypeLabel;
	private static JRadioButton railroadRadioButton;
	private static JLabel railroadTypeLabel;
	private static JRadioButton industrialRadioButton;
	private static JLabel industrialTypeLabel;
	private static JRadioButton skyscraperRadioButton;
	private static JLabel skyscraperTypeLabel;
	private static JRadioButton stadiumRadioButton;
	private static JLabel stadiumLabel;
	private static JRadioButton monopolyTowerRadioButton;
	private static JLabel monopolyTowerLabel;
	private static JRadioButton removeHazardRadioButton;
	private static JLabel removeHazardLabel;
	
	private static JLabel blocksToBuildLabel;
	private static JSpinner buildTypeAmountSpinner;
	private static JButton addToCartButton;
	private static JLabel buildAllowanceLabel;
	private static JTextField buildAllowanceTextField;
	private static JButton cancelButton;
	
	
	private static JPanel buildCartPanel;
	
	private static JList buildList;
	private static JLabel totalCostLabel;
	private static JTextField totalTextField;	
	private static JButton purchaseButton;
	private static JButton deleteButton;
	
	private Player player1;
	
	private static String[] playerDistricts;
	
	private int playerAllowance;
	private int allowanceRemaining;
	
	private static int railroadsRemaining;
	private static int residentialBlocksRemaining;
	private static int industrialBlocksRemaining;
	
	
	
	private static final BuildPane BUILDPANE = new BuildPane();
	
	public class AddToCartButton extends JButton {
		
		public AddToCartButton() {
		    //setIcon(new ImageIcon("images/getOutOfJailFree.png"));
		    //setRolloverIcon(new ImageIcon("images/getOutOfJailFreeRollOver.png"));
		    //setRolloverEnabled(false);
		    setText("Add To Cart");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User added district buildings to cart.");
							
						}
					});
		}
	}
	
	public class PurchaseButton extends JButton {
		
		public PurchaseButton() {
		    setText("Build");
			setPreferredSize(new Dimension(100, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User purchased district buildings.");
						}
					});
		}
	}
	
	public class DeleteButton extends JButton {
		
		public DeleteButton() {
		    setText("Delete");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked delete button.");
						}
					});
		}
	}
	
	public class CancelButton extends JButton {
		
		public CancelButton() {
			setText("Cancel");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							//BuildMaster.cancel();
							System.out.println("User clicked Cancel.");
							
						}
					});
		}
	}
	
	public class OwnedDistrictsComboBox extends JComboBox {
			
		public OwnedDistrictsComboBox() {
			
			//ownedDistrictsComboBox = new JComboBox(playerDistricts);
			ownedDistrictsComboBox = new JComboBox();
			
			
			
			playerDistricts = new String[player1.getDistricts().size()];
			for(int jj = 0; jj < player1.getDistricts().size(); jj++){
				playerDistricts[jj] = player1.getDistricts().get(jj).getName();
				System.out.println(playerDistricts[jj]);
				ownedDistrictsComboBox.addItem(playerDistricts[jj]);
			}
			
			ownedDistrictsComboBox = new JComboBox(playerDistricts);
			
			ownedDistrictsComboBox.setEditable(false);
/*			
			ownedDistrictsComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
				
					System.out.println("User selected district: "+selected);
				}
			});
*/			
			ownedDistrictsComboBox.setSelectedItem(playerDistricts[0]);
			
			ownedDistrictsComboBox.addItemListener(
					new ItemListener(){
						public void itemStateChanged(ItemEvent event){
							if(event.getStateChange() == ItemEvent.SELECTED)
								//System.out.println("User selected district: "+playerDistricts[ownedDistrictsComboBox.getSelectedIndex()]);
								System.out.println("index:"+event.getItem());
								districtSelected((String)event.getItem());
								
						}

						private void districtSelected(String selectedDistrictName) {
							
							// Setting spinner max
							int blocksOnDistrict = GameMaster.getInstance().getBoard().getDistrictByName(selectedDistrictName).getTotalBlockCount();
							District selectedDistrict = GameMaster.getInstance().getBoard().getDistrictByName(selectedDistrictName);
							
							if(8 - blocksOnDistrict < allowanceRemaining){
								SpinnerNumberModel model = new SpinnerNumberModel(0,0, 8-blocksOnDistrict, 1);
								buildTypeAmountSpinner.setModel(model);
							} else{
								SpinnerNumberModel model = new SpinnerNumberModel(0, 0, allowanceRemaining, 1);
								buildTypeAmountSpinner.setModel(model);
							}
							
							if(blocksOnDistrict < 8 && allowanceRemaining > 0 && player1.getCash() > selectedDistrict.residentialCost){
								residentialRadioButton.setEnabled(true);
							}
							
							if(blocksOnDistrict < 8 && allowanceRemaining > 0 && player1.getCash() > selectedDistrict.industrialCost){
								industrialRadioButton.setEnabled(true);
							}
							
							//Allowance is railroad
//							if(allowanceRemaining == -1 &&  ){
//								industrialRadioButton.setVisible(false);
//								industrialTypeLabel.setVisible(false);
//							}
							
							//if()
							
							
							//buildShopPanel.add(buildTypeAmountSpinner, "cell 1 3 1 1");
//							residentialRadioButton.setVisible(false);
//							residentialTypeLabel.setVisible(false);
//							railroadRadioButton.setVisible(true);
//							railroadTypeLabel.setVisible(true);
//							industrialRadioButton.setVisible(false);
//							industrialTypeLabel.setVisible(false);
//							skyscraperRadioButton.setVisible(false);
//							skyscraperTypeLabel.setVisible(false);
//							stadiumRadioButton.setVisible(false);
//							stadiumLabel.setVisible(false);	
//							monopolyTowerRadioButton.setVisible(false);
//							monopolyTowerLabel.setVisible(false);
//							removeHazardRadioButton.setVisible(false);
//							removeHazardLabel.setVisible(false);
						}
					}
			);
			buildShopPanel.add(ownedDistrictsComboBox, "cell 1 1 3 1");
			
		}
	}
	
	public class BuildList extends JList {
		
		public BuildList() {
			setPreferredSize(new Dimension(250, 150));
		}
	}
	
	public class TotalTextField extends JTextField {
		
		public TotalTextField() {
			setText("total");
			setPreferredSize(new Dimension(250, 30));
		}
	}
	
	public class BuildTypeAmountSpinner extends JSpinner {
		
		public BuildTypeAmountSpinner() {
			SpinnerNumberModel model = new SpinnerNumberModel(0,0,allowanceRemaining,1);
			buildTypeAmountSpinner = new JSpinner(model);
			//repaint();
			//buildShopPanel.add(buildTypeAmountSpinner, "cell 1 3 1 1");
			//buildTypeAmountSpinner.setModel(model);
			//buildTypeAmountSpinner.setValue(allowanceRemaining);
			setPreferredSize(new Dimension(100,30));
			//setAttributes(1,3);
		}
	}
	
	public class BuildAllowanceTextField extends JTextField {
		
		public BuildAllowanceTextField(){
			if(playerAllowance == 0){
				setText("Railroad");
			}else{
				setText(allowanceRemaining+" Blocks");
			}
			setEditable(false);
			setPreferredSize(new Dimension(250,30));
		}
		
	}
	
	public class ResidentialRadioButton extends JRadioButton {
		
		public ResidentialRadioButton() {
			
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose Residential build type.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(true);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
		
	}
	
	public class IndustrialRadioButton extends JRadioButton {
	
		public IndustrialRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose Industrial build type.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(true);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
	}
	
	public class RailroadRadioButton extends JRadioButton {
		
		public RailroadRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to build a Railroad.");
							railroadRadioButton.setSelected(true);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
	}
	
	public class SkyscraperRadioButton extends JRadioButton {
		
		public SkyscraperRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to build a Skyscraper.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(true);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
	}
	
	public class StadiumRadioButton extends JRadioButton {
		
		public StadiumRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to build a Stadium.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(true);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
	}
	
	public class MonopolyTowerRadioButton extends JRadioButton {
		
		public MonopolyTowerRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to build a Monopoly Tower.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(true);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(false);
						}
					});
		}
	}
	
	public class RemoveHazardRadioButton extends JRadioButton {
		
		public RemoveHazardRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to build a Monopoly Tower.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(true);
						}
					});
		}
	}
		
	public class BuildTypePanel extends JPanel { ;

	// currentSquarePanel
		
		public BuildTypePanel() {
			//setLayout(new BorderLayout());
			setLayout(new MigLayout());
			setBorder(BorderFactory.createLineBorder(Color.gray,2));
			
			setPreferredSize(new Dimension(250,100));
			//buildTypePanel.setPreferredSize(new Dimension(250,100));
			setBorder(BorderFactory.createTitledBorder("Building Type"));
			
			residentialRadioButton = new ResidentialRadioButton();
			residentialTypeLabel = new JLabel("Residential");
			
			railroadRadioButton = new RailroadRadioButton();
			railroadTypeLabel = new JLabel("Railroad");
			
			industrialRadioButton = new IndustrialRadioButton();
			industrialTypeLabel = new JLabel("Industrial");
			
			skyscraperRadioButton = new SkyscraperRadioButton();
			skyscraperTypeLabel = new JLabel("Skyscraper");
			
			stadiumRadioButton = new StadiumRadioButton();
			stadiumLabel = new JLabel("Stadium");
			
			monopolyTowerRadioButton = new MonopolyTowerRadioButton();
			monopolyTowerLabel = new JLabel("Monopoly Tower");
			
			removeHazardRadioButton = new RemoveHazardRadioButton();
			removeHazardLabel = new JLabel("Remove Hazard");
			
			// Hide radio buttons
			residentialRadioButton.setVisible(true);
			residentialTypeLabel.setVisible(true);
			railroadRadioButton.setVisible(true);
			railroadTypeLabel.setVisible(true);
			industrialRadioButton.setVisible(true);
			industrialTypeLabel.setVisible(true);
			skyscraperRadioButton.setVisible(true);
			skyscraperTypeLabel.setVisible(true);
			stadiumRadioButton.setVisible(true);
			stadiumLabel.setVisible(true);	
			monopolyTowerRadioButton.setVisible(true);
			monopolyTowerLabel.setVisible(true);
			removeHazardRadioButton.setVisible(true);
			removeHazardLabel.setVisible(true);

			//cell column row width height
			add(residentialRadioButton, "cell 0 0 1 1");
			add(residentialTypeLabel, "cell 1 0 1 1");
			add(railroadRadioButton, "cell 0 1 1 1");
			add(railroadTypeLabel, "cell 1 1 1 1");
			add(industrialRadioButton, "cell 0 2 1 1");
			add(industrialTypeLabel, "cell 1 2 1 1");
			add(skyscraperRadioButton, "cell 0 3 1 1");
			add(skyscraperTypeLabel, "cell 1 3 1 1");
			add(stadiumRadioButton, "cell 0 4 1 1");
			add(stadiumLabel,"cell 1 4 1 1");
			add(monopolyTowerRadioButton, "cell 0 5 1 1");
			add(monopolyTowerLabel, "cell 1 5 1 1");
			add(removeHazardRadioButton, "cell 0 6 1 1");
			add(removeHazardLabel, "cell 1 6 1 1");
			
			residentialRadioButton.setEnabled(false);
			railroadRadioButton.setEnabled(false);
			industrialRadioButton.setEnabled(false);
			skyscraperRadioButton.setEnabled(false);
			stadiumRadioButton.setEnabled(false);
			monopolyTowerRadioButton.setEnabled(false);
			removeHazardRadioButton.setEnabled(false);
			
		}
	}
		
	
	private BuildPane() {
		
		player1 = new Player(1);
		player1.addDistrict(GameMaster.getInstance().getBoard().getDistrict(1));
		player1.addDistrict(GameMaster.getInstance().getBoard().getDistrict(3));
		GameMaster.getInstance().getBoard().getDistrict(1).addIndustrialBlock(5);
		GameMaster.getInstance().getBoard().getDistrict(3).addResidentialBlock(7);
		
		playerAllowance = generateAllowance();
		allowanceRemaining = playerAllowance;
		
		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 500, 350);
		baseLayer.setOpaque(true);

		buildShopPanel = new JPanel();
		buildShopPanel.setLayout(new MigLayout());
		buildShopPanel.setPreferredSize(new Dimension(250,350));
		buildShopPanel.setBorder(BorderFactory.createTitledBorder("Build Shop"));
		
		buildCartPanel = new JPanel();
		buildCartPanel.setLayout(new MigLayout());
		buildCartPanel.setPreferredSize(new Dimension(250,350));
		buildCartPanel.setBorder(BorderFactory.createTitledBorder("Build Cart"));		
		
		buildTypePanel = new BuildTypePanel();

		buildAllowanceLabel = new JLabel("Allowance:");
		selectDistrictLabel = new JLabel("Districts:");
		blocksToBuildLabel = new JLabel("Blocks:");

		buildAllowanceTextField = new BuildAllowanceTextField();
		ownedDistrictsComboBox = new OwnedDistrictsComboBox();
		
		addToCartButton = new AddToCartButton();
		

		buildTypeAmountSpinner = new BuildTypeAmountSpinner();
		

		buildShopPanel.add(buildAllowanceLabel, "cell 0 0 1 1");
		buildShopPanel.add(buildAllowanceTextField, "cell 1 0 2 1");
		buildShopPanel.add(selectDistrictLabel, "cell 0 1 1 1");
		//buildShopPanel.add(ownedDistrictsComboBox, "cell 1 1 3 1");
		buildShopPanel.add(buildTypePanel, "cell 0 2 4 1");
		buildShopPanel.add(blocksToBuildLabel, "cell 0 3 1 1");
		buildShopPanel.add(buildTypeAmountSpinner, "cell 1 3 1 1");
		buildShopPanel.add(addToCartButton, "cell 2 3 1 1");

		
		totalCostLabel = new JLabel("Total Cost: ");
		buildList = new BuildList();
		totalTextField = new TotalTextField();	
		purchaseButton = new PurchaseButton();
		deleteButton = new DeleteButton();
		cancelButton = new CancelButton();
		// cell column row width height
		buildCartPanel.add(buildList, "cell 0 0 3 1");
		buildCartPanel.add(deleteButton, "cell 0 1 1 1");
		buildCartPanel.add(totalCostLabel, "cell 1 1 1 1");
		buildCartPanel.add(totalTextField, "cell 2 1 1 1");
		buildCartPanel.add(purchaseButton, "cell 2 2 1 1");	
		buildCartPanel.add(cancelButton, "cell 0 3 1 1");

		
		
		baseLayer.add(buildShopPanel, BorderLayout.WEST);
		baseLayer.add(buildCartPanel, BorderLayout.EAST);
	
		add(baseLayer, new Integer(1));
	}
	
	public static BuildPane getInstance() {
		return BUILDPANE;
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
	
	public void addToCartButton() {
		
	}
	
	public JButton purchaseButton() {
		return purchaseButton;
	}
	
	public JButton deleteButton() {
		return deleteButton;
	}
	
	public JButton cancelButton() {
		return cancelButton;
	}
	
	public void update() {
		revalidate();
		repaint();
	}
	
	public int generateAllowance() {
		Random generator = new Random();
		int random = generator.nextInt(4);
		if(random == 0){
			return -1;
		}else{
			return random;
		}
	}
	
}