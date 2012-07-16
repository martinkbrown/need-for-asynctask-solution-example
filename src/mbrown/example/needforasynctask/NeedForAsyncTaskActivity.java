package mbrown.example.needforasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NeedForAsyncTaskActivity extends Activity {
	
	TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) findViewById(R.id.textView1);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new VanityTask().execute();
				
			}
		});
        
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Button 2 clicked", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    class VanityTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
        protected Void doInBackground(Void... v) {
            
        	long endTime = System.currentTimeMillis() + 15 * 1000;
			
			// pretend to do something important here
	        while (System.currentTimeMillis() < endTime) {
	            synchronized (this) {
	                try {
	                    wait(endTime - System.currentTimeMillis());
	                } catch (Exception e) {
	                }
	            }
	        }
	        
	        runOnUiThread(new Runnable() {
	        	public void run() {
	        		tv.setText("Aaaaahhhhhhh!!!");
	        	}
	        });
	        
	        
			return null;
        }
    }
    
}