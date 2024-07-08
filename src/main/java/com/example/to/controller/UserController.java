package com.example.to.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.to.entity.User;
import com.example.to.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProc(@ModelAttribute User user, Model model, HttpSession session) {
        int result = userService.login(user.getUid(), user.getPwd());
        switch (result) {
            case UserService.CORRECT_LOGIN:
                User userSession = userService.getUserByUid(user.getUid());
                session.setAttribute("user", userSession);
                session.setMaxInactiveInterval(3600);
                model.addAttribute("message", "로그인에 성공했습니다.");
                return "redirect:/cart/list";
                
            case UserService.USER_NOT_EXIST:
                model.addAttribute("error", "사용자가 존재하지 않습니다.");
                return "login";
                
            case UserService.WRONG_PASSWORD:
                model.addAttribute("error", "비밀번호가 틀렸습니다.");
                return "login";
                
            default:
                model.addAttribute("error", "알 수 없는 오류가 발생했습니다.");
                return "login";
        }
    }
}
