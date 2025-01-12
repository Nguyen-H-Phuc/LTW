package vn.edu.hcmuaf.fit.doancuoiki.model;

public class OrderDetail {
    private int orderId;
    private String licensePlate;
    private int quanlity;
    private double priceAtOrder;

    public OrderDetail(int orderId, String licensePlate, int quanlity, double priceAtOrder) {
        this.orderId = orderId;
        this.licensePlate = licensePlate;
        this.quanlity = quanlity;
        this.priceAtOrder = priceAtOrder;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public double getPriceAtOrder() {
        return priceAtOrder;
    }

    public void setPriceAtOrder(double priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }
}
