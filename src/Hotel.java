public class Hotel {
    Room[] rooms = new Room[30]; // odaların tutulduğu Room classı türünde array
    Employee[] employees = new Employee[50]; // işçilerin tutulduğu employee türünde array
    Customer[] customers = new Customer[30]; // müşterilerin tutulduğu customer türünde array
    Reservation[] rezervations = new Reservation[30];

    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;
    int rezervationIndex = 0;


    // rooms
    void addRoom(Room room) {
        if (roomIndex >= 30) return;

        room.roomId = roomIndex + 1;
        rooms[roomIndex] = room;
        roomIndex++;

    }

    void listRoom() {
        System.out.println(rooms[0]);
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
                System.out.println(String.format("Employee  #%d  %s  %s  %s  %s  %s", employees[i].employeeid, employees[i].employeeName, employees[i].employeSurname, employees[i].employeeGender, employees[i].employeeBirthdate, employees[i].job));
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
    void addRezervation(Room room, Reservation reservation) {
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
        if (room != null)
            room.rezervationCount++;
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

    void findMostRezervedRoom() {
        int maxReserve = rooms[0].rezervationCount;

        for (int i = 0; i < rooms.length; i++) { // rezerveler içinde en çok rezervasyon yapilmis odayı bul
            if (rooms[i] != null) {
                if (rooms[i].rezervationCount > maxReserve)
                    maxReserve = rooms[i].rezervationCount;
            }
        }
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                if (maxReserve == rooms[i].rezervationCount) {
                    System.out.println(String.format("The most reserved room = Room #%d", rooms[i].roomId));
                }

            }
        }
    }


}
