package Contract;

import java.util.Date;
import java.util.List;

public class СellularContract extends AbstractContract {
    private int minutesNum;
    private int gbNum;
    private int smsNum;

    public СellularContract(Date startDate, Date stopDate, List listOfClient, int minutesNum, int gbNum, int smsNum) {
        super(startDate, stopDate, listOfClient);
        this.minutesNum = minutesNum;
        this.gbNum = gbNum;
        this.smsNum = smsNum;
    }

    public int getMinutesNum() {
        return minutesNum;
    }

    public void setMinutesNum(int minutesNum) {
        this.minutesNum = minutesNum;
    }

    public int getGbNum() {
        return gbNum;
    }

    public void setGbNum(int gbNum) {
        this.gbNum = gbNum;
    }

    public int getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(int smsNum) {
        this.smsNum = smsNum;
    }
}
