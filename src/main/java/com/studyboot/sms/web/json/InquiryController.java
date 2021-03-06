package com.studyboot.sms.web.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyboot.sms.domain.Inquiry;
import com.studyboot.sms.service.InquiryService;
import com.studyboot.sms.service.MemberService;


// AJAX 기반 JSON 데이터를 다루는 컨트롤러
@RestController("json/InquiryController")
@RequestMapping("/json/inquiry")
public class InquiryController {

  @Autowired InquiryService inquiryService;
  @Autowired MemberService memberService;

  @PostMapping("add")
  public Object add(Inquiry inquiry) {
    HashMap<String,Object> content = new HashMap<>();
    
    try {
      inquiryService.add(inquiry);
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
      if (inquiryService.delete(no) == 0) 
        throw new RuntimeException("해당 번호의 게시물이 없습니다.");
      content.put("status", "success");

    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }

  @GetMapping("detail")
  public Object detail(@RequestParam int no) {
    
    Inquiry inquiry = inquiryService.get(no);
    
    return inquiry;
  }


  @GetMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      @RequestParam String pageCls,
      @RequestParam String keyword) {

    HashMap<String,Object> content = new HashMap<>();

    int clsNo = 0;
    switch(pageCls){
      case "문의": clsNo = 1; break;
      case "신고": clsNo = 2; break;
      case "undefined": clsNo = 0;
    }

    List<Inquiry> inquirys;
    List<Integer> memberNos = null;

    if(!keyword.equals("undefined")) {
      memberNos = memberService.findMemberNoByKeyword(keyword);
      
      if (memberNos.size() == 0) {
        content.put("pageNo", 0);
        return content;
      }
    } 
    
    if (pageSize < 3 || pageSize > 8) 
      pageSize = 3;
    

    int rowCount = inquiryService.size(clsNo, memberNos);
    
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


    inquirys = inquiryService.list(pageNo, pageSize, clsNo, memberNos);
    

    content.put("list", inquirys);
    content.put("pageNo", pageNo);
    content.put("pageSize", pageSize);
    content.put("totalPage", totalPage);

    return content;
  }

}










