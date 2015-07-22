package cn.edu.sjtu.dcl.proxy;

public interface IMsgParser {
	public IMessageModel Parse(String rawMsg);
}
