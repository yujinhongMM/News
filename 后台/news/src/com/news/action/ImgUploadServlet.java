package com.news.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;



/**
 * Description 图片上传
 * @author YJH
 * @date 2018.11.21
 * @category 图片上传
 */

@WebServlet("/ImgUploadServlet")
public class ImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		HashMap<String, Object> map=new HashMap<>();
		map=addImg(request);
		//初始化jsonObject对象，并添加数据
		JSONObject jsonObject =new JSONObject(); 
		jsonObject.put("map",map);
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		
    }
    
    public HashMap<String, Object> addImg(HttpServletRequest request) throws IOException{
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload load=new ServletFileUpload(factory);
		HashMap<String, Object> map=new HashMap<>();
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list=load.parseRequest(request);
			for(FileItem fileItem :list){
				if(fileItem.isFormField()){//字符信息
					
					String name=fileItem.getFieldName();
					String value=fileItem.getString("UTF-8");
					map.put(name, value);
				}else{//文件信息
					String fileName=fileItem.getName();
					
					if(fileName!=null&&!"".equals(fileName)){
						String str = (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date());
						String picture="abc.jpg";
					    String[] s=picture.split("\\.");
						str=str+"."+s[1];
						fileName=str;
						System.out.println(fileName);
						
						map.put("url","http://localhost:8080/news/img/"+fileName);
						//System.out.println(map.get("url"));
						String path=this.getServletContext().getRealPath("\\img");
						OutputStream output=new FileOutputStream(path+File.separator+fileName);
						IOUtils.copy(fileItem.getInputStream(), output);
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		map.put("code",200);
        map.put("msg","上传成功");
		return map;
	}

}
