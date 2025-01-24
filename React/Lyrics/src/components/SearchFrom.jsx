import { useState } from 'react'

function SearchForm({ onSearch }) {
  const [artist, setArtist] = useState('')
  const [song, setSong] = useState('')
  const [isSearching, setIsSearching] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setIsSearching(true)
    await onSearch(artist, song)
    setIsSearching(false)
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Artista"
        value={artist}
        onChange={(e) => setArtist(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Canción"
        value={song}
        onChange={(e) => setSong(e.target.value)}
        required
      />
      <button type="submit" disabled={isSearching}>
        {isSearching ? 'Buscando' : 'Buscar'}
      </button>
    </form>
  )
}

export default SearchForm