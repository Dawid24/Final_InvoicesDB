package pl.paciorek.dawid.finalinvoicesdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.paciorek.dawid.finalinvoicesdb.model.Role;
import pl.paciorek.dawid.finalinvoicesdb.model.User;
import pl.paciorek.dawid.finalinvoicesdb.repository.RoleRepository;
import pl.paciorek.dawid.finalinvoicesdb.repository.UserRepository;
import pl.paciorek.dawid.finalinvoicesdb.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleRepository.findByRole("SITE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User getActiveUser(String name) {
        List<User> getUsers = userRepository.findAll();
        for (User u: getUsers) {
            if (u.getEmail().equals(name)) {
                return u;
            }
        } return null;
    }


    @Override
    public boolean isUserAlreadyPresent(User user) {
        return false;
    }
}
