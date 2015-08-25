package edu.song.weixin.service;

import edu.song.weixin.jdbc.MySQLAccess;

public class TextAnalyser {

	
	public static String analyze(String content) {
		String respContent = "您说的我暂时还不能理解，试试输入 ？ 打开帮助菜单吧。";
		
		switch (content) {
		case "？":
		case "?":
			respContent = getMainMenu();
			break;
		case "1":
			respContent = "暂无";
			break;
		default:
			respContent = MySQLAccess.getTextReply(content);
			break;
		}
		
		return respContent;
	}
	
	public static String getMainMenu() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("您好，有什么可以帮忙的么？").append("\n");
        buffer.append("回复指定数字，获得相应帮助：\n");
        buffer.append("1  天气预报").append("\n");
        buffer.append("回复“?”显示此帮助菜单");  
        return buffer.toString();  
    }
	
	public static void main(String[] args) {
		System.out.println(analyze("您好"));
	}
}
