package tour;

import java.util.List;
import java.util.ArrayList;

public class Accommodation {
    private String type;
    private String name;
    private String roomType;
    private List<String> additionalDescription;

    public Accommodation() {
        this.type = "Отель";
        this.name = "";
        this.roomType = "";
        this.additionalDescription = new ArrayList<>();
    }

    public Accommodation(String type, String name, String roomType, List<String> additionalDescription) {
        this.type = type;
        this.name = name;
        this.roomType = roomType;
        this.additionalDescription = additionalDescription;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void addAdditionalDescription(String description) {
        this.additionalDescription.add(description);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();
        description.append("Тип: ").append(type).append("\n");
        description.append("Название: ").append(name).append("\n");
        description.append("Тип комнаты: ").append(roomType).append("\n");
        description.append("Удобства и услуги:\n");
        for (String line : additionalDescription) {
            description.append(" - ").append(line).append("\n");
        }
        return description.toString();
    }
}

