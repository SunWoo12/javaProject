package individual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButton1 = new ImageIcon("img/exit1.png");
	private ImageIcon exitButton2 = new ImageIcon("img/exit2.png");
	private ImageIcon startButton1 = new ImageIcon("img/button1.jpg");
	private ImageIcon startButton2 = new ImageIcon("img/button2.jpg");
	private ImageIcon quitButton1 = new ImageIcon("img/button3.jpg");
	private ImageIcon quitButton2 = new ImageIcon("img/button4.jpg");
	
	private Image background = new ImageIcon("img/orchard.jpg").getImage();
	private JLabel menuBar = new JLabel(new ImageIcon("img/menuBar.jpg"));
		
	private JButton exitButton = new JButton(exitButton1);
	private JButton startButton = new JButton(startButton1);
	private JButton quitButton = new JButton(quitButton1);
	
	private int width, height;
	private int mouseX, mouseY;
	
	// ======================개인 추가 변수====================== start
	private OrchardModel model;
	private TileType tileType;
	
	private ImageIcon GrapesImageIcon = new ImageIcon("img/grapes.png");
	private ImageIcon LemonImageIcon = new ImageIcon("img/lemon.png");
	private ImageIcon EmptyImageIcon = new ImageIcon("img/empty.png");
	private ImageIcon WallImageIcon = new ImageIcon("img/wall.png");
	
	private Image GrapesImage = GrapesImageIcon.getImage();
	private Image LemonImage = LemonImageIcon.getImage();
	private Image EmptyImage = EmptyImageIcon.getImage();
	private Image WallImage = WallImageIcon.getImage();
	
	private Image ScaledGrapesImage = GrapesImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH );
	private Image ScaledLemonImage = LemonImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH );
	private Image ScaledEmptyImage = EmptyImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH );
	private Image ScaledWallImage = WallImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH );
	
	private float tileX, tileY;
	private int tileStartX;
	private int tileStartY;
	private boolean gameStart;
	//======================개인 추가 변수====================== end
	
	public MainUI()
	{
		gameStart = false;
		model = new OrchardModel();
		tileStartY = 50;
		tileStartX = 20;
		//
		width = 1280;
		height = 720;
		
		setUndecorated(true);
		setTitle("OrchardPang");
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		exitButton.setBounds(1240,0,32,30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				exitButton.setIcon(exitButton2);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				exitButton.setIcon(exitButton1);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				/*Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				try {Thread.sleep(1000);}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				*/
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(210,450,400,100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				startButton.setIcon(startButton2);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				startButton.setIcon(startButton1);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
				startButton.setVisible(false);
				quitButton.setVisible(false);
				background = new ImageIcon(("img/background.jpg")).getImage();
				gameStart = true;
			}
		});
		add(startButton);
		
		quitButton.setBounds(660,450,400,100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				quitButton.setIcon(quitButton2);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				quitButton.setIcon(quitButton1);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				/*Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				*/
				System.exit(0);
			}
		});
		add(quitButton);
		
		
		exitButton.setBounds(1240,5,32,32);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				exitButton.setIcon(exitButton2);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				exitButton.setIcon(exitButton1);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				/*Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}
				*/
				System.exit(0);
			}
		});
		add(exitButton);
		
		menuBar.setBounds(0,0,1280,40);
		menuBar.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				int x= e.getXOnScreen();
				int y =e.getYOnScreen();
				setLocation(x-mouseX,y-mouseY);
			}
		});
		add(menuBar);
		
		//Music introMusic = new Music("backMusic.mp3",true);
		//introMusic.start();
		
		addMouseListener(new tileMouseListener());
	}//MainUI
	
	//======================개인 추가 함수====================== start
	//tileMouseListener
	class tileMouseListener implements MouseListener, MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			Point pt = e.getPoint();
			
			for(int i=1;i<model.map.HEIGHT-1;i++)
			{
				for(int j=1;j<model.map.WIDTH-1;j++)
				{
					if(model.map.grid[i][j].getX()+tileStartX < pt.x && pt.x < model.map.grid[i][j].getX()+tileStartX+64 &&
							model.map.grid[i][j].getY()+tileStartY < pt.y && pt.y < model.map.grid[i][j].getY()+tileStartY+64)
					{
							model.CheckCol(i,j);
							model.CheckRow(i,j);
							model.RemoveMatch();
					}
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}//tileMouseListener
	//======================개인 추가 함수====================== end
	
	public void paint(Graphics g)
	{
		screenImage = createImage(width, height);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
		
		//======================개인 추가 로직====================== start
		// 게임 시작 될시 맵 그리기 시작
		if(gameStart == true)
		{
			for(int i=0; i<model.map.HEIGHT; i++)
			{
				for(int j=0; j<model.map.WIDTH; j++)
				{
					tileType = model.map.grid[i][j].getType();
					tileX = model.map.grid[i][j].getX();
					tileY = model.map.grid[i][j].getY();
				
					if(tileType == TileType.Grapes)
					{
						g.drawImage(ScaledGrapesImage,(int)tileX+tileStartX,(int)tileY+tileStartY,null);
					}
					
					else if(tileType == TileType.Lemon)
					{
						g.drawImage(ScaledLemonImage,(int)tileX+tileStartX,(int)tileY+tileStartY,null);
					}
					
					else if(tileType == TileType.Empty)
					{
						g.drawImage(ScaledEmptyImage,(int)tileX+tileStartX,(int)tileY+tileStartY,null);
					}
					
					else if(tileType == TileType.Wall)
					{
						g.drawImage(ScaledWallImage,(int)tileX+tileStartX,(int)tileY+tileStartY,null);
					}
				} // for j
			} // for i
		} // if
	} // paint
	//======================개인 추가 로직====================== end
	
	public void screenDraw(Graphics g)
	{
		g.drawImage(background,0,0,null);
		paintComponents(g);
		this.repaint();
	}
} // MainUI
