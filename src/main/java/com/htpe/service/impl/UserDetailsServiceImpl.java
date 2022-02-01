package com.htpe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrAccount;
import com.htpe.mapper.nnew.CsrAccountMapper;



@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CsrAccountMapper csrAccountMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	   	
    	 CsrAccount csrAccount = csrAccountMapper.getAccountByUserno(username);
       	   
        if(csrAccount == null) {
            throw new UsernameNotFoundException("用戶不存在");
        }
        
        String systemAuth = csrAccount.getSystemprivilege();    
    	
        List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(systemAuth);	//封裝權限
       		
        return new User(csrAccount.getUserno(),
			new BCryptPasswordEncoder().encode(csrAccount.getUserpwd()),auths);

    }
}