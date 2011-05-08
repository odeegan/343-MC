package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardPrisonOvercrowding extends ChanceCard {
	
	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton prisonOvercrowdingButton;
	private boolean failed;
	
	public ChanceCardPrisonOvercrowding(){
		failed = false;
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		
		prisonOvercrowdingButton = new JButton("Build Prison");
		prisonOvercrowdingButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				prisonOvercrowdingButtonPerformed();
			}
		});
		gamePane.clearSelectedDistrict();
		gamePane.setMessagePanelText("You drew the Prison Overcrowding Card! Select " +
				"a District to build a Prison on!");
		if(failed)gamePane.addMessagePanelText("District cannot have a Bonus on it!");
		gamePane.addMessagePanelButton(prisonOvercrowdingButton);
	}

	protected void prisonOvercrowdingButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(district.bonus == null){
					gamePane.clearSelectedDistrict();
					district.hazard = STRUCTURE.PRISON;
					gamePane.setMessagePanelText(district.getName() +" now has a Prison!");
					failed = false;
					prisonOvercrowdingButton.setVisible(false);
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
