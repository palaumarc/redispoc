/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.api;

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
    
    public User getUserById(int userId) {
        
        String redisKey = getRedisKey(userId);
        
        if (!template.hasKey(redisKey)) {
            throw new IllegalArgumentException("User id not found");
        }
            
        String name = (String) template.opsForHash().get(redisKey, "name");
        String age = (String) template.opsForHash().get(redisKey, "age");

        return new User(userId, name, Integer.parseInt(age));
    }
    
    private String getRedisKey(int userId) {
        return "user:" + Integer.toString(userId);
    }
    
}
