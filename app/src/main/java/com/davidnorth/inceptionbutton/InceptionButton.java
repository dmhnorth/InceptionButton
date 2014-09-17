package com.davidnorth.inceptionbutton;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class InceptionButton extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inception_button);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inception_button, menu);
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

    public void playSound(View view) {

        final MediaPlayer mp = new MediaPlayer();

        ImageButton b = (ImageButton) findViewById(R.id.redButton);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Reset if currently playing
                if(mp.isPlaying())
                {
                    mp.stop();
                    mp.reset();
                }

                //Attempt to play the sound
                try {

                    Context appContext = getApplicationContext();

                    MediaPlayer mp = MediaPlayer.create(appContext , R.raw.inceptionbutton);
                    mp.start();

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();

                        }
                    });
                    Log.i("onClick", "You pressed the button");


                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_inception_button, container, false);
            return rootView;
        }
    }
}
