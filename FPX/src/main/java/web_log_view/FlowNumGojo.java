package web_log_view;

import java.util.ArrayList;

public class FlowNumGojo {
    private ArrayList<String> dates;
    private ArrayList<Integer> pVNum;
    private ArrayList<Integer> uVNum;
    private ArrayList<Integer> iPNum;
    private ArrayList<Integer> newUvNum;
    private ArrayList<Integer> visitNum;

    public FlowNumGojo() {
    }

    public FlowNumGojo(ArrayList<String> dates, ArrayList<Integer> pVNum, ArrayList<Integer> uVNum, ArrayList<Integer> iPNum, ArrayList<Integer> newUvNum, ArrayList<Integer> visitNum) {
        this.dates = dates;
        this.pVNum = pVNum;
        this.uVNum = uVNum;
        this.iPNum = iPNum;
        this.newUvNum = newUvNum;
        this.visitNum = visitNum;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<Integer> getpVNum() {
        return pVNum;
    }

    public ArrayList<Integer> getuVNum() {
        return uVNum;
    }

    public ArrayList<Integer> getiPNum() {
        return iPNum;
    }

    public ArrayList<Integer> getNewUvNum() {
        return newUvNum;
    }

    public ArrayList<Integer> getVisitNum() {
        return visitNum;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public void setpVNum(ArrayList<Integer> pVNum) {
        this.pVNum = pVNum;
    }

    public void setuVNum(ArrayList<Integer> uVNum) {
        this.uVNum = uVNum;
    }

    public void setiPNum(ArrayList<Integer> iPNum) {
        this.iPNum = iPNum;
    }

    public void setNewUvNum(ArrayList<Integer> newUvNum) {
        this.newUvNum = newUvNum;
    }

    public void setVisitNum(ArrayList<Integer> visitNum) {
        this.visitNum = visitNum;
    }
}
