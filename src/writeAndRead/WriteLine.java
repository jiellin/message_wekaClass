package writeAndRead;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class WriteLine {
	private File file;
	private BufferedWriter writer;

	public WriteLine(String path) throws UnsupportedEncodingException {
		this.file = new File(path);
		try {
			this.writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file),"utf-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void appendLine(String content) {
		try {
			writer.write(content + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void appendLine(int content) {
		try {
			writer.write(content + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void append(String content) {
		try {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeWrite() {
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
