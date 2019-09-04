package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth/user")
@Swaggerize
public class UserAuthController {

//    private UserGoogleAuthentication userGoogleAuthentication;
//    private CommonGoogleAuthentication commonGoogleAuthentication;
//
//    public UserAuthController(UserGoogleAuthentication userGoogleAuthentication, CommonGoogleAuthentication commonGoogleAuthentication) {
//        this.userGoogleAuthentication = userGoogleAuthentication;
//        this.commonGoogleAuthentication = commonGoogleAuthentication;
//    }
//
//    @PostMapping("/createNewUser")
//    public ResponseEntity<?> createNewUser(@RequestBody User user) {
//        try {
//            UserRecord userRecord = this.userGoogleAuthentication.createUserWithEmailAndPassword(user);
//            return new ResponseEntity<>(userRecord, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/updateUser")
//    public ResponseEntity<?> updateUser(@RequestBody GoogleUserDTO googleUserDTO) {
//        try {
//            UserRecord userRecord = this.commonGoogleAuthentication.updateUser(googleUserDTO);
//            return new ResponseEntity<>(userRecord, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/deleteUser/{uid}")
//    public ResponseEntity<?> deleteUser(@PathVariable("uid") String uid) {
//        try {
//            boolean status = this.commonGoogleAuthentication.deleteUser(uid);
//            return new ResponseEntity<>(status, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }



}
