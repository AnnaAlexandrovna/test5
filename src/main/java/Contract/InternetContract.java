package Contract;

import java.util.Date;
import java.util.List;

public class InternetContract extends AbstractContract{
    private int maxSpeed;

    public InternetContract(Date startDate, Date stopDate, List listOfClient, int maxSpeed) {
        super(startDate, stopDate, listOfClient);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
