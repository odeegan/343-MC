package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardSewagePlant extends ChanceCard {

	
	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton sewagePlantButton;
	private boolean failed;
	
	public ChanceCardSewagePlant(){
		failed = false;
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		
		sewagePlantButton = new JButton("Build Sewage Plant");
		sewagePlantButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				sewagePlantButtonPerformed();
			}
		});
		gamePane.clearSelectedDistrict();
		gamePane.setMessagePanelText("You drew the Sewage Plant Card! Select " +
				"a District to build a Sewage Plant on!");
		if(failed)gamePane.addMessagePanelText("District cannot have a Bonus on it!");
		gamePane.addMessagePanelButton(sewagePlantButton);
	}

	protected void sewagePlantButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(district.bonus == null){
					gamePane.clearSelectedDistrict();
					district.hazard = STRUCTURE.SEWAGEPLANT;
					gamePane.setMessagePanelText(district.getName() +" now has a Sewage Plant!");
					failed = false;
					GameMaster.getInstance().setPerformed(true);
					sewagePlantButton.setVisible(false);
					gamePane.update();
				}else{
					failed = true;
					performCard();
			}}
		}
	else{
		failed = true;
		performCard();
	}	
	}

}
