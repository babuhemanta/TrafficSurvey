package com.shiftu.trafficsurvey;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.shiftu.trafficsurvey.database.TrafficDatabaseHandler;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.app.Activity;
import android.os.Bundle;
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
			times.setWrap(true);

			// Create create a bold font with unterlines
			WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
					UnderlineStyle.SINGLE);
			timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
			// Lets automatically wrap the cells
			timesBoldUnderline.setWrap(true);

			CellView cv = new CellView();
			cv.setFormat(times);
			cv.setFormat(timesBoldUnderline);
			cv.setAutosize(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			String currentDateandTime = sdf.format(new Date());
			
			// Write a few headers
			addCaption(sheet, 0, 0, "DATE="+currentDateandTime);
			addCaption(sheet, 0, 1, "Time");
			addCaption(sheet, 1, 1, "Cycle");
			addCaption(sheet, 2, 1, "Two Wheeler");
			addCaption(sheet, 3, 1, "Cycle Rikshaw");
			addCaption(sheet, 4, 1, "Hourse Cart");
			addCaption(sheet, 5, 1, "Bullock cart");
			addCaption(sheet, 6, 1, "3- Wheeler auto");
			addCaption(sheet, 7, 1, "LCV(3-wheeler)");
			addCaption(sheet, 8, 1, "Car, Jeep, Taxi");
			addCaption(sheet, 9, 1, "4-Wheeler share auto");
			addCaption(sheet, 10, 1, "LCV(4-wheeler)");
			addCaption(sheet, 11, 1, "Govt Car");
			addCaption(sheet, 12, 1, "Mini bus");
			addCaption(sheet, 13, 1, "Full bus");
			addCaption(sheet, 14, 1, "Tractor");
			addCaption(sheet, 15, 1, "Two Axles truck");
			addCaption(sheet, 16, 1, "Tractor+trailler");
			addCaption(sheet, 17, 1, "3_Axles truck");
			addCaption(sheet, 18, 1, "4-6 Axiles trick");
			addCaption(sheet, 19, 1, "7-more axiles truck");
			addCaption(sheet, 20, 1, "JCB");
			addCaption(sheet, 21, 1, "LCV(6-wheeler)");
			addCaption(sheet, 22, 1, "Govt truck");

		}

		private void createContent(WritableSheet sheet) throws WriteException,
				RowsExceededException {

			addCaption(sheet, 0, 2, "00:00:00 to 01:00:00");
			addCaption(sheet, 0, 3, "01:00:00 to 02:00:00");
			addCaption(sheet, 0, 4, "02:00:00 to 03:00:00");
			addCaption(sheet, 0, 5, "03:00:00 to 04:00:00");
			addCaption(sheet, 0, 6, "04:00:00 to 05:00:00");
			addCaption(sheet, 0, 7, "05:00:00 to 06:00:00");
			addCaption(sheet, 0, 8, "06:00:00 to 07:00:00");
			addCaption(sheet, 0, 9, "07:00:00 to 08:00:00");
			addCaption(sheet, 0, 10, "08:00:00 to 09:00:00");
			addCaption(sheet, 0, 11, "09:00:00 to 10:00:00");
			addCaption(sheet, 0, 12, "10:00:00 to 11:00:00");
			addCaption(sheet, 0, 13, "11:00:00 to 12:00:00");
			addCaption(sheet, 0, 14, "12:00:00 to 13:00:00");
			addCaption(sheet, 0, 15, "13:00:00 to 14:00:00");
			addCaption(sheet, 0, 16, "14:00:00 to 15:00:00");
			addCaption(sheet, 0, 17, "15:00:00 to 16:00:00");
			addCaption(sheet, 0, 18, "16:00:00 to 17:00:00");
			addCaption(sheet, 0, 19, "17:00:00 to 18:00:00");
			addCaption(sheet, 0, 20, "18:00:00 to 19:00:00");
			addCaption(sheet, 0, 21, "19:00:00 to 20:00:00");
			addCaption(sheet, 0, 22, "20:00:00 to 21:00:00");
			addCaption(sheet, 0, 23, "21:00:00 to 22:00:00");
			addCaption(sheet, 0, 24, "22:00:00 to 23:00:00");
			addCaption(sheet, 0, 25, "23:00:00 to 24:00:00");
			addCaption(sheet, 0, 26, "Total");
			int j;
			int i;
			
			for (j = 1; j <= 22; j++) {
				for (i = 1; i <= 24; i++) {
					//String countvalue = Integer.toString(db.reportview(i, j));
				    //System.out.println(countvalue);
					int countvalue = db.reportview(i, j);
					addNumber(sheet, j, i+2, countvalue);
				}
			}
			
			 // Lets calculate the sum of it
		    StringBuffer buf = new StringBuffer();
		    buf.append("SUM(B2:B26)");
		    Formula f = new Formula(1, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(C2:C26)");
		    f = new Formula(2, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(D2:D26)");
		    f = new Formula(3, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(E2:E26)");
		    f = new Formula(4, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(F2:F26)");
		    f = new Formula(5, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(G2:G26)");
		    f = new Formula(6, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(H2:H26)");
		    f = new Formula(7, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(I2:I26)");
		    f = new Formula(8, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(J2:J26)");
		    f = new Formula(9, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(K2:K26)");
		    f = new Formula(10, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(L2:L26)");
		    f = new Formula(11, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(M2:M26)");
		    f = new Formula(12, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(N2:N26)");
		    f = new Formula(13, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(O2:O26)");
		    f = new Formula(14, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(P2:P26)");
		    f = new Formula(15, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(Q2:Q26)");
		    f = new Formula(16, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(R2:R26)");
		    f = new Formula(17, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(S2:S26)");
		    f = new Formula(18, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(T2:T26)");
		    f = new Formula(19, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(U2:U26)");
		    f = new Formula(20, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(V2:V26)");
		    f = new Formula(21, 26, buf.toString());
		    sheet.addCell(f);
		    
		    
		    buf = new StringBuffer();
		    buf.append("SUM(W2:W26)");
		    f = new Formula(22, 26, buf.toString());
		    sheet.addCell(f);
		    	    
		    
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
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

		String formattedDate = df.format(c.getTime());


		//File absolutePath = Environment.getExternalStorageDirectory();
		test.setOutputFile("/sdcard/"+formattedDate+"report.xls");
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
//		mEditTextdate = (EditText) findViewById(R.id.editText1);
//		mEditTextformtime = (EditText) findViewById(R.id.editText2);
//		mEditTexttotime = (EditText) findViewById(R.id.editText3);
//		date = mEditTextdate.toString();
//		fromtime = mEditTextformtime.toString();
//		totime = mEditTexttotime.toString();

		mButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reportExcel();
				
			}
		});

	}

}
