package Service;
import Exception.ServiceException;
import model.BookingEntity;
import model.FlightEntity;
import model.UserEntity;
import Repository.UserRepository;
import Security.JWTProvider;
import vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private static ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtProvider.createToken(email, userRepository.findByEmail(email).getRoles());
            return new LoginResponse(token);
        } catch (AuthenticationException e) {
            throw new ServiceException("Invalid username/password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public RegistrationResponse register(User user) {

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        if (!userRepository.existsByEmail(userEntity.getEmail())) {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            UserEntity savedUserEntity = (UserEntity) userRepository.save(userEntity);
            System.out.println("Saved User: " + savedUserEntity);
            String token = jwtProvider.createToken(userEntity.getEmail(), userEntity.getRoles());
            return new RegistrationResponse(token);
        } else {
            throw new ServiceException("Email already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    public User search(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new ServiceException("User Not Found", HttpStatus.NOT_FOUND);
        }
        return modelMapper.map(userEntity, User.class);
    }

    public String refresh(String email) {
        return jwtProvider.createToken(email, userRepository.findByEmail(email).getRoles());
    }
    static UserCollection userCollection = new UserCollection();
    public Flightdetail read(int id)
    {
        return userCollection.flight.get(id);
    }
    public  User readUser(int id) {
        return userCollection.items.get(id);
    }
    public  User create(User user)
    {
        userCollection.items.add(user);
        return (User) userCollection.items;
    }
    public static String  bookflight(int id, User user, Flightdetail fd, Reservation bk) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        FlightEntity flightEntity = modelMapper.map(fd, FlightEntity.class);
        BookingEntity bookingEntity = modelMapper.map(bk, BookingEntity.class);
        if (!userRepository.existsByEmail(userEntity.getEmail())) {
            UserCollection.items.add(user);
            int seats = flightEntity.getno_Seats();
            int sno = bookingEntity.getSeat_no();
            int uid = userEntity.getId();
            int fid = flightEntity.getFlight_id();
            if (seats != 0 && sno == 0) {
                seats--;
                sno++;
                flightEntity.setno_Seats(seats);
                bookingEntity.setSeat_no(sno);
                bookingEntity.setFlight_id(fid);
                bookingEntity.setId(id);
                bookingEntity.setUser_id(uid);
            }

        System.out.println(userCollection.booking.get(id));
        return ("booked");}
        else{
            return "not booked";
        }
    }
    public static String delete(int id,User user,Flightdetail flightdetail,Reservation reservation)
    {
        UserEntity u=modelMapper.map(user,UserEntity.class);
        FlightEntity f=modelMapper.map(user,FlightEntity.class);
        BookingEntity b=modelMapper.map(user,BookingEntity.class);
       if(!u.getRoles().equals("ROLE_ADMIN") )
       {
           int tseats= f.getno_Seats();
           int snum = b.getSeat_no();
           int uid = u.getId();
           int fid = f.getFlight_id();
           userCollection.items.remove(id);
           userCollection.booking.remove(id);
           tseats++;
           snum--;
           return("deleted");
       }
       else{
        return "not deleted";}
    }
}
