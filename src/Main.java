public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        FlightReservation[] reservations = createReservations();

        populateTree(tree, reservations);

        System.out.println();
        System.out.println("PreOrder:");

        tree.printPreOrder();
        System.out.println();

        tree.remove(reservations[8]);
        tree.remove(reservations[9]);
        tree.remove(reservations[4]);

        System.out.println();
        System.out.println("PreOrder:");

        tree.printPreOrder();
    }

    public static void populateTree(RBTree tree, FlightReservation[] reservations) {
        for (FlightReservation reservation : reservations) {
            tree.insert(reservation);
        }

        System.out.println("Reservas adicionadas a arvore com sucesso!");
    }

    public static FlightReservation[] createReservations() {
        FlightReservation[] reservations = new FlightReservation[10];

        reservations[0] = new FlightReservation(1001, "John Doe", "V001", "2024-08-20T15:30:00");
        reservations[1] = new FlightReservation(1002, "John Doe", "V001", "2024-08-20T15:30:00");
        reservations[2] = new FlightReservation(1003, "Jane Smith", "V001", "2024-09-05T10:45:00");
        reservations[3] = new FlightReservation(1004, "Michael Johnson", "V002", "2024-10-11T08:00:00");
        reservations[4] = new FlightReservation(1005, "Emily Davis", "V002", "2024-11-25T22:15:00");
        reservations[5] = new FlightReservation(1006, "William Brown", "V001", "2024-12-02T18:00:00");
        reservations[6] = new FlightReservation(1007, "Sophia Wilson", "V002", "2024-12-18T06:30:00");
        reservations[7] = new FlightReservation(1008, "James Taylor", "V001", "2025-01-14T12:00:00");
        reservations[8] = new FlightReservation(1009, "Olivia Martinez", "V002", "2025-02-28T23:45:00");
        reservations[9] = new FlightReservation(10010, "Lucas Anderson", "V001", "2025-03-20T16:15:00");

        System.out.println("Reservas criadas com sucesso!");
        return reservations;
    }

}