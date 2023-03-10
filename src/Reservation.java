public class Reservation {
    int customerid;
    int roomid;
    String startDateString;
    String endDateString;
    Date startDate;
    Date endDate;

    public Reservation(int customerid, int roomid , String startDateString, String endDateString,Date startDate, Date endDate){
        this.customerid = customerid;
        this.roomid = roomid;
        this.startDateString = startDateString;
        this.endDateString = endDateString;
        this.startDate = startDate;
        this.endDate = endDate;
    }



}
