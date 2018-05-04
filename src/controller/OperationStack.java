package controller;

import java.util.Stack;

public class OperationStack {
	private Stack<String> stack= new Stack<String>();
	public OperationStack() {
		// TODO Auto-generated constructor stub
		stack.add("");
	}
	public void addOperation(String code){
		stack.add(code);
	}

	public String restoreOperation(){
		String ansString = "";
		if(stack.size()!=1)
			return stack.pop();
		return ansString;
	}
	public String getTop(){
		return stack.peek();
	}
}
