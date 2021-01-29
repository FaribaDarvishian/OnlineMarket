package com.example.OnlineMarket.data.model.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links{

	@SerializedName("self")
	private List<SelfItem> self;

	@SerializedName("collection")
	private List<CollectionItem> collection;
}