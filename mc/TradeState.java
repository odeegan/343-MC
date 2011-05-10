
package mc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
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
	
	private static JLabel currentPlayerNameLabel;
	private static JLabel currentPlayerSelectDistrictLabel;
	
	private static JComboBox currentPlayerDistrictsComboBox;
	private static DefaultComboBoxModel currentPlayerDistrictsComboBoxModel;
	
	private static JButton currentPlayerDeleteButton;
	private static JButton currentPlayerAddButton;
	private static JButton currentPlayerCancelButton;
	private static JButton currentPlayerAgreeButton;
	
	private static JSlider currentPlayerCashSlider;
	
	private static JTextField currentPlayerNameTextField;
	
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
	
	private static JLabel tradePartnerSelectDistrictLabel;
	private static JLabel selectTradePartnerLabel;
	
	private static JComboBox tradePartnerDistrictsComboBox;
	private DefaultComboBoxModel tradePartnerDistrictsComboBoxModel; 
	private static JComboBox selectTradePartnerComboBox;
	private DefaultComboBoxModel selectTradePartnerComboBoxModel;

	private static JButton tradePartnerDeleteButton;
	private static JButton tradePartnerAddButton;
	private static JButton tradePartnerCancelButton;
	private static JButton tradePartnerAgreeButton;
	
	private static JSlider tradePartnerCashSlider;

	private static JList tradePartnerTradeList;
	private DefaultListModel tradePartnerTradeListModel;
	
	private static String[] tradePartnerDistricts;
	

	private double tradePartnerCash;
	

	private Player currentPlayer;
	private Player tradePartner;
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
		baseLayer.setBounds(0, 0, 500, 380);
		baseLayer.setOpaque(true);
		
		//Current Player
		
		currentPlayerTradePanel = new JPanel();
		currentPlayerTradePanel.setLayout(new MigLayout());
		currentPlayerTradePanel.setPreferredSize(new Dimension(250,350));
		//currentPlayerTradePanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
		
		currentPlayerNamePanel = new JPanel();
		currentPlayerNamePanel.setLayout(new MigLayout());
		currentPlayerNamePanel.setPreferredSize(new Dimension(250,50));
		currentPlayerNamePanel.setBorder(BorderFactory.createTitledBorder("Player"));	
		
		currentPlayerDistrictPanel = new JPanel();
		currentPlayerDistrictPanel.setLayout(new MigLayout());
		currentPlayerDistrictPanel.setPreferredSize(new Dimension(250,150));
		currentPlayerDistrictPanel.setBorder(BorderFactory.createTitledBorder("Districts to Trade"));	
		
		currentPlayerCashPanel = new JPanel();
		currentPlayerCashPanel.setLayout(new MigLayout());
		currentPlayerCashPanel.setPreferredSize(new Dimension(250,75));
		currentPlayerCashPanel.setBorder(BorderFactory.createTitledBorder("Cash to Trade"));
		
		currentPlayerButtonPanel = new JPanel();
		currentPlayerButtonPanel.setLayout(new MigLayout());
		currentPlayerButtonPanel.setPreferredSize(new Dimension(250,75));
		currentPlayerButtonPanel.setBorder(BorderFactory.createLineBorder(null));
		
		
		currentPlayerNameLabel = new JLabel("Name:");

		currentPlayerNameTextField = new JTextField();
		currentPlayerNameTextField.setEditable(false);
		currentPlayerNameTextField.setPreferredSize(new Dimension(250,30));
		
		currentPlayerSelectDistrictLabel = new JLabel("District:");
		
		currentPlayerDistrictsComboBox = new JComboBox();	
		currentPlayerDistrictsComboBox.setPreferredSize(new Dimension(100,35));
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
		currentPlayerAddButton.setPreferredSize(new Dimension(50,35));
		currentPlayerAddButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								System.out.println("Current player clicked add button.");
								//currentPlayerAddButtonActionPerformed();
							}
						});
		
		currentPlayerDeleteButton = new JButton("Delete");
		currentPlayerDeleteButton.setPreferredSize(new Dimension(50,35));
		currentPlayerDeleteButton.setVisible(false);
		currentPlayerDeleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked delete button.");
						//currentPlayerDeleteButtonActionPerformed();
					}
				});
		
		currentPlayerCashSlider = new JSlider();
		
		currentPlayerCancelButton = new JButton("Cancel");
		currentPlayerCancelButton.setPreferredSize(new Dimension(100,50));
		currentPlayerCancelButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked cancel button.");
						//currentPlayerCancelButtonActionPerformed();
					}
				});
		
		currentPlayerAgreeButton = new JButton("Agree");
		currentPlayerAgreeButton.setPreferredSize(new Dimension(100,50));
		currentPlayerAgreeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Current player clicked agree button.");
						//currentPlayerAgreeButtonActionPerformed();
					}
				});
		
		currentPlayerTradeList = new JList();
		currentPlayerTradeList.setPreferredSize(new Dimension(100,150));
		currentPlayerTradeListModel = new DefaultListModel();

		currentPlayerNamePanel.add(currentPlayerNameLabel, "cell 0 0 1 1");
		currentPlayerNamePanel.add(currentPlayerNameTextField, "cell 1 0 3 1");
		
		currentPlayerDistrictPanel.add(currentPlayerSelectDistrictLabel, "cell 0 0 1 1");
		currentPlayerDistrictPanel.add(currentPlayerDistrictsComboBox, "cell 1 0 1 1");
		currentPlayerDistrictPanel.add(currentPlayerTradeList, "cell 2 0 1 3");
		currentPlayerDistrictPanel.add(currentPlayerAddButton, "cell 0 1 1 1");
		currentPlayerDistrictPanel.add(currentPlayerDeleteButton, "cell 1 1 1 1");
		
		currentPlayerCashPanel.add(currentPlayerCashSlider, "cell 0 0 1 1");
		
		currentPlayerButtonPanel.add(currentPlayerCancelButton, "cell 0 0 1 1");
		currentPlayerButtonPanel.add(currentPlayerAgreeButton, "cell 1 0 1 1");
		
		currentPlayerTradePanel.add(currentPlayerNamePanel, "cell 0 0 1 1");
		currentPlayerTradePanel.add(currentPlayerDistrictPanel, "cell 0 1 1 1");
		currentPlayerTradePanel.add(currentPlayerCashPanel, "cell 0 2 1 1");
		currentPlayerTradePanel.add(currentPlayerButtonPanel, "cell 0 3 1 1");
		
		
		//Trade partner
		
		tradePartnerPanel = new JPanel();
		tradePartnerPanel.setLayout(new MigLayout());
		tradePartnerPanel.setPreferredSize(new Dimension(250,350));
		//tradePartnerTradePanel.setBorder(BorderFactory.createTitledBorder("Player 1"));
		
		tradePartnerNamePanel = new JPanel();
		tradePartnerNamePanel.setLayout(new MigLayout());
		tradePartnerNamePanel.setPreferredSize(new Dimension(250,50));
		tradePartnerNamePanel.setBorder(BorderFactory.createTitledBorder("Trade Partner"));	
		
		tradePartnerDistrictPanel = new JPanel();
		tradePartnerDistrictPanel.setLayout(new MigLayout());
		tradePartnerDistrictPanel.setPreferredSize(new Dimension(250,150));
		tradePartnerDistrictPanel.setBorder(BorderFactory.createTitledBorder("Districts to Trade"));	
		
		tradePartnerCashPanel = new JPanel();
		tradePartnerCashPanel.setLayout(new MigLayout());
		tradePartnerCashPanel.setPreferredSize(new Dimension(250,75));
		tradePartnerCashPanel.setBorder(BorderFactory.createTitledBorder("Cash to Trade"));
		
		tradePartnerButtonPanel = new JPanel();
		tradePartnerButtonPanel.setLayout(new MigLayout());
		tradePartnerButtonPanel.setPreferredSize(new Dimension(250,75));
		tradePartnerButtonPanel.setBorder(BorderFactory.createLineBorder(null));
		
		
		selectTradePartnerLabel = new JLabel("Partner Name:");

		selectTradePartnerComboBox = new JComboBox();
		selectTradePartnerComboBox.setPreferredSize(new Dimension(100,35));
		selectTradePartnerComboBox.setEditable(false);
		selectTradePartnerComboBoxModel = new DefaultComboBoxModel(empty);
		selectTradePartnerComboBoxModel.removeAllElements();
