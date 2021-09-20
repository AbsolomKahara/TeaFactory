import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class manageClerk extends JPanel {
	private JTable table;
	String cID;
	String cName;
	String cContact;
	String cLocation;
	String cPassword;
	Connection con;

	/**
	 * Create the panel.
	 */
	public manageClerk() {
		setLayout(null);
		
		JLabel lblManageClerk = new JLabel("Manage Clerk");
		lblManageClerk.setBounds(250, 12, 139, 15);
		add(lblManageClerk);
		
		JLabel lblRegisteredClerk = new JLabel("Registered Clerk");
		lblRegisteredClerk.setBounds(110, 38, 160, 15);
		add(lblRegisteredClerk);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Clerk ID", "Name", "Phone","Email", "Password"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(180);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);

		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(26, 62, 632, 225);
		add(scrollPane);
		
		getClerks();
		
		//scrollPane.setViewportView(table);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				RegisterClerks rc=new RegisterClerks();
				rc.setVisible(true);
			}
		});
		btnAddNew.setBounds(48, 305, 117, 25);
		add(btnAddNew);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm1=(DefaultTableModel) table.getModel() ;
				int selectedRowIndex=table.getSelectedRow();
				Object toDelete= dtm1.getValueAt(selectedRowIndex, 0);
				 String sql="DELETE FROM Clerk WHERE id="+toDelete+"";
				 
				// String sql="DELETE FROM Search WHERE termSearched ='"+selected+"'";
					//String dropTable="DROP Table "+selected+"";
					
		            Connection con=getConnection();
		            try {
		            	Statement ps=con.prepareStatement(sql);
				           // Statement ps2=con.prepareStatement(dropTable);
				           // ps.setString(1, selected);
				            ps.execute(sql);
		            }

		            catch(SQLException e2) {
		            	e2.printStackTrace();
		            	System.out.println(e2.getMessage());
		            	
		            }
		            dtm1.removeRow(selectedRowIndex);
		           
		            
		           // ps2.execute(dropTable);
		           // getClerks();

			}
		});
		btnDelete.setBounds(203, 305, 117, 25);
		add(btnDelete);

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
	public void getClerks() {
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM Clerk";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				cID=rs.getString("id");
			    cName=rs.getString("Name");
				cLocation=rs.getString("Email");
				cContact=rs.getString("Phone");
				cPassword=rs.getString("Password");
				String tableData[] = {cID,cName,cContact,cLocation,cPassword};
				DefaultTableModel dtm= (DefaultTableModel)table.getModel();
				//dtm.setRowCount(0);
				dtm.addRow(tableData);
				
			}
		}
		catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Something went wrong");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
}
