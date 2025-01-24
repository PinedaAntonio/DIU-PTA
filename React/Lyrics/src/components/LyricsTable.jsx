function LyricsTable({ lyrics }) {
    return (
      <table>
        <thead>
          <tr>
            <th>Artista</th>
            <th>Canción</th>
            <th>Letra</th>
          </tr>
        </thead>
        <tbody>
          {lyrics.map((lyric, index) => (
            <tr key={index}>
              <td>{lyric.artist}</td>
              <td>{lyric.song}</td>
              <td>{lyric.lyrics}</td>
            </tr>
          ))}
        </tbody>
      </table>
    )
  }
  
  export default LyricsTable