package model;

public class Room {
    private long id;
    private int number;
    private boolean isReserved;

    public Room(long id, int number, boolean isReserved) {
        this.id = id;
        this.number = number;
        this.isReserved = isReserved;
    }

    public Room(int number, boolean isReserved) {
        this.number = number;
        this.isReserved = isReserved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", isReserved=" + isReserved +
                '}';
    }
}
