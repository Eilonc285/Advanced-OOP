package components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import utilities.GameDriver;

public class Moked {
	private static volatile Moked mySingleton = null;
	private static final String path = "C:\\Users\\Eilon\\Desktop\\reports.txt";
	private static final String tempPath = "C:\\Users\\Eilon\\Desktop\\temp.txt";
	private static final ReadWriteLock fileLock = new ReentrantReadWriteLock();
	private static int reportCounter = 0;

	private Moked() {
	}

	public static Moked getMoked() {
		Moked localRef = mySingleton;
		if (localRef == null) {
			synchronized (Moked.class) {
				localRef = mySingleton;
				if (mySingleton == null) {
					localRef = new Moked();
					mySingleton = localRef;
				}
			}
		}
		return mySingleton;
	}

	public void submitReport(int vehicleId) {
		fileLock.writeLock().lock();
		try {
			FileWriter myWriter = new FileWriter(path, true);
			BufferedWriter bWriter = new BufferedWriter(myWriter);
			Report report = new Report(vehicleId, reportCounter);
			reportCounter++;
			bWriter.write(report.toString());
			bWriter.newLine();
			bWriter.close();
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileLock.writeLock().unlock();
		}
		notifyDriver(vehicleId);
	}

	public void approveReport(int reportId) {
		boolean reportApproved = false;
		fileLock.writeLock().lock();
		try {
			File oldReports = new File(path);
			FileReader oldReportsReader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(oldReportsReader);
			FileWriter myWriter = new FileWriter(tempPath, true);
			BufferedWriter bWriter = new BufferedWriter(myWriter);
			String currentLine = "";
			while ((currentLine = bReader.readLine()) != null) {
				Report report = Report.constructFromString(currentLine);
				if (report.getReportId() == reportId && report.isAuthorized() == false && !reportApproved) {
					reportApproved = true;
					report.authorize();
					bWriter.write(report.toString());
					bWriter.newLine();
				} else {
					bWriter.write(currentLine);
					bWriter.newLine();
				}
			}
			bReader.close();
			oldReportsReader.close();
			bWriter.close();
			myWriter.close();

			oldReports.delete();
			File newReports = new File(tempPath);
			newReports.renameTo(oldReports);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileLock.writeLock().unlock();
		}
	}

	public void notifyDriver(int vehicleId) {
		ArrayList<Vehicle> vehicles = GameDriver.getDriving().getVehicles();
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getId() == vehicleId) {
				vehicles.get(i).noticeReport(vehicleId, path);
				break;
			}
		}
	}

	public ReadWriteLock getFileLock() {
		return fileLock;
	}

	private boolean verifyReports() {
		boolean allReportsAuthorized = true;
		fileLock.readLock().lock();
		try {
			File reports = new File(path);
			FileReader reportsReader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(reportsReader);
			String currentLine = "";
			while ((currentLine = bReader.readLine()) != null) {
				Report report = Report.constructFromString(currentLine);
				if (!report.isAuthorized()) {
					allReportsAuthorized = false;
					break;
				}
			}
			bReader.close();
			reportsReader.close();
			return allReportsAuthorized;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileLock.readLock().unlock();

		}
		return false;
	}
}
