package com.springboot.jpa.cmm.batch;

public enum CmmNTCBatchCheckResultType {

    SUCCESS("체크 결과 성공"),
    FAIL("체크 결과 실패"),
    FAIL_AND_STOP("체크 결과 실패, 실행 중인 TASK 종료 요청");

    private CmmNTCBatchCheckResultType(String docs) {

    }
}
