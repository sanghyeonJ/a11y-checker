import { useState } from "react"
import type { CheckResponse } from "./types/check"
import { checkUrl, getErrorMessage } from "./api/check";

import UrlForm from "./components/UrlForm";
import CheckSummary from "./components/CheckSummary";
import ItemList from "./components/ItemList";

function App() {

  const [result, setResult] = useState<CheckResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handleCheck (url: string) {
    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const data = await checkUrl(url);
      setResult(data)
    } catch (e) {
      setError(getErrorMessage(e));
    } finally {
      setLoading(false);
    }
  }

  return (
    <main className="p-8">
      <h1 className="text-3xl font-bold text-blue-600">
        a11y-checker
      </h1>

      <UrlForm loading={loading} error={error} onSubmit={handleCheck} />

      {result && (
        <>
          <CheckSummary result={result} />
          <ItemList items={result.items} />
        </>
      )}
    </main>
  )
}

export default App