// 백엔드 check/dto 패키지와 1:1로 맞춘 타입모음

/** 개별 이슈의 심각도 */
export type Severity = 'FAIL' | 'WARNING';

/** 검사항목의 최종상태 */
export type ItemStatus = 'PASS' | 'FAIL' | 'WARNING' | 'MANUAL';

/** 문제 요소 하나 */
export type Issue = {
  severity: Severity;
  line: number;
  message: string;
  snippet: string;
}

/** 검사항목 하나의 결과 */
export type ItemResult = {
  code: string;
  title: string;
  auto: boolean;
  status: ItemStatus;
  issues: Issue[];
}

/** 상태별 항목 개수 */
export type Summary = {
  pass: number;
  fail: number;
  warning: number;
  manual: number;
}

/** POST /api/check 성공 응답 */
export type CheckResponse = {
  url: string;
  checkedAt: string;
  passed: boolean;
  summary: Summary;
  items: ItemResult[];
  sourceLines: string[];
}

/** POST /api/check 요청 본문 */
export type CheckRequest = {
  url: string;
}

/** 400 에러 응답 (Spring ProblemDetail) */
export type ProblemDetail = {
  type: string;
  title: string;
  status: number;
  detail: string;
  instance: string;
}
