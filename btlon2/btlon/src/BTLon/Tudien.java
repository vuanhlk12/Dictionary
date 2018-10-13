/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTLon;

import java.util.Scanner;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author FEELSBADMAN
 */
public class Tudien {

	public final int capacity = 1000;
	public WORD[] words = new WORD[capacity];
	public WORD[] words2 = new WORD[capacity];// có 2 cái words là vì 1 cái anh->việt 1 cái việt->anh
	private final File f = new File("data\\Word.dic");
	int soluong = this.getSoluong();
	public static Scanner input = new Scanner(System.in);

	int getSoluong() {
		int i = 0;
		while (words[i] != null) {
			i++;
		}
		return i;
	}

	int getSoluongTrue() {
		int i, dem = 0;
		for (i = 0; i < this.getSoluong(); i++) {
			if (this.words[i].match == true)
				dem++;
		}

		return dem;
	}

	public void addfromfile() {// lấy từ từ file
		try {
			int i = this.getSoluong();
			Scanner sc = new Scanner(f);
			try {
				while (sc.hasNextLine()) {
					String[] str = sc.nextLine().split(":");
					this.words[i] = new WORD(str[1], str[2], str[3], str[4], i);
					this.words2[i] = new WORD(str[4], str[2], str[3], str[1], i);
					soluong++;
					i++;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (sc != null) {
					sc.close();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void newfile() {// có cái hàm này là để
		// 1 là chưa có file từ thì tạo mới luôn
		// 2 là sau khi chỉnh sửa thì xóa file cũ ghi vào file mới

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String data = "";

			File file = new File("data\\Word.dic");

			// kiểm tra nếu file chưa có thì tạo file mới
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			int i;
			for (i = 0; i < soluong; i++) {
				data = ":" + this.words[i].tu + ":" + this.words[i].loai + ":" + this.words[i].cachdoc + ":"
						+ this.words[i].nghia + "\n";
				bw.write(data);
				data = "";
			}

			// System.out.println("Xong");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	void refreshfile() {// xóa file đi và tạo file mới 
		this.sort();
		f.delete();
		this.newfile();
	}

	public void addnew() {// thêm từ mới

		int stt = soluong + 1;
		String tu;
		String nghia;
		String loai;
		String cachdoc;

		if (stt < capacity) {
			input = new Scanner(System.in);
			System.out.println("STT :" + stt);

			// nhap word
			System.out.println("Nhap tu :");
			tu = input.nextLine().toLowerCase();

			for (int e = 0; e < soluong; e++) {
				if (words[e].tu.equals(tu)) {
					System.out.println("đã có từ này");
					// input.nextLine();
				}
			}

			// nhap nghia
			input = new Scanner(System.in);
			System.out.println("Nhap nghĩa :");
			nghia = input.nextLine();

			// nhap loai tu
			input = new Scanner(System.in);
			System.out.println("Nhập loại :");
			loai = input.nextLine();

			input = new Scanner(System.in);
			System.out.println("Nhập cách đọc :");
			cachdoc = input.nextLine();

			words[soluong] = new WORD(tu, loai, cachdoc, nghia, stt);
			soluong++;
			this.sort();
		} else {
			System.out.println("đã vượt quá capacity ");
		}

	}

	public void refreshMatch() {
		int i;
		for (i = 0; i < this.getSoluong(); i++) {
			this.words[i].match = true;
		}
	}

	public void Searchchar(char a, int i) {
		int j;
		int n = this.getSoluong();
		for (j = 0; j < n; j++) {
			if (words[j].match == false)
				continue;
			else if (words[j].match == false && words[j - 1].match == true)
				break;
			if (words[j].tu.charAt(i) != a) {
				words[j].match = false;
			}
		}

	}

	public boolean Search(String a) {
		boolean check =true;
		int i, n = a.length();
		this.refreshMatch();
		for (i = 0; i < n; i++) {
			this.Searchchar(a.charAt(i), i);
		}
		if (this.getSoluongTrue() == 0) {
			System.out.println("Không tim đc từ cần tìm");
			check=false;
		}
		return check;
	}

	public void printTrue() {
		int n = this.getSoluong();
		int j;
		for (j = 0; j < n; j++) {
			if (this.words[j].match == true) {
				this.words[j].getinfo();
			}
		}
	}

	// phuong thuc xoa tu trong tu dien
	public void deleteWord() {
		String delWord;
		System.out.println("Nhập từ xóa :");
		delWord = input.next().toLowerCase();
		boolean check = this.Search(delWord);
		if (check == true) {
			System.out.println("Đây là danh sách các từ bạn cần tìm, nhập stt của từ bạn cần xóa");
			this.printTrue();
			int i = input.nextInt(), j;
			for (j = i; j < soluong - 1; j++) {
				words[j] = words[j + 1];
			}

		}

	}

	public void printEV() {// in danh sách anh việt
		int i;
		for (i = 0; i < soluong; i++) {
			this.words[i].getinfo();
		}

	}

	public void printVE() {
		int i;
		for (i = 0; i < soluong; i++) {
			this.words2[i].getinfo();
		}

	}

	public void sort() {
		int i, j;
		WORD temp = new WORD();
		for (i = 0; i < this.getSoluong() - 1; i++) {
			for (j = i; j < this.getSoluong(); j++) {
				if (this.words[i].tu.compareTo(this.words[j].tu) > 0) {
					temp = this.words[i];
					this.words[i] = this.words[j];
					this.words[j] = temp;
				}
			}
		}
	}

}
