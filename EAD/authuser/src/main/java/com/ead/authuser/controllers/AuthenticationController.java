package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                                   @JsonView(UserDto.UserView.RegistrationPost.class)
                                                   @Validated(UserDto.UserView.RegistrationPost.class)
                                                   UserDto userDto){
        log.debug("POST registerUser userDto received {} ", userDto.toString());
        if (userService.existsByUsername(userDto.getUsername())){
            log.warn("Username {} is Already Taken!", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error Username is Already Taken!");
        }
        if (userService.existsByEmail(userDto.getEmail())){
            log.warn("Email {} is Already Taken!", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error Email is Already Taken!");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        log.debug("POST registerUser userModel saved {} ", userModel.toString());
        log.info("User saved successfully userId {} ", userModel.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
    //Comando para funcionar o log trace e debug
    //mvn spring-boot:run -Dspring-boot.run.arguments=--logging.level.com.ead=TRACE
    @GetMapping("/")
    public String index(){
        //Quando queremos ter rastreamento
        log.trace("TRACE");
        //Utilizado em nível de desenvolvimento. informações relevantes para desenvolvedores
        log.debug("DEBUG");
        //Info uteis e relevantes, mas mais quando obtem sucesso
        log.info("INFO");
        //Warning
        log.warn("WARN");
        //error, quando algo dá errado. Usado em try cats
        log.error("ERROR");
        try {
            throw new Exception("Exception message");
        }catch (Exception e){
            log.error("--------------ERROR-------------", e);
        }
        return "Logging Spring Boot";
    }
}
