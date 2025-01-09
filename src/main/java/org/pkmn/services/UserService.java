package org.pkmn.services;
import org.pkmn.models.UserDTO;
import javax.security.auth.login.CredentialException;


public interface UserService {
    String loginUser(UserDTO userDTO) throws CredentialException;
    void registerUser(UserDTO userDTO);
}