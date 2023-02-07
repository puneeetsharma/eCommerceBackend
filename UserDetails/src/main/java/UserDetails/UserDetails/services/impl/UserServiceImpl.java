package UserDetails.UserDetails.services.impl;

import UserDetails.UserDetails.entity.User;
import UserDetails.UserDetails.repository.UserDetailsRepository;
import UserDetails.UserDetails.services.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

//import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.header;
//import static com.sun.xml.internal.messaging.saaj.util.Base64.base64;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;



    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";




    private Mac sha256Hmac;

    @PostConstruct
    public void hmacSha256() {
        try {
            String secret = "confidential-key";
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
            sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);
            System.out.println("sha256Hmac: "+sha256Hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            ex.printStackTrace();
        }
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


    @Override
    public User insertOrUpdate(User user) {
        if(user.getUserId()==null)
        {
            //user.setUserId((UUID.randomUUID().toString());
            return userDetailsRepository.insert(user);
        }
        else
        {
            return userDetailsRepository.save(user);
        }
    }

    @Override
    public boolean existsById(String id) {
        return userDetailsRepository.existsById(id);
    }

    @Override
    public Optional<User> findByUserId(String productId) {
        return userDetailsRepository.findByuserId(productId);
    }

    @Override
    public Optional<User> findByUserEmail(String userEmail) {
        return userDetailsRepository.findByUserEmail(userEmail);
    }

    @Override
    public boolean login(String userEmail, String password) {

//        List<UserEntity> userEntities = userRepository.findByEmail(email);
//        if (userEntities.size() != 0) {
//            if (userEntities.get(4).getPassword().equals(password)) {
//                return true;
//            }
//        }
//        Iterable<User> userEntities=userDetailsRepository.findAll();
//        for(User userEntity:userEntities){
//            if (userEntity.getPassword().equals(password) && userEntity.getUserId().equals(userId)) {
//                byte[] signedBytes = sha256Hmac.doFinal(userEntity.getUserId().getBytes(StandardCharsets.UTF_8));
//                String token = encode(signedBytes);
//                System.out.println("token: "+ token);
//
//
//                JSONObject payload = new JSONObject();
//                payload.put("userId", userId);
//                payload.put("password", password);
//                //payload.put("exp", expires);


                //String signature = hmacSha256(Base64(header) + "." + base64(payload), token);
                //String jwtToken = base64(header) + "." + base64(payload) + "." + signature;

                //signature = hmacSha256(encodedHeader + "." + encode(payload));
        Optional<User> user=userDetailsRepository.findByUserEmail(userEmail);
        if(user.isPresent()){
            User user1 =new User();
            user1=user.get();
            if(user1.getUserEmail().equals(userEmail) && user1.getPassword().equals(password))

                return true;

            }
        return false;
    }


    @Override
    public boolean signup(User user) {


        User userEntity = new User();
        userEntity.setUserName(user.getUserName());
        userEntity.setUserId(user.getUserId());
        userEntity.setPassword(user.getPassword());
        userEntity.setUserEmail(user.getUserEmail());

        Optional<User> userEntities = userDetailsRepository.findById(user.getUserId());
        //List<Optional<User>> users = new ArrayList<>();
        //users.add(userEntities);
        //List<User> userEntities1 = userDetailsRepository.findById(user.getUserName());
        if (userEntities.isPresent()==false) {

                userDetailsRepository.save(userEntity);
                return true;
        }
        return false;
    }

    @Override
    public User addUser(User user) {
        return userDetailsRepository.save(user);
    }

}
