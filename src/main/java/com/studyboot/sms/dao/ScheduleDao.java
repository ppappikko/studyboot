package com.studyboot.sms.dao;

import java.util.List;
import java.util.Map;
import com.studyboot.sms.domain.Schedule;

public interface ScheduleDao {
  
  int insert(Schedule schedule);
  List<Object> findAll(int no);
  List<Schedule>findAllByAllStudy(Map<String, Object> studyNoList);
  Schedule findByNo(int no);
  int delete(int no);
  int update(Schedule schedule);
  int attend(Map<String, Object> attendMap);
  List<Schedule> attendTrueFalse(int scheduleNo);
  int allEventCount(int studyNo);
  int studyAttendCount(Map<String, Object> map);
}







