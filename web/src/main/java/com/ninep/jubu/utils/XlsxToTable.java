package com.ninep.jubu.utils;

import com.alibaba.fastjson.JSON;
import com.ninep.jubu.exception.JubuException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.STRING;


/**
 * Created by wangjf
 * 读取excel文件封装类
 */
public class XlsxToTable {
    private static final Logger log = LoggerFactory.getLogger(XlsxToTable.class);

    private static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    private static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    private static final String POINT = ".";

    /**
     * 读取excel文件返回list
     *
     * @param path 路径
     * @return list
     */
    public static List<List<String>> readExcel(String path) {
        if (StringUtils.isEmpty(path.trim())) {
            throw new JubuException("上传文件格式错误！");
        } else {
            String keyPath = (path.split("\\?"))[0];
            String type = keyPath.substring((keyPath.split("\\?"))[0].lastIndexOf(POINT) + 1, keyPath.length());
            switch (type) {
                case OFFICE_EXCEL_2003_POSTFIX:
                    return readXls(path);
                case OFFICE_EXCEL_2010_POSTFIX:
                    return readXlsx(path);
                default:
                    throw new JubuException("上传文件格式错误！");
            }
        }
    }

    /**
     * 读取excel 返回Map
     *
     * @param path 路径
     * @return List< Map<String, String>>
     */
    public static List<Map<String, String>> readExcelMap(String path) {
        if (StringUtils.isEmpty(path.trim())) {
            throw new JubuException("上传文件格式错误！");
        } else {
            String keyPath = (path.split("\\?"))[0];
            String type = keyPath.substring(keyPath.lastIndexOf(POINT) + 1, keyPath.length());
            switch (type) {
                case OFFICE_EXCEL_2003_POSTFIX:
                    return readXlsMap(path);
                case OFFICE_EXCEL_2010_POSTFIX:
                    return readXlsxMap(path);
                default:
                    throw new JubuException("上传文件格式错误！");
            }
        }
    }

