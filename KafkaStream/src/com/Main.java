package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.producer.ProducerStream;

public class Main {

	public static void main(String s[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data = br.readLine();
		while(data !=" ")
		{
			ProducerStream.add(data);
			data=br.readLine();
		}
		br.close();
		
		
	}
	
}
