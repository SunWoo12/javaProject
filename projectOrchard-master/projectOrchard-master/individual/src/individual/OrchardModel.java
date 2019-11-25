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
	
	//===========개인 추가 변수=========== start
	public boolean	colMatch;	// 행 매치 여부
	public boolean	rowMatch;	// 열 매치 여부
	public int		crossX;		// 십자 매치의 X 인덱스
	public int		crossY;		// 십자 매치의 Y 인덱스
	//===========개인 추가 변수=========== end
	
	public OrchardModel()
	{
		map = new TileGrid();
		colMatch = false;
		rowMatch = false;
	}
	
	//======================매칭 열검사(우선검사), 가중치1======================
	public void CheckCol(int row, int col) // 무조건 열 우선, 가중치 1
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
	    
	    else // 원상태로 복구
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
	
	//======================매칭 행검사, 가중치2======================
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
	 	    	if(map.matchCount[x][y]==3) // 가중치 3인 인덱스값이 십자 중심
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
	 	    	if(map.matchCount[x][y]==2) // 가중치가 2인것만 돌려 놓음
	 	    	{
	 	    		map.matchCount[x][y]=0;
	 	    	}
	 	    }
	    	this.rowMatch=false;
	    }
	}
	
	//======================매칭된 블록 제거======================
	public void RemoveMatch()
	{
		//===========십자 매치 제거===========
		if(colMatch == true && rowMatch == true)
		{
			//십자 매치의 행 제거
			for(int i=1; i<map.WIDTH-1; i++)
			{
				if(map.matchCount[crossX][i]>=1)
				{
					map.grid[crossX][i].setType(TileType.Empty);
					map.matchCount[crossX][i]=0;
				}
			}
					
			//십자 매치의 열제거
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
		//===========열 제거===========
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
		
		//===========행제거===========
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
