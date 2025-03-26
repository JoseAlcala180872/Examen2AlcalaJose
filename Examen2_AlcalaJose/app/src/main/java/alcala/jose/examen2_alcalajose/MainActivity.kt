package alcala.jose.examen2_alcalajose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var canciones=  ArrayList<Cancion>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        agregarCanciones()
        // Agregar algunas canciones de ejemplo
        if (SongManager.getSongCount() == 0) {
            SongManager.addSong("Bohemian Rhapsody", "Queen", "5:55", "A Night at the Opera")
            SongManager.addSong("Imagine", "John Lennon", "3:04", "Imagine")
            SongManager.addSong("Hotel California", "Eagles", "6:30", "Hotel California")
        }

        var gridView:GridView=findViewById(R.id.gridView)
        var adaptador: AdaptadorCanciones=AdaptadorCanciones(this, canciones)
        gridView.adapter=adaptador
        val button: Button = findViewById(R.id.btnAgregarCancion)

        button.setOnClickListener {
            val intent:Intent= Intent(this,AgregarCancion::class.java)
            startActivity(intent)
        }


    }

    fun agregarCanciones(){
        canciones.add(Cancion("Enter sandman", "Metallica", "5:31", "Metallica"))
        canciones.add(Cancion("Holy wars", "Megadeth", "6:36", "Rust in peace"))
        canciones.add(Cancion("Let it happen", "Tame impala", "7:46", "Currents"))
        canciones.add(Cancion("Bajan", "Pescado rabioso", "3:27", "Artaud"))
        canciones.add(Cancion("Cementerio club", "Pescado rabioso", "4:55", "Artaud"))
        canciones.add(Cancion("El taquicardio", "El komander", "3:00", "Belico"))
    }

    class AdaptadorCanciones: BaseAdapter {
        var cancioness=ArrayList<Cancion>()
        var context: Context?=null

        constructor(context: Context, cancioness: ArrayList<Cancion>){
            this.cancioness=cancioness
            this.context=context
        }

        override fun getCount(): Int {
            return cancioness.size
        }

        override fun getItem(position: Int): Any {
            return cancioness[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var cancion=cancioness[position]
            var inflator=context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista=inflator.inflate(R.layout.cancion, null)

            //imagen
            var imagen: ImageView=vista.findViewById(R.id.ivCancion)
            //titulo
            var titulo: TextView =vista.findViewById(R.id.titulo)
            //artista
            var artista: TextView =vista.findViewById(R.id.artista)

            titulo.setText(cancion.name)
            artista.setText(cancion.artist)

            imagen.setOnClickListener(){

                var intent=Intent(context, DetalleCancion::class.java)
                intent.putExtra("name",cancion.name)
                intent.putExtra("artist",cancion.artist)
                intent.putExtra("duration",cancion.duration)
                intent.putExtra("album",cancion.album)

                context!!.startActivity(intent)
            }
            return vista
        }

    }
}
