package com.example.team3_covid19.CovidMenu.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "updated")
    public String updated;

    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "countryInfoFlag")
    public String countryInfoFlag;
    @ColumnInfo(name = "countryInfoId")
    public int countryInfoId;
    @ColumnInfo(name = "countryInfoIso2")
    public String countryInfoIso2;
    @ColumnInfo(name = "countryInfoLat")
    public float countryInfoLat;
    @ColumnInfo(name = "countryInfoLong")
    public float countryInfoLong;
    @ColumnInfo(name = "countryInfoIso3")
    public String countryInfoIso3;

    @ColumnInfo(name = "cases")
    public String cases;

    @ColumnInfo(name = "today_cases")
    public int todayCases;

    @ColumnInfo(name = "deaths")
    public int deaths;

    @ColumnInfo(name = "today_deaths")
    public int todayDeaths;

    @ColumnInfo(name = "recovered")
    public int recovered;

    @ColumnInfo(name = "today_recovered")
    public int todayRecovered;

    @ColumnInfo(name = "active")
    public String active;

    @ColumnInfo(name = "critical")
    public String critical;

    @ColumnInfo(name = "cases_per_one_million")
    public String casesPerOneMillion;

    @ColumnInfo(name = "deaths_per_one_million")
    public String deathsPerOneMillion;

    @ColumnInfo(name = "tests")
    public String tests;

    @ColumnInfo(name = "tests_per_one_million")
    public String testsPerOneMillion;

    @ColumnInfo(name = "population")
    public String population;

    @ColumnInfo(name = "continent")
    public String continent;

    @ColumnInfo(name = "one_case_per_people")
    public String oneCasePerPeople;

    @ColumnInfo(name = "one_death_per_people")
    public String oneDeathPerPeople;

    @ColumnInfo(name = "one_test_per_people")
    public String oneTestPerPeople;

    @ColumnInfo(name = "active_per_one_million")
    public String activePerOneMillion;

    @ColumnInfo(name = "recovered_per_one_million")
    public String recoveredPerOneMillion;

    @ColumnInfo(name = "critical_per_one_million")
    public String criticalPerOneMillion;

    public int getId() {
        return id;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryInfoFlag() {
        return countryInfoFlag;
    }

    public int getCountryInfoId() {
        return countryInfoId;
    }

    public String getCountryInfoIso2() {
        return countryInfoIso2;
    }

    public float getCountryInfoLat() {
        return countryInfoLat;
    }

    public float getCountryInfoLong() {
        return countryInfoLong;
    }

    public String getCountryInfoIso3() {
        return countryInfoIso3;
    }

    public String getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public String getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getTests() {
        return tests;
    }

    public String getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public String getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public String getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public String getOneDeathPerPeople() {
        return oneDeathPerPeople;
    }

    public String getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public String getActivePerOneMillion() {
        return activePerOneMillion;
    }

    public String getRecoveredPerOneMillion() {
        return recoveredPerOneMillion;
    }

    public String getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryInfoFlag(String countryInfoFlag) {
        this.countryInfoFlag = countryInfoFlag;
    }

    public void setCountryInfoId(int countryInfoId) {
        this.countryInfoId = countryInfoId;
    }

    public void setCountryInfoIso2(String countryInfoIso2) {
        this.countryInfoIso2 = countryInfoIso2;
    }

    public void setCountryInfoLat(float countryInfoLat) {
        this.countryInfoLat = countryInfoLat;
    }

    public void setCountryInfoLong(float countryInfoLong) {
        this.countryInfoLong = countryInfoLong;
    }

    public void setCountryInfoIso3(String countryInfoIso3) {
        this.countryInfoIso3 = countryInfoIso3;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void setTodayRecovered(int todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public void setCasesPerOneMillion(String casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public void setDeathsPerOneMillion(String deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public void setTestsPerOneMillion(String testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setOneCasePerPeople(String oneCasePerPeople) {
        this.oneCasePerPeople = oneCasePerPeople;
    }

    public void setOneDeathPerPeople(String oneDeathPerPeople) {
        this.oneDeathPerPeople = oneDeathPerPeople;
    }

    public void setOneTestPerPeople(String oneTestPerPeople) {
        this.oneTestPerPeople = oneTestPerPeople;
    }

    public void setActivePerOneMillion(String activePerOneMillion) {
        this.activePerOneMillion = activePerOneMillion;
    }

    public void setRecoveredPerOneMillion(String recoveredPerOneMillion) {
        this.recoveredPerOneMillion = recoveredPerOneMillion;
    }

    public void setCriticalPerOneMillion(String criticalPerOneMillion) {
        this.criticalPerOneMillion = criticalPerOneMillion;
    }

    public Data(String country, String cases) {
        this.country = country;
        this.cases = cases;
    }

    public Data() {

    }

    public Data(String stringExtra) {
    }
/*public Data(int id, String updated, String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String critical, String casesPerOneMillion, String deathsPerOneMillion, String tests, String testsPerOneMillion, String population, String continent, String oneCasePerPeople, String oneDeathPerPeople, String oneTestPerPeople, String activePerOneMillion, String recoveredPerOneMillion, String criticalPerOneMillion) {
        this.id = id;
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.tests = tests;
        this.testsPerOneMillion = testsPerOneMillion;
        this.population = population;
        this.continent = continent;
        this.oneCasePerPeople = oneCasePerPeople;
        this.oneDeathPerPeople = oneDeathPerPeople;
        this.oneTestPerPeople = oneTestPerPeople;
        this.activePerOneMillion = activePerOneMillion;
        this.recoveredPerOneMillion = recoveredPerOneMillion;
        this.criticalPerOneMillion = criticalPerOneMillion;
    }*/

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", updated='" + updated + '\'' +
                ", country='" + country + '\'' +
                ", countryInfoFlag='" + countryInfoFlag + '\'' +
                ", countryInfoId='" + countryInfoId + '\'' +
                ", countryInfoIso2='" + countryInfoIso2 + '\'' +
                ", countryInfoLat='" + countryInfoLat + '\'' +
                ", countryInfoLong='" + countryInfoLong + '\'' +
                ", countryInfoIso3='" + countryInfoIso3 + '\'' +
                ", cases='" + cases + '\'' +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", todayRecovered=" + todayRecovered +
                ", active='" + active + '\'' +
                ", critical='" + critical + '\'' +
                ", casesPerOneMillion='" + casesPerOneMillion + '\'' +
                ", deathsPerOneMillion='" + deathsPerOneMillion + '\'' +
                ", tests='" + tests + '\'' +
                ", testsPerOneMillion='" + testsPerOneMillion + '\'' +
                ", population='" + population + '\'' +
                ", continent='" + continent + '\'' +
                ", oneCasePerPeople='" + oneCasePerPeople + '\'' +
                ", oneDeathPerPeople='" + oneDeathPerPeople + '\'' +
                ", oneTestPerPeople='" + oneTestPerPeople + '\'' +
                ", activePerOneMillion='" + activePerOneMillion + '\'' +
                ", recoveredPerOneMillion='" + recoveredPerOneMillion + '\'' +
                ", criticalPerOneMillion='" + criticalPerOneMillion + '\'' +
                '}';
    }
}
