package SwagLabs_Automation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    /**
     * Fetches a value from an Excel file based on row and column criteria.
     *
     * @param filePath   Path to the Excel file
     * @param sheetName  Name of the sheet in the Excel file
     * @param keyColumn  Column name to identify the row (e.g., "Username")
     * @param keyValue   Value to match in the key column (e.g., "standard_user")
     * @param valueColumn Column name to fetch the value from (e.g., "FirstName")
     * @return The value from the specified column, or null if not found
     */
    public static String fetchValueFromExcel(String filePath, String Sheet1, String Username, String standard_user, String FirstName) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Nicos\\eclipse-workspace\\Stratpoint_QA_department\\TestData\\Credentials.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(Sheet1);

        // Find the column indices for the key and value columns
        Row headerRow = sheet.getRow(0);
        int keyColIndex = -1, valueColIndex = -1;

        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(Username)) {
                keyColIndex = cell.getColumnIndex();
            }
            if (cell.getStringCellValue().equalsIgnoreCase(FirstName)) {
                valueColIndex = cell.getColumnIndex();
            }
        }

        if (keyColIndex == -1 || valueColIndex == -1) {
            throw new IllegalArgumentException("Key or value column not found in the Excel file.");
        }

        // Iterate through rows to find the matching key value
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row.getCell(keyColIndex).getStringCellValue().equalsIgnoreCase(standard_user)) {
                Cell valueCell = row.getCell(valueColIndex);
                return valueCell != null ? valueCell.toString() : null;
            }
        }

        workbook.close();
        fis.close();
        return null; // Return null if no match is found
    }
}
