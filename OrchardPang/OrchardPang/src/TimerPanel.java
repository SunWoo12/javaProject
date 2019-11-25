import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TimerPanel extends JPanel {

	private JPanel leftPanel,rightPanel;
	private JLabel lblTimer;
	private TimerThread lblMark;
	private JButton btnStart;
	private JButton btnPause;
	boolean flag = true;
	
	//private JLabel lblMark;
	
	private ButtonListener buttonL;
	
	public TimerPanel() {
		
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.white);
		setLayout(null);
		
		buttonL = new ButtonListener();
		
		leftPanel = new JPanel();
		leftPanel.setBounds(10,10,400,580);
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.cyan);
		add(leftPanel);
		
		rightPanel = new JPanel();
		rightPanel.setBounds(420, 10, 170, 580);
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.pink);
		add(rightPanel);
		
		
		lblTimer = new JLabel("Timer");
		lblTimer.setBounds(20, 40, 130, 50);
		lblTimer.setFont(new Font("Verdana",Font.BOLD,30));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblTimer);
		
		btnStart = new JButton("START");
		btnStart.addActionListener(buttonL);
		btnStart.setBounds(40, 150, 90, 20);
		btnStart.setBackground(Color.lightGray);
		btnStart.setForeground(Color.white);
		btnStart.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(btnStart);
		
		btnPause = new JButton("PAUSE");
		btnPause.addActionListener(buttonL);
		btnPause.setBounds(40, 180, 90, 20);
		btnPause.setBackground(Color.LIGHT_GRAY);
		btnPause.setForeground(Color.white);
		btnPause.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(btnPause);
		
		
		lblMark = new TimerThread("00:00");
		lblMark.setBounds(20,100,130,40);
		lblMark.setFont(new Font("Verdana",Font.ITALIC,30));
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(lblMark);
		
		
		
		
	}	// constructor
	
	// 1. listener class
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			if(obj == btnStart) {
				if(flag == true)
				{
					lblMark.setStart(60,99);
					lblMark.setSleepTime(1000, 10);
					lblMark.start();
				}
				else
				{
					lblMark.start();
					
				}
			}
			
			if(obj == btnPause) {
				flag = false;
				lblMark.stop();
				
				
			}
		}
		
	} // ButtonListener class
} // TimerPanel class
