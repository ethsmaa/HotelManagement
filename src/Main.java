import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try { // error olmadığı sürece
            File commandFile = new File("commands.txt"); // dosyayı aç
            Scanner reader = new Scanner(commandFile);

            Hotel DEUCengHotel = new Hotel(); // otel class'ı içinde obje oluştur


            while (reader.hasNextLine()) { // dosyanın sonuna gelene kadar
                String line = reader.nextLine(); // satırları oku
                String[] commandList = line.split(";"); // her bir satırı ; ile ayır
                if (commandList[0].equals("addRoom")) {
                    int numberOfRoom = Integer.parseInt(commandList[1]); // 2. elemanı integere çevir
                    for (int i = 0; i < numberOfRoom; i++) { //oda sayısı kadar değişken tut
                        String type = commandList[2];
                        Boolean airCondition = Boolean.parseBoolean(commandList[3]);
                        Boolean balcony = Boolean.parseBoolean(commandList[4]);
                        float price = Float.parseFloat(commandList[5]);

                        Room room = new Room(type, airCondition, balcony, price); // bu attribute'lara sahip room classında yeni bir eleman
                        DEUCengHotel.addRoom(room); // otele bu odayı ekle
                        // komutu öğrenmek için listenin ilk elemanını kontrol et
                    }
                } else if (commandList[0].equals(("addEmployee"))) {
                    String name = commandList[1];
                    String surname = commandList[2];
                    String gender = commandList[3];
                    String birthdate = commandList[4];
                    String addresstext = commandList[5];
                    String district = commandList[6];
                    String city = commandList[7];
                    String phone = commandList[8];
                    String job = commandList[9];
                    int salary = Integer.parseInt(commandList[10]);

                    Employee employee = new Employee(name, surname, gender, birthdate, addresstext, district, city, phone, job
                            , salary);
                    DEUCengHotel.addEmployee(employee);
                } else if (commandList[0].equals(("addCustomer"))) {
                    String name = commandList[1];
                    String surname = commandList[2];
                    String gender = commandList[3];
                    String birthdate = commandList[4];
                    String addresstext = commandList[5];
                    String district = commandList[6];
                    String city = commandList[7];
                    String phone = commandList[8];

                    Customer customer = new Customer(name, surname, gender, birthdate, addresstext, district, city, phone);
                    DEUCengHotel.addCustomer(customer);

                } else if (commandList[0].equals("listRooms")) {
                    DEUCengHotel.listRoom();
                } else if (commandList[0].equals(("listEmployees"))) {
                    DEUCengHotel.listEmployee();
                } else if (commandList[0].equals(("listCustomers"))) {
                    DEUCengHotel.listCustomer();
                } else if (commandList[0].equals("addReservation")) {
                    int customerid = Integer.parseInt(commandList[1]);
                    int roomid = Integer.parseInt(commandList[2]);
                    Room targetRoom = DEUCengHotel.findRoomById(roomid);

                    String startdate = commandList[3];
                    String enddate = commandList[4];

                    Reservation rezervation = new Reservation(customerid, roomid, startdate, enddate);
                    DEUCengHotel.addRezervation(targetRoom, rezervation);
                }
                else if (commandList[0].equals("listReservations")) {
                    DEUCengHotel.listRezervation();


                }

            }
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("command txt cannot be found");


        }
    }
}

