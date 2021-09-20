import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Activities extends JPanel {
	private JTable table;
	Connection con;
	private JLabel lblBags;
	private JLabel lblAmount;

	/**
	 * Create the panel.
	 */
	public Activities() {
		setLayout(null);
		
		JLabel lblAddActivities = new JLabel("Received Fertilizer Submission");
		lblAddActivities.setBounds(212, 12, 274, 15);
		add(lblAddActivities);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Farmer Id", "No of Bags", "Collection Location","Amount"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(70);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(70);
		//scrollPane.setViewportView(table);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(25, 53, 614, 264);
		add(scrollPane);
		
		JLabel lblTotalNoOf = new JLabel("Total No. of Bags ");
		lblTotalNoOf.setBounds(74, 350, 149, 15);
		add(lblTotalNoOf);
		
		JLabel lblTotalAmount = new JLabel("Total Amount (Ksh)");
		lblTotalAmount.setBounds(74, 391, 149, 15);
		add(lblTotalAmount);
		
		lblBags = new JLabel("0");
		lblBags.setBounds(266, 350, 70, 15);
		add(lblBags);
		
		lblAmount = new JLabel("0");
		lblAmount.setBounds(266, 391, 91, 15);
		add(lblAmount);
		
		getFertilizerApplied();
		getTotal();
		

	}
	public void getFertilizerApplied() {
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM fertilizer";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String fId=rs.getString("fId");
			    String bags=rs.getString("bags");
				String place=rs.getString("collectionPlace");
				String amount=rs.getString("amount");
				//cPassword=rs.getString("Password");
				String tableData[] = {fId,bags,place,amount};
				DefaultTableModel dtm= (DefaultTableModel)table.getModel();
				//dtm.setRowCount(0);
				dtm.addRow(tableData);
				
			}
		}
		catch(SQLException e) {
			//JOptionPane.showMessageDialog(e.getMessage());
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
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
	public void getTotal() {
		int bagSum=0;
		int amountSum=0;
		for (int i=0;i< table.getRowCount(); i++ ) {
			bagSum=bagSum+Integer.valueOf((String) table.getValueAt(i, 1));
			amountSum=amountSum+Integer.valueOf((String) table.getValueAt(i, 3));

		}
		lblAmount.setText(String.valueOf(amountSum));
		lblBags.setText(String.valueOf(bagSum));
		
	}
}
