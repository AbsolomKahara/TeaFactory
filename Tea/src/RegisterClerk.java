import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterClerk extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public RegisterClerk() {
		setLayout(null);
		
		JLabel lblRegisterClerk = new JLabel("Register Clerk");
		lblRegisterClerk.setBounds(126, 12, 130, 15);
		add(lblRegisterClerk);
		
		JLabel lblClerkId = new JLabel("Clerk ID");
		lblClerkId.setBounds(81, 71, 70, 15);
		add(lblClerkId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(81, 111, 70, 15);
		add(lblName);
		
		textField = new JTextField();
		textField.setBounds(202, 69, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(202, 109, 114, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(81, 154, 70, 15);
		add(lblLocation);
		
		textField_2 = new JTextField();
		textField_2.setBounds(202, 152, 114, 19);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(81, 198, 70, 15);
		add(lblPassword);
		
		textField_3 = new JTextField();
		textField_3.setBounds(202, 196, 114, 19);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAddClerk = new JButton("Add Clerk");
		btnAddClerk.setBounds(202, 274, 117, 25);
		add(btnAddClerk);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				manageClerk mngClerk=new manageClerk();
				mngClerk.setVisible(true);
			}
		});
		btnClose.setBounds(62, 274, 117, 25);
		add(btnClose);

	}

}
