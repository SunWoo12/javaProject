package individual;

import java.util.Random;

public class TileGrid
{
	public Tile[][] grid;
	public static int WIDTH = 6;
	public static int HEIGHT = 6;
	private Random random = new Random();
	private int random_number;
	
	//===========���� �߰� ����=========== start
	public int[][] matchCount; // ���� ���� ����ġ�� ��� �ִ� 2���� �迭
	//===========���� �߰� ����=========== end
	
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
		
		//===========���� �߰� ����=========== start
		//initMatchCount
		matchCount = new int[HEIGHT][WIDTH];
				
		for(int i=0; i<HEIGHT; i++)
		{
			for(int j=0; j<WIDTH; j++)
			{
				matchCount[i][j] = 0;
			}
		}
		//===========���� �߰� ����=========== end
		
	}// ������
	
	//===========���� �߰� �Լ�=========== start
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
	//===========���� �߰� �Լ�=========== end
	
	public Tile GetTile(int xCoord, int yCoord) 
	{
		return grid[xCoord][yCoord];
	}

}
