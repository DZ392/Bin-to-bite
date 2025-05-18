
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONException;

public class BinToBiteTitle extends JFrame implements ActionListener {
	
	private JPanel menuPanel = new JPanel();
	private JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon("images/btb logo.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
	private JLabel title = new JLabel("BIN TO BITE");
	private JButton menuInfoButton = new JButton("Information");
	private JButton menuScannerButton = new JButton("Scanner");
	private JButton menuIngredientListButton = new JButton("Ingredient List");
	private JButton menuRecipesButton = new JButton("Recipes");
	
	private JPanel mainPanel = new JPanel();
	private JScrollPane scrollPane;
	
	private JPanel sloganPanel = new JPanel();
	private JLabel sloganLabel = new JLabel("<html>Join our cause and ensure <br>that no crumb is left <br>behind!<html>");
	private JLabel veggiesGraphics = new JLabel(new ImageIcon(new ImageIcon("images/fruits and veggies.png").getImage().getScaledInstance(370, 200, Image.SCALE_SMOOTH)));
	
	private JPanel aboutPanel = new JPanel();
	private JLabel aboutHeaderLabel = new JLabel("ABOUT US");
	private JLabel aboutLabel = new JLabel("<html>Every year, 2.5 billion tons of food <br>"
			+ "go to waste. That’s why we created <br>"
			+ "Bin to Bite! Our mission is simple: <br>"
			+ "reduce food waste by helping you <br>"
			+ "make the most of the ingredients <br>"
			+ "already in your cabinets.<html>");
	private JLabel sandwichGraphics = new JLabel(new ImageIcon(new ImageIcon("images/sandwich.png").getImage().getScaledInstance(490, 363, Image.SCALE_SMOOTH)));
	
	private JPanel infoPanel = new JPanel();
	private JLabel infoHeaderLabel = new JLabel("INFORMATION");
	private JLabel infoLabel = new JLabel("<html>Learn how Bin to Bite works, "
			+ "discover <br>how to navigate through different <br>"
			+ "pages, and find useful tips!<html>");	
	private JLabel soupGraphics = new JLabel(new ImageIcon(new ImageIcon("images/soup.png").getImage().getScaledInstance(442, 352, Image.SCALE_SMOOTH)));
	private JButton infoButton = new JButton("Explore more");
	
	private JPanel scanPanel = new JPanel();
	private JLabel scanHeaderLabel = new JLabel("FOOD SCANNER");
	private JLabel scanLabel = new JLabel("<html>Easily add to your ingredients list by <br>"
			+ "snapping a photo of your food. Our <br>"
			+ "scanner uses image recognition!<html>");	
	private JLabel tomatoGraphics = new JLabel(new ImageIcon(new ImageIcon("images/tomato.png").getImage().getScaledInstance(462, 348, Image.SCALE_SMOOTH)));
	private JButton scanButton = new JButton("Scan now");
	
	private JPanel ingredientPanel = new JPanel();
	private JLabel ingredientHeaderLabel = new JLabel("INGREDIENT LIST");
	private JLabel ingredientLabel = new JLabel("<html>Take a look at the ingredients you've <br>"
			+ "scanned into the system!<html>");	
	private JLabel spicesGraphics = new JLabel(new ImageIcon(new ImageIcon("images/spices.png").getImage().getScaledInstance(437, 310, Image.SCALE_SMOOTH)));
	private JButton ingredientButton = new JButton("View list");
	
	private JPanel recipesPanel = new JPanel();
	private JLabel recipesHeaderLabel = new JLabel("RECIPE IDEAS");
	private JLabel recipesLabel = new JLabel("<html>Explore delicious recipes you can <br>"
			+ "make with your scanned ingredients!!<html>");	
	private JLabel storageGraphics = new JLabel(new ImageIcon(new ImageIcon("images/storage.png").getImage().getScaledInstance(460, 317, Image.SCALE_SMOOTH)));
	private JButton recipesButton = new JButton("Show recipes");
	
	private JPanel creditsPanel = new JPanel();
	private JLabel creditsLabel = new JLabel("Website created by: Lakshna, Samreen, Jennifer, & Daisy");

	public BinToBiteTitle() {
		
		menuPanelSetup();
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
		
		menuInfoButton.setBounds(370,18, 190, 50);
		menuInfoButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuInfoButton.setForeground(Color.decode("#506046"));
		menuInfoButton.setOpaque(false); 
		menuInfoButton.setBorderPainted(false); 
		menuInfoButton.setFocusPainted(false);
		menuInfoButton.addActionListener(this);
		menuPanel.add(menuInfoButton);
		
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

	private void mainPanelSetup() {
		
		mainPanel.setBackground(Color.decode("#a4bd38"));	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 93, 1050, 607);
		
		sloganPanelSetup();
		aboutPanelSetup();
		infoPanelSetup();
		scanPanelSetup();
		ingredientPanelSetup();
		recipesPanelSetup();
		creditsPanelSetup();
		
	}

	private void sloganPanelSetup() {
		
		sloganPanel.setBackground(Color.decode("#a4bd38"));	
		sloganPanel.setPreferredSize(new Dimension (1050,244));
		sloganPanel.setLayout(null);
		
		sloganLabel.setBounds(38, 32, 700, 181);
		sloganLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		sloganLabel.setForeground(Color.decode("#e86201"));
		sloganPanel.add(sloganLabel);
		
		veggiesGraphics.setBounds(645,29,370,200);
		sloganPanel.add(veggiesGraphics);
						
		mainPanel.add(sloganPanel);
		
	}
	
	private void aboutPanelSetup() {
		
		aboutPanel.setBackground(Color.decode("#f3e2c1"));	
		aboutPanel.setPreferredSize(new Dimension(1050,363));
		aboutPanel.setLayout(null);
		
		sandwichGraphics.setBounds(0,0,490, 363);
		aboutPanel.add(sandwichGraphics);
		
		aboutHeaderLabel.setBounds(530, 32, 275, 62);
		aboutHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		aboutHeaderLabel.setForeground(Color.decode("#e86201"));
		aboutPanel.add(aboutHeaderLabel);
		
		aboutLabel.setBounds(530, 87, 486, 231);
		aboutLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		aboutLabel.setForeground(Color.decode("#506046"));
		aboutPanel.add(aboutLabel);
		
		mainPanel.add(aboutPanel);
		
	}
	
	private void infoPanelSetup() {
		
		infoPanel.setBackground(Color.decode("#a4bd38"));	
		infoPanel.setPreferredSize(new Dimension(1050,352));
		infoPanel.setLayout(null);
		
		soupGraphics.setBounds(608,0,442, 352);
		infoPanel.add(soupGraphics);
		
		infoHeaderLabel.setBounds(39, 35, 425, 62);
		infoHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		infoHeaderLabel.setForeground(Color.decode("#e86201"));
		infoPanel.add(infoHeaderLabel);
		
		infoLabel.setBounds(39, 101, 600, 113);
		infoLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		infoLabel.setForeground(Color.decode("#506046"));
		infoPanel.add(infoLabel);
		
		infoButton.setBounds(39,231, 260, 60);
		infoButton.setFont(new Font("Calibri", Font.BOLD, 28));
		infoButton.setBackground(Color.decode("#e86201"));
		infoButton.setForeground(Color.decode("#506046"));
		infoButton.setOpaque(true); 
		infoButton.setBorderPainted(false); 
		infoButton.setFocusPainted(false);
		infoButton.addActionListener(this);
		infoPanel.add(infoButton);
		
		mainPanel.add(infoPanel);
		
	}
	
	private void scanPanelSetup() {
		
		scanPanel.setBackground(Color.decode("#f3e2c1"));	
		scanPanel.setPreferredSize(new Dimension(1050,348));
		scanPanel.setLayout(null);
		
		tomatoGraphics.setBounds(0,0,462, 348);
		scanPanel.add(tomatoGraphics);
		
		scanHeaderLabel.setBounds(505, 35, 425, 62);
		scanHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		scanHeaderLabel.setForeground(Color.decode("#e86201"));
		scanPanel.add(scanHeaderLabel);
		
		scanLabel.setBounds(505, 101, 600, 113);
		scanLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		scanLabel.setForeground(Color.decode("#506046"));
		scanPanel.add(scanLabel);
		
		scanButton.setBounds(505,231, 240, 60);
		scanButton.setFont(new Font("Calibri", Font.BOLD, 28));
		scanButton.setBackground(Color.decode("#e86201"));
		scanButton.setForeground(Color.decode("#506046"));
		scanButton.setOpaque(true); 
		scanButton.setBorderPainted(false); 
		scanButton.setFocusPainted(false);
		scanButton.addActionListener(this);
		scanPanel.add(scanButton);
		
		mainPanel.add(scanPanel);
		
	}

	private void ingredientPanelSetup() {
		
		ingredientPanel.setBackground(Color.decode("#a4bd38"));	
		ingredientPanel.setPreferredSize(new Dimension(1050,310));
		ingredientPanel.setLayout(null);
		
		spicesGraphics.setBounds(605,0,437, 310);
		ingredientPanel.add(spicesGraphics);
		
		ingredientHeaderLabel.setBounds(39, 35, 460, 62);
		ingredientHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		ingredientHeaderLabel.setForeground(Color.decode("#e86201"));
		ingredientPanel.add(ingredientHeaderLabel);
		
		ingredientLabel.setBounds(39, 83, 600, 113);
		ingredientLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		ingredientLabel.setForeground(Color.decode("#506046"));
		ingredientPanel.add(ingredientLabel);
		
		ingredientButton.setBounds(39,190, 240, 60);
		ingredientButton.setFont(new Font("Calibri", Font.BOLD, 28));
		ingredientButton.setBackground(Color.decode("#e86201"));
		ingredientButton.setForeground(Color.decode("#506046"));
		ingredientButton.setOpaque(true); 
		ingredientButton.setBorderPainted(false); 
		ingredientButton.setFocusPainted(false);
		ingredientButton.addActionListener(this);
		ingredientPanel.add(ingredientButton);
		
		mainPanel.add(ingredientPanel);
		
	}

	private void recipesPanelSetup() {
		
		recipesPanel.setBackground(Color.decode("#f3e2c1"));	
		recipesPanel.setPreferredSize(new Dimension(1050,317));
		recipesPanel.setLayout(null);
		
		storageGraphics.setBounds(0,0,460, 317);
		recipesPanel.add(storageGraphics);
		
		recipesHeaderLabel.setBounds(505, 40, 425, 62);
		recipesHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		recipesHeaderLabel.setForeground(Color.decode("#e86201"));
		recipesPanel.add(recipesHeaderLabel);
		
		recipesLabel.setBounds(505, 90, 600, 113);
		recipesLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
		recipesLabel.setForeground(Color.decode("#506046"));
		recipesPanel.add(recipesLabel);
		
		recipesButton.setBounds(505,200, 240, 60);
		recipesButton.setFont(new Font("Calibri", Font.BOLD, 28));
		recipesButton.setBackground(Color.decode("#e86201"));
		recipesButton.setForeground(Color.decode("#506046"));
		recipesButton.setOpaque(true); 
		recipesButton.setBorderPainted(false); 
		recipesButton.setFocusPainted(false);
		recipesButton.addActionListener(this);
		recipesPanel.add(recipesButton);
		
		mainPanel.add(recipesPanel);
		
	}

	private void creditsPanelSetup() {
		
		creditsPanel.setBackground(Color.decode("#a4bd38"));	
		creditsPanel.setPreferredSize(new Dimension(1050,85));
		creditsPanel.setLayout(null);	
		
		creditsLabel.setBounds(25,10, 700, 40);
		creditsLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		creditsLabel.setForeground(Color.decode("#506046"));
		creditsPanel.add(creditsLabel);
		
		mainPanel.add(creditsPanel);
		
	}

	private void frameSetup() {
		
		//set the title, size, and layout
		setSize(1050,700);
		setTitle("Title Frame");
		getContentPane().setBackground(Color.decode("#f3e2c1"));
		setLayout(null);
		
		//add the panels
		add(menuPanel);
		add(scrollPane);
		
		//allow to be visible
		setDefaultCloseOperation(EXIT_ON_CLOSE); //stops window from keep appearing
		setResizable(false);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == infoButton || event.getSource() == menuInfoButton) {
			
			setVisible(false); 
			new BinToBiteInfoFrame();
			
		}
		
		if(event.getSource() == scanButton || event.getSource() == menuScannerButton) {
						
			try {

				File htmlFile = new File("BinToBiteScannerFrame.html");
				Desktop.getDesktop().browse(htmlFile.toURI());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		if(event.getSource() == ingredientButton || event.getSource() == menuIngredientListButton) {
			
			setVisible(false); 
			new BinToBiteIngredientFrame();
			
		}
		
		if(event.getSource() == recipesButton || event.getSource() == menuRecipesButton) {
			
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
