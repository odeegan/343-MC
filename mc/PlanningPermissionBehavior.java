package mc;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;



public class PlanningPermissionBehavior implements SquareBehavior {

	public PlanningPermissionBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("You landed on Planning Permission");
		gamePane.addSelectionLayer();
		int i = GameMaster.getInstance().getCurrentPlayer().getPosition();
		
		if (i > 0 && i < 10) {
			// Rubbish Dump / Park
			gamePane.addMessagePanelText("Build a ...");
			
			JCheckBox dump = new JCheckBox("Rubbish Dump", true);
			dump.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JCheckBox park = new JCheckBox("Park", true);
			park.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			
			gamePane.addMessagePanelCheckBox(dump);
			gamePane.addMessagePanelCheckBox(park);
		}
		if (i > 10 && i < 20) {
			// Prison / School
			gamePane.addMessagePanelText("Build a ...");
			
			JCheckBox prison = new JCheckBox("Prison", true);
			prison.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JCheckBox school = new JCheckBox("School", true);
			school.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			gamePane.addMessagePanelCheckBox(prison);
			gamePane.addMessagePanelCheckBox(school);
		}
		
		if (i > 20 && i < 30) {
			// Sewage Plant / Water Tower
			gamePane.addMessagePanelText("Build a ...");
			
			JCheckBox sewage = new JCheckBox("Sewage Plant", true);
			sewage.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JCheckBox water = new JCheckBox("Water Tower", true);
			water.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
		
			gamePane.addMessagePanelCheckBox(sewage);
			gamePane.addMessagePanelCheckBox(water);
		}

		if (i > 30 && i <= 39) {
			// Power Station / Wind Farm
			gamePane.addMessagePanelText("Build a ...");
		
			JCheckBox power = new JCheckBox("Power Station", true);
			power.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JCheckBox wind = new JCheckBox("Wind Farm", true);
			wind.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
		
			gamePane.addMessagePanelCheckBox(power);
			gamePane.addMessagePanelCheckBox(wind);
		}

	}

}
