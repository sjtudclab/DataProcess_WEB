package cn.edu.sjtu.dcl.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class HttpUtil {
	public static String MAIN_SITE = "http://localhost:7001/DataProcess_WEB";
	
	private static HttpURLConnection urlConn;
	private static final int TIMEOUT = 5000;
	
	public static JSONObject local_post(String method, String content){
		String result = "";
		JSONObject jsonObj = null;
		System.out.println(method);
		try {
			URL url = new URL(MAIN_SITE + method);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(TIMEOUT);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlConn.connect();
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();

			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream());

			BufferedReader buffer = new BufferedReader(in);
			String inputLine = null;
			while (((inputLine = buffer.readLine()) != null)) {
				System.out.println(inputLine);
				result += inputLine + "\n";
			}
			System.out.println(result);
			in.close();
			urlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result.equals("null\n"))
			jsonObj = JSONObject.fromObject(result);
		else
			jsonObj = JSONObject.fromObject("{error:" + result + "}");

		return jsonObj;
	}

	public static JSONObject http_get(String method) {
		System.out.println(MAIN_SITE + method);
		String result = "";
		JSONObject jsonObj = null;
		System.out.println("in http");
		try {
			URL url = new URL(MAIN_SITE + method);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(TIMEOUT);
			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream());

			BufferedReader buffer = new BufferedReader(in);
			String inputLine = null;

			while (((inputLine = buffer.readLine()) != null)) {
				result += inputLine + "\n";
			}

			in.close();
			urlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
		if (!result.equals("null\n"))
			jsonObj = JSONObject.fromObject(result);
		else
			jsonObj = JSONObject.fromObject("{error:" + result + "}");
		return jsonObj;
	}

	public static JSONObject http_post(String method, String content) {
		String result = "";
		JSONObject jsonObj = null;
		System.out.println(method);
		try {
			URL url = new URL(MAIN_SITE + method);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(TIMEOUT);
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			urlConn.setRequestMethod("POST");
			urlConn.setUseCaches(false);
			urlConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlConn.connect();
			DataOutputStream out = new DataOutputStream(
					urlConn.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();

			InputStreamReader in = new InputStreamReader(
					urlConn.getInputStream());

			BufferedReader buffer = new BufferedReader(in);
			String inputLine = null;
			while (((inputLine = buffer.readLine()) != null)) {
				System.out.println(inputLine);
				result += inputLine + "\n";
			}
			System.out.println(result);
			in.close();
			urlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!result.equals("null\n"))
			jsonObj = JSONObject.fromObject(result);
		else
			jsonObj = JSONObject.fromObject("{error:" + result + "}");

		return jsonObj;
	}

}

