package narif.poc.springsecurity.springsecurityjpa;

import narif.poc.springsecurity.springsecurityjpa.models.MyUserDetails;
import narif.poc.springsecurity.springsecurityjpa.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> byUserName = repository.findByUsername(userName);
        return byUserName.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException(userName));
    }
}
