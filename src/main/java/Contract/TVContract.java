package Contract;

import java.util.Date;
import java.util.List;

public class TVContract extends AbstractContract {
    private int packageNumber;
    public TVContract(Date startDate, Date stopDate, List listOfClient, int packageNumber) {
        super(startDate, stopDate, listOfClient);
        this.packageNumber = packageNumber;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }
}
