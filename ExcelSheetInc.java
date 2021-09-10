package Week5Day1Ass;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetInc {

	public static String[][] readData(String filename)  throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+filename+".xlsx");
		XSSFSheet ws = wb.getSheet("Sheet1");
		int ROWCOUNT = ws.getLastRowNum();
		int CELLCOUNT = ws.getRow(0).getLastCellNum();
		String[][] data = new String[ROWCOUNT][CELLCOUNT];
		for (int i = 1; i <= ROWCOUNT; i++) {
			for (int j = 0; j < CELLCOUNT; j++) {
				String text = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(text);
				data[i - 1][j] = text;
			}

		}
		wb.close();
		return data;
	}
}
