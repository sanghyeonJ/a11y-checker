import type { Issue, ItemResult, Severity } from "../types/check";
import { SEVERITY_LABEL } from '../constants/labels';

type SourceViewProps = {
  items: ItemResult[];
  sourceLines: string[];
}

/** 줄에 표시할 이슈 하나 (어느 항목에서 나온 이슈인지 함께 보관) */
type LineIssue = {
  code: string;
  issue: Issue;
}

/** 문제 줄 배경 + 왼쪽 테두리 */
const LINE_STYLE: Record<Severity, string> = {
  FAIL: 'bg-red-50 border-red-600',
  WARNING: 'bg-amber-50 border-amber-500'
}

/** 라벨 글자색 */
const LABEL_TEXT: Record<Severity, string> = {
  FAIL: 'text-red-700',
  WARNING: 'text-amber-800'
}

/** 모든 항목의 이슈를 줄 번호별로 묶음 */
function groupIssuesByLine(items: ItemResult[]): Map<number, LineIssue[]> {
  const map = new Map<number, LineIssue[]>();
  for (const item of items) {
    for (const issue of item.issues) {
      const list = map.get(issue.line) ?? [];
      list.push({ code: item.code, issue });
      map.set(issue.line, list);
    }
  }
  return map;
}

function SourceView ({ items, sourceLines }: SourceViewProps) {
  const issuesByLine = groupIssuesByLine(items);

  return (
    <section aria-labelledby="source-title" className="mt-10">
      <h2 id="source-title" className="text-xl font-bold">
        HTML 전문
        <span className="ml-2 text-base font-normal text-gray-600">
          (문제가 있는 줄 {issuesByLine.size}개)
        </span>
      </h2>
      <p className="mt-1 text-sm text-gray-600">
        검사에 사용한 정리된 HTML 기준이라, 브라우저의 원본 소스와 줄 번호가 다를 수 있습니다.
      </p>

      <ol className="mt-4 rounded border border-gray-300 bg-white py-2 font-mono text-sm">
        {sourceLines.map((text, index) => {
          const lineNo = index + 1;
          const lineIssues = issuesByLine.get(lineNo);
          const severity: Severity | null = lineIssues ?
            lineIssues.some(({ issue }) => issue.severity === 'FAIL') ? 'FAIL' : 'WARNING' : 
            null;
          
          return (
            <li
              key={lineNo}
              id={`line-${lineNo}`}
              className={`scroll-mt-4 border-l-4 target:outline-2 target:outline-blue-600 ${
                severity ? LINE_STYLE[severity] : 'border-transparent'
              }`}
            >
              <div className="flex">
                <span className="w-12 shrink-0 select-none pr-3 text-right text-gray-500">{lineNo}</span>
                <code className="min-h-5 whitespace-pre-wrap break-all text-gray-900">{text}</code>
              </div>

              {lineIssues && (
                <ul className="mb-2 ml-12 mt-1 space-y-1 font-sans">
                  {lineIssues.map(({ code, issue }, i) => (
                    <li key={`${code}-${i}`}>
                      <span className={`font-semibold ${LABEL_TEXT[issue.severity]}`}>
                        [{SEVERITY_LABEL[issue.severity]}]
                      </span>
                      <span className="ml-2 text-gray-600">{code}</span>
                      <span className="ml-2 text-gray-900">{issue.message}</span>
                    </li>
                  ))}
                </ul>
              )}
            </li>
          );
        })}
      </ol>

    </section>
  );
}

export default SourceView;