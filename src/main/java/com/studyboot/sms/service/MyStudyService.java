package com.studyboot.sms.service;

import java.util.List;
import com.studyboot.sms.domain.StudyBoard;

public interface MyStudyService {
  
  // 스터디 게시판 관련 service
  List<StudyBoard> list(int pageNo, int pageSize, 
      int clsNo, List<Integer> memberNos, String keyword);
  
  int size(int clsNo, List<Integer> memberNos, String keyword);
  // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//  Inquiry get(int no);
//  int delete(int no);
//  Study getStudy(int no);
//  int add(Inquiry inquiry);
}