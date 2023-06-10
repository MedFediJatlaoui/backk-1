package tn.esprit.teriak.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.teriak.Entities.Role;
import tn.esprit.teriak.Entities.User;
import tn.esprit.teriak.Repositories.UserRepository;
import tn.esprit.teriak.Services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

//moo
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService,
                          UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/profile")
    public User getProfile(@NonNull HttpServletRequest request) {
        return userService.getUserByToken(request);
    }
    @PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<User> createUser(@RequestParam String email, @RequestParam String password, @RequestParam Role role, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,  @RequestParam(required = false,value="file") MultipartFile profileImage,@RequestParam(required = false) String remise) throws IOException {
        User createdUser = userService.createUser(email,password,role,firstname,lastname,profileImage,remise);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdUser.getId())
                                .toUri())
                .body(createdUser);
    }

    @PutMapping(value="/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestParam Optional<String> email, @RequestParam Optional<String> password, @RequestParam Optional<Role> role, @RequestParam Optional<String> firstname, @RequestParam Optional<String> lastname, @RequestParam(name = "file") Optional< MultipartFile> profileImage,@RequestParam Optional<String> remise) throws IOException {

        User updatedUser = userService.updateUser(id,email,password,role,firstname,lastname,profileImage,remise);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @Controller
    @RequestMapping("/admin/users")
    public class UserRegistrationController {

        @Autowired
        private UserRepository userRepository;

        @GetMapping("/new")
        public ModelAndView showRegistrationForm() {
            ModelAndView mav = new ModelAndView();
            mav.addObject("user", new User());
            mav.setViewName("add-user");
            return mav;
        }



        @PostMapping("/new")
        public String registerUser(@ModelAttribute("user") User user) {
            userRepository.save(user);
            return "redirect:/admin/users";
        }
    }
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.storeProfileImage(file);
    }

    @GetMapping("/find/{email}")
    public Optional<User> findByemail(@PathVariable("email") String email) throws IOException {
        return userRepository.findByEmail(email);

    }
    @PutMapping(value="/updateProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updateProfile(@NonNull HttpServletRequest request, @RequestParam Optional <String> email, @RequestParam Optional <String> password, @RequestParam Optional <String> firstname, @RequestParam Optional <String> lastname, @RequestParam Optional <MultipartFile> profileImage) throws IOException {
        return userService.updateUserByToken(request,email,password,firstname,lastname,profileImage);

    }
}

