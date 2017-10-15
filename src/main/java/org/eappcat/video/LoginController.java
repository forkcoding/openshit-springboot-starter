package org.eappcat.video;

import org.eappcat.dao.UserRepository;
import org.eappcat.entity.User;
import org.eappcat.model.LoginVO;
import org.eappcat.model.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuebo on 2017/10/15.
 */
@RestController
@RequestMapping("api")
public class LoginController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserRepository userRepository;
    public ResponseVO<LoginVO> login(@RequestBody LoginVO loginVO){
        ResponseVO<LoginVO> responseVO=new ResponseVO();
        if(userRepository.findByOpenid(loginVO.getOpenid())==null){
            User  user=new User();
            BeanUtils.copyProperties(loginVO,user);
            userRepository.save(user);


        }else{
            request.getSession().setAttribute("user",userRepository.findByOpenid(loginVO.getOpenid()));

        }


        return responseVO;
    }
}
