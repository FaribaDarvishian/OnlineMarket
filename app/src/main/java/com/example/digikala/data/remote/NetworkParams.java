package com.example.digikala.data.remote;

import java.util.HashMap;
import java.util.Map;
public class NetworkParams {
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    public static final String CONSUMER_KEY = "ck_f025265e3479f7bee8e93bffe5685517b93ec27d";
    public static final String CONSUMER_SECRET = "cs_27b19e572ac9cf1333d4d53f7082a15e9fb6a2b0";

    public static final String API_KEY = "?consumer_key=" +
            CONSUMER_KEY +
            "&consumer_secret=" +
            CONSUMER_SECRET;

    public static Map<String, String> BASE_OPTIONS = new HashMap<String, String>() {{
        put("consumer_key", CONSUMER_KEY);
        put("consumer_secret", CONSUMER_SECRET);
    }};

    public static Map<String, String> getProducts(int perPage, int page, String orderBy) {
        Map<String, String> queryOptions = new HashMap<>();
        queryOptions.putAll(BASE_OPTIONS);
        queryOptions.put("per_page", String.valueOf(perPage));
        queryOptions.put("page", String.valueOf(page));
        queryOptions.put("orderby", orderBy);

        return queryOptions;
    }
}
