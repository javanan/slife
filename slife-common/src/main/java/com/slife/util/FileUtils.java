package com.slife.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by chen on 2017/9/26.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:  文件工具
 */
public class FileUtils {

    /**
     * 获取附件名称 13位时间戳+5位随机数
     *
     * @return
     */
    public static String createFileName() {
        return System.currentTimeMillis() + "" + (int) (Math.random() * 100000);
    }


    /**
     * 判断文件的扩展名
     *
     * @param suffix
     * @return
     */
    public static Boolean getImageFormat(String suffix) {
        return "TFF".equalsIgnoreCase(suffix) || "TIFF".equalsIgnoreCase(suffix) || "PNG".equalsIgnoreCase(suffix) || "GIF".equalsIgnoreCase(suffix) || "JPG".equalsIgnoreCase(suffix) || "JPEG".equalsIgnoreCase(suffix);
    }


    /**
     * 保存文件到磁盘
     *
     * @param file
     * @param savePath
     */
    public static File saveFileToDisk(MultipartFile file, String savePath) {
        File localFile = new File(savePath);
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(savePath));
            return localFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 生成缩略图
     * @param localFile
     * @param savePath
     * @return
     */
    public static Boolean createThumbnail(File localFile, String savePath) {

        File thumbnailFile = new File(savePath);
        if (!thumbnailFile.getParentFile().exists()) {
            thumbnailFile.getParentFile().mkdirs();
        }

        //生成缩略图
        if (!localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }

        try {

            Thumbnails.of(localFile).size(256, 256).outputQuality(1.0f).toFile(savePath.toString());
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 通过响应输出流实现文件下载
     *
     * @param response     响应的请求
     * @param fileLocal    文件的绝对路径 请用/斜杠表示路径
     * @param downloadName 自定义的文件名 ( 不要后缀),如果此值为空则使用时间日期做为默认的文件名
     * @param deleFile     下载完成后是否删除文件（true: 删除 , false：不删除）
     */
    public static void downLoadFile(HttpServletResponse response, String fileLocal, String downloadName, boolean deleFile){
        InputStream in=null;
        OutputStream out=null;
        try{
            if(!"".equals(downloadName)){
                downloadName=downloadName+fileLocal.substring(fileLocal.lastIndexOf("."));
            }else{
                downloadName=fileLocal.substring(fileLocal.lastIndexOf("/")+1);
            }
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(downloadName,"UTF-8"));
            in=new FileInputStream(fileLocal);
            int len=0;
            byte buffer[]=new byte[1024];
            out=response.getOutputStream();
            while((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(in!=null){
                try{
                    //
                    in.close();
                    if(deleFile){
                        Thread.sleep(1000l);
                        File file=new File(fileLocal);
                        file.delete();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
