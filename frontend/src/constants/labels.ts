import type { ItemStatus, Severity } from "../types/check";

/** 항목 상태 → 한글 라벨 */
export const STATUS_LABEL: Record<ItemStatus, string> = {
  PASS: '통과',
  FAIL: '실패',
  WARNING: '주의',
  MANUAL: '수동확인'
};

/** 이슈 심각도 → 한글 라벨 */
export const SEVERITY_LABEL: Record<Severity, string> = {
  FAIL: '실패',
  WARNING: '주의'
}