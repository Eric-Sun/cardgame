package com.h13.cardgame.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

public class HttpCommonUtils {

	public static String getJsonFromRequest(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		byte[] allArr = new byte[contentLength];
		InputStream is = request.getInputStream();
		byte[] readArr = new byte[1024];
		int i = 0;
		int pos = 0;
		while ((i = is.read(readArr)) != -1) {
			System.arraycopy(readArr, 0, allArr, pos, i);
			pos += i;
		}
		return new String(allArr, "UTF-8");
	}

}
