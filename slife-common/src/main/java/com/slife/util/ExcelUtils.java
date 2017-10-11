package com.slife.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chen on 2017/9/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: excel 工具
 */
public class ExcelUtils {



    /**
     * excel 2003版本的导出方法 支持多个sheet导出 导出的文件后缀为.xls
     *
     * @param dataMap       要导出的数据
     * @param excelFilePath excel文件的存放位置
     * @param fileName      excel文件名字
     *
     * @return
     */
    public static String exportXlsExcel(Map<String,List<String[]>> dataMap, String excelFilePath, String fileName){
        FileOutputStream fout=null;
        String fileLocal="";
        try{
            File file=new File(excelFilePath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb=new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet=null;
            List<String[]> dataList=null;
            HSSFCellStyle style=wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Set<String> keyTitle=dataMap.keySet();
            for(String title : keyTitle){
                sheet=wb.createSheet(title);
                dataList=dataMap.get(title);
                for(int i=0;null!=dataList&&i<dataList.size();i++){
                    // 生成第一行
                    HSSFRow row=sheet.createRow(i);
                    String[] arr=dataList.get(i);
                    for(int j=0;null!=arr&&j<arr.length;j++){
                        // 给这一行的第一列赋值
                        HSSFCell cell=row.createCell(j);
                        cell.setCellValue(arr[j]);
                        cell.setCellStyle(style);
                        if(i==0){
                            HSSFCellStyle tempStyle=style;
                            tempStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                            tempStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
                            cell.setCellStyle(tempStyle);
                        }
                    }
                }
            }
            // 第六步，将文件存到指定位置
            fileLocal=excelFilePath+"/"+fileName+".xls";
            fout=new FileOutputStream(fileLocal);
            wb.write(fout);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fout != null) {
                    fout.close();
                }
            }catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileLocal;
    }
}
