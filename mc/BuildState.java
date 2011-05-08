
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

public class BuildState implements GameState{

	GameMaster gameMaster;
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	JLayeredPane layeredPane = new JLayeredPane();
	
	private static JPanel baseLayer;
	private static JPanel buildShopPanel;
	private static JPanel buildTypePanel;
	private static JPanel buildCartPanel;
	
	private static JLabel selectDistrictLabel;
	private static JLabel residentialTypeLabel;
	private static JLabel railroadTypeLabel;
	private static JLabel industrialTypeLabel;
	private static JLabel skyscraperTypeLabel;
	private static JLabel stadiumLabel;
	private static JLabel monopolyTowerLabel;
	private static JLabel removeHazardLabel;
	private static JLabel blocksToBuildLabel;
	private static JLabel buildAllowanceLabel;
	private static JLabel totalCostLabel;
	private static JLabel playerCashLabel;
	
	private static JRadioButton residentialRadioButton;
	private static JRadioButton railroadRadioButton;
	private static JRadioButton industrialRadioButton;
	private static JRadioButton skyscraperRadioButton;
	private static JRadioButton stadiumRadioButton;
	private static JRadioButton monopolyTowerRadioButton;
	private static JRadioButton removeHazardRadioButton;
	
	private static JButton addToCartButton;
	private static JButton cancelButton;
	private static JButton buildButton;
	private static JButton deleteButton;	

	private static JTextField buildAllowanceTextField;
	private static JTextField totalTextField;	
	private static JTextField messageTextField;
	private static JTextField playerCashTextField;
	
	private static JComboBox ownedDistrictsComboBox;
	private static JSpinner buildTypeAmountSpinner;
	private static JList buildList;

	//private SpinnerNumberModel model;
	private DefaultListModel cartListModel;
	
	private static String[] playerDistricts;
	
	private static District playerSelectedDistrict;
	
	private int playerAllowance;
	private int allowanceRemaining;
	private double projectedCosts;
	private double playerCash;
	
	//private Player currentPlayer;
	private Player currentPlayer;
	
	private String currentlySelectedRadioButton;
	
	public BuildState(GameStateMachine gsm, JFrame mf) {
		gameMaster = GameMaster.getInstance();
		gameStateMachine = gsm;
		mainFrame = mf;
		
		initComponents();
	}
	
	private void initComponents() {
		
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(new MigLayout("align center"));
		
		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 500, 380);
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

		//buildAllowanceTextField = new BuildAllowanceTextField();
		
		addToCartButton = new AddToCartButton();
	
		buildTypeAmountSpinner = new JSpinner();
		
		buildShopPanel.add(buildAllowanceLabel, "cell 0 0 1 1");
		//buildShopPanel.add(buildAllowanceTextField, "cell 1 0 2 1");
		buildShopPanel.add(selectDistrictLabel, "cell 0 1 1 1");
		//combo box
		buildShopPanel.add(buildTypePanel, "cell 0 2 4 1");
		buildShopPanel.add(blocksToBuildLabel, "cell 0 3 1 1");
		buildShopPanel.add(buildTypeAmountSpinner, "cell 1 3 1 1");
		buildShopPanel.add(addToCartButton, "cell 2 3 1 1");
		
		buildTypeAmountSpinner.setVisible(false);
		
		totalCostLabel = new JLabel("Total Cost: ");
		buildList = new BuildList();
		totalTextField = new JTextField();
		totalTextField.setText("total");
		totalTextField.setPreferredSize(new Dimension(250, 30));
		totalTextField.setEditable(false);
		buildButton = new BuildButton();
		deleteButton = new DeleteButton();
		cancelButton = new CancelButton();
		playerCashTextField = new JTextField();
		playerCashTextField.setPreferredSize(new Dimension(250, 30));
		playerCashTextField.setEditable(false);
		playerCashLabel = new JLabel("Player's Cash:");
		
	
		buildCartPanel.add(buildList, "cell 0 0 3 1");
		buildCartPanel.add(playerCashLabel, "cell 1 2 1 1");
		buildCartPanel.add(playerCashTextField, "cell 2 2 1 1");
		buildCartPanel.add(deleteButton, "cell 0 1 1 1");
		buildCartPanel.add(totalCostLabel, "cell 1 1 1 1");
		buildCartPanel.add(totalTextField, "cell 2 1 1 1");
		buildCartPanel.add(buildButton, "cell 2 3 1 1");	
		buildCartPanel.add(cancelButton, "cell 0 4 1 1");
		deleteButton.setVisible(false);
		
