package webj2ee;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

@RestController
public class SpringMVCApacheCommonsUploadController {
    @RequestMapping(value = "/recvfile")
    public void handleUpload(HttpServletRequest request) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        Iterator<String> fileNames = multipartRequest.getFileNames();
        while (fileNames.hasNext()) {
            String fieldName = fileNames.next();
            List<MultipartFile> files = multipartRequest.getFiles(fieldName);
            for (MultipartFile file : files) {

                String fileName = file.getOriginalFilename();
                String fileType = file.getContentType();
                String fileContent = new String(
                        IOUtils.toByteArray(
                                file.getInputStream()
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
