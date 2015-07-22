package cn.edu.sjtu.dcl.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import cn.edu.sjtu.dcl.dao.bean.Job;
import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.service.interfaces.TemplateService;
import cn.edu.sjtu.dcl.util.Page;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TemplateAction extends BaseAction {

	private long id;
	private String name;
	private String description;
	private String path;
	private long developerId;
	private Date uploadTime;
	private int searchKey;
	private String content;
	private String searchValue;
	private int pageSize = 10;
	private int pno;
	private TemplateService tempService;

	public String getTemplateById() {
		if (id > 0) {
			Template temp = this.getTempService().getTemplateById(id);
			this.getRequest().setAttribute("temp", temp);
			this.getRequest().setAttribute("developer", temp.getDeveloper());
			return SUCCESS;
		} else
			return FAILURE;
	}
	
	public String modifyTempInfo() {
		if (id > 0) {
			Template temp = this.getTempService().getTemplateById(id);
			this.getRequest().setAttribute("temp", temp);
			return SUCCESS;
		} else
			return FAILURE;
	}
	
	public String tempTrans(){
//		this.getRequest().setAttribute("path", path);
		this.getRequest().setAttribute("content", content);
		return SUCCESS;
	}

	public String getTemplateList() {
		if (pno <= 0) {
			this.setPno(1);
		}
		Page page = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		if (this.getSearchValue() != null && !this.getSearchValue().equals("")) {
			System.out.println("in map");
			map.put(searchKey, this.getSearchValue());

			this.getRequest().setAttribute(
					"searchString",
					"&searchKey=" + this.getSearchKey() + "&searchValue="
							+ this.getSearchValue());
			this.setSearchValue(null);
			page = this.getTempService().getPage(pno, pageSize, map);
		} else {
			page = this.getTempService().getPage(pno, pageSize, null);
		}
		this.getRequest().setAttribute("page", page);
		List tempList = page.getList();
		this.getRequest().setAttribute("list", tempList);
		return SUCCESS;
	}

	public String createTemplate() {
		uploadTemplateFile();
		tempService.createTemp(generateTemp());
		return SUCCESS;
	}
	
	public String updateTemplate() {
		tempService.updateTemp(modifyInfo(id));
		return SUCCESS;
	}

	public String deleteTemplate() {
		if (tempService.deleteTemp(id, this.getSessionUser())) {
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	public String getPersonalTemplate() {
		if (pno <= 0) {
			this.setPno(1);
		}
		Page page = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(2, this.getSessionUser().getName());
		if (this.getSearchValue() != null && !this.getSearchValue().equals("")) {
			System.out.println("in map");
			map.put(searchKey, this.getSearchValue());

			this.getRequest().setAttribute(
					"searchString",
					"&searchKey=" + this.getSearchKey() + "&searchValue="
							+ this.getSearchValue());
			this.setSearchValue(null);

		}
		page = this.getTempService().getPage(pno, pageSize, map);
		this.getRequest().setAttribute("page", page);
		List tempList = page.getList();
		this.getRequest().setAttribute("list", tempList);
		return SUCCESS;
	}

	public void uploadTemplateFile() {

	}

	public Template generateTemp() {
		Template temp = new Template();
		temp.setDescription(description);
		temp.setDeveloper(getSessionUser());
		temp.setName(name);
		try {
			System.out.println(content);
			temp.setContent(URLEncoder.encode(content,"utf-8").replaceAll("[+]", "%20"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setUploadTime(new Date());
		return temp;
	}
	
	public Template modifyInfo(long id) {
		Template temp = tempService.getTemplateById(id);
		temp.setDescription(description);
		temp.setDeveloper(getSessionUser());
		temp.setName(name);
		try {
			System.out.println(content);
			temp.setContent(URLEncoder.encode(content,"utf-8").replaceAll("[+]", "%20"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public TemplateService getTempService() {
		return tempService;
	}

	public void setTempService(TemplateService tempService) {
		this.tempService = tempService;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(int searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
