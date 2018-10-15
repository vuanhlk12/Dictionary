/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btloncmd;

/**
 *
 * @author FEELSBADMAN
 */

import java.io.IOException;
import java.util.Scanner;
import java.text.ParseException;

public class Main {

	private static Scanner input;
	public static void main(String[] args) {
		TuDienDemo a1 = new TuDienDemo();
		boolean loop = true;
		input = new Scanner(System.in);

		a1.addfromfile();
		try {
			while (loop) {
				System.out.println("Menu tu dien b: ");
				System.out.println("1. In danh sách");
				System.out.println("2. Tìm từ");
				System.out.println("3. Thêm từ");
				System.out.println("4. Xóa từ");
				System.out.println("5. Google translate");
				System.out.println("6. Google voice //bản này chưa có");
				System.out.println("7. Lưu thay đổi");
				boolean temp;
				input = new Scanner(System.in);
				int x;
				String item;
				x = input.nextInt();
				switch (x) {
				case 1://in
					a1.print();
					break;
				case 2://find
					System.out.println("Nhập từ cần tìm (cần nhập đúng các ký tự ở đầu từ): ");
					input = new Scanner(System.in);
					item = input.nextLine();
					temp = a1.search(item);
					if (temp == false)
						System.out.println("Không tìm thấy từ: " + item);
					break;
				case 3://add
					String tu;
					String nghia;
					String loai;
					String cachdoc;
					input = new Scanner(System.in);
					System.out.println("Nhập từ :");
					tu = input.nextLine().toLowerCase();
					temp=a1.checkHad(tu);
					if (temp == false) {
						System.out.println("Đã có từ này.");
						break;
					}		
					input = new Scanner(System.in);
					System.out.println("Nhập nghĩa :");
					nghia = input.nextLine();

					input = new Scanner(System.in);
					System.out.println("Nhập loại :");
					loai = input.nextLine();

					input = new Scanner(System.in);
					System.out.println("Nhập cách đọc :");
					cachdoc = input.nextLine();
					
					break;
				case 4://delete
					System.out.println("Nhập từ cần xóa (nhập chính xác): ");
					input = new Scanner(System.in);
					item = input.nextLine();
					temp = a1.delete(item);
					if (temp == true)
						System.out.println("Đã xóa từ: " + item);
					else
						System.out.println("Không tìm thấy từ: " + item);
					break;
                                case 5://gg
					System.out.println("Nhập từ tiếng anh (có thể dịch cả câu): ");
					input = new Scanner(System.in);
					item = input.nextLine();
					try {
						System.out.println(GoogleTranslate.translate("en", "vi", item));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6://gg
					Voice b;
					System.out.println("Nhập từ tiếng anh: ");
					input = new Scanner(System.in);
					item = input.nextLine();
					b = new Voice();
                                        b.setLanguage("en");
					b.setLine(item);
					b.speak();
					break;
				case 7:
					a1.refreshfile();
					break;
				default:
					break;
				}
				String question;

				System.out.println("Dùng tiếp? (Y/N) :");

				input = new Scanner(System.in);
				question = input.nextLine().toUpperCase();
				if (question.equals("N")) {
					loop = false;
					System.out.println("Chương trình kết thúc.");
				} else {
                                }

			}
		} catch (Exception e) {
		}
		;

		;

	}

}
