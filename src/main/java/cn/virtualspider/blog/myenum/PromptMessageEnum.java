package cn.virtualspider.blog.myenum;

/**
 * 提示信息枚举类
 * @author Administrator
 *
 */
public enum PromptMessageEnum {
	GET_ARTICLE_LIST_SUCCESS(true,"获取文章列表成功"),
	GET_USER_DETAIL_SUCCESS(true,"获取用户详情成功"),
	GET_USER_DETAIL_FAILURE(false,"获取用户详情失败"),
	GET_ARTICLE_DETAIL_SUCCESS(true,"获取文章详情成功");
	private Boolean status;//状态，成功为true，失败为false
	private String promptMessage;//提示信息
	private PromptMessageEnum(Boolean status,String promptMessage) {
		this.status = status;
		this.promptMessage = promptMessage;
	}
	public Boolean getStatus() {
		return status;
	}
	public String getPromptMessage() {
		return promptMessage;
	}
	
}
