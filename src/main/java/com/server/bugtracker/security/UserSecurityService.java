package com.server.bugtracker.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.server.bugtracker.user.UserRepo;



import com.server.bugtracker.jwt.JwtUtil;
import com.server.bugtracker.user.User;
import com.server.bugtracker.jwt.*;


@Service
public class UserSecurityService implements UserDetailsService{
	
	 	@Autowired
	    private UserRepo userRepo;
	 	
	    @Autowired
	    private JwtUtil jwtUtil;
	 	

	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;    
	    
	    
	    
	    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
	        String userName = jwtRequest.getUsername();
	        String userPassword = jwtRequest.getPassword();
	        authenticate(userName, userPassword);

	        UserDetails userDetails = loadUserByUsername(userName);
	        String newGeneratedToken = jwtUtil.generateToken(userDetails);

	        User user = userRepo.findByusername(userName);
	        return new JwtResponse(user.getUser_name(), newGeneratedToken);
	    }

		@Override
		public UserDetails loadUserByUsername(String username) {
		 User user = userRepo.findByusername(username);
		  if (user != null) {
	        return new org.springframework.security.core.userdetails.User(
	                    user.getUser_name(),
	                    user.getPassword(),
	                    getAuthority(user)
	            );
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }

		}
		
		private Set<SimpleGrantedAuthority> getAuthority(User user) {
		        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));		     
		        return authorities;
		    }
		  
		private void authenticate(String userName, String userPassword) throws Exception {
		        try {
		            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		        } catch (DisabledException e) {
		            throw new Exception("USER_DISABLED", e);
		        } catch (BadCredentialsException e) {
		            throw new Exception("INVALID_CREDENTIALS", e);
		        }
		    }
	/**
	 * Check if username already exist in the database, if it does...then it returns a conflic status, otherwise user gets saved to repo.
	 * @param user
	 * @return 209 status code (Created) if successful
	 * @return 409 status code (Conflict) if unsuccessful (username already exists)
	 */
	public ResponseEntity<String> registerNewUser(User user) {
				if(userRepo.existsByusername(user.getUser_name())) {					
					return new ResponseEntity<String>(user.getUser_name() + " already exist in the database", HttpStatus.CONFLICT);
				}
				else {
					user.setPassword(passwordEncoder.encode(user.getPassword()));
					userRepo.save(user);
					return new ResponseEntity<String>(user.getUser_name() + " has succesfully registered", HttpStatus.CREATED);
				}
			  
		    }

}
