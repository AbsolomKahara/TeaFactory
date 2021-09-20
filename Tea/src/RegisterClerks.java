import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterClerks extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtPass;
	String cName;
	String cEmail;
	String cPhone;
	String cPassword;
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterClerks frame = new RegisterClerks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterClerks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterNewClerk = new JLabel("Register New Clerk");
		lblRegisterNewClerk.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRegisterNewClerk.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterNewClerk.setBounds(182, 12, 208, 29);
		contentPane.add(lblRegisterNewClerk);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(84, 58, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(84, 100, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(84, 143, 70, 15);
		contentPane.add(lblPhone);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(84, 186, 70, 15);
		contentPane.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(210, 53, 114, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(210, 98, 114, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(210, 141, 114, 19);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(210, 184, 114, 19);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminHome mngClerk=new AdminHome();
				mngClerk.setVisible(true);
			}
		});
		btnBack.setBounds(101, 246, 117, 25);
		contentPane.add(btnBack);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 con=getConnection();
	            
				 cName=txtName.getText();
				 cEmail=txtEmail.getText();
				 cPhone=txtPhone.getText();
				 cPassword=txtPass.getText();
				 
				 
	            String newData="INSERT INTO Clerk"
						+ "(Name,Email,Phone,Password)"							
						+"values(?,?,?,?)";
	            try {
				
				PreparedStatement ps=con.prepareStatement(newData);
				
				
				ps.setString(1, cName);
				ps.setString(2, cEmail);
				ps.setString(3, cPhone);
				ps.setString(4, cPassword);
				
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Clerk Registered Successfully");
	            }
	            catch(SQLException e1) {
	            	System.out.println(e1.getMessage());
	            }
			}
		});
		btnSubmit.setBounds(264, 246, 117, 25);
		contentPane.add(btnSubmit);
		
		
		
		
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
			System.out.println(e.getMessage());
			//JOptionPane.showConfirmDialog(null, "Not Connected");
			return null;
		}
		
		
	}

}
