package com.example.back.service;

import com.example.back.dto.response.Inventory.DetailedRevenueDto;
import com.example.back.dto.response.Inventory.InventoryReportDto;
import com.example.back.dto.response.Inventory.RevenueReportDto;
import com.example.back.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<InventoryReportDto> getInventoryReport() {
        List<Object[]> results = reportRepository.getInventoryReport();
        return results.stream().map(row -> new InventoryReportDto(
                ((Number) row[0]).intValue(),
                (String) row[1],
                (String) row[2],
                (String) row[3],
                ((Number) row[4]).intValue(),
                ((Number) row[5]).intValue(),
                (BigDecimal) row[6],
                (BigDecimal) row[7],
                (String) row[8],
                (Date) row[9]
        )).collect(Collectors.toList());
    }

    public List<RevenueReportDto> getRevenueReport(Date startDate, Date endDate) {
        List<Object[]> results = reportRepository.getRevenueReport(startDate, endDate);
        return results.stream().map(row -> new RevenueReportDto(
                (Date) row[0],
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (BigDecimal) row[4],
                (BigDecimal) row[5],
                (String) row[6],
                (String) row[7]
        )).collect(Collectors.toList());
    }

    public List<DetailedRevenueDto> getDetailedRevenueReport(Date startDate, Date endDate) {
        List<Object[]> results = reportRepository.getDetailedRevenueReport(startDate, endDate);
        return results.stream().map(row -> new DetailedRevenueDto(
                ((Number) row[0]).intValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                (BigDecimal) row[4],
                (BigDecimal) row[5],
                ((Number) row[6]).intValue(),
                (Date) row[7],
                (Date) row[8]
        )).collect(Collectors.toList());
    }

    public ByteArrayInputStream exportInventoryToExcel() {
        List<InventoryReportDto> data = getInventoryReport();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Báo cáo tồn kho");

            // Tạo các style
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle alternateRowStyle = createAlternateRowStyle(workbook);
            CellStyle numberStyle = createNumberStyle(workbook);
            CellStyle alternateNumberStyle = createAlternateNumberStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle alternateCurrencyStyle = createAlternateCurrencyStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);
            CellStyle alternateDateStyle = createAlternateDateStyle(workbook);
            CellStyle centerStyle = createCenterStyle(workbook);
            CellStyle alternateCenterStyle = createAlternateCenterStyle(workbook);
            CellStyle summaryLabelStyle = createSummaryLabelStyle(workbook);
            CellStyle summaryValueStyle = createSummaryValueStyle(workbook);

            // Header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Mã SP", "Tên sản phẩm", "Thể loại", "Danh mục", "Tồn kho", "Đã bán", "Giá", "Giá trị tồn", "Trạng thái", "Cập nhật"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data rows với màu xen kẽ
            int rowIdx = 1;
            double totalValue = 0;
            int totalProducts = 0;
            int totalStock = 0;
            int totalSold = 0;

            for (InventoryReportDto item : data) {
                Row row = sheet.createRow(rowIdx);
                boolean isEvenRow = (rowIdx % 2 == 0);

                // Tính tổng
                totalValue += item.getTotalValue().doubleValue();
                totalProducts++;
                totalStock += item.getCurrentStock();
                totalSold += item.getTotalSold();

                // Mã SP
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(item.getProductId());
                cell0.setCellStyle(isEvenRow ? alternateCenterStyle : centerStyle);

                // Tên sản phẩm
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(item.getProductName());
                cell1.setCellStyle(isEvenRow ? alternateRowStyle : dataStyle);

                // Thể loại
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(item.getCategoryName());
                cell2.setCellStyle(isEvenRow ? alternateRowStyle : dataStyle);

                // Danh mục
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(item.getCatalogName());
                cell3.setCellStyle(isEvenRow ? alternateRowStyle : dataStyle);

                // Tồn kho
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(item.getCurrentStock());
                cell4.setCellStyle(isEvenRow ? alternateNumberStyle : numberStyle);

                // Đã bán
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(item.getTotalSold());
                cell5.setCellStyle(isEvenRow ? alternateNumberStyle : numberStyle);

                // Giá
                Cell cell6 = row.createCell(6);
                cell6.setCellValue(item.getUnitPrice().doubleValue());
                cell6.setCellStyle(isEvenRow ? alternateCurrencyStyle : currencyStyle);

                // Giá trị tồn
                Cell cell7 = row.createCell(7);
                cell7.setCellValue(item.getTotalValue().doubleValue());
                cell7.setCellStyle(isEvenRow ? alternateCurrencyStyle : currencyStyle);

                // Trạng thái (với màu conditional)
                Cell cell8 = row.createCell(8);
                String status = getStockStatusVietnamese(item.getStockStatus());
                cell8.setCellValue(status);
                cell8.setCellStyle(getStatusStyle(workbook, status, isEvenRow));

                // Ngày cập nhật
                Cell cell9 = row.createCell(9);
                cell9.setCellValue(item.getLastUpdateDate().toString());
                cell9.setCellStyle(isEvenRow ? alternateDateStyle : dateStyle);

                rowIdx++;
            }

            // Thêm dòng trống
            rowIdx++;

            // Dòng tổng kết 1: Tổng số lượng sản phẩm và tồn kho
            Row summaryRow1 = sheet.createRow(rowIdx++);

            // Label
            Cell labelCell1 = summaryRow1.createCell(0);
            labelCell1.setCellValue("TỔNG KẾT:");
            labelCell1.setCellStyle(summaryLabelStyle);

            Cell labelCell2 = summaryRow1.createCell(1);
            labelCell2.setCellValue("Số sản phẩm:");
            labelCell2.setCellStyle(summaryLabelStyle);

            Cell valueCell1 = summaryRow1.createCell(2);
            valueCell1.setCellValue(totalProducts);
            valueCell1.setCellStyle(summaryValueStyle);

            Cell labelCell3 = summaryRow1.createCell(3);
            labelCell3.setCellValue("Tồn kho:");
            labelCell3.setCellStyle(summaryLabelStyle);

            Cell valueCell2 = summaryRow1.createCell(4);
            valueCell2.setCellValue(totalStock);
            valueCell2.setCellStyle(summaryValueStyle);

            Cell labelCell4 = summaryRow1.createCell(5);
            labelCell4.setCellValue("Đã bán:");
            labelCell4.setCellStyle(summaryLabelStyle);

            Cell valueCell3 = summaryRow1.createCell(6);
            valueCell3.setCellValue(totalSold);
            valueCell3.setCellStyle(summaryValueStyle);

            // Dòng tổng kết 2: Tổng giá trị
            Row summaryRow2 = sheet.createRow(rowIdx++);

            Cell totalLabelCell = summaryRow2.createCell(6);
            totalLabelCell.setCellValue("TỔNG GIÁ TRỊ:");
            totalLabelCell.setCellStyle(summaryLabelStyle);

            Cell totalValueCell = summaryRow2.createCell(7);
            totalValueCell.setCellValue(totalValue);
            totalValueCell.setCellStyle(createSummaryValueCurrencyStyle(workbook));

            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
                // Đặt width tối thiểu để tránh quá hẹp
                if (sheet.getColumnWidth(i) < 2000) {
                    sheet.setColumnWidth(i, 2000);
                }
            }

            // Freeze header row
            sheet.createFreezePane(0, 1);

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export Excel file", e);
        }
    }

    // Header style - màu xanh đậm, chữ trắng, in đậm
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        // Font
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short) 12);

        // Background
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Alignment
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Border
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        style.setFont(font);
        return style;
    }

    // Data style - chữ đen, nền trắng
    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontHeightInPoints((short) 11);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Border nhẹ
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        style.setFont(font);
        return style;
    }

    // Alternate row style - nền xám nhạt
    private CellStyle createAlternateRowStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    // Number style - căn phải cho số
    private CellStyle createNumberStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        return style;
    }

    private CellStyle createAlternateNumberStyle(Workbook workbook) {
        CellStyle style = createAlternateRowStyle(workbook);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        return style;
    }

    // Currency style - định dạng tiền tệ
    private CellStyle createCurrencyStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0\" VNĐ\""));
        return style;
    }

    private CellStyle createAlternateCurrencyStyle(Workbook workbook) {
        CellStyle style = createAlternateRowStyle(workbook);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0\" VNĐ\""));
        return style;
    }

    // Date style
    private CellStyle createDateStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setDataFormat(workbook.createDataFormat().getFormat("dd/mm/yyyy"));
        return style;
    }

    private CellStyle createAlternateDateStyle(Workbook workbook) {
        CellStyle style = createAlternateRowStyle(workbook);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setDataFormat(workbook.createDataFormat().getFormat("dd/mm/yyyy"));
        return style;
    }

    // Center style
    private CellStyle createCenterStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createAlternateCenterStyle(Workbook workbook) {
        CellStyle style = createAlternateRowStyle(workbook);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    // Summary styles
    private CellStyle createSummaryLabelStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());

        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Border
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);

        style.setFont(font);
        return style;
    }

    private CellStyle createSummaryValueStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.DARK_RED.getIndex());

        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));

        // Border
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);

        style.setFont(font);
        return style;
    }

    private CellStyle createSummaryValueCurrencyStyle(Workbook workbook) {
        CellStyle style = createSummaryValueStyle(workbook);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0\" VNĐ\""));
        return style;
    }

    // Status style với màu sắc khác nhau - cải tiến để có alternating row
    private CellStyle getStatusStyle(Workbook workbook, String status, boolean isEvenRow) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);

        // Alignment và border
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        switch (status.toLowerCase()) {
            case "hết hàng":
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
                font.setColor(IndexedColors.WHITE.getIndex());
                break;
            case "sắp hết":
            case "ít hàng":
                style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
                font.setColor(IndexedColors.BLACK.getIndex());
                break;
            case "còn hàng":
            case "đủ hàng":
                style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                font.setColor(IndexedColors.BLACK.getIndex());
                break;
            default:
                style.setFillForegroundColor(isEvenRow ? IndexedColors.GREY_25_PERCENT.getIndex() : IndexedColors.WHITE.getIndex());
                font.setColor(IndexedColors.BLACK.getIndex());
        }

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);
        return style;
    }
    public ByteArrayInputStream exportRevenueToExcel(Date startDate, Date endDate) {
        List<DetailedRevenueDto> data = getDetailedRevenueReport(startDate, endDate);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Báo cáo doanh thu");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Mã SP", "Tên sản phẩm", "Thể loại", "Số lượng bán", "Giá", "Doanh thu", "Số đơn", "Bán đầu", "Bán cuối"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Data rows
            int rowIdx = 1;
            for (DetailedRevenueDto item : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(item.getProductId());
                row.createCell(1).setCellValue(item.getProductName());
                row.createCell(2).setCellValue(item.getCategoryName());
                row.createCell(3).setCellValue(item.getQuantitySold());
                row.createCell(4).setCellValue(item.getUnitPrice().doubleValue());
                row.createCell(5).setCellValue(item.getTotalRevenue().doubleValue());
                row.createCell(6).setCellValue(item.getTotalOrders());
                row.createCell(7).setCellValue(item.getFirstSaleDate().toString());
                row.createCell(8).setCellValue(item.getLastSaleDate().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export Excel file", e);
        }
    }

    private String getStockStatusVietnamese(String status) {
        switch (status) {
            case "OUT_OF_STOCK": return "Hết hàng";
            case "LOW_STOCK": return "Sắp hết";
            case "MEDIUM_STOCK": return "Vừa phải";
            case "HIGH_STOCK": return "Nhiều";
            default: return "Không xác định";
        }
    }
}
