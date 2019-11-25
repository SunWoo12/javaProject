package individual;

public enum TileType
{
	Grapes("grapes"), Lemon("lemon"), Empty("empty"), Wall("wall");

	public String textureName;
	
	TileType(String textureName)
	{
		this.textureName = textureName;
	}

	public String getTextureName()
	{
		return textureName;
	}

	public void setTextureName(String textureName)
	{
		this.textureName = textureName;
	}
	
	
}
