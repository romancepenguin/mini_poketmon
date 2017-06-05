package dugeonRPG;
//import java.util.Random;

public class Fight_Cul {
	public Fight_Cul(){

	}
	public int getRan_Po() {
		//Random random=new Random();
		return (int)(Math.random()*13+1);
	}
	public int getRan_level() {
		//Random random=new Random();
		return (int)(Math.random()*10+1);
	}
	public boolean get_Poketmon(int con) {
		if(con>=(int)(Math.random()*100+1)){ return true;}
		else return false;
	}
	public int getAttack_point(int level,int base_ap) {
		return level*base_ap;
	}
	public int getSpeed_point(int level,int base_speed) {
		return level*base_speed;
	}
	public int getShield_point(int level,int base_sp) {
		return level*base_sp;
	}
	public int getHp_point(int level,int base_hp) {
		return level*base_hp;
	}
}
