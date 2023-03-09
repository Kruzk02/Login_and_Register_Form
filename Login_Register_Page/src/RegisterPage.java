
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

public class RegisterPage extends JFrame {
	
	private Timer timer;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton submitButton,signButton;
	private JLabel Label,userLabel,passwordLabel;
	
	public RegisterPage() {
		/*
		Setup JFrame
		*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(255,380);
		setLocationRelativeTo(null);
		//setVisible(true);
		/*
		 *Setup JPanel
		 */
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45,45,45));
		setContentPane(panel);
		panel.setLayout(null);
		/*
		 * Setup Label top center
		 */
		Label = new JLabel("Register");
		Label.setBounds(80, 0, 116, 36);
		Label.setFont(new Font("Verdana", Font.BOLD, 20));
		Label.setForeground(Color.WHITE);
		panel.add(Label);
		/*
		 * Setup JLabel Username 
		 */
		userLabel = new JLabel("Username");
		userLabel.setForeground(Color.white);
		userLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		userLabel.setBounds(33, 90, 84, 14);
		panel.add(userLabel);
		/*
		 * Setup User JtextField 
		 */
		userField = new JTextField();
		userField.setFont(new Font("Verdana", Font.PLAIN, 13));
		userField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(10,5, 10, 5))));
		userField.setBounds(30, 105,195,40);
		panel.add(userField);
		/*
		 * Setup JLabel Password
		 */
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setBounds(33, 175, 80, 14);
		panel.add(passwordLabel);
		/*
		 * Setup JPasswordField 
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(30, 190,195,40);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				 new CustomeBorder(),
				 new EmptyBorder(new Insets(10,5, 10, 5))));
		panel.add(passwordField);
		/*
		 * Setup Sign JButton (when press button will turn back to Login Page)
		 */
		signButton = new JButton("Sign in account?");
		signButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		signButton.setBounds(5,290,185,15);
		signButton.setForeground(Color.white);
		signButton.setFocusable(false);
		signButton.setBorderPainted(false);
		signButton.setContentAreaFilled(false);
		signButton.addActionListener(e ->{
			dispose();		
			LoginPage lP = new LoginPage();
			lP.setUndecorated(true);
			lP.setVisible(true);
			lP.setLocationRelativeTo(null);
		});
		panel.add(signButton);
		/*
		 * Setup submit Jbutton (Don't delete aALister , aALister will save info)
		 */
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		submitButton.setBounds(85, 240,85, 40);
		submitButton.setBackground(new Color(115,135,217));
		submitButton.setFocusable(false);
		submitButton.setBorder(BorderFactory.createCompoundBorder(
					new CustomeBorder(), 
					new EmptyBorder(new Insets(10,5, 10, 5))));
		submitButton.addActionListener(e -> saveUser());
		panel.add(submitButton);
		
		 
	}
	/*
	 * this will write txt 
	 */
	 protected void saveUser() {
		
		 String username = userField.getText();
		 String password = new String(passwordField.getPassword());
		 
		 if(username.isEmpty()){ // this thing will alert if your username is empty
			 JOptionPane.showMessageDialog(this,"Please enter a valid Username");
			 return;
		 }
		 if(password.isEmpty()){// this thing will alert if your password is empty
			 JOptionPane.showMessageDialog(this,"Please enter a valid Password");
			 return;
		 }	 
		 try {
			/*
			 * Create Write to write info 
			 */
		    File file = new File("user_info.txt");
			FileWriter fWriter = new FileWriter(file,true);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			
			String userInfo = username +","+ password +"\n";
			bWriter.write(userInfo);
			bWriter.close();
			JOptionPane.showMessageDialog(this,"User successfully create account");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Failed create account");
		}
		}
	 	/*
	 	 * CustomeBorder will make button little curve
	 	 */
		class CustomeBorder extends AbstractBorder{
	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y,
	                int width, int height) {
	        	super.paintBorder(c, g, x, y, width, height);
	        	Graphics2D g2D = (Graphics2D)g;
	        	g2D.setStroke(new BasicStroke(8));
	        	g2D.setColor(new Color(45,45,45));
	        	g2D.drawRoundRect(x, y, width-1, height-1, 11, 11);
}
}
}
