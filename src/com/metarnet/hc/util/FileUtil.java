/**
 * 
 */
package com.metarnet.hc.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author liuhy
 */
public class FileUtil {

	public static Properties loadConf(String path) {
		Properties props = new Properties();
		FileInputStream istream = null;
		try {
			istream = new FileInputStream(path);
			props.load(istream);
			istream.close();
		} catch (Exception e) {

		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (Exception ignore) {
				}
			}
		}
		return props;
	}

}
