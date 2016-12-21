package info.bonber.www.liga;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //web();
        descargarContenido();

    }

    public void descargarContenido(){
        //Enviar parametros por post
        //AsyncHttpClient client = new AsyncHttpClient();
        String url="http://bonber.info/liga/liga.php";
        WebView view = (WebView) this.findViewById(R.id.navegador);
        view.loadDataWithBaseURL(null, "Cargando el contenido...", "text/html", "UTF-8", null);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl(url); //try js alert
        view.setWebChromeClient(new WebChromeClient()); // adding js alert support

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void recargarContenido(MenuItem item){
        descargarContenido();
    }



    public void descargarImg(String imagen){
        //Descargar imagen
       AsyncHttpClient client = new AsyncHttpClient();
        //client.get(imagen, new FileAsyncHttpResponseHandler(/* Context */ this) {
         /*    @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                setContentView(R.layout.activity_main);
                TextView texto = (TextView)findViewById(R.id.texto);
                texto.setText("Error al enviar al descargar la imagen: "+statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                setContentView(R.layout.activity_main);
                TextView texto = (TextView)findViewById(R.id.texto);
                texto.setText("Imagen descargada");
                ImageView imagen = (ImageView)findViewById(R.id.imagen);
                imagen.setImageURI(Uri.fromFile(file));
            }

        });*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_maps) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/maps?q=Colonia%20iñigo%20arista%2013%207%20estella%2031200"));
            startActivity(browserIntent);
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(),
                    "Funcion no disponible", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_inicio) {
            Toast.makeText(getApplicationContext(),
                    "Inicio", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_nosotros) {
            Toast.makeText(getApplicationContext(),
                    "Nosotr@s", Toast.LENGTH_SHORT).show();
            AlertDialog alerta;
            alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Sobre Nosotr@s");
            alerta.setMessage("Mensaje kajsdfkajwdshfl ashflkasj hflk asjhdfklj ashdflk \n ahdsf lkjashdaljkdshf kljasdhf kaljhf kaldsjh fkljashdf lkajsdfh klasjdhf lkajshd");
            alerta.show();
        } else if (id == R.id.nav_twitter) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/lizarrakogazte"));
            startActivity(browserIntent);
        } else if (id == R.id.nav_facebook) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Lizarrako-Gazte-Asanblada-454088591453598/"));
            startActivity(browserIntent);
        }else if (id == R.id.nav_telegram) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/infoliga"));
            startActivity(browserIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
