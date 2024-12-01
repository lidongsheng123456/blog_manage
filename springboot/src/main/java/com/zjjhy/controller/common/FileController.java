package com.zjjhy.controller.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.zjjhy.common.pojo.vo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/files")
public class FileController {
    //文件上传存储路径
    //System.getProperty("user.dir") //当前用户的工作目录:  D:\develop\idea_project\my_blog
    private static final String filePath = System.getProperty("user.dir") + "/files/"; //D:\develop\idea_project\my_blog/files/

    //注入yml配置文件中的配置
    @Value("${server.port}")
    private String port; //8088

    //注入yml配置文件中的配置
    @Value("${server.host}")
    private String ip; //localhost

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        //1.文件名后缀
        String flag; //1732069777447

        //2.同步代码块用当前类的字节码文件当做锁对象;避免其他线程操作干扰创建文件名后缀
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            //当前线程暂停1ms确保每次文件名后缀不一样
            ThreadUtil.sleep(1L);
        }

        //3.获取原始文件名
        String fileName = file.getOriginalFilename();

        //4.判断filePath是否指向一个目录 不是则创建目录，否结束
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }

        //5.指定目录下写入文件字节可能会发生异常
        try {
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName); //D:\develop\idea_project\my_blog/files/1732069777447-原始文件名.png
            System.out.println(fileName + "--上传成功");
        } catch (IOException e) {
            System.out.println(fileName + "--文件上传失败");
        }

        //6.拼接访问文件的url路径
        String http = "http://" + ip + ":" + port + "/files/";

        //7.返回文件上传后的url路径
        return Result.success(http + flag + "-" + fileName); //http://localhost:8088/files/1697438073596-img.png
    }

    /**
     * 获取文件 通过servlet响应对象响应数据
     * HttpServletResponse 对象，可以直接设置响应头、响应状态码和响应体内容。
     * 这种方式适用于需要更细粒度控制响应的情况，例如文件下载、流式传输等。
     *
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}") //1732069777447-原始文件名.png
    public void imgPath(@PathVariable String flag, HttpServletResponse response) {
        //定义一个输出流对象，用于后续文件下载操作
        OutputStream os;

        try {
            //是不是空
            if (StrUtil.isNotEmpty(flag)) {
                //设置HTTP响应头，指示浏览器以附件形式下载文件，并处理文件名的UTF-8编码，防止中文乱码问题
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, StandardCharsets.UTF_8));
                //设置HTTP响应的内容类型为二进制，适合文件下载
                response.setContentType("application/octet-stream");
                //阅读指定路径下的文件的字节到字节数组中
                byte[] bytes = FileUtil.readBytes(filePath + flag);
                //获取HTTP响应的输出流对象，用于向响应中写入数据
                os = response.getOutputStream();
                //将文件内容写入HTTP响应中，实现文件下载
                os.write(bytes);
                //刷新输出流，确保所有数据被写入
                os.flush();
                //关闭输出流，释放系统资源
                os.close();
            }
        } catch (IOException e) {
            //捕获处理
            System.out.println("文件下载失败");
        }
    }

    @DeleteMapping("/{flag}")
    public void delFile(@PathVariable String flag) {
        FileUtil.del(filePath + flag);
        System.out.println("删除文件" + flag + "成功");
    }
}
