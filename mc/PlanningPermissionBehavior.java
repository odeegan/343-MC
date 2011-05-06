package mc;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;



public class PlanningPermissionBehavior implements SquareBehavior {

	ButtonGroup buttonGroup;
	
	public PlanningPermissionBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		
		GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("You landed on Planning Permission");
		gamePane.addMessagePanelText("Select a Hazard or a Bonus to build.");
		gamePane.addSelectionLayer();
		
		int i = GameMaster.getInstance().getCurrentPlayer().getPosition();
		
		buttonGroup = new ButtonGroup();
		
		if (i > 0 && i < 10) {
			// Rubbish Dump / Park
			
			JRadioButton dump = new JRadioButton("Rubbish Dump");
			dump.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			        }
			    }
			);
			JRadioButton park = new JRadioButton("Park", true);
			park.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			        }
			    }
			);
			buttonGroup.add(dump);
			buttonGroup.add(park);
			gamePane.addMessagePanelRadioButton(dump);
			gamePane.addMessagePanelRadioButton(park);
		}
		if (i > 10 && i < 20) {
			// Prison / School			
			JRadioButton prison = new JRadioButton("Prison", true);
			prison.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JRadioButton school = new JRadioButton("School", true);
			school.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			buttonGroup.add(prison);
			buttonGroup.add(school);
			gamePane.addMessagePanelRadioButton(prison);
			gamePane.addMessagePanelRadioButton(school);
		}
		
		if (i > 20 && i < 30) {
			// Sewage Plant / Water Tower
			JRadioButton sewage = new JRadioButton("Sewage Plant", true);
			sewage.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			
			JRadioButton water = new JRadioButton("Water Tower", true);
			water.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			buttonGroup.add(sewage);
			buttonGroup.add(water);
			gamePane.addMessagePanelRadioButton(sewage);
			gamePane.addMessagePanelRadioButton(water);
		}

		if (i > 30 && i <= 39) {
			// Power Station / Wind Farm		
			JRadioButton power = new JRadioButton("Power Station", false);
			power.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			JRadioButton wind = new JRadioButton("Wind Farm", false);
			wind.addItemListener(
			    new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			            // Set "ignore" whenever box is checked or unchecked.
			            //ignore = (e.getStateChange() == ItemEvent.SELECTED);
			        }
			    }
			);
			buttonGroup.add(power);
			buttonGroup.add(wind);
			gamePane.addMessagePanelRadioButton(power);
			gamePane.addMessagePanelRadioButton(wind);
		}

	}

}
