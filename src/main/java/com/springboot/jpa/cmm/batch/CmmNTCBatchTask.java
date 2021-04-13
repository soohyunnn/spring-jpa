package com.springboot.jpa.cmm.batch;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class CmmNTCBatchTask {

    private String batchId;

    @Autowired
    private CmmNTCBatchScheduleService cmmNTCBatchScheduleService;

    public CmmNTCBatchTask(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId(){
        return batchId;
    }

    public abstract CmmNTCBatchExecResultType check(CmmNTCBatchScheduleVO scheduleVO) throws Exception;

    public CmmNTCBatchExecResultType execute() throws Exception {
        CmmNTCBatchScheduleVO vo = new CmmNTCBatchScheduleVO();
        vo.setBatchId(this.batchId);
        cmmNTCBatchScheduleService.updateNTCBatchScheduleForExecTime(vo);

        CmmNTCBatchVo paramVo = new CmmNTCBatchVo();
        paramVo.setStdDt("20210414");
        return execute(paramVo);

    }
    public abstract CmmNTCBatchExecResultType execute(CmmNTCBatchVo vo) throws Exception;
}
