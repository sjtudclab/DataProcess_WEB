package cn.edu.sjtu.dcl.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.edu.sjtu.dcl.dao.bean.History;
import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.service.interfaces.HistoryService;
import cn.edu.sjtu.dcl.service.interfaces.TemplateService;
import cn.edu.sjtu.dcl.util.Page;


public class HistoryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String path;
	private long userId;
	private Date useTime;
	private int searchKey;
	private String searchValue;
	private int pageSize = 10;
	private int pno;
	private HistoryService historyService;

	public String getHistoryById() {
		if (id > 0) {
			History history = this.getHistoryService().getHistoryById(id);
			this.getRequest().setAttribute("history", history);
			this.getRequest().setAttribute("user", history.getUser());
			return SUCCESS;
		} else
			return FAILURE;
	}

	public String getHistoryList() {
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
			page = this.getHistoryService().getPage(pno, pageSize, map);
		} else {
			page = this.getHistoryService().getPage(pno, pageSize, null);
		}
		this.getRequest().setAttribute("page", page);
		List tempList = page.getList();
		this.getRequest().setAttribute("list", tempList);
		return SUCCESS;
	}

	public String deleteHistory() {
		if (historyService.deleteHistory(id, this.getSessionUser())) {
			return SUCCESS;
		} else {
			return FAILURE;
		}
	}

	public String getPersonalHistory() {
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
		page = this.getHistoryService().getPage(pno, pageSize, map);
		this.getRequest().setAttribute("page", page);
		List tempList = page.getList();
		this.getRequest().setAttribute("list", tempList);
		return SUCCESS;
	}

	public void uploadTemplateFile() {

	}

	public History generateHistory() {
		History history = new History();
		history.setUser(getSessionUser());
		history.setName(name);
		history.setUsedTime(new Date());
		return history;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

}
