package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChanceCardInheritance extends ChanceCard {
	GameMaster gameMaster;
	GamePane gamePane;
	ArrayList<District> districts;
	ArrayList<District> unownedDistricts;
	boolean failed;
	JButton inheritanceButton;
	
	public ChanceCardInheritance() {
	failed = false;
	}

	@Override
	public void performCard() {
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		gamePane.clearMessageLayer();
		if(!failed)	gamePane.setMessagePanelText("You drew Inheritance!");
		else gamePane.setMessagePanelText("District Already Owned.");
		gamePane.addMessagePanelText("Select an Unowned District to Purchase for 500k.");

		gamePane.setSelectedDistrict(-1);
		inheritanceButton = new JButton("Purchase District");
		inheritanceButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						inheritanceButtonPerformed();
					}
				});
		gamePane.addMessagePanelButton(inheritanceButton);
	}

	protected void inheritanceButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(!district.isOwned()){
					gamePane.clearSelectedDistrict();
					gameMaster.getCurrentPlayer().addDistrict(district);
					gameMaster.getCurrentPlayer().pay(0.5);
					gamePane.clearMessageLayer();
					gamePane.setMessagePanelText("You now own " + district.getName() +"!");
					failed = false;
					inheritanceButton.setVisible(false);
				}else{
					failed = true;
					performCard();
			}}
		}
	else{
		failed = true;
		performCard();
	}}
}
