package valiant;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Call {
    private String type;
    private Date startAt;
    private Date endAt;
    private long duration;
    private float cost;

    public Call(String type, String startAt, String endAt, Tariff subscriberTariff) throws ParseException {
        this.type = type;
        this.startAt = parseDate(startAt);
        this.endAt = parseDate(endAt);
        this.duration = this.endAt.getTime() - this.startAt.getTime();
        this.cost = subscriberTariff.getCost(this.type, this.duration);
    }

    public String getType() {
        return this.type;
    }

    public String getStartAt() {
        return formatDateToString(this.startAt);
    }

    public String getEndAt() {
        return formatDateToString(this.endAt);
    }

    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
    }

    private String formatDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public String getDurationCall() {
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        setDateWithTimeZone(date);
        return date.format(new Date(this.duration));
    }

    public void setDateWithTimeZone(SimpleDateFormat date) {
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public float getCostCall() {
        return this.cost;
    }
}
