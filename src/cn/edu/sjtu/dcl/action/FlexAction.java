package cn.edu.sjtu.dcl.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import cn.edu.sjtu.dcl.service.interfaces.TemplateService;


@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FlexAction extends BaseAction {
	
	private long tempId;
	private String content;
	private String deContent;
	private TemplateService tempService;
	
	public String redirect(){
		if(tempId!=0){
			content=tempService.getTemplateById(tempId).getContent();
			try {
				deContent=URLDecoder.decode(content,"utf-8").replaceAll("\\\"", "\\"+"\\\"").replaceAll("\\\n", "");
				System.out.println(deContent);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else 
			content="";
		tempId=0;
		return SUCCESS;
	}

	public long getTempId() {
		return tempId;
	}

	public void setTempId(long tempId) {
		this.tempId = tempId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TemplateService getTempService() {
		return tempService;
	}

	public void setTempService(TemplateService tempService) {
		this.tempService = tempService;
	}

	public String getDeContent() {
		return deContent;
	}

	public void setDeContent(String deContent) {
		this.deContent = deContent;
	}

}
