package laboratorio.practica;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import laboratorio.practica.adapters.MiAdaptadorConIcono;
import laboratorio.practica.modelo.RecursoWeb;
import laboratorio.practica.modelo.TipoRecurso;

import android.app.ActivityManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<RecursoWeb> values ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        values = new ArrayList<RecursoWeb>();

        values.add(new RecursoWeb(0, "Fantasma", "http://www.sonidosmp3gratis.com/sounds/ruido_1.mp3", "Ruido fantasmagórico", TipoRecurso.AUDIO));
        values.add(new RecursoWeb(0, "Campanas", "http://www.sonidosmp3gratis.com/sounds/campanas_3.mp3", "Sonido de camapandas", TipoRecurso.AUDIO));
        values.add(new RecursoWeb(0, "Instagram", "http://www.instagram.com", "Sitio Oficial de Instagram", TipoRecurso.SITIO_WEB));
        values.add(new RecursoWeb(0, "Guitarra", "https://d1aeri3ty3izns.cloudfront.net/media/44/448686/1200/preview.jpg", "Guitarra PRS", TipoRecurso.IMAGEN));
        values.add(new RecursoWeb(0, "Perro", "https://d1aeri3ty3izns.cloudfront.net/media/44/448686/1200/preview.jpg", "Perro", TipoRecurso.IMAGEN));


        listView = this.findViewById(R.id.listaRecursos);
        this.setListView(listView);
        this.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.getListView().setAdapter(new MiAdaptadorConIcono(this,  values));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                RecursoWeb rw = (RecursoWeb) adapterView.getAdapter().getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rw.getUrl()));
                startActivity(intent);
                //Toast.makeText(getBaseContext(),rw.getUrl(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //===============================================================


        final String textValues= this.convertString(values);

        FloatingActionButton shareBtn = findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String texto = textValues;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, texto);
                startActivity(Intent.createChooser(intent, "Compartir"));
            }
        });


    }

    public ListView getListView() {
        return listView;
    }





    public void setListView(ListView listView) {
        this.listView = listView;
    }


    private  String convertString( List<RecursoWeb> values){
        String cadena = "==LISTADO DE RECURSOS== \n";
        cadena =cadena + "\n";
        for (int i = 0; i < values.size(); i++) {
            RecursoWeb recursoWeb = values.get(i);
            cadena = cadena+(values.get(i).toString())+ "\n";

        }

        return cadena;
    }


}