package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChanceCardRailroad extends ChanceCard {
	GameMaster gameMaster;
	GamePane gamePane;
	ArrayList<District> districts;
	ArrayList<District> unownedDistricts;
	boolean failed;
	
	JButton railroadBuildButton = new JButton("Build Railroad");
	
	public ChanceCardRailroad() {
	failed = false;
	}

	@Override
	public void performCard() {
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		gamePane.clearMessageLayer();
		if(!failed)	gamePane.setMessagePanelText("You drew build Railroad Card!");
		else gamePane.setMessagePanelText("District already has Railroad.");
		gamePane.addMessagePanelText("Select a district with no Railroad.");

		gamePane.clearSelectedDistrict();
		
		railroadBuildButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						railroadBuildButtonPerformed();
					}
				});
		gamePane.addMessagePanelButton(railroadBuildButton);
	}

	protected void railroadBuildButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(!district.isRailroaded() && StructureFactory.getInstance().railroadCount > 0 ){
					district.addRailroad();
					StructureFactory.getInstance().get(StructureFactory.getInstance().getStructureByName("railroad"));
					GameMaster.getInstance().setPerformed(true);
					gamePane.update();
					railroadBuildButton.setVisible(false);
					gamePane.clearMessageLayer();
				}else{
					failed = true;
					performCard();
				}
			}
		}
		else{
			failed = true;
			performCard();
		}
	}
}

