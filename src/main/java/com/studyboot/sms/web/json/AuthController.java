package com.studyboot.sms.web.json;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.studyboot.sms.domain.Member;
import com.studyboot.sms.domain.Study;
import com.studyboot.sms.service.MemberService;
import com.studyboot.sms.service.StudyMemberService;

@RestController("json/AuthController")
@RequestMapping("/json/auth")
public class AuthController {

  final static Logger logger = LogManager.getLogger(AuthController.class);

  static final String REFERER_URL = "refererUrl";

  @Autowired MemberService memberService;
  @Autowired StudyMemberService studyMemberService;
  @Autowired ServletContext servletContext;

  @PostMapping("login")
  public Object login(
      String email,
      String password,
      HttpSession session,
      HttpServletResponse response) {

    Member member = memberService.get(email, password);

    HashMap<String,Object> content = new HashMap<>();
    if (member == null) {
      content.put("status", "fail");
      content.put("message", "이메일이 없거나 암호가 맞지 않습니다.");
    } else {
      session.setAttribute("loginUser", member);


      content.put("status", "success");
    }

    return content;
  }

  @GetMapping("logout")
  public Object logout(HttpSession session) throws Exception {

    logger.debug("세션 무효화시킴!");
    logger.debug("loginUser: " + session.getAttribute("loginUser"));
    session.invalidate();

    HashMap<String,Object> content = new HashMap<>();
    content.put("status", "success");

    return content;
  }

  @GetMapping("user")
  public Object user(HttpSession session) throws Exception {

    Member loginUser = (Member)session.getAttribute("loginUser");

    HashMap<String,Object> content = new HashMap<>();

    if (loginUser != null) {

      List<Study> myStudyList = studyMemberService.findMyStudyList(loginUser.getNo());
      
      if(myStudyList != null) {
        content.put("myStudyList", myStudyList);
      }
      content.put("status", "success");
      content.put("user", loginUser);
    } else {
      content.put("status", "fail");
    }
    return content;
  }

  @PostMapping("register")
  public Object register(HttpSession session,
      String email,
      String nickName,
      String name,
      String password
      ) throws Exception {
    HashMap<String,Object> content = new HashMap<>();

    Member member = new Member();

    try {
    //등록시 에러가 나면 알려준다
      member.setEmail(email);
      member.setNickName(nickName);
      member.setName(name);
      if(password != null) {
        if(!password.equals("undefined"))
        member.setPassword(password);
      }
      memberService.add(member);
      content.put("status", "success");
    }catch(Exception e) {
      content.put("status", "fail");
    }

    
    return content;
  }
  
  
  @PostMapping("sociallogin")
  public Object socialLogin(HttpSession session,
      String token,  String social
      ) throws Exception {
    HashMap<String,Object> content = new HashMap<>();

    Member member = null;
    
    //토큰으로 서버에 요청한다.
    if(social.equalsIgnoreCase("facebook")) {
      
      member = memberService.getFacebookMember(token);
      
    }else if(social.equalsIgnoreCase("naver")){
      
      member = memberService.getNaverMember(token);
      
    }
    
    
    if (member == null) {
      content.put("status", "fail");
    } else {
      session.setAttribute("loginUser", member);
      List<Study> myStudyList = studyMemberService.findMyStudyList(member.getNo());
      if(myStudyList != null) {
        session.setAttribute("myStudyList", myStudyList);
      }
      content.put("status", "success");
    }

    return content;
  }



}










