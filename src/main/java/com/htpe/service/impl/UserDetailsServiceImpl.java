package com.htpe.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
import com.htpe.bean.CsrAccountAuth;
import com.htpe.mapper.nnew.CsrAccountMapper;



@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CsrAccountMapper csrAccountMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	   
    	String authority ="";
    	StringBuffer authorityBuffer = new StringBuffer(authority);
   	
    	CsrAccount csrAccount = csrAccountMapper.getAccountByUserno(username);
       	   
        if(csrAccount == null) {
            throw new UsernameNotFoundException("�Τᤣ�s�b");
        }
        
        List<CsrAccountAuth> csrAccountAuths = csrAccount.getCsrAccountAuths();
        
        for (CsrAccountAuth csrAccountAuth : csrAccountAuths) {      	
        	authorityBuffer.append(csrAccountAuth.getCsrAuth().getAuthno() + ", ");		//�����v��authA, authB, authC
		}
       
         authority = authorityBuffer.deleteCharAt(authorityBuffer.length()-2).toString();		//�h���̫�,
    	
        List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authority);	//�ʸ��v��

        return new User(csrAccount.getUserno(),
			new BCryptPasswordEncoder().encode(csrAccount.getUserpwd()),auths);

    }
}