import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// todo
// address her birey için güncellenecek
// telefon her birey için güncellenecek
// date bulunduğu her yer için güncellenecek
// omer canın attığı kod eklenecek

public class HotelManagement {

    Room[] rooms = new Room[30]; // array of type Room
    Employee[] employees = new Employee[50];
    Customer[] customers = new Customer[30];
    Reservation[] reservations = new Reservation[30];
    int[] reservedRoomIds = new int[30];

    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;
    int rezervationIndex = 0;
    int reservedRoomIndex = 0;


    void init() {
        try {
            File commandFile = new File("commands.txt"); // open file
            Scanner reader = new Scanner(commandFile);
            while (reader.hasNextLine()) { // until you reach the end of the file
                String line = reader.nextLine(); // read lines
                String[] commandList = line.split(";"); // separate each line with ","
                String command = commandList[0];
                switch (command) {
                    case "addRoom":
                        int numberOfRoom = Integer.parseInt(commandList[1]); // Convert 2nd element to integer

                        for (int i = 0; i < numberOfRoom; i++) { //keep as variable as the number of rooms
                            String type = commandList[2];
                            Boolean airCondition = Boolean.parseBoolean(commandList[3]);
                            Boolean balcony = Boolean.parseBoolean(commandList[4]);
                            float price = Float.parseFloat(commandList[5]);
                            Room room = new Room(type, airCondition, balcony, price); //new element in the room class with these attributes
                            addRoom(room); // add this room to the hotel
                        }
                        break;

                    case ("addEmployee"): {
                        String name = commandList[1];
                        String surname = commandList[2];
                        String gender = commandList[3];
                        String birthdate = commandList[4];
                        String addresstext = commandList[5];
                        String district = commandList[6];
                        String city = commandList[7];
                        String countryCode = commandList[8].substring(0, 3);
                        String cityCode = commandList[8].substring(3, 6);
                        String number = commandList[8].substring(6);
                        String job = commandList[9];
                        int salary = Integer.parseInt(commandList[10]);
                        String birthdates[] = commandList[4].split("\\.");

                        int day = Integer.parseInt(birthdates[0]);
                        int month = Integer.parseInt(birthdates[1]);
                        int year = Integer.parseInt(birthdates[2]);
                        Date date = new Date(day, month, year);


                        Phone phone = new Phone(countryCode, cityCode, number);
                        Address address = new Address(addresstext, district, city);
                        Employee employee = new Employee(name, surname, gender, birthdate, phone, job, address, salary, date);
                        addEmployee(employee);
                        break;
                    }
                    case ("addCustomer"): {
                        String name = commandList[1];
                        String surname = commandList[2];
                        String gender = commandList[3];
                        String birthdate = commandList[4];
                        String addresstext = commandList[5];
                        String district = commandList[6];
                        String city = commandList[7];
                        String countryCode = commandList[8].substring(0, 3);
                        String cityCode = commandList[8].substring(3, 6);
                        String number = commandList[8].substring(6);
                        String birthdates[] = commandList[4].split("\\.");

                        int day = Integer.parseInt(birthdates[0]);
                        int month = Integer.parseInt(birthdates[1]);
                        int year = Integer.parseInt(birthdates[2]);

                        Address address = new Address(addresstext, district, city);
                        Date date = new Date(day, month, year);
                        Phone phone = new Phone(countryCode, cityCode, number);
                        Customer customer = new Customer(name, surname, gender, birthdate, phone, date, address);

                        addCustomer(customer);

                        break;
                    }
                    case "listRooms":
                        listRoom();
                        break;
                    case ("listEmployees"):
                        listEmployee();
                        break;
                    case ("listCustomers"):
                        listCustomer();
                        break;
                    case "addReservation": {
                        int customerid = Integer.parseInt(commandList[1]);
                        int roomid = Integer.parseInt(commandList[2]);
                        String startDates[] = commandList[3].split("\\.");
                        int dayStart = Integer.parseInt(startDates[0]);
                        int monthStart = Integer.parseInt(startDates[1]);
                        int yearStart = Integer.parseInt(startDates[2]);
                        Date startDate = new Date(dayStart, monthStart, yearStart);

                        String endDates[] = commandList[4].split("\\.");
                        int dayEnd = Integer.parseInt(endDates[0]);
                        int monthEnd = Integer.parseInt(endDates[1]);
                        int yearEnd = Integer.parseInt(endDates[2]);
                        Date endDate = new Date(dayEnd, monthEnd, yearEnd);


                        String startdateString = commandList[3];
                        String enddateString = commandList[4];

                        Reservation rezervation = new Reservation(customerid, roomid, startdateString, enddateString, startDate, endDate);
                        addRezervation(rezervation);
                        break;
                    }
                    case "listReservations":
                        listRezervation();
                        break;
                    case "searchCustomer":
                        String targetCustomer = commandList[1];
                        findCustomerbyName(targetCustomer);

                        break;
                    case "searchRoom": {
                        String startDates[] = commandList[1].split("\\.");
                        int dayStart = Integer.parseInt(startDates[0]);
                        int monthStart = Integer.parseInt(startDates[1]);
                        int yearStart = Integer.parseInt(startDates[2]);
                        Date startDate = new Date(dayStart, monthStart, yearStart);

                        String endDates[] = commandList[2].split("\\.");
                        int dayEnd = Integer.parseInt(endDates[0]);
                        int monthEnd = Integer.parseInt(endDates[1]);
                        int yearEnd = Integer.parseInt(endDates[2]);
                        Date endDate = new Date(dayEnd, monthEnd, yearEnd);

                        searchRoom(startDate, endDate);

                        break;
                    }
                    case "deleteEmployee":
                        int employeeid = Integer.parseInt(commandList[1]);
                        deleteEmployee(employeeid);


                        break;
                    case "statistics":
                        break;
                }


            }
            reader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("commands.txt cannot be found");


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
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", rooms[i].roomId,
                        rooms[i].roomType, airconditionText, balconyText, rooms[i].price));
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
        System.out.println("listEmployees");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                System.out.println(String.format("  Employee  #%d  %s  %s  %s  %s  %s", employees[i].employeeid, employees[i].name,
                        employees[i].surname, employees[i].gender, employees[i].birthdayString,
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
                System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                        customers[i].customerid, customers[i].customerName,
                        customers[i].customerSurname, customers[i].customerGender,
                        customers[i].customerBirthdate, customers[i].address.city, customers[i].customerPhone.countryCode,
                        customers[i].customerPhone.cityCode, customers[i].customerPhone.number));
            }


        }
    }

    // rezervation
    void addRezervation(Reservation reservation) {

        reservations[rezervationIndex] = reservation;
        rezervationIndex++;

        reservedRoomIds[reservedRoomIndex] = reservation.roomid; // rezervasyon yapılan odayı ekle
        reservedRoomIndex++;


    }

    void listRezervation() {
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                int targetCustomerIndex = findCustomerIndexById(reservations[i].customerid);
                System.out.println(String.format("Room  #%d  %s %s  %s   %s", reservations[i].roomid, customers[targetCustomerIndex].customerName,
                        customers[targetCustomerIndex].customerSurname, reservations[i].startDateString, reservations[i].endDateString));
            }


        }


    }

    // search functions
    Room findRoomById(int id) {
        for (int i = 0; i < rooms.length; i++) {
            if (reservations[i] != null) {
                if (rooms[i].roomId == id)
                    return rooms[i];
            }
        }
        return null;
    }

    int findCustomerIndexById(int id) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                if (customers[i].customerid == id)
                    return i;
            }
        }
        return 0;
    }

    void findCustomerbyName(String name) {
        for (int i = 0; i < customers.length; i++) {
            if (name.contains("*")) {  // yıldızlı işlemler
                int targetIndex = name.indexOf('*');
                if (customers[i] != null && customers[i].customerName.substring(0, targetIndex).equals(name.substring(0, targetIndex))) {
                    System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                            customers[i].customerid, customers[i].customerName,
                            customers[i].customerSurname, customers[i].customerGender,
                            customers[i].customerBirthdate, customers[i].address.city, customers[i].customerPhone.countryCode,
                            customers[i].customerPhone.cityCode, customers[i].customerPhone.number));
                }
            } else if (name.contains("?")) {
                if (customers[i] != null) {
                    if (customers[i].customerName.length() == name.length()) {
                        int targetIndex = name.indexOf('?');
                        //
                        if (customers[i].customerName.substring(0, targetIndex).equals(name.substring(0, targetIndex))) {
                            System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                                    customers[i].customerid, customers[i].customerName,
                                    customers[i].customerSurname, customers[i].customerGender,
                                    customers[i].customerBirthdate, customers[i].address.city, customers[i].customerPhone.countryCode,
                                    customers[i].customerPhone.cityCode, customers[i].customerPhone.number));
                        }
                    }

                }


            }

        }
    } // SearchCustomer

    int calculateDays(Date date) {
        int day;
        int month = date.month;
        int daycount = date.day;


        for (int i = 1; i < month; i++) {
            if ((i < 8 && i % 2 != 0) || i >= 8 && i % 2 == 0) // 1, 3, 5, 7, 8, 10, 12
            {
                day = 31;
            } else if (i == 2)
                day = 29;
            else
                day = 30; // 4, 6, 11
            daycount += day;
        }
        return daycount;
    } // girilen tarihin yılın kaçıncı günü olduğunu hesaplar

    void calculateDaysBetweenDates(Date firstDate, Date secondDate) {
        int dayBetween = calculateDays(secondDate) - calculateDays(firstDate);
        System.out.println(dayBetween);
    } //!! Bunu istatistik için kullanacağız

    boolean isBetweenDates(Reservation reservation, Date startDate, Date endDate) {

        // bu fonksiyon eğer ki girilen tarihler arasında oda rezerve edilmediyse true döndürür.
        boolean dayCheck = false;
        int targetStartDate = calculateDays(startDate);
        int targetEndDate = calculateDays(endDate);

        int reservationStart = calculateDays(reservation.startDate);
        int reservationEnd = calculateDays(reservation.endDate);

        if (reservationEnd < targetStartDate || reservationStart > targetEndDate) {
            dayCheck = true; // boştur
            return dayCheck;
        }

        return dayCheck; // oda doludur


    }

    void searchRoom(Date startDate, Date endDate) {

        for (int i = 0; i < reservations.length; i++) {

            if (reservations[i] != null) { //ortada bir rezervasyon var ama tarih kontrolü için üstteki fonksiyona yollamalıyız
                if (isBetweenDates(reservations[i], startDate, endDate) == true) { // tarih kontrolünden geçtiyse
                    Room room = findRoomById(reservations[i].roomid); // bu odayı bul

                    String balconyText = room.balcony ? "balcony" : "non-balcony";
                    String airconditionText = room.airCondition ? "aircondition" : "no-aircondition";
                    System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", room.roomId,
                            room.roomType, airconditionText, balconyText, room.price));

                }

            } else
                break;
        }

        // eğer rezervasyon yoksa dolu oda id'lerinin arasında odayı ara. yoksa bastır.
        int j = 0;
        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i] != null) {
                if (rooms[i].roomId != reservedRoomIds[j]) {
                    String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                    String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                    System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", rooms[i].roomId,
                            rooms[i].roomType, airconditionText, balconyText, rooms[i].price));
                }
                j++;
            }
        }
    }
//most reserved room
void mostReservedRoom(){ // en fazla süre kalınan oda


}













}



