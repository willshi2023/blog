package cn.virtualspider.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 专门为ueditor设置的类
 * @author virtualspider
 *
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {
	@Value("${custom.resourceLocation}")
	private String resourceLocation;
	
	@Value("${custom.tmpLocation}")
	private String tmpLocation;
	/**
	 * 设置服务器富文本图片上传的限制信息
	 * @return
	 */
	@RequestMapping(value="/getImgUploadConfig",method=RequestMethod.GET)
	@ResponseBody
	public String getImgUploadConfig(HttpServletRequest request) {
		String config = "{\r\n" + 
				"    \"imageActionName\": \"uploadimage\",\r\n" + 
				"    \"imageFieldName\": \"upfile\",\r\n" + 
				"    \"imageMaxSize\": 2048000,\r\n" + 
				"    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\r\n" + 
				"    \"imageCompressEnable\": true, \r\n" + 
				"    \"imageCompressBorder\": 1600, \r\n" + 
				"    \"imageInsertAlign\": \"none\", \r\n" + 
				"    \"imageUrlPrefix\": \"\",\r\n" + 
				"    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", \r\n" + 
				"    \"scrawlActionName\": \"uploadscrawl\", \r\n" + 
				"    \"scrawlFieldName\": \"upfile\", \r\n" + 
				"    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", \r\n" + 
				"    \"scrawlMaxSize\": 2048000, \r\n" + 
				"    \"scrawlUrlPrefix\": \"\",\r\n" + 
				"    \"scrawlInsertAlign\": \"none\",\r\n" + 
				"    \"snapscreenActionName\": \"uploadimage\",\r\n" + 
				"    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\r\n" + 
				"    \"snapscreenUrlPrefix\": \"\",\r\n" + 
				"    \"snapscreenInsertAlign\": \"none\",\r\n" + 
				"    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\r\n" + 
				"    \"catcherActionName\": \"catchimage\",\r\n" + 
				"    \"catcherFieldName\": \"source\", \r\n" + 
				"    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\", \r\n" + 
				"    \"catcherUrlPrefix\": \"\", \r\n" + 
				"    \"catcherMaxSize\": 2048000, \r\n" + 
				"    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \r\n" + 
				"    \"videoActionName\": \"uploadvideo\", \r\n" + 
				"    \"videoFieldName\": \"upfile\", \r\n" + 
				"    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\", \r\n" + 
				"    \"videoUrlPrefix\": \"\",\r\n" + 
				"    \"videoMaxSize\": 102400000,\r\n" + 
				"    \"videoAllowFiles\": [\r\n" + 
				"        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\r\n" + 
				"        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    \"fileActionName\": \"uploadfile\", \r\n" + 
				"    \"fileFieldName\": \"upfile\",\r\n" + 
				"    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\", \r\n" + 
				"    \"fileUrlPrefix\": \"\", \r\n" + 
				"    \"fileMaxSize\": 51200000,\r\n" + 
				"    \"fileAllowFiles\": [\r\n" + 
				"        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\r\n" + 
				"        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\r\n" + 
				"        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\r\n" + 
				"        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\r\n" + 
				"        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\r\n" + 
				"    ], \r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    \"imageManagerActionName\": \"listimage\", \r\n" + 
				"    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\",\r\n" + 
				"    \"imageManagerListSize\": 20, \r\n" + 
				"    \"imageManagerUrlPrefix\": \"\",\r\n" + 
				"    \"imageManagerInsertAlign\": \"none\", \r\n" + 
				"    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \r\n" + 
				"\r\n" + 
				"    \r\n" + 
				"    \"fileManagerActionName\": \"listfile\", \r\n" + 
				"    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\",\r\n" + 
				"    \"fileManagerUrlPrefix\": \"\", \r\n" + 
				"    \"fileManagerListSize\": 20, \r\n" + 
				"    \"fileManagerAllowFiles\": [\r\n" + 
				"        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\r\n" + 
				"        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\r\n" + 
				"        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\r\n" + 
				"        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\r\n" + 
				"        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\r\n" + 
				"    ] \r\n" + 
				"\r\n" + 
				"}";
		return config;
	}
	
	/**
	 * 上传图片
	 * @return
	 */
	@RequestMapping(value="/imgUpload",method=RequestMethod.POST)
	@ResponseBody
	public String imgUpload(@RequestParam(value = "upfile")MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String path = tmpLocation+resourceLocation+fileName;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(path);
        // 检测是否存在目录
        File parentFile = dest.getParentFile();
		if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String config =
        "{\n" +
        "            \"state\": \"SUCCESS\",\n" +
        "                \"url\": \"http://localhost/ueditor/showImg?filename="+path+"\",\n" +
        "                \"title\": \"path\",\n" +
        "                \"original\": \"path\"\n" +
        "        }";
        return config;
	}
	
	/**
	 * 在线预览图片
	 * @param filename
	 */
	@RequestMapping(value="/showImg",method=RequestMethod.GET)
	public void showImg(@RequestParam("filename")String filename,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/jpeg");
		try {
			FileInputStream fis = new FileInputStream(filename);
			OutputStream os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024*1024];
			while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
            os.close();
            fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
