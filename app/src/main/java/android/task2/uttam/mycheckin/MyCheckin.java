// Uttam Shrestha
// 1107016
package android.task2.uttam.mycheckin;


import java.util.Date;
import java.util.UUID;

public class MyCheckin {
    private UUID mycheckinId;

    private String checkin_Title;
    private Date checkin_Date;
    private String checkin_Restaurant;
    private String checkin_Location;
    private String checkin_Detail;

    public String getLocation() {
        return checkin_Location;
    }

    public void setLocation(String location) {
        this.checkin_Location = location;
    }



    public MyCheckin() {
        this(UUID.randomUUID());
    }
    public MyCheckin(UUID id) {
        mycheckinId = id;
        checkin_Date = new Date(); }
    public UUID getId() {
        return mycheckinId;
    }
    public String getTitle() {
        return checkin_Title;    }
    public void setTitle(String title) {
        checkin_Title = title;    }
    public Date getDate() {
        return checkin_Date;
    }
    public void setDate(Date date) {
        checkin_Date = date;
    }


    public String getRestaurantPlace() {
        return checkin_Restaurant;
    }

    public void setRestaurantPlace(String restarantPlace) {
        checkin_Restaurant = restarantPlace;
    }

    public String getDetail() {
        return checkin_Detail;
    }

    public void setDetail(String detail) {
        checkin_Detail = detail;
    }

    public String getImageFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }


}
