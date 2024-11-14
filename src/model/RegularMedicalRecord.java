package model;

public class RegularMedicalRecord extends MedicalRecord {
	private int hospitalFee;

	public RegularMedicalRecord(String recordCode, String patientCode, String patientName, String admissionDate,
	                            String dischargeDate, String admissionReason, int hospitalFee) {
		super(recordCode, patientCode, patientName, admissionDate, dischargeDate, admissionReason);
		this.hospitalFee = hospitalFee;
	}

	public int getHospitalFee() {
		return hospitalFee;
	}

	public void setHospitalFee(int hospitalFee) {
		this.hospitalFee = hospitalFee;
	}
}
