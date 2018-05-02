package model;

public class ConnectionInfo {
	//连接线，图形的位置，头连接还是尾连接
	private MyLine line;
	private int location;
	private String ConnectionPart;
	public ConnectionInfo(MyLine line,int location,String part) {
		this.line=line;
		this.location=location;
		this.ConnectionPart=part;
	}
	public MyLine getLine() {
		return line;
	}
	public void setLine(MyLine line) {
		this.line = line;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getConnectionPart() {
		return ConnectionPart;
	}
	public void setConnectionPart(String connectionPart) {
		ConnectionPart = connectionPart;
	}
}
