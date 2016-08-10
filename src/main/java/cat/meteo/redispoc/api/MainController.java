/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

import cat.meteo.redispoc.commons.exception.HttpStatusMessage;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return userService.getUserById(userId);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HttpStatusMessage addUser(@RequestParam String name, @RequestParam int age) {
        userService.addUser(name, age);
        return new HttpStatusMessage(HttpStatus.CREATED.toString(), "", "User " + name + " added correctly");
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public HttpStatusMessage deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return new HttpStatusMessage(HttpStatus.OK.toString(), "", "User " + userId + " deleted correctly");
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    @ResponseBody
    public HttpStatusMessage updateUser(@PathVariable("id") String userId, @RequestParam String name, @RequestParam int age) 
    {
        userService.updateUser(userId, name, age);
        return new HttpStatusMessage(HttpStatus.OK.toString(), "", "User " + userId + " updated correctly");
    }
}
