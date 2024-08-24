import java.util.Objects;

public class RBTree {
    private Node root;
    private final Node TNULL;

    public RBTree() {
        this.TNULL = new Node();
        this.TNULL.setColor("BLACK");
        this.root = TNULL;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void find(int code) {
        Node current = this.root;
        boolean found = false;
        while (current != this.TNULL) {
            if (code == current.getReservation().getCode()) {
                System.out.println("Reserva encontrada: ID = " + code + ", Passageiro = " + current.getReservation().getPassengerName() + ", Voo = " + current.getReservation().getFlight() + ", Horario = " + current.getReservation().getDepartureHour());
                found = true;
                break;
            } else if (code < current.getReservation().getCode()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (!found) {
            System.out.println("Reserva não encontrada: ID = " + code);
        }
    }

    public void printInOrder(Node node) {
        if (node != this.TNULL) {
            printInOrder(node.getLeft());
            System.out.println(node.getReservation().getCode());
            printInOrder(node.getRight());
        }
    }

    private void printPreOrderHelper(Node node) {
        if (node != this.TNULL) {
            System.out.println(node.getReservation().getCode());
            printPreOrderHelper(node.getLeft());
            printPreOrderHelper(node.getRight());
        }
    }

    public void printPreOrder() {
        printPreOrderHelper(this.root);
    }

    public void printPostOrder(Node node) {
        if (node != this.TNULL) {
            printPostOrder(node.getLeft());
            printPostOrder(node.getRight());
            System.out.println(node.getReservation().getCode());
        }
    }

    public void list(String flight) {
        listHelper(this.root, flight);
    }

    private void listHelper(Node node, String flight) {
        if (node != this.TNULL) {
            listHelper(node.getLeft(), flight);
            if (node.getReservation().getFlight().equals(flight)) {
                System.out.println("Reserva encontrada: ID = " + node.getReservation().getCode() + ", Passageiro = " + node.getReservation().getPassengerName() + ", Voo = " + node.getReservation().getFlight() + ", Horário = " + node.getReservation().getDepartureHour());
            }
            listHelper(node.getRight(), flight);
        }
    }

    public void changeColor(Node node, String color) {
        if (color.equals("RED")) {
            node.setColor("RED");
        } else if (color.equals("BLACK")) {
            node.setColor("BLACK");
        }
    }

    private void leftRotate(Node node) {
        Node aux = node.getRight();
        node.setRight(aux.getLeft());

        if (aux.getLeft() != this.TNULL) {
            aux.getLeft().setParent(node);
        }

        aux.getParent().setParent(node.getParent());

        if (node.getParent() == null) {
            this.setRoot(aux);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(aux);
        } else {
            node.getParent().setRight(aux);
        }

        aux.setLeft(node);
        node.setParent(aux);
    }

    private void rightRotate(Node node) {
        Node aux = node.getLeft();
        node.setLeft(aux.getRight());

        if (aux.getRight() != this.TNULL) {
            aux.getRight().setParent(node);
        }

        aux.setParent(node.getParent());

        if (node.getParent() == null) {
            this.setRoot(aux);
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(aux);
        } else {
            node.getParent().setLeft(aux);
        }

        aux.setRight(node);
        node.setParent(aux);
    }

    private void fixInsert(Node node) {
        Node aux;

        while (node.getParent().getColor().equals("RED")) {
            if (node.getParent() == node.getParent().getParent().getRight()) {
                aux = node.getParent().getParent().getLeft(); // Uncle node

                if (aux.getColor().equals("RED")) { // Uncle is RED
                    aux.setColor("BLACK");
                    node.getParent().setColor("BLACK");
                    node.getParent().getParent().setColor("RED");
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) { // node is LEFT child
                        node = node.getParent();
                        rightRotate(node);
                    }

                    // node is RIGHT child
                    node.getParent().setColor("BLACK");
                    node.getParent().getParent().setColor("RED");
                    leftRotate(node.getParent().getParent());
                }
            } else {
                aux = node.getParent().getParent().getRight();

                if (aux.getColor().equals("RED")) { // Uncle is RED
                    aux.setColor("BLACK");
                    node.getParent().setColor("BLACK");
                    node.getParent().getParent().setColor("RED");
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }

                    node.getParent().setColor("BLACK");
                    node.getParent().getParent().setColor("RED");
                    rightRotate(node.getParent().getParent());
                }
            }
            if (node == this.root) {
                break;
            }
        }

        this.root.setColor("BLACK");
    }

    public void insert(FlightReservation reservation) {
        Node node = new Node(reservation);
        node.setParent(null);
        node.setData(reservation);
        node.setLeft(this.TNULL);
        node.setRight(this.TNULL);
        node.setColor("RED");

        Node aux = this.root;
        Node parent = null;

        while (aux != this.TNULL) {
            parent = aux;
            if (reservation.getCode() < aux.getReservation().getCode()) {
                aux = aux.getLeft();
            } else if (reservation.getCode() > aux.getReservation().getCode()) {
                aux = aux.getRight();
            } else {
                return;
            }
        }

        node.setParent(parent);
        if (parent == null) {
            this.root = node;
        } else if (reservation.getCode() < parent.getReservation().getCode()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }

        if (node.getParent() == null) {
            node.setColor("BLACK");
            return;
        }

        if (node.getParent().getParent() == null) {
            return;
        }

        fixInsert(node);
        System.out.println("Reserva inserida com sucesso: ID = " + reservation.getCode() + ", Passageiro = " + reservation.getPassengerName() + ", Voo = " + reservation.getFlight() + ", Horário = " + reservation.getDepartureHour());
    }

    private void transplant(Node target, Node with) {
        if (target.getParent() == null) {
            this.root = with;
        } else if (target == target.getParent().getLeft()) {
            target.getParent().setLeft(with);
        } else {
            target.getParent().setRight(with);
        }

        with.setParent(target.getParent());
    }

    private Node minimum(Node node) {
        while (node.getLeft() != this.TNULL) {
            node = node.getLeft();
        }

        return node;
    }

    private void fixDelete(Node node) {
        Node aux;
        while (node != this.root && node.getColor().equals("BLACK")) {
            if (node == node.getParent().getLeft()) {
                aux = node.getParent().getRight();
                if (aux.getColor().equals("RED")) {
                    aux.setColor("BLACK");
                    node.getParent().setColor("RED");
                    leftRotate(node.getParent());
                    aux = node.getParent().getRight();
                }

                if (aux.getLeft().getColor().equals("BLACK") && aux.getRight().getColor().equals("BLACK")) {
                    aux.setColor("RED");
                    node = node.getParent();
                } else {
                    if (aux.getRight().getColor().equals("BLACK")) {
                        aux.getLeft().setColor("BLACK");
                        aux.setColor("RED");
                        rightRotate(aux);
                        aux = node.getParent().getRight();
                    }

                    aux.setColor(node.getParent().getColor());
                    node.getParent().setColor("BLACK");
                    aux.getRight().setColor("BLACK");
                    leftRotate(node.getParent());
                    node = this.root;
                }
            } else {
                aux = node.getParent().getLeft();
                if (aux.getColor().equals("RED")) {
                    aux.setColor("BLACK");
                    node.getParent().setColor("RED");
                    rightRotate(node.getParent());
                    aux = node.getParent().getLeft();
                }

                if (aux.getRight().getColor().equals("BLACK") && aux.getLeft().getColor().equals("BLACK")) {
                    aux.setColor("RED");
                    node = node.getParent();
                } else {
                    if (aux.getLeft().getColor().equals("BLACK")) {
                        aux.getRight().setColor("BLACK");
                        aux.setColor("RED");
                        leftRotate(aux);
                        aux = node.getParent().getLeft();
                    }

                    aux.setColor(node.getParent().getColor());
                    node.getParent().setColor("BLACK");
                    aux.getLeft().setColor("BLACK");
                    rightRotate(node.getParent());
                    node = this.root;
                }
            }
        }

        node.setColor("BLACK");
    }

    private void removeHelper(Node node, FlightReservation reservation) {
        Node aux = this.TNULL;
        Node temp;
        while (node != this.TNULL) {
            if (node.getReservation().getCode() == reservation.getCode()) {
                aux = node;
            }

            if (node.getReservation().getCode() <= reservation.getCode()) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }

        if (aux == this.TNULL) {
            return;
        }

        temp = aux;
        String tempColor = temp.getColor();
        Node aux2;

        if (aux.getLeft() == this.TNULL) {
            aux2 = aux.getRight();
            transplant(aux, aux.getRight());
        } else if (aux.getRight() == this.TNULL) {
            aux2 = aux.getLeft();
            transplant(aux, aux.getLeft());
        } else {
            temp = minimum(aux.getRight());
            tempColor = temp.getColor();
            aux2 = temp.getRight();
            if (temp.getParent() == aux) {
                aux2.setParent(temp);
            } else {
                transplant(temp, temp.getRight());
                temp.setRight(aux.getRight());
                temp.getRight().setParent(temp);
            }

            transplant(aux, temp);
            temp.setLeft(aux.getLeft());
            temp.getLeft().setParent(temp);
            temp.setColor(aux.getColor());
        }

        if (tempColor.equals("BLACK")) {
            fixDelete(aux2);
        }
    }


    public void remove(FlightReservation reservation) {
        removeHelper(this.root, reservation);
        System.out.println("Reserva removida com sucesso: ID = " + reservation.getCode());
    }

}
