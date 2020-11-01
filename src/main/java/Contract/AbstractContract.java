package Contract;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AbstractContract {
    private UUID id;
    private Date startDate;
    private Date stopDate;
    private List listOfClient;


    public AbstractContract(Date startDate, Date stopDate, List listOfClient) {
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.listOfClient = listOfClient;
        this.id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractContract)) return false;
        AbstractContract that = (AbstractContract) o;
        return id.equals(that.id) &&
                startDate.equals(that.startDate) &&
                stopDate.equals(that.stopDate) &&
                listOfClient.equals(that.listOfClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, stopDate, listOfClient);
    }

    public UUID getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public List getListOfClient() {
        return listOfClient;
    }

    public void setListOfClient(List listOfClient) {
        this.listOfClient = listOfClient;
    }
}
