package tn.esprit.teriak.IServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.teriak.Entities.Role;
import tn.esprit.teriak.Entities.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> getAllUsers();

    User createUser(String email, String password, Role role, String firstname, String lastname, MultipartFile profileImage, String remise) throws IOException;

    public User updateUser(Long id, Optional<String> email, Optional<String> password, Optional<Role> role, Optional<String> firstname, Optional<String> lastname, Optional<MultipartFile> profileImage,Optional<String>remise) throws IOException ;

    String storeProfileImage(MultipartFile profileImage) throws IOException;

    public Optional<User> getUserById(Long id);

    public Optional<User> getUserByEmail(String email);





    public void deleteUser(Long id);

    User getUserByToken(@NonNull HttpServletRequest request);

    public User updateUserByToken(@NonNull HttpServletRequest request, Optional<String> email, Optional<String> password,Optional<String> firstname, Optional<String> lastname, Optional<MultipartFile> profileImage) throws IOException;
}
