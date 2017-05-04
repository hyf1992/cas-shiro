package com.whitehorse.qingzhi.util;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author hyf
 * @date 2017年5月2日
 * @description
 */
public class CasTicket {

	private static final Logger LOG = Logger.getLogger(CasTicket.class.getName());

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000)
			.setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000)
			.build();

	private CasTicket() {

	}

	public static String getTicket(final String server, final String username, final String password,
			final String service) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		notNull(service, "service must not be null");
		return getServiceTicket(server, getTicketGrantingTicket(server, username, password), service);
	}

	private static String getServiceTicket(final String server, final String ticketGrantingTicket,
			final String service) {

		if (ticketGrantingTicket == null)
			return null;

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;

		try {
			// 创建httpPost
			HttpPost httpPost = new HttpPost(server + "/" + ticketGrantingTicket);

			String params = "service=" + service;
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);

			// 创建默认的httpClient实例
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");

			int code = response.getStatusLine().getStatusCode();
			System.out.println("code=" + code);

			switch (code) {
			case 200:
				return responseContent;
			default:
				LOG.warning("Invalid response code (" + code + ") from CAS server!");
				LOG.info("Response (1k): " + responseContent.substring(0, Math.min(1024, responseContent.length())));
				break;
			}

		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getTicketGrantingTicket(final String server, final String username, final String password) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;

		try {
			// 创建httpPost
			HttpPost httpPost = new HttpPost(server);
			// 创建参数队列
			// List<NameValuePair> nameValuePairs = new
			// ArrayList<NameValuePair>();
			// nameValuePairs.add(new BasicNameValuePair("username", username));
			// nameValuePairs.add(new BasicNameValuePair("password", password));

			// 设置参数
			String params = "username=" + username + "&password=" + password;
			StringEntity stringEntity = new StringEntity(params, "UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);

			// 创建默认的httpClient实例
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");

			int code = response.getStatusLine().getStatusCode();
			System.out.println("code=" + code);

			switch (code) {
			case 201: {
				final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(responseContent);
				if (matcher.matches()) {
					String str = matcher.group(1);
					System.out.println("str=" + str);

					return str;
				}
				LOG.warning("Successful ticket granting request, but no ticket found!");
				LOG.info("Response (1k): " + responseContent.substring(0, Math.min(1024, responseContent.length())));
				break;
			}
			default:
				LOG.warning("Invalid response code (" + code + ") from CAS server!");
				LOG.info("Response (1k): " + responseContent.substring(0, Math.min(1024, responseContent.length())));
				break;
			}
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void main(final String[] args) {
		final String server = "http://127.0.0.1:38080/cas/v1/tickets";
		final String username = "admin";
		final String password = "123456";
		final String service = "http://127.0.0.1:28080/cas";
		LOG.info(getTicket(server, username, password, service));
	}
}
