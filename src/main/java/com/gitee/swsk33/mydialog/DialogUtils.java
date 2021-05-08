package com.gitee.swsk33.mydialog;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 * 内部实用类
 * 
 * @author swsk33
 *
 */
class DialogUtils {

	/**
	 * 异步播放音频
	 * 
	 * @param audioUrl 音频文件URL
	 */
	public static void playAudioAsync(URL audioUrl) {
		new Thread(() -> {
			AudioInputStream audioStream = null;
			AudioFormat audioFormat = null;
			DataLine.Info dataLineInfo = null;
			SourceDataLine sourceDataLine = null;
			try {
				audioStream = AudioSystem.getAudioInputStream(audioUrl);
				audioFormat = audioStream.getFormat();
				dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
				sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();
				int count;
				byte tempBuff[] = new byte[1024];
				while ((count = audioStream.read(tempBuff, 0, tempBuff.length)) != -1) {
					sourceDataLine.write(tempBuff, 0, count);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sourceDataLine.drain();
				sourceDataLine.close();
				try {
					audioStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}