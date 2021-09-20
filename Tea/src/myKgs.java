import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class myKgs extends JPanel {
	private JTextField myD;
	private JTextField cTotal;
	private Connection con;
	private JTextField txtAmount;
	private String toDay;

	/**
	 * Create the panel.
	 */
	public myKgs(int n) {
		setLayout(null);
		
		JLabel lblMykgs = new JLabel("My Profile");
		lblMykgs.setBounds(291, 12, 70, 15);
		add(lblMykgs);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(12, 36, 683, 214);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFarmerName = new JLabel("Farmer Name");
		lblFarmerName.setBounds(12, 22, 129, 15);
		panel.add(lblFarmerName);
		
		JLabel lblFarmerId = new JLabel("Farmer ID");
		lblFarmerId.setBounds(12, 56, 70, 15);
		panel.add(lblFarmerId);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(12, 95, 70, 15);
		panel.add(lblLocation);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 177, 70, 15);
		panel.add(lblPassword);
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset rs=new reset(n);
				rs.setVisible(true);
			}
		});
		btnResetPassword.setBounds(411, 177, 149, 25);
		panel.add(btnResetPassword);
		
		JLabel lblName = new JLabel("");
		lblName.setBounds(194, 22, 205, 15);
		panel.add(lblName);
		
		JLabel lblId = new JLabel("");
		lblId.setBounds(194, 56, 70, 15);
		panel.add(lblId);
		
		JLabel lblLoc = new JLabel("");
		lblLoc.setBounds(194, 95, 205, 15);
		panel.add(lblLoc);
		
		JLabel lblPass = new JLabel("");
		lblPass.setBounds(194, 177, 113, 15);
		panel.add(lblPass);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(12, 134, 70, 15);
		panel.add(lblContact);
		
		JLabel lblCont = new JLabel("");
		lblCont.setBounds(194, 142, 205, 15);
		panel.add(lblCont);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBounds(12, 262, 683, 241);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMyDeliveryReport = new JLabel("My Delivery Report");
		lblMyDeliveryReport.setBounds(231, 12, 196, 15);
		panel_1.add(lblMyDeliveryReport);
		
		JLabel lblSelectAMonth = new JLabel("Select  Month  and Day:");
		lblSelectAMonth.setBounds(32, 39, 212, 15);
		panel_1.add(lblSelectAMonth);
		
		JLabel lblMonthlyTotal = new JLabel("Cumulative Monthly Total");
		lblMonthlyTotal.setBounds(370, 105, 203, 15);
		panel_1.add(lblMonthlyTotal);
		
		JLabel lblSubtotal = new JLabel("Day's net Weight");
		lblSubtotal.setBounds(370, 39, 148, 15);
		panel_1.add(lblSubtotal);
		
		myD = new JTextField();
		myD.setEditable(false);
		myD.setBounds(370, 66, 114, 27);
		panel_1.add(myD);
		myD.setColumns(10);
		
		cTotal = new JTextField();
		cTotal.setEditable(false);
		cTotal.setBounds(370, 132, 114, 27);
		panel_1.add(cTotal);
		cTotal.setColumns(10);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(32, 62, 223, 159);
		panel_1.add(calendar);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAmount.setText("0");
				myD.setText("0");
				cTotal.setText("0");
				
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				toDay=df.format(calendar.getDate());
				
				String byDate="SELECT collectedKgs FROM collectedTea WHERE collectionDate='"+toDay+"' AND farmerId="+n+"";
				Statement state;
				try {
					state=con.createStatement();
					ResultSet st=state.executeQuery(byDate);
					while(st.next()) {
						String today=st.getString("collectedKgs");
						JOptionPane.showMessageDialog(null,"On "+toDay+" "+today+"Kgs of tea were collected from you");
						//System.out.println(today);
						myD.setText(today);
					}
				}catch(SQLException sq) {
					sq.printStackTrace();
					
					System.out.print("Error getting today's date");
					
				}
				
				//Get cumulative total
				String cumu="SELECT * FROM collectedTea WHERE farmerId="+n+"";
				Statement st;
				try {
					st = con.createStatement();
					ResultSet rs=st.executeQuery(cumu);
					while(rs.next()) {
						 String cumulative=rs.getString("cumulative");
						 cTotal.setText(cumulative);
						 
					}
				}catch(SQLException m){
					System.out.print("Error occured");
				}
				int myCumTotal=Integer.valueOf(cTotal.getText());
				txtAmount.setText(String.valueOf(myCumTotal*45));
				
			}
		});
		btnView.setBounds(267, 197, 69, 25);
		panel_1.add(btnView);
		
		JLabel lblExpectedAmountksh = new JLabel("Expected Amount (Ksh)");
		lblExpectedAmountksh.setBounds(370, 171, 179, 15);
		panel_1.add(lblExpectedAmountksh);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(370, 198, 114, 23);
		panel_1.add(txtAmount);
		txtAmount.setColumns(10);
		
	
		
		con=getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql="Select * FROM farmer WHERE farmerID="+n+"";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String fID=rs.getString("farmerID");
			    String fName=rs.getString("Name");
				String fContact=rs.getString("Phone");
				String fLocation=rs.getString("Location");
				String fPassword=rs.getString("Password");
				
				lblName.setText(fName);
				lblId.setText(fID);
				lblLoc.setText(fLocation);
				lblPass.setText(fPassword);
				lblCont.setText(fContact);
				
			}
		}
		catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Something went wrong");
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
			//e.printStackTrace();
			//JOptionPane.showConfirmDialog(null, "Not Connected");
			return null;
		}
	}
}
