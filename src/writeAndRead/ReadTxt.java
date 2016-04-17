package writeAndRead;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadTxt {
	private BufferedReader bufferedReader;
	private String filePath;
	private InputStreamReader inputStreamReader;

	public ReadTxt(String filepath) {
		this.filePath = filepath;
	}

	public BufferedReader getRead() {
		String encoding = "utf-8";
		File file = new File(filePath);
		try {
			inputStreamReader = new InputStreamReader(
					new FileInputStream(file), encoding);
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return bufferedReader;
	}

	public void closeRead() {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readToString() {
		File file = new File(filePath);
		long fileLength = file.length();
		byte[] fileContent = new byte[Integer.parseInt(String
				.valueOf(fileLength))];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(fileContent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(fileContent);
	}
}
