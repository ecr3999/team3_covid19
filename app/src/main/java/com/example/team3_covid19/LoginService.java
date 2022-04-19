package com.example.team3_covid19;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    String BASE_URL = "https://talentpool.oneindonesia.id";

    @Headers({
            "Accept: application/json",
            "x-api-key: 454041184B0240FBA3AACD15A1F7A8BB"
    })
    @POST("/api/user/login")
    Call<LoginData> getLoginData(@Body RequestBody body);
}
