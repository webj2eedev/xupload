<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    </head>
    <body>
        <form method="post" action="./mvc/recvfile"
              enctype="multipart/form-data">
            <label>文本域:</label>
            <input type="text" name="textfield"/>
            <br/>

            <label>单文件:</label>
            <input type="file" name="single"/>
            <br/>

            <label>多文件:</label>
            <input type="file" name="multi" multiple/>
            <br/>

            <label>没文件:</label>
            <input type="file" name="empty"/>
            <br/>

            <button type="submit">提交</button>
        </form>
    </body>
</html>
