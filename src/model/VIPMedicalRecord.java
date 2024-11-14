package model;

public class VIPMedicalRecord extends MedicalRecord {
	private String VIPType;     // VIP I/VIP II/VIP III
	private int VIPPeriod;      // count by days

	public VIPMedicalRecord(String recordCode, String patientCode, String patientName, String admissionDate,
	                        String dischargeDate, String admissionReason, String VIPType) {
		super(recordCode, patientCode, patientName, admissionDate,
				dischargeDate, admissionReason);
		this.VIPType = VIPType;
	}

	public String getVIPType() {
		return VIPType;
	}

	public void setVIPType(String VIPType) {
		this.VIPType = VIPType;
	}

	public int getVIPPeriod() {
		return VIPPeriod;
	}

	public void setVIPPeriod(int VIPPeriod) {
		this.VIPPeriod = VIPPeriod;
	}
}
