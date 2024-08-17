package railway_reservation_system;

public class Passenger1 {
    static int id = 1;
    String name, gender, cname, berth_preference, allotted;
    int age, cage, number;
    int passengerId = id++;

    public Passenger1(String name, int age, String gender, String cname, int cage, String berth_preference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.cname = cname;
        this.cage = cage;
        this.berth_preference = berth_preference;
        allotted = " ";
        number = -1;
    }
}
