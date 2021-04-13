package com.springboot.jpa.cmm.batch;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmmNTCBatchScheduleMapper {

    public void updateNTCBatchScheduleForExecTime(CmmNTCBatchScheduleVO vo);

    List<CmmNTCBatchScheduleVO> selectAllNTCBatchSchedule();
}
