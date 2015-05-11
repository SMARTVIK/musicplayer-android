package d366.net.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;

import java.util.ArrayList;

// Service class execute music playback continuously, even when the user is not directly interacting with the application
public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;

    private ArrayList<Song> songs;

    private int currentSongPosition;

    public void onCreate(){
        super.onCreate();

        currentSongPosition = 0;
        mediaPlayer = new MediaPlayer();
        initMediaPlayer();
    }

    public void initMediaPlayer(){
        // The wake lock will let playback continue when device becomes idle
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setList(ArrayList<Song> theSongs){
        songs = theSongs;
    }

    // Binder support the interaction between the Activity and Service classes
    public class MusicBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
