package com.zkq.Controller;

import com.zkq.utils.UploadResultHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Upload {

    @RequestMapping("/fileUpload")
    @ResponseBody
    public UploadResultHandler<String> fileUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
        //得到文件保存的路径
        String path=request.getServletContext().getRealPath("/upload");
        byte [] buffer=new byte[400];
        int len=0;
        Map<String,String> map=new HashMap<>();
        if(file!=null){
            InputStream is= file.getInputStream();
            OutputStream os=new FileOutputStream(new File(path,file.getOriginalFilename()));
            while ((len=is.read())!=-1){
                os.write(buffer,0,len);
            }
            map.put("src","upload/"+file.getOriginalFilename());//upload
            map.put("title",file.getOriginalFilename());
            is.close();
            os.close();
            return new UploadResultHandler(0,"",map);
        }else{
            return new UploadResultHandler(1,"",map);
        }
    }
}
