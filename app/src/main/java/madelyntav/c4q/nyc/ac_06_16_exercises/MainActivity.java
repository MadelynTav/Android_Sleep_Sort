package madelyntav.c4q.nyc.ac_06_16_exercises;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {

    EditText num1;
    Button submit;
    TextView solution;
    String submitted;
    String solutionText="";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.button);
        solution = (TextView) findViewById(R.id.solution);
        num1 = (EditText) findViewById(R.id.num1);




    }

    public void submit(View view) {
        submitted=num1.getText().toString();
        for(int i=0;i<submitted.length();i++) {
            android.os.Handler handler=new android.os.Handler();
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    solutionText+=submitted.charAt(finalI);
                    solution.setText(solutionText);
                }
            }, Integer.valueOf(submitted.charAt(i)+"000"));


        }

        showNotification();

    }

    public void showNotification(){

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);

        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Job Has Finished");
        builder.setContentText("All Numbers Loaded");
        builder.setSmallIcon(R.drawable.ic_stat_action_grade);
        Notification notification=builder.build();
        builder.setVisibility(Notification.VISIBILITY_SECRET);
        notificationManager.notify(1234,notification);



    }
}
