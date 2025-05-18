
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BinToBiteRecipesFrame extends JFrame implements ActionListener {
	
	private JPanel menuPanel = new JPanel();
	private JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon("images/btb logo.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
	private JLabel title = new JLabel("BIN TO BITE");
	private JButton menuHomeButton = new JButton("Home");
	private JButton menuInfoButton = new JButton("Information");
	private JButton menuScannerButton = new JButton("Scanner");
	private JButton menuIngredientListButton = new JButton("Ingredient List");
	
	private JPanel headerPanel = new JPanel();
	private JLabel headerLabel = new JLabel("RECIPE IDEAS");
	
	private JPanel mainPanel = new JPanel();
	private JPanel firstRecipePanel = new JPanel();
	private JLabel firstRecipeHeader = new JLabel();
	private JLabel firstRecipeImage = new JLabel();
	private JLabel firstRecipeDesc = new JLabel();
	private JPanel secondRecipePanel = new JPanel();
	private JLabel secondRecipeHeader = new JLabel();
	private JLabel secondRecipeImage = new JLabel();
	private JLabel secondRecipeDesc = new JLabel();
	private JPanel thirdRecipePanel = new JPanel();
	private JLabel thirdRecipeHeader = new JLabel();
	private JLabel thirdRecipeImage = new JLabel();
	private JLabel thirdRecipeDesc = new JLabel();
	
	private JSONArray recipesArray;
	
	public BinToBiteRecipesFrame() throws JSONException, IOException {
		
		getData();
		menuPanelSetup();
		headerPanelSetup();
		mainPanelSetup();
		frameSetup();
		
	}
	
private void getData() throws JSONException, IOException {
		
		String apiUrl = "https://api.spoonacular.com/recipes/findByIngredients";
		
		String filePath = "ingredients.txt";
        String ingredients = readFileToString(filePath);
		if (ingredients == null) {
			JOptionPane.showMessageDialog(null, "Error reading ingredients from file.");
		}
        String apiKey = "6f1c306270dd4207a287f5cba0cb9172";  

        String urlString = apiUrl + "?ingredients=" + ingredients + "&number=3&apiKey=" + apiKey;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        this.recipesArray = new JSONArray(response.toString());
        
	}

	
	private String readFileToString(String filePath) {
		StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
     
            while ((line = br.readLine()) != null) {
                content.append(line);  
            }
        } catch (IOException error) {
            System.out.println("An error occurred while reading the file: " + error.getMessage());
            return null;  
        }

        return content.toString();  
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
		
		menuHomeButton.setBounds(375,18, 150, 50);
		menuHomeButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuHomeButton.setForeground(Color.decode("#506046"));
		menuHomeButton.setOpaque(false); 
		menuHomeButton.setBorderPainted(false); 
		menuHomeButton.setFocusPainted(false);
		menuHomeButton.addActionListener(this);
		menuPanel.add(menuHomeButton);
		
		menuInfoButton.setBounds(465,18, 240, 50);
		menuInfoButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuInfoButton.setForeground(Color.decode("#506046"));
		menuInfoButton.setOpaque(false); 
		menuInfoButton.setBorderPainted(false); 
		menuInfoButton.setFocusPainted(false);
		menuInfoButton.addActionListener(this);
		menuPanel.add(menuInfoButton);
		
		menuScannerButton.setBounds(645,18, 190, 50);
		menuScannerButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuScannerButton.setForeground(Color.decode("#506046"));
		menuScannerButton.setOpaque(false); 
		menuScannerButton.setBorderPainted(false); 
		menuScannerButton.setFocusPainted(false);
		menuScannerButton.addActionListener(this);
		menuPanel.add(menuScannerButton);
		
		menuIngredientListButton.setBounds(790,18, 230, 50);
		menuIngredientListButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		menuIngredientListButton.setForeground(Color.decode("#506046"));
		menuIngredientListButton.setOpaque(false); 
		menuIngredientListButton.setBorderPainted(false); 
		menuIngredientListButton.setFocusPainted(false);
		menuIngredientListButton.addActionListener(this);
		menuPanel.add(menuIngredientListButton);
		
	}
	
	private void headerPanelSetup() {
		
		headerPanel.setBackground(Color.decode("#a4bd38"));	
		headerPanel.setBounds(0,93,1050,100);
		headerPanel.setLayout(null);
		
		headerLabel.setBounds(300, 18, 900, 62);
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 55));
		headerLabel.setForeground(Color.decode("#e86201"));
		headerPanel.add(headerLabel);
		
	}

	private void mainPanelSetup() throws JSONException, IOException {
		
		mainPanel.setBackground(Color.decode("#f3e2c1"));	
		mainPanel.setBounds(0,193,1050,507);
		mainPanel.setLayout(null);
		
		recipesSetup();
		
	}

	private void recipesSetup() throws JSONException, IOException {
		
		firstRecipePanel.setBackground(Color.decode("#a4bd38"));	
		firstRecipePanel.setBounds(43,40,284,392);
		firstRecipePanel.setLayout(null);
		mainPanel.add(firstRecipePanel);
		
		secondRecipePanel.setBackground(Color.decode("#a4bd38"));	
		secondRecipePanel.setBounds(383,40,284,392);
		secondRecipePanel.setLayout(null);
		mainPanel.add(secondRecipePanel);
		
		thirdRecipePanel.setBackground(Color.decode("#a4bd38"));	
		thirdRecipePanel.setBounds(724,40,284,392);
		thirdRecipePanel.setLayout(null);
		mainPanel.add(thirdRecipePanel);
		
		if (recipesArray.length() > 0) { 
			
			//headers
	        JSONObject firstRecipe = recipesArray.getJSONObject(0);
	        String firstRecipeTitle = firstRecipe.getString("title");
	        firstRecipeHeader.setText("<html>"+firstRecipeTitle+"<html>");
	        
	        JSONObject secondRecipe = recipesArray.getJSONObject(1);
	        String secondRecipeTitle = secondRecipe.getString("title");
	        secondRecipeHeader.setText("<html>"+secondRecipeTitle+"<html>");
	        
	        JSONObject thirdRecipe = recipesArray.getJSONObject(2);
	        String thirdRecipeTitle = thirdRecipe.getString("title");
	        thirdRecipeHeader.setText("<html>"+thirdRecipeTitle+"<html>");
	        
	        //images
	        URL firstRecipeImageURL = new URL(firstRecipe.getString("image"));
	        Image firstRecipeImageRaw = ImageIO.read(firstRecipeImageURL).getScaledInstance(222, 165, Image.SCALE_SMOOTH);
	        ImageIcon firstRecipeImageIcon = new ImageIcon(firstRecipeImageRaw);
	        firstRecipeImage.setIcon(firstRecipeImageIcon);
	        
	        URL secondRecipeImageURL = new URL(secondRecipe.getString("image"));
	        Image secondRecipeImageRaw = ImageIO.read(secondRecipeImageURL).getScaledInstance(222, 165, Image.SCALE_SMOOTH);
	        ImageIcon secondRecipeImageIcon = new ImageIcon(secondRecipeImageRaw);
	        secondRecipeImage.setIcon(secondRecipeImageIcon);

	        URL thirdRecipeImageURL = new URL(thirdRecipe.getString("image"));
	        Image thirdRecipeImageRaw = ImageIO.read(thirdRecipeImageURL).getScaledInstance(222, 165, Image.SCALE_SMOOTH);
	        ImageIcon thirdRecipeImageIcon = new ImageIcon(thirdRecipeImageRaw);
	        thirdRecipeImage.setIcon(thirdRecipeImageIcon);
	        
		}
		
		else 
	        firstRecipeHeader.setText("No recipes found.");
		
		firstRecipeHeader.setBounds(20,15,250,100);
		firstRecipeHeader.setFont(new Font("Calibri", Font.BOLD, 26));
		firstRecipeHeader.setForeground(Color.decode("#e86201"));
		firstRecipePanel.add(firstRecipeHeader);
		
		firstRecipeImage.setBounds(20,130,222, 165);
		firstRecipePanel.add(firstRecipeImage);
		
		secondRecipeHeader.setBounds(20,15,250,100);
		secondRecipeHeader.setFont(new Font("Calibri", Font.BOLD, 26));
		secondRecipeHeader.setForeground(Color.decode("#e86201"));
		secondRecipePanel.add(secondRecipeHeader);
		
		secondRecipeImage.setBounds(20,130,222, 165);
		secondRecipePanel.add(secondRecipeImage);
		
		thirdRecipeHeader.setBounds(20,15,250,100);
		thirdRecipeHeader.setFont(new Font("Calibri", Font.BOLD, 26));
		thirdRecipeHeader.setForeground(Color.decode("#e86201"));
		thirdRecipePanel.add(thirdRecipeHeader);
		
		thirdRecipeImage.setBounds(20,130,222, 165);
		thirdRecipePanel.add(thirdRecipeImage);
	
	}

	private void frameSetup() {
		
		//set the title, size, and layout
		setSize(1050,700);
		setTitle("Recipes Frame");
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
		
		if(event.getSource() == menuInfoButton) {
			
			setVisible(false); 
			new BinToBiteInfoFrame();
			
		}
		
	}

}
