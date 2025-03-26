package alcala.jose.examen2_alcalajose

data class Cancion(val name: String,
                   val artist: String,
                   val duration: String,
                   val album: String,
                    )

object SongManager {
    private val songList = mutableListOf<Cancion>()

    private val colors = listOf(
        0xFFE57373.toInt(), // Rojo claro
        0xFF81C784.toInt(), // Verde claro
        0xFF64B5F6.toInt(), // Azul claro
        0xFFFFB74D.toInt(), // Naranja claro
        0xFF9575CD.toInt()  // Púrpura claro
    )

    fun addSong(name: String, artist: String, duration: String, album: String) {
        // Usamos el hash del nombre para seleccionar un color consistente
        val colorIndex = name.hashCode().absoluteValue() % colors.size
        val newSong = Cancion(name, artist, duration, album)
        songList.add(newSong)
    }

    fun removeSong(index: Int) {
        if (index in 0 until songList.size) {
            songList.removeAt(index)
        }
    }

    fun getAllSongs(): List<Cancion> {
        return songList.toList()
    }

    fun getSongCount(): Int {
        return songList.size
    }
}
private fun Int.absoluteValue(): Int = if (this < 0) -this else this