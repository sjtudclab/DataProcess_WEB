package cn.edu.sjtu.dcl.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.sjtu.dcl.dao.bean.Template;
import cn.edu.sjtu.dcl.dao.impl.DB_Template;

public class AddTemplate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2538527354671563800L;

	/**
	 * Constructor of the object.
	 */
	public AddTemplate() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try
        {
            List list = upload.parseRequest(request);// 解析传过来的内容 返回LIST
            Iterator it = list.iterator();// 对LIST进行枚举
            while (it.hasNext())
            {
                FileItem item = (FileItem) it.next();
                // 判断是文本信息还是对象（文件）
                if(item.isFormField())
                {
                    // 如果是文本信息返回TRUE
                    System.out.println("参数" + item.getFieldName() + ":"
                            + item.getString("UTF-8"));
                    // 注意输出值的时候要注意加上编码
                }
                else 
                {
                    if(item.getName() != null && !item.getName().equals(""))
                    {
                        System.out.println("上传文件的大小：" + item.getSize());
                        System.out.println("上传文件的类型：" + item.getContentType());
                        System.out.println("上传文件的名称：" + item.getName());
                        String savePath = "templates/template" + System.currentTimeMillis() + ".jcdl.xml";
                        System.out.println(request.getSession().getServletContext().getRealPath("/") + savePath);
                        // 保存在服务器硬盘中
                        //File tempFile = new File(item.getName());// 临时FILE对象
                        // 此时有路径的名字
                        // TEMPFILE主要为了获取文件的单纯文件名
                        File file = new File(request.getSession().getServletContext().getRealPath("/") + savePath);
                        //在这里可以更改名字   
                        //用subString 的方法截取后缀 就可以加上自定义名称放在第二个参数
                        // 这个FILE是真正要保存的文件
                        item.write(file);// 写入服务器

                        // 返回结果
                        request.setAttribute("upload.message", "上传成功");
                        out.write("add successfully");
                        Template mrt = new Template();
                        mrt.setName(request.getParameter("tname"));
                        mrt.setDescription(request.getParameter("tdescription"));
                        mrt.setPath(file.getCanonicalPath());
                        DB_Template dbt = new DB_Template();
                        dbt.addTemplate(mrt);
                    }
                    else
                    {
                        request.setAttribute("upload.message", "没有选择上传文件");
                    }
                }
            }
        }
        catch(FileUploadException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.setAttribute("upload.message", "上传没成功");
        }
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
