package mc;

import java.awt.*;

import javax.swing.*;

class GamePane extends JLayeredPane {

	private static JPanel baseLayer;
	private static JPanel boardPanel;
	private static JPanel playerTokensPanel;
	private static JPanel squaresPanel;
	private static JPanel hudPanel;
	
	private static JPanel messageLayer;
	
	private static JLabel hudLabel1;
	private static JLabel hudLabel2;
	private static JLabel hudLabel3;
	
	private static final GamePane GAMEPANE = new GamePane();
	
	private GamePane() {


		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 1200, 800);
		baseLayer.setBackground(Color.white);
		baseLayer.setOpaque(true);

		
		boardPanel = new JPanel();
		boardPanel.setOpaque(false);
		boardPanel.setPreferredSize(new Dimension(812,767));
		
		
		ImageIcon icon = new ImageIcon("images/board-small.png");
		JLabel board = new JLabel(icon);
		board.setOpaque(true);
			
		playerTokensPanel = new JPanel();
		playerTokensPanel.setOpaque(false);
		
		hudPanel = new JPanel(new GridBagLayout());
		hudPanel.setLayout(null);
		hudPanel.setPreferredSize(new Dimension(388,767));
		hudPanel.setOpaque(false);

		
		JLabel helloHudLabel = new JLabel("Hello HudPanel");
		helloHudLabel.setBounds(5,5,370,200);
		helloHudLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloHudLabel.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		helloHudLabel.setOpaque(true);
		
		messageLayer = new JPanel(new GridBagLayout());
		messageLayer.setBounds(0, 5, 812, 767);
		//messageLayer.setPreferredSize(new Dimension(812, 767));
		messageLayer.setOpaque(false);
		//messageLayer.setBackground(Color.blue);
		
		
		boardPanel.add(board, new Integer(0));
		baseLayer.add(boardPanel, BorderLayout.WEST);
			
		hudPanel.add(helloHudLabel);
		baseLayer.add(hudPanel, BorderLayout.EAST);
			
		add(baseLayer, new Integer(0));
		add(messageLayer, new Integer(1));	
		}
	
	public static GamePane getInstance() {
		return GAMEPANE;
	}
	
	
	public void clearMessageLayer() {
		System.out.println("clearing messageLayer");
		messageLayer.removeAll();
		messageLayer.repaint();
	}
	
	public void setMessageLayer(JPanel jp) {
		clearMessageLayer();
		System.out.println("setting messageLayer");
		messageLayer.add(jp);
		messageLayer.repaint();
	}
	
}