		messageTextField = new JTextField();
		messageTextField.setPreferredSize(new Dimension(500, 30));
		messageTextField.setEditable(false);
		
		baseLayer.add(buildShopPanel, BorderLayout.WEST);
		baseLayer.add(buildCartPanel, BorderLayout.EAST);
		baseLayer.add(messageTextField, BorderLayout.SOUTH);
	
	}// End constructor.
	
	@SuppressWarnings("serial")
	public class BuildTypePanel extends JPanel { ;
	
	public BuildTypePanel() {

		setLayout(new MigLayout());
		setBorder(BorderFactory.createLineBorder(Color.gray,2));
		setPreferredSize(new Dimension(250,100));
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
		
	}// End constructor.
}// End buildTypePanel.
	
	//private static final BuildPane BUILDPANE = new BuildPane();
	
	public class OwnedDistrictsComboBox extends JComboBox {
		
		public OwnedDistrictsComboBox() {
			ownedDistrictsComboBox = new JComboBox();
			currentPlayer = GameMaster.getInstance().getCurrentPlayer();
			//Put list of players owned districts name's into an array of strings.
			playerDistricts = new String[currentPlayer.getDistricts().size()+1];
			playerDistricts[0] = "";
			ownedDistrictsComboBox.addItem(playerDistricts[0]);
			for(int jj = 0; jj < currentPlayer.getDistricts().size(); jj++){
				playerDistricts[jj+1] = currentPlayer.getDistricts().get(jj).getName();
				ownedDistrictsComboBox.addItem(playerDistricts[jj+1]);
			}// End for.
			
			ownedDistrictsComboBox = new JComboBox(playerDistricts);
			ownedDistrictsComboBox.setEditable(false);	
			//ownedDistrictsComboBox.setSelectedItem(playerDistricts[0]);
			
			ownedDistrictsComboBox.addItemListener(
					new ItemListener(){
						public void itemStateChanged(ItemEvent event){
							if(event.getStateChange() == ItemEvent.SELECTED){
								
								enableButtonsAccordingToDistrict((String)event.getItem());
								playerSelectedDistrict =GameMaster.getInstance().getBoard().getDistrictByName((String)event.getItem());
								System.out.println("Player selected district "+ playerSelectedDistrict);
							}//End if.
						}// End itemStateChanged.
			});// End addItemListener.
			
			buildShopPanel.add(ownedDistrictsComboBox, "cell 1 1 3 1");
		}// End constructor.
			
		private void enableButtonsAccordingToDistrict(String selectedDistrictName) {
			
			// Setting spinner max
			int blocksOnDistrict = GameMaster.getInstance().getBoard().getDistrictByName(selectedDistrictName).getTotalBlockCount();
			District selectedDistrict = GameMaster.getInstance().getBoard().getDistrictByName(selectedDistrictName);
			
			// residential logic
			if(blocksOnDistrict < 8 && allowanceRemaining > 0 && currentPlayer.getCash() > selectedDistrict.residentialCost)
				residentialRadioButton.setEnabled(true);

			// industrial logic
			if(blocksOnDistrict < 8 && allowanceRemaining > 0 && currentPlayer.getCash() > selectedDistrict.industrialCost)
				industrialRadioButton.setEnabled(true);
			
			// Railroad logic
			if(playerAllowance == -1 && selectedDistrict.isRailRoaded() == false && StructureFactory.getInstance().railroadCount>0)
				railroadRadioButton.setEnabled(true);

			// Skyscraper logic
			if(currentPlayer.getCash() > selectedDistrict.skyscraperCost && !selectedDistrict.skyscraper){
				String selectedDistrictColor = selectedDistrict.getColor();
				int selectedDistrictColorCount = 0;
				int expectedCount = 0;
				for(int oo = 0; oo < currentPlayer.getDistricts().size(); oo++)
					if(currentPlayer.getDistricts().get(oo).getColor() == selectedDistrictColor)
						selectedDistrictColorCount++;

				if(selectedDistrictColor == "brown" || selectedDistrictColor == "blue"){
					expectedCount = 2;
				}else
					expectedCount = 3;
				
				if(selectedDistrictColorCount == expectedCount)
					skyscraperRadioButton.setEnabled(true);

			}// End if.
			
			//Stadium logic
			if(currentPlayer.getCash() > 2 && StructureFactory.getInstance().stadiumCount>0){
				String selectedDistrictColor = selectedDistrict.getColor();
				int selectedDistrictColorCount = 0;
				for(int oo = 0; oo < currentPlayer.getDistricts().size(); oo++){
					if(currentPlayer.getDistricts().get(oo).getColor() == selectedDistrictColor){
						selectedDistrictColorCount++;
					}// End if.
				}// End for.
				if(selectedDistrictColorCount >= 2){
					stadiumRadioButton.setEnabled(true);
				}// End if.
			}// End if.
			
			// Monopoly tower logic
			if(currentPlayer.getCash() > selectedDistrict.getMonopolyTowerCost()){
				String selectedDistrictColor = selectedDistrict.getColor();
				int selectedDistrictColorCount = 0;
				int expectedCount = 0;
				int brownCount = 0;
				int purpleCount = 0;
				int yellowCount = 0;
				int blueCount = 0;
				int orangeCount = 0;
				int redCount = 0;
				int greenCount = 0;
				int skyCount = 0;
				
				for( int oo = 0; oo < currentPlayer.getDistricts().size(); oo++){
					if(currentPlayer.getDistricts().get(oo).getColor() == selectedDistrictColor)
						selectedDistrictColorCount++;

					String testingColor = currentPlayer.getDistricts().get(oo).getColor();
					if(testingColor.equals("brown")){
						brownCount++;
					}else if(testingColor.equals("purple")){
						purpleCount++;
					}else if(testingColor.equals("yellow")){
						yellowCount++;
					}else if(testingColor.equals("blue")){
						blueCount++;
					}else if(testingColor.equals("orange")){
						orangeCount++;
					}else if(testingColor.equals("red")){
						redCount++;
					}else if(testingColor.equals("green")){
						greenCount++;
					}else if(testingColor.equals("sky")){
						skyCount++;
					}// End else if.
				}// End for.
				
				if(selectedDistrictColor == "brown" || selectedDistrictColor == "blue"){
					expectedCount = 2;
				}else{
					expectedCount = 3;
				}// End else.
				
				if(selectedDistrictColorCount == expectedCount && (brownCount == 2 || blueCount == 2 || 
						purpleCount == 3 || yellowCount == 3 || blueCount == 3 || orangeCount == 3 || 
						redCount == 3 || greenCount == 3 || skyCount == 3 )){
					monopolyTowerRadioButton.setEnabled(true);
				}// End if.
			}// End if.
			
			// Remove hazard logic
			if(selectedDistrict.isHazarded() == true && currentPlayer.getCash() > (selectedDistrict.hazard.getBlockCount()*.5))
				removeHazardRadioButton.setEnabled(true);
		}// end enableButtonsAccordingToDistrict();					
	}// End OwnedDistrictsComboBox.

	public class BuildList extends JList {
		
		public BuildList() {
			buildList = new JList();
			cartListModel = new DefaultListModel();
			setPreferredSize(new Dimension(250, 150));
			
			addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e){
					if(buildList.getSelectedIndex() == -1){
						deleteButton.setVisible(false);
					}else
						deleteButton.setVisible(true);
				}// End valueChanged.
			});// End addListSelectionListener.
		}// End constructor.
	}// End BuildList.

	public class BuildAllowanceTextField extends JTextField {
		
		public BuildAllowanceTextField(){
			if(allowanceRemaining == -1){
				setText("Railroad");
			}else
				setText(allowanceRemaining+" Blocks");
			
			setEditable(false);
			setPreferredSize(new Dimension(250,30));
		}// End constructor.
		
	}// End BuildAllowanceTextField.

	public class AddToCartButton extends JButton {
		
		public AddToCartButton() {
		    setText("Add To Cart");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							addToCartAction();
							System.out.println("User attempted to add items to cart.");
							
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
		
		private void addToCartAction() {
			
			//Residential logic.
			if(currentlySelectedRadioButton.compareToIgnoreCase("residential") == 0){
				int buildAmount = (Integer)buildTypeAmountSpinner.getValue();
				if (allowanceRemaining > 0){
					if(playerSelectedDistrict.getTotalBlockCount() < 8){
						// Make sure user isn't building 0 blocks.
						if(buildAmount != 0){
							// Make sure there are enough blocks left.
							if(buildAmount <= StructureFactory.getInstance().residentialCount){
								cartListModel.addElement(playerSelectedDistrict.getName() +" "+ currentlySelectedRadioButton +" "+buildAmount);
								buildList.setModel(cartListModel);
								// Take away structures equal to build amount.
								for(int ii = 0; ii < buildAmount; ii++){
									StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("residential"));
									System.out.println("Residential block count decreased by 1.");
									playerSelectedDistrict.addResidentialBlock(1);
									System.out.println("Residential block added to "+ playerSelectedDistrict.getName());
								}// End for.	
								System.out.println("Build place: "+ playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " buildAmount " + buildAmount + " added to cart.");
								allowanceRemaining = allowanceRemaining - buildAmount;
								buildAllowanceTextField.setText(""+allowanceRemaining + " Blocks");
								projectedCosts = projectedCosts + playerSelectedDistrict.getResidentialCost()*buildAmount;
								totalTextField.setText(""+projectedCosts+" Mil");
							}else
								messageTextField.setText("Not enough residential blocks left to build.");
						}else
							messageTextField.setText("Cannot build 0 blocks.");
					}else
						messageTextField.setText("Maximum number of blocks allowed on district will be exceeded.");
				}else
					messageTextField.setText("You have 0 allowance left.");
				
			// Industrial logic.
			}else if(currentlySelectedRadioButton.compareToIgnoreCase("industrial") == 0){
				int buildAmount = (Integer)buildTypeAmountSpinner.getValue();	
				if(allowanceRemaining > 0){
					if(playerSelectedDistrict.getTotalBlockCount() < 8){
						// Make sure user isn't building 0 blocks.
						if(buildAmount != 0){
							// Make sure there are enough blocks left.
							if(buildAmount <= StructureFactory.getInstance().industrialCount){
								cartListModel.addElement(playerSelectedDistrict.getName() +" "+ currentlySelectedRadioButton +" "+buildAmount);
								buildList.setModel(cartListModel);
								// Take away structures equal to build amount.
								for(int ii = 0; ii < buildAmount; ii++){
									StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("industrial"));
									System.out.println("Industrial block count decreased by 1.");
									playerSelectedDistrict.addIndustrialBlock(1);
									System.out.println("Industrial block added to "+ playerSelectedDistrict.getName());
								}// End for.	
								System.out.println("Build place: "+ playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " buildAmount " + buildAmount + " added to cart.");
								allowanceRemaining = allowanceRemaining - buildAmount;
								buildAllowanceTextField.setText(""+allowanceRemaining + " Blocks");
								projectedCosts = projectedCosts + playerSelectedDistrict.getIndustrialCost()*buildAmount;
								totalTextField.setText(""+projectedCosts+" Mil");
							}else
								messageTextField.setText("Not enough industrial blocks left to build.");
						}else
							messageTextField.setText("Cannot build 0 blocks.");
					}else
						messageTextField.setText("Maximum number of blocks allowed on district will be exceeded.");
				}else
					messageTextField.setText("You have 0 allowance left.");
				
			// Railroad logic.
			}else if (currentlySelectedRadioButton.compareToIgnoreCase("railroad") == 0){
				if(allowanceRemaining != 0){
					if(StructureFactory.getInstance().railroadCount != 0){
						cartListModel.addElement(playerSelectedDistrict.getName() + " " + currentlySelectedRadioButton);
						buildList.setModel(cartListModel);
						System.out.println("Build place: "+ playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " added to cart.");
						StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("railroad"));
						playerSelectedDistrict.addRailroad();
						System.out.println("Railroad added to "+ playerSelectedDistrict.getName());
						allowanceRemaining = allowanceRemaining + 1;
						buildAllowanceTextField.setText("No blocks left.");
					}else
						messageTextField.setText("No railroads are left to build.");
				}else
					messageTextField.setText("You have 0 allowance left.");
				
			// Stadium logic.	
			}else if (currentlySelectedRadioButton.compareToIgnoreCase("stadium") == 0){
				if(playerSelectedDistrict.isStadiumed() == false){
					if(StructureFactory.getInstance().stadiumCount != 0){
						cartListModel.addElement(playerSelectedDistrict.getName() + " " + currentlySelectedRadioButton);
						System.out.println("Build place: " + playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " added to cart.");
						StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("stadium"));
						System.out.println("Stadium count decreased by 1.");
						playerSelectedDistrict.addStadium();
						System.out.println("Stadium added to " + playerSelectedDistrict.getName());
						projectedCosts = projectedCosts + 2;
						totalTextField.setText(""+projectedCosts+" Mil");
					}else
						messageTextField.setText("No stadiums are left to build.");
				}else
					messageTextField.setText("District already has stadium.");
				
			// Skyscraper logic.
			}else if (currentlySelectedRadioButton.compareToIgnoreCase("skyscraper") == 0){
				if(playerSelectedDistrict.isSkyScrapered() == false){
					if(StructureFactory.getInstance().skyscraperCount != 0){
						cartListModel.addElement(playerSelectedDistrict.getName() + " " + currentlySelectedRadioButton);
						System.out.println("Build place: " + playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " added to cart.");
						StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("skyscraper"));
						System.out.println("Skyscraper count decreased by 1.");
						playerSelectedDistrict.addSkyscraper();
						System.out.println("Skyscraper added to " + playerSelectedDistrict.getName());
						projectedCosts = projectedCosts + playerSelectedDistrict.skyscraperCost;
						totalTextField.setText(""+projectedCosts+" Mil");
					}else
						messageTextField.setText("No skyscrapers are left to build.");
				}else
					messageTextField.setText("District already has skyscraper.");
				
			// Monopoly Tower logic.	
			}else if (currentlySelectedRadioButton.compareToIgnoreCase("monopolyTower") == 0) {
				if(StructureFactory.getInstance().monopolyTowerCount != 0){
					cartListModel.addElement(playerSelectedDistrict.getName() + " " + currentlySelectedRadioButton);
					System.out.println("Build place: " + playerSelectedDistrict.getName() + " Build Type: " + currentlySelectedRadioButton + " added to cart.");
					StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("monopolyTower"));
					System.out.println("Monopoly Tower count decreased by 1.");
					playerSelectedDistrict.addMonopolyTower();
					System.out.println("Monopoly Tower added to " + playerSelectedDistrict.getName());
					projectedCosts = projectedCosts + 7;
					totalTextField.setText(""+projectedCosts+" Mil");
				}else
					messageTextField.setText("No monopoly towers are left to build.");
				
		    // Remove hazards logic.
			}else if (currentlySelectedRadioButton.compareToIgnoreCase("removeHazard") == 0) {
				//TODO:
				cartListModel.addElement(playerSelectedDistrict.getName() + " " + currentlySelectedRadioButton);
				System.out.println("Remove hazard from " + playerSelectedDistrict.getName() + " added to cart.");
				projectedCosts = projectedCosts + playerSelectedDistrict.hazard.getBlockCount()*.5;
				totalTextField.setText(""+projectedCosts+" Mil");
			}// End else if.
		}// End addToCartAction;
		
	}// End AddToCartButton.
	
	public class BuildButton extends JButton {
		
		public BuildButton() {
		    setText("Build");
			setPreferredSize(new Dimension(100, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User attempted to build cart items.");
							if(playerCash > projectedCosts){
								currentPlayer.collect(projectedCosts); 
								gameStateMachine.setState(gameStateMachine.getGamePlayState());
							}else
								messageTextField.setText("You do not have enough money to build.");
							
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End BuildButton.
	
	public class DeleteButton extends JButton {
		
		public DeleteButton() {
		    setText("Delete");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked delete button.");
							if(deleteButton.isVisible() == true){
								//deleteButtonAction();
								System.out.println("User clicked Delete.");
								removeListItem(cartListModel.getElementAt(buildList.getSelectedIndex()).toString());
								cartListModel.remove(buildList.getSelectedIndex());
								buildList.clearSelection();
								buildList.setModel(cartListModel);
							}// End if.
						}// End actionPerformed.
			});// End addActionListener.
		}// End constructor.
	}// End DeleteButton.
	
	public class CancelButton extends JButton {
		
		public CancelButton() {
			setText("Cancel");
			setPreferredSize(new Dimension(50, 30));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked Cancel.");
							while(cartListModel.isEmpty() == false){
								removeListItem(cartListModel.getElementAt(0).toString());
								cartListModel.remove(0);
							}
							buildList.setModel(cartListModel);
							gameStateMachine.setState(gameStateMachine.getGamePlayState());
							
						}// End actionPerformed.
			});// End addActionListener.
		}// End constructor.
	}// End CancelButton.
	
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("residential");
							
							// set block type build amount spinner
							int blocksOnDistrict = playerSelectedDistrict.getTotalBlockCount();
							if(8 - blocksOnDistrict < allowanceRemaining){
								SpinnerNumberModel model = new SpinnerNumberModel(0,0, 8-blocksOnDistrict, 1);
								buildTypeAmountSpinner.setModel(model);
							} else{
								SpinnerNumberModel model = new SpinnerNumberModel(0, 0, allowanceRemaining, 1);
								buildTypeAmountSpinner.setModel(model);
							}// End else.
							buildTypeAmountSpinner.setVisible(true);
							
						}// End actionPerformed.
					});// End addActionListener.
		}// end constructor.
		
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("industrial");
							
							// set block type build amount spinner
							int blocksOnDistrict = playerSelectedDistrict.getTotalBlockCount();
							if(8 - blocksOnDistrict < allowanceRemaining){
								SpinnerNumberModel model = new SpinnerNumberModel(0,0, 8-blocksOnDistrict, 1);
								buildTypeAmountSpinner.setModel(model);
							} else{
								SpinnerNumberModel model = new SpinnerNumberModel(0, 0, allowanceRemaining, 1);
								buildTypeAmountSpinner.setModel(model);
							}// End else.
							buildTypeAmountSpinner.setVisible(true);
						}// End actionPerformed.
			});// End addActionListener.
		}// End constructor.
	}// End IndustrialRadioButton.
	
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("railroad");
							
							// Hide spinner
							buildTypeAmountSpinner.setVisible(false);
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End RailroadRadioButton.
	
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("skyscraper");
							
							// Hide spinner
							buildTypeAmountSpinner.setVisible(false);
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End SkyscraperRadioButton.
	
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("stadium");
							
							// Hide spinner
							buildTypeAmountSpinner.setVisible(false);
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End StadiumRadioButton.
	
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
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("monopolyTower");
							
							// Hide spinner
							buildTypeAmountSpinner.setVisible(false);
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End MonopolyTowerRadioButton.
	
	public class RemoveHazardRadioButton extends JRadioButton {
		
		public RemoveHazardRadioButton() {
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User chose to remove a hazard.");
							railroadRadioButton.setSelected(false);
							industrialRadioButton.setSelected(false);
							residentialRadioButton.setSelected(false);
							stadiumRadioButton.setSelected(false);
							monopolyTowerRadioButton.setSelected(false);
							skyscraperRadioButton.setSelected(false);
							removeHazardRadioButton.setSelected(true);
							
							//Currently selected radio button
							currentlySelectedRadioButton = new String("removeHazard");
							
							// Hide spinner
							buildTypeAmountSpinner.setVisible(false);
						}// End actionPerformed.
					});// End addActionListener.
		}// End constructor.
	}// End RemoveHazardRadioButton.
	
	public void removeListItem(String str) {
		//System.out.println("cart list "+cartListModel.getElementAt(buildList.getSelectedIndex()));
		String deleteSelection = str;
		String []splits = deleteSelection.split(" ");
		
		
		//Residential / Industrial delete from list logic.
		if(splits[1].compareToIgnoreCase("residential") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeResidentialBlock(Integer.parseInt(splits[2]));
			for(int ii = 0; ii < Integer.parseInt(splits[2]); ii++)
				StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("residential"));
			
			allowanceRemaining = allowanceRemaining + Integer.parseInt(splits[2]);
			buildAllowanceTextField.setText(""+allowanceRemaining + " Blocks");
			projectedCosts = projectedCosts - playerSelectedDistrict.getResidentialCost()*Integer.parseInt(splits[2]);
			totalTextField.setText(""+projectedCosts+" Mil");
			
			//TODO:
		//Industrial delete from list logic.
		}else if(splits[1].compareToIgnoreCase("industrial") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeIndustrialBlock(Integer.parseInt(splits[2]));
			for(int ii = 0; ii < Integer.parseInt(splits[2]); ii++)
				StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("industrial"));
			
			allowanceRemaining = allowanceRemaining + Integer.parseInt(splits[2]);
			buildAllowanceTextField.setText(""+allowanceRemaining + " Blocks");
			projectedCosts = projectedCosts - playerSelectedDistrict.getIndustrialCost()*Integer.parseInt(splits[2]);
			totalTextField.setText(""+projectedCosts+" Mil");
			
		//Railroad delete from list logic.
		}else if(splits[1].compareToIgnoreCase("railroad") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeRailroad();
			StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("railroad"));
			allowanceRemaining = allowanceRemaining - 1;
			buildAllowanceTextField.setText("Railroad");
			
		//Stadium delete from list logic.
		}else if(splits[1].compareToIgnoreCase("stadium") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeStadium();
			StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("stadium"));
			projectedCosts = projectedCosts - 2;
			totalTextField.setText(""+projectedCosts+" Mil");
			
		//Skyscraper delete from list logic.
		}else if(splits[1].compareToIgnoreCase("skyscraper") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeSkyscraper();
			StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("skyscraper"));
			projectedCosts = projectedCosts - GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).skyscraperCost;
			totalTextField.setText(""+projectedCosts+" Mil");
			
		//Monopoly Tower delete from list logic.
		}else if(splits[1].compareToIgnoreCase("monopolyTower") == 0){
			GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeMonopolyTower();
			StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("monopolyTower"));
			projectedCosts = projectedCosts - 7;
			totalTextField.setText(""+projectedCosts+" Mil");
			
		//Remove hazard delete from list logic.
		}else if(splits[0].compareToIgnoreCase("removeHazard") == 0){
			//TODO;
			//GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).removeHazard();
			//StructureFactory.getInstance().scrap(StructureFactory.getInstance().getStructureByName("hazard"));
			projectedCosts = projectedCosts - .5 * GameMaster.getInstance().getBoard().getDistrictByName(splits[0]).hazard.getBlockCount();
			totalTextField.setText(""+projectedCosts+" Mil");
		}// End else if.
	}// End remove from list.
	
	public int generateAllowance() {
		Random generator = new Random();
		int random = generator.nextInt(4);
		if(random == 0){
			return -1;
		}else{
			return random;
		}// End else.
	}// End generateAllowance.
	
	public void enter() {
		System.out.println("This is the Build State.");
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		playerCash = GameMaster.getInstance().getCurrentPlayer().getCash();
		playerAllowance = generateAllowance();
		allowanceRemaining = playerAllowance;
		ownedDistrictsComboBox = new OwnedDistrictsComboBox();
		playerCashTextField.setText(""+playerCash+ " Mil");
		buildAllowanceTextField = new BuildAllowanceTextField();
		buildShopPanel.add(buildAllowanceTextField, "cell 1 0 2 1");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		startMode();
			
	}
	
	private void startMode() {
		layeredPane.add(baseLayer);
		baseLayer.setVisible(true);
	}
		
}// End BuildState.
	
