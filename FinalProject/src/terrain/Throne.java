package terrain;

import java.awt.image.BufferedImage;

public class Throne extends Terrain {

	private static Throne throne;
	private static int[] location;
	private boolean seized = false;
	
	private Throne(boolean standable, int[] location){
		super(true, location);
		this.location = location;
	}
	
	public static synchronized Throne getThrone(){
		if(throne == null)
			throne = new Throne(true, location);
		return throne;
	}
	
	public void setLocation(int[] coord){
		location = coord;
	}
	
	public int[] getLocation(){
		return location;
	}

	@Override
	public BufferedImage getGraphic() {
		// TODO Auto-generated method stub
		return null;
	}
}
