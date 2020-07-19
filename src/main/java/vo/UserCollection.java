package vo;

import lombok.Data;

import java.util.List;

@Data
public class UserCollection {

   public  List<Reservation> booking;
    public static List<Flightdetail> flight;
   public static List<User> items;

    public void setItems(List<User> singletonList) {
    }
}
