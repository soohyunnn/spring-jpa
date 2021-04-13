package com.springboot.jpa.cmm.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ntc0002BatchService")
public class NTC0002BatchServiceImpl extends CmmNTCBatchTask{

    private static final Logger log = LoggerFactory.getLogger(NTC0002BatchServiceImpl.class);
    private static final String BATCH_ID = "NTC0002";

    String URL ="https://enotice.ksd.or.kr/ntc/eni/NTC_ENTC20100V";

    public NTC0002BatchServiceImpl() {
        super(BATCH_ID);

    }

    @Override
    public CmmNTCBatchExecResultType check(CmmNTCBatchScheduleVO scheduleVO) throws Exception {
        return null;
    }

    @Override
    public CmmNTCBatchExecResultType execute(CmmNTCBatchVo vo) throws Exception {
        return null;
    }
}
