import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Members extends JFrame {

	private static JTextField[] T = new JTextField[10];
	private JLabel[] L = new JLabel[10];
	private JButton[] B = new JButton[7];
    private DefaultTableModel listModel = new DefaultTableModel();
    private static JTable table = new JTable();
    private 	JScrollPane pane = new JScrollPane();
  
    private JTextField Time;
    private JPasswordField PS;
	private JPasswordField PS2;

	private String [] V = new String[10];
    
	public Members(String Member)
	{
		getContentPane().setLayout(null);

		//FN
		L[0] = new JLabel("Name");
		L[0].setBounds(300,200,200,50);
		L[0].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[0]);
		
		T[0] = new JTextField();
		T[0].setBounds(410,200,120,50);
		T[0].setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(T[0]);
	  
		//password
		L[2] = new JLabel("password");
		L[2].setBounds(550,200,200,50);
		L[2].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[2]);
				
		PS = new JPasswordField();
		PS.setBounds(660,200,120,50);
		PS.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(PS);
		
		//Confirm Password
		L[3] = new JLabel("confirm password");
		L[3].setBounds(800,200,200,50);
		L[3].setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(L[3]);
						
		PS2 = new JPasswordField();
		PS2.setBounds(1000,200,120,50);
		PS2.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(PS2);
		
		// Go
				B[2] = new JButton("Go");
				B[2].setBounds(1200,800,100,50);
				B[2].setFont(new Font("Tahoma", Font.BOLD, 20));
				B[2].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 Main_Page MP = new Main_Page();
						   MP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						   MP.setVisible(true);
						   MP.setSize(1900,1000);
						   MP.setLocation(10,10);
					}
				});

				getContentPane().add(B[2]);
				
		
		// ADD
		B[0] = new JButton("Add");
		B[0].setBounds(900,800,100,50);
		B[0].setFont(new Font("Tahoma", Font.BOLD, 20));
		B[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/food_store? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
							"root", "");
					PreparedStatement ps; // = con.prepareStatement("insert into
											// user(user_id,user_Last,user_password,user_password2)
											// values(?,?,?,?);");
					String query = "INSERT INTO `member`(`PS_Member`,`Num_Mem`, `Con_Pass`) VALUES (?,?,?)";
					ps = con.prepareStatement(query);



					String name = T[0].getText();
					ps.setString(2, T[0].getText());
					int m2 = name.length();
					
					String pass = String.valueOf(PS.getPassword());
					ps.setString(1, PS.getText());
					int m4 = pass.length();
					
					String pass2 = String.valueOf(PS2.getPassword());
					ps.setString(3, PS2.getText());
					int m5 = pass2.length();
	          
					ps.setString(1, pass);
					ps.setString(2, name);
					ps.setString(3, pass2);
					
					
					if(m4 <= 4)
					{
						JOptionPane.showMessageDialog(null,
								"Please Input Password \n Password must more than four number.");
						PS.requestFocus();
						return;
						
					}
					// ResultSet rs = ps.executeQuery();
					if (m2 <= 3) // First Name
					{
						JOptionPane.showMessageDialog(null,
								"Please Input (First Name)\n First Name must more than four number.");
						T[0].requestFocus();
						return;
					}


					 else if (m5 <= 3) // Confirm Password
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
						System.out.println("Registration Successfully...");
					} else {
						System.out.println("Registration Failed...");
					}
				} catch (Exception ex) {
					Logger.getLogger(Members.class.getName()).log(Level.SEVERE, null, ex);

				}
				

			}
		});


		getContentPane().add(B[0]);
		
		B[3] = new JButton("Refresh");
		B[3].setBounds(1500,800,100,50);
		B[3].setFont(new Font("Tahoma", Font.BOLD, 15));
		B[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Members M = new Members("Member");
			        M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        M.setVisible(true);
			        M.setSize(1900,1000);
			 	   M.setLocation(10, 10);
			}
		});

		getContentPane().add(B[3]);

		table = new JTable();
		  table.setBackground(Color.WHITE);
	        table.setForeground(Color.black);
	        Font font = new Font("Tohama",Font.BOLD,15);
	        table.setFont(font);
	        table.setRowHeight(40);
	        
	        table.addMouseListener(new MouseAdapter() {
				   @Override
				   public void mouseClicked(MouseEvent arg0) {
				    int row = table.getSelectedRow();
				    if (row != -1) {
				     T[0].setText(table.getValueAt(row, 2).toString());
				     PS.setText(table.getValueAt(row, 1).toString());
				     PS2.setText(table.getValueAt(row, 3).toString());

				    }
				   }
				  });
	        
	        
		
		JScrollPane pane = new JScrollPane(table);
        pane.setBounds(250, 300, 1400, 450);
        
        getContentPane().add(pane);
		
		pane.setViewportView(table);
		
		
		//Delete
		B[1] = new JButton("Delete");
		B[1].setBounds(600,800,100,50);
		B[1].setFont(new Font("Tahoma", Font.BOLD, 20));
		B[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Yes", "No" };
				int n = JOptionPane
						.showOptionDialog(null, "Do you want to Delete data?",
								"Confirm to Delete?",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);
				if (n == 0) // Confirm Delete = Yes
				{

					for (int i = 0; i < table.getRowCount(); i++) {
						Boolean chkDel = Boolean.valueOf(table.getValueAt(i, 0).toString()); // Checked
						if(chkDel) // Checked to Delete
						{
							String strDate= table.getValueAt(i, 1)
									.toString(); // get CustomerID
							
							DeleteData(strDate); // Delete Data
						}
					}
					
					JOptionPane.showMessageDialog(null, "Delete Data Successfully");

					PopulateData(); // Reload Table
				}

			}
		});

		getContentPane().add(B[1]);

		PopulateData();
	}
	
