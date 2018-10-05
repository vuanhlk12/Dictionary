/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author FEELSBADMAN
 */
public class WORD {
    String tu, nghia, loai;
    int stt;
    WORD (String a, String b, String c, int d){
        tu=a;
        nghia=b;
        loai=c;
        stt=d;
    }
    void getinfo(){
        System.out.println(tu+"  "+nghia+"   "+loai+"    "+stt);
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
