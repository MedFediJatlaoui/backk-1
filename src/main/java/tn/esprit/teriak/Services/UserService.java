package tn.esprit.teriak.Services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.teriak.Config.JwtService;
import tn.esprit.teriak.Entities.Role;
import tn.esprit.teriak.Entities.User;
import tn.esprit.teriak.IServices.IUserService;
import tn.esprit.teriak.Repositories.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private  UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
@Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
@Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password, Role role, String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException {
        return null;
    }

    @Override
    public User updateUser(Long id, String email, String password, Role role, String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException {
        return null;
    }

    @Override
    public String storeProfileImage(MultipartFile profileImage) throws IOException {
        String imagePath = null;
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = StringUtils.cleanPath(profileImage.getOriginalFilename());
            String currentDir = System.getProperty("user.dir");
            Path uploadDir = Paths.get(currentDir, "src", "main", "resources", "user-profiles");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            try (InputStream inputStream = profileImage.getInputStream()) {
                Path filePath = uploadDir.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePath = filePath.toAbsolutePath().toString();
            } catch (IOException ex) {
                throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
        return imagePath;
    }
    public User createUser(String email, String password, Role role, String firstname, String lastname, MultipartFile profileImage) throws IOException {
        User user = new User(email, password, role, firstname, lastname);
        String profileImagePath = storeProfileImage(profileImage);
        user.setProfileImagePath(profileImagePath);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
@Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
@Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByToken(@NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        return userRepository.findByEmail(userEmail).get();
    }
    @Override
    public User updateUserByToken(@NonNull HttpServletRequest request, String email, String password,String firstname, String lastname, String address, String phone, MultipartFile profileImage) throws IOException {
        User user = getUserByToken(request);
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password);

        }

        if (firstname != null) {
            user.setFirstname(firstname);
        }
        if (lastname != null) {
            user.setLastname(lastname);
        }



        String profileImagePath = storeProfileImage(profileImage);
        if (profileImagePath != null) {
            user.setProfileImagePath(profileImagePath);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}