    /**
     * 读取excel文件流并解析
     *
     * @param multipartFile 流
     * @return List<Map<String, String>>
     */
    public static List<Map<String, String>> readFileExcelMap(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String filename = multipartFile.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf(POINT) + 1, filename.length());
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        switch (type) {
            case OFFICE_EXCEL_2003_POSTFIX:
                return xlsFileMap(multipartFile);
            case OFFICE_EXCEL_2010_POSTFIX:
                return xlsxFileMap(multipartFile);
            default:
                throw new JubuException("文件格式错误");
        }
    }

    /**
     * 读取配置流的方式
     *
     * @param multipartFile 流
     * @return List<Map<String,String>>
     */
    private static List<Map<String, String>> xlsxFileMap(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            XSSFWorkbook xssWorkbook = new XSSFWorkbook(inputStream);
            List<Map<String, String>> result = new ArrayList<>();
            for (Sheet sheet : xssWorkbook) {
                if (sheet == null)
                    continue;
                Row keyRow = sheet.getRow(0);
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row xssfRow = sheet.getRow(rowNum);
                    if (xssfRow == null)
                        break;
                    int minCollx = xssfRow.getFirstCellNum();
                    int maxCollx = xssfRow.getLastCellNum();
                    Map<String, String> rowMap = new HashMap<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        Cell key = keyRow.getCell(collx);
                        if (key == null) {
                            continue;
                        }
                        Cell xssfCell = xssfRow.getCell(collx);
                        //过滤掉为空的值
                        if (StringUtils.isNotEmpty(getStringVal(xssfCell).trim())) {
                            rowMap.put(key.toString().trim(), getStringVal(xssfCell).trim());
                        }
                        /*//第一列是string类型
                        if (collx == 0 && StringUtils.isNotEmpty(getStringVal(xssfCell).trim())) {
                            rowMap.put(key.toString().trim(), getStringVal(xssfCell).trim());
                        }
                        //其他列是double类型的数据
                        if (collx > 0 && xssfCell.getNumericCellValue() >= 0) {
                            rowMap.put(key.toString().trim(), xssfCell.getNumericCellValue());
                        }*/
                    }
                    result.add(rowMap);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("xlsxFileMap error:", e);
            throw new JubuException("xlsxFileMap error");
        }
    }

    /**
     * 读取流，解析
     *
     * @param multipartFile 流
     * @return List<Map<String, String>>
     */
    private static List<Map<String, String>> xlsFileMap(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            List<Map<String, String>> result = new ArrayList<>();
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null)
                    continue;
                HSSFRow keyRow = hssfSheet.getRow(0);
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    int minCollx = hssfRow.getFirstCellNum();
                    int maxCollx = hssfRow.getLastCellNum();
                    Map<String, String> rowMap = new HashMap<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        HSSFCell key = keyRow.getCell(collx);
                        if (key == null) {
                            continue;
                        }
                        HSSFCell hssfCell = hssfRow.getCell(collx);
                        //过滤掉为空的值
                        if (StringUtils.isNotEmpty(getStringVal(hssfCell).trim())) {
                            rowMap.put(key.toString().trim(), getStringVal(hssfCell).trim());
                        }
                        /*//第一列是string类型
                        if (collx == 0 && StringUtils.isNotEmpty(getStringVal(hssfCell).trim())) {
                            rowMap.put(key.toString().trim(), getStringVal(hssfCell).trim());
                        }
                        //其他列是double类型的数据
                        if (collx > 0 && hssfCell.getNumericCellValue() >= 0) {
                            rowMap.put(key.toString().trim(), hssfCell.getNumericCellValue());
                        }*/
                    }
                    result.add(rowMap);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("xlsFileMap error:", e);
            throw new JubuException("xlsFileMap error");
        }
    }

    private static List<List<String>> readXlsx(String path) {
        try {
            InputStream is = new FileInputStream(new File(path));
            XSSFWorkbook xssWorkbook = new XSSFWorkbook(is);
            List<List<String>> result = new ArrayList<>();
            for (Sheet sheet : xssWorkbook) {
                if (sheet == null)
                    continue;
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    int minCollx = row.getFirstCellNum();
                    int maxCollx = row.getLastCellNum();
                    List<String> rowList = new ArrayList<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        Cell cell = row.getCell(collx);
                        rowList.add(getStringVal(cell).trim());
                    }
                    result.add(rowList);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("readXlsx error:", e);
            throw new JubuException("readXlsx error");
        }
    }

    private static List<Map<String, String>> readXlsxMap(String path) {
        try {
            InputStream is = new FileInputStream(new File(path));
            XSSFWorkbook xssWorkbook = new XSSFWorkbook(is);
            List<Map<String, String>> result = new ArrayList<>();
            for (Sheet sheet : xssWorkbook) {
                if (sheet == null)
                    continue;
                Row keyRow = sheet.getRow(0);
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row xssfRow = sheet.getRow(rowNum);
                    if (xssfRow == null)
                        break;
                    int minCollx = xssfRow.getFirstCellNum();
                    int maxCollx = xssfRow.getLastCellNum();
                    Map<String, String> rowMap = new HashMap<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        Cell key = keyRow.getCell(collx);
                        if (key == null) {
                            continue;
                        }
                        Cell xssfCell = xssfRow.getCell(collx);
                        rowMap.put(key.toString().trim(), getStringVal(xssfCell).trim());
                    }
                    result.add(rowMap);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("readXlsxMap error:", e);
            throw new JubuException("readXlsxMap error");
        }
    }

    private static List<List<String>> readXls(String path) {
        try {
            InputStream is = new FileInputStream(new File(path));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            List<List<String>> result = new ArrayList<>();
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null)
                    continue;
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    int minCollx = hssfRow.getFirstCellNum();
                    int maxCollx = hssfRow.getLastCellNum();
                    List<String> rowList = new ArrayList<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        HSSFCell hssfCell = hssfRow.getCell(collx);
                        rowList.add(getStringVal(hssfCell).trim());
                    }
                    result.add(rowList);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("readXls error:", e);
            throw new JubuException("readXls error");
        }
    }

    private static List<Map<String, String>> readXlsMap(String path) {
        try {
            InputStream is = new FileInputStream(new File(path));
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            List<Map<String, String>> result = new ArrayList<>();
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null)
                    continue;
                HSSFRow keyRow = hssfSheet.getRow(0);
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    int minCollx = hssfRow.getFirstCellNum();
                    int maxCollx = hssfRow.getLastCellNum();
                    Map<String, String> rowMap = new HashMap<>();
                    for (int collx = minCollx; collx < maxCollx; collx++) {
                        HSSFCell key = keyRow.getCell(collx);
                        if (key == null) {
                            continue;
                        }
                        HSSFCell hssfCell = hssfRow.getCell(collx);
                        rowMap.put(key.toString().trim(), getStringVal(hssfCell).trim());
                    }
                    result.add(rowMap);
                }
            }
            return result;
        } catch (Exception e) {
            log.warn("readXlsMap error:", e);
            throw new JubuException("readXlsMap error");
        }
    }

    //类型判断
    private static String getStringVal(Cell cell) {
        if (cell == null) {
            return "";
        } else {
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                case FORMULA:
                    return cell.getCellFormula().replace("\"", "");
                case NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        Date date = cell.getDateCellValue();
                        return sdf.format(date);
                    } else {
                        cell.setCellType(STRING);
                        return cell.getStringCellValue();
                    }
                case STRING:
                    return cell.getStringCellValue();
                default:
                    return StringUtils.EMPTY;
            }
        }
    }

    public static void main(String args[]) {
        String path = "C:\\Users\\Administrator\\Desktop\\pp系数集demo.xlsx";
        List<Map<String, String>> result3 = readExcelMap(path);
        for (Map<String, String> list : result3) {
            System.out.println(JSON.toJSONString(list));
        }
    }
}
