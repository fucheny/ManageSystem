/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.entity;

/**
 *
 * @author Administrator
 */
public class Clazz {
	private int clazzNo;
	private String clazzName;

	public Clazz() {
	}

	public int getClazzNo() {
		return clazzNo;
	}

	public void setClazzNo(int clazzNo) {
		this.clazzNo = clazzNo;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	@Override
	public String toString() {
		return "Clazz [clazzNo=" + clazzNo + ", clazzName=" + clazzName + "]";
	}

}
