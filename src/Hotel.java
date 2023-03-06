import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Hotel {
    Room[] rooms = new Room[30]; // odaların tutulduğu Room classı türünde array
    Employee[] employees = new Employee[50]; // işçilerin tutulduğu employee türünde array
    Customer[] customers = new Customer[30]; // müşterilerin tutulduğu customer türünde array
    Reservation[] rezervations = new Reservation[30];

    // todo odaların boş olpu olammasını tutanb i array olaiblir mi??
    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;
    int rezervationIndex = 0;
    void init() {
        try {
            File commandFile = new File("commands.txt"); // dosyayı aç
            Scanner reader = new Scanner(commandFile);
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
                        addRoom(room); // otele bu odayı ekle
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
                    addEmployee(employee);
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
                    addCustomer(customer);

                } else if (commandList[0].equals("listRooms")) {
                    listRoom();
                } else if (commandList[0].equals(("listEmployees"))) {
                    listEmployee();
                } else if (commandList[0].equals(("listCustomers"))) {
                    listCustomer();
                } else if (commandList[0].equals("addReservation")) {
                    int customerid = Integer.parseInt(commandList[1]);
                    int roomid = Integer.parseInt(commandList[2]);

                    String startdate = commandList[3];
                    String enddate = commandList[4];

                    Reservation rezervation = new Reservation(customerid, roomid, startdate, enddate);
                    addRezervation(rezervation);
                } else if (commandList[0].equals("listReservations")) {
                    listRezervation();
                } else if (commandList[0].equals("searchCustomer")) {
                    String targetCustomer = commandList[1];
                    findCustomerbyName(targetCustomer);

                } else if (commandList[0].equals("searchRoom")) {
                    String startdate = commandList[1];
                    String enddate = commandList[2];
                    findRoombyDate(startdate, enddate);

                } else if (commandList[0].equals("deleteEmployee")) {
                    int employeeid = Integer.parseInt(commandList[1]);
                    deleteEmployee(employeeid);


                } else if (commandList[0].equals("statistics")) {

                }


            }
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("command.txt cannot be found");


        }


    }
    // rooms
    void addRoom(Room room) {
        if (roomIndex >= 30) return;

        room.roomId = roomIndex + 1;
        rooms[roomIndex] = room;
        roomIndex++;

    }
    void listRoom() {
        //System.out.println(rooms[0]);
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", rooms[i].roomId, rooms[i].roomType, airconditionText, balconyText, rooms[i].price));
            } else break;
        }

    }
    // employee
    void addEmployee(Employee employee) {
        if (employeeIndex < 50) {
            employee.employeeid = employeeIndex + 1;
            employees[employeeIndex] = employee;
            employeeIndex++;

        }
    }
    void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (id == employees[i].employeeid) {
                employees[i] = null;
                break;
            }
        }
    }
    void listEmployee() {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                System.out.println(String.format("Employee  #%d  %s  %s  %s  %s  %s", employees[i].employeeid, employees[i].employeeName,
                        employees[i].employeSurname, employees[i].employeeGender, employees[i].employeeBirthdate,
                        employees[i].job));
            }
        }
    }
    // customer
    void addCustomer(Customer customer) {
        customer.customerid = customerIndex + 1;
        customers[customerIndex] = customer;
        customerIndex++;
    }
    void listCustomer() {
        System.out.println("listCustomer");
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s",
                        customers[i].customerid, customers[i].customerName,
                        customers[i].customerSurname, customers[i].customerGender,
                        customers[i].customerBirthdate, customers[i].customerPhone));
            }


        }
    }
    // rezervation
    void addRezervation( Reservation reservation) {
        /*
        !! !! // burayı rezerve edilmiş oda kontrolü için yazmıştım ama çalıştıramadım.
        boolean hasRezerved = false;
        for (int i = 0; i < rezervations.length; i++) {
            if (room.roomId == rezervations[i].roomid) {
                hasRezerved = true;
            }
        }

        if (!hasRezerved) return;
        */

        rezervations[rezervationIndex] = reservation;
        rezervationIndex++;

    }
    void listRezervation() {
        for (int i = 0; i < rezervations.length; i++) {
            if (rezervations[i] != null) {
                int targetCustomerIndex = findCustomerIndexById(rezervations[i].customerid);
                System.out.println(String.format("Room  #%d  %s %s  %s   %s", rezervations[i].roomid, customers[targetCustomerIndex].customerName,
                        customers[targetCustomerIndex].customerSurname, rezervations[i].startDate, rezervations[i].endDate));
            }


        }


    }
    // search functions
    Room findRoomById(int id) {
        for (int i = 0; i < rooms.length; i++) {
            if (rezervations[i] != null) {
                if (rooms[i].roomId == id)
                    return rooms[i];
            }
        }
        return null;
    }
    int findCustomerIndexById(int id) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i].customerid == id)
                return i;
        }
        return 0;
    }
    void findCustomerbyName(String name) {
        for (int i = 0; i < customers.length; i++) {
            if (name.contains("*")) {  // yıldızlı işlemler
                int targetIndex = name.indexOf('*');
                if (customers[i] != null) {
                    if (customers[i].customerName.substring(0, targetIndex).equals(name.substring(0, targetIndex))) {
                        System.out.println(String.format("Customer  #%d  %s  %s  %s  %s  %s",
                                customers[i].customerid, customers[i].customerName, customers[i].customerSurname,
                                customers[i].customerGender, customers[i].customerBirthdate, customers[i].customerPhone));
                    }

                }
            } else if (name.contains("?")) {
                if (customers[i] != null) {
                    if (customers[i].customerName.length() == name.length()) {
                        int targetIndex = name.indexOf('?');
                        //
                        if (customers[i].customerName.substring(0, targetIndex).equals(name.substring(0, targetIndex))) {
                            System.out.println(String.format("Customer  #%d  %s  %s  %s  %s  %s",
                                    customers[i].customerid, customers[i].customerName, customers[i].customerSurname,
                                    customers[i].customerGender, customers[i].customerBirthdate, customers[i].customerPhone));
                        }
                    }

                }


            }

        }
        return;
    }
    void findRoombyDate(String startDate, String endDate) {
        for (int i = 0; i < rooms.length; i++) {
            if (startDate.equals(rooms[i])) {

            }
        }

    } // Todo YAPILACAK



}
