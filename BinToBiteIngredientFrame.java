import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import javax.swing.*;

import org.json.JSONException;

public class BinToBiteIngredientFrame extends JFrame implements ActionListener {

    private JPanel menuPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel(new ImageIcon(new ImageIcon("images/btb logo.png").getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
    private JLabel title = new JLabel("BIN TO BITE");
    private JButton menuHomeButton = new JButton("Home");
    private JButton menuScannerButton = new JButton("Scanner");
    private JButton menuIngredientListButton = new JButton("Ingredient List");
    private JButton menuRecipesButton = new JButton("Recipes");

    private JButton recipe_generate = new JButton("Generate Recipes");
    private JLabel cartGraphics = new JLabel(new ImageIcon(new ImageIcon("images/ingredients cart.png").getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));

    private JPanel headerPanel = new JPanel();
    private JLabel headerLabel = new JLabel("INGREDIENTS LIST");
    private JLabel list = new JLabel("");

    public BinToBiteIngredientFrame() {
        menuPanelSetup();
        headerPanelSetup();
        mainPanelSetup();
        frameSetup();
    }

    private void menuPanelSetup() {
        menuPanel.setBackground(Color.decode("#f3e2c1"));
        menuPanel.setBounds(0, 0, 1050, 93);
        menuPanel.setLayout(null);

        logoLabel.setBounds(23, 20, 55, 55);
        menuPanel.add(logoLabel);

        title.setBounds(92, 28, 195, 39);
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        title.setForeground(Color.decode("#506046"));
        menuPanel.add(title);

        menuHomeButton.setBounds(395, 18, 190, 50);
        menuHomeButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        menuHomeButton.setForeground(Color.decode("#506046"));
        menuHomeButton.setOpaque(false);
        menuHomeButton.setBorderPainted(false);
        menuHomeButton.setFocusPainted(false);
        menuHomeButton.addActionListener(this);
        menuPanel.add(menuHomeButton);

        menuScannerButton.setBounds(535, 18, 160, 50);
        menuScannerButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        menuScannerButton.setForeground(Color.decode("#506046"));
        menuScannerButton.setOpaque(false);
        menuScannerButton.setBorderPainted(false);
        menuScannerButton.setFocusPainted(false);
        menuScannerButton.addActionListener(this);
        menuPanel.add(menuScannerButton);

        menuIngredientListButton.setBounds(660, 18, 240, 50);
        menuIngredientListButton.setFont(new Font("Calibri", Font.PLAIN, 25));
        menuIngredientListButton.setForeground(Color.decode("#506046"));
        menuIngredientListButton.setOpaque(false);
        menuIngredientListButton.setBorderPainted(false);
        menuIngredientListButton.setFocusPainted(false);
        menuIngredientListButton.addActionListener(this);
        menuPanel.add(menuIngredientListButton);

        menuRecipesButton.setBounds(870, 18, 160, 50);
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
        headerPanel.setBounds(0, 93, 1050, 100);
        headerPanel.setLayout(null);

        headerLabel.setBounds(230, 18, 900, 62);
        headerLabel.setFont(new Font("Calibri", Font.BOLD, 55));
        headerLabel.setForeground(Color.decode("#e86201"));
        headerPanel.add(headerLabel);
    }
    
    private void mainPanelSetup() {
    	
        mainPanel.setBackground(Color.decode("#f3e2c1"));
        mainPanel.setBounds(0, 193, 1050, 507);
        mainPanel.setLayout(null);
        
        // Read file content and store in StringBuilder
            StringBuilder fileContent = new StringBuilder();
    
    try {
        File file = new File("ingredients.txt");
        Scanner scanner = new Scanner(file);
        
        // Start the unordered list
        fileContent.append("<ul style='list-style-type: disc; padding-left: 20px;'>");
        
        // Add each ingredient as a list item
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            // Split the line by commas
            String[] ingredients = line.split(",");
            
            // Loop through the ingredients and add each to the list as an <li> element
            for (String ingredient : ingredients) {
                ingredient = ingredient.trim();  // Trim any extra spaces around the ingredient
                fileContent.append("<li>").append(ingredient).append("</li>");
            }
        }
        
        // End the unordered list
        fileContent.append("</ul>");
        
        scanner.close();
    } catch (FileNotFoundException e) {
        fileContent.append("Error: File not found!");
    }

        // Set file content in JLabel (Formatted as HTML)
        list.setBounds(12, 25, 500, 500);
        list.setHorizontalAlignment(SwingConstants.LEFT);
        list.setVerticalAlignment(SwingConstants.TOP);
        list.setFont(new Font("Calibri", Font.PLAIN, 25));
        list.setForeground(Color.decode("#506046"));
        list.setText("<html>" + fileContent.toString() + "</html>"); // Wrap with <html> tags for formatting
        mainPanel.add(list);

        cartGraphics.setBounds(525,0,500, 500);
		mainPanel.add(cartGraphics);

        recipe_generate.setBounds(695, 200, 300, 60);
        recipe_generate.setBackground(Color.decode("#506046"));
        recipe_generate.setFont(new Font("Calibri", Font.PLAIN, 25));
        recipe_generate.setForeground(Color.decode("#f3e2c1"));
        recipe_generate.setBorderPainted(false);
        recipe_generate.setFocusPainted(false);
        recipe_generate.addActionListener(this);
        mainPanel.add(recipe_generate);
    }


    private void frameSetup() {
        setSize(1050, 700);
        setTitle("Ingredient Frame");
        getContentPane().setBackground(Color.decode("#f3e2c1"));
        setLayout(null);

        add(menuPanel);
        add(headerPanel);
        add(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuHomeButton) {
            setVisible(false);
            new BinToBiteTitle();
        }

        if (event.getSource() == menuScannerButton) {            
			try {

				File htmlFile = new File("BinToBiteScannerFrame.html");
				Desktop.getDesktop().browse(htmlFile.toURI());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        }

        if (event.getSource() == menuIngredientListButton) {
            setVisible(false);
			new BinToBiteIngredientFrame(); 
	
		}

        if (event.getSource() == menuRecipesButton) {
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

        if (event.getSource() == recipe_generate) {
            try {
                FileWriter writer = new FileWriter("ingredients.txt");
                writer.write(list.getText().replaceAll("<html>|</html>", ""));
                writer.close();
                JOptionPane.showMessageDialog(this, "Ingredients saved to ingredients.txt!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage());
            }
        }
    }

}