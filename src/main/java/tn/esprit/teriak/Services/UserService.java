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
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
         this.jwtService = jwtService;
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
    public String storeProfileImage(MultipartFile profileImage) throws IOException {
        String imagePath = null;

        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = StringUtils.cleanPath(profileImage.getOriginalFilename());

            // Save image to Spring Boot project directory
            String currentDir = System.getProperty("user.dir");
            Path uploadDir = Paths.get(currentDir, "src", "main", "resources", "user-profiles");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            try (InputStream inputStream = profileImage.getInputStream()) {
                Path springFilePath = uploadDir.resolve(fileName);
                Files.copy(inputStream, springFilePath, StandardCopyOption.REPLACE_EXISTING);
                //String springImagePath = springFilePath.toAbsolutePath().toString();
                //imagePaths.add(springImagePath);
            } catch (IOException ex) {
                throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
            }

            // Save image to Angular project directory
            Path angularUploadDir = Paths.get("C:","Users","user","OneDrive","Bureau","template", "src", "assets", "profileimg");

            if (!Files.exists(angularUploadDir)) {
                Files.createDirectories(angularUploadDir);
            }
            try (InputStream inputStream2 = Files.newInputStream(uploadDir.resolve(fileName))) {
                Path angularFilePath = angularUploadDir.resolve(fileName);
                Files.copy(inputStream2, angularFilePath, StandardCopyOption.REPLACE_EXISTING);
                String angularImagePath = "assets/profileimg/" + fileName;
                imagePath = angularImagePath;
            } catch (IOException ex) {
                throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
            }

        }

        return imagePath;
    }
    @Override
    public User createUser(String email, String password, Role role, String firstname, String lastname, MultipartFile profileImage, String remise) throws IOException {
        User user = new User(email, password, role, firstname, lastname);

        if (remise != null && !remise.isEmpty()) {
            user.setRemise(remise);
        } else {
            user.setRemise("0");
        }
        String profileImagePath = storeProfileImage(profileImage);
        user.setProfileImagePath(profileImagePath);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, Optional<String> email, Optional<String> password,Optional<Role> role, Optional<String> firstname, Optional<String> lastname, Optional<MultipartFile> profileImage,Optional<String> remise ) throws IOException {
        User user =getUserById(id).get();
        if (email.isPresent()) {
            user.setEmail(email.get());
        }
        if (password.isPresent()) {
            user.setPassword(passwordEncoder.encode(password.get()));
        }
        if (remise.isPresent()) {
            user.setRemise(remise.get());
        }
        if (role .isPresent()) {
            user.setRole(role.get());
        }
        if (firstname .isPresent()) {
            user.setFirstname(firstname.get());
        }

        if (lastname.isPresent()) {
            user.setLastname(lastname.get());
        }


        if(profileImage.isPresent()) {
            String profileImagePath = storeProfileImage(profileImage.get());
            if (profileImagePath != null) {
                user.setProfileImagePath(profileImagePath);
            }



        }
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
    public User updateUserByToken(@NonNull HttpServletRequest request, Optional<String> email, Optional<String> password,Optional<String> firstname, Optional<String> lastname, Optional<MultipartFile> profileImage) throws IOException {
        User user = getUserByToken(request);
        if (email.isPresent()) {
            user.setEmail(email.get());
        }
        if (password.isPresent()) {
            user.setPassword(passwordEncoder.encode(password.get()));


        }

        if (firstname .isPresent()) {
            user.setFirstname(firstname.get());
        }

        if (lastname.isPresent()) {
            user.setLastname(lastname.get());
        }


        if(profileImage.isPresent()) {
            String profileImagePath = storeProfileImage(profileImage.get());
            if (profileImagePath != null) {
                user.setProfileImagePath(profileImagePath);
            }
        }
        return userRepository.save(user);
    }

}


