package webj2ee;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class SendMultiPartRequest {
    public static void main(String[] args) {
        // 构造连接池
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(4);
        cm.setDefaultMaxPerRoute(2);

        // CloseableHttpClient
        CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();

        // 构造 MultiPart 请求实体
        MultipartEntityBuilder mpEntityBuilder = MultipartEntityBuilder.create();
        mpEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 注意字符集
        mpEntityBuilder.setCharset(Charset.forName("UTF-8"));
        mpEntityBuilder.addTextBody("text", "这是普通文本字段", ContentType.TEXT_PLAIN.withCharset("UTF-8")); // MultiPart 普通字段
        mpEntityBuilder.addBinaryBody("file1", new File("D:/杰洛斯.txt")); // Mulitpart 文件字段
        mpEntityBuilder.addBinaryBody("file2", new File("D:/琦玉1.txt"));
        HttpEntity entity = mpEntityBuilder.build();

        // 构造 POST 请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/xupload/apacherecvfile");
        httpPost.setEntity(entity);

        // 发送请求
        try {
            CloseableHttpResponse closeableResponse = httpclient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
