package com.soluosft.helloaidl;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class MyClass implements Parcelable {
    private int valueX;
    private int valueY;
    private String text;
    private ArrayList<Integer> listOfinteger;
    
    public MyClass(){};
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(valueX);
        out.writeInt(valueY);
        out.writeString(text);
        out.writeList(listOfinteger);
       
    }

    public void setvalues(int valueX,int valueY,String text,ArrayList<Integer>listOfinteger){
    	this.valueX = valueX;
    	this.valueY = valueX;
    	this.text = text;
    	this.listOfinteger=listOfinteger;
    }
    
    
    public static final Parcelable.Creator<MyClass> CREATOR
            = new Parcelable.Creator<MyClass>() {
        public MyClass createFromParcel(Parcel in) {
            return new MyClass(in);
        }

        public MyClass[] newArray(int size) {
            return new MyClass[size];
        }
    };
    
    public MyClass(Parcel in) {
    	valueX = in.readInt();
    	valueY = in.readInt();
    	text = in.readString();
    	listOfinteger = new ArrayList<Integer>();
    	in.readList(listOfinteger, getClass().getClassLoader());
    	
    }

	public int getValueX() {
		return valueX;
	}

	public void setValueX(int valueX) {
		this.valueX = valueX;
	}

	public int getValueY() {
		return valueY;
	}

	public void setValueY(int valueY) {
		this.valueY = valueY;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<Integer> getListOfinteger() {
		return listOfinteger;
	}
	public void setListOfinteger(ArrayList<Integer> listOfinteger) {
		this.listOfinteger = listOfinteger;
	}

    
}