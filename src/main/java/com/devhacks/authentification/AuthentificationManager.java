package com.devhacks.authentification;

import com.devhacks.bean.AuthentificationBean;
import com.devhacks.dao.UserDAO;
import com.devhacks.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Service
public class AuthentificationManager {

    private Map<String,AuthentificationBean> authentificationBeanMap;

    @Inject
    private UserDAO userDAO;

    @PostConstruct
    public void postConstruct(){
        authentificationBeanMap = new HashMap<>();
        List<User> users = userDAO.getAllUsers();

        for(User user : users){
            authentificationBeanMap.put(user.getUsername(),null);
        }
    }

    public AuthentificationBean loginUser(String username, String password){
        User user = userDAO.getUserByUsername(username);
        if(user == null){
            return null;
        }
        if(user.getPassword().equals(password)){
            String token = UUID.randomUUID().toString();
            AuthentificationBean authentificationBean = new AuthentificationBean(token,username);
            authentificationBeanMap.put(username,authentificationBean);
            return authentificationBean;
        } else {
            return null;
        }
    }

    public void logoutUser(String username){
        authentificationBeanMap.put(username,null);
    }

    public boolean validateAuthentification(String username, String token){
        AuthentificationBean authentificationBean = authentificationBeanMap.get(username);

        if(authentificationBean == null){
            return false;
        } else {
            if(authentificationBean.getToken().equals(token)){
                return true;
            }
            else {
                return false;
            }
        }
    }

}
