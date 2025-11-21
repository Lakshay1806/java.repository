package railway.reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Passenger {
    int seatNo;
    String name;
    int age;

    Passenger(int seatNo, String name, int age) {
        this.seatNo = seatNo;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Seat " + seatNo + " | Name: " + name + " | Age: " + age;
    }
}

public class RailwayReservationGUI extends JFrame {

    private boolean[] seats;
    private ArrayList<Passenger> passengers;
    private int totalSeats;

    private JTextArea outputArea;
    private JButton btnShowSeats, btnBook, btnCancel, btnShowPassengers, btnExit;

    public RailwayReservationGUI(int seatCount) {
        super("Mini Railway Reservation System");

        this.totalSeats = seatCount;
        this.seats = new boolean[seatCount + 1];
        this.passengers = new ArrayList<>();

        initUI();
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUI() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Mini Railway Reservation System", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        main.add(title, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(outputArea);
        main.add(scroll, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        btnShowSeats = new JButton("Show Available Seats");
        btnBook = new JButton("Book a Seat");
        btnCancel = new JButton("Cancel Booking");
        btnShowPassengers = new JButton("Show Passengers");
        btnExit = new JButton("Exit");

        Dimension btnSize = new Dimension(180, 35);
        btnShowSeats.setMaximumSize(btnSize);
        btnBook.setMaximumSize(btnSize);
        btnCancel.setMaximumSize(btnSize);
        btnShowPassengers.setMaximumSize(btnSize);
        btnExit.setMaximumSize(btnSize);

        right.add(btnShowSeats); right.add(Box.createVerticalStrut(8));
        right.add(btnBook); right.add(Box.createVerticalStrut(8));
        right.add(btnCancel); right.add(Box.createVerticalStrut(8));
        right.add(btnShowPassengers); right.add(Box.createVerticalStrut(8));
        right.add(btnExit);

        main.add(right, BorderLayout.EAST);
        add(main);

        btnShowSeats.addActionListener(e -> showAvailableSeats());
        btnBook.addActionListener(e -> bookSeat());
        btnCancel.addActionListener(e -> cancelBooking());
        btnShowPassengers.addActionListener(e -> showPassengers());
        btnExit.addActionListener(e -> dispose());

        outputArea.setText("Welcome to Mini Railway Reservation System\n\n");
        showAvailableSeats();
    }

    private void showAvailableSeats() {
        StringBuilder sb = new StringBuilder("Available Seats:\n");
        boolean any = false;

        for (int i = 1; i <= totalSeats; i++) {
            if (!seats[i]) {
                sb.append(i).append(" ");
                any = true;
            }
        }

        if (!any) sb.append("No seats available.");

        append(sb.toString());
    }

    private void bookSeat() {
        if (passengers.size() == totalSeats) {
            JOptionPane.showMessageDialog(this, "Train Full!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Details", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) return;

        String name = nameField.getText().trim();
        int age;

        try {
            age = Integer.parseInt(ageField.getText().trim());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid age!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int assigned = -1;
        for (int i = 1; i <= totalSeats; i++) {
            if (!seats[i]) { assigned = i; break; }
        }

        seats[assigned] = true;
        passengers.add(new Passenger(assigned, name, age));

        append("Seat booked successfully! Seat No: " + assigned);
    }

    private void cancelBooking() {
        String s = JOptionPane.showInputDialog(this, "Enter Seat No to Cancel:");
        if (s == null) return;

        int seat;
        try {
            seat = Integer.parseInt(s.trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Seat No.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (seat < 1 || seat > totalSeats || !seats[seat]) {
            JOptionPane.showMessageDialog(this, "Seat Not Booked!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        seats[seat] = false;

        passengers.removeIf(p -> p.seatNo == seat);

        append("Seat " + seat + " cancelled successfully.");
    }

    private void showPassengers() {
        if (passengers.isEmpty()) {
            append("No bookings yet.");
            return;
        }

        passengers.sort((a, b) -> Integer.compare(a.seatNo, b.seatNo));

        StringBuilder sb = new StringBuilder("Passenger List:\n");
        for (Passenger p : passengers)
            sb.append(p).append("\n");

        append(sb.toString());
    }

    private void append(String text) {
        outputArea.append(text + "\n\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RailwayReservationGUI(10).setVisible(true);
        });
    }
}
