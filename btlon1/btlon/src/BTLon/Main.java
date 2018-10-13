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
//import java.util.Scanner;

public class Main {

    /**
     * tổng kết lại thì đã có các hàm sau:
     * thêm từ
     * xóa từ
     * tìm từ
     * tìm nghĩa
     * google dịch
     * google âm thanh
     */
    public static void main(String[] args) {
        Tudien a = new Tudien();

        a.addfromfile();
       // a.printEV();
       // a.printEV();
       // a.refreshfile();
       // Scanner input = new Scanner(System.in);
       /* try {
            System.out.println(GoogleTranslate.translate("en", "vi", "hello world"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Voice b;
        b = new Voice();
        b.setLine("một hai");
        System.out.println(a.getSoluong());
       
        //b.speak();*/
        a.refreshMatch();
        a.Searchchar('a', 0);
        a.Searchchar('m', 1);
        a.Searchchar('p', 2);
        a.printTrue();


        ;

    }

}
