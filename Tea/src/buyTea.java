import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;

public class buyTea extends JPanel {
	private JTextField textField;
	private JTable table;
	private JComboBox comboBox;
	private Date colDate;
	private JLabel lblFarmer;
	int cumulative;
	int newCumulative;
	Connection con;

	/**
	 * Create the panel.
	 */
	public buyTea() {
		setLayout(null);
		
		JLabel lblBuyTea = new JLabel("Buy Tea");
		lblBuyTea.setBounds(310, 12, 70, 15);
		add(lblBuyTea);
		
		JLabel lblSelectFarmersNo = new JLabel("Select Farmer's No");
		lblSelectFarmersNo.setBounds(71, 100, 160, 15);
		add(lblSelectFarmersNo);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		comboBox.setBounds(278, 95, 148, 24);
		add(comboBox);
		
		fillCombo();
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(71, 140, 70, 15);
		add(lblName);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(71, 51, 70, 15);
		add(lblDate);
		
		JLabel lblKgs = new JLabel("Kgs");
		lblKgs.setBounds(71, 179, 70, 15);
		add(lblKgs);
		
		lblFarmer = new JLabel("");
		lblFarmer.setBounds(278, 140, 290, 15);
		add(lblFarmer);
		
		textField = new JTextField();
		textField.setBounds(278, 177, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(278, 47, 148, 19);
		add(dateChooser);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Date","Farmer ID", "Name","Today'sKg", "CumulativeKg"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(102);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(71, 239, 548, 227);
		add(scrollPane);
		
		//table = new JTable();
		
		getTodayKgs();
		
		//scrollPane.setColumnHeaderView(table);
		
		JLabel lblTodaysKg = new JLabel("Collected Kgs");
		lblTodaysKg.setBounds(154, 212, 138, 15);
		add(lblTodaysKg);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			//	colDate=((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				colDate=dateChooser.getDate();
				String fId=comboBox.getSelectedItem().toString();
				String farmerName=lblFarmer.getText();
				int colKgs=Integer.valueOf(textField.getText());
				
				System.out.println(colDate +" ,"+df.format(colDate));
				//Add to database
				Connection con=getConnection();
				Statement st;
				
				//Get cumulative total
				String cumu="SELECT cumulative FROM collectedTea WHERE farmerId="+fId+"";
				try {
					st = con.createStatement();
					ResultSet rs=st.executeQuery(cumu);
					while(rs.next()) {
						 cumulative=rs.getInt("cumulative");
					}
				}catch(SQLException m){
					System.out.print("Error occured");
				}
				
				newCumulative=cumulative+colKgs;
	            
	            String newData="INSERT INTO collectedTea"
						+ "(collectionDate,farmerId,farmerName,collectedKgs,cumulative)"							
						+"values(?,?,?,?,?)";
				try {
				PreparedStatement ps=con.prepareStatement(newData);
				
				
				ps.setString(1, df.format(colDate));
				ps.setString(2, fId);
				ps.setString(3, farmerName);
				ps.setInt(4, colKgs);
				ps.setInt(5,newCumulative);
				JOptionPane.showConfirmDialog(null,"Confirm "+colKgs+"Kgs before submission!" );
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, colKgs+"Kgs collected from "+farmerName+" farmer Id "+fId);
				}
				catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					
				}
				/*
				String toMonth="INSERT INTO Month"
						+ "(collectionDate,farmerId,farmerName,collectedKgs)"							
						+"values(?,?,?,?)";
				try {
				PreparedStatement ps1=con.prepareStatement(toMonth);
				
				
				ps1.setString(1, df.format(colDate));
				ps1.setString(2, fId);
				ps1.setString(3, farmerName);
				ps1.setInt(4, colKgs);
				//JOptionPane.showConfirmDialog(null,"Confirm "+colKgs+"Kgs before submission!" );
				ps1.executeUpdate();
				//JOptionPane.showMessageDialog(null, colKgs+"Kgs collected from "+farmerName+" farmer Id "+fId);
				}
				catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					
				}
*/
				
			}
		});
		btnSubmit.setBounds(451, 174, 117, 25);
		add(btnSubmit);

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
	public void getTodayKgs() {
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM collectedTea";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String cDate=rs.getString("collectionDate");
			    String fId=rs.getString("farmerID");
				String fName=rs.getString("farmerName");
				String fKg=rs.getString("collectedKgs");
				String cKg=rs.getString("cumulative");
				//cPassword=rs.getString("Password");
				String tableData[] = {cDate,fId,fName,fKg,cKg};
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
