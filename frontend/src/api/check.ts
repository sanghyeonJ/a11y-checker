import axios from 'axios';
import type { CheckRequest, CheckResponse, ProblemDetail } from '../types/check';

/** URL 검사 요청: 성공하면 CheckResponse를 돌려줌 */
export async function checkUrl (url: string): Promise<CheckResponse> {
  const body: CheckRequest = { url };
  const response = await axios.post<CheckResponse>('/api/check', body);
  return response.data;
}

/** 에러 객체에서 화면에 보여줄 메시지만 꺼냄 */
export function getErrorMessage (error: unknown): string {
  if (axios.isAxiosError<ProblemDetail>(error)) {
    // 400: 백엔드가 보낸 ProblemDetail의 detail 사용
    const detail = error.response?.data?.detail;
    if (detail) return detail;

    // 응답 자체가 없음: 서버에 연결 실패
    if (!error.response) return '서버에 연결할 수 없습니다. 잠시 후 다시 시도해 주세요.';

    // 그 외 상태 코드
    return `요청에 실패했습니다. (상태 코드 ${error.response.status})`
  }
  return "알 수 업는 오류가 발생했습니다.";
}