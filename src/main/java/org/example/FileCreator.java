package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import org.example.jsonModels.*;

public class FileCreator {

    public void createExcel(Book[] books) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Book list");
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        header.createCell(0).setCellValue("");

        Cell bookCell = header.createCell(1);
        bookCell.setCellValue("Book");
        bookCell.setCellStyle(headerStyle);

        Cell authorCell = header.createCell(2);
        authorCell.setCellValue("Author");
        authorCell.setCellStyle(headerStyle);

        CellStyle lightGreenStyle = workbook.createCellStyle();
        lightGreenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        lightGreenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle paleGreenStyle = workbook.createCellStyle();
        paleGreenStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        paleGreenStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        int rowNum = 1;
        for (Book book : books) {

            CellStyle rowStyle = (rowNum % 2 == 0) ? lightGreenStyle : paleGreenStyle;
            Row row = sheet.createRow(rowNum);

            Cell cell0 = row.createCell(0);
            cell0.setCellValue(rowNum);
            cell0.setCellStyle(rowStyle);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(book.GetName());
            cell1.setCellStyle(rowStyle);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(book.GetAuthor());
            cell2.setCellStyle(rowStyle);
            rowNum++;
        }

        try (FileOutputStream fileOut = new FileOutputStream("bookList.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Файл успешно записан.");
        } catch (IOException e) { e.getMessage();}
    }
}
