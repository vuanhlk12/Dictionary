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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.io.*;

public class TuDienDemo {
	private final File f = new File("data\\Word.txt");
	public static Scanner input = new Scanner(System.in);

    HashMap<String, String> hMap;
	ArrayList<String> word;
	
	
	public void addfromfile() {

		int i=0;
        hMap = new HashMap();
        word = new ArrayList<String>();
        //String s = "";
        try {
            Scanner sc = new Scanner(f);
            try {
                while (sc.hasNextLine()) {
                	String[] str = sc.nextLine().split("#");
					word.add(str[1].trim());
                    hMap.put(str[1].trim(), "#"+str[1]+"#"+str[2]+"#"+str[3]+"#"+str[4]);
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                Collections.sort(word);
                if (sc != null) {
                    sc.close();
                }
            }
        } catch (Exception ep) {
            System.out.println(ep.getMessage());
        }
    }
	
	public void newfile() {// có cái hàm này là để
		// 1 là chưa có file từ thì tạo mới luôn
		// 2 là sau khi chỉnh sửa thì xóa file cũ ghi vào file mới

		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			String data = "";

			// kiểm tra nếu file chưa có thì tạo file mới
			if (!f.exists()) {
				f.createNewFile();
			}
			// true = append file
			fw = new FileWriter(f.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			int i;
			for (i = 0; i < this.word.size(); i++) {
				data = hMap.get(this.word.get(i))+"\n";
				bw.write(data);
				data = "";
			}

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
		f.delete();
		this.newfile();
	}
	
	public boolean search(String a) {
		int i;
		for (i = 0; i < this.word.size(); i++) {
			if (this.word.get(i).startsWith(a)) {
				String resulf1 = this.word.get(i);
				String resulf = this.hMap.get(resulf1);
				String[] p = resulf.split("#");
				System.out.println("Từ: " + p[1]+ ". Loại từ: "+ p[2]+". Cách đọc: "+p[3]+". Nghĩa: "+p[4]);
				break;
			}
		}
		if(i<=this.word.size()-1) return true;
		else return false;
	}
	public void print() {
		int i;
		for (i = 0; i < this.word.size(); i++) {
				String resulf1 = this.word.get(i);
				String resulf = this.hMap.get(resulf1);
				String[] p = resulf.split("#");
				System.out.println("STT: " + i + ". Từ: " + p[1]+ ". Loại từ: "+ p[2]+". Cách đọc: "+p[3]+". Nghĩa: "+p[4]);
		}
	}
	
	public boolean checkHad(String tu) {
		boolean temp = true;
		for (int e = 0; e < this.word.size(); e++) {
			if (this.word.get(e).equals(tu)) {
				temp=false;
				return temp;
			}
		}
		return temp;
	}
	public void add(String tu, String nghia, String loai, String cachdoc) {	
		hMap.put(tu,"#"+tu+"#"+nghia+"#"+loai+"#"+cachdoc);
		word.add(tu);
		Collections.sort(word);

	}
	
	public boolean delete(String a) {
		int i;
		int n= this.word.size();
		boolean t=true;
		for (i = 0; i <n; i++) {
			if(this.word.get(i).equals(a)) {
				hMap.remove(a);
				word.remove(a);
				if(i == n-1)
					t=false;
				break;
			}
		}
		if(i==n-1 && t) return false;
		else return true;
	}

}

