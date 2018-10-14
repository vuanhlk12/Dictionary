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

	public final int capacity = 200000;
	public WORD[] words = new WORD[capacity];
	public WORD[] words2 = new WORD[capacity];
	private final File f = new File("C:\\Users\\FEELSBADMAN\\Desktop\\btlon\\data\\Word.txt");
	int soluong = 0;
	int startsearch = 0;
	int endsearch = this.getSoluong();
	public static Scanner input = new Scanner(System.in);

	public int getSoluong() {
		int i = 0;
		while (words[i] != null) {
			i++;
		}
		endsearch = i;
		return i;
	}

	public int getSoluongTrue() {
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
					String[] str = sc.nextLine().split("#");
					this.words[i] = new WORD(str[1], str[2], str[3], str[4], i);
					soluong++;
					i++;
				}
			} catch (Exception e) {
			} finally {
				if (sc != null) {
					sc.close();
				}
			}
		} catch (Exception ex) {
		}
	}

	public void newfile() {// có cái hàm này là để
		// 1 là chưa có file từ thì tạo mới luôn
		// 2 là sau khi chỉnh sửa thì xóa file cũ ghi vào file mới

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String data = "";

			File file = new File("data\\Word.txt");

			// kiểm tra nếu file chưa có thì tạo file mới
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			int i;
			for (i = 0; i < this.getSoluong(); i++) {
				data = "#" + this.words[i].tu + "#" + this.words[i].loai + "#" + this.words[i].cachdoc + "#"
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
			}
		}
	}

	void refreshfile() {// xóa file đi và tạo file mới
		this.sort(0, this.getSoluong() - 1);
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
			this.sort(0, this.getSoluong());
		} else {
			System.out.println("đã vượt quá capacity ");
		}

	}

	public void refreshMatch() {
		int i;
		startsearch = 0;
		endsearch = this.getSoluong();
		for (i = 0; i < this.getSoluong(); i++) {
			this.words[i].match = true;
		}
	}

	public void Searchchar(char a, int i) {
		int j;
		for (j = this.startsearch; j < this.endsearch; j++) {
			if (words[j].match == false)
				continue;
			if (j > 0) {
				if (words[j].match == false && words[j - 1].match == true)
					break;
				if (words[j].match == true && words[j - 1].match == false)
					this.startsearch = j;
			}
			if (words[j].tu.charAt(i) != a) {
				words[j].match = false;
				if (j > 0) {
					if (words[j - 1].match == true) {
						this.endsearch = j;
						break;
					}
				}
			}
		}

	}

	public boolean Search(String a) {
		boolean check = true;
		int i, n = a.length();
		this.refreshMatch();
		for (i = 0; i < n; i++) {
			this.Searchchar(a.charAt(i), i);
		}
		if (this.startsearch + 1 == this.endsearch) {
			System.out.println("Không tim đc từ cần tìm");
			check = false;
		}
		return check;
	}

	public void printTrue() {
		int j;
		for (j = this.startsearch; j <= this.endsearch; j++) {
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
			soluong--;
			words[soluong] = null;

		}

	}

	public void printEV() {// in danh sách anh việt
		int i;
		for (i = 0; i < soluong; i++) {
			this.words[i].getinfo();
		}

	}

	public void resetstt() {
		int i;
		for (i = 0; i < this.getSoluong(); i++) {
			words[i].stt = i;
		}
	}

	public void merging(int low, int mid, int high) {
		int l1, l2, i;

		for (l1 = low, l2 = mid + 1, i = low; l1 <= mid && l2 <= high; i++) {
			if (words[l1].tu.compareTo(words[l2].tu) < 0)
				words2[i] = words[l1++];
			else
				words2[i] = words[l2++];
		}

		while (l1 <= mid)
			words2[i++] = words[l1++];

		while (l2 <= high)
			words2[i++] = words[l2++];

		for (i = low; i <= high; i++)
			words[i] = words2[i];
		;

	}

	public void sort(int low, int high) {
		int mid;
		if (low < high) {
			mid = (low + high) / 2;
			sort(low, mid);
			sort(mid + 1, high);
			merging(low, mid, high);
		}
		;

	}

}
