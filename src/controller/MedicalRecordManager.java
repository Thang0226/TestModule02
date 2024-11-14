package controller;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordManager {
	private final String FILE_HEADER = "Số thứ tự, Mã bệnh án, Mã bệnh nhân, Tên bệnh nhân, Ngày vào viện, Ngày ra " +
			"viện, Lý do nhập viện, Loại VIP/Viện phí";
	private final String DELIMITER = ", ";
	private final String NEW_LINE_SEPARATOR = "\n";

	private String filePath;
	private List<MedicalRecord> records;

	public MedicalRecordManager(String filePath) {
		this.filePath = filePath;
		this.records = new ArrayList<>();
		loadRecords();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void loadRecords() {
		try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
			String line = br.readLine(); // cancel header at first row
			while ((line = br.readLine()) != null) {
				String[] propertyArray = line.split(DELIMITER);
				String recordCode = propertyArray[1];
				String patientCode = propertyArray[2];
				String patientName = propertyArray[3];
				String admissionDate = propertyArray[4];
				String dischargeDate = propertyArray[5];
				String admissionReason = propertyArray[6];
				String VIPOrFee = propertyArray[7];

				String[] categoryArray = VIPOrFee.split(" ");
				if (categoryArray[0].equals("VIP")) {
					records.add(new VIPMedicalRecord(recordCode, patientCode, patientName, admissionDate,
							dischargeDate, admissionReason, VIPOrFee));
				} else {
					records.add(new RegularMedicalRecord(recordCode, patientCode, patientName, admissionDate,
							dischargeDate, admissionReason, Integer.parseInt(VIPOrFee)));
				}
			}
			System.out.println("Records loaded.");

		} catch (IOException e) {
			System.out.println("Error reading csv file!");
			System.out.println(e.getMessage());
		}
	}

	public void saveRecords() {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getFilePath());
			fileWriter.write(FILE_HEADER);
			fileWriter.append(NEW_LINE_SEPARATOR);
			for (MedicalRecord record : records) {
				fileWriter.append(String.valueOf(record.getId()));
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getRecordCode());
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getPatientCode());
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getPatientName());
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getAdmissionDate());
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getDischargeDate());
				fileWriter.append(DELIMITER);
				fileWriter.append(record.getAdmissionReason());
				fileWriter.append(DELIMITER);
				if (record instanceof VIPMedicalRecord) {
					fileWriter.append(((VIPMedicalRecord) record).getVIPType());
				} else {
					fileWriter.append(String.valueOf(((RegularMedicalRecord) record).getHospitalFee()));
				}
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("Records saved.");

		} catch (IOException e) {
			System.out.println("Error in CsvFileWriter!");
			System.out.println(e.getMessage());

		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter!");
				System.out.println(e.getMessage());
			}
		}
	}

	public boolean addNewRecord() {
		try {
			String recordCode = inputRecordCode();
			RecordType recordType = inputRecordType();
			RecordFactory factory = RecordFactory.getInstance();
			MedicalRecord newRecord = factory.getMedicalRecord(recordType, recordCode);
			if (newRecord != null) {
				if (validateRecord(newRecord)) {
					add(newRecord);
					saveRecords();
					return true;
				}
			}
			return false;

		} catch (DuplicateMedicalRecordException e) {
			System.out.println(e.getMessage());
			System.out.println("Hãy nhập lại bệnh án mới");
		}
		return false;
	}

	private String inputRecordCode() throws DuplicateMedicalRecordException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập vào mã bệnh án (BA-XXX): ");
		String recordCode = scanner.nextLine();
		if (recordCodeExisted(recordCode)) {
			throw new DuplicateMedicalRecordException("Mã bệnh án đã tồn tại!");
		}
		return recordCode;
	}

	private boolean recordCodeExisted(String recordCode) {
		for (MedicalRecord record : records) {
			if (record.getRecordCode().equals(recordCode)) {
				return true;
			}
		}
		return false;
	}

	private boolean add(MedicalRecord record) {
		return records.add(record);
	}

	private RecordType inputRecordType() {
		System.out.print("Nhập loại bệnh án (REGULAR/VIP): ");
		String type = (new Scanner(System.in)).nextLine();
		type = type.toUpperCase();
		return RecordType.valueOf(type);
	}

	public void deleteRecord() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập vào mã bệnh án (BA-XXX): ");
		String recordCode = scanner.nextLine();

		if (recordCodeExisted(recordCode)) {
			System.out.println("Chắc chắn xóa? (Yes/No)");
			String confirm = (new Scanner(System.in)).nextLine();
			confirm = confirm.toUpperCase();

			if (confirm.equals("YES") || confirm.equals("Y")) {
				for (MedicalRecord record : records) {
					if (record.getRecordCode().equals(recordCode)) {
						records.remove(record);
						break;
					}
				}
				saveRecords();
				records.clear();
				MedicalRecord.setCount(0);
				loadRecords();
				displayRecords();
			}
		}
	}

	public void displayRecords() {
		for (MedicalRecord record : records) {
			System.out.print("Số thứ tự: " + record.getId() + "\t");
			System.out.print("Mã bệnh án: " + record.getRecordCode() + "\t");
			System.out.print("Mã bệnh nhân: " + record.getPatientCode() + "\t");
			System.out.print("Tên bệnh nhân: " + record.getPatientName() + "\t");
			System.out.print("Ngày nhập viện: " + record.getAdmissionDate() + "\t");
			System.out.print("Ngày ra viện: " + record.getDischargeDate() + "\t");
			System.out.print("Lý do nhập viện: " + record.getAdmissionReason() + "\t");
			System.out.println();
		}
	}

	private boolean validateRecord(MedicalRecord record) {
		return (new ValidateRecord()).validate(record);
	}
}
