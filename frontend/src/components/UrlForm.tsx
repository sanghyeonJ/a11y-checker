import { useState } from "react";

type UrlFormProps = {
  loading: boolean;
  error: string | null;
  onSubmit: (url: string) => void;
}

function UrlForm ({ loading, error, onSubmit }: UrlFormProps) {
  const [url, setUrl] = useState('');

  return (
    <form
      noValidate
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit(url.trim());
      }}
      className="mt-6"
    >
      <label htmlFor="check-url" className="block font-semibold text-gray-800">
        검사할 페이지 주소
      </label>

      <div className="mt-2 flex gap-2">
        <input 
          id="check-url"
          type="url"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="https://example.com"
          autoComplete="url"
          aria-invalid={error ? true : undefined}
          aria-describedby={error ? 'check-url-help check-url-error' : 'check-url-help'}
          className="flex-1 rounded border border-gray-400 px-3 py-2 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
        />
        <button
          type="submit"
          disabled={loading}
          className="rounded bg-blue-700 px-4 py-2 font-semibold text-white hover:bg-blue-800 disabled:cursor-not-allowed disabled:bg-gray-500 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600"
        >
          {loading ? '검사 중...' : '검사하기'}
        </button>
      </div>

      <p id="check-url-help" className="mt-2 text-sm text-gray-600">
        정적 HTML만 검사합니다. JavaScript로 화면을 그리는 페이지(SPA)는 결과가 정확하지 않을 수 있어요.
      </p>

      {error && (
        <p id="check-url-error" role="alert" className="mt-2 text-sm font-semibold text-red-700">
          {error}
        </p>
      )}
    </form>
  );
}

export default UrlForm;