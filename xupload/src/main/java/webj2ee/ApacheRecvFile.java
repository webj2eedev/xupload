package webj2ee;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ApacheRecvFile", urlPatterns = {"/apacherecvfile"})
public class ApacheRecvFile extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 检测是否为文件上传请求
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("不是文件上传请求!");
        }


        // 配置上传文件缓存策略
        // 1. SizeThreshold: 缓存文件大小阈值
        //     a.上传文件小于此阈值，暂存于内存；
        //     b.上传文件大于此阈值，暂存于磁盘；
        // 2. Repository: 上传文件暂存于磁盘时的目录；
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(512); // 默认值:10240
        factory.setRepository(new File("d:/temp"));

        // 文件上传相关参数
        // 1. FileSizeMax: 限制请求中单个文件大小
        // 2. SizeMax: 限制请求的总大小
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024); // 默认：-1，无限制
        upload.setSizeMax(2048); // 默认：-1，无限制

        // 分析请求
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                // 普通字段
                String fieldName = item.getFieldName(); //字段名
                String fileContent = item.getString("UTF-8");//字段值

                System.out.format(
                        "field:%s, content:%s\n",
                        fieldName, fileContent);
            } else {
                // 文件字段
                String fieldName = item.getFieldName(); // 字段名
                String fileName = item.getName(); // 文件名
                String fileType = item.getContentType();
                String fileContent = new String(
                        IOUtils.toByteArray(
                                item.getInputStream()
                        ),
                        Charset.forName("UTF-8")
                );

                System.out.format(
                        "field:%s, fileName:%s, type:%s, content:%s\n",
                        fieldName, fileName, fileType, fileContent);
            }
        }
    }
}
