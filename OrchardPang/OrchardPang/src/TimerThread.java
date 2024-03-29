import javax.swing.JLabel;
import java.awt.*;

public class TimerThread extends JLabel implements Runnable{

	Thread myThread;
	int nStart1,nStart2;
	int nSleepTime1, nSleepTime2;
	
	public TimerThread() {
		
	} // constructor
	
	public TimerThread(String arg) {
		super(arg);
		initInstanceDatas();
		
	} // parameter constructor
	
	private void initInstanceDatas() {
	
		myThread = null;
		nStart1 = 60;
		nStart2 = 99;
		nSleepTime1 = 1000;
		nSleepTime2 = 10;
	}
	
	public void setStart(int arg1,int arg2) 		{nStart1 = arg1; nStart2 = arg2;}
	public void setSleepTime(int arg1, int arg2) 	{nSleepTime1 = arg1; nSleepTime2 = arg2;}
	public int getStart1() 				{return nStart1;}
	public int getStart2()				{return nStart2;}
	public int getSleepTime1() 			{return nSleepTime1;}
	public int getSleepTime2()			{return nSleepTime2;}

	public void start() {
		if(myThread == null) myThread = new Thread(this);
		myThread.start();
	}
	
	public void stop() {
		if(myThread != null) myThread.stop();
//		myThread.interrupt();
		
	}
	private void interrupt() {
		
		// TODO Auto-generated method stub
		
	}
	public void suspend() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=nStart1 ; i>=0; i--)
		{
			for(int j=nStart2;j>=0;j--)
			{
				setText(Integer.toString(i) + ":"+ Integer.toString(j));
				try {
					myThread.sleep(nSleepTime2);
//					myThread.suspend();
				}
				catch(Exception e) {}
			}

			
		}
		
		
		
	} // run()

	
	
	

} // TimerThread class
