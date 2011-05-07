package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;



public class PlanningPermissionBehavior implements SquareBehavior {

	GameMaster gameMaster;
	GamePane gamePane;
	String structure;
	
	public PlanningPermissionBehavior() {
		gamePane = GamePane.getInstance();
		gameMaster = GameMaster.getInstance();
	}

	@Override
	public void execute() {
		
		gamePane.setMessagePanelText("You landed on Planning Permission");
		gamePane.addMessagePanelText("Select a Hazard or a Bonus to build.");
		
		int i = GameMaster.getInstance().getCurrentPlayer().getPosition();
		
		
		if (i > 0 && i < 10) {
			// Rubbish Dump / Park
			
			JCheckBox dump = new JCheckBox("Rubbish Dump");
			dump.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());
				        	
				        }
			    }
			);
			JCheckBox park = new JCheckBox("Park");
			park.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
		        		System.out.println(e.getActionCommand());
		        		buildStructure(e.getActionCommand());		        	
			        }
			    }
			);

			gamePane.addMessagePanelCheckBox(dump);
			gamePane.addMessagePanelCheckBox(park);
		}
		if (i > 10 && i < 20) {
			// Prison / School			
			JCheckBox prison = new JCheckBox("Prison");
			prison.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	
			        }
			    }
			);
			JCheckBox school = new JCheckBox("School");
			school.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	
				        }
			    }
			);

			gamePane.addMessagePanelCheckBox(prison);
			gamePane.addMessagePanelCheckBox(school);
		}
		
		if (i > 20 && i < 30) {
			// Sewage Plant / Water Tower
			JCheckBox sewage = new JCheckBox("Sewage Plant");
			sewage.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	

			        }
			    }
			);
			
			JCheckBox water = new JCheckBox("Water Tower");
			water.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	

			        }
			    }
			);

			gamePane.addMessagePanelCheckBox(sewage);
			gamePane.addMessagePanelCheckBox(water);
		}

		if (i > 30 && i <= 39) {
			// Power Station / Wind Farm		
			JCheckBox power = new JCheckBox("Power Station");
			power.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	

			        }
			    }
			);
			JCheckBox wind = new JCheckBox("Wind Farm");
			wind.addActionListener(
				    new ActionListener() {
				        public void actionPerformed(ActionEvent e) {
			        		System.out.println(e.getActionCommand());
			        		buildStructure(e.getActionCommand());		        	

			        }
			    }
			);

			gamePane.addMessagePanelCheckBox(power);
			gamePane.addMessagePanelCheckBox(wind);
		}

	}

	public void buildStructure(String str) {
		
		final String structure = str; 
		gamePane.clearMessageLayer();
		gamePane.setMessagePanelText(structure);
		gamePane.addMessagePanelText("Now select a district to build on.");
		
		JButton buildItButton = new JButton("build it");
		buildItButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						District district = GameMaster.getInstance().getBoard().getDistrict(gamePane.getSelectedDistrict());
						
						GamePane.getInstance().clearMessageLayer();
						
						if (structure == "Rubbish Dump") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.RUBBISHDUMP));
						} else if (structure == "Park") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.PARK));
						} else if (structure == "Prison") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.PRISON));
						} else if (structure == "School") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.SCHOOL));
						} else if (structure == "Sewage Plant") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.SEWAGEPLANT));
						} else if (structure == "Water Tower") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.WATERTOWER));
						} else if (structure == "Power Station") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.POWERSTATION));
						} else if (structure == "Wind Farm") {
							district.addHazard(
									StructureFactory.getInstance().get(STRUCTURE.WINDFARM));
						}
					
					}
				});

		gamePane.addMessagePanelButton(buildItButton);
	}

}
