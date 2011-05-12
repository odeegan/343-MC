
package mc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

public class TradeState implements GameState{

	GameMaster gameMaster;
	GameStateMachine gameStateMachine;
	
	JFrame mainFrame;
	JLayeredPane layeredPane = new JLayeredPane();
	
	private static JPanel baseLayer;
	
	//Current Player
	private static JPanel currentPlayerTradePanel;
	private static JPanel currentPlayerNamePanel;
	private static JPanel currentPlayerDistrictPanel;
	private static JPanel currentPlayerCashPanel;
	private static JPanel currentPlayerButtonPanel;
	private static JPanel currentPlayerListPanel;
	private static JPanel messagePanel;
	
	private static JLabel currentPlayerNameLabel;
	private static JLabel currentPlayerSelectDistrictLabel;
	
	private static JComboBox currentPlayerDistrictsComboBox;
	private static DefaultComboBoxModel currentPlayerDistrictsComboBoxModel;
	
	private static JButton currentPlayerDeleteButton;
	private static JButton currentPlayerAddButton;
	private static JButton currentPlayerCancelButton;
	private static JToggleButton currentPlayerAgreeButton;
	
	private static JSlider currentPlayerCashSlider;
	
	private static JTextField currentPlayerNameTextField;
	private static JTextField currentPlayerCashTextField;
	
	private static JList currentPlayerTradeList;
	private DefaultListModel currentPlayerTradeListModel;
	
	private static String[] currentPlayerDistricts;
	
	private double currentPlayerCash;
	
	//Trade partner
	private static JPanel tradePartnerPanel;
	private static JPanel tradePartnerNamePanel;
	private static JPanel tradePartnerDistrictPanel;
	private static JPanel tradePartnerCashPanel;
	private static JPanel tradePartnerButtonPanel;
	private static JPanel tradePartnerListPanel;
	
	private static JLabel tradePartnerSelectDistrictLabel;
	private static JLabel selectTradePartnerLabel;
	
	private static JComboBox tradePartnerDistrictsComboBox;
	private DefaultComboBoxModel tradePartnerDistrictsComboBoxModel; 
	private static JComboBox selectTradePartnerComboBox;
	private DefaultComboBoxModel selectTradePartnerComboBoxModel;

	private static JButton tradePartnerDeleteButton;
	private static JButton tradePartnerAddButton;
	private static JButton tradePartnerCancelButton;
	private static JToggleButton tradePartnerAgreeButton;
	
	private static JSlider tradePartnerCashSlider;
	
	private static JTextField tradePartnerCashTextField;

	private static JList tradePartnerTradeList;
	private DefaultListModel tradePartnerTradeListModel;
	
	private static JTextField messageTextField;

	
	private double tradePartnerCash;	
	private static final DecimalFormat money = new DecimalFormat("###,###,###,###");
	private Player currentPlayer;
	private Player tradePartner;
	private ArrayList<Player> players;
	private ArrayList<District> currentPlayersDistricts;
	private ArrayList<District> tradePartnersDistricts;
	String[] playersNames;
	String[] empty = new String[0];
	

	
	public TradeState(GameStateMachine gsm, JFrame mf) {
		gameMaster = GameMaster.getInstance();
		gameStateMachine = gsm;
		mainFrame = mf;
		
		initComponents();
	}
	
	private void initComponents() {
		
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(new MigLayout("align center"));
		
		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 800, 400);
		baseLayer.setOpaque(true);
		
		//Current Player
		
		currentPlayerTradePanel = new JPanel();
		currentPlayerTradePanel.setLayout(new MigLayout());
		currentPlayerTradePanel.setPreferredSize(new Dimension(400,400));
		//currentPlayerTradePanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
		
		currentPlayerNamePanel = new JPanel();
		currentPlayerNamePanel.setLayout(new MigLayout());
		currentPlayerNamePanel.setPreferredSize(new Dimension(400,50));
		currentPlayerNamePanel.setBorder(BorderFactory.createTitledBorder("Player"));	
		