/*		selectTradePartnerComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange() == ItemEvent.SELECTED){
						}//End if.
					}// End itemStateChanged.
		});// End addItemListener.
*/
		
		tradePartnerSelectDistrictLabel = new JLabel("District:");
		
		tradePartnerDistrictsComboBox = new JComboBox();	
		tradePartnerDistrictsComboBox.setPreferredSize(new Dimension(100,35));
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
		tradePartnerAddButton.setPreferredSize(new Dimension(50,35));
		tradePartnerAddButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								System.out.println("Trade partner clicked add button.");
								//tradePartnerAddButtonActionPerformed();
							}
						});
		
		tradePartnerDeleteButton = new JButton("Delete");
		tradePartnerDeleteButton.setPreferredSize(new Dimension(50,35));
		tradePartnerDeleteButton.setVisible(false);
		tradePartnerDeleteButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked delete button.");
						//tradePartnerDeleteButtonActionPerformed();
					}
				});
		
		tradePartnerCashSlider = new JSlider();
		
		tradePartnerCancelButton = new JButton("Cancel");
		tradePartnerCancelButton.setPreferredSize(new Dimension(100,50));
		tradePartnerCancelButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked cancel button.");
						//tradePartnerCancelButtonActionPerformed();
					}
				});
		
		tradePartnerAgreeButton = new JButton("Agree");
		tradePartnerAgreeButton.setPreferredSize(new Dimension(100,50));
		tradePartnerAgreeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Trade partner clicked agree button.");
						//tradePartnerAgreeButtonActionPerformed();
					}
				});
		
		tradePartnerTradeList = new JList();
		tradePartnerTradeList.setPreferredSize(new Dimension(100,150));
		tradePartnerTradeListModel = new DefaultListModel();

		tradePartnerNamePanel.add(selectTradePartnerLabel, "cell 0 0 1 1");
		tradePartnerNamePanel.add(selectTradePartnerComboBox, "cell 1 0 1 1");
		
		tradePartnerDistrictPanel.add(tradePartnerSelectDistrictLabel, "cell 0 0 1 1");
		tradePartnerDistrictPanel.add(tradePartnerDistrictsComboBox, "cell 1 0 1 1");
		tradePartnerDistrictPanel.add(tradePartnerTradeList, "cell 2 0 1 3");
		tradePartnerDistrictPanel.add(tradePartnerAddButton, "cell 0 1 1 1");
		tradePartnerDistrictPanel.add(tradePartnerDeleteButton, "cell 1 1 1 1");
		
		tradePartnerCashPanel.add(tradePartnerCashSlider, "cell 0 0 1 1");
		
		tradePartnerButtonPanel.add(tradePartnerCancelButton, "cell 0 0 1 1");
		tradePartnerButtonPanel.add(tradePartnerAgreeButton, "cell 1 0 1 1");
		
		tradePartnerPanel.add(tradePartnerNamePanel, "cell 0 0 1 1");
		tradePartnerPanel.add(tradePartnerDistrictPanel, "cell 0 1 1 1");
		tradePartnerPanel.add(tradePartnerCashPanel, "cell 0 2 1 1");
		tradePartnerPanel.add(tradePartnerButtonPanel, "cell 0 3 1 1");
		
		
		
		baseLayer.add(currentPlayerTradePanel, BorderLayout.WEST);
		baseLayer.add(tradePartnerPanel, BorderLayout.EAST);
		
		layeredPane.add(baseLayer);
		
	}// End constructor.

	
	public void enter() {
		System.out.println("This is the Build State.");
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		currentPlayerCash = GameMaster.getInstance().getCurrentPlayer().getCash();
		
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		startMode();
			
	}// End enter.
	
	private void startMode() {
		baseLayer.setVisible(true);
		
	}// End startMode.
		
}// End BuildState.
	

