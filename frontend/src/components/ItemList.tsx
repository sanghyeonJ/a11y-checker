import type { ItemResult, ItemStatus, Severity } from '../types/check';
import { SEVERITY_LABEL, STATUS_LABEL } from '../constants/labels';

type ItemListProps = {
  items: ItemResult[];
};

/** 상태 배지 색 (글자색과 배경 대비를 맞춘 조합) */
const STATUS_BADGE: Record<ItemStatus, string> = {
  FAIL: 'bg-red-700 text-white',
  WARNING: 'bg-amber-300 text-gray-900',
  PASS: 'bg-green-700 text-white',
  MANUAL: 'bg-gray-600 text-white'
};

/** 이슈 심각도 글자색 + 왼쪽 테두리색 */
const SEVERITY_STYLE: Record<Severity, string> = {
  FAIL: 'border-red-600 text-red-700',
  WARNING: 'border-amber-500 text-amber-800'
}

function ItemList ({ items }: ItemListProps) {
  return (
    <section aria-labelledby="items-title" className="mt-10">
      <h2 id="items-title" className='text-xl font-bold'>
        항목별 결과
        <span className='ml-2 text-base font-normal text-gray-600'>(${items.length}개)</span>
      </h2>

      <ul className='mt-4 space-y-3'>
        {items.map((item) => (
          <li key={item.code} className='rounded border border-gray-300 bg-white p-4'>
            <h3 className='flex flex-wrap items-center gap-2 font-semibold'>
              <span className='text-gray-600'>{ item.code }</span>
              <span>{ item.title }</span>
              <span className={`rounded px-2 py-0.5 text-sm ${STATUS_BADGE[item.status]}`}>
                { STATUS_LABEL[item.status] }
              </span>
            </h3>

            {item.status === 'MANUAL' && (
              <p className='mt-2 text-sm text-gray-700'>
                자동으로 판단할 수 없는 항목입니다. 직접 확인해 주세요.
              </p>
            ) }

            {item.status === 'PASS' && (
              <p className='mt-2 text-sm text-gray-700'>
                발견된 문제가 없습니다.
              </p>
            ) }

            {item.issues.length > 0 && (
              <ul className='mt-3 space-y-3'>
                {item.issues.map((issue, index) => (
                  <li
                    key={`${issue.line}-${index}`}
                    className={`border-l-4 pl-3 ${SEVERITY_STYLE[issue.severity]}`}
                  >
                    <p className='text-sm font-semibold'>
                      [{SEVERITY_LABEL[issue.severity]}]
                      <a
                        href={`#line-${issue.line}`}
                        className="ml-2 font-normal text-blue-700 underline hover:text-blue-900 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
                      >
                        {issue.line}번째 줄 코드 보기
                      </a>
                    </p>
                    <p className='mt-1 text-gray-900'>{issue.message}</p>
                    <pre className="mt-2 whitespace-pre-wrap break-all rounded bg-gray-100 p-2 text-sm text-gray-900">
                      <code>{issue.snippet}</code>
                    </pre>
                  </li>
                ))}
              </ul>
            )}
          </li>
        ))}
      </ul>
    </section>
  );
}

export default ItemList;