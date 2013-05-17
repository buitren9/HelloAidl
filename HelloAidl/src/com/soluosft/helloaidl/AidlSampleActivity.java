package com.soluosft.helloaidl;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AidlSampleActivity extends Activity {
	private final static String TAG = "AidlSampleActivity";
	EditText inputText;
	Button sendButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "--onCreate()--");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//It is made the binding the activity with the service
		Log.i(TAG,"--bindService()--");
		if(aidlServiceCmd == null){
			bindService(new Intent("com.soluosft.helloaidl.IAidlSampleServiceCmd"), serviceConnection, BIND_AUTO_CREATE);
		}

		inputText = (EditText) findViewById(R.id.inputText);
		sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					aidlServiceCmd.suma(Integer.parseInt(inputText.getText().toString()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

	}
	//implements the serviceconnection that connect te activity with the service
	private IAidlSampleServiceCmd aidlServiceCmd = null;
	private ServiceConnection serviceConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			aidlServiceCmd = IAidlSampleServiceCmd.Stub.asInterface(service);
			try {
				aidlServiceCmd.registerCallback(aidlActivityCmd);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			aidlServiceCmd = null;
		}

	};
	//implements the aidlactivity interface
	public IAidlSampleActivityCmd.Stub aidlActivityCmd = new IAidlSampleActivityCmd.Stub() {


		@Override
		public void sendSuma(int numero) throws RemoteException {
			TextView txv = (TextView)findViewById(R.id.outPutText);
			
			txv.setText(String.valueOf(numero));
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setObject(MyClass myc) throws RemoteException {
			// TODO Auto-generated method stub

			TextView txv1 = (TextView)findViewById(R.id.outPutArray);
			txv1.setText(String.valueOf(myc.getListOfinteger().get(0)+","+String.valueOf(myc.getListOfinteger().get(1))));
			TextView txv2 = (TextView)findViewById(R.id.outPutIntegerValue);
			txv2.setText(String.valueOf(myc.getValueX()));
			TextView txv3 = (TextView)findViewById(R.id.outPutTextPar);
			txv3.setText(myc.getText());
			
		}

	
	};

}