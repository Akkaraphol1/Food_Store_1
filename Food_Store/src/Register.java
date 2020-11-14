import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
public class Register extends JFrame{

	private JTextField[] T = new JTextField[1];
	private JPasswordField PS;
	private JPasswordField PS2;
	private JButton[] B = new JButton[3];
	public Register(){
		getContentPane().setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblRegister.setBounds(325, 56, 220, 81);
		getContentPane().add(lblRegister);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblFirstName.setBounds(183, 195, 179, 50);
		getContentPane().add(lblFirstName);

		T[0] = new JTextField();
		T[0].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		T[0].setBounds(477, 200, 200, 45);
		getContentPane().add(T[0]);
		T[0].setColumns(10);
		
		JLabel PSS = new JLabel("Password");
		PSS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		PSS.setBounds(183, 295, 179, 50);
		getContentPane().add(PSS);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblConfirmPassword.setBounds(83, 395,287, 50);
		getContentPane().add(lblConfirmPassword);

		PS = new JPasswordField();
		PS.setBounds(477, 300, 200, 45);
		PS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		PS.setColumns(10);
		getContentPane().add(PS);

		PS2 = new JPasswordField();
		PS2.setBounds(477, 400, 200, 45);
		PS2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		PS2.setColumns(10);
		getContentPane().add(PS2);
		

		B[0] = new JButton("Login");
		B[0].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		B[0].setBounds(180, 550, 150, 57);
	
		B[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == B[0])
				{
					Login frame = new Login("");
					frame.setVisible(true);
					frame.setSize(800, 690);
			        frame.setVisible(true);
                    frame.setLocation(960, 270);
                    frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}

			
		}
		});
		getContentPane().add(B[0]);		
	
	
	B[1] = new JButton("Register");
	B[1].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	B[1].setBounds(480, 550, 150, 57);

	B[1].addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/food_store? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
						"root", "");
				PreparedStatement ps;
				
				String query = "INSERT INTO `member`(`PS_Member`,`Num_Mem`,`Con_Pass`) VALUES (?,?,?)";
				ps = con.prepareStatement(query);

				String name = T[0].getText();
				ps.setString(2, T[0].getText());
				int m1 = name.length();
				
				String pass = String.valueOf(PS.getPassword());
				ps.setString(1, PS.getText());
				int m3 = pass.length();

				String pass2 = String.valueOf(PS2.getPassword());
				ps.setString(3, PS2.getText());
				int m4 = pass2.length();

				ps.setString(1, pass);
				ps.setString(2, name);
				ps.setString(3, pass2);
			
				// ResultSet rs = ps.executeQuery();
				if (m1 <= 3) // First Name
				{
					JOptionPane.showMessageDialog(null,
							"Please Input (First Name)\n First Name must more than four number.");
					T[0].requestFocus();
					return;
				}
	
				if (m3 <= 3) // Password
				{
					JOptionPane.showMessageDialog(null,
							"Please Input Password \n Password must more than four number.");
					PS.requestFocusInWindow();
					return;
				} else if (m4 <= 3) // Confirm Password
				{
					JOptionPane.showMessageDialog(null,
							"Please Input (Confirm Password)\n Confirmpassword must equal to password.");
					PS2.requestFocusInWindow();
					return;
				}

				if (!pass.equals(pass2)) // Password math
				{
					JOptionPane.showMessageDialog(null, "Please Input (Password Not Match!)");
					PS.requestFocusInWindow();
					return;
				}
			

				int x = ps.executeUpdate();
				if (x > 0) {
					System.out.println("Registration done successfully....");
					Login frame = new Login("Login");
					frame.setVisible(true);
					frame.setSize(800, 690);
			        frame.setVisible(true);
                    frame.setLocation(960, 270);
                    frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					System.out.println("Registration Failed...");
				}
			} catch (Exception ex) {
				Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

			}
	}
	});
	getContentPane().add(B[1]);		
	
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         Register R = new Register();
         R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         R.setVisible(true);
         R.setSize(950,700);
         R.setLocation(300,100);
	}

}
