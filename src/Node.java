import java.util.Map;

public class Node {
    public enum Color {
        RED,
        BLACK;

        public String getColorName() {
            return this.name();
        }

    }

    private FlightReservation reservation;
    private Color color;
    private Node parent;
    private Node left;
    private Node right;

    public Node() {
        this.reservation = null;
        this.color = Color.BLACK;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Node(FlightReservation reservation) {
        this.reservation = reservation;
        this.color = Color.RED;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public FlightReservation getReservation() {
        return reservation;
    }

    public void setData(FlightReservation reservation) {
        this.reservation = reservation;
    }

    public String getColor() {
        return color.getColorName();
    }

    // TODO: usar o enum ao inves de string nos metodos

    public void setColor(String color) {
        if (color.equals("RED")) {
            this.color = Color.RED;
        } else if (color.equals("BLACK")) {
            this.color = Color.BLACK;
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

