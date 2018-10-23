package com.banary.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author eden
 * @Date 2018/5/3 上午11:28
 */
public class POIMain {

    public static void main(String[] args) throws Exception {
        final String inFilePath = args[0];
        System.out.println("*************************");
        System.out.println("输入文件为：" + inFilePath);
        System.out.println("*************************");
        if(StringUtils.isBlank(inFilePath)){
            System.err.println("前方高能！待处理的文件不存在！");
            return;
        }
        File file = new File(inFilePath);
        if(!file.exists()){
            System.err.println("前方高能！待处理的文件不存在！");
            return;
        }
        final String outFilePath = file.getParent() + "/" + file.getName().substring(0, file.getName().indexOf(".")) + "_工资统计.xlsx";
        System.out.println("*************************");
        System.out.println("生成文件为：" + outFilePath);
        System.out.println("*************************");
        Map<String, Object> list = read(inFilePath);
        Map<String, Prod> prodMap = (Map<String, Prod>)list.get("0");
        List<Work> workList = (List<Work>)list.get("1");
        System.err.println("本次共读取数据：" + workList.size() + "条");
        //分组
        Map<String, List<Work>> workMap = workList.stream().collect(Collectors.groupingBy(Work::getName));
        write(outFilePath, workMap, prodMap);
    }

