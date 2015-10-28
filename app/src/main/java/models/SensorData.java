package models;

/**
 * Created by Kevin on 30-09-2015.
 */
public class SensorData {
    private long timestamp;
    private float[] data;

    public SensorData(float[] data, long timestamp){
        this.setData(data);
        this.setTimestamp(timestamp);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }
}
