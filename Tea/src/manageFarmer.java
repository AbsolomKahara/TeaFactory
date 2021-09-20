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

public class manageFarmer extends JPanel {
	private JTable table;
	private Connection con;
	String fID;
	String fName;
	String fContact;
	String fLocation;
	String fPassword;

	/**
	 * Create the panel.
	 */
	public manageFarmer() {
		setLayout(null);
		
		JLabel lblManageFarmer = new JLabel("Manage Farmer");
		lblManageFarmer.setBounds(233, 12, 155, 15);
		add(lblManageFarmer);
		
		JLabel lblRegisteredFarmers = new JLabel("Registered Farmers");
		lblRegisteredFarmers.setBounds(122, 35, 223, 15);
		add(lblRegisteredFarmers);
		
		table = new JTable();
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Farmer ID", "Name","Phone No", "Location", "Password"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(80);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(41, 58, 598, 259);
		add(scrollPane);
		
		getFarmers();
		
		//Add table results
		
		//scrollPane.setViewportView(table);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Register rg=new Register();
				rg.main(null);
			}
		});
		btnAddNew.setBounds(70, 350, 117, 25);
		add(btnAddNew);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm1=(DefaultTableModel) table.getModel() ;
				int selectedRowIndex=table.getSelectedRow();
				Object toDelete= dtm1.getValueAt(selectedRowIndex, 0);
				 String sql="DELETE FROM farmer WHERE farmerID="+toDelete+"";
				 
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
		            
		            
		           // getFarmers();
		           
		            
			}
		});
		btnDelete.setBounds(228, 350, 117, 25);
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
			JOptionPane.showConfirmDialog(null, "Not Connected");
			return null;
		}
	}
	public void getFarmers() {
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM farmer";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				fID=rs.getString("farmerID");
			    fName=rs.getString("Name");
				fContact=rs.getString("Phone");
				fLocation=rs.getString("Location");
				fPassword=rs.getString("Password");
				String tableData[] = {fID,fName,fContact,fLocation,fPassword};
				DefaultTableModel dtm= (DefaultTableModel)table.getModel();
				
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
