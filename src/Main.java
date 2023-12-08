import org.w3c.dom.*;
import tour.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TourManager tourManager = TourManager.getInstance();
        try {
            File inputFile = new File("tours.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList tourList = doc.getElementsByTagName("tour");

            for (int i = 0; i < tourList.getLength(); i++) {
                Node tourNode = tourList.item(i);
                if (tourNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tourElement = (Element) tourNode;

                    String tourId = tourElement.getAttribute("id");
                    String currency = tourElement.getElementsByTagName("currency").item(0).getTextContent();
                    String startDate = tourElement.getElementsByTagName("start_date").item(0).getTextContent();
                    int durationNights = Integer.parseInt(tourElement.getElementsByTagName("duration_nights").item(0).getTextContent());
                    double price = Double.parseDouble(tourElement.getElementsByTagName("price").item(0).getTextContent());

                    Element overviewElement = (Element) tourElement.getElementsByTagName("overview").item(0);
                    String tourName = overviewElement.getElementsByTagName("title").item(0).getTextContent();
                    NodeList tourDescriptionNodes = overviewElement.getElementsByTagName("highlight");
                    List<String> tourDescription = new ArrayList<String>();
                    for (int j = 0; j < tourDescriptionNodes.getLength(); j++) {
                        tourDescription.add(tourDescriptionNodes.item(j).getTextContent());
                    }

                    Element departureElement = (Element) tourElement.getElementsByTagName("departure").item(0);
                    String departureCountry = departureElement.getElementsByTagName("country").item(0).getTextContent();
                    String departureCity = departureElement.getElementsByTagName("city").item(0).getTextContent();
                    Location departure = new Location(departureCountry, departureCity);

                    Element destinationElement = (Element) tourElement.getElementsByTagName("destination").item(0);
                    String destinationCountry = destinationElement.getElementsByTagName("country").item(0).getTextContent();
                    String destinationCity = destinationElement.getElementsByTagName("city").item(0).getTextContent();
                    Location destination = new Location(destinationCountry, destinationCity);

                    Element participantsElement = (Element) tourElement.getElementsByTagName("participants").item(0);
                    int adults = Integer.parseInt(participantsElement.getElementsByTagName("adults").item(0).getTextContent());
                    int children = Integer.parseInt(participantsElement.getElementsByTagName("children").item(0).getTextContent());
                    Participants participants = new Participants(adults, children);

                    Element accommodationElement = (Element) tourElement.getElementsByTagName("accommodation").item(0);
                    String accommodationType = accommodationElement.getAttribute("type");
                    String accommodationName = accommodationElement.getElementsByTagName("title").item(0).getTextContent();
                    String roomType = accommodationElement.getElementsByTagName("room_type").item(0).getTextContent();

                    NodeList additionalDescriptionNodes = accommodationElement.getElementsByTagName("highlight");
                    List<String> additionalDescription = new ArrayList<>();
                    for (int j = 0; j < additionalDescriptionNodes.getLength(); j++) {
                        additionalDescription.add(additionalDescriptionNodes.item(j).getTextContent());
                    }
                    Accommodation accommodation = new Accommodation(accommodationType, accommodationName, roomType, additionalDescription);

                    Element includedElement = (Element) tourElement.getElementsByTagName("included").item(0);
                    NodeList includedDescriptionNodes = includedElement.getElementsByTagName("highlight");
                    List<String> includedDescription = new ArrayList<>();
                    for (int j = 0; j < includedDescriptionNodes.getLength(); j++) {
                        includedDescription.add(includedDescriptionNodes.item(j).getTextContent());
                    }

                    tourManager.addTour(new Tour(tourId, departure, destination, currency, startDate, durationNights, participants, price, accommodation, includedDescription, tourName, tourDescription));
                    //System.out.println("Parsed Tour: " + tour);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tourManager.displayAllTours();

        tourManager.bookTour("Очарование Парижа", "Линкевич Артём Александрович", "+375447319100");
        tourManager.bookTour("Барселона для семьи", "Лапицкий Николай Леонидович", "+375298315120");
        tourManager.printAllBookings();

        tourManager.cancelBooking("Линкевич Артём Александрович", "Очарование Парижа");
        tourManager.printAllBookings();
    }
}