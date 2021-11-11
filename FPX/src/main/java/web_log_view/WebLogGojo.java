package web_log_view;

import java.util.ArrayList;

/**
 * 用于转json
 */
public class WebLogGojo {

    private ArrayList<String> dates;
    private ArrayList<Float> data;

    public WebLogGojo() {
    }

    public WebLogGojo(ArrayList<String> dates, ArrayList<Float> data) {
        this.dates = dates;
        this.data = data;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public ArrayList<Float> getData() {
        return data;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public void setData(ArrayList<Float> data) {
        this.data = data;
    }


}
