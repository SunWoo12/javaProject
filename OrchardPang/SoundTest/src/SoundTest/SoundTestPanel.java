package SoundTest;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SoundTestPanel extends JFrame{


	private static final long serialVersionUID = 1L;
	
	private JButton musicButton = new JButton("ON");
	private JButton soundeffectButton = new JButton("EFFECT");
	private boolean isMusic = false;

	
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
		Thread mu1 = new SoundTestThread("backMusic.mp3",false);
		
		
		musicButton.setBounds(50,50,100,100);
		musicButton.setBorderPainted(false);
		musicButton.setContentAreaFilled(false);
		musicButton.setFocusPainted(false);
		musicButton.addMouseListener(new MouseAdapter() {
		@Override
			public void mousePressed(MouseEvent e) {
				
				if (isMusic==false&&mu1.isInterrupted()==true) {
				mu1.resume();
				mu1.start();
				} // 음악 재시작
				
				else if(isMusic==false) {
				isMusic =true;
				mu1.start();} //음악 아예 처음 시작
				
				else if (isMusic==true) {
				mu1.interrupt();
				mu1.suspend();
				isMusic = false; //음악 일시정지
				}
			}
		});
		add(musicButton);//음악은 쓰레드를 전역으로 빼서 일시정지 재시작 기능 구현
		
		soundeffectButton.setBounds(50,200,100,100);
		soundeffectButton.setBorderPainted(false);
		soundeffectButton.setContentAreaFilled(false);
		soundeffectButton.setFocusPainted(false);
		soundeffectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SoundTestThread mu = new SoundTestThread("click.mp3",false);
				mu.start();
			}
		});
		add(soundeffectButton);
		
		}//효과음은 쓰레드 이벤트 발생시 할당해서 자동으로 해제 되게 함
	
}
