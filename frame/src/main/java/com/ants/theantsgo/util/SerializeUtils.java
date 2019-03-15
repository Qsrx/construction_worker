package com.ants.theantsgo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * ===============Txunda===============
 * 作者：DUKE_HwangZj
 * 日期：2017/6/30 0030
 * 时间：上午 11:25
 * 描述：Serialize 序列化
 * ===============Txunda===============
 */
public class SerializeUtils {

	/**
	 * deserialization from file
	 * 
	 * @param filePath
	 * @return
	 * @throws RuntimeException
	 *             if an error occurs
	 */
	public static Object deserialization(String filePath) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(filePath));
			Object o = in.readObject();
			in.close();
			return o;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ClassNotFoundException occurred. ", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * serialize to file
	 * 
	 * @param filePath
	 * @param obj
	 * @return
	 * @throws RuntimeException
	 *             if an error occurs
	 */
	public static void serialization(String filePath, Object obj) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filePath));
			out.writeObject(obj);
			out.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}
}
