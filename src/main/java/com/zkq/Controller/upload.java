package com.zkq.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class upload {

    public String fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
        //得到文件保存的路径
        String path=request.getServletContext().getRealPath("/upload");
        byte [] buffer=new byte[400];
        int len=0;
        InputStream is= file.getInputStream();
        OutputStream os=new FileOutputStream(new File(path,file.getOriginalFilename()));
        while ((len=is.read())!=-1){
            os.write(buffer,0,len);
        }
        is.close();
        os.close();
        //使用返回string方式进行存储转发
        return "jsp/main.jsp";
    }
}
