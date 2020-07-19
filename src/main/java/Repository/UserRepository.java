package Repository;
import model.FlightEntity;
import model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component

public interface UserRepository extends JpaRepository {
    boolean existsByEmail(String username);

    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

   /* default UserEntity findByRole(List<model.Role>) {
        return null;
    }*/

    FlightEntity findByFlightid(int flight_id);
    FlightEntity findById(int id);
}
