package com.example.team3_covid19;

import com.google.gson.annotations.SerializedName;

public class CovidData {

	public int id;

	@SerializedName("continent")
	public String continent;

	@SerializedName("country")
	public String country;

	@SerializedName("recoveredPerOneMillion")
	public double recoveredPerOneMillion;

	@SerializedName("cases")
	public String cases;

	@SerializedName("critical")
	public int critical;

	@SerializedName("oneCasePerPeople")
	public int oneCasePerPeople;

	@SerializedName("active")
	public int active;

	@SerializedName("testsPerOneMillion")
	public int testsPerOneMillion;

	@SerializedName("population")
	public int population;

	@SerializedName("oneDeathPerPeople")
	public int oneDeathPerPeople;

	@SerializedName("recovered")
	public int recovered;

	@SerializedName("oneTestPerPeople")
	public int oneTestPerPeople;

	@SerializedName("tests")
	public int tests;

	@SerializedName("criticalPerOneMillion")
	public double criticalPerOneMillion;

	@SerializedName("deathsPerOneMillion")
	public int deathsPerOneMillion;

	@SerializedName("todayRecovered")
	public int todayRecovered;

	@SerializedName("casesPerOneMillion")
	public int casesPerOneMillion;

	@SerializedName("countryInfo")
	public CountryInfo countryInfo;

	@SerializedName("updated")
	public long updated;

	@SerializedName("deaths")
	public int deaths;

	@SerializedName("activePerOneMillion")
	public double activePerOneMillion;

	@SerializedName("todayCases")
	public int todayCases;

	@SerializedName("todayDeaths")
	public int todayDeaths;

	public String getContinent(){
		return continent;
	}

	public String getCountry(){
		return country;
	}

	public double getRecoveredPerOneMillion(){
		return recoveredPerOneMillion;
	}

	public String getCases(){
		return cases;
	}

	public int getCritical(){
		return critical;
	}

	public int getOneCasePerPeople(){
		return oneCasePerPeople;
	}

	public int getActive(){
		return active;
	}

	public int getTestsPerOneMillion(){
		return testsPerOneMillion;
	}

	public int getPopulation(){
		return population;
	}

	public int getOneDeathPerPeople(){
		return oneDeathPerPeople;
	}

	public int getRecovered(){
		return recovered;
	}

	public int getOneTestPerPeople(){
		return oneTestPerPeople;
	}

	public int getTests(){
		return tests;
	}

	public double getCriticalPerOneMillion(){
		return criticalPerOneMillion;
	}

	public int getDeathsPerOneMillion(){
		return deathsPerOneMillion;
	}

	public int getTodayRecovered(){
		return todayRecovered;
	}

	public int getCasesPerOneMillion(){
		return casesPerOneMillion;
	}

	public CountryInfo getCountryInfo(){
		return countryInfo;
	}

	public long getUpdated(){
		return updated;
	}

	public int getDeaths(){
		return deaths;
	}

	public double getActivePerOneMillion(){
		return activePerOneMillion;
	}

	public int getTodayCases(){
		return todayCases;
	}

	public int getTodayDeaths(){
		return todayDeaths;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setRecoveredPerOneMillion(double recoveredPerOneMillion) {
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}

	public void setCases(String cases) {
		this.cases = cases;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public void setOneCasePerPeople(int oneCasePerPeople) {
		this.oneCasePerPeople = oneCasePerPeople;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setTestsPerOneMillion(int testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setOneDeathPerPeople(int oneDeathPerPeople) {
		this.oneDeathPerPeople = oneDeathPerPeople;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public void setOneTestPerPeople(int oneTestPerPeople) {
		this.oneTestPerPeople = oneTestPerPeople;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	public void setCriticalPerOneMillion(double criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}

	public void setDeathsPerOneMillion(int deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	public void setTodayRecovered(int todayRecovered) {
		this.todayRecovered = todayRecovered;
	}

	public void setCasesPerOneMillion(int casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public void setUpdated(long updated) {
		this.updated = updated;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void setActivePerOneMillion(double activePerOneMillion) {
		this.activePerOneMillion = activePerOneMillion;
	}

	public void setTodayCases(int todayCases) {
		this.todayCases = todayCases;
	}

	public void setTodayDeaths(int todayDeaths) {
		this.todayDeaths = todayDeaths;
	}

	public CovidData(String country, String cases) {
		this.country = country;
		this.cases = cases;
	}

	public CovidData() {

	}
}