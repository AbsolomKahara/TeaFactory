import javax.swing.JPanel;
import javax.swing.JLabel;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JButton;
import java.awt.Color;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class deliveryReports extends JPanel {
	private String toDay;
	Connection con;
	int tKgs=0;
	int mKgs=0;

	/**
	 * Create the panel.
	 */
	public deliveryReports() {
		setLayout(null);
		
		JLabel lblDeliveryReports = new JLabel("Delivery reports");
		lblDeliveryReports.setBounds(64, 12, 201, 15);
		add(lblDeliveryReports);
		
		JLabel lblMonthlyReport = new JLabel("Monthly Report");
		lblMonthlyReport.setBounds(423, 54, 159, 15);
		add(lblMonthlyReport);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(349, 81, 306, 351);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSelectTheMonth = new JLabel("Select the month");
		lblSelectTheMonth.setBounds(12, 24, 137, 15);
		panel.add(lblSelectTheMonth);
		
		JLabel lblTotalKgs = new JLabel("Total Kgs");
		lblTotalKgs.setBounds(55, 185, 79, 15);
		panel.add(lblTotalKgs);
		
		JLabel monthKgs = new JLabel("0");
		monthKgs.setBounds(224, 185, 70, 15);
		panel.add(monthKgs);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(55, 68, 122, 27);
		panel.add(monthChooser);
		
		JLabel lblAmountToPay = new JLabel("Amount To Pay (Ksh)");
		lblAmountToPay.setBounds(55, 225, 145, 15);
		panel.add(lblAmountToPay);
		
		JLabel monthAmount = new JLabel("0");
		monthAmount.setBounds(224, 225, 70, 15);
		panel.add(monthAmount);
		
		JButton btnByMonth = new JButton("View");
		btnByMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				monthKgs.setText("0");
				monthAmount.setText("0");
				int getMonth=monthChooser.getMonth()+1;
				String sDate="2021-"+getMonth+"-01";
				String eDate="2021-"+getMonth+"-31";
				//System.out.println(sDate +","+eDate+","+getMonth);
				con=getConnection();
				String byDate="SELECT collectedKgs FROM collectedTea WHERE collectionDate BETWEEN '"+sDate+"' AND '"+eDate+"' " ;
				Statement stat;
				try {
					stat=con.createStatement();
					ResultSet s=stat.executeQuery(byDate);
					while(s.next()) {
						int kgs=s.getInt("collectedKgs");
					 
						mKgs=mKgs+kgs;
						//System.out.println(today);
						monthKgs.setText(String.valueOf(mKgs));
						monthAmount.setText(String.valueOf(mKgs*45));
						mKgs=0;
					}
				}catch(SQLException sn) {
					sn.printStackTrace();
					
					System.out.print("Error getting today's date");
					
				}
			}
		});
		btnByMonth.setBounds(55, 119, 97, 25);
		panel.add(btnByMonth);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBounds(37, 81, 292, 351);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSelectMonthAnd = new JLabel("Select month and day");
		lblSelectMonthAnd.setBounds(12, 12, 182, 15);
		panel_1.add(lblSelectMonthAnd);
		
		JLabel lblTotalKgs_1 = new JLabel("Total Kgs");
		lblTotalKgs_1.setBounds(38, 259, 79, 15);
		panel_1.add(lblTotalKgs_1);
		
		JLabel lblKgs = new JLabel("0");
		lblKgs.setBounds(194, 259, 70, 15);
		panel_1.add(lblKgs);
		
		JLabel lblAmountToPay_1 = new JLabel("Amount To Pay (Ksh)");
		lblAmountToPay_1.setBounds(35, 299, 145, 15);
		panel_1.add(lblAmountToPay_1);
		
		JLabel lblAmount = new JLabel("0");
		lblAmount.setBounds(194, 299, 70, 15);
		panel_1.add(lblAmount);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(57, 38, 223, 159);
		panel_1.add(calendar);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblKgs.setText("0");
				lblAmount.setText("0");
				DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				toDay=df.format(calendar.getDate());
				con=getConnection();
				String byDate="SELECT collectedKgs FROM collectedTea WHERE collectionDate='"+toDay+"' ";
				Statement state;
				try {
					state=con.createStatement();
					ResultSet st=state.executeQuery(byDate);
					while(st.next()) {
						int kgs=st.getInt("collectedKgs");
					
						tKgs=tKgs+kgs;
						//System.out.println(today);
						lblKgs.setText(String.valueOf(tKgs));
						lblAmount.setText(String.valueOf(tKgs*45));
						tKgs=0;
					}
				}catch(SQLException sq) {
					sq.printStackTrace();
					
					System.out.print("Error getting today's date");
					
				}
			}
		});
		btnView.setBounds(183, 222, 97, 25);
		panel_1.add(btnView);
		
		
		
		JLabel lblDailyReport = new JLabel("Daily Report");
		lblDailyReport.setBounds(76, 54, 143, 15);
		add(lblDailyReport);

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