    public static void write(String outFilePath, Map<String, List<Work>> workMap, Map<String, Prod> prodMap) throws Exception{
        FileOutputStream fos = new FileOutputStream(new File(outFilePath));
        Workbook wb = new XSSFWorkbook();
        workMap.entrySet().stream().forEach(entry-> {
            if(StringUtils.isBlank(entry.getKey())){
                System.err.println("前方高能！！！存在没名字共计" + entry.getValue().size() + "条");
                return;
            }
            Sheet sheet = wb.createSheet(entry.getKey());
            CreationHelper helper = wb.getCreationHelper();
            CellStyle dateStyle = wb.createCellStyle();
            dateStyle.setDataFormat(helper.createDataFormat().getFormat("e\"年\"m\"月\"d\"日\" aaaa"));
            dateStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

            CellStyle cellStyle = wb.createCellStyle();
            //下边框
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            //左边框
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            //上边框
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            Map<Date, List<Work>> map = entry.getValue().stream().collect(Collectors.groupingBy(Work::getDate));
            List<Map.Entry<Date, List<Work>>> dateListEntry = new ArrayList<>(map.entrySet());
            Collections.sort(dateListEntry, (entry1, entry2)->entry1.getKey().compareTo(entry2.getKey()));
            int i=0;
            for(Map.Entry<Date, List<Work>> dayEntry : dateListEntry){
                //时间
                Row dateRow = sheet.createRow(i);
                Cell dateCell = dateRow.createCell(0);
                dateCell.setCellValue(dayEntry.getKey());
                dateCell.setCellStyle(dateStyle);
                // 合并 起始行, 终止行, 起始列, 终止列
                CellRangeAddress cra = new CellRangeAddress(i, i, 0, 10);
                sheet.addMergedRegion(cra);
                //标题
                Row titleRow = sheet.createRow(i+1);
                createTitleRow(titleRow, cellStyle);
                //内容
                int j=0;
                BigDecimal total  = BigDecimal.ZERO;
                for(Work work:dayEntry.getValue()){
                    try {
                        total = total.add(new BigDecimal(work.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }catch (Exception e){
                        continue;
                    }
                    Row contentRow = sheet.createRow(i+2+j);
                    createContentRow(contentRow, work, prodMap, cellStyle);
                    j++;
                }
                Row totalRow = sheet.createRow(i+2+j);
                createTotalRow(totalRow, sheet, total, cellStyle);

                Row creatorRow = sheet.createRow(i+3+j);
                createRow(creatorRow);
                i = i+5+j;
            }
        });

        wb.write(fos);
        fos.close();
    }

    private static void createTotalRow(Row row, Sheet sheet, BigDecimal total, CellStyle cellStyle){
        Cell cell0 = row.createCell(0);
        cell0.setCellStyle(cellStyle);
        Cell cell1 = row.createCell(1);
        cell1.setCellStyle(cellStyle);
        Cell cell2 = row.createCell(2);
        cell2.setCellStyle(cellStyle);
        Cell cell3 = row.createCell(3);
        cell3.setCellStyle(cellStyle);
        Cell cell4 = row.createCell(4);
        cell4.setCellStyle(cellStyle);
        Cell cell5 = row.createCell(5);
        cell5.setCellStyle(cellStyle);
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("合计：");
        cell6.setCellStyle(cellStyle);
        Cell cell7 = row.createCell(7);
        cell7.setCellStyle(cellStyle);
        CellRangeAddress cra1 = new CellRangeAddress(row.getRowNum(), row.getRowNum(), 6, 7);
        sheet.addMergedRegion(cra1);
        Cell cell8 =row.createCell(8);
        cell8.setCellValue(total.toString());
        cell8.setCellStyle(cellStyle);
        Cell cell9 = row.createCell(9);
        cell9.setCellStyle(cellStyle);
        Cell cell10 = row.createCell(10);
        cell10.setCellStyle(cellStyle);
    }

    private static void createContentRow(Row row, Work work, Map<String, Prod> prodMap, CellStyle cellStyle){
        Cell cell0 = row.createCell(0);
        cell0.setCellValue(work.getNo());
        cell0.setCellStyle(cellStyle);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        if(prodMap.get(work.getNo())!=null){
            cell1.setCellValue(prodMap.get(work.getNo()).getName());
            cell2.setCellValue(prodMap.get(work.getNo()).getLegal());
        }
        cell1.setCellStyle(cellStyle);
        cell2.setCellStyle(cellStyle);
        Cell cell3 = row.createCell(3);
        cell3.setCellValue(work.getProcess());
        cell3.setCellStyle(cellStyle);
        Cell cell4 = row.createCell(4);
        try{
            BigDecimal cell4Value = new BigDecimal(work.getWeight()).setScale(1, BigDecimal.ROUND_HALF_UP);
            cell4.setCellValue(cell4Value.toString());
        }catch (Exception e){
            System.err.println("报错了！重量为：" + work.getWeight());
        }

        cell4.setCellStyle(cellStyle);
        Cell cell5 = row.createCell(5);
        try{
            BigDecimal cell5Value = new BigDecimal(work.getThWeight()).setScale(3, BigDecimal.ROUND_HALF_UP);
            cell5.setCellValue(cell5Value.toString());
        }catch (Exception e){
            System.err.println("报错了！千金重为：" + work.getThWeight());
        }

        cell5.setCellStyle(cellStyle);
        Cell cell6 = row.createCell(6);
        try{
            BigDecimal cell6Value = new BigDecimal(work.getNumber()).setScale(3, BigDecimal.ROUND_HALF_UP);
            cell6.setCellValue(cell6Value.toString());
        }catch (Exception e){
            System.err.println("报错了！数量为：" + work.getNumber());
        }
        cell6.setCellStyle(cellStyle);
        Cell cell7 = row.createCell(7);
        try{
            BigDecimal cell7Value = new BigDecimal(work.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
            cell7.setCellValue(cell7Value.toString());
        }catch (Exception e){
            System.err.println("报错了！单机为：" + work.getPrice());
        }
        cell7.setCellStyle(cellStyle);
        Cell cell8 = row.createCell(8);
        cell8.setCellValue(new BigDecimal(work.getAmount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        cell8.setCellStyle(cellStyle);
        Cell cell9 = row.createCell(9);
        cell9.setCellValue(work.getName());
        cell9.setCellStyle(cellStyle);
        Cell cell10 = row.createCell(10);
        cell10.setCellValue("");
        cell10.setCellStyle(cellStyle);
    }

    private static void createTitleRow(Row row, CellStyle cellStyle){
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("编号");
        cell0.setCellStyle(cellStyle);
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("名称");
        cell1.setCellStyle(cellStyle);
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("规格");
        cell2.setCellStyle(cellStyle);
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("工序");
        cell3.setCellStyle(cellStyle);
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("重量kg");
        cell4.setCellStyle(cellStyle);
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("千件重");
        cell5.setCellStyle(cellStyle);
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("千件");
        cell6.setCellStyle(cellStyle);
        Cell cell7 = row.createCell(7);
        cell7.setCellValue("单价");
        cell7.setCellStyle(cellStyle);
        Cell cell8 = row.createCell(8);
        cell8.setCellValue("金额");
        cell8.setCellStyle(cellStyle);
        Cell cell9 = row.createCell(9);
        cell9.setCellValue("姓名");
        cell9.setCellStyle(cellStyle);
        Cell cell10 = row.createCell(10);
        cell10.setCellValue("批 次 号");
        cell10.setCellStyle(cellStyle);
    }

    private static void createRow(Row row){
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("制单人：");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("胡巍");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("品保：");
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("核审：");
    }

    public static Map<String, Object> read(String filePath) throws InvalidFormatException, IOException {
        Map<String, Object> map = new HashMap<>();
        InputStream ins = new FileInputStream(new File(filePath));
        Workbook wb = WorkbookFactory.create(ins);
        ins.close();

        Sheet sheet0 = wb.getSheetAt(0);
        Map<String, Prod> prodMap = new HashMap<>();

        for(int i=1; i<=sheet0.getLastRowNum(); i++){
            Row row = sheet0.getRow(i);
            Prod prod = new Prod(row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
            prodMap.put(row.getCell(0).getStringCellValue(), prod);
        }
        map.put("0", prodMap);

        //3.得到Excel工作表对象
        Sheet sheet = wb.getSheetAt(1);
        List<Work> list = new ArrayList<>();
        for(int i=1; i<=sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            Date date = row.getCell(0).getDateCellValue();
            if(date != null){
                Work work = new Work();
                work.setDate(date);
                work.setName(row.getCell(1).getStringCellValue().trim());
                work.setNo(row.getCell(2).getStringCellValue());

                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                work.setWeight(row.getCell(3).getStringCellValue());
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                work.setProcess(row.getCell(4).getStringCellValue());

                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                work.setThWeight(row.getCell(5).getStringCellValue());

                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                work.setNumber(row.getCell(6).getStringCellValue());

                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                work.setPrice(row.getCell(7).getStringCellValue());

                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                work.setAmount(row.getCell(8).getStringCellValue());
                list.add(work);
            }
        }
        map.put("1", list);
        return map;
    }
}
