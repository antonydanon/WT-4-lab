package model;

public class Order {
    private long orderId;
    private long userId;
    private long roomId;

    public Order(long orderId, long userId, long roomId) {
        this.orderId = orderId;
        this.userId = userId;
        this.roomId = roomId;
    }

    public Order(long userId, long roomId) {
        this.userId = userId;
        this.roomId = roomId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
