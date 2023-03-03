
public class Hotel {
    Room[] rooms = new Room[30]; // odaların tutulduğu Room classı türünde array
    Employee[] employees = new Employee[50]; // işçilerin tutulduğu employee türünde array
    Customer[] customers = new Customer[30]; // müşterilerin tutulduğu customer türünde array
    int roomIndex = 0;
    int employeeIndex = 0;
    int customerIndex = 0;

    // rooms
    void addRoom(Room room) {
        if (roomIndex < 30) { // 30(kapasite) olana kadar oda ekle
            room.roomId = this.roomIndex + 1;
            rooms[roomIndex] = room;
            this.roomIndex++;
        }

    }

    void listRoom() {
        System.out.println(rooms[0]);
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null) {
                String balconyText = rooms[i].balcony ? "balcony" : "non-balcony";
                String airconditionText = rooms[i].airCondition ? "aircondition" : "no-aircondition";
                System.out.println(String.format("Room #%d %s  %s  %s  %.0fTL", rooms[i].roomId, rooms[i].roomType, airconditionText, balconyText, rooms[i].price));
            } else
                break;
        }

    }

    // employee
    void addEmployee(Employee employee) {
        if (employeeIndex < 50) {
            employee.employeeid = this.employeeIndex + 1;
            employees[employeeIndex] = employee;
            this.employeeIndex++;

        }
    }

    void listEmployee() {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                System.out.println(String.format("Employee  #%d  %s  %s  %s  %s  %s", employees[i].employeeid, employees[i].employeeName,
                        employees[i].employeSurname, employees[i].employeeGender, employees[i].employeeBirthdate, employees[i].job));
            }


        }
    }

    // customer

    void addCustomer( Customer customer) {
        customer.customerid = this.customerIndex+1;
        customers[customerIndex] = customer;
        this.customerIndex++;
    }

    void listCustomer() {

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                System.out.println(String.format("Customer  #%d  %s  %s  %s  %s  %s", customers[i].customerid, customers[i].customerName,
                        customers[i].customerSurname, customers[i].customerGender, customers[i].customerBirthdate,customers[i].customerPhone));
            }


        }
    }

    void AddRezervation() {

    }



}
