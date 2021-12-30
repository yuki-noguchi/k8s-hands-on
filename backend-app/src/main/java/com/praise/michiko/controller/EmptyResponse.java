package com.praise.michiko.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EmptyResponse {

	private static EmptyResponse instance = new EmptyResponse();

	private EmptyResponse() {
	}

	public static EmptyResponse instance() {
		return instance;
	}
}
