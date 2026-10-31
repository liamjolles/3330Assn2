package Proj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


 // demo of the University IT Help Desk system
 
public class Main {

    public static void main(String[] args) {

        // Technicians and  HashSet behavior
        Technician alice = new Technician("Alice");
        Technician bob = new Technician("Bob");
        Technician aliceDuplicate = new Technician("Alice"); // same name as alice

        Set<Technician> technicianSet = new HashSet<Technician>();
        technicianSet.add(alice);
        technicianSet.add(bob);
        technicianSet.add(aliceDuplicate);

        System.out.println("< HashSet<Technician> demo >");
        System.out.println("Added 3 technicians (two named Alice). Set size = "
                + technicianSet.size() + " (expected 2)");
        System.out.println("alice.equals(aliceDuplicate): " + alice.equals(aliceDuplicate));
        System.out.println();

        // Create requests (2 of each type) 
        SupportRequest r1 = new PasswordResetRequest(1, "Reset faculty email password", 10, "jdoe");
        SupportRequest r2 = new PasswordResetRequest(2, "Unlock student account", 10, "asmith");
        SupportRequest r3 = new DeviceSetupRequest(3, "Set up lab laptops", 5, 4);
        SupportRequest r4 = new DeviceSetupRequest(4, "Configure office printer", 5, 2);
        SupportRequest r5 = new NetworkIssueRequest(5, "Dorm Wi Fi outage", 15, 25);
        SupportRequest r6 = new NetworkIssueRequest(6, "Library switch failure", 15, 8);

        // Add to help desk
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.addRequest(r1);
        helpDesk.addRequest(r2);
        helpDesk.addRequest(r3);
        helpDesk.addRequest(r4);
        helpDesk.addRequest(r5);
        helpDesk.addRequest(r6);

        System.out.println("<All requests (before assignment)>");
        helpDesk.printAllRequests();
        System.out.println();

        // Assign requests across 2 technicians
        helpDesk.assignRequest(alice, 1);
        helpDesk.assignRequest(alice, 3);
        helpDesk.assignRequest(alice, 5);
        helpDesk.assignRequest(bob, 2);
        helpDesk.assignRequest(bob, 4);
        helpDesk.assignRequest(bob, 6);

        // Resolve requests
        System.out.println("<Resolving requests>");
        int pts1 = helpDesk.resolveRequest(alice, 1); // PasswordReset
        System.out.println("Alice resolved request 1 and earned " + pts1 + " points");
        int pts5 = helpDesk.resolveRequest(alice, 5); // NetworkIssue
        System.out.println("Alice resolved request 5 and earned " + pts5 + " points");
        int pts4 = helpDesk.resolveRequest(bob, 4);   // DeviceSetup
        System.out.println("Bob resolved request 4 and earned " + pts4 + " points");
        System.out.println();

        //  Print all requests and assignments 
        System.out.println("<All requests (after resolutions)>");
        helpDesk.printAllRequests();
        System.out.println();

        System.out.println("<Assignments for Alice>");
        helpDesk.printAssignmentsFor(alice);
        System.out.println();
        System.out.println("<Assignments for Bob>");
        helpDesk.printAssignmentsFor(bob);
        System.out.println();

        //  Final totals + generic util
        List<Technician> technicians = new ArrayList<Technician>();
        technicians.add(alice);
        technicians.add(bob);

        System.out.println("<Final technician service points>");
        HelpDeskUtil.printAll(technicians);
        System.out.println("Total service points across technicians: "
                + HelpDeskUtil.sumPoints(technicians));
        System.out.println();

        //  Fail fast demonstrations 
        System.out.println("<Fail fast demonstrations>");

        // Invalid action 1 resolving the same request twice
        try {
            helpDesk.resolveRequest(alice, 1);
            System.out.println("ERROR: resolving twice should have thrown!");
        } catch (IllegalStateException e) {
            System.out.println("Caught IllegalStateException (resolve twice): " + e.getMessage());
        }

        // Invalid action 2 adding a duplicate request ID
        try {
            helpDesk.addRequest(new PasswordResetRequest(1, "Duplicate ID request", 10, "someone"));
            System.out.println("ERROR: duplicate ID should have thrown!");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException (duplicate ID): " + e.getMessage());
        }

        // Invalid action 3 constructing an invalid request
        try {
            new DeviceSetupRequest(99, "Bad request", 5, 0);
            System.out.println("ERROR invalid device count should have thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException (invalid deviceCount): " + e.getMessage());
        }
    }
}
