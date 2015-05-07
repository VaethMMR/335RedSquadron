package model;

public class TrapFactory {
	private Trap trap;
	
	public TrapFactory(){
	}
	
	/**
	 * This method creates a trap that has different effects depending on its name
	 * 
	 * @param name
	 *            The name of the trap
	 * @param level
	 *            The level of the trap. All are level 1 by default
	 * @return Trap
	 * 			  returns the created trap           
	 */
	public Trap makeTrap(String name, int level){
		if (name == "Mine"){
			trap = new Trap(name, level);
			trap.setCost(10);
			trap.setDamage(50);
			return trap;
		}
		if (name == "Barrier"){
			trap = new Trap(name, level);
			trap.setCost(10);
			trap.setDamage(0);
			return trap;
		}else{
			return null;
		}
	}
}
