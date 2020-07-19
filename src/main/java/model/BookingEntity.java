package model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "booking_info")
@ToString
@Data

public class BookingEntity {
    @OneToMany(mappedBy = "UserEntity")
    @JoinColumn(referencedColumnName = "user_id")
    @Column(unique = true,nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "[user]")
    @JoinColumn(referencedColumnName = "flightid"
    )
    @Column(unique = true,nullable=false)
    private Integer flight_id;
    @Column(unique = true,nullable = false)
    private Integer seat_no;
    @Column(unique=true,nullable=false)
    private Integer user_id;
    @Column(unique = true,nullable = true)
    private Integer agent_id;

    public void setflightId(int flight_id)
    {
        this.flight_id=flight_id;
    }
    public int getflightId() {
        return flight_id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public int getId() {
        return id;
    }
    public void setSeat_no( int seat_no)
    {
        this.seat_no=seat_no;
    }

    public int getSeat_no() {
        return seat_no;
    }
    public void setUserid(int user_id)
    {
        this.user_id=user_id;
    }
    public int getUserid()
    {
        return user_id;
    }
    public void setAgentid(int agent_id)
    {
        this.agent_id=agent_id;
    }
    public int getAgentid(){
        return agent_id;
    }

}
