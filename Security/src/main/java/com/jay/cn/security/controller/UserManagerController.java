package com.jay.cn.security.controller;

import com.jay.cn.security.model.bean.ResultBean;
import com.jay.cn.security.model.bean.SysUser;
import com.jay.cn.security.model.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/userManager")
public class UserManagerController {

    Logger log = LoggerFactory.getLogger(UserManagerController.class);

    @Autowired
    SysUserServiceImpl sysUserService;

    @PostMapping("/add.do")
    @ResponseBody
    public ResultBean addUser(HttpServletRequest request, HttpServletResponse response){
        ResultBean resultBean = new ResultBean(ResultBean.SUCCESS_CODE,"成功");
        log.info("********** add.do start **************");
        try {
            String account = request.getParameter("account");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String status = request.getParameter("status");

            if (StringUtils.isEmpty(account) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                resultBean = new ResultBean(ResultBean.FAIL_CODE,"关键信息不能为空");
                return resultBean;
            }

            SysUser user = new SysUser();
            user.setAccount(account);
            user.setUserName(username);
            user.setPassword( new BCryptPasswordEncoder().encode(password));
            user.setEnabled(status.equals("1") ? true : false);

            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());

            log.info("user:"+user.toString());
            sysUserService.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            resultBean = new ResultBean(ResultBean.FAIL_CODE,"系统繁忙，请联系管理员");
        }
        log.info("********** add.do end **************");
        return  resultBean;
    }
}
