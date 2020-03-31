package com.cuong.phonestore.model;

public class Ward {

	private String name;
	private String type;
	private String slug;
	private String nameWithType;
	private String path;
	private String pathWithType;
	private String code;
	private String parentCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getNameWithType() {
		return nameWithType;
	}
	public void setNameWithType(String nameWithType) {
		this.nameWithType = nameWithType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPathWithType() {
		return pathWithType;
	}
	public void setPathWithType(String pathWithType) {
		this.pathWithType = pathWithType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public Ward(String name, String type, String slug, String nameWithType, String path, String pathWithType,
			String code, String parentCode) {
		super();
		this.name = name;
		this.type = type;
		this.slug = slug;
		this.nameWithType = nameWithType;
		this.path = path;
		this.pathWithType = pathWithType;
		this.code = code;
		this.parentCode = parentCode;
	}
	public Ward() {
		super();
	}
	
}
