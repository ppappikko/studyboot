package com.studyboot.sms.web.json;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.studyboot.sms.domain.Member;
import com.studyboot.sms.domain.Message;
import com.studyboot.sms.service.MemberService;
import com.studyboot.sms.service.MessageService;


// AJAX 기반 JSON 데이터를 다루는 컨트롤러
@RestController("json/MessageController")
@RequestMapping("/json/message")
public class MessageController {

  @Autowired MessageService messageService;
  @Autowired MemberService memberService;

  @PostMapping("add")
  public Object add(Message message, Member member,
                    HttpSession session) {

    // 로그인한 유저의 정보를 담는다.
    Member loginUser = (Member) session.getAttribute("loginUser"); 
    int loginNo = loginUser.getNo();
    
    HashMap<String,Object> content = new HashMap<>();

    String nickName = member.getNickName();
    int memberNo = memberService.findByNickName(nickName);
    message.setRecvNo(memberNo);
    message.setSendNo(loginNo);

    try {
      messageService.add(message);
      content.put("status", "success");
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }

    return content;
  }

  @GetMapping("delete")
  public Object delete(int no) {

    HashMap<String,Object> content = new HashMap<>();
    try {
      if (messageService.delete(no) == 0) 
        throw new RuntimeException("해당 번호의 게시물이 없습니다.");
      content.put("status", "success");

    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }

  @GetMapping("delete2")
  public Object delete2(int[] list) {

    HashMap<String,Object> content = new HashMap<>();
    try {
      
      if (list.length < 1 || list == null) {
        throw new RuntimeException("삭제할 쪽지가 없습니다.");
      }
      for (int i = 0; i < list.length; i++) {
        messageService.delete(list[i]);
      }
      content.put("status", "success");
      
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }


  @GetMapping("detail")
  public Object detail(@RequestParam int no) {

    Message message = messageService.get(no);

    return message;
  }


  @GetMapping("receiveList")
  public Object receiveList(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      @RequestParam String keyword,
      HttpSession session) {
    
    // 로그인한 유저의 정보를 담는다.
    Member loginUser = (Member) session.getAttribute("loginUser");
    HashMap<String,Object> content = new HashMap<>();
    
    if (loginUser != null) {
    
      int loginNo = loginUser.getNo();
      List<Message> message;
      List<Integer> memberNos = null;
  
      if(!keyword.equals("undefined") && !keyword.equals("")) {
        memberNos = memberService.findMemberNoMsg(keyword);
  
        if (memberNos.size() == 0) {
          content.put("pageNo", 0);
          return content;
        }
      }
  
      if (pageSize < 3 || pageSize > 8) 
        pageSize = 3;
  
      int rowCount = messageService.size(memberNos, loginNo); 
  
      if (rowCount == 0) {
        content.put("pageNo", 0);
        return content;
      }
  
      int totalPage = rowCount / pageSize;
  
      if (rowCount % pageSize > 0)
        totalPage++;
  
      if (pageNo < 1) 
        pageNo = 1;
      else if (pageNo > totalPage)
        pageNo = totalPage;
  
      message = messageService.list(pageNo, pageSize, memberNos, loginNo);
  
      content.put("status", "success");
      content.put("list", message);
      content.put("pageNo", pageNo);
      content.put("pageSize", pageSize);
      content.put("totalPage", totalPage);
      
    } else {
      content.put("status", "fail");
    }

    return content;
  }

  @GetMapping("sendList")
  public Object sendList(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      @RequestParam String keyword,
      HttpSession session
      ) {

    // 로그인한 유저의 정보를 담는다.
    Member loginUser = (Member) session.getAttribute("loginUser"); 
    HashMap<String,Object> content = new HashMap<>();
    
    if (loginUser != null) {
      
      int loginNo = loginUser.getNo();
      List<Message> message;
      List<Integer> memberNos = null;
  
      if(!keyword.equals("undefined") && !keyword.equals("")) {
        memberNos = memberService.findMemberNoMsg(keyword);
  
        if (memberNos.size() == 0) {
          content.put("pageNo", 0);
          return content;
        }
      }
  
      if (pageSize < 3 || pageSize > 8) 
        pageSize = 3;
  
      int rowCount = messageService.size2(memberNos, loginNo); 
  
      if (rowCount == 0) {
        content.put("pageNo", 0);
        return content;
      }
  
      int totalPage = rowCount / pageSize;
  
      if (rowCount % pageSize > 0)
        totalPage++;
  
      if (pageNo < 1) 
        pageNo = 1;
      else if (pageNo > totalPage)
        pageNo = totalPage;
  
      message = messageService.list2(pageNo, pageSize, memberNos, loginNo);
  
      content.put("status", "success");
      content.put("list", message);
      content.put("pageNo", pageNo);
      content.put("pageSize", pageSize);
      content.put("totalPage", totalPage);

    } else {
      content.put("status", "fail");
    }

    return content;
  }

}










