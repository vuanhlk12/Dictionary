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
public class WORD {
    String tu, nghia, loai, cachdoc;
    int stt;
    boolean match;
    WORD (String a, String b, String c, String e, int d){
        tu=a;
        loai=b;
        cachdoc=c;
        stt=d;
        nghia=e;
        match=true;
    }
    void getinfo(){
        System.out.println(tu+" + "+nghia+" +  "+loai+"  +  "+cachdoc+"  +  "+stt);
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
