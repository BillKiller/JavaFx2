package model;

public class ConnectionInfo {
	//�����ߣ�ͼ�ε�λ�ã�ͷ���ӻ���β����
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
