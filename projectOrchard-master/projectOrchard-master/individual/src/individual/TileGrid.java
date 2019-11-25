package individual;

import java.util.Random;

public class TileGrid
{
	public Tile[][] grid;
	public static int WIDTH = 6;
	public static int HEIGHT = 6;
	private Random random = new Random();
	private int random_number;
	
	//===========개인 추가 변수=========== start
	public int[][] matchCount; // 과일 제거 가중치를 담고 있는 2차원 배열
	//===========개인 추가 변수=========== end
	
	public TileGrid()
	{
		// create random map
		grid = new Tile[HEIGHT][WIDTH];
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(i == 0 || j == 0 || i == grid.length-1 || j == grid.length-1)
				{
					grid[i][j] = new Tile(j*64, i*64, 64, 64, TileType.Wall);
					grid[i][j].setRow(i);
					grid[i][j].setCol(j);
				}
				
				else
				{
					random_number = random.nextInt(2);
					switch(random_number)
					{
					case 0:
					{
					grid[i][j] = new Tile(j*64, i*64, 64, 64, TileType.Grapes);
					grid[i][j].setRow(i);
					grid[i][j].setCol(j);
					break;
					}
					
					case 1:
					{
					grid[i][j] = new Tile(j*64, i*64, 64, 64, TileType.Lemon);
					grid[i][j].setRow(i);
					grid[i][j].setCol(j);
					break;
					}
					
					}//switch
				}
			}// for j
		}// for i
		
		//===========개인 추가 로직=========== start
		//initMatchCount
		matchCount = new int[HEIGHT][WIDTH];
				
		for(int i=0; i<HEIGHT; i++)
		{
			for(int j=0; j<WIDTH; j++)
			{
				matchCount[i][j] = 0;
			}
		}
		//===========개인 추가 로직=========== end
		
	}// 생성자
	
	//===========개인 추가 함수=========== start
	public void initMatchCount()
	{
		for(int i=0; i<HEIGHT; i++)
		{
			for(int j=0; j<WIDTH; j++)
			{
				matchCount[i][j] = 0;
			}
		}
	}
	//===========개인 추가 함수=========== end
	
	public Tile GetTile(int xCoord, int yCoord) 
	{
		return grid[xCoord][yCoord];
	}

}
