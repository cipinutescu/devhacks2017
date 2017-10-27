package com.devhacks.authentification;

import com.devhacks.bean.AuthentificationBean;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by Ciprian on 10/27/2017.
 */
@RestController
public class AuthentificationController {

    @Inject
    private AuthentificationManager authentificationManager;


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public  @ResponseBody AuthentificationBean login(@RequestParam(value = "username") String username,
                                            @RequestParam(value = "password") String password) {
        return authentificationManager.loginUser(username,password);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
    public  @ResponseBody void login(@RequestParam(value = "username") String username) {
        authentificationManager.logoutUser(username);
    }
}
