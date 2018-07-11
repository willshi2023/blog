package cn.virtualspider.blog.util;

/**
 * 字符串处理类
 * @author virtualspider
 *
 */
public class StringUtil {
	/**
	 * 检查字符串是否为空
	 * 		如果为空或者只包含了空格等无效字符串就返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean CheckEmpty(String str) {
		if(str!=null && str.trim().length()>0) {
			return false;
		}else {
			return true;
		}
	}
}
