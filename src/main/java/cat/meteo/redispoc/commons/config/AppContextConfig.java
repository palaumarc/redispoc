/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuració programàtica de context que es fa servir a tota l'aplicació
 *
 * @author mpe
 */
@Configuration
@PropertySource("classpath:Global.properties")
public class AppContextConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate< String, Object> redisTemplate() {
        final RedisTemplate< String, Object> template = new RedisTemplate< String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //Problema con acentos solucionado con StringRedisSerializer
        template.setHashValueSerializer(new GenericToStringSerializer< Object>(Object.class));
        template.setValueSerializer(new GenericToStringSerializer< Object>(Object.class));
        return template;
    }

    /**
     * Permet accedir a les propietats definides a l'arxiu indicat amb
     * l'anotació @PropertySource (en aquest mateix arxiu part superior) des de
     * qualsevol punt del codi, fent servir @Value (incloent els TESTS)
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

