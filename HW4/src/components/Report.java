package components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private int reportId;
	private Date date;
	private boolean authorized = false;
	private int vehicleId;

	public Report(int vehicleId, int reportId) {
		this.vehicleId = vehicleId;
		this.reportId = reportId;
		date = new Date();
	}

	private Report(int vehicleId, int reportId, Date date, boolean authorized) {
		this.vehicleId = vehicleId;
		this.reportId = reportId;
		this.date = date;
		this.authorized = authorized;
	}

	public static Report constructFromString(String str) {
		String myDate = str.substring(6, 25);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = new Date();
		try {
			d = formatter.parse(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int vicId = Integer.parseInt(str.substring(38, str.indexOf("REPORT") - 1));
		int rId = Integer.parseInt(str.substring(str.indexOf("#") + 1, str.indexOf("AUTH") - 1));
		boolean authorized = Boolean.parseBoolean(str.substring(str.indexOf("AUTH") + 12));

		return new Report(vicId, rId, d, authorized);
	}

	public void authorize() {
		authorized = true;
	}

	public int getReportId() {
		return reportId;
	}

	public Date getDate() {
		return date;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	@Override
	public String toString() {
		return "DATE: " + formatter.format(date) + " VEHICLE ID: " + vehicleId + " REPORT #" + reportId
				+ " AUTHORIZED: " + Boolean.toString(authorized).toUpperCase();
	}
}
