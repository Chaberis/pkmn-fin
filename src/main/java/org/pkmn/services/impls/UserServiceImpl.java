package org.pkmn.services.impls;
import org.pkmn.models.UserDTO;
import org.pkmn.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.pkmn.services.UserService;
import javax.security.auth.login.CredentialException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String loginUser(UserDTO userDTO) throws CredentialException {
        if (!jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(userDTO.getUsername());
        if (!passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
            throw new CredentialException("Данные неправильные");
        }
        return jwtService.createToken(userDetails.getUsername(), userDetails.getAuthorities().iterator().next());
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            throw new UsernameNotFoundException("имя занято");
        }
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        UserDTO user = new UserDTO(userDTO.getUsername(), encodedPassword, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        jdbcUserDetailsManager.createUser(user);
    }
}