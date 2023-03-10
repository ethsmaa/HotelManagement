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
    Reservation[] rezervations = new Reservation[30];

    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;
    int rezervationIndex = 0;


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
                        String countryCode = commandList[8].substring(0,3);
                        String cityCode = commandList[8].substring(3,6);
                        String number = commandList[8].substring(6);
                        String job = commandList[9];
                        int salary = Integer.parseInt(commandList[10]);
                        String birthdates[] = commandList[4].split("\\.");

                        int day = Integer.parseInt(birthdates[0]);
                        int month = Integer.parseInt(birthdates[1]);
                        int year = Integer.parseInt(birthdates[2]);
                        Date date = new Date(day,month,year);



                        Phone phone = new Phone(countryCode,cityCode,number);
                        Address address = new Address(addresstext,district,city);
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
                        String countryCode = commandList[8].substring(0,3);
                        String cityCode = commandList[8].substring(3,6);
                        String number = commandList[8].substring(6);
                        String birthdates[] = commandList[4].split("\\.");

                        int day = Integer.parseInt(birthdates[0]);
                        int month = Integer.parseInt(birthdates[1]);
                        int year = Integer.parseInt(birthdates[2]);

                        Address address = new Address(addresstext,district,city);
                        Date date = new Date(day,month,year);
                        Phone phone = new Phone(countryCode,cityCode,number);
                        Customer customer = new Customer(name, surname, gender, birthdate,phone,date,address);

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
                        Date startDate = new Date(dayStart,monthStart,yearStart);

                        String endDates[] = commandList[4].split("\\.");
                        int dayEnd = Integer.parseInt(endDates[0]);
                        int monthEnd = Integer.parseInt(endDates[1]);
                        int yearEnd = Integer.parseInt(endDates[2]);
                        Date endDate = new Date(dayEnd,monthEnd,yearEnd);


                        String startdateString = commandList[3];
                        String enddateString = commandList[4];

                        Reservation rezervation = new Reservation(customerid, roomid, startdateString, enddateString,startDate,endDate);
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
                        String startdate = commandList[1];
                        String enddate = commandList[2];
                        findRoombyDate(startdate, enddate);

                        break;
                    }
                    case "deleteEmployee":
                        int employeeid = Integer.parseInt(commandList[1]);
                        deleteEmployee(employeeid);


                        break;
                    case "statistics":
                        break;
                    case "hesapla":
                        String startDates[] = commandList[1].split("\\.");
                        int dayStart = Integer.parseInt(startDates[0]);
                        int monthStart = Integer.parseInt(startDates[1]);
                        int yearStart = Integer.parseInt(startDates[2]);
                        Date startDate = new Date(dayStart,monthStart,yearStart);

                        String endDates[] = commandList[2].split("\\.");
                        int dayEnd = Integer.parseInt(endDates[0]);
                        int monthEnd = Integer.parseInt(endDates[1]);
                        int yearEnd = Integer.parseInt(endDates[2]);
                        Date endDate = new Date(dayEnd,monthEnd,yearEnd);

                        calculateDaysBetweenDates(startDate,endDate);

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
                        customers[i].customerBirthdate,customers[i].address.city , customers[i].customerPhone.countryCode,
                        customers[i].customerPhone.cityCode,customers[i].customerPhone.number ));
            }


        }
    }

    // rezervation
    void addRezervation(Reservation reservation) {
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
                        customers[targetCustomerIndex].customerSurname, rezervations[i].startDateString, rezervations[i].endDateString));
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
                if (customers[i] != null && customers[i].customerName.substring(0, targetIndex).equals(name.substring(0, targetIndex))) {
                    System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                            customers[i].customerid, customers[i].customerName,
                            customers[i].customerSurname, customers[i].customerGender,
                            customers[i].customerBirthdate,customers[i].address.city , customers[i].customerPhone.countryCode,
                            customers[i].customerPhone.cityCode,customers[i].customerPhone.number ));
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
                                    customers[i].customerBirthdate,customers[i].address.city , customers[i].customerPhone.countryCode,
                                    customers[i].customerPhone.cityCode,customers[i].customerPhone.number ));
                        }
                    }

                }


            }

        }
    }

    void findRoombyDate(String startDate, String endDate)
    {
        int[] doluroom= new int[30];
        int doluroomindex=0;

        String[] startdatear= startDate.split("\\.");
        int startday=Integer.parseInt(startdatear[0]);
        int startmonth=Integer.parseInt(startdatear[1]);

        String[] enddatear= endDate.split("\\.");
        int endday=Integer.parseInt(enddatear[0]);
        int endmonth=Integer.parseInt(enddatear[1]);
        int searchday=0;
        int searchlastday=0;
        if(startmonth==1)
        {
            searchday=0+startday;

        }
        else if(startmonth==2)
        {
            searchday=31+startday;

        }
        else if(startmonth==3)
        {
            searchday=60+startday;

        }
        else if(startmonth==4)
        {
            searchday=91+startday;

        }
        else if(startmonth==5)
        {
            searchday=121+startday;

        }
        else if(startmonth==6)
        {
            searchday=152+startday;

        }
        else if(startmonth==7)
        {
            searchday=182+startday;

        }
        else if(startmonth==8)
        {
            searchday=213+startday;

        }
        else if(startmonth==9)
        {
            searchday=244+startday;


        }
        else if(startmonth==10)
        {
            searchday=274+startday;

        }
        else if(startmonth==11)
        {
            searchday=305+startday;

        }
        else if(startmonth==12)
        {
            searchday=335+startday;

        }

        if(endmonth==1)
        {
            searchlastday=0+endday;

        }
        else if(endmonth==2)
        {
            searchlastday=31+endday;

        }
        else if(endmonth==3)
        {
            searchlastday=60+endday;

        }
        else if(endmonth==4)
        {
            searchlastday=91+endday;

        }
        else if(endmonth==5)
        {
            searchlastday=121+endday;

        }
        else if(endmonth==6)
        {
            searchlastday=152+endday;

        }
        else if(endmonth==7)
        {
            searchlastday=182+endday;

        }
        else if(endmonth==8)
        {
            searchlastday=213+endday;

        }
        else if(endmonth==9)
        {
            searchlastday=244+endday;


        }
        else if(endmonth==10)
        {
            searchlastday=274+endday;

        }
        else if(endmonth==11)
        {
            searchlastday=305+endday;

        }
        else if(endmonth==12)
        {
            searchlastday=335+endday;

        }



        for (int i=0;i<rezervations.length;i++)
        {
            boolean flag=true;
            if (rezervations[i]!=null) {
                String[] rezstart = rezervations[i].startDateString.split("\\.");
                int rezstartday=Integer.parseInt(rezstart[0]);
                int rezstartmonth=Integer.parseInt(rezstart[1]);


                String[] rezend = rezervations[i].endDateString.split("\\.");
                int rezendday=Integer.parseInt(rezend[0]);
                int rezendmonth=Integer.parseInt(rezend[1]);
                int rezdaysayac=0;
                int lastdaysayac=0;


                if(rezstartmonth==1)
                {
                    rezdaysayac=0+rezstartday;

                }
                else if(rezstartmonth==2)
                {
                    rezdaysayac=31+rezstartday;

                }
                else if(rezstartmonth==3)
                {
                    rezdaysayac=60+rezstartday;

                }
                else if(rezstartmonth==4)
                {
                    rezdaysayac=91+rezstartday;

                }
                else if(rezstartmonth==5)
                {
                    rezdaysayac=121+rezstartday;

                }
                else if(rezstartmonth==6)
                {
                    rezdaysayac=152+rezstartday;

                }
                else if(rezstartmonth==7)
                {
                    rezdaysayac=182+rezstartday;

                }
                else if(rezstartmonth==8)
                {
                    rezdaysayac=213+rezstartday;

                }
                else if(rezstartmonth==9)
                {
                    rezdaysayac=244+rezstartday;


                }
                else if(rezstartmonth==10)
                {
                    rezdaysayac=274+rezstartday;

                }
                else if(rezstartmonth==11)
                {
                    rezdaysayac=305+rezstartday;

                }
                else if(rezstartmonth==12)
                {
                    rezdaysayac=335+rezstartday;

                }

                if(rezendmonth==1)
                {
                    lastdaysayac=0+rezendday;

                }else if(rezendmonth==2)
                {
                    lastdaysayac=31+rezendday;

                }
                else if(rezendmonth==3)
                {
                    lastdaysayac=60+rezendday;

                }
                else if(rezendmonth==4)
                {
                    lastdaysayac=91+rezendday;

                }
                else if(rezendmonth==5)
                {
                    lastdaysayac=121+rezendday;

                }
                else if(rezendmonth==6)
                {
                    lastdaysayac=152+rezendday;

                }
                else if(rezendmonth==7)
                {
                    lastdaysayac=182+rezendday;

                }
                else if(rezendmonth==8)
                {
                    lastdaysayac=213+rezendday;

                }
                else if(rezendmonth==9)
                {
                    lastdaysayac=244+rezendday;


                }
                else if(rezendmonth==10)
                {
                    lastdaysayac=274+rezendday;

                }
                else if(rezendmonth==11)
                {
                    lastdaysayac=305+rezendday;

                }
                else if(rezendmonth==12)
                {
                    lastdaysayac=335+rezendday;

                }
                if((searchday<=rezdaysayac&&(searchlastday<=lastdaysayac&&searchlastday>=rezdaysayac))||(searchday>=rezdaysayac&&searchlastday<=lastdaysayac)||(searchday>=rezdaysayac&&searchday<=lastdaysayac)&&(searchlastday>=lastdaysayac)||(searchday<=rezdaysayac)&&(searchlastday>=lastdaysayac))
                {
                    doluroom[doluroomindex]=rezervations[i].roomid;
                    doluroomindex++;
                }

            }

        }

        for(int v=0;v<doluroom.length;v++)
        {/*
            if(doluroom[v]!=0)
            {
                System.out.println(doluroom[v]);
            }*/
        }

        for(int i=0;i<rooms.length;i++)
        {
            if (rooms[i]!=null) {

                boolean roomflag = true;
                for (int j = 0; j < doluroom.length; j++)
                {
                    if (rooms[i].roomId == doluroom[j]) {
                        roomflag = false;
                    }

                }
                String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                if (roomflag == true) {
                    System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", rooms[i].roomId, rooms[i].roomType, airconditionText, balconyText, rooms[i].price));

                }
            }


        }



    }

     int calculateDays(Date date)
    {
        int day;
        int month = date.month;
        int daycount = date.day;


        for(int i = 1 ; i<month; i++) {
            if((i<8 && i%2!=0) || i>=8 && i%2 ==0) // 1, 3, 5, 7, 8, 10, 12
            {
                day = 31;
            }
            else if(i == 2)
                day = 29;
            else
                day = 30; // 4, 6, 11
            daycount+=day;
        }
        return daycount;
    }
    void  calculateDaysBetweenDates(Date firstDate, Date secondDate) {
        int dayBetween = calculateDays(secondDate) - calculateDays(firstDate);
        System.out.println(dayBetween);
    }

}
