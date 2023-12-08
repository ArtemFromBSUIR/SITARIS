package tour;

import java.io.*;
import java.util.List;

public class Tour {
    private final String tourId;
    private Location departure;
    private Location destination;
    private String currency;
    private String startDate;
    private int durationNights;
    private Participants participants;
    private double price;
    private Accommodation accommodation;
    private List<String> description;
    private String tourName;
    private List<String> tourDescription;

    public Tour(String tourId, Location departure, Location destination, String currency, String startDate, int durationNights, Participants participants, double price, Accommodation accommodation, List<String> description, String tourName, List<String> tourDescription) {
        this.tourId = tourId;
        this.departure = departure;
        this.destination = destination;
        this.currency = currency;
        this.startDate = startDate;
        this.durationNights = durationNights;
        this.participants = participants;
        this.price = price;
        this.accommodation = accommodation;
        this.description = description;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
    }

    @Override
    public String toString() {
        StringBuilder description_t = new StringBuilder();
        for (String line : tourDescription) {
            description_t.append(" - ").append(line).append("\n");
        }
        StringBuilder description_d = new StringBuilder();
        for (String line : description) {
            description_d.append(" - ").append(line).append("\n");
        }
        return "\nTour ID: " + tourId +
                "\nНазвание тура: " + tourName +
                "\nОписание тура:\n" + description_t.toString()+
                "Вылет из: " + departure +
                "\nПрилет в: " + destination +
                "\nВалюта: " + currency +
                "\nДата начала: " + startDate +
                "\nКол-во ночей: " + durationNights +
                "\nЛюди: " + participants +
                "\nЦена: " + price +
                "\nРазмещение: " + accommodation +
                "Описание:\n" + description_d.toString();
    }

    public String getTourName() {
        return this.tourName;
    }
}
