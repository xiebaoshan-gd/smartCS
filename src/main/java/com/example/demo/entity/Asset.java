package com.example.demo.entity;

/*
	�ʲ���ʵ�壬��Ӧasset��
*/
public class Asset {
	private int id; // ��Ӧasset���id�ֶ�
	private String name; // ��Ӧasset���name�ֶ�
	private String num; // ��Ӧasset���num�ֶ�
	private String rfid; // ��Ӧasset���rfid�ֶ�
	private int state; // ��Ӧasset���state�ֶ�
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", num=" + num
				+ ", rfid=" + rfid + ", state=" + state + "]";
	}

}
