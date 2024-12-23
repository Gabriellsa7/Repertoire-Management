    package com.academico.webproject.service;

    import com.academico.webproject.model.Band;
    import com.academico.webproject.model.User;
    import com.academico.webproject.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public User createUser(User user) {
            return userRepository.save(user);
        }

        public Optional<User> getUserById(String id) {
            return  userRepository.findById(id);
        }

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public void deleteUser(String id) {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new RuntimeException("Band not found with ID: " + id);
            }
        }

        public User updateUser(String id, User userDetails) {
            //if the user is found it goes to the map if not it goes to orElse.
            //with the set, change the current values to a new.
            return userRepository.findById(id).map(user -> {
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                user.setPassword(userDetails.getPassword());
                return userRepository.save(user);
            }).orElseThrow(() -> new RuntimeException("User not found"));
        }

        public boolean authenticateUser(String email, String password) {
            //Optional is intended to handle values that may or may not be present, helping to avoid NullPointerException
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return user.getPassword().equals(password);
            }
            return false;
        }
    }
