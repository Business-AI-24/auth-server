package com.sparta.auth.filter;

import com.sparta.auth.core.UserRepository;
import com.sparta.auth.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public UserDetailsServiceImpl(UserRepository userRepository,
        RedisTemplate<String, String> redisTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Cacheable(cacheNames = "userDetailsCache", key = "args[0]")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username : " + username);
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(user);
    }


}
