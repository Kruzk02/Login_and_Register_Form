import java.awt.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



public class LoginPage extends JFrame implements ActionListener{

	
	private JPanel Panel;
	private JLabel welcomeLabel,UserLabel,passwordLabel;
	private JTextField userField;
	private JPasswordField passwordField;
	private JButton LoginButton,RegisterButton;
	private JCheckBox rememberButton ;// don't know how rememberButton work so .....
	
	public LoginPage(){
		/*
		 * Setup JFrame
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(255,380);
		setLocationRelativeTo(null);
		/*
		 * Setup JPanel
		 */
		Panel = new JPanel();
		Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		Panel.setBackground(new Color(45,45,45));
		setContentPane(Panel);
		Panel.setLayout(null);
		/*
		 * Setup JLabel top center
		 */
		welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setBounds(80, 0, 116, 36);
		welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		welcomeLabel.setForeground(Color.WHITE);
		Panel.add(welcomeLabel);
		/*
		 * Setup Username Label
		 */
		UserLabel = new JLabel("Username");
		UserLabel.setForeground(Color.WHITE);
		UserLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		UserLabel.setBounds(33, 90, 84, 14);
		Panel.add(UserLabel);
		/*
		 * Setup username JTextField(CustomeBorder will make button little curve)
		 */
		userField = new JTextField();
		userField.setFont(new Font("Verdana", Font.PLAIN, 13));
		userField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(10,5, 10, 5))));
		userField.setBounds(30, 105,195,40);
		Panel.add(userField);
		/*
		 * Setup Password JLabel
		 */
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setBounds(33, 175, 80, 14);
		Panel.add(passwordLabel);
		/*
		 * Setup PasswordField JPasswordField
		 */
		passwordField = new JPasswordField();
		passwordField.setBounds(30, 190,195,40);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				 new CustomeBorder(),
				 new EmptyBorder(new Insets(10,5, 10, 5))));
		Panel.add(passwordField);
		/*
		 * Setup LoginButton
		 */
		LoginButton = new JButton("Login");
		LoginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		LoginButton.setBounds(30, 250,195, 40);
		LoginButton.setBackground(new Color(115,135,217));
		LoginButton.setFocusable(false);
		LoginButton.setBorder(BorderFactory.createCompoundBorder(
					new CustomeBorder(), 
					new EmptyBorder(new Insets(10,5, 10, 5))));
		LoginButton.addActionListener(this);
		Panel.add(LoginButton);
		/*
		 * Setup Button that will bring you to the RegisterPage
		 */
		RegisterButton = new JButton("Create account");
		RegisterButton.setBounds(15,290,125,15);
		RegisterButton.setBorderPainted(false);
		RegisterButton.setFocusPainted(false);
		RegisterButton.setContentAreaFilled(false);
		RegisterButton.setForeground(Color.white);
		RegisterButton.addActionListener(this);
		Panel.add(RegisterButton);
    	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==RegisterButton) {
			dispose();
			RegisterPage rPage = new RegisterPage();
			rPage.setVisible(true);
		}
		if(e.getSource()==LoginButton) {
			loginInfo();
		}
	}
	/*
	 * this will Read txt check if your acc register or not
	 */
	private void loginInfo() {
		String username = userField.getText();
		String password = new String(passwordField.getPassword());
		
		try {
			File file = new File("user_info.txt");
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			
			String line;
			boolean foundUser = false;
			while((line = bReader.readLine()) != null){
				String[] userInfo = line.split(",");
				if(userInfo[0].equals(username) && userInfo[1].equals(password)) {
					foundUser = true;
					break;
				}}
			
			bReader.close();
			if(foundUser) {
				JOptionPane.showMessageDialog(this, "Login successful");	
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid username or password");
			}
		}catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error reading user information.");
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
