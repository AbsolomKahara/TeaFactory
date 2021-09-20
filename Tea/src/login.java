import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

public class login {

	private JFrame frame;
	private JTextField txtName;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnClerk;
	private JRadioButton rdbtnFarmer;
	Connection con;
	
	String userPass;
	String userPas;
	int userId;
	String userName;
	private JCheckBox chckbxShowPassword;
	private JPasswordField pass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblPassword = new JLabel("Password");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String userName=txtName.getText().toString().strip();
				//int userPass=Integer.valueOf(txtPassword.getText().toString());
				//String name="admin";
				//System.out.println(userName);
				//System.out.println(userPass);
				userPass=getfPassword();
				userPas=getcPassword();
				if(rdbtnFarmer.isSelected() && userPass.equals(pass.getText())) {
					//sSystem.out.println("Farmer login");
					
									
					frame.setVisible(false);
					FarmerHome fHome=new FarmerHome(Integer.valueOf(txtName.getText()));
					fHome.setVisible(true);
					
				}
				else if(rdbtnClerk.isSelected() && userPas.equals(pass.getText())) {
					frame.setVisible(false);
					ClerkHome cHome=new ClerkHome();
					cHome.setVisible(true);
				//	System.out.println("Clerk login");
					}				
				else if(   rdbtnAdmin.isSelected() && Integer.valueOf(pass.getText())==1234 && txtName.getText().equals("Admin")){
					//System.out.println("Admin login");

					//System.out.println(userName+":"+userPass);
					
				frame.setVisible(false);
				AdminHome homeAdmin=new AdminHome();
				homeAdmin.setVisible(true);
				//JOptionPane.showMessageDialog(null, "Welcome");
					
					//JOptionPane.showMessageDialog(null, "Welcomeeee");

				}
				else {
					JOptionPane.showMessageDialog(null,"Confirm credentials enterd are right");
				}
			}
			
		});
		
		chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxShowPassword.isSelected()) {
					pass.setEchoChar((char)0);
				}
				else {
					pass.setEchoChar('*');
				}
			}
		});
		
		pass = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblPassword))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(pass)
								.addComponent(btnLogin, Alignment.LEADING)
								.addComponent(chckbxShowPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtName, Alignment.LEADING))))
					.addContainerGap(227, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxShowPassword)
					.addGap(9)
					.addComponent(btnLogin)
					.addContainerGap(93, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblSignInAs = new JLabel("Sign in As : ");
		lblSignInAs.setBounds(12, 12, 101, 15);
		panel.add(lblSignInAs);
		
		 rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnAdmin.isSelected()) {
					rdbtnFarmer.setSelected(false);
					rdbtnClerk.setSelected(false);
					lblName.setText("User Name");
				}
			}
		});
		rdbtnAdmin.setBounds(103, 35, 149, 23);
		panel.add(rdbtnAdmin);
		
		 rdbtnClerk = new JRadioButton("Clerk");
		 rdbtnClerk.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(rdbtnClerk.isSelected()) {
		 			rdbtnAdmin.setSelected(false);
		 			rdbtnFarmer.setSelected(false);
		 			lblName.setText("Clerk ID");
		 			
		 		}
		 	}
		 });
		rdbtnClerk.setBounds(103, 62, 149, 23);
		panel.add(rdbtnClerk);
		
		rdbtnFarmer = new JRadioButton("Farmer");
		rdbtnFarmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnFarmer.isSelected()) {
					rdbtnClerk.setSelected(false);
					rdbtnAdmin.setSelected(false);
					lblName.setText("Farmer ID");
					
				}	
			}
		});
		rdbtnFarmer.setBounds(103, 89, 149, 23);
		panel.add(rdbtnFarmer);
		frame.getContentPane().setLayout(groupLayout);
	}
	public Connection getConnection() {
		Connection con= null;
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TeaFactory","root",""); 
			//JOptionPane.showConfirmDialog(null, "Connected");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//JOptionPane.showConfirmDialog(null, "Not Connected");
			return null;
		}
		
		
	}
	public String getfPassword() {
		con=getConnection();
		Statement st;
		String fPassword=null;
		try {
			st = con.createStatement();
			String sql="Select Password FROM farmer WHERE farmerID="+txtName.getText()+"";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				//String fID=rs.getString("farmerID");						   
				fPassword=rs.getString("Password");
				
				
			}
		}
		catch(SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Something went wrong");
			//e1.printStackTrace();
			//System.out.println(e1.getMessage());
		}
		return fPassword;
	}
	public String getcPassword() {
		con=getConnection();
		Statement st;
		String cPassword=null;
		try {
			st = con.createStatement();
			String sql="Select Password FROM Clerk WHERE id="+txtName.getText()+"";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				//String fID=rs.getString("farmerID");						   
				cPassword=rs.getString("Password");
				
				
			}
		}
		catch(SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Something went wrong");
			//e1.printStackTrace();
			//System.out.println(e1.getMessage());
		}
		return cPassword;
	}
}
