import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class farmerActivity extends JPanel {
	private JTextField txtBags;
	private JTextField txtPlace;
	private JComboBox comboBox;
	private JLabel lblAmount;
	private Connection con;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public farmerActivity(int n) {
		setLayout(null);
		
		JLabel lblFarmerActivity = new JLabel("Farmer activity");
		lblFarmerActivity.setBounds(240, 12, 168, 15);
		add(lblFarmerActivity);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(41, 28, 580, 190);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFarmerDetails = new JLabel("Farmer Details");
		lblFarmerDetails.setBounds(34, 12, 151, 15);
		panel.add(lblFarmerDetails);
		
		JLabel lblFarmerId = new JLabel("Farmer Id");
		lblFarmerId.setBounds(23, 39, 70, 15);
		panel.add(lblFarmerId);
		
		JLabel lblCollectionCentre = new JLabel("Collection centre");
		lblCollectionCentre.setBounds(23, 119, 162, 15);
		panel.add(lblCollectionCentre);
		
		JLabel lblNomberOfBags = new JLabel("Number of Bags");
		lblNomberOfBags.setBounds(23, 79, 130, 15);
		panel.add(lblNomberOfBags);
		
		txtBags = new JTextField();
		txtBags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Bags=Integer.valueOf(txtBags.getText());
				lblAmount.setText(String.valueOf(Bags*1800));
			}
		});
		txtBags.setText("Enter < 50");
		txtBags.setBounds(206, 77, 114, 19);
		panel.add(txtBags);
		txtBags.setColumns(10);
		
		txtPlace = new JTextField();
		txtPlace.setBounds(206, 119, 114, 19);
		panel.add(txtPlace);
		txtPlace.setColumns(10);
		
		JLabel lblTotalCostTo = new JLabel("Total cost to be Deducted");
		lblTotalCostTo.setBounds(23, 169, 212, 15);
		panel.add(lblTotalCostTo);
		
		lblAmount = new JLabel("0");
		lblAmount.setBounds(270, 169, 60, 15);
		panel.add(lblAmount);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Bags=Integer.valueOf(txtBags.getText());
				lblAmount.setText(String.valueOf(Bags*1800));
				
               con=getConnection();
	            
	            String newData="INSERT INTO fertilizer"
						+ "(fId,bags,collectionPlace,amount)"							
						+"values(?,?,?,?)";
				try {
				PreparedStatement ps=con.prepareStatement(newData);
				
				
				ps.setString(1, comboBox.getSelectedItem().toString());
				ps.setString(2, txtBags.getText());
				ps.setString(3, txtPlace.getText());
				ps.setString(4, lblAmount.getText());
				JOptionPane.showConfirmDialog(null,"Confirm "+txtBags.getText()+" bags before application! \n Total cost is "+lblAmount.getText() );
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "You have successfully applied for Fertilizer"); 
				}
				catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					
				}

			}
		});
		btnSubmit.setBounds(425, 159, 117, 25);
		panel.add(btnSubmit);
		
		JLabel lblFarmer = new JLabel("");
		lblFarmer.setBounds(346, 39, 212, 19);
		panel.add(lblFarmer);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item=comboBox.getSelectedItem().toString();
				
				con=getConnection();
				Statement st;
				try {
					st=con.createStatement();
					String data="Select Name FROM farmer WHERE farmerID="+item+"";
					ResultSet rs=st.executeQuery(data);
					while(rs.next()) {
						lblFarmer.setText(rs.getString("Name"));
						//String id=rs.getString("farmerID");
						//comboBox.addItem(id);
						
					}
				}catch(Exception e1) {
					//System.out.println("Something went wrong" +e1.getMessage());
					
				}
			}
				
			
		});
		comboBox.setBounds(206, 34, 114, 24);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBounds(41, 230, 580, 184);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(12, 12, 556, 160);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Collection Date", "Collected Weight", "Cumulative Weight"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(70);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(170);
		
		scrollPane.setViewportView(table);
		
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM collectedTea WHERE farmerId="+n+"";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String mDate=rs.getString("collectionDate");
			    String tKgs=rs.getString("collectedKgs");
				String cKgs=rs.getString("cumulative");
				
				String tableData[] = {mDate,tKgs,cKgs};
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
		
		
		
		fillCombo();

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
public void fillCombo() {
	con=getConnection();
	Statement st;
	try {
		st=con.createStatement();
		String data="Select * FROM farmer";
		ResultSet rs=st.executeQuery(data);
		while(rs.next()) {
			String id=rs.getString("farmerID");
			comboBox.addItem(id);
			
		}
	}catch(Exception e) {
		System.out.println("Something went wrong" +e.getMessage());
		
	}
}
public void getMyKgs() {

}
}	

