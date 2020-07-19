package vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import model.Role;

import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class User  {
    private Integer id;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    List<model.Role> roleList;

    public void setRoles(ArrayList<Role> roles) {
    }
}
