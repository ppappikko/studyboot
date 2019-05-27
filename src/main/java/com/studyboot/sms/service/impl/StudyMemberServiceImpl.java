package com.studyboot.sms.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.studyboot.sms.dao.StudyDao;
import com.studyboot.sms.dao.StudyMemberDao;
import com.studyboot.sms.domain.Study;
import com.studyboot.sms.service.StudyMemberService;

@Service
public class StudyMemberServiceImpl implements StudyMemberService {

  StudyMemberDao studyMemberDao;
  StudyDao studyDao;

  public StudyMemberServiceImpl(StudyMemberDao studyMemberDao,
      StudyDao studyDao) {
    this.studyMemberDao = studyMemberDao;
    this.studyDao = studyDao;
  }

  @Override
  public List<Study> findMyStudyList(int no) {
    
    List<Integer> list = studyMemberDao.findStudyNoByMemberNo(no);
    
    if(list.size() != 0) {
      return studyDao.findByNos(list);
    } else {
      return null;
    }
  }

  
  
  
}







