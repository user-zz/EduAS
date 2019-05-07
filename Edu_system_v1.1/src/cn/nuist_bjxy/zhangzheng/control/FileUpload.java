package cn.nuist_bjxy.zhangzheng.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.nuist_bjxy.zhangzheng.bean.Filebean;
import cn.nuist_bjxy.zhangzheng.bean.Homeworkbean;
import cn.nuist_bjxy.zhangzheng.dao.FileDao;
import cn.nuist_bjxy.zhangzheng.dao.HomeworkDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String send_img = "";
    String fid = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置文件上传基本路径
        String savePath = this.getServletContext().getRealPath("/uploadFiles");
        //String savePath = request.getContextPath()+"/WEB-INF/uploadFiles";
        System.out.println("基本路径："+savePath);
        
      //设置临时文件路径
       String tempPath = this.getServletContext().getRealPath("/tempFiles");
       // String tempPath = request.getContextPath()+"/WEB-INF/tempFiles";
        System.out.println("临时路径："+tempPath);
        
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
 
        //定义异常消息
        String errorMessage = "";
        //创建file items工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置缓冲区大小
        factory.setSizeThreshold(1024 * 1024);
        //设置临时文件路径
        factory.setRepository(tempFile);
        
    //    System.out.println("临时文件路径"+factory.getRepository()); //控制台测试
        
        //创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
  //      System.out.println("开始监听上传进度");
        //监听文件上传进度
        ProgressListener progressListener = new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("正在读取文件： " + pItems);
                if (pContentLength == -1) {
                    System.out.println("已读取： " + pBytesRead + " 剩余0");
                } else {
                    System.out.println("文件总大小：" + pContentLength + " 已读取：" + pBytesRead);
                }
            }
        };
        
        upload.setProgressListener(progressListener);
 
   //     System.out.println("正在监听》。。");
        
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            //按照传统方式获取数据
            return;
        }
 
        //设置上传单个文件的大小的最大值，目前是设置为1024*1024*30字节，也就是30MB
        upload.setFileSizeMax(1024 * 1024 * 30);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为100MB
        upload.setSizeMax(1024 * 1024 * 100);
 
        System.out.println("开始解析上传数据");
        try {
            //使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            
            while (iterator.hasNext()) {
            	
                FileItem item = iterator.next();
                System.out.println("测试item值："+item);
                
                //判断jsp提交过来的是不是文件
                if (item.isFormField()) {
                    errorMessage = "请提交文件！";
                    System.out.println(errorMessage);
                    break;
                } else {
                    //文件名
                    String fileName = item.getName();
                    if (fileName == null || fileName.trim() == "") {
                        System.out.println("文件名为空！");
                        
                    }
                    
                    //处理不同浏览器提交的文件名带路径问题
                   fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    
                  //文件大小
                    Long fileSize = item.getSize();
                    
                    //文件扩展名
                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //判断扩展名是否合法
                    if (!validExtension(fileExtension)) {
                        errorMessage = "上传文件非法！";
                        System.out.println(errorMessage);
                        item.delete();
                        break;
                    }
                    System.out.println("开始获取文件输入流");
                    //获得文件输入流
                    InputStream in = item.getInputStream();
                    //得到保存文件的名称
                    String saveFileName = createFileName(fileName);
                    System.out.println("保存文件名称："+saveFileName);
                    //得到文件保存路径
                    String realFilePath = createRealFilePath(savePath, saveFileName);
                    System.out.println("保存文件路径："+realFilePath);
                    //创建文件输出流
                    FileOutputStream out = new FileOutputStream(realFilePath);
                    //创建缓冲区
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        //写文件
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除临时文件 TODO
                    item.delete();
                    
                    //将上传文件信息保存到文件表中 TODO
                    
                    Filebean filebean = new Filebean();
                    Homeworkbean hwbean = new Homeworkbean();
                   
                    //获取随机编号
                    Random random = new Random();  
                    int max = 1000000; //设置最大值
                    int min = 1; //设置最小值
                    int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
                    
                    //获取文件id,普通文件id默认为0，作业文件id为1
                    fid = (String)request.getSession().getAttribute("fid");
                    
                    String stumajor= (String)request.getSession().getAttribute("stumajor");
                    
                    String stuclass= (String)request.getSession().getAttribute("stuclass");
                    
                    String stuname = (String)request.getSession().getAttribute("stuname");
                    
                    send_img = request.getParameter("filetype_img");  //判断是聊天页传过来的图片数据
                    
                    System.out.println("filetypeImg:"+send_img); //测试节点
                    
                    if(send_img.equals("") || send_img == null || !send_img.equals("T")) {
                    	request.getSession().setAttribute("filetype_img", "");
                    	request.getSession().setAttribute("path", "");
                    }else {
                    	request.getSession().setAttribute("filetype_img", send_img);  //放入session中
                    	
                    	Calendar today = Calendar.getInstance();
                        String year = String.valueOf(today.get(Calendar.YEAR));
                        String month = String.valueOf(today.get(Calendar.MONTH) + 1);
                        String imgPath = year + File.separator + month + File.separator+saveFileName;
                        
                    	request.getSession().setAttribute("path", imgPath); //把图片路径放入session
                    }
                    
                    if (stumajor == null || stumajor.equals("")) {
						stumajor =  (String)request.getSession().getAttribute("input_stumajor");
					}
                    
                    if (stuclass == null || stuclass.equals("")) {
                    	stuclass =  (String)request.getSession().getAttribute("input_stuclass");
					}
                    
                    if(fid == null || fid.equals("")) {
                    	fid = "0";   //共享文件id=0
                    }
                    
                    filebean.setFno(randomNumber);
                    filebean.setFname(fileName);
                    filebean.setFsize(fileSize);
                    filebean.setFusername((String)request.getSession().getAttribute("username")); //文件上传者
                    filebean.setFpath(realFilePath);
                    filebean.setFtype( (String)request.getSession().getAttribute("filetype"));
                    filebean.setFid(fid);
                    
                    //设置作业的相关数据
                    hwbean.setHw_no(randomNumber);
                    hwbean.setHw_stumajor(stumajor);
                    hwbean.setHw_stuclass(stuclass);
                    hwbean.setHw_stuname(stuname);
                    
                    System.out.println("开始将文件存入数据库");
                    if(send_img.equals("") || send_img == null) {
                    	FileDao.add(filebean);
                    }else {
                    	//待拓展
                    	//测试
                    	System.out.println("这是发送的图片信息...,不存库");
                    }

                    if(fid.equals("1")) {
                    	
                    	System.out.println("将文件存入作业表");
                    	HomeworkDao.hwAdd(hwbean);
             
                    }else {
                    	//待拓展
                    }
                    
                    System.out.println("文件上传成功！"); //测试
                    iterator.remove();
                    break; 
                }             
            }
            
            
       //     errorMessage = "文件上传成功！";
 
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "单个文件超出最大值！！！");
            request.getRequestDispatcher("jsp/fileupload.jsp").forward(request, response);
            return;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("jsp/fileupload.jsp").forward(request, response);
            return;
        } catch (FileUploadException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "文件上传失败！！！");
            request.getRequestDispatcher("jsp/fileupload.jsp").forward(request, response);
            return;
        }finally {
        	//System.out.println(errorMessage.equals(""));
            if (!errorMessage.equals("")) {
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("jsp/file_upload_error.jsp").forward(request, response);
            } else {
	            	if(send_img.equals("") || send_img == null) {
	            		response.sendRedirect("ListUploadFiles");
	            	}else {
	            		
	            		System.out.println("发送给消息控制器进行处理...");
	            		
	            		response.sendRedirect("InputInformation");
	            		
	            	}   
            }
        }
        /*
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("jsp/fileupload.jsp").forward(request, response);
        */
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	/* 
	 * validExtension方法
	 * 判断上传文件是否为指定类型
	 * 
	 * */
	private boolean validExtension(String fileExtension) {
		
	//	System.out.println("开始执行validExtension");
		
        String[] exts = {"ZIP","zip","RAR","rar","jpg","JPG","png","PNG","gif","GIF", "txt","TXT", "doc","DOC","docx","DOCX","xls","XLS","xlsx","XLSX", "pdf","PDF"};  
        System.out.println("fileExtension值:"+fileExtension);
        for (int i = 0; i < exts.length; i++) {
            if (fileExtension.equals(exts[i])) {
                return true;
            }    
        }
        return false;
    }
 
	/*
	 * createFileName方法
	 * 创建随机文件名
	 * @param fileName
	 * 
	 * */
    private String createFileName(String fileName) {
    //	System.out.println("开始执行createFileName");
        return UUID.randomUUID().toString() + "_" + fileName;
    }
 
    /**
     * 根据基本路径和文件名称生成真实文件路径，基本路径\\年\\月\\fileName
     *
     * @param basePath
     * @param fileName
     * @return
     */
	
    private String createRealFilePath(String basePath, String fileName) {
    //	System.out.println("开始执行createRealFilePath");
        Calendar today = Calendar.getInstance();
        String year = String.valueOf(today.get(Calendar.YEAR));
        String month = String.valueOf(today.get(Calendar.MONTH) + 1);
 
 
        String upPath = basePath + File.separator + year + File.separator + month + File.separator;
       // upPath = "G:"+File.separator;  //测试路径,可以使上传的文件到指定地址下，这里为G盘
    //    System.out.println("upPath的值:"+upPath);
        File uploadFolder = new File(upPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
 
        String realFilePath = upPath + fileName;
     //   System.out.println("realFilePath:"+realFilePath);
        return realFilePath;
    }
	
}
