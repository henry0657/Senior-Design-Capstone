package htoups2.prototype;

/**
 * Created by Henry on 9/27/2016.
 */
public class Data {

    private String street, timestamp;
    private Double latitude,longitude, height;

    public Data(String street, String timestamp, Double latitude,Double longitude,Double height){

        this.setStreet(street);
        this.setTimestamp(timestamp);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setHeight(height);


    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
