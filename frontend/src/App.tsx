import { useState, useEffect } from 'react'
import axios from 'axios'

type HealthResponse = {
  status: string
}

function App() {

  const [status, setStatus] = useState<string>('확인 중...');

  useEffect(() => {
    axios
      .get<HealthResponse>('/api/health')
      .then((res) => setStatus(res.data.status))
      .catch(() => setStatus('연결 실패'));
  }, [])

  return (
    <main className="p-8">
      <h1 className="text-3xl font-bold text-blue-600">
        a11y-checker
      </h1>
      <p className="mt-2 text-gray-600">서버 상태: {status}</p>
    </main>
  )
}

export default App