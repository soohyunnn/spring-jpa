package com.springboot.jpa.cmm.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.ui.context.Theme;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

public class CmmNTCBatchManager implements DisposableBean {

    Logger log = LoggerFactory.getLogger(CmmNTCBatchManager.class);

    @Autowired
    private ThreadPoolTaskScheduler pool;
    private ScheduledFuture<?> scheduledFuture;
    private ScheduledFuture<?> taskFuture;
    private boolean scheduleRunning = false;
    private long scheduleInterval = 1000;

    @Autowired
    private CmmNTCBatchScheduleService cmmNTCBatchScheduleService;

    @Autowired
    private CmmNTCBatchTask[] taskList;


    @EventListener
    public void refreshed(ContextRefreshedEvent event) {
        ApplicationContext ctx = event.getApplicationContext();
        if(ctx.getParent() != null) { //dispatcher-servlet context 초기화가 완료되었을 경우 실행되는 코드 블럭입니다.
            log.info("CmmNTCBatchManager.refreshed(): dispatcher-servlet context 초기화 완료");
            resetSchedule();
        }
    }

    private void resetSchedule() {
        if(scheduleRunning) {
            scheduledFuture.cancel(true);
            scheduleRunning = false;
        }
        if(scheduleInterval  > 0) {
            Runnable task = createScheduleThread();
            scheduledFuture = pool.scheduleWithFixedDelay(task, scheduleInterval);
            scheduleRunning = true;
        }
    }

    private Runnable createScheduleThread() {
        log.info("CmmNTCBatchManager.createScheduleThread()");
        Runnable task = new Thread() {
            public void run() {
                List<CmmNTCBatchScheduleVO> list = null;
                try {
                    list = cmmNTCBatchScheduleService.selectAllNTCBatchSchedule();
                } catch (Exception e) {
                    return;
                }
                Iterator<CmmNTCBatchScheduleVO> iter = list.iterator();
                for(CmmNTCBatchTask taskItem : taskList) {
                    CmmNTCBatchScheduleVO scheduleVO = null;
                    while(iter.hasNext()) {
                        CmmNTCBatchScheduleVO schedule = iter.next();
                        if(taskItem.getBatchId().equals(schedule.getBatchId())){
                            scheduleVO = schedule;
                            break;
                        }
                    }

                    if(scheduleVO == null){
                        continue;
                    }

                    try {
                        boolean isExecutable = false;
                        if(scheduleVO.getLastExcuDttm() == null) {  //최초 실행
                            isExecutable = true;
                        } else {
                            long lastExcuDttm = scheduleVO.getLastExcuDttm().getTime();
                            long pastTime = System.currentTimeMillis() - lastExcuDttm;
                            if(pastTime > scheduleVO.getIntervalMillis()) {
                                isExecutable = true;
                            }
                        }

                        if(!isExecutable) {
                            continue;
                        }

                        CmmNTCBatchCheckResultType resultType = taskItem.check(scheduleVO);
                        switch (resultType) {
                            case SUCCESS:
                                //task 중지
                                if(taskFuture != null){
                                    taskFuture.cancel(true);
                                    taskFuture = null;
                                }
                                log.info("배치 task 실행 taskinfo={} isExecutable={}", scheduleVO.toString(), isExecutable);

                                //task 실행
                                Runnable task = createTaskThread(taskItem);
                                log.info("task 호출 시작");
                                taskFuture = pool.scheduleAtFixedRate(task, scheduleVO.getIntervalMillis());
                                break;
                            case FAIL:
                                break;
                            case FAIL_AND_STOP:
                                //task중지
                                if(taskFuture != null) {
                                    taskFuture.cancel(true);
                                    taskFuture = null;
                                }
                                break;
                        }
                    }catch (Exception e){
                        log.error("CmmNTCBatchManager가 task의 실행 가능 여부를 체크 `{taskIntem.check(scheduleVO)}`하는 중 오류가 발생했습니다", e);

                    }
                }
            };
        };

        return task;
    }

    private Runnable createTaskThread(CmmNTCBatchTask taskItem) {
        Runnable task = new Thread() {
            public void run() {
                try {
                    CmmNTCBatchExecResultType result = taskItem.execute();
                    switch (result) {
                        case SUCCESS:
                            break;
                        case FAIL:
                            break;
                    }
                } catch (Exception e) {
                    log.error("CmmNTCBatchManager가 task를 실행 `{taskItem.execute()}`하는 중 오류가 발생했습니다.", e);
                }
            }
        };
        return task;
    }

    @Override
    public void destroy() throws Exception {
        if(scheduledFuture == null){
            return;
        }
        scheduledFuture.cancel(true);

        if(taskFuture == null) {
            return;
        }
        taskFuture.cancel(true);
    }
}
