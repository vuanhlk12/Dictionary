/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTLon;

/**
 *
 * @author FEELSBADMAN
 */
import java.io.IOException;
import com.darkprograms.speech.translator.GoogleTranslate;
import java.util.Scanner;

public class Main {

	/**
	 * tổng kết lại thì đã có các hàm sau: thêm từ xóa từ tìm từ tìm nghĩa google
	 * dịch google âm thanh tìm từ theo ký tự(tiếng anh) sort
	 */
	public static void main(String[] args) {
		Tudien a = new Tudien();
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		a.addfromfile();
		a.sort();
		try {
			while (loop) {
				System.out.println("Menu: ");
				System.out.println("1. In danh sách");
				System.out.println("2. Tìm từ");
				System.out.println("3. Thêm từ");
				System.out.println("4. Xóa từ");
				System.out.println("5. Google translate");
				System.out.println("6. Google voice");
				System.out.println("7. Lưu thay đổi");

				
				input = new Scanner(System.in);
				int x;
				String item;
				x = input.nextInt();
				switch (x) {
				case 1:
					a.printEV();
					break;
				case 2:
					System.out.println("Nhập từ cần tìm (cần nhập đúng các ký tự ở đầu từ): ");
					input = new Scanner(System.in);
					item = input.nextLine();
					a.Search(item);
					a.printTrue();
					break;
				case 3:
					a.addnew();
					break;
				case 4:
					a.deleteWord();
					break;
				case 5:
					System.out.println("Nhập từ tiếng anh (có thể dịch cả câu): ");
					input = new Scanner(System.in);
					item = input.nextLine();
					try {
						System.out.println(GoogleTranslate.translate("en", "vi", item));

					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6:
					Voice b;
					System.out.println("Nhập từ tiếng anh hoặc tiếng việt: ");
					input = new Scanner(System.in);
					item = input.nextLine();
					b = new Voice();
					b.setLine(item);
					b.speak();
					break;
				case 7:
					a.refreshfile();
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
				} else
					continue;

			}
		} catch (Exception e) {
		}
		;

		/*
		 * a.refreshMatch(); a.Searchchar('a', 0); a.Searchchar('m', 1);
		 * a.Searchchar('p', 2); a.printTrue(); a.sort(); a.printEV(); a.refreshfile();
		 */

		;

	}

}