		currentPlayerDistrictPanel = new JPanel();
		currentPlayerDistrictPanel.setLayout(new MigLayout("","","[]80[]"));
		currentPlayerDistrictPanel.setPreferredSize(new Dimension(200,200));
		currentPlayerDistrictPanel.setBorder(BorderFactory.createTitledBorder("Districts to Trade"));
		
		currentPlayerListPanel = new JPanel();
		currentPlayerListPanel.setLayout(new MigLayout());
		currentPlayerListPanel.setPreferredSize(new Dimension(200,200));
		currentPlayerListPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
		
		currentPlayerCashPanel = new JPanel();
		currentPlayerCashPanel.setLayout(new MigLayout());
		currentPlayerCashPanel.setPreferredSize(new Dimension(400,75));
		currentPlayerCashPanel.setBorder(BorderFactory.createTitledBorder("Cash to Trade"));
		
		currentPlayerButtonPanel = new JPanel();
		currentPlayerButtonPanel.setLayout(new MigLayout("","[]135[]",""));
		currentPlayerButtonPanel.setPreferredSize(new Dimension(400,75));
		currentPlayerButtonPanel.setBorder(BorderFactory.createLineBorder(null));
		
		messagePanel = new JPanel();
		messagePanel.setLayout(new MigLayout());
		messagePanel.setPreferredSize(new Dimension(800,30));
		messagePanel.setBorder(BorderFactory.createLineBorder(null));
		
		
		currentPlayerNameLabel = new JLabel("Name:");

		currentPlayerNameTextField = new JTextField();
		currentPlayerNameTextField.setEditable(false);
		currentPlayerNameTextField.setPreferredSize(new Dimension(100,30));
		
		currentPlayerSelectDistrictLabel = new JLabel("District:");
		
		currentPlayerDistrictsComboBox = new JComboBox();	
		currentPlayerDistrictsComboBox.setPreferredSize(new Dimension(120,30));
		currentPlayerDistrictsComboBox.setEditable(false);
		currentPlayerDistrictsComboBoxModel = new DefaultComboBoxModel(empty);
		currentPlayerDistrictsComboBoxModel.removeAllElements();
/*		currentPlayerDistrictsComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
							enableButtonsAccordingToDistrict();
						}//End if.
					}// End itemStateChanged.
		});// End addItemListener.
*/		
		
		
		
