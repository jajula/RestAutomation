package com.rest.github;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Repository {

	public final static String bearerToken = "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";

	@Test
	public void createRepo() {

		RestAssured.baseURI = "https://api.github.com";
		RestAssured.basePath = "/user/repos";

		String token = "Bearer " + bearerToken;

		System.out.println(token);
		String repoName = "Rest-Assured-Repository" + (int) (Math.random() * 100);

		JSONObject json = new JSONObject();
		json.put("name", repoName);
		
		System.out.println(json);

		Response res = given().log().all().

				header("Authorization", token).header("Accept-Encoding", "gzip, deflate, br")
				.contentType(ContentType.JSON).body(json.toString()).when().post().then().log().all().assertThat().statusCode(201)
				.and().contentType(ContentType.JSON)
				.and().
				body("name", equalTo(repoName))
				.extract().response();
		
		System.out.println(res.getBody().jsonPath().get("name").toString());
	}
	
	@Test
	public void createRepoSerialized() {

		RestAssured.baseURI = "https://api.github.com";
		RestAssured.basePath = "/user/repos";

		String token = "Bearer " + bearerToken;

		System.out.println(token);
		String repoName = "Rest-Assured-Repository" + (int) (Math.random() * 100);

		JSONObject json = new JSONObject();
		json.put("name", repoName);
		
		System.out.println(json);

		Response res = given().log().all().

				header("Authorization", token).header("Accept-Encoding", "gzip, deflate, br")
				.contentType(ContentType.JSON).body(json.toString()).when().post().then().log().all().assertThat().statusCode(201)
				.and().contentType(ContentType.JSON)
				.and().
				body("name", equalTo(repoName))
				.extract().response();
		
		System.out.println(res.getBody().jsonPath().get("name").toString());
	}
}
