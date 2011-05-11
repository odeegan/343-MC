package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;

public class ChanceCardEarthquake extends ChanceCard {
	GameMaster gameMaster;
	GamePane gamePane;
	StructureFactory sf;
	ArrayList<District> districts;
	ArrayList<District> unownedDistricts;
	boolean failed;
	boolean bonusExists;
	
	JButton destroyBonusButton = new JButton("DESTROY!");
	
	public ChanceCardEarthquake() {
	failed = false;
	}

	@Override
	public void performCard() {
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		sf = StructureFactory.getInstance();
		gamePane.clearMessageLayer();	
		districts = gameMaster.getBoard().getDistricts();
		
		Iterator<District> itr = districts.iterator();
		while(itr.hasNext())
			if(itr.next().isBonused()){
				bonusExists = true;
				break;
			}
		
		if(!bonusExists){
			destroyBonusButton.setText("Build Hazard");
			gamePane.setMessagePanelText("You drew Earthquake Card!");
			gamePane.addMessagePanelText("No players have districts with bonuses.");
			gamePane.addMessagePanelText("Choose a district to build a hazard instead.");
		}else {
			if(!failed)	gamePane.setMessagePanelText("You drew Earthquake Card!");
			else gamePane.setMessagePanelText("District has no bonus.");
			gamePane.addMessagePanelText("Select a district with a bonus.");
		}
		
		


		gamePane.clearSelectedDistrict();
		
		destroyBonusButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						if(bonusExists)
							destroyBonusButtonPerformed();
						else
							buildHazardButtonPerformed();
					}
				});
		gamePane.addMessagePanelButton(destroyBonusButton);
	}

	protected void destroyBonusButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(district.isBonused()){
					// remove district's bonus
					district.removeBonus();
					gamePane.update();
					GameMaster.getInstance().setPerformed(true);
					destroyBonusButton.setVisible(false);
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
	
	protected void buildHazardButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				Random generator = new Random();
				int random = generator.nextInt(4);
				String hazard = new String("");
				switch(random){
					case 0: if(sf.prisonCount>0){ hazard = "prison"; break;} 
					case 1: if(sf.powerStationCount>0){hazard = "powerStation"; break;}
					case 2: if(sf.sewagePlantCount>0){hazard = "sewagePlant"; break;}
					case 4: if(sf.rubbishDumpCount>0){hazard = "rubbishDump"; break;}
					default:sf.scrap(sf.getStructureByName("rubbishDump"));
							hazard = "rubbishDump";
							break;
				}
				district = (District)selectedSquare;
				sf.get(sf.getStructureByName(hazard));
				district.addHazard(sf.getStructureByName(hazard));
				GameMaster.getInstance().setPerformed(true);
				gamePane.update();
				destroyBonusButton.setVisible(false);
				gamePane.clearMessageLayer();

			}
		}
		else{
			failed = true;
			performCard();
		}
	}
}


