package com.example.myfirstapp;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

public class Client {
	Socket toServer;
	OutputStream stream;
	
	JSONOutputStream jsonOut;
	byte[] b = new byte[100];
	
	String destinationIP = "192.168.56.1";
	int port;
	String send;
	

	public Client(){
		//destinationIP = "127.0.0.1";
		port = 9995;
		send = "JOSH IS'NT Asadfsadfa";
	}
	
	public Client(String message){
		this.send = message;
		//destinationIP = "127.0.0.1";
		port = 9995;
	}
	
	public void transmit()
	{
		try {
			System.out.println("Attempting Connection...\n");
			toServer = new Socket(destinationIP, port);
			System.out.println("Connected to " + toServer.getInetAddress().getCanonicalHostName());
			jsonOut = new JSONOutputStream(toServer.getOutputStream());
			
			CommBean bean = new CommBean();
			
			jsonOut.writeObject(bean);
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
	}
}
