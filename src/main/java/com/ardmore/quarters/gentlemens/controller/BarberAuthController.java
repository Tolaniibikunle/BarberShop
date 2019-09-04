package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import org.springframework.web.bind.annotation.RestController;

@RestController("/barber/auth")
@Swaggerize
public class BarberAuthController {

//    private BarberGoogleAuthentication authentication;
//    private CommonGoogleAuthentication commonGoogleAuthentication;
//
//    public BarberAuthController(BarberGoogleAuthentication barberGoogleAuthentication, CommonGoogleAuthentication commonGoogleAuthentication) {
//        this.authentication = barberGoogleAuthentication;
//        this.commonGoogleAuthentication = commonGoogleAuthentication;
//    }
//
//    @PostMapping("/createNewBarber")
//    public ResponseEntity<?> createNewBarber(@RequestBody Barber barber) {
//        try {
//            UserRecord userRecord = this.authentication.createBarberWithEmailAndPassword(barber);
//            return new ResponseEntity<>(userRecord, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/updateBarber")
//    public ResponseEntity<?> updateUser(@RequestBody GoogleUserDTO googleUserDTO) {
//        try {
//            UserRecord userRecord = this.commonGoogleAuthentication.updateUser(googleUserDTO);
//            return new ResponseEntity<>(userRecord, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/deleteBarber/{uid}")
//    public ResponseEntity<?> deleteUser(@PathVariable("uid") String uid) {
//        try {
//            boolean status = this.commonGoogleAuthentication.deleteUser(uid);
//            return new ResponseEntity<>(status, HttpStatus.OK);
//        } catch (FirebaseAuthException e) {
//            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
//        }
//    }

}
