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

    User updateUser(Long id, String email, String password, Role role, String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException;

    String storeProfileImage(MultipartFile profileImage) throws IOException;

    public Optional<User> getUserById(Long id);

    public Optional<User> getUserByEmail(String email);
    User createUser(String email, String password, Role role, String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException;


    User updateUser(User user);

    public void deleteUser(Long id);

    User getUserByToken(@NonNull HttpServletRequest request);

    User updateUserByToken(@NonNull HttpServletRequest request, String email, String password, String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException;
}
