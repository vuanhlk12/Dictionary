package BTLon;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TuDienDemo {
	private final File f = new File("data\\Word.dic");
	public static Scanner input = new Scanner(System.in);

    HashMap<String, String> hMapEV;
	ArrayList<String> word;
	
	/*public void addfromfile() {// lấy từ từ file
		hMapEV = new HashMap<String, String>();
		word = new ArrayList<String>();
		int i=0;
		try {
			Scanner sc = new Scanner(f);
			try {
				while (sc.hasNextLine()) {
					String[] str = sc.nextLine().split("#");
					word.add(str[1]);
                    hMapEV.put(str[1].trim(), "\nSTT: "+i +"|từ: " + str[1]+"| nghĩa: "+str[4]+"| loại: "+str[2]+"| cách đọc: "+str[3]);
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
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
	*/
	
	public void addfromfile() {

		int i=0;
        hMapEV = new HashMap();
        word = new ArrayList<String>();
        File f = new File("data\\Word.dic");
        //String s = "";
        try {
            Scanner sc = new Scanner(f);
            try {
                while (sc.hasNextLine()) {
                	String[] str = sc.nextLine().split("#");
					word.add(str[1]);
                    hMapEV.put(str[1].trim(), "\nSTT: "+i +"|từ: " + str[1]+"| nghĩa: "+str[4]+"| loại: "+str[2]+"| cách đọc: "+str[3]);
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
	
	public void Ssearch2(String a) {
		int i;
		for (i = 0; i < this.word.size(); i++) {
			if (this.word.get(i).startsWith(a)) {
				String resulf1 = this.word.get(i);
				String resulf = this.hMapEV.get(resulf1);
				System.out.println(resulf);
			}
		}
	}
	public void print() {
		int i;
		for (i = 0; i < this.word.size(); i++) {
				String resulf1 = this.word.get(i);
				String resulf = this.hMapEV.get(resulf1);
				System.out.println(resulf);
		}
	}

}
