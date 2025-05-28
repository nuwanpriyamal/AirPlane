package dto;

public class AirportDTO {
    private int id;
    private String code;
    private String name;
    private String location;

    public AirportDTO() {}

    public AirportDTO(int id, String code, String name, String location) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.location = location;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
} 