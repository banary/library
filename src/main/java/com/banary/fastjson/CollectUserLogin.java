package com.banary.fastjson;

/**
 * 登录日志数据实体类
 * @author XS
 *
 */

public class CollectUserLogin {
	
	private String frameworkSource;
	private String ip;
	private String userId;
	private String time;
	private String businessType;
	
	public String getFrameworkSource() {
		return frameworkSource;
	}
	public void setFrameworkSource(String frameworkSource) {
		this.frameworkSource = frameworkSource;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Override
	public String toString() {
		return "CollectUserLogin [frameworkSource=" + frameworkSource + ", ip="
				+ ip + ", userId=" + userId + ", time=" + time
				+ ", businessType=" + businessType + "]";
	}
}
