package model;

import java.util.Scanner;

public class RecordFactory {
	private static RecordFactory factory;

	private RecordFactory() {
	}
	// Singleton
	public static RecordFactory getInstance() {
		if (factory == null) {
			factory = new RecordFactory();
		}
		return factory;
	}

	// Factory method
	public MedicalRecord getMedicalRecord(RecordType type, String recordCode) {
		return switch (type) {
			case REGULAR -> inputRegularRecord(recordCode);
			case VIP -> inputVIPRecord(recordCode);
			default -> null;
		};
	}

	private MedicalRecord inputRegularRecord(String recordCode) {
		Scanner input = new Scanner(System.in);
		System.out.print("Mã bệnh nhân (BN-XXX): ");
		String patientCode = input.nextLine();
		System.out.print("Tên bệnh nhân: ");
		String patientName = input.nextLine();
		System.out.print("Ngày vào viện (dd/MM/yyyy): ");
		String admissionDate = input.nextLine();
		System.out.print("Ngày ra viện (dd/MM/yyyy): ");
		String dischargeDate = input.nextLine();
		System.out.print("Lý do nhập viện: ");
		String admissionReason = input.nextLine();
		System.out.print("Viện phí: ");
		int fee = input.nextInt();

		return new RegularMedicalRecord(recordCode, patientCode, patientName, admissionDate, dischargeDate,
				admissionReason, fee);
	}

	private MedicalRecord inputVIPRecord(String recordCode) {
		Scanner input = new Scanner(System.in);
		System.out.print("Mã bệnh nhân (BN-XXX): ");
		String patientCode = input.nextLine();
		System.out.print("Tên bệnh nhân: ");
		String patientName = input.nextLine();
		System.out.print("Ngày vào viện (dd/MM/yyyy): ");
		String admissionDate = input.nextLine();
		System.out.print("Ngày ra viện (dd/MM/yyyy): ");
		String dischargeDate = input.nextLine();
		System.out.print("Lý do nhập viện: ");
		String admissionReason = input.nextLine();
		System.out.print("Loại VIP (VIP I/VIP II/VIP III): ");
		String VIPType = input.nextLine();

		return new VIPMedicalRecord(recordCode, patientCode, patientName, admissionDate, dischargeDate,
				admissionReason, VIPType);
	}
}
