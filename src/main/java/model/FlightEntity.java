package model;
import lombok.Data;
import lombok.ToString;
import java.util.*;

import javax.persistence.*;
import javax.print.attribute.IntegerSyntax;

@Table(name = "flight_info")
@ToString
@Data

public class FlightEntity {
    @Column(unique = true,nullable=true)
    private String origin;
    @Column(unique=true,nullable=true)
    private String destination;
    @Column(unique=true,nullable=false)
    private String arrival_time;
    @Column(unique=true,nullable=false)
    private String departure_time;
    @Column(unique=true,nullable = true)
    private Integer no_seats;
    @Column(unique = true,nullable=false)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "flight_id")
    private Integer flight_id;
    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @Column(unique=true,nullable=false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setOrigin(String origin)
    {
        this.origin=origin;
    }
    public String getOrigin(){
        return origin;
    }
    public void setDestination(String destination)
    {this.destination=destination;}
    public String getDestination(){
        return destination;
    }
    public void setarrival_time(String arrival_time)
    {
        this.arrival_time=arrival_time;
    }

    public String getarrival_time()
    {
        return arrival_time;
    }
    public void setdeparture_time(String departure_time)
    {
        this.departure_time=departure_time;
    }

    public String getdeparture_time()
    {
        return departure_time;
    }
    public void setflightId(int flight_id)
    {
        this.flight_id=flight_id;
    }
    public int getflightId()
    {return flight_id;}
    public void setno_Seats(int no_seats)
    {
        this.no_seats=no_seats;
    }
    public int getno_Seats()
    {
        return no_seats;
    }









}
