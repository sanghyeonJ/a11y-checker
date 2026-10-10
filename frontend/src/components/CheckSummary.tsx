import { useEffect, useRef } from "react";
import type { CheckResponse, ItemStatus } from "../types/check";
import { STATUS_LABEL } from "../contents/labels";

type CheckSummaryProps = {
  result: CheckResponse;
}

/** 상태별 왼쪽 테두리 색 (색만으로 구분하지 않도록 라벨 글자와 함께 사용) */
const COUNT_BORDER: Record<ItemStatus, string> = {
  FAIL: 'border-red-600',
  WARNING: 'border-amber-500',
  PASS: 'border-green-600',
  MANUAL: 'border-gray-500'
}

function CheckSummary ({ result }: CheckSummaryProps) {
  const { url, checkedAt, passed, summary } = result;
  const headingRef = useRef<HTMLHeadingElement>(null);

  // 결과가 나타나면 제목으로 포커스 이동 (스크린리더가 결과를 바로 읽도록)
  useEffect(() => {
    headingRef.current?.focus();
  }, []);

  // 문제가 있는 상태부터 보여주는 순서
  const counts: { status: ItemStatus; count: number }[] = [
    { status: 'FAIL', count: summary.fail },
    { status: 'WARNING', count: summary.warning },
    { status: 'PASS', count: summary.pass },
    { status: 'MANUAL', count: summary.manual }
  ];

  return (
    <section aria-labelledby="summary-title" className="mt-8">
      <h2 id="summary-title" ref={headingRef} tabIndex={-1} className="flex items-center gap-3 text-2xl font-bold">
        검사 결과
        <span
          className={`rounded px-3 py-1 text-base text-white ${passed ? 'bg-green-700' : 'bg-red-700'}`}
        >
          {passed ? STATUS_LABEL.PASS : STATUS_LABEL.FAIL}
        </span>
      </h2>

      <p>
        {passed ? 
          '자동 검사 항목에서 실패가 없습니다.' : 
          `실패 항목이 ${summary.fail}개 있습니다.`}
        {summary.manual > 0 && ` 수동 확인 항목 ${summary.manual}개는 직접 확인이 필요합니다.`}
      </p>

      <dl className="mt-4 grid grid-cols-[auto_1fr] gap-x-4 gap-y-1 text-sm">
        <dt className="font-semibold text-gray-600">검사 주소</dt>
        <dd className="break-all">{ url }</dd>
        <dt className="font-semibold text-gray-600">검사 시각</dt>
        <dd>
          <time dateTime={checkedAt}>{checkedAt.replace('T', ' ')}</time>
        </dd>
      </dl>

      <ul className="mt-6 grid grid-cols-2 gap-3 sm:grid-cols-4">
        {counts.map(({ status, count }) => (
          <li key={ status } className={`rounded border border-l-8 bg-white p-4 ${COUNT_BORDER[status]}`}>
            <span className="block text-sm font-semibold text-gray-700">{ STATUS_LABEL[status] }</span>
            <span className="text-2xl font-bold">{ count }개</span>
          </li>
        ))}
      </ul>
    </section>
  );

}

export default CheckSummary;