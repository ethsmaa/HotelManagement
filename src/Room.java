

public class Room {
    int roomId;
    String roomType;
    boolean airCondition;
    boolean balcony;
    float price;
    int rezervationCount = 1; // todo : istatiste işime yarayacak


    public Room(String roomType, boolean airCondition, boolean balcony, float price) {
        this.roomType = roomType;
        this.price = price;
        this.airCondition = airCondition;
        this.balcony = balcony;
    }


}
