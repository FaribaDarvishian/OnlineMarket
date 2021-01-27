package com.example.digikala.data.model.product;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Links{

	@SerializedName("self")
	private List<com.example.digikala.data.model.product.SelfItem> self;

	@SerializedName("collection")
	private List<com.example.digikala.data.model.product.CollectionItem> collection;
}