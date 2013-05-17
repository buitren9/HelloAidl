package com.soluosft.helloaidl;

import java.util.ArrayList;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class AidlSampleService extends Service {
	private final static String TAG = "AidlSampleService";
	private RemoteCallbackList<IAidlSampleActivityCmd> aidlActivityCmd = new RemoteCallbackList<IAidlSampleActivityCmd>();
	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(TAG, "--onBind()--");
		return aidlServiceCmd;
	}
	//implements the service aidl interface
	public IAidlSampleServiceCmd.Stub aidlServiceCmd = new IAidlSampleServiceCmd.Stub(){

		@Override
		public void registerCallback(IAidlSampleActivityCmd cb)
				throws RemoteException {
			aidlActivityCmd.register(cb);
		}

		@Override
		public void unregisterCallback(IAidlSampleActivityCmd cb)
				throws RemoteException {
			aidlActivityCmd.unregister(cb);
		}
		@Override
		public void suma(int suma) throws RemoteException {
			int suma2 = suma +1;
			
			MyClass myclass = new MyClass();
			ArrayList<Integer> lenteros = new ArrayList<Integer>();
			lenteros.add(1);
			lenteros.add(2);
			myclass.setvalues(1, 2, "data from service",lenteros);
			sendToActivities(suma2,myclass);
			// TODO Auto-generated method stub
			
		}
	};
	void sendToActivities(int suma,MyClass myc){
		int N = aidlActivityCmd.beginBroadcast();//send by broadcast to all activities or services that are binded
		for( int i = 0 ; i < N ; i++ ){
			try {
				aidlActivityCmd.getBroadcastItem(i).sendSuma(suma);//send data to sendsuma method of activity interface
				aidlActivityCmd.getBroadcastItem(i).setObject(myc);//send data to setobject method of activity interface
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			aidlActivityCmd.finishBroadcast();//
		}
	}
}
