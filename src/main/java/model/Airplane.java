package model;

public class Airplane {
    private int id;
    private String code;
    private CapacityType capacityType;

    public enum CapacityType {
        SMALL, MEDIUM, LARGE
    }

    public Airplane() {}

    public Airplane(int id, String code, CapacityType capacityType) {
        this.id = id;
        this.code = code;
        this.capacityType = capacityType;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public CapacityType getCapacityType() { return capacityType; }
    public void setCapacityType(CapacityType capacityType) { this.capacityType = capacityType; }
} 