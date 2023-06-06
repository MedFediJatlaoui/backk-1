package tn.esprit.teriak.Services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.teriak.Entities.TableRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileProcessingService {

    public List<TableRow> processFile(MultipartFile file) throws IOException {
        List<TableRow> tableRows = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Assuming the first column is "CODE PCT" and the second column is "Qte initiale Commandée"
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }

                Cell codePctCell = row.getCell(0);
                Cell qteInitialeCommandeeCell = row.getCell(1);

                String codePct = getValueAsString(codePctCell);
                String qteInitialeCommandee = getValueAsString(qteInitialeCommandeeCell);

                // Perform calculations or any required processing here

                TableRow tableRow = new TableRow(codePct, qteInitialeCommandee);
                tableRows.add(tableRow);
            }
        }

        return tableRows;
    }

    private String getValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellType cellType = cell.getCellType();
        if (cellType == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cellType == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType == CellType.FORMULA) {
            return cell.getCellFormula();
        } else {
            return null;
        }
    }
}
