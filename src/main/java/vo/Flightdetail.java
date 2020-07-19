package vo;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Flightdetail  {
    public Flightdetail(){}
    private Integer id;
    private Integer flight_id;
    private Integer seats;
    private String origin;
    private String destination;
    private String arrival_time;
    private String departure_time;

}
