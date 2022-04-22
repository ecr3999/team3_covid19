package com.example.team3_covid19.CovidMenu.retrofit;

import com.google.gson.annotations.SerializedName;

public class 	CountryInfo{
	@SerializedName("flag")
	private String flag;

	@SerializedName("_id")
	private int id;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private float lat;

	@SerializedName("long")
	private float jsonMemberLong;

	@SerializedName("iso3")
	private String iso3;

	public String getFlag(){
		return flag;
	}

	public int getId(){
		return id;
	}

	public String getIso2(){
		return iso2;
	}

	public float getLat(){
		return lat;
	}

	public float getJsonMemberLong(){
		return jsonMemberLong;
	}

	public String getIso3(){
		return iso3;
	}
}