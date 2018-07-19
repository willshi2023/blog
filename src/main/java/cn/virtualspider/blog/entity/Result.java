package cn.virtualspider.blog.entity;

import cn.virtualspider.blog.myenum.PromptMessageEnum;

/**
 * 返回结果
 * @author Administrator
 *
 */
public class Result {
	private Boolean status;//状态
	private String msg;//返回信息
	private Object data;//返回结果
	public Result() {}
	public Result(PromptMessageEnum em) {
		status = em.getStatus();
		msg = em.getPromptMessage();
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
