package com.example.myfirstapp;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

public class Client {
	Socket toServer;
	OutputStream stream;
	
	JSONOutputStream jsonOut;
	byte[] b = new byte[100];
	
	String destinationIP = "192.168.2.11";
	int port;
	CommBean data;
	

	public Client(){
		//destinationIP = "127.0.0.1";
		port = 9995;
		this.data = new CommBean("command");
	}
	
	public Client(CommBean message){
		this.data = message;
		//destinationIP = "127.0.0.1";
		port = 9995;
	}
	
	@SuppressWarnings("rawtypes")
	public HashMap transmit()
	{
		HashMap inString = null;
		
		try {
			System.out.println("Attempting Connection...\n");
			toServer = new Socket(destinationIP, port);
			System.out.println("Connected to " + toServer.getInetAddress().getCanonicalHostName());
			jsonOut = new JSONOutputStream(toServer.getOutputStream());
			
			jsonOut.writeObject(this.data);
			
			JSONInputStream jsonIn = new JSONInputStream(toServer.getInputStream());
			inString = (HashMap) jsonIn.readObject();
			
			toServer.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Unknown Host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception: Server Probably isn't listening");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("End of Client");
		return inString;
	}
}
