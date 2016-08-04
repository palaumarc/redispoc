/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author marc
 */

@Controller
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")
public class MainController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") String userId) {

        return userService.getUserById(Integer.parseInt(userId));
    }
}
