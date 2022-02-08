package com.rest.trello;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class Board {

	public final static String KEY = "b6805fcb141599e9c402b97ed8ef56c5";
	public final static String TOKEN = "a29b4d70b72e435025bb8ebf16ec8d4ddcad63f69053e53206a4cb4be300f494";

	@Test
	public void getBoard() {

		RestAssured.baseURI = "https://api.trello.com";
		RestAssured.basePath = "/1/boards/EmvH6R7I";

		given().
			param("key", KEY).
			param("token", TOKEN).
		when().
			get().
		then().
			assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("name", equalTo("First Board")).and().
				body("prefs.background", equalTo("orange"));
	}
	
	@Test
	public void createBoard() {

		RestAssured.baseURI = "https://api.trello.com";
		RestAssured.basePath = "/1/boards";
		
		String boardName = "Rest Assured Created Board" + (int)(Math.random()*100);
		given().
			queryParam("key", KEY).
			queryParam("token", TOKEN).
			queryParam("name",boardName).
			header("Content-Type","application/json").
		when().
			post().
		then().
			assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("name", equalTo(boardName));
	}
}
