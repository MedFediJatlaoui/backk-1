package tn.esprit.teriak.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
//moo
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<User> createUser(@RequestParam String email, @RequestParam String password, @RequestParam Role role, @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,  @RequestParam(required = false,value="file") MultipartFile profileImage) throws IOException {
        User createdUser = userService.createUser(email,password,role,firstname,lastname,profileImage);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdUser.getId())
                                .toUri())
                .body(createdUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        User updatedUser = userService.updateUser(user);
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

}

