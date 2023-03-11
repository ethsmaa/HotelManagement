
public class Room {
    int roomId;
    String roomType;
    boolean airCondition;
    boolean balcony;
    float price;
    int reservationTime;
    boolean hasReserved;
    public Room(String roomType, boolean airCondition, boolean balcony, float price) {
        this.roomType = roomType;
        this.price = price;
        this.airCondition = airCondition;
        this.balcony = balcony;
    }

}
