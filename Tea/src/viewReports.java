import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class viewReports extends JPanel {
	private JTable table;
	Connection con;

	int tKgs;
	int cKgs;
	/**
	 * Create the panel.
	 */
	public viewReports() {
		setLayout(null);
		
		JLabel lblViewReports = new JLabel("View  Reports");
		lblViewReports.setBounds(54, 12, 129, 15);
		add(lblViewReports);
		
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(12, 120, 656, 264);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Farmer ID", "Name","Today'sKg", "CumulativeKg"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(70);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(170);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			
		scrollPane.setViewportView(table);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(38, 74, 141, 19);
		add(dateChooser);
		
		JLabel lblSelectDate = new JLabel("Select Date");
		lblSelectDate.setBounds(38, 47, 145, 15);
		add(lblSelectDate);
		
		JLabel label = new JLabel("0");
		label.setBounds(390, 396, 70, 15);
		add(label);
		
		JLabel label_1 = new JLabel("0");
		label_1.setBounds(390, 430, 70, 15);
		add(label_1);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel dtm1=(DefaultTableModel)table.getModel();
				while(dtm1.getRowCount()>0) {
					dtm1.removeRow(0);
				}
				
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String mDate=df.format(dateChooser.getDate());
				con=getConnection();
				Statement st;
				try {
					st = con.createStatement();
					String sql="Select * FROM collectedTea WHERE collectionDate='"+mDate+"'";
					ResultSet rs=st.executeQuery(sql);
					while(rs.next()) {
						//String cDate=rs.getString("collectionDate");
					    String fId=rs.getString("farmerID");
						String fName=rs.getString("farmerName");
						String fKg=rs.getString("collectedKgs");
						String cKg=rs.getString("cumulative");
						//cPassword=rs.getString("Password");
						tKgs=rs.getInt("collectedKgs")+tKgs;
						cKgs=rs.getInt("cumulative")+cKgs;
						label.setText(String.valueOf(tKgs));
						label_1.setText(String.valueOf(cKgs));
						
						String tableData[] = {fId,fName,fKg,cKg};
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
				
			
		});
		btnView.setBounds(198, 69, 117, 25);
		add(btnView);
		
		JLabel lblTotalWeight = new JLabel("Total Weight");
		lblTotalWeight.setBounds(233, 396, 117, 15);
		add(lblTotalWeight);
		
		
		
		JLabel lblCummulativeTotal = new JLabel("Cumulative Total");
		lblCummulativeTotal.setBounds(233, 430, 129, 15);
		add(lblCummulativeTotal);
		
		

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
}
