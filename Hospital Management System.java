import java.util.ArrayList;

import java.util.HashMap;

import java.util.Scanner;



public class HospitalSys {



    private static HashMap<Integer, Patient> patients = new HashMap<>();

    private static HashMap<Integer, Appt> appts = new HashMap<>();

    private static HashMap<Integer, Staff> staffList = new HashMap<>();

    private static Inventory inv = new Inventory();

    private static int patientId = 1001;

    private static int staffId = 501;



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean running = true;



        while (running) {

            System.out.println("\n--- Hospital Management System ---");

            System.out.println("1. Register Patient");

            System.out.println("2. Schedule Appointment");

            System.out.println("3. Manage EHR");

            System.out.println("4. Billing");

            System.out.println("5. Inventory");

            System.out.println("6. Staff Management");

            System.out.println("7. Exit");

            System.out.print("Enter option: ");



            int option = sc.nextInt();

            switch (option) {

                case 1:

                    registerPatient();

                    break;

                case 2:

                    scheduleAppt();

                    break;

                case 3:

                    manageEhr();

                    break;

                case 4:

                    handleBilling();

                    break;

                case 5:

                    manageInventory();

                    break;

                case 6:

                    manageStaff();

                    break;

                case 7:

                    running = false;

                    break;

                default:

                    System.out.println("Invalid option. Try again.");

            }

        }

        sc.close();

    }



    public static void registerPatient() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");

        String name = sc.nextLine();

        System.out.print("Enter age: ");

        int age = sc.nextInt();

        sc.nextLine(); 

        System.out.print("Enter address: ");

        String addr = sc.nextLine();

        System.out.print("Enter contact: ");

        String contact = sc.nextLine();



        Patient p = new Patient(patientId, name, age, addr, contact);

        patients.put(patientId, p);



        System.out.println("Patient registered with ID: " + patientId);

        patientId++;

    }



    public static void scheduleAppt() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient ID: ");

        int pId = sc.nextInt();

        sc.nextLine(); 



        if (patients.containsKey(pId)) {

            System.out.print("Enter date (yyyy-mm-dd): ");

            String date = sc.nextLine();

            System.out.print("Enter doctor: ");

            String doctor = sc.nextLine();



            Appt a = new Appt(pId, date, doctor);

            appts.put(pId, a);



            System.out.println("Appointment scheduled for patient ID: " + pId);

        } else {

            System.out.println("Patient not found.");

        }

    }



    public static void manageEhr() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient ID: ");

        int pId = sc.nextInt();

        sc.nextLine(); 



        if (patients.containsKey(pId)) {

            Patient p = patients.get(pId);

            System.out.println("EHR for " + p.getName() + ":");

            p.displayHistory();

            System.out.print("Add new record? (yes/no): ");

            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("yes")) {

                System.out.print("Enter record: ");

                String record = sc.nextLine();

                p.addRecord(record);

                System.out.println("Record added.");

            }

        } else {

            System.out.println("Patient not found.");

        }

    }



    public static void handleBilling() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient ID: ");

        int pId = sc.nextInt();



        if (patients.containsKey(pId)) {

            Billing bill = new Billing(pId);

            System.out.print("Enter amount: ");

            double amt = sc.nextDouble();

            bill.addCharge(amt);

            bill.displayBill();

        } else {

            System.out.println("Patient not found.");

        }

    }



    public static void manageInventory() {

        Scanner sc = new Scanner(System.in);

        System.out.println("--- Inventory Management ---");

        System.out.println("1. View Inventory");

        System.out.println("2. Add Supplies");

        System.out.print("Select option: ");

        int option = sc.nextInt();



        switch (option) {

            case 1:

                inv.display();

                break;

            case 2:

                sc.nextLine(); 

                System.out.print("Enter supply name: ");

                String item = sc.nextLine();

                System.out.print("Enter quantity: ");

                int qty = sc.nextInt();

                inv.update(item, qty);

                break;

            default:

                System.out.println("Invalid option.");

        }

    }



    public static void manageStaff() {

        Scanner sc = new Scanner(System.in);

        System.out.println("--- Staff Management ---");

        System.out.println("1. Add Staff");

        System.out.println("2. View Staff");

        System.out.print("Select option: ");

        int option = sc.nextInt();



        switch (option) {

            case 1:

                sc.nextLine(); 

                System.out.print("Enter name: ");

                String name = sc.nextLine();

                System.out.print("Enter position: ");

                String pos = sc.nextLine();

                System.out.print("Enter salary: ");

                double sal = sc.nextDouble();

                Staff s = new Staff(staffId, name, pos, sal);

                staffList.put(staffId, s);

                System.out.println("Staff added with ID: " + staffId);

                staffId++;

                break;

            case 2:

                for (Staff sObj : staffList.values()) {

                    sObj.display();

                }

                break;

            default:

                System.out.println("Invalid option.");

        }

    }

}



// Supporting Classes



class Patient {

    private int id;

    private String name;

    private int age;

    private String address;

    private String contact;

    private ArrayList<String> history;



    public Patient(int id, String name, int age, String address, String contact) {

        this.id = id;

        this.name = name;

        this.age = age;

        this.address = address;

        this.contact = contact;

        this.history = new ArrayList<>();

    }



    public String getName() {

        return name;

    }



    public void addRecord(String record) {

        history.add(record);

    }



    public void displayHistory() {

        System.out.println("Medical History:");

        for (String rec : history) {

            System.out.println(rec);

        }

    }

}



class Appt {

    private int patientId;

    private String date;

    private String doctor;



    public Appt(int patientId, String date, String doctor) {

        this.patientId = patientId;

        this.date = date;

        this.doctor = doctor;

    }



    public void display() {

        System.out.println("Appointment on " + date + " with Dr. " + doctor);

    }

}



class Staff {

    private int id;

    private String name;

    private String position;

    private double salary;



    public Staff(int id, String name, String position, double salary) {

        this.id = id;

        this.name = name;

        this.position = position;

        this.salary = salary;

    }



    public void display() {

        System.out.println("Staff ID: " + id + ", Name: " + name + ", Position: " + position + ", Salary: $" + salary);

    }

}



class Inventory {

    private HashMap<String, Integer> supplies;



    public Inventory() {

        supplies = new HashMap<>();

    }



    public void update(String item, int qty) {

        supplies.put(item, qty);

        System.out.println("Updated " + item + " to " + qty + " units.");

    }



    public void display() {

        System.out.println("--- Inventory ---");

        for (String item : supplies.keySet()) {

            System.out.println(item + ": " + supplies.get(item) + " units");

        }

    }

}



class Billing {

    private int patientId;

    private double total;



    public Billing(int patientId) {

        this.patientId = patientId;

        this.total = 0;

    }



    public void addCharge(double amount) {

        total += amount;

    }



    public void displayBill() {

        System.out.println("Patient ID: " + patientId + ", Total Bill: $" + total);

    }

}
