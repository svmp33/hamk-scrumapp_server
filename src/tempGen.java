/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author erami
 */
public class tempGen {

    private double muuttuja;
    String date, spv, skk, svuosi, time;
    int pv, kk, vuosi;


    public String addDate() {

        String[] parts = date.split("-", 3);
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        vuosi = Integer.parseInt(part1);
        kk = Integer.parseInt(part2);
        pv = Integer.parseInt(part3);
        pv = pv + 1;
        svuosi = "" + vuosi;
        String skk = String.format("%02d", kk);
        String spv = String.format("%02d", pv);
        date = (svuosi + "-" + skk + "-" + spv);
        return date;
    }
    

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    
    public String newDate() {
        String[] parts = date.split("-", 3);
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        vuosi = Integer.parseInt(part1);
        kk = Integer.parseInt(part2);
        pv = Integer.parseInt(part3);
        pv = 1;
        kk = kk+1;
        svuosi = "" + vuosi;
        String skk = String.format("%02d", kk);
        String spv = String.format("%02d", pv);
        date = (svuosi + "-" + skk + "-" + spv);
        return date;
        
    }
    public String newYear() {
        String[] parts = date.split("-", 3);
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        vuosi = Integer.parseInt(part1);
        kk = Integer.parseInt(part2);
        pv = Integer.parseInt(part3);
        pv = 1;
        kk = 1;
        vuosi = vuosi + 1;
        svuosi = "" + vuosi;
        String skk = String.format("%02d", kk);
        String spv = String.format("%02d", pv);
        date = (svuosi + "-" + skk + "-" + spv);
        return date;
        
    }

    public double getRandomtemp() {
        double range = (22 - 18);
        muuttuja = (Math.random() * range) + 18;
        double muuttuja1 = Math.round((muuttuja) * 10) / 10.0;
        return muuttuja1;
    }
}
