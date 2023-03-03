

public class Room {
    int roomId;
    String roomType;
    String airCondition;
    String balcony;
    float price;


    public Room(String roomType, boolean airCondition, boolean balcony, float price) {
        this.roomType = roomType;
        this.price = price;
        if(airCondition == true)
            this.airCondition = "aircondition";
         if(airCondition == false)
            this.airCondition = "no-aircondition";
         if(balcony == true)
             this.balcony = "balcony";
         else
             this.balcony = "no-balcony";

    }

}
