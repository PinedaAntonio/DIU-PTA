import { useState } from 'react'
import axios from 'axios'
import SearchForm from './components/SearchFrom'
import LyricsTable from './components/LyricsTable'
import './App.css'

function App() {
  const [lyrics, setLyrics] = useState([])

  const handleSearch = async (artist, song) => {
    try {
      const response = await axios.get(`https://api.lyrics.ovh/v1/${artist}/${song}`)
      setLyrics((prevLyrics) => [
        ...prevLyrics,
        { artist, song, lyrics: response.data.lyrics },
      ])
    } catch (error) {
      console.error('Error fetching lyrics:', error)
    }
  }

  return (
    <div className="App">
      <h1>Buscar Letras de Canciones</h1>
      <SearchForm onSearch={handleSearch} />
      <LyricsTable lyrics={lyrics} />
    </div>
  )
}

export default App