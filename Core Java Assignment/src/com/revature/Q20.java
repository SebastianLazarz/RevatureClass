package com.revature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q20 {
	
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public Q20 (File file) {
		setFile(file);
	}
	
	public void print () throws IOException {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		String line;
		while((line = br.readLine()) != null) {
			//process the line
			String[] splitLine = line.split(":");
			System.out.println();
			System.out.println("Name: " + splitLine[0] + " " + splitLine[1]);
			System.out.println("Age: " + splitLine[2]);
			System.out.println("State: " + splitLine[3]);
		}
		br.close();
		
	}	
	
}
