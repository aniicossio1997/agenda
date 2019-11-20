package laboratorio.practica.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import laboratorio.practica.R;
import laboratorio.practica.modelo.RecursoWeb;



public class MiAdaptadorConIcono extends ArrayAdapter<RecursoWeb> {
    private final Context context;
    private final List<RecursoWeb> values;

    public MiAdaptadorConIcono(Context context, List<RecursoWeb> values) {
        super(context, R.layout.recurso_iconico, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View rowView = inflater.inflate(R.layout.recurso_iconico, parent, false);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recurso_iconico, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.name);
        ImageView iv= (ImageView) convertView.findViewById(R.id.image);
        RecursoWeb rw = values.get(position);

        switch(rw.getTipoRecurso()) {
            case IMAGEN:
                iv.setImageResource(R.drawable.picture);
                break;
            case SITIO_WEB:
                iv.setImageResource(R.drawable.globe);
                break;
            case AUDIO:
                iv.setImageResource(R.drawable.sound);
                break;
            case VIDEO:
                iv.setImageResource(R.drawable.video);
                break;
        }
        tv.setText(rw.getNombre());

        return convertView;
    }
}