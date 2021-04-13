package com.springboot.jpa.cmm.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmmNTCBatchScheduleService {

    @Autowired
    private CmmNTCBatchScheduleMapper cmmNTCBatchScheduleMapper;

    public void updateNTCBatchScheduleForExecTime(CmmNTCBatchScheduleVO vo) {
        cmmNTCBatchScheduleMapper.updateNTCBatchScheduleForExecTime(vo);
    }

    public List<CmmNTCBatchScheduleVO> selectAllNTCBatchSchedule() {
       return cmmNTCBatchScheduleMapper.selectAllNTCBatchSchedule();
    }
}
