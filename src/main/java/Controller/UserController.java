package Controller;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Collections;


@RestController
@RequestMapping("/flightbooking/account")
public class UserController {

    @Autowired
    private UserService userService;
  //creating new user
    @PostMapping("/users")
    public User create(@RequestBody User user) /*throws NotImplementedException*/ {
        return userService.create(user);
    }

    //shows the flight details to the user
    @GetMapping("/users/flight_details/{userId}")
    public Flightdetail read(@PathVariable int id)/* throws NotImplementedException*/ {
        return userService.read(id);
    }

    //booking
    @PutMapping("/users/{id}")
    public String booking(@PathParam("id") Integer id,@RequestBody User user, @RequestBody Flightdetail flight, @RequestBody Reservation res) {
        UserService.bookflight(id,user,flight, res);
        return("booked");
        //throw new NotImplementedException();

    }

  

    @DeleteMapping("/users/{userId}")
    public ResponseEntity delete(@PathParam("id") Integer id,@RequestBody User us,@RequestBody Flightdetail fl,@RequestBody Reservation rs) {
        UserService.delete(id,us,fl,rs);
        return null;
       // throw new NotImplementedException();
    }


    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserCollection> search(@RequestParam String username) {
        UserCollection userCollection = new UserCollection();
        User user = userService.search(username);
        userCollection.setItems(Collections.singletonList(user));
        return new ResponseEntity<>(userCollection, HttpStatus.OK);

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody User user) {
        return userService.register(user);

    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}

