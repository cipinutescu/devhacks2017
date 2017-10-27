package com.devhacks.initial;

import com.devhacks.authentification.AuthentificationManager;
import com.devhacks.bean.AuthentificationBean;
import com.devhacks.model.Developer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Ciprian on 10/27/2017.
 */
@Controller
public class InitialContextController {

    @Inject
    private InitialContextProvider initialContextProvider;

    @Inject
    private AuthentificationManager authentificationManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getDevelopers", method = RequestMethod.GET)
    public  @ResponseBody
    List<Developer> getDevelopers(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "token") String token) {
        if(authentificationManager.validateAuthentification(username,token)){
            return initialContextProvider.generateRandomInitialContext();
        } else {
            return null;
        }
    }



}
