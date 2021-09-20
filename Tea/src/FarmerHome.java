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

public class FarmerHome extends JFrame {

	private JPanel contentPane;

	private farmerActivity fActivity;
	private myKgs kgs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmerHome frame = new FarmerHome();
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
	public FarmerHome() {}
	public FarmerHome(int n) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 585);
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
					.addComponent(sidePane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(sidePane, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		fActivity=new farmerActivity(n);
		fActivity.setBounds(0, 12, 644, 512);
		mainPanel.add(fActivity);
		
		kgs=new myKgs(n);
		kgs.setBounds(0, 12, 644, 524);
		mainPanel.add(kgs);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 180, 140));
		panel_2.addMouseListener(new PanelButtonMouseAdapter(panel_2)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(fActivity);
			}
			
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(210, 180, 140));
		panel_3.addMouseListener(new PanelButtonMouseAdapter(panel_3)
		{
			public void mouseClicked(MouseEvent e) {
				menuClicked(kgs);
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
					.addContainerGap()
					.addGroup(gl_sidePane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_sidePane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_sidePane.createSequentialGroup()
							.addComponent(btnClose)
							.addGap(63))
						.addGroup(gl_sidePane.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_sidePane.setVerticalGroup(
			gl_sidePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClose)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(null);
		
		JLabel lblMyKgs = new JLabel("My Kgs");
		lblMyKgs.setBounds(51, 57, 70, 15);
		panel_3.add(lblMyKgs);
		panel_2.setLayout(null);
		
		JLabel lblActivities = new JLabel("Activities");
		lblActivities.setBounds(41, 54, 70, 15);
		panel_2.add(lblActivities);
		sidePane.setLayout(gl_sidePane);
		mainPanel.setLayout(null);
		contentPane.setLayout(gl_contentPane);
		
		menuClicked(kgs);
	}
	public void menuClicked(JPanel panel) {
		kgs.setVisible(false);
		fActivity.setVisible(false);
		
		
		
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
