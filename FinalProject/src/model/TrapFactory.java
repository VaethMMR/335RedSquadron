package model;

public class TrapFactory {
	private Trap trap;
	
	public TrapFactory(){
	}
	
	public Trap makeTrap(String name, int level){
		if (name == "Mine"){
			trap = new Trap(name, level);
			return trap;
		}
		if (name == "Barrier"){
			trap = new Trap(name, level);
			return trap;
		}else{
			return null;
		}
	}
}
