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

    public final int capacity = 100;
    public WORD[] words = new WORD[capacity];
    public WORD[] words2 = new WORD[capacity];//có 2 cái words là vì 1 cái anh->việt 1 cái việt->anh
    private final File f = new File("data\\Word.dic");
    int soluong = 0;
    public static Scanner input = new Scanner(System.in);
    int getSoluong() {//chưa thấy cần xài lắm, chả hiểu sao ban đầu lại code cái này
        int i = 0;
        while (words[i] != null) {
            i++;
        }
        return i;
    }

    public void addfromfile() {//lấy từ từ file

        try {
            int i = soluong;
            Scanner sc = new Scanner(f);
            try {
                while (sc.hasNextLine()) {
                    String[] str = sc.nextLine().split(":");
                    //System.out.println(str[0]+"   "+str[1]);
                    this.words[i] = new WORD(str[1], str[2], str[3], i);
                    this.words2[i] = new WORD(str[2], str[1], str[3], i);
                    soluong++;
                    //this.words[i].getinfo();
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

    public void newfile() {//có cái hàm này là để
        //1 là chưa có file từ thì tạo mới luôn
        //2 là sau khi chỉnh sửa thì xóa file cũ ghi vào file mới

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
                data = ":" + this.words[i].tu + ":" + this.words[i].nghia + ":" + this.words[i].loai + "\n";
                bw.write(data);
                data = "";
            }

            System.out.println("Xong");
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

    void refreshfile() {//xóa file đi và tạo file mới đó
        f.delete();
        this.newfile();
    }

    public void addnew() {// thêm từ mới thôi
        addNew:

        while (true) {
            int stt = soluong + 1;
            String tu;
            String nghia;
            String loai;

            if (stt < capacity) {
                input = new Scanner(System.in);
                System.out.println("STT :" + stt);

                // nhap word
                System.out.println("Nhap tu :");
                tu = input.nextLine().toLowerCase();

                for (int e = 0; e < soluong; e++) {
                    if (words[e].tu.equals(tu)) {
                        System.out.println("trong tu dien da co tu nay");
                        //input.nextLine();
                        continue addNew;
                    }
                }

                // nhap nghia
                input = new Scanner(System.in);
                System.out.println("Nhap nghia :");
                nghia = input.nextLine();

                // nhap loai tu
                input = new Scanner(System.in);
                System.out.println("Nhap loai tu :");
                loai = input.nextLine();

                words[soluong] = new WORD(tu, nghia, loai, stt);
                soluong++;
            } else {
                System.out.println("da vuot qua luong tu cho phep nhap, khong the them nua !! ");
            }

            String question;

            do {
                System.out.println(" Ban muon them tu nua:(Y/N) :");

                input = new Scanner(System.in);
                question = input.next().toUpperCase();

                if (question.equals("N")) {
                    break addNew;
                }

            } while (!question.equals("N") && !question.equals("Y"));

        }
    }


    public void wordSearch() {//tìm từ
        int d = 0;
        String keyWord;
        input = new Scanner(System.in);

        if (soluong == 0) {
            System.out.println("Tu Dien rong, ban nhap tu di nhe");
        } else {
            System.out.println("Nhap tu khoa: ");
            keyWord = input.next().toLowerCase();

            for (int b = 0; b < soluong; b++) {
                if (words[b].tu.equals(keyWord)) {
                    d = 1;
                    words[b].getinfo();
                }
            }
        }
        if (d == 0 & soluong > 0) {
            System.out.println(" Khong co tu nao giong tu ban muon tim !!");
        }
    }
    
    public void wordSearchVE() {//dùng để tìm từ theo nghĩa, cũng có thể coi là tìm từ tiếng việt dịch qua tiếng anh
        int d = 0;
        String keyWord;
        input = new Scanner(System.in);

        if (soluong == 0) {
            System.out.println("Tu Dien rong, ban nhap tu di nhe");
        } else {
            System.out.println("Nhap tu khoa: ");
            keyWord = input.nextLine().toLowerCase();

            for (int b = 0; b < soluong; b++) {
                if (words[b].nghia.equals(keyWord)) {
                    d = 1;
                    words[b].getinfo();
                }
            }
        }
        if (d == 0 & soluong > 0) {
            System.out.println(" Khong co tu nao giong tu ban muon tim !!");
        }
    }

    // phuong thuc xoa tu trong tu dien
    public void deleteWord() {
        String delWord;
        int m = 0;

        if (soluong == 0) {
            System.out.printf("\n Tu Dien rong");
        } else {
            System.out.printf("\n Nhap tu ban muon xoa :");
            delWord = input.next().toLowerCase();

            int i, j;
            for (i = 0; i < soluong; i++) {
                if (words[i].tu.equals(delWord)) {
                    for (j = i; j < soluong - 1; j++) {
                        words[j] = words[j + 1];
                    }
                    m = 1;
                }
            }
        }

        if (m == 1) {
            System.out.printf("\n Ban da xoa thanh cong, ban co the kiem tra o list\n");
            soluong--;
        }

        if (m == 0 & soluong > 0) {
            System.out.print("\n\n--- Khong tim duoc tu muon xoa, Nhap lai tu----!!!!!!!");
        }

    }

    public void printEV() {//in danh sách anh việt
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

}
