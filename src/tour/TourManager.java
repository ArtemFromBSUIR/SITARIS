package tour;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TourManager {
    private static TourManager instance;
    private  List<Tour> tours;
    private TourManager() {
        this.tours = new ArrayList<>();
    }

    public static TourManager getInstance() {
        if (instance == null) {
            instance = new TourManager();
        }
        return instance;
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void bookTour(String tourName, String fullName, String phoneNumber) {

        Tour tourToBook = findTourByName(tourName);

        if (tourToBook != null) {
            String bookingId = generateBookingId();

            saveBookingToFile(bookingId, tourName, fullName, phoneNumber);

            System.out.println("Бронирование успешно сохранено.");
        } else {
            System.out.println("Тур с указанным названием не найден.");
        }
    }


    public void cancelBooking(String fullName, String tourName) {
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt")); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean canceled = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String bookedTourName = parts[1];
                    String name = parts[2];

                    if (!fullName.equals(name) || !tourName.equals(bookedTourName)) {
                        writer.write(line + "\n");
                    } else {
                        canceled = true;
                    }
                }
            }

            reader.close();
            writer.close();

            File oldFile = new File("bookings.txt");
            if (oldFile.exists()) {
                oldFile.delete();
            }

            tempFile.renameTo(new File("bookings.txt"));

            if (canceled) {
                System.out.println("Бронирование успешно отменено.");
            } else {
                System.out.println("Бронирование не найдено.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при отмене бронирования.");
        }
    }

    public void cancelBooking(String bookingId) {
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt")); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean canceled = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    String id = parts[0];

                    if (!bookingId.equals(id)) {
                        writer.write(line + "\n");
                    } else {
                        canceled = true;
                    }
                }
            }

            reader.close();
            writer.close();

            File oldFile = new File("bookings.txt");
            if (oldFile.exists()) {
                oldFile.delete();
            }

            tempFile.renameTo(new File("bookings.txt"));

            if (canceled) {
                System.out.println("Бронирование успешно отменено.");
            } else {
                System.out.println("Бронирование не найдено.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при отмене бронирования.");
        }
    }

    public void printAllBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String bookingId = parts[0];
                    String fullName = parts[2];
                    String phoneNumber = parts[3];

                    String tourName = parts[1];
                    Tour tour = findTourByName(tourName);

                    if (tour != null) {
                        System.out.println("Booking ID: " + bookingId);
                        System.out.println("ФИО: " + fullName);
                        System.out.println("Контактный номер: " + phoneNumber);
                        System.out.println(tour);
                        System.out.println("=======================================\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла с бронированиями.");
        }
    }


    private String generateBookingId() {
        return "booking_" + UUID.randomUUID().toString();
    }

    private void saveBookingToFile(String bookingId, String tourName, String fullName, String phoneNumber) {
        try (FileWriter writer = new FileWriter("bookings.txt", true)) {
            String bookingInfo = bookingId + "," + tourName + "," + fullName + "," + phoneNumber + "\n";
            writer.write(bookingInfo);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении бронирования в файл.");
        }
    }

    public Tour findTourByName(String tourName) {
        for (Tour tour : tours) {
            if (tour.getTourName().equalsIgnoreCase(tourName)) {
                return tour;
            }
        }
        return null;
    }

    public void displayAllTours() {
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }
}

