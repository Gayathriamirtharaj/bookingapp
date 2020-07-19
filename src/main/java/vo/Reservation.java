package vo;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Reservation{
    private Integer id;
    private Integer flight_id;
    private Integer seat_no;
    private Integer user_id;
    private Integer agent_id;

}