private static void PopulateData() {
	// Clear table
			table.setModel(new DefaultTableModel());

			// Model for Table
			DefaultTableModel model = new DefaultTableModel() {

				public Class<?> getColumnClass(int column) {
					switch (column) {
					case 0:
						return Boolean.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
					case 6:
						return String.class;
				
					default:
						return String.class;
					}
				}
			};
			table.setModel(model);

// Add Column
model.addColumn("Select");
model.addColumn("PS_Member");
model.addColumn("Num_Mem");
model.addColumn("Con_Pass");


Connection connect = null;
Statement s = null;

try {


	Class.forName("com.mysql.cj.jdbc.Driver");
	connect = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/food_store? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "");
	
	s = connect.createStatement();

	String sql = "SELECT * FROM  member ORDER BY PS_Member ASC";

	ResultSet rec = s.executeQuery(sql);
	int row = 0;
	while ((rec != null) && (rec.next())) {
		model.addRow(new Object[0]);
		model.setValueAt(false, row, 0); // Checkbox
		model.setValueAt(rec.getString("PS_Member"), row, 1);
		model.setValueAt(rec.getString("Num_Mem"), row, 2);
		model.setValueAt(rec.getString("Con_Pass"), row, 3);
		
	
		row++;
	}

} catch (Exception e) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, e.getMessage());
	e.printStackTrace();
}

try {
	if (s != null) {
		s.close();
		connect.close();
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}



// Delete
private void DeleteData(String strDate) {

Connection connect = null;
Statement s = null;

try {

	Class.forName("com.mysql.cj.jdbc.Driver");
	connect = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/food_store? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
			"root", "");
	
	s = connect.createStatement();

	String sql = "DELETE FROM member WHERE " + "PS_Member = '"
			+ strDate + "' ";
	s.execute(sql);

} catch (Exception e) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, e.getMessage());
	e.printStackTrace();
}

try {
	if (s != null) {
		s.close();
		connect.close();
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	System.out.println(e.getMessage());
	e.printStackTrace();
}
		getContentPane().add(B[1]);
			
}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				 Members M = new Members("Member");
			        M.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        M.setVisible(true);
			        M.setSize(1900,1000);
			 	   M.setLocation(10, 10);
			}
		});
	}
}
