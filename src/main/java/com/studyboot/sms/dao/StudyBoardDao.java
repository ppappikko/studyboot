package com.studyboot.sms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.studyboot.sms.domain.StudyBoard;

public interface StudyBoardDao {
  
  List<StudyBoard> findAll(Map<String,Object> params);
  int countByClsAndMember(HashMap<String, Object> params);
  
//  int insert(StudyMember studyMember);
  
  // 스터디 넘버로 스터디 구성원 가져오기
//  List<StudyMember> findStudyMembersByNo(int no);
  
//  StudyMember findByNo(int no);
  //int increaseCount(int no);
//  int update(StudyMember studyMember);
//  int delete(int no);
//  int countAll();
//  List<Integer> findStudyNoByMemberNo(int no);
}