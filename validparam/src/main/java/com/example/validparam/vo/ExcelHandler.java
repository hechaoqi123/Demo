package com.example.validparam.vo;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hcq
 * @date 2020/5/23 18:07
 */
public class ExcelHandler {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) throws Exception {
       File baseDir=new File("D:\\test\\机构数据\\组织机构");
        for( File  file :  baseDir.listFiles()){
            System.out.println("当前正在处理的文件夹为："+file.getName());
            for(File  subFile :  file.listFiles()){
                Map<String ,Object> map=new HashMap<>(16);
                Workbook box = getWorkbok(subFile);
                Sheet sheet = box.getSheet("年度考核");
                //姓名
                String userName=subFile.getName().replace(".xls","");
                map.put("userName",userName);
                //所在单位
                String unit=box.getSheet("职务变动").getRow(2).getCell(3).getStringCellValue();
                map.put("unit",unit);
                //职工号
                String id=box.getSheet("补充信息").getRow(1).getCell(3).getStringCellValue();
                map.put("id",id);
                List<Map> list=new ArrayList<>();
                for(int i=2;i<sheet.getLastRowNum();i++){
                    //考核日期
                    String date=sheet.getRow(i).getCell(1).getStringCellValue();
                    //考核类别
                    String type=sheet.getRow(i).getCell(2).getStringCellValue();;
                    //考核结果
                    String result=sheet.getRow(i).getCell(3).getStringCellValue();
                    //考核单位名称
                    String unitName=sheet.getRow(i).getCell(4).getStringCellValue();
                    //归档情况
                    String pigeonhole=sheet.getRow(i).getCell(6).getStringCellValue();
                    Map<String,String> record=new HashMap<>();
                    record.put("date",date);
                    record.put("type",type);
                    record.put("result",result);
                    record.put("unitName",unitName);
                    record.put("pigeonhole",pigeonhole);
                    list.add(record);
                }
                map.put("records",list);
                handler(map);
            }
        }
    }

    private static void handler(Map<String,Object> row) throws Exception {
        try {
            Workbook workBok = getWorkbok(new File("D:\\test\\机构数据\\航运在职人员名单.xlsx"));
            //年度考核
            Sheet sheet = workBok.getSheet("年度考核");
            for(int i=1;i<=sheet.getLastRowNum();i++){
                String id=sheet.getRow(i).getCell(1).getStringCellValue();
                String userName=sheet.getRow(i).getCell(2).getStringCellValue();
                if(id.equals(row.get(id)) || userName.equals(row.get("userName"))){
                    List<Map<String,String>> records= (List) row.get("records");
                    if(records!=null && !records.isEmpty()){
                        for(int j=1;j<=records.size();j++){
                            System.out.println("正在处理"+userName+"的信息！");
                            Row newRow = sheet.createRow(i + j);
                            //序号
                            newRow.createCell(0).setCellValue("");
                            //职工号
                            newRow.createCell(1).setCellValue(id);
                            //姓名
                            newRow.createCell(2).setCellValue(userName);
                            //所在单位
                            newRow.createCell(3).setCellValue(String.valueOf(row.get("unit")));
                            Map<String,String> record=records.get(j-1);
                            //考核日期
                            newRow.createCell(4).setCellValue(record.get("date"));
                            //考核类别
                            newRow.createCell(5).setCellValue(record.get("type"));
                            //考核结果
                            newRow.createCell(6).setCellValue(record.get("result"));
                            //考核单位名称
                            newRow.createCell(7).setCellValue(record.get("unitName"));
                            //备注
                            newRow.createCell(8).setCellValue("SYSTEM");
                            //归档情况
                            newRow.createCell(9).setCellValue(record.get("pigeonhole"));
                            workBok.write(new FileOutputStream("D:\\test\\机构数据\\航运在职人员名单.xlsx"));
                        }
                        System.out.println(userName+"信息处理完毕！");
                        return ;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("异常记录:"+row);
        }

    }

    /**
     * 判断Excel的版本,获取Workbook
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
