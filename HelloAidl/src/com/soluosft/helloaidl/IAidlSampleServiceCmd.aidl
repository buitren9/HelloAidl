package com.soluosft.helloaidl;

import com.soluosft.helloaidl.IAidlSampleActivityCmd;

interface IAidlSampleServiceCmd{
	void suma(int suma);
	
	void registerCallback(IAidlSampleActivityCmd cb);  
   void unregisterCallback(IAidlSampleActivityCmd cb);  
}