/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

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
    
    public User getUserById(int userId) {
        
        return userDao.getUserById(userId);
    }
}
