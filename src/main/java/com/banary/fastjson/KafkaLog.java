package com.banary.fastjson;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 业务端日志数据对象
 * 
 * @author YuanZhiQiang
 *
 */
@SuppressWarnings("serial")
public class KafkaLog<T> implements Serializable{

	private String businessType;
	private String startDate;
	private Integer pageNo;
	private List<T> data;
	
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "KafkaLog [businessType=" + businessType + ", startDate="
				+ startDate + ", pageNo=" + pageNo + ", data=" + data + "]";
	}
}
