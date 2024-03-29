import java.io.Serializable;

public class SubscriptionCycle implements Serializable {

    String startDate;
    String endDate;

    public SubscriptionCycle(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate(){
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

