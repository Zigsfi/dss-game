package com.dss_game;


public interface Stats {
	/*health*/
	public int getHp();
	public void setHp(int curHp);
	public void changeHp(int addHp);
	
	/*Magic*/
	public int getMp();
	public void setMp(int curMp);
	public void changeMp(int addMp);
	
	/*Attack/Strength*/
	public int getStr();
	public void setStr(int curStr);
	public void changeStr(int addStr);
	
	/*Defence*/
	public int getDef();
	public void setDef(int curDef);
	public void changeDef(int addDef);
	
	/*Evasion/Dex*/
	public int getDex();
	public void setDex(int curDex);
	public void changeDex(int addDex);
	
	/*intellect*/
	public int getInt();
	public void setInt(int curInt);
	public void changeInt(int addInt);
	
	
}
