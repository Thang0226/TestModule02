package view;

import controller.MedicalRecordManager;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final String filePath = "data/medical_records.csv";
		MedicalRecordManager manager = new MedicalRecordManager(filePath);
		Scanner input = new Scanner(System.in);
		int choice;
		while(true) {
			System.out.print("""
				\n\t--CHƯƠNG TRÌNH QUẢN LÝ BỆNH ÁN--
				Chọn chức năng theo số để tiếp tục
					1. Thêm mới
					2. Xóa
					3. Xem danh sách các bệnh án
					4. Thoát
					Chọn chức năng: """);
			choice = input.nextInt();
			switch (choice) {
				case 1:
					manager.addNewRecord();
					break;
				case 2:
					manager.deleteRecord();
					break;
				case 3:
					manager.displayRecords();
					break;
				case 4:
					System.exit(0);
				default:
					System.out.println("Không có chức năng này!");
			}
		}
	}
}
