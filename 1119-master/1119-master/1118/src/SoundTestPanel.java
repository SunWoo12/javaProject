
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SoundTestPanel extends JFrame{


	private static final long serialVersionUID = 1L;
	
	private JButton musicButton = new JButton("ON");
	private JButton soundeffectButton = new JButton("EFFECT");
	private boolean isMusic = false;
	private long clipTime = 0;
	

	

	

	
	public SoundTestPanel(){
		
		
		setUndecorated(true);
		setTitle("STP");
		setSize(800,800);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.black);
		setLayout(null);
		File f1 = new File("C:\\Users\\jrpon\\Desktop\\1118\\src\\res\\backMusic.wav");
		File f2 = new File("C:\\Users\\jrpon\\Desktop\\1118\\src\\res\\click.wav");
		SoundTestThread mu1 = new SoundTestThread(f1);
		Thread t1= new Thread(mu1) ;
		
		musicButton.setBounds(50,50,100,100);
		musicButton.setBorderPainted(false);
		musicButton.setContentAreaFilled(false);
		musicButton.setFocusPainted(false);
		musicButton.addMouseListener(new MouseAdapter() {
		@SuppressWarnings("deprecation")
		@Override
			public void mousePressed(MouseEvent e) {
			
			
				if (isMusic==false&&t1.isAlive()==true) {
				mu1.getClip().setMicrosecondPosition(clipTime);
				mu1.play();
				isMusic=true;
				} // ���� �����
				
				else if(isMusic==false) {
				isMusic=true;
				mu1.play();} //���� �ƿ� ó�� ����
				
				else if (isMusic==true) {
				clipTime= mu1.getClip().getMicrosecondPosition();
				mu1.stop();
				isMusic = false; //���� �Ͻ�����
				}
			}
		});
		add(musicButton);//������ �����带 �������� ���� �Ͻ����� ����� ��� ����
		
		soundeffectButton.setBounds(50,200,100,100);
		soundeffectButton.setBorderPainted(false);
		soundeffectButton.setContentAreaFilled(false);
		soundeffectButton.setFocusPainted(false);
		soundeffectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SoundTestThread mu = new SoundTestThread(f2);
				mu.play();
			}
		});
		add(soundeffectButton);
		
		}//ȿ������ ������ �̺�Ʈ �߻��� �Ҵ��ؼ� �ڵ����� ���� �ǰ� ��
	
}

