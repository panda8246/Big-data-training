package Test;

/**
 * MOdle层
 */
public class WebLogModel {

    private String ID;
    private String DataStr;
    private Float AvgPvNum;

    public WebLogModel(String ID, String dataStr, Float avgPvNum) {
        this.ID = ID;
        DataStr = dataStr;
        AvgPvNum = avgPvNum;
    }

    public WebLogModel() {
    }

    public String getID() {
        return ID;
    }

    public String getDataStr() {
        return DataStr;
    }

    public Float getAvgPvNum() {
        return AvgPvNum;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setDataStr(String dataStr) {
        DataStr = dataStr;
    }

    public void setAvgPvNum(Float avgPvNum) {
        AvgPvNum = avgPvNum;
    }


    @Override
    public String toString() {
        return "WebLogModel{" +
                "ID='" + ID + '\'' +
                ", DataStr='" + DataStr + '\'' +
                ", AvgPvNum=" + AvgPvNum +
                '}';
    }
}
