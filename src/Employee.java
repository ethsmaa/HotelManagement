public class Employee {
    int employeeid;
    String name;
    String surname;
    String gender;
    String birthdayString;
    Phone phone;
    String job;
    Address address;
    int salary;
    Date birthdate;


    public Employee(String name, String surname,
                    String gender, String birthdayString, Phone phone,
                    String job, Address address, int salary, Date birthdate) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdayString = birthdayString;
        this.phone = phone;
        this.job = job;
        this.address = address;
        this.salary = salary;
        this.birthdate = birthdate;
    }
}
