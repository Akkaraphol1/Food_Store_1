
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.StatementImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login_Staff extends JFrame {

	private JPanel contentPane;
	private JTextField FN;
	private JPasswordField PS;
	protected Statement MyConnection;
	private JButton B = new JButton();
	/*
	JLabel imageLabel,imageLabel2;
	public JLabel img;

	
	private Image image = null;
	private Image image2 = null;
	*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Staff frame = new Login_Staff("Staff");
					frame.setVisible(true);
					frame.setSize(800, 690);
			        frame.setVisible(true);
                    frame.setLocation(960, 270);
                    frame.setResizable(false);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void paint(Graphics g){
	     super.paint(g);

	     g.setColor(Color.blue);
	     g.drawRect(30, 115, 745, 500); 
	     
	}
	

	/**
	 * Create the frame.
	 */
	public Login_Staff(String file) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblUsername = new JLabel("Name :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblUsername.setBounds(126, 240, 237, 79);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lblPassword.setBounds(126, 350, 237, 56);
		contentPane.add(lblPassword);

		FN = new JTextField();
		FN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		FN.setBounds(394, 250, 218, 67);
		contentPane.add(FN);
		FN.setColumns(10);

		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				  Staff S = new Staff("Staff");
			     
				String query = "SELECT * FROM `staff` WHERE `N_Staff` =? and `PS_Staff` =? ";
				try {

					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/food_store? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "");
					PreparedStatement ps;

					String name = FN.getText();
					int m1 = name.length();
					String pass = String.valueOf(PS.getPassword());

					if (m1 <= 3) // Name
					{
						JOptionPane.showMessageDialog(null,
								"Sorry, Name incorrect. \n Please to try again!!! ");
						FN.requestFocusInWindow();
						return;
					}

					if (pass.equals("")) // Password
					{
						JOptionPane.showMessageDialog(null,
								"Sorry, Password Staff incorrect\n Please to try again!!! ");
						PS.requestFocusInWindow();
						return;
					}

					ps = con.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, pass);
					ResultSet rs = ps.executeQuery();
					int i = 0;

					while (rs.next()) {

						i++;
					}
					if (i == 1) {
						System.out.println("Welcome to Page Staff"); 
					        S.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					        S.setVisible(true);
					        S.setSize(1900,1000);
					 	   S.setLocation(10, 10);
					}

					else {
						System.out.println("Sorry , Please check username and password");
					}

				} catch (Exception ex) {
					Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
				}

      			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnLogin.setBounds(320, 480, 150, 70);
		contentPane.add(btnLogin);


		
		PS = new JPasswordField();
		PS.setBounds(394, 350, 218, 67);
		contentPane.add(PS);
	}

}

