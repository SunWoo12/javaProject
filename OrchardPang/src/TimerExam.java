import javax.swing.JFrame;

public class TimerExam {

	public static void main(String[] args) {
		JFrame frame = new JFrame("OrchardPang");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TimerPanel primary = new TimerPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);

	} // main()

} //TimerExam class