		currentPlayerAddButton = new JButton("Add");
		currentPlayerAddButton.setRolloverEnabled(true);
		currentPlayerAddButton.setPreferredSize(new Dimension(50,35));
		currentPlayerAddButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked add button.");
						currentPlayerAddButtonActionPerformed();
					}
				});
		
		currentPlayerDeleteButton = new JButton("Delete");
		currentPlayerDeleteButton.setRolloverEnabled(true);
		currentPlayerDeleteButton.setPreferredSize(new Dimension(50,35));
		currentPlayerDeleteButton.setVisible(false);
		currentPlayerDeleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked delete button.");
						currentPlayerDeleteButtonActionPerformed();
					}
				});
		
		currentPlayerCashSlider = new JSlider();
		currentPlayerCashSlider.setMinimum(0);
		currentPlayerCashSlider.setValue(100000);
		currentPlayerCashSlider.setMinorTickSpacing(100000);
		currentPlayerCashSlider.setMajorTickSpacing(1000000);
		currentPlayerCashSlider.setSnapToTicks(true);
		currentPlayerCashSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				currentPlayerCashTextField.setText(money.format(currentPlayerCashSlider.getValue()));
			}
		});
		
		currentPlayerCashTextField = new JTextField();
		currentPlayerCashTextField.setEditable(false);
		currentPlayerCashTextField.setPreferredSize(new Dimension(100,30));
		
		currentPlayerCancelButton = new JButton("Cancel");
		currentPlayerCancelButton.setPreferredSize(new Dimension(100,50));
		currentPlayerCancelButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked cancel button.");
						currentPlayerCancelButtonActionPerformed();
					}
				});
		
		currentPlayerAgreeButton = new JToggleButton("Agree");
		currentPlayerAgreeButton.setRolloverEnabled(true);
		currentPlayerAgreeButton.setPreferredSize(new Dimension(100,50));
		currentPlayerAgreeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked agree button.");
						currentPlayerAgreeButtonActionPerformed();
					}
				});
		
		currentPlayerTradeList = new JList();
		currentPlayerTradeList.setPreferredSize(new Dimension(200,200));
		currentPlayerTradeListModel = new DefaultListModel();
		currentPlayerTradeList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent e){
					if(currentPlayerTradeList.getSelectedIndex() == -1){
						currentPlayerDeleteButton.setVisible(false);
					}else
						currentPlayerDeleteButton.setVisible(true);
				}// End valueChanged.
			});// End addListSelectionListener.

		currentPlayerNamePanel.add(currentPlayerNameLabel, "cell 0 0 1 1");
		currentPlayerNamePanel.add(currentPlayerNameTextField, "cell 1 0 3 1");
		
		currentPlayerDistrictPanel.add(currentPlayerSelectDistrictLabel, "cell 0 0 1 1");
		currentPlayerDistrictPanel.add(currentPlayerDistrictsComboBox, "cell 1 0 1 1");
		currentPlayerDistrictPanel.add(currentPlayerAddButton, "cell 0 1 1 1");
		currentPlayerDistrictPanel.add(currentPlayerDeleteButton, "cell 1 1 1 1");
		
		currentPlayerListPanel.add(currentPlayerTradeList, "cell 0 0 0 0");
		
		currentPlayerCashPanel.add(currentPlayerCashSlider, "cell 0 0 1 1");
		currentPlayerCashPanel.add(currentPlayerCashTextField, "cell 1 0 1 1");
		
		currentPlayerButtonPanel.add(currentPlayerCancelButton, "cell 0 0 2 1");
		currentPlayerButtonPanel.add(currentPlayerAgreeButton, "cell 2 0 2 1");
		
		currentPlayerTradePanel.add(currentPlayerNamePanel, "cell 0 0 4 1");
		currentPlayerTradePanel.add(currentPlayerDistrictPanel, "cell 0 1 1 1");
		currentPlayerTradePanel.add(currentPlayerListPanel, "cell 1 1 1 1");
		currentPlayerTradePanel.add(currentPlayerCashPanel, "cell 0 2 4 1");
		currentPlayerTradePanel.add(currentPlayerButtonPanel, "cell 0 3 4 1");
		
		
		//Trade partner
		
		tradePartnerPanel = new JPanel();
		tradePartnerPanel.setLayout(new MigLayout());
		tradePartnerPanel.setPreferredSize(new Dimension(400,400));
		//tradePartnerTradePanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
		
		tradePartnerNamePanel = new JPanel();
		tradePartnerNamePanel.setLayout(new MigLayout());
		tradePartnerNamePanel.setPreferredSize(new Dimension(400,50));
		tradePartnerNamePanel.setBorder(BorderFactory.createTitledBorder("Trade Partner"));	
		
		tradePartnerDistrictPanel = new JPanel();
		tradePartnerDistrictPanel.setLayout(new MigLayout("","","[]80[]"));
		tradePartnerDistrictPanel.setPreferredSize(new Dimension(200,200));
		tradePartnerDistrictPanel.setBorder(BorderFactory.createTitledBorder("Districts to Trade"));	
		
		tradePartnerListPanel = new JPanel();
		tradePartnerListPanel.setLayout(new MigLayout());
		tradePartnerListPanel.setPreferredSize(new Dimension(200,200));
		tradePartnerListPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
		
		tradePartnerCashPanel = new JPanel();
		tradePartnerCashPanel.setLayout(new MigLayout());
		tradePartnerCashPanel.setPreferredSize(new Dimension(400,75));
		tradePartnerCashPanel.setBorder(BorderFactory.createTitledBorder("Cash to Trade"));
		
		tradePartnerButtonPanel = new JPanel();
		tradePartnerButtonPanel.setLayout(new MigLayout("","[]135[]",""));
		tradePartnerButtonPanel.setPreferredSize(new Dimension(400,75));
		tradePartnerButtonPanel.setBorder(BorderFactory.createLineBorder(null));
		
		
		selectTradePartnerLabel = new JLabel("Partner Name:");

		selectTradePartnerComboBox = new JComboBox();
		selectTradePartnerComboBox.setPreferredSize(new Dimension(120,30));
		selectTradePartnerComboBox.setEditable(false);
		selectTradePartnerComboBoxModel = new DefaultComboBoxModel(empty);
		selectTradePartnerComboBoxModel.removeAllElements();
		selectTradePartnerComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
							selectTradePartnerComboBoxActionPerformed();
						}//End if.
					}// End itemStateChanged.
				});// End addItemListener.

		
		tradePartnerSelectDistrictLabel = new JLabel("District:");
		
		tradePartnerDistrictsComboBox = new JComboBox();	
		tradePartnerDistrictsComboBox.setPreferredSize(new Dimension(120,30));
		tradePartnerDistrictsComboBox.setEditable(false);
		tradePartnerDistrictsComboBoxModel = new DefaultComboBoxModel(empty);
		tradePartnerDistrictsComboBoxModel.removeAllElements();
