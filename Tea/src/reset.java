import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class reset extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reset frame = new reset();
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
	public reset() {}
	public reset(int n) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResetPassword = new JLabel("Reset Password");
		lblResetPassword.setBounds(115, 12, 159, 15);
		contentPane.add(lblResetPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(56, 58, 133, 15);
		contentPane.add(lblNewPassword);
		
		textField = new JTextField();
		textField.setBounds(220, 56, 145, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con=getConnection();
				 String newData="UPDATE farmer SET Password=? WHERE farmerID="+n+"";
							
					try {
					PreparedStatement ps=con.prepareStatement(newData);
					
					
					ps.setString(1, textField.getText());
				//	ps.setString(2, fId);
				//	ps.setString(3, farmerName);
				//	ps.setInt(4, colKgs);
				//	JOptionPane.showConfirmDialog(null,"Confirm "+colKgs+"Kgs before submission!" );
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "New password have been reset "); 
					
					}
					catch(SQLException e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
						
					}

					
				
				
				setVisible(false);
			}
		});
		btnUpdate.setBounds(158, 126, 117, 25);
		contentPane.add(btnUpdate);
	}
	public Connection getConnection() {
		Connection con= null;
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/TeaFactory","root",""); 
			//JOptionPane.showConfirmDialog(null, "Connected");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//JOptionPane.showConfirmDialog(null, "Not Connected");
			return null;
		}
		
		
	}
}
