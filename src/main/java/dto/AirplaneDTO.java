package dto;

public class AirplaneDTO {
    private int id;
    private String code;
    private String capacityType;

    public AirplaneDTO() {}

    public AirplaneDTO(int id, String code, String capacityType) {
        this.id = id;
        this.code = code;
        this.capacityType = capacityType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getCapacityType() { return capacityType; }
    public void setCapacityType(String capacityType) { this.capacityType = capacityType; }

    @Override
    public String toString() {
        return code;
    }
} 