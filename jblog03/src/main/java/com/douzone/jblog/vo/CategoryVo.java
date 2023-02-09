package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String id;
	private String defaultView;
	private Long count;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public String getDefaultView() {
		return defaultView;
	}
	public void setDefaultView(String defaultView) {
		this.defaultView = defaultView;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", id=" + id + ", defaultView=" + defaultView + ", count="
				+ count + "]";
	}
}
