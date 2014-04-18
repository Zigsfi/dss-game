package com.dss_game;


public interface Stats {
	/*health*/
	public int getHp();
	public void setHp(int curHp);
	public void changeHp(int addMe);
	
	/*Magic*/
	public int getMp();
	public void setMp(int curMp);
	public void changeMp(int addMe);
	
	/*Attack/Strength*/
	public int getStr();
	public void setStr(int curStr);
	public void changeStr(int addMe);
	
	/*Defence*/
	public int getDef();
	public void setDef(int curDef);
	public void changeDef(int addMe);
	
	/*Evasion/Dex*/
	public int getDex();
	public void setDex(int curDex);
	public void changeDex(int addMe);
	
	
}
