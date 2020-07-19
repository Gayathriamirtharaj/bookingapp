package Security;

import Repository.UserRepository;
import model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public abstract class UserDetailss implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //@Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email + " NOT FOUND");
        }
        System.out.println("loadUserByUsername: " + userEntity);

        return User
                .withUsername(email)
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}


