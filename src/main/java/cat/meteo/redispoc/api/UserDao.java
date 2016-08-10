/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marc
 */

@Repository
public class UserDao {
    
    @Autowired
    private RedisTemplate<String, Object> template;
    
    public List getAllUsers() {
        Set<String> userKeys = template.keys("user:*");
        List<User> users = new ArrayList();
        
        for (String key : userKeys) {
            users.add(getUserByRedisId(key));
        }
        
        return users;
    }
    
    public void addUser(User user) throws IllegalArgumentException {
        
        if (user == null) {
            throw new IllegalArgumentException("Trying to add a null user instance to the system");
        }
        
        String redisKey = getRedisKey(user.getId());
        
        if (template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id already exists");
        }
        
        Map<String, Object> userProperties = new HashMap();
        userProperties.put("id", user.getId());
        userProperties.put("name", user.getName());
        userProperties.put("age", user.getAge());
        
        template.opsForHash().putAll(redisKey, userProperties);
    }
    
    public void updateUser(User user) {
        
        if (user == null) {
            throw new IllegalArgumentException("Trying to modify a null user instance");
        }
        
        String redisKey = getRedisKey(user.getId());
        
        if (!template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id not found");
        }
        
        Map<String, Object> userProperties = new HashMap();
        userProperties.put("id", user.getId());
        userProperties.put("name", user.getName());
        userProperties.put("age", user.getAge());
        
        template.opsForHash().putAll(redisKey, userProperties);
    }
    
    public void deleteUser(UUID userId) {
        
        String redisKey = this.getRedisKey(userId);
        
        if (!template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id not found");
        }
        
        template.delete(redisKey);
    }
    
    public User getUserById(UUID userId) {
        
        String redisKey = getRedisKey(userId);
        return this.getUserByRedisId(redisKey);
    }
    
    private String getRedisKey(UUID userId) {
        return "user:" + userId.toString();
    }
    
    private User getUserByRedisId(String redisKey) {
        
        if (!template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id not found");
        }
            
        String id = (String) template.opsForHash().get(redisKey, "id");
        String name = (String) template.opsForHash().get(redisKey, "name");
        String age = (String) template.opsForHash().get(redisKey, "age");
        
        return new User(UUID.fromString(id), name, Integer.parseInt(age));
    }
}
