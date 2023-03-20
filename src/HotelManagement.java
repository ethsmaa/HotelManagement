import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class HotelManagement {

    Room[] rooms = new Room[30]; // array of type Room
    Employee[] employees = new Employee[50];
    Customer[] customers = new Customer[30];
    Reservation[] reservations = new Reservation[30];
    int[] howManyCustomers = new int[367]; // this array represents the days of the year (size of 367 because out of range error)
    int[][] reservationTable = new int[30][366];

    int housekeeperCount = 0;
    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;
    int rezervationIndex = 0;


    // read file
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
                        listReservation();
                        break;
                    case "searchCustomer":
                        String targetCustomer = commandList[1];
                        searchCustomer(targetCustomer);

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
                        allReservationInRange(startDate, endDate);

                        break;
                    }
                    case "deleteEmployee":
                        int employeeid = Integer.parseInt(commandList[1]);
                        deleteEmployee(employeeid);


                        break;
                    case "statistics":
                        mostReservedRoomandCustomer();
                        calculateProfit();
                        occupancyRate();
                        break;

                    case "simulation":
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

                        simulation(startDate, endDate);
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

        reservationTable[room.roomId][0] = room.roomId;
    }

    void listRoom() {
        System.out.println("listRooms");
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                System.out.println(String.format("  Room #%d   %s  %s  %s  %.0fTL", rooms[i].roomId,
                        rooms[i].roomType, airconditionText, balconyText, rooms[i].price));
            } else break;
        }
        System.out.println();
    }

    // employee
    void addEmployee(Employee employee) {
        if (employeeIndex < 50) {
            employee.employeeid = employeeIndex + 1;
            employees[employeeIndex] = employee;
            employeeIndex++;

            if (employee.job.equals("housekeeper")) {
                housekeeperCount++;
            }
        }
    }

    void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && id == employees[i].employeeid) {
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
        System.out.println();
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
        System.out.println();

    }

    // rezervation
    void addRezervation(Reservation reservation) {
        reservation.reservationTime = calculateDaysBetweenDates(reservation.startDate, reservation.endDate);
        reservations[rezervationIndex] = reservation;
        rezervationIndex++;

        findRoomById(reservation.roomid).reservationTime = reservation.reservationTime;

        findRoomById(reservation.roomid).hasReserved = true; // oda rezerve edildi. // kullanılmıyor su an

        int start = calculateDays(reservation.startDate);
        int end = calculateDays(reservation.endDate);

        for (int i = start; i < end; i++) {
            howManyCustomers[i]++;
        }
        for (int i = start; i < end; i++) {
            reservationTable[reservation.roomid][i] = reservation.customerid;
        }


    }

    void listReservation() {
        System.out.println("listReservations");

        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                int targetCustomerIndex = findCustomerIndexById(reservations[i].customerid);
                System.out.println(String.format("  Room  #%d  %s %s  %s   %s", reservations[i].roomid, customers[targetCustomerIndex].customerName,
                        customers[targetCustomerIndex].customerSurname, reservations[i].startDateString, reservations[i].endDateString));
            }


        }
        System.out.println();

    }

    // search functions
    Room findRoomById(int id) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].roomId == id) {
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

    int findReservationIndexByRoomId(int id) {
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                if (reservations[i].roomid == id)
                    return i;
            }
        }
        return 0;
    }

    void searchCustomer(String name) { //searchCustomer
        System.out.println("searchCustomer: " + name);
        if (name.contains("*") && !name.contains("?")) {
            int starCount = 0;
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == '*') {
                    starCount++;
                }
            }

            if (starCount == 1) {
                for (int i = 0; i < customers.length; i++) {
                    boolean flag = true;
                    for (int j = 0; j < name.length(); j++) {
                        if (customers[i] != null && name.charAt(j) != '*' && customers[i].customerName.charAt(j) != name.charAt(j)) {
                            flag = false;
                            break;
                        }
                    }

                    if (customers[i] != null && flag) {
                        System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                                customers[i].customerid, customers[i].customerName,
                                customers[i].customerSurname, customers[i].customerGender,
                                customers[i].customerBirthdate, customers[i].address.city, customers[i].customerPhone.countryCode,
                                customers[i].customerPhone.cityCode, customers[i].customerPhone.number));
                    }
                }
            } else {
                System.out.println("There can only be one '*' character used for search");
            }
        } else if (name.contains("?") && !name.contains("*")) {
            for (int i = 0; i < customers.length; i++) {
                boolean flag = true;

                if (customers[i] != null && name.length() == customers[i].customerName.length()) {
                    for (int j = 0; j < name.length(); j++) {
                        if (customers[i] != null && name.charAt(j) != '?' && customers[i].customerName.charAt(j) != name.charAt(j)) {
                            flag = false;
                            break;
                        }
                    }

                    if (customers[i] != null && flag) {
                        System.out.println(String.format("   Customer  #%d  %s  %s  %s  %s  %s  %s (%s) %s",
                                customers[i].customerid, customers[i].customerName,
                                customers[i].customerSurname, customers[i].customerGender,
                                customers[i].customerBirthdate, customers[i].address.city, customers[i].customerPhone.countryCode,
                                customers[i].customerPhone.cityCode, customers[i].customerPhone.number));
                    }
                }
            }
            System.out.println();
        } else {
            System.out.println("Your formatting was deemed invalid. Make sure to use either '*' or '?'.");
            System.out.println();
        }


    }

    void searchRoom(Date startDate, Date endDate) {
        System.out.println(String.format("searchRoom %d.%d.%d - %d.%d.%d", startDate.day, startDate.month, startDate.year, endDate.day,
                endDate.month, endDate.year));
        int start = calculateDays(startDate);
        int end = calculateDays(endDate);
        for (int i = 0; i < 30; i++) {
            boolean flag = true;
            for (int j = start; j <= end; j++) {
                if (reservationTable[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true && reservationTable[i][0] != 0) {
                Room room = findRoomById(i);
                String balconyText = room.balcony ? "balcony" : "non-balcony";
                String airconditionText = room.airCondition ? "aircondition" : "no-aircondition";
                System.out.println(String.format("  Room #%d   %s  %s  %s  %.0fTL", room.roomId,
                        room.roomType, airconditionText, balconyText, room.price));
            }
        }

        System.out.println();

    }

    void allReservationInRange(Date startDate, Date endDate) {
        System.out.println(String.format("All reservations between %d.%d.%d - %d.%d.%d", startDate.day, startDate.month, startDate.year, endDate.day,
                endDate.month, endDate.year));
        int start = calculateDays(startDate);
        int end = calculateDays(endDate);


        for (int i = 0; i < 30; i++) {
            boolean flag = false;
            for (int j = start; j <= end; j++) {
                if (reservationTable[i][j] != 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                if (reservations[i] != null) {
                    int index = findReservationIndexByRoomId(i);
                    int targetCustomerIndex = findCustomerIndexById(reservations[index].customerid);
                    System.out.println(String.format("  Room  #%d  %s %s  %s   %s", reservations[index].roomid, customers[targetCustomerIndex].customerName,
                            customers[targetCustomerIndex].customerSurname, reservations[index].startDateString, reservations[index].endDateString));
                }
            }

        }
        System.out.println();

    }


    // date functions / procedures
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
    } // Calculates which day of the year the entered date is

    int calculateDaysBetweenDates(Date firstDate, Date secondDate) {
        int dayBetween = calculateDays(secondDate) - calculateDays(firstDate);
        return dayBetween;
    }

    int endOfTheMonthDay(int month) {

        int day;
        int daycount = 0;
        for (int i = 1; i <= month; i++) {
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

    } // what day of the year is the end of that month?

    int daysInMonth(int i) { // how many days is this month

        if ((i != 2 && (i < 8 && i % 2 != 0) || i >= 8 && i % 2 == 0)) // 1, 3, 5, 7, 8, 10, 12
        {
            return 31;
        } else if (i == 2)
            return 29;
        else
            return 30; // 4, 6, 11
    } // how many days are there in that month

    // statistic functions / procedures
    void mostReservedRoomandCustomer() { // room and customer staying the most
        System.out.println("statistics");

        System.out.print("1. The most reserved room is = ");
        int max = reservations[0].reservationTime;
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                if (reservations[i].reservationTime > max)
                    max = reservations[i].reservationTime;
            }
        }

        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                if (reservations[i].reservationTime == max)
                    System.out.print("Room #" + reservations[i].roomid + "    "); // there can be more than one
            }
        }
        System.out.println();


        System.out.print("2. The best customer =");
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                if (reservations[i].reservationTime == max) {
                    int customerIndex = findCustomerIndexById(reservations[i].customerid);
                    System.out.print(String.format("  %s %s    ", customers[customerIndex].customerName,
                            customers[customerIndex].customerSurname));
                }
            }
        }
        System.out.println();
    }

    float roomIncome() {
        float incomeSum = 0;
        float incomeOfRoom;
        System.out.print("3. Income = ");
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                incomeOfRoom = rooms[i].reservationTime * rooms[i].price;
                incomeSum += incomeOfRoom;
                System.out.print(String.format("%.0fTL", incomeOfRoom));
                if (rooms[i + 1] == null)
                    System.out.print(" = ");
                else
                    System.out.print(" + ");
            }
        }
        System.out.println(String.format("%.0fTL", incomeSum));
        return incomeSum;
    }

    float salary() {
        int salarySum = 0;

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                int salary = employees[i].salary;
                salarySum += 12 * salary;
            }

        }
        System.out.println("   Salary = " + salarySum + "TL");
        return salarySum;

    }

    void calculateProfit() {
        float income = roomIncome();
        float salary = salary();
        System.out.println("   Constant Expenses = 120000TL");
        float constantExpenses = 12 * 10000;
        float profitResult = income - salary - constantExpenses;
        System.out.println(String.format("   Profit = %.0fTL - %.0fTL - %.0fTL = %.0fTL ",
                income, salary, constantExpenses, profitResult));

    }

    void occupancyRate() {
        System.out.println("4. Monhtly occupancy rate");
        for (int month = 1; month <= 12; month++) {
            System.out.print(String.format("%10s", month));
        }
        System.out.println();

        for (int month = 1; month <= 12; month++) {
            int sumOfDayCount = 0;
            for (int i = endOfTheMonthDay(month - 1); i <= endOfTheMonthDay(month); i++) {
                sumOfDayCount += howManyCustomers[i];
            }

            int denum = (roomIndex) * daysInMonth(month);
            double result = ((double) sumOfDayCount / (double) denum) * 100;
            System.out.printf("%9.1f%%", result);
        }
        System.out.println();
        System.out.println();
    }

    // simulation functions / procedures
    void simulation(Date startDate, Date endDate) {
        System.out.println("simulation");


        int firstMonth = startDate.month;
        int secondMonth = endDate.month;
        System.out.print("Day         :");
        if (firstMonth != secondMonth) {
            for (int i = startDate.day; i <= daysInMonth(firstMonth); i++) {
                //System.out.print(i + "        ");
                System.out.print(String.format("%10s", i));

            }
        } else if (firstMonth == secondMonth) {
            for (int i = startDate.day; i <= endDate.day; i++) {
                System.out.print(String.format("%10s", i));

            }
        }

        for (int i = firstMonth + 1; i < secondMonth; i++) {
            for (int j = 1; j <= daysInMonth(i); j++) {
                System.out.print(String.format("%10s", j));
            }
        }

        if (firstMonth != secondMonth) {
            for (int i = 1; i <= endDate.day; i++) {
                System.out.print(String.format("%10s", i));
            }
        }

        System.out.println();

        System.out.print("Customer    :");

        for (int i = calculateDays(startDate); i <= calculateDays(endDate); i++) {
            System.out.print(String.format("%10s", howManyCustomers[i]));
        }

        System.out.println();

        System.out.print("Satisfaction:");

        for (int i = calculateDays(startDate); i <= calculateDays(endDate); i++) {
            System.out.printf("%9d%%", satisfaction(howManyCustomers[i]));
        }
        System.out.println();

        System.out.print("Average Satisfaction = " + averageSatisfaction(startDate, endDate));
        System.out.println();
        System.out.println();

    }

    int satisfaction(int customerNumber) { // (300 / müşteri sayısı) * housekeeper sayısı = memnuniyet
        if (customerNumber == 0)
            return 100;

        float satisfaction = ((300 / customerNumber) * housekeeperCount);
        if (satisfaction > 100)
            satisfaction = 100;
        return (int) satisfaction;
    }

    float averageSatisfaction(Date startDate, Date endDate) {
        int sumSatisfaction = 0;
        int howManyDays = calculateDaysBetweenDates(startDate, endDate) + 1;
        for (int i = calculateDays(startDate); i <= calculateDays(endDate); i++) {
            sumSatisfaction += satisfaction(howManyCustomers[i]);
        }
        return sumSatisfaction / (howManyDays);
    }


}



