import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import dashboard.PanelButtonMouseAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome extends JFrame {

	private JPanel contentPane;
	private Activities adminActivities;
	private deliveryReports dReports;
	private manageClerk mngClerk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome frame = new AdminHome();
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
	public AdminHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdminHome = new JLabel("Admin Home");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		
		JPanel mainPanel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdminHome)
							.addGap(552))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdminHome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
					.addContainerGap())
		);
		mainPanel.setLayout(null);
		
		adminActivities=new Activities();
		adminActivities.setBounds(0, 25, 690, 578);
		mainPanel.add(adminActivities);
		
		mngClerk=new manageClerk();
		mngClerk.setBounds(0, 25, 690, 578);
		mainPanel.add(mngClerk);
		
		dReports=new deliveryReports();
		dReports.setBounds(0, 25, 690, 578);
		mainPanel.add(dReports);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(210, 180, 140));
		panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(mngClerk);
			}
			
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 180, 140));
		panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(dReports);
			}
			
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(210, 180, 140));
		panel_3.addMouseListener(new PanelButtonMouseAdapter(panel_3)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(adminActivities);
			}
			
		});
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login lg=new login();
				lg.main(null);
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(71, Short.MAX_VALUE)
					.addComponent(btnClose)
					.addGap(33))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnClose)
					.addGap(40))
		);
		panel_3.setLayout(null);
		
		JLabel lblActivities = new JLabel("Activities");
		lblActivities.setBounds(56, 49, 70, 15);
		panel_3.add(lblActivities);
		panel_2.setLayout(null);
		
		JLabel lblDelivery = new JLabel("Delivery");
		lblDelivery.setBounds(66, 56, 70, 15);
		panel_2.add(lblDelivery);
		panel_1.setLayout(null);
		
		menuClicked(mngClerk);

		
		JLabel lblManageClerks = new JLabel("Manage Clerks");
		lblManageClerks.setBounds(41, 23, 144, 15);
		panel_1.add(lblManageClerks);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	public void menuClicked(JPanel panel) {
		adminActivities.setVisible(false);
		dReports.setVisible(false);
		mngClerk.setVisible(false);
		
		
		panel.setVisible(true);
	}
	private class PanelButtonMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel=panel;
		}
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(240, 128, 128));
			
		}
		public  void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(255, 192, 203));
		}
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(240, 128, 128));
			
		}
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(240, 128, 128));
			
		}
		
		
	}
}
