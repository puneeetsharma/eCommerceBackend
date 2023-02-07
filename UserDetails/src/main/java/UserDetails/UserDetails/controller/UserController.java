package UserDetails.UserDetails.controller;

import UserDetails.UserDetails.dto.UserDto;
import UserDetails.UserDetails.entity.User;
import UserDetails.UserDetails.repository.UserDetailsRepository;
import UserDetails.UserDetails.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(value="/insertOrUpdate")
    public ResponseEntity<User> addNeWUser(@RequestBody UserDto userDto)
    {
        User user = new User();

        System.out.println("EmployeeDTo" + userDto);
        BeanUtils.copyProperties(userDto,user);

//        EmployeeService newEmployee = new EmployeeService();

        User user1 = userService.insertOrUpdate(user);


        return new ResponseEntity<User>(user1,HttpStatus.CREATED);
    }


    @PostMapping(value="/addUser/{userId}/{userPassword}/{userName}")
    public ResponseEntity<User> addUser(@PathVariable (value = "userId") String userId,@PathVariable (value = "userPassword") String userPassword,
                                        @PathVariable (value = "userName") String userName)
    {
        UserDto userDto = new UserDto();
        User user = new User();
        userDto.setUserId(userId);
        userDto.setUserName(userName);
        userDto.setPassword(userPassword);
        BeanUtils.copyProperties(userDto,user);
        User user1 = userService.insertOrUpdate(user);
        return new ResponseEntity<User>(user1,HttpStatus.CREATED);
    }

    @GetMapping("/existById/{id}")
    public ResponseEntity<Boolean> existByIdId(@PathVariable ("id") String id)
    {
        boolean employee = userService.existsById(id);

        return new ResponseEntity<Boolean>(employee,HttpStatus.OK);
    }

    @GetMapping(path="/userlogin" ,produces = "application/json")
    public ResponseEntity<Boolean> userlogin(@RequestParam String email, String password){



        Boolean isTrue = userService.login(email,password);
        if(isTrue){

            return new ResponseEntity<Boolean>(isTrue,HttpStatus.OK);

        }else {
            return new ResponseEntity<Boolean>(isTrue ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getItemByUserId/{userId}")
    public Optional<User> getItemByUserId(@PathVariable(value = "userId") String userId)
    {
        return userService.findByUserId(userId);
    }

    @GetMapping(value = "/findByEmailId/{userEmail}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable(value = "userEmail") String userEmail)
    {
        return new ResponseEntity<>(userService.findByUserEmail(userEmail),HttpStatus.OK);
    }


    @PostMapping(path="/usersignup" ,produces = "application/json")
    public ResponseEntity<Boolean> usersignup(@RequestBody User user){


        Boolean isTrue = userService.signup(user);
        if(isTrue){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<Boolean>(isTrue , HttpStatus.BAD_GATEWAY);
        }

    }



}
