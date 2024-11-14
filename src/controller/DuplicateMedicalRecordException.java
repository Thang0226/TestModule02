package controller;

public class DuplicateMedicalRecordException extends Exception {
	public DuplicateMedicalRecordException() {
	}

	public DuplicateMedicalRecordException(String message) {
		super(message);
	}
}
