package com.nighthawk.spring_portfolio.controllers;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// AI Chat Bot Controller based on Chat GPT 3.5 API
@RestController
@RequestMapping("/aichatbot")
public class AIChatbotController {
	
	private static final String key = "sess-dSJiZ7jXqwrqCWhUoqC056qKVH9LSTU0o3lUPb3n";

	// create chat GPT assistant id
	private static String assistantId = "asst_8OuJGh5SmCLAhKpRhdYuYgmQ";

	// create chat GTP thread id
	private static String threadId  = "thread_3KhrX2pFMUB059ox4YNIfQuI";

	// create the assistant and thread once the controller loads
	// need to test if this needs to be refreshed after a while
	/*static {
		// create assistant URL
		String createAssistantUrl = "https://api.openai.com/v1/assistants";

		// create thread URL
		String createThreadUrl = "https://api.openai.com/v1/threads";

		// chat gpt required headers
		Header contentType = new BasicHeader("Content-Type", "application/json");
		Header auth = new BasicHeader("Authorization", "Bearer " + key);
		Header openAiBeta = new BasicHeader("OpenAI-Beta", "assistants=v1");
		Header org = new BasicHeader("OpenAI-Organization", "org-sv0fuwJ8PSa0kMI5psf5d0Q8");

		// hard coded JSON string for assistance request payload
		// getting errors while JSON parsing, hence hardcoding it for now
		String aBodyStr = "{\"instructions\": \"You are a personal math tutor. Write and run code to answer math questions.\",\"name\": \"Math Tutor\",\"tools\": [{\"type\": \"code_interpreter\"}],\"model\": \"gpt-3.5-turbo\"}";

		JSONObject aJson;

		// make a post request and get assistant id
		try {
			aJson = sendHttpPost(createAssistantUrl, aBodyStr, contentType, auth, openAiBeta, org);
			assistantId = (String) aJson.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject tJson;

		// make a post request and get thread id
		try {
			// Chat GPT doesn't require a body / payload for thread request
			tJson = sendHttpPost(createThreadUrl, "", contentType, auth, openAiBeta, org);
			threadId = (String) tJson.get("id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	// basic hello greeting
	@GetMapping("")
	public String greeting() {
		return "Hello From Chatbot AI.";
	}

	// chat request mapping
	@GetMapping("/chat")
	public String chat(@RequestParam String message) {
		try {
			// user sends a message that is sent to chat gpt and a response is returned
			String response = getResponseFromAI(message);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * Chat GPT API requires creating a message, then calling the run. The status
	 * check must come "complete" Then the response can be read from the messages
	 * and returned to the user
	 */
	public String getResponseFromAI(String userQuery) throws Exception {

		System.out.println("Assistant Id: " + assistantId);
		System.out.println("Thread Id: " + threadId);

		// Create the message. Use the user's query
		String createMessageUrl = "https://api.openai.com/v1/threads/" + threadId + "/messages";
		Header contentType = new BasicHeader("Content-Type", "application/json");
		Header auth = new BasicHeader("Authorization", "Bearer " + key);
		Header org = new BasicHeader("OpenAI-Organization", "org-sv0fuwJ8PSa0kMI5psf5d0Q8");
		Header openAiBeta = new BasicHeader("OpenAI-Beta", "assistants=v1");

		String bodyStr = "{\"role\": \"user\",\"content\": \"" + userQuery + "\"}";

		JSONObject message = sendHttpPost(createMessageUrl, bodyStr, contentType, auth, openAiBeta, org);
		String messageId = (String) message.get("id");
		System.out.println(messageId);
		
		// Call the RUN api
		String runThreadUrl = "https://api.openai.com/v1/threads/" + threadId + "/runs";
		String tBodyStr = "{\"assistant_id\": \"" + assistantId
				+ "\",\"instructions\": \"Please address the user as Shivansh. The user has a premium account.\"}";

		JSONObject runObj = sendHttpPost(runThreadUrl, tBodyStr, contentType, auth, openAiBeta);
		String runId = (String) runObj.get("id");

		// check status
		String statusCheckUrl = "https://api.openai.com/v1/threads/" + threadId + "/runs/" + runId;
		JSONObject sObj = sendHttpGet(statusCheckUrl, contentType, auth, openAiBeta, org);

		String status = (String) sObj.get("status");
		int retry = 0;

		while (!status.equals("completed")) {
			// wait max 10 seconds for completion
			if (++retry >= 10) {
				break;
			}

			// sleep a second
			Thread.sleep(1000);
			sObj = sendHttpGet(statusCheckUrl, contentType, auth, openAiBeta);
			status = (String) sObj.get("status");
		}

		// get response
		// TODO error handling
		String getResponseUrl = "https://api.openai.com/v1/threads/" + threadId + "/messages";

		JSONObject rObj = sendHttpGet(getResponseUrl, contentType, auth, openAiBeta, org);

		System.out.println(rObj.toJSONString());
		// the response will match the first id
		String firstId = (String)rObj.get("first_id");
		// get data array from json
		JSONArray dataArray = (JSONArray)rObj.get("data");

		// to create the response string
		StringBuilder chatReponse = new StringBuilder();
		
	    for (int i = 0; i < dataArray.size(); i++) {
	    	JSONObject anObj = (JSONObject)dataArray.get(i);
	    	
	    	// the role must be assistant to hold the value and id must match firstId
	    	if (anObj.get("role").equals("assistant") && anObj.get("id").equals(firstId)) {
	    		JSONArray contentArray = (JSONArray)anObj.get("content");
	    		
	    		for (int j = 0; j < contentArray.size(); j++) {
	    			JSONObject contentObj = (JSONObject)contentArray.get(j);
	    			JSONObject textObj = (JSONObject)contentObj.get("text");
	    		
	    			// this contains the chat gpt's response
	    			chatReponse.append((String)textObj.get("value"));
	    			break;
	    		}
	    	}
	    }

	    return chatReponse.toString();
	}

	// send http post and return JSON response
	public static JSONObject sendHttpPost(String url, String body, Header... headers) throws Exception {
		JSONObject json = null;

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(body));
			httpPost.setHeaders(headers);
			json = httpClient.execute(httpPost, new JSONResponseHandler());
		}

		return json;
	}

	// send http get and return JSON response
	public static JSONObject sendHttpGet(String url, Header... headers) throws Exception {
		JSONObject json = null;

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeaders(headers);
			json = httpClient.execute(httpGet, new JSONResponseHandler());
		}

		return json;
	}

	// main method to testing
	public static void main(String[] args) throws Exception {
		AIChatbotController ai = new AIChatbotController();
		String response = ai.getResponseFromAI("Hi");
		System.out.println(response);
	}
}

class JSONResponseHandler implements HttpClientResponseHandler<JSONObject> {

	@Override
	public JSONObject handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
		// Get the status of the response
		int status = response.getCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			} else {
				JSONParser parser = new JSONParser();
				try {
					return (JSONObject) parser.parse(EntityUtils.toString(entity));
				} catch (ParseException | org.json.simple.parser.ParseException | IOException e) {
					e.printStackTrace();
					return null;
				}
			}

		} else {
			return null;
		}
	}
}
