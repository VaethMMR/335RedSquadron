package ai;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Unit;

//Class AI: Creates strategies implemented for the 
//AI team of units. Also tracks the AI's team by adding
//and removing units

public class AI {
	private List<Unit> team;
	private Strategy s;
	private Model model;
	
	public AI(List<Unit> team, Model model){ 
		this.team = team;
		this.model = model;
		}
	
	public void setStrategy(Strategy s){this.s = s;}
	
	public Strategy getStrategy(){return s;}
	
	public Model getModel(){return model;}
	
	public List<Unit> getTeam(){return team;}
	
	public boolean remove(Unit unit){
		if(!team.contains(unit))
			return false;
		team.remove(unit);
		return true;
	}
	
	public void useStrategy(Unit u){
			if(model.getGame().getMap().getInRangePlayerUnits(u).size() > 0)
				this.setStrategy(new AttackPlayer());
			else
				this.setStrategy(new Roam());
			s.strategy(u, model);
			if(u.getCurrentHp() < 1){
				model.getGame().getMap().removeUnit(u);
				this.remove(u);
			}
	}
		
}
