/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marc
 */

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public User getUserById(String userId) {
        return userDao.getUserById(UUID.fromString(userId));
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public void addUser(String name, int age) {
        
        if (name.isEmpty() || age < 0) {
            throw new IllegalArgumentException("User name must not be empty and age must be >= 0");
        }
        
        UUID id =  UUID.randomUUID();
        User newUser = new User(id, name, age);
        
        userDao.addUser(newUser);
    }
            
}
