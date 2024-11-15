package controller;

import model.MedicalRecord;
import model.VIPMedicalRecord;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidateRecord {
	public ValidateRecord() {}

	public boolean validate(MedicalRecord record) {
		return passedRecordCode(record.getRecordCode()) && passedPatientCode(record.getPatientCode()) &&
				passedDate(record.getAdmissionDate()) && passedDate(record.getDischargeDate()) &&
				passedPeriod(record) && passedVIPType(record);
	}

	private boolean passedRecordCode(String recordCode) {
		return Pattern.matches("BA-\\d{3}", recordCode);
	}

	private boolean passedPatientCode(String patientCode) {
		return Pattern.matches("BN-\\d{3}", patientCode);
	}

	private boolean passedDate(String admissionDate) {
		return Pattern.matches("^(0[1-9])|([12][0-9])|(3[01])/(0[1-9])|(1[0-2])/\\d{4}$", admissionDate);
	}

	private boolean passedPeriod(MedicalRecord record) {
		LocalDate admissionLocalDate = createLocalDate(record.getAdmissionDate());
		LocalDate dischargeLocalDate = createLocalDate(record.getDischargeDate());
		if (admissionLocalDate.compareTo(dischargeLocalDate) <= 0) {
			return true;
		}
		return false;
	}

	private LocalDate createLocalDate(String date) {
		String[] dateArray = date.split("/");
		return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]),
				Integer.parseInt(dateArray[0]));
	}

	private boolean passedVIPType(MedicalRecord record) {
		if (record instanceof VIPMedicalRecord) {
			return Pattern.matches("^(VIP) (I{1,3})$", ((VIPMedicalRecord) record).getVIPType());
		}
		return true;
	}
}
