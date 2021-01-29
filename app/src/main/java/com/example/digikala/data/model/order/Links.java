package com.example.digikala.data.model.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links{

	@SerializedName("self")
	private List<SelfItem> self;

	@SerializedName("collection")
	private List<CollectionItem> collection;

	@SerializedName("customer")
	private List<CustomerItem> customer;
}