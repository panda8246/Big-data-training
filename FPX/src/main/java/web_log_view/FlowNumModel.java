package web_log_view;

public class FlowNumModel {

    private String id;
    private String dateStr;
    private int pVNum;
    private int uVNum;
    private int iPNum;
    private int newUvNum;
    private int visitVNum;

    public FlowNumModel() {
    }

    public FlowNumModel(String id, String dateStr, int pVNum, int uVNum, int iPNum, int newUvNum, int visitVNum) {
        this.id = id;
        this.dateStr = dateStr;
        this.pVNum = pVNum;
        this.uVNum = uVNum;
        this.iPNum = iPNum;
        this.newUvNum = newUvNum;
        this.visitVNum = visitVNum;
    }

    public String getId() {
        return id;
    }

    public String getDateStr() {
        return dateStr;
    }

    public int getpVNum() {
        return pVNum;
    }

    public int getuVNum() {
        return uVNum;
    }

    public int getiPNum() {
        return iPNum;
    }

    public int getNewUvNum() {
        return newUvNum;
    }

    public int getVisitVNum() {
        return visitVNum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public void setpVNum(int pVNum) {
        this.pVNum = pVNum;
    }

    public void setuVNum(int uVNum) {
        this.uVNum = uVNum;
    }

    public void setiPNum(int iPNum) {
        this.iPNum = iPNum;
    }

    public void setNewUvNum(int newUvNum) {
        this.newUvNum = newUvNum;
    }

    public void setVisitVNum(int visitVNum) {
        this.visitVNum = visitVNum;
    }
}