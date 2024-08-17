package railway_reservation_system;
import java.util.*;

class Passenger {
    static int id = 1;
    String name, gender, cname, berth_preference, allotted;
    int age, cage, number;
    int passengerId = id++;

    public Passenger(String name, int age, String gender, String cname, int cage, String berth_preference) {
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

class TicketBook {
    static int available_lower_berth = 4;
    static int available_middle_berth = 3;
    static int available_upper_berth = 4;


    // ArrayList is a class that inherits List interface and list interface inherits collection interface
    // resizable array as array ka size fixed rhta hai, arraylist is resizable

// creating a list of type integer using ArrayList
    static List<Integer> lower_berth_position = new ArrayList<Integer>(Arrays.asList(1, 2,3,4));  // creating a list out of an array,
    // modifying the new list out of an array
    static List<Integer> middle_berth_position = new ArrayList<Integer>(Arrays.asList(1,2,3));
    static List<Integer> upper_berth_position = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
    static List<Integer> bookedTicketList = new ArrayList<Integer>();

//    It stores the data in (Key, Value) pairs
    static Map<Integer, Passenger> passenger_stored_data = new HashMap<>();

    public void bookTicket(Passenger p, int snumber, String allottedBerth) {
        p.number = snumber;
        p.allotted = allottedBerth;
        passenger_stored_data.put(p.passengerId, p);
        bookedTicketList.add(p.passengerId);
        System.out.println("Passenger ID: " + p.passengerId);
        System.out.println("Passenger Name: " + p.name);
        System.out.println("Passenger Age: " + p.age);
        System.out.println("Passenger Gender: " + p.gender);
        System.out.println("Allotted Berth: " + snumber + allottedBerth);
        System.out.println("--------------------------BOOKED SUCCESSFULLY--------------------------------------");
    }


    public void cancelTicket(int passengerId) {
        Passenger p = passenger_stored_data.get(passengerId);
        passenger_stored_data.remove(passengerId);
        bookedTicketList.remove(passengerId);
        int psnumber = p.number;
        System.out.println("--------------------------Cancelled Successfully--------------------------------");
        if (p.allotted.equals("L")) {
            lower_berth_position.add(psnumber);
            available_lower_berth++;
        } else if (p.allotted.equals("M")) {
            middle_berth_position.add(psnumber);
            available_middle_berth++;
        } else if (p.allotted.equals("U")) {
            upper_berth_position.add(psnumber);
            available_upper_berth++;
        }
    }

    public void availableTickets(){
        System.out.println("Lower Berth Tickets are: " + available_lower_berth);
        System.out.println("Middle Berth Tickets are: " + available_middle_berth);
        System.out.println("Upper Berth Tickets are: " + available_upper_berth);

    }

    public void passengerDetail(){
        if (passenger_stored_data.size() == 0){
            System.out.println("No passengers detail found");
            return;
        }
        else{
            for (Passenger p : passenger_stored_data.values()){
                System.out.println("Passenger Id: " + p.passengerId);
                System.out.println("Passenger name: " + p.name);
                System.out.println("Passenger age: " + p.age);
                System.out.println("Passenger gender: " + p.gender);
                System.out.println("Child name: " + p.cname);
                System.out.println("Child age: " + p.cage);
                System.out.println("Allotted Berth: " + p.number + p.allotted);
            }
        }
    }
}
public class RailwayReservationSystem {
    public static void bookTicket(Passenger p) {
        TicketBook tb = new TicketBook();
        if (TicketBook.available_lower_berth == 0) {
            System.out.println("No Tickets Available");
            return;
        } else if (p.age > 60 && TicketBook.available_lower_berth > 0) {
            System.out.println("You are a senior citizen, arranging a lower berth");
//            call booking function in the TicketBook class
            tb.bookTicket(p, (TicketBook.lower_berth_position.get(0)), "L");
            //    removed the booked position from available positions and also decrease available seats of that particular type

            TicketBook.lower_berth_position.remove(0);
            TicketBook.available_lower_berth--;
        } else if (p.cname != "null" && TicketBook.available_lower_berth > 0) {
            System.out.println("You have a child, arranging lower berth");
            tb.bookTicket(p, (TicketBook.lower_berth_position.get(0)), "L");
            TicketBook.lower_berth_position.remove(0);
            TicketBook.available_lower_berth--;
        } else if ((p.berth_preference.equals("L") && TicketBook.available_lower_berth > 0)
                || (p.berth_preference.equals("M") && TicketBook.available_middle_berth > 0)
                || (p.berth_preference.equals("U") && TicketBook.available_upper_berth > 0)) {
            if (p.berth_preference.equals("L")) {
                System.out.println("Lower Berth Given");
                //            call booking function in the TicketBook class
                tb.bookTicket(p, (TicketBook.lower_berth_position.get(0)), "L");
                //    removed the booked position from available positions and also decrease available seats of that particular type

                TicketBook.lower_berth_position.remove(0);
                TicketBook.available_lower_berth--;
            } else if (p.berth_preference.equals("M")) {
                System.out.println("Middle Berth Given");
                tb.bookTicket(p, (TicketBook.middle_berth_position.get(0)), "M");
                TicketBook.middle_berth_position.remove(0);
                TicketBook.available_middle_berth--;
            } else if (p.berth_preference.equals("U")) {
                System.out.println("Upper Berth Given");
                tb.bookTicket(p, (TicketBook.upper_berth_position.get(0)), "U");
                TicketBook.upper_berth_position.remove(0);
                TicketBook.available_upper_berth--;
                System.out.println();
            }
        }
        else if (TicketBook.available_lower_berth > 0){
            System.out.println("Lower berth given");
            tb.bookTicket(p, (TicketBook.lower_berth_position.get(0)), "L");
            TicketBook.lower_berth_position.remove(0);
            TicketBook.available_lower_berth--;
        }
        else if (TicketBook.available_middle_berth > 0){
            System.out.println("Middle berth given");
            tb.bookTicket(p, (TicketBook.middle_berth_position.get(0)), "M");
            TicketBook.middle_berth_position.remove(0);
            TicketBook.available_middle_berth--;
        }
        else if (TicketBook.available_upper_berth >0 ){
            System.out.println("Upper berth given");
            tb.bookTicket(p, (TicketBook.upper_berth_position.get(0)), "U");
            TicketBook.upper_berth_position.remove(0);
            TicketBook.available_upper_berth--;
        }

    }
    public static void cancelTicket(int id){
        TicketBook tb = new TicketBook();
//        checking if passenger id is valid


        if (! tb.passenger_stored_data.containsKey(id)){
            System.out.println("Passenger id not found");
        }
        else {
            tb.cancelTicket(id);
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Ritika Railway Junction");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("1. Book ticket/tickets \n2. Cancel Ticket/tickets \n3. Available Tickets \n4. Booked Tickets \n5.Exit");
            int choice = sc.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Enter the Passenger Name: ");
                    String name = sc.next();
                    System.out.println("Enter the Passenger Age: ");
                    int age = sc.nextInt();
                    System.out.println("Enter Passenger Gender('M' for Male, 'F' for Female, 'O' for Others: ");
                    String gender = sc.next();
                    if ((gender.equals("F")) || (gender.equals("M"))){
                        System.out.println("1. If you have an infant, PRESS '1'\n2. If you don't have an infant, PRESS '2'");
                        int gchoice = sc.nextInt();
                        if (gchoice == 1 ){
                            System.out.println("Enter name of child");
                            String cname = sc.next();
                            System.out.println("Enter age of child");
                            int cage = sc.nextInt();
                            System.out.println("Enter the Passenger berth preference ('L' for Lower Berth, 'M' for Middle Berth, 'U' for Upper Berth: )");
                            String berth_preference = sc.next();
                            Passenger p = new Passenger(name, age, gender, cname, cage, berth_preference);
                            bookTicket(p);
                            break;
                        }
                        else if (gchoice == 2 ){
                            System.out.println("Enter the Passenger berth preference ('L' for Lower Berth, 'M' for Middle Berth, 'U' for Upper Berth: )");
                            String berth_preference = sc.next();
                            String cname = "null";
                            int cage = 0;
                            Passenger p = new Passenger(name, age, gender, cname, cage, berth_preference);
                            bookTicket(p);
                            break;
                        }
                    }
                }
                case 2: {
                    System.out.println("Enter the passenger id: ");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                }
                case 3:{
                    TicketBook tb = new TicketBook();
                    tb.availableTickets();
                    break;
                }
                case 4:{
                    TicketBook tb =new TicketBook();
                    tb.passengerDetail();
                    break;
                }
                case 5:{
                    System.out.println("Thanks For Visiting Ritika's Railway Junction");
                    loop = false;
                    break;
                }
            }
        }
    }
}
