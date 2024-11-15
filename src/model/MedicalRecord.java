package model;

import java.time.LocalDate;

public abstract class MedicalRecord {
	private static int count = 0;
	private int id;
	private String recordCode;
	private String patientCode;
	private String patientName;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private String admissionReason;

	public MedicalRecord(String recordCode, String patientCode, String patientName, String admissionDate,
	                     String dischargeDate, String admissionReason) {
		this.id = ++count;
		this.recordCode = recordCode;
		this.patientCode = patientCode;
		this.patientName = patientName;
		this.admissionDate = createLocalDate(admissionDate);
		this.dischargeDate = createLocalDate(dischargeDate);
		this.admissionReason = admissionReason;
	}

	public int getId() {
		return id;
	}

	public static void setCount(int count) {
		MedicalRecord.count = count;
	}

	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAdmissionDate() {
		return changeDateFormat(this.admissionDate);
	}

	private String changeDateFormat(LocalDate date) {
		String rawString = date.toString();    // yyyy-MM-dd
		String[] strArray = rawString.split("-");
		return strArray[2] + "/" + strArray[1] + "/" + strArray[0];
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = createLocalDate(admissionDate);
	}

	private LocalDate createLocalDate(String date) {
		String[] dateArray = date.split("/");
		return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]),
				Integer.parseInt(dateArray[0]));
	}

	public String getDischargeDate() {
		return changeDateFormat(this.dischargeDate);
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = createLocalDate(dischargeDate);
	}

	public String getAdmissionReason() {
		return admissionReason;
	}

	public void setAdmissionReason(String admissionReason) {
		this.admissionReason = admissionReason;
	}
}
