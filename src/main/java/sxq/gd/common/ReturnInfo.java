package sxq.gd.common;

public class ReturnInfo {
	private boolean flag;
	private String msg;
	public ReturnInfo(){}
	
	public ReturnInfo(boolean flag,String msg){
		this.msg = msg;
		this.flag = flag;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
