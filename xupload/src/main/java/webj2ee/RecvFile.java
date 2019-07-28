package webj2ee;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

@MultipartConfig(
        fileSizeThreshold = 512, // 超过这个值，就暂存到磁盘
        location = "d:/temp", // 暂存区
        maxFileSize = 1024, // 请求中单个文件的最大尺寸
        maxRequestSize = 2048 // 请求的最大尺寸
)
@WebServlet(name = "RecvFile", urlPatterns = {"/recvfile"})
public class RecvFile extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        Collection<Part> parts = request.getParts();
        Iterator<Part> itor = parts.iterator();
        while (itor.hasNext()) {
            Part part = itor.next();
            String fieldName = part.getName(); // 字段名
            String fileName = part.getSubmittedFileName(); // 文件名(注：此接口Servlet 3.1才有)
            String fileType = part.getContentType();
            String fileContent = new String(
                    IOUtils.toByteArray(
                            part.getInputStream()
                    ),
                    Charset.forName("UTF-8")
            );
            System.out.format(
                    "field:%s, fileName:%s, type:%s, content:%s\n",
                    fieldName, fileName, fileType, fileContent);
        }
    }
}
