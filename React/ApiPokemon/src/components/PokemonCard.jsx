import { useEffect } from "react"

function PokemonCard() {
    useEffect (() =>{
        fetch("https://pokeapi.co/api/v2/pokemon/1")
            .then((response) => (response.json))
            .then((data) => {
                console.log(data)
            })
    })

  return (
    <li>

    </li>
  )
}

export default PokemonCard