/*		tradePartnerDistrictsComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
						}//End if.
					}// End itemStateChanged.
		});// End addItemListener.
*/		
		tradePartnerAddButton = new JButton("Add");
		tradePartnerAddButton.setRolloverEnabled(true);
		tradePartnerAddButton.setPreferredSize(new Dimension(50,35));
		tradePartnerAddButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked add button.");
						tradePartnerAddButtonActionPerformed();
					}
				});
		
		tradePartnerDeleteButton = new JButton("Delete");
		tradePartnerDeleteButton.setRolloverEnabled(true);
		tradePartnerDeleteButton.setPreferredSize(new Dimension(50,35));
		tradePartnerDeleteButton.setVisible(false);
		tradePartnerDeleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked delete button.");
						tradePartnerDeleteButtonActionPerformed();
					}
				});
		
		tradePartnerCashSlider = new JSlider();
		tradePartnerCashSlider.setMinimum(0);
		tradePartnerCashSlider.setValue(100000);
		tradePartnerCashSlider.setMinorTickSpacing(100000);
		tradePartnerCashSlider.setMajorTickSpacing(1000000);
		tradePartnerCashSlider.setSnapToTicks(true);
		tradePartnerCashSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				tradePartnerCashTextField.setText(money.format(tradePartnerCashSlider.getValue()));
			}
		});
		
		tradePartnerCashTextField = new JTextField();
		tradePartnerCashTextField.setEditable(false);
		tradePartnerCashTextField.setPreferredSize(new Dimension(100,30));
		
		tradePartnerCancelButton = new JButton("Cancel");
		tradePartnerCancelButton.setRolloverEnabled(true);
		tradePartnerCancelButton.setPreferredSize(new Dimension(100,50));
		tradePartnerCancelButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked cancel button.");
						tradePartnerCancelButtonActionPerformed();
					}
				});
		
		tradePartnerAgreeButton = new JToggleButton("Agree");
		tradePartnerAgreeButton.setRolloverEnabled(true);
		tradePartnerAgreeButton.setPreferredSize(new Dimension(100,50));
		tradePartnerAgreeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked agree button.");
						tradePartnerAgreeButtonActionPerformed();
					}
				});
		
		tradePartnerTradeList = new JList();
		tradePartnerTradeList.setPreferredSize(new Dimension(200,200));
		tradePartnerTradeListModel = new DefaultListModel();
		tradePartnerTradeList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if(tradePartnerTradeList.getSelectedIndex() == -1){
					tradePartnerDeleteButton.setVisible(false);
				}else
					tradePartnerDeleteButton.setVisible(true);
			}// End valueChanged.
		});// End addListSelectionListener.

		tradePartnerNamePanel.add(selectTradePartnerLabel, "cell 0 0 1 1");
		tradePartnerNamePanel.add(selectTradePartnerComboBox, "cell 1 0 1 1");
		
		tradePartnerDistrictPanel.add(tradePartnerSelectDistrictLabel, "cell 0 0 1 1");
		tradePartnerDistrictPanel.add(tradePartnerDistrictsComboBox, "cell 1 0 1 1");
		tradePartnerDistrictPanel.add(tradePartnerAddButton, "cell 0 1 1 1");
		tradePartnerDistrictPanel.add(tradePartnerDeleteButton, "cell 1 1 1 1");
		
		tradePartnerListPanel.add(tradePartnerTradeList, "cell 0 0 0 0");
		
		tradePartnerCashPanel.add(tradePartnerCashSlider, "cell 0 0 1 1");
		tradePartnerCashPanel.add(tradePartnerCashTextField, "cell 1 0 1 1");
		
		tradePartnerButtonPanel.add(tradePartnerCancelButton, "cell 0 0 2 1");
		tradePartnerButtonPanel.add(tradePartnerAgreeButton, "cell 2 0 2 1");
		
		tradePartnerPanel.add(tradePartnerNamePanel, "cell 0 0 4 1");
		tradePartnerPanel.add(tradePartnerDistrictPanel, "cell 0 1 1 1");
		tradePartnerPanel.add(tradePartnerListPanel, "cell 1 1 1 1");
		tradePartnerPanel.add(tradePartnerCashPanel, "cell 0 2 4 1");
		tradePartnerPanel.add(tradePartnerButtonPanel, "cell 0 3 4 1");
		
		messageTextField = new JTextField();
		messageTextField.setPreferredSize(new Dimension(800,30));
		messagePanel.add(messageTextField);		
		
		baseLayer.add(currentPlayerTradePanel, BorderLayout.WEST);
		baseLayer.add(tradePartnerPanel, BorderLayout.EAST);
		baseLayer.add(messagePanel, BorderLayout.SOUTH);
		
		layeredPane.add(baseLayer);
		
	}// End constructor.
	
	protected void currentPlayerAgreeButtonActionPerformed() {
		// TODO Auto-generated method stub
		if(currentPlayerAgreeButton.getText().equals("Agreed!"))
			currentPlayerAgreeButton.setText("Agree?");
		else
			currentPlayerAgreeButton.setText("Agreed!");
		
		if(tradePartnerAgreeButton.getText().equals("Agreed!")){
			tradeAccepted();
		}
	}

	protected void currentPlayerCancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		if(currentPlayerAgreeButton.getText().equals("Agreed!"))
			currentPlayerAgreeButton.setText("Agree?");
		else{
			if(!currentPlayerTradeListModel.isEmpty()){
				currentPlayerTradeListModel.clear();
				currentPlayerTradeList.setModel(currentPlayerTradeListModel);
				currentPlayerDistrictsComboBox.setSelectedIndex(-1);
			}else
				gameStateMachine.setState(gameStateMachine.getGamePlayState());
		}
	}

	protected void currentPlayerDeleteButtonActionPerformed() {
		// TODO Auto-generated method stub
		currentPlayerTradeListModel.removeElementAt(currentPlayerTradeList.getSelectedIndex());
		currentPlayerTradeList.setModel(currentPlayerTradeListModel);
		
	}

	protected void currentPlayerAddButtonActionPerformed() {
		//TODO;
		System.out.println("index is " + currentPlayerDistrictsComboBox.getSelectedIndex());
		if(currentPlayerDistrictsComboBox.getSelectedIndex()!= 0){
			//currentPlayerSelectedDistrict = GameMaster.getInstance().getBoard().getDistrictByName(currentPlayerDistrictsComboBoxModel.getElementAt(currentPlayerDistrictsComboBox.getSelectedIndex()).toString());
			if(!currentPlayerTradeListModel.contains(currentPlayerDistrictsComboBoxModel.getElementAt(currentPlayerDistrictsComboBox.getSelectedIndex()).toString())){
				currentPlayerTradeListModel.addElement(currentPlayerDistrictsComboBoxModel.getElementAt(currentPlayerDistrictsComboBox.getSelectedIndex()).toString());
				currentPlayerTradeList.setModel(currentPlayerTradeListModel);
				currentPlayerDistrictsComboBox.setSelectedIndex(-1);
			}
			else
				messageTextField.setText("District already in list.");
		}else
			messageTextField.setText("Select a vaild district.");		
	}

	private void tradeAccepted() {
		// TODO Auto-generated method stub
		for(int ii = 0; ii<tradePartnerTradeListModel.size(); ii++){
			currentPlayer.addDistrict(gameMaster.getBoard().getDistrictByName(tradePartnerTradeListModel.getElementAt(ii).toString()));
		}
		for(int ii = 0; ii<currentPlayerTradeListModel.size(); ii++){
			tradePartner.addDistrict(gameMaster.getBoard().getDistrictByName(currentPlayerTradeListModel.getElementAt(ii).toString()));
		}
		if(tradePartner!= null){
			if(!currentPlayerCashTextField.getText().equals(""))
				currentPlayer.pay((double)((double)currentPlayerCashSlider.getValue()/(double)1000000), tradePartner);
			if(!tradePartnerCashTextField.getText().equals(""))
				tradePartner.pay((double)tradePartnerCashSlider.getValue()/(double)1000000,currentPlayer);
			gameStateMachine.setState(gameStateMachine.getGamePlayState());
			currentPlayerAgreeButton.setText("Agree?");
			tradePartnerAgreeButton.setText("Agree?");
			currentPlayerAgreeButton.setSelected(false);
			tradePartnerAgreeButton.setSelected(false);
		}
	}

	protected void tradePartnerAgreeButtonActionPerformed() {
		// TODO Auto-generated method stub
		if(tradePartnerAgreeButton.getText().equals("Agreed!"))
			tradePartnerAgreeButton.setText("Agree?");
		else
			tradePartnerAgreeButton.setText("Agreed!");
		
		if(currentPlayerAgreeButton.getText().equals("Agreed!")){
			tradeAccepted();
		}
	}
	
	protected void tradePartnerCancelButtonActionPerformed() {
		// TODO Auto-generated method stub
		if(tradePartnerAgreeButton.getText().equals("Agreed!"))
			tradePartnerAgreeButton.setText("Agree?");
		else{
			if(!tradePartnerTradeListModel.isEmpty()){
				tradePartnerTradeListModel.clear();
				tradePartnerTradeList.setModel(tradePartnerTradeListModel);
				tradePartnerDistrictsComboBox.setSelectedIndex(-1);

			}else
				gameStateMachine.setState(gameStateMachine.getGamePlayState());
		}
	}

	protected void tradePartnerDeleteButtonActionPerformed() {
		// TODO Auto-generated method stub
		tradePartnerTradeListModel.removeElementAt(tradePartnerTradeList.getSelectedIndex());
		tradePartnerTradeList.setModel(tradePartnerTradeListModel);
		
	}

	protected void tradePartnerAddButtonActionPerformed() {
		//TODO;
		if(tradePartnerDistrictsComboBox.getSelectedIndex()!= 0){
			//tradePartnerSelectedDistrict = GameMaster.getInstance().getBoard().getDistrictByName(tradePartnerDistrictsComboBoxModel.getElementAt(tradePartnerDistrictsComboBox.getSelectedIndex()).toString());
			if(!tradePartnerTradeListModel.contains(tradePartnerDistrictsComboBoxModel.getElementAt(tradePartnerDistrictsComboBox.getSelectedIndex()).toString())){
				tradePartnerTradeListModel.addElement(tradePartnerDistrictsComboBoxModel.getElementAt(tradePartnerDistrictsComboBox.getSelectedIndex()).toString());
				tradePartnerTradeList.setModel(tradePartnerTradeListModel);
				tradePartnerDistrictsComboBox.setSelectedIndex(-1);

			}
			else
				messageTextField.setText("District already in list.");
		}else
			messageTextField.setText("Select a vaild district.");		
	}
	protected void selectTradePartnerComboBoxActionPerformed() {
		if(selectTradePartnerComboBox.getSelectedIndex()!= 0){
			String selectedTradePartnersName = selectTradePartnerComboBoxModel.getElementAt(selectTradePartnerComboBox.getSelectedIndex()).toString();	
			tradePartner = gameMaster.getPlayerByName(selectedTradePartnersName);
			tradePartnerCash = tradePartner.getCash();
			tradePartnerCashSlider.setMaximum((int)(tradePartnerCash*1000000));
			System.out.println("trade partner"+ tradePartner.getName());	
			
			tradePartnersDistricts = tradePartner.getDistricts();
			String[] tradePartnersDistrictsNames = new String[tradePartner.getDistricts().size()+1];
			tradePartnersDistrictsNames[0] = ""; // Forces player to have to select the district and not go with default.
			for(int ii = 0; ii < tradePartner.getDistricts().size(); ii++){
				tradePartnersDistrictsNames[ii+1] = tradePartnersDistricts.get(ii).getName();
			}
			tradePartnerDistrictsComboBoxModel = new DefaultComboBoxModel(tradePartnersDistrictsNames);
			tradePartnerDistrictsComboBox.setModel(tradePartnerDistrictsComboBoxModel);
		}else
			messageTextField.setText("Select a valid trade partner.");
		
	}

	public void enter() {
		System.out.println("This is the Trade State.");
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		currentPlayerCash = GameMaster.getInstance().getCurrentPlayer().getCash();
		currentPlayerCashSlider.setMaximum((int)(currentPlayerCash*1000000));
		messageTextField.setText("");
		// set current players name
		currentPlayerNameTextField.setText("        "+currentPlayer.getName());
		// set list of other players to trade with
		players = gameMaster.getPlayers();
		//set players lists models
		currentPlayerTradeListModel = new DefaultListModel();
		tradePartnerTradeListModel = new DefaultListModel();
		//clear buttons
		currentPlayerAgreeButton.setText("Agree?");
		tradePartnerAgreeButton.setText("Agree?");
		currentPlayerCashSlider.setMaximum((int)(currentPlayer.getCash()*1000000));
		currentPlayerCashSlider.setValue(0);
		tradePartnerCashSlider.setValue(0);
		selectTradePartnerComboBox.setSelectedIndex(-1);
		tradePartnerTradeList.setSelectedIndex(-1);
		tradePartnerDistrictsComboBox.setSelectedIndex(-1);
		currentPlayerDistrictsComboBox.setSelectedIndex(-1);
		currentPlayerAgreeButton.setSelected(false);
		tradePartnerAgreeButton.setSelected(false);

		
		// All players in game except for current
		playersNames = new String[players.size()];
		playersNames[0] = ""; // Forces player to have to select the district and not go with default.
		int counter = 0;
		for(int ii = 0; ii < players.size(); ii++){
			if(!players.get(ii).getName().equals(currentPlayer.getName())){
				//if(ii != 0){
					playersNames[counter+1] = players.get(ii).getName();
					counter = counter + 1;
				//}
			}
		}
		
		// Set combo box of players to choose from
		selectTradePartnerComboBoxModel = new DefaultComboBoxModel(playersNames);
		selectTradePartnerComboBox.setModel(selectTradePartnerComboBoxModel);
		
		
		// Set current player's comboBox of districts to choose from.
		currentPlayersDistricts = currentPlayer.getDistricts();
		String[] currentPlayersDistrictsNames = new String[currentPlayer.getDistricts().size()+1];
		currentPlayersDistrictsNames[0] = ""; // Forces player to have to select the district and not go with default.
		for(int ii = 0; ii < currentPlayer.getDistricts().size(); ii++){
			currentPlayersDistrictsNames[ii+1] = currentPlayersDistricts.get(ii).getName();
		}
		currentPlayerDistrictsComboBoxModel = new DefaultComboBoxModel(currentPlayersDistrictsNames);
		currentPlayerDistrictsComboBox.setModel(currentPlayerDistrictsComboBoxModel);
		
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		startMode();
			
	}// End enter.
	
	private void startMode() {
		baseLayer.setVisible(true);
		
	}// End startMode.
		
}// End BuildState.
	

