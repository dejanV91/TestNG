package testNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtil {

	static ArrayList<Object[]> myData = new ArrayList<Object[]>();
	static ArrayList<Object[]> log = new ArrayList<Object[]>();

	public static ArrayList<Object[]> Lista() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Dejan\\Desktop\\Projekat\\Data.xls");
		HSSFWorkbook wbook = new HSSFWorkbook(file);
		DataFormatter formater = new DataFormatter();
		HSSFSheet sheet = wbook.getSheet("Registracija");

		for (int i = 1; i < 6; i++) {
			String firstName = formater.formatCellValue(sheet.getRow(i).getCell(0));
			String lastName = formater.formatCellValue(sheet.getRow(i).getCell(1));
			String userName = formater.formatCellValue(sheet.getRow(i).getCell(2));
			String email = formater.formatCellValue(sheet.getRow(i).getCell(3));
			String password = formater.formatCellValue(sheet.getRow(i).getCell(4));
			Object[] list = { firstName, lastName, userName, email, password };

			myData.add(list);

		}
		return myData;
	}

	public static ArrayList<Object[]> LogIn() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users\\Dejan\\Desktop\\Projekat\\Data.xls");
		HSSFWorkbook wbook = new HSSFWorkbook(file);
		DataFormatter formater = new DataFormatter();
		HSSFSheet sheet = wbook.getSheet("Registracija");

		for (int i = 1; i <6 ; i++) {
			String userName = formater.formatCellValue(sheet.getRow(i).getCell(2));
			String password = formater.formatCellValue(sheet.getRow(i).getCell(4));
			Object[] logInData= {userName,password};
			
			log.add(logInData);
			}
		return log;
	}
}
