package individual;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class OrchardModel
{
	public static float tileLength = 64;
	public TileGrid	map;
	
	//===========���� �߰� ����=========== start
	public boolean	colMatch;	// �� ��ġ ����
	public boolean	rowMatch;	// �� ��ġ ����
	public int		crossX;		// ���� ��ġ�� X �ε���
	public int		crossY;		// ���� ��ġ�� Y �ε���
	//===========���� �߰� ����=========== end
	
	public OrchardModel()
	{
		map = new TileGrid();
		colMatch = false;
		rowMatch = false;
	}
	
	//======================��Ī ���˻�(�켱�˻�), ����ġ1======================
	public void CheckCol(int row, int col) // ������ �� �켱, ����ġ 1
	{
	    TileType currentType = map.grid[row][col].getType();
	    ArrayList<Tile> matchedTiles = new ArrayList<Tile>();
	    
	    map.matchCount[row][col]++;
	    matchedTiles.add(map.grid[row][col]);

	    if(row < map.HEIGHT-2 && map.grid[row + 1][col].getType() == currentType)
	    { // 1 up
	    	map.matchCount[row+1][col]++;
	    	matchedTiles.add(map.grid[row + 1][col]);
	        
	        if(row < map.HEIGHT-3 && map.grid[row + 2][col].getType() == currentType) // 2 up
	        {
	        	matchedTiles.add(map.grid[row + 2][col]);
	        	map.matchCount[row+2][col]++;
	        }
	    }

	    if(row > 1 && map.grid[row - 1][col].getType() == currentType)
	    { // 1 down
	    	map.matchCount[row-1][col]++;
	        matchedTiles.add(map.grid[row - 1][col]);
	        
	        if(row > 2 && map.grid[row - 2][col].getType() == currentType) // 2 down
	        {
	        	map.matchCount[row-2][col]++;
	            matchedTiles.add(map.grid[row - 2][col]);
	        }
	    }
	    
	    if(matchedTiles.size() >= 3)
	    {
	    	this.colMatch=true;
	    }
	    
	    else // �����·� ����
	    {
		    for(Tile tile : matchedTiles)
		    {
		    	int x = tile.getRow();              
		    	int y = tile.getCol();
		    	map.matchCount[x][y] = 0;
		    }
	    	this.colMatch=false;
	    }
	}
	
	//======================��Ī ��˻�, ����ġ2======================
	public void CheckRow(int row, int col)
	{
	    TileType currentType = map.grid[row][col].getType();
	    ArrayList<Tile> matchedTiles = new ArrayList<Tile>();
	    
	    map.matchCount[row][col] = map.matchCount[row][col]+2;
	    matchedTiles.add(map.grid[row][col]);
	    
	    
	    if(col < map.WIDTH-2 && map.grid[row][col+1].getType() == currentType)
	    { // 1 right
	        map.matchCount[row][col+1] = map.matchCount[row][col+1]+2;
	        matchedTiles.add(map.grid[row][col+1]);
	        
	        if(col < map.WIDTH-3 && map.grid[row][col+2].getType() == currentType) // 2 right
	        {
	        	map.matchCount[row][col+2] = map.matchCount[row][col+2]+2;
	        	matchedTiles.add(map.grid[row][col+2]);
	        }
	    }

	    if(col > 1 && map.grid[row][col-1].getType() == currentType)
	    { // 1 left
	    	map.matchCount[row][col-1] = map.matchCount[row][col-1]+2;
	        matchedTiles.add(map.grid[row][col-1]);
	        
	        if(col > 2 && map.grid[row][col-2].getType() == currentType) // 2 left
	        {
	            matchedTiles.add(map.grid[row][col-2]);
	        	map.matchCount[row][col-2] = map.matchCount[row][col-2]+2;
	        }
	    }
	    
	    if(matchedTiles.size() >= 3)
	    {
	    	for(Tile tile : matchedTiles)
	 	    {
	    		int x = tile.getRow();              
	 	    	int y = tile.getCol();
	 	    	if(map.matchCount[x][y]==3) // ����ġ 3�� �ε������� ���� �߽�
	 	    	{
	 	    		crossX = x;
	 	    		crossY = y;
	 	    	}
	 	    }
	    	this.rowMatch=true;
	    }
	    else
	    {
	    	for(Tile tile : matchedTiles)
	 	    {
	    		int x = tile.getRow();              
	 	    	int y = tile.getCol();
	 	    	if(map.matchCount[x][y]==2) // ����ġ�� 2�ΰ͸� ���� ����
	 	    	{
	 	    		map.matchCount[x][y]=0;
	 	    	}
	 	    }
	    	this.rowMatch=false;
	    }
	}
	
	//======================��Ī�� ��� ����======================
	public void RemoveMatch()
	{
		//===========���� ��ġ ����===========
		if(colMatch == true && rowMatch == true)
		{
			//���� ��ġ�� �� ����
			for(int i=1; i<map.WIDTH-1; i++)
			{
				if(map.matchCount[crossX][i]>=1)
				{
					map.grid[crossX][i].setType(TileType.Empty);
					map.matchCount[crossX][i]=0;
				}
			}
					
			//���� ��ġ�� ������
			for(int j=1; j<map.HEIGHT-1; j++)
			{
				if(map.matchCount[j][crossY]>=1)
				{
					map.grid[j][crossY].setType(TileType.Empty);
					map.matchCount[j][crossY]=0;
				}
			}
					
			this.colMatch = false;
			this.rowMatch = false;
			return;
		}
		//===========�� ����===========
		else if(colMatch == true) 
		{
			for(int i=1; i<map.HEIGHT-1; i++)
			{
				for(int j=1; j<map.WIDTH-1; j++)
				{
					if(map.matchCount[i][j]>=1)
					{
						map.grid[i][j].setType(TileType.Empty);
						map.matchCount[i][j]=0;
					}
				}
			}
			this.colMatch = false;
			return;
		}//else if
		
		//===========������===========
		else if(rowMatch == true)
		{
			for(int i=1; i<map.HEIGHT-1; i++)
			{
				for(int j=1; j<map.WIDTH-1; j++)
				{
					if(map.matchCount[i][j]>=1)
					{
						map.grid[i][j].setType(TileType.Empty);
						map.matchCount[i][j]=0;
					}
				}
			}
			this.rowMatch = false;
			return;
		}//else if
	}//removwMatch
	
}//OrchardModel
