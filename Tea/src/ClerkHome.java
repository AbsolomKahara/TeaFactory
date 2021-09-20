import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import AdminHome.PanelButtonMouseAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClerkHome extends JFrame {

	private JPanel contentPane;
	private manageFarmer mngFarmer;
	private buyTea buyT;
	private viewReports reports;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkHome frame = new ClerkHome();
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
	public ClerkHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel sidePane = new JPanel();
		sidePane.setBackground(new Color(240, 230, 140));
		
		JPanel mainPanel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(sidePane, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(sidePane, GroupLayout.PREFERRED_SIZE, 453, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
					.addContainerGap())
		);
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 515, Short.MAX_VALUE)
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 453, Short.MAX_VALUE)
		);
		mainPanel.setLayout(gl_mainPanel);
		
		mngFarmer=new manageFarmer();
		mngFarmer.setBounds(0, 25, 690, 578);
		mainPanel.add(mngFarmer);
		
		buyT =new buyTea();
		buyT.setBounds(0, 25, 690, 578);
		mainPanel.add(buyT);
		
		reports=new viewReports();
		reports.setBounds(0, 25, 690, 578);
		mainPanel.add(reports);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 180, 140));
		panel.addMouseListener(new PanelButtonMouseAdapter(panel)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(mngFarmer);
			}
			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(210, 180, 140));
		panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(buyT);
			}
			
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 180, 140));
		panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(reports);
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
		GroupLayout gl_sidePane = new GroupLayout(sidePane);
		gl_sidePane.setHorizontalGroup(
			gl_sidePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePane.createSequentialGroup()
					.addGroup(gl_sidePane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_sidePane.createSequentialGroup()
					.addContainerGap(80, Short.MAX_VALUE)
					.addComponent(btnClose)
					.addGap(39))
		);
		gl_sidePane.setVerticalGroup(
			gl_sidePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnClose)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_2.setLayout(null);
		
		menuClicked(mngFarmer);
		
		JLabel lblViewReports = new JLabel("View Reports");
		lblViewReports.setBounds(34, 30, 136, 15);
		panel_2.add(lblViewReports);
		panel_1.setLayout(null);
		
		JLabel lblBuyTea = new JLabel("Buy Tea");
		lblBuyTea.setBounds(85, 57, 70, 15);
		panel_1.add(lblBuyTea);
		panel.setLayout(null);
		
		JLabel lblManageFarmer = new JLabel("Manage Farmer");
		lblManageFarmer.setBounds(39, 40, 151, 15);
		panel.add(lblManageFarmer);
		sidePane.setLayout(gl_sidePane);
		contentPane.setLayout(gl_contentPane);
	}
	public void menuClicked(JPanel panel) {
		mngFarmer.setVisible(false);
		reports.setVisible(false);
		buyT.setVisible(false);
		
		
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
