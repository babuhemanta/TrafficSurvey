package com.shiftu.trafficsurvey;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import com.shiftu.trafficsurvey.database.TrafficDatabaseHandler;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportActivity extends Activity {
	TrafficDatabaseHandler db;
	Button mButtonNext;
	String date, fromtime, totime;
	EditText mEditTextdate, mEditTextformtime, mEditTexttotime;

	class WriteExcel {

		private WritableCellFormat timesBoldUnderline;
		private WritableCellFormat times;
		private String inputFile;

		public void setOutputFile(String inputFile) {
			this.inputFile = inputFile;
		}

		public void write() throws IOException, WriteException {
			File file = new File(inputFile);
			WorkbookSettings wbSettings = new WorkbookSettings();
			wbSettings.setLocale(new Locale("en", "EN"));
			WritableWorkbook workbook = Workbook.createWorkbook(file,
					wbSettings);
			workbook.createSheet("Report", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createLabel(excelSheet);
			createContent(excelSheet);
			workbook.write();
			workbook.close();
		}

		private void createLabel(WritableSheet sheet) throws WriteException {
			// Lets create a times font
			WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
			// Define the cell format
			times = new WritableCellFormat(times10pt);
			// Lets automatically wrap the cells
			times.setWrap(false);

			// Create create a bold font with unterlines
			WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
					UnderlineStyle.SINGLE);
			timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
			// Lets automatically wrap the cells
			timesBoldUnderline.setWrap(false);

			CellView cv = new CellView();
			cv.setFormat(times);
			cv.setFormat(timesBoldUnderline);
			cv.setAutosize(true);
			
			// Write a few headers
			addCaption(sheet, 0, 0, "Time");
			addCaption(sheet, 1, 0, "Cycle");
			addCaption(sheet, 2, 0, "Two Wheeler");
			addCaption(sheet, 3, 0, "Cycle Rikshaw");
			addCaption(sheet, 4, 0, "Hourse Cart");
			addCaption(sheet, 5, 0, "Bull cart");
			addCaption(sheet, 6, 0, "3- Wheeler auto");
			addCaption(sheet, 7, 0, "LCV(3-wheeler)");
			addCaption(sheet, 8, 0, "Car, Jeep, Taxi");
			addCaption(sheet, 9, 0, "4-Wheeler share auto");
			addCaption(sheet, 10, 0, "LCV(4-wheeler)");
			addCaption(sheet, 11, 0, "Govt Car");
			addCaption(sheet, 12, 0, "Mini bus");
			addCaption(sheet, 13, 0, "Full buss");
			addCaption(sheet, 14, 0, "Tractor");
			addCaption(sheet, 15, 0, "Two Axles truck");
			addCaption(sheet, 16, 0, "Tractor+trailler");
			addCaption(sheet, 17, 0, "3_Axles truck");
			addCaption(sheet, 18, 0, "4-6 Axiles trick");
			addCaption(sheet, 19, 0, "7-more axiles truck");
			addCaption(sheet, 20, 0, "JCB");
			addCaption(sheet, 21, 0, "LCV(6-wheeler)");
			addCaption(sheet, 22, 0, "Govt truck");

		}

		private void createContent(WritableSheet sheet) throws WriteException,
				RowsExceededException {

			addCaption(sheet, 0, 1, "00:00:00 to 01:00:00");
			addCaption(sheet, 0, 2, "01:00:00 to 02:00:00");
			addCaption(sheet, 0, 3, "02:00:00 to 03:00:00");
			addCaption(sheet, 0, 4, "03:00:00 to 04:00:00");
			addCaption(sheet, 0, 5, "04:00:00 to 05:00:00");
			addCaption(sheet, 0, 6, "05:00:00 to 06:00:00");
			addCaption(sheet, 0, 7, "06:00:00 to 07:00:00");
			addCaption(sheet, 0, 8, "07:00:00 to 08:00:00");
			addCaption(sheet, 0, 9, "08:00:00 to 09:00:00");
			addCaption(sheet, 0, 10, "09:00:00 to 10:00:00");
			addCaption(sheet, 0, 11, "10:00:00 to 11:00:00");
			addCaption(sheet, 0, 12, "11:00:00 to 12:00:00");
			addCaption(sheet, 0, 13, "12:00:00 to 13:00:00");
			addCaption(sheet, 0, 14, "13:00:00 to 14:00:00");
			addCaption(sheet, 0, 15, "14:00:00 to 15:00:00");
			addCaption(sheet, 0, 16, "15:00:00 to 16:00:00");
			addCaption(sheet, 0, 17, "16:00:00 to 17:00:00");
			addCaption(sheet, 0, 18, "17:00:00 to 18:00:00");
			addCaption(sheet, 0, 19, "18:00:00 to 19:00:00");
			addCaption(sheet, 0, 20, "19:00:00 to 20:00:00");
			addCaption(sheet, 0, 21, "20:00:00 to 21:00:00");
			addCaption(sheet, 0, 22, "21:00:00 to 22:00:00");
			addCaption(sheet, 0, 23, "22:00:00 to 23:00:00");
			addCaption(sheet, 0, 24, "23:00:00 to 24:00:00");
			addCaption(sheet, 0, 25, "Total");
			int j;
			int i;
			
			for (j = 1; j <= 22; j++) {
				for (i = 1; i <= 24; i++) {
					//String countvalue = Integer.toString(db.reportview(i, j));
				    //System.out.println(countvalue);
					int countvalue = db.reportview(i, j);
					addNumber(sheet, j, i, countvalue);
				}
			}
			Toast.makeText(ReportActivity.this, "Generated report sucessfully", Toast.LENGTH_LONG).show();
		}

		private void addCaption(WritableSheet sheet, int column, int row,
				String s) throws RowsExceededException, WriteException {
			Label label;
			label = new Label(column, row, s, timesBoldUnderline);
			sheet.addCell(label);
		}

		private void addNumber(WritableSheet sheet, int column, int row,
				Integer integer) throws WriteException, RowsExceededException {
			Number number;
			number = new Number(column, row, integer, times);
			sheet.addCell(number);
		}
	}

	public void reportExcel() {

		WriteExcel test = new WriteExcel();
		//File absolutePath = Environment.getExternalStorageDirectory();
		test.setOutputFile("/sdcard/TrifficSurvey.xls");
		// test.setOutputFile(Environment.getExternalStorageDirectory().getPath()+"TrifficSurvey.xls");
		try {
			test.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		mButtonNext = (Button) findViewById(R.id.button1);
		db = new TrafficDatabaseHandler(this);
		mEditTextdate = (EditText) findViewById(R.id.editText1);
		mEditTextformtime = (EditText) findViewById(R.id.editText2);
		mEditTexttotime = (EditText) findViewById(R.id.editText3);
		date = mEditTextdate.toString();
		fromtime = mEditTextformtime.toString();
		totime = mEditTexttotime.toString();

		mButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reportExcel();
				
			}
		});

	}

}
