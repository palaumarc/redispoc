/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
            System.out.println(key);
        }
        
        return users;
    }
    
    public User getUserById(int userId) {
        
        String redisKey = getRedisKey(userId);
        return this.getUserByRedisId(redisKey);
    }
    
    private String getRedisKey(int userId) {
        return "user:" + Integer.toString(userId);
    }
    
    private User getUserByRedisId(String redisKey) {
        
        if (!template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id not found");
        }
            
        String id = (String) template.opsForHash().get(redisKey, "id");
        String name = (String) template.opsForHash().get(redisKey, "name");
        String age = (String) template.opsForHash().get(redisKey, "age");
        
        return new User(Integer.parseInt(id), name, Integer.parseInt(age));
    }
}
