package cn.byau.homework.entity;

public class Homework {
	private int no;
	private String title;
	private String kecheng;
	private String oktime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKecheng() {
		return kecheng;
	}

	public void setKecheng(String kecheng) {
		this.kecheng = kecheng;
	}

	public String getOktime() {
		return oktime;
	}

	public void setOktime(String oktime) {
		this.oktime = oktime;
	}

	@Override
	public String toString() {
		return "no = " + no + " title = " + title + " kecheng = " + kecheng + " oktime = " + oktime;
	}
}
