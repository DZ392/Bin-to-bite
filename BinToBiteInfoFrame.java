
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONException;

public class BinToBiteInfoFrame extends JFrame implements ActionListener {
	
	private JPanel menuPanel = new JPanel();
	private JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon("images/btb logo.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
	private JLabel title = new JLabel("BIN TO BITE");
	private JButton menuHomeButton = new JButton("Home");
	private JButton menuScannerButton = new JButton("Scanner");
	private JButton menuIngredientListButton = new JButton("Ingredient List");
	private JButton menuRecipesButton = new JButton("Recipes");
	
	private JPanel headerPanel = new JPanel();
	private JLabel headerLabel = new JLabel("WEBSITE INFORMATION");
	
	private JPanel mainPanel = new JPanel();
	private JLabel introLabel = new JLabel("<html>Welcome to Bin to Bite: your guide to smarter food use and less waste.\n"
			+ "<br>Turn leftover ingredients into delicious meals, right from your pantry!<html>");
	private JLabel firstStepHeader = new JLabel("1. SCAN INGREDIENTS");
	private JLabel firstStepInfo = new JLabel("<html>Snap a picture of your ingredients <br>"
			+ "and let our smart scanner detect <br>what's in your kitchen!<html>");
	private JLabel secondStepHeader = new JLabel("2. ADD MANUALLY");
	private JLabel secondStepInfo = new JLabel("<html>Prefer to type? Use the Ingredient <br>"
			+ "List to add items manually and <br>"
			+ "keep track of what you have.<html>");
	private JLabel thirdStepHeader = new JLabel("3. GET RECIPES");
	private JLabel thirdStepInfo = new JLabel("<html>With your ingredients saved, explore personalized recipes that help <br>"
			+ "reduce food waste and inspire your next meal.<html>");

	public BinToBiteInfoFrame() {
		
		menuPanelSetup();
		headerPanelSetup();
		mainPanelSetup();
		frameSetup();
		
	}
	
	private void menuPanelSetup() {
		
		menuPanel.setBackground(Color.decode("#f3e2c1"));	
		menuPanel.setBounds(0,0,1050,93);
		menuPanel.setLayout(null);
		
		logoLabel.setBounds(23,20, 55, 55);
		menuPanel.add(logoLabel);
		
		title.setBounds(92,28,195,39);
		title.setFont(new Font("Calibri", Font.BOLD, 30));
		title.setForeground(Color.decode("#506046"));
		menuPanel.add(title);
		
		menuHomeButton.setBounds(395,18, 190, 50);
		menuHomeButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuHomeButton.setForeground(Color.decode("#506046"));
		menuHomeButton.setOpaque(false); 
		menuHomeButton.setBorderPainted(false); 
		menuHomeButton.setFocusPainted(false);
		menuHomeButton.addActionListener(this);
		menuPanel.add(menuHomeButton);
		
		menuScannerButton.setBounds(535,18, 160, 50);
		menuScannerButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuScannerButton.setForeground(Color.decode("#506046"));
		menuScannerButton.setOpaque(false); 
		menuScannerButton.setBorderPainted(false); 
		menuScannerButton.setFocusPainted(false);
		menuScannerButton.addActionListener(this);
		menuPanel.add(menuScannerButton);
		
		menuIngredientListButton.setBounds(660,18, 240, 50);
		menuIngredientListButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuIngredientListButton.setForeground(Color.decode("#506046"));
		menuIngredientListButton.setOpaque(false); 
		menuIngredientListButton.setBorderPainted(false); 
		menuIngredientListButton.setFocusPainted(false);
		menuIngredientListButton.addActionListener(this);
		menuPanel.add(menuIngredientListButton);
		
		menuRecipesButton.setBounds(870,18, 160, 50);
		menuRecipesButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuRecipesButton.setForeground(Color.decode("#506046"));
		menuRecipesButton.setOpaque(false); 
		menuRecipesButton.setBorderPainted(false); 
		menuRecipesButton.setFocusPainted(false);
		menuRecipesButton.addActionListener(this);
		menuPanel.add(menuRecipesButton);
		
	}
	
	private void headerPanelSetup() {
		
		headerPanel.setBackground(Color.decode("#a4bd38"));	
		headerPanel.setBounds(0,93,1050,100);
		headerPanel.setLayout(null);
		
		headerLabel.setBounds(195, 18, 900, 62);
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 55));
		headerLabel.setForeground(Color.decode("#e86201"));
		headerPanel.add(headerLabel);
		
	}

	private void mainPanelSetup() {
		
		mainPanel.setBackground(Color.decode("#f3e2c1"));	
		mainPanel.setBounds(0,193,1050,507);
		mainPanel.setLayout(null);
		
		introLabel.setBounds(36, 25, 1000, 75);
		introLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		introLabel.setForeground(Color.decode("#506046"));
		mainPanel.add(introLabel);
		
		firstStepHeader.setBounds(36, 115, 500, 50);
		firstStepHeader.setFont(new Font("Calibri", Font.BOLD, 35));
		firstStepHeader.setForeground(Color.decode("#e86201"));
		mainPanel.add(firstStepHeader);
		
		firstStepInfo.setBounds(36, 100, 486, 231);
		firstStepInfo.setFont(new Font("Calibri", Font.PLAIN, 26));
		firstStepInfo.setForeground(Color.decode("#506046"));
		mainPanel.add(firstStepInfo);
		
		secondStepHeader.setBounds(530, 115, 500, 50);
		secondStepHeader.setFont(new Font("Calibri", Font.BOLD, 35));
		secondStepHeader.setForeground(Color.decode("#e86201"));
		mainPanel.add(secondStepHeader);
		
		secondStepInfo.setBounds(530, 100, 486, 231);
		secondStepInfo.setFont(new Font("Calibri", Font.PLAIN, 26));
		secondStepInfo.setForeground(Color.decode("#506046"));
		mainPanel.add(secondStepInfo);
		
		thirdStepHeader.setBounds(36, 300, 500, 50);
		thirdStepHeader.setFont(new Font("Calibri", Font.BOLD, 35));
		thirdStepHeader.setForeground(Color.decode("#e86201"));
		mainPanel.add(thirdStepHeader);
		
		thirdStepInfo.setBounds(36, 270, 970, 231);
		thirdStepInfo.setFont(new Font("Calibri", Font.PLAIN, 26));
		thirdStepInfo.setForeground(Color.decode("#506046"));
		mainPanel.add(thirdStepInfo);
		
	}

	private void frameSetup() {
		
		//set the title, size, and layout
		setSize(1050,700);
		setTitle("Information Frame");
		getContentPane().setBackground(Color.decode("#f3e2c1"));
		setLayout(null);
		
		//add the panels
		add(menuPanel);
		add(headerPanel);
		add(mainPanel);
		
		//allow to be visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); //stops window from keep appearing
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == menuHomeButton) {
			
			setVisible(false); 
			new BinToBiteTitle();
			
		}
		
		if(event.getSource() == menuScannerButton) {
			
			try {

				File htmlFile = new File("BinToBiteScannerFrame.html");
				Desktop.getDesktop().browse(htmlFile.toURI());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		if(event.getSource() == menuIngredientListButton) {
			
			setVisible(false); 
			new BinToBiteIngredientFrame();
			
		}
		
		if(event.getSource() == menuRecipesButton) {
			
			setVisible(false); 
			try {
				new BinToBiteRecipesFrame();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
