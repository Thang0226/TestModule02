package model;

public abstract class MedicalRecord {
	private static int count = 0;
	private int id;
	private String recordCode;
	private String patientCode;
	private String patientName;
	private String admissionDate;
	private String dischargeDate;
	private String admissionReason;

	public MedicalRecord(String recordCode, String patientCode, String patientName, String admissionDate,
	                     String dischargeDate, String admissionReason) {
		this.id = ++count;
		this.recordCode = recordCode;
		this.patientCode = patientCode;
		this.patientName = patientName;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
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
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getAdmissionReason() {
		return admissionReason;
	}

	public void setAdmissionReason(String admissionReason) {
		this.admissionReason = admissionReason;
	}
}
