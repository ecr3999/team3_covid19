package com.example.team3_covid19;

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
    public String countryInfoId;
    @ColumnInfo(name = "countryInfoIso2")
    public String countryInfoIso2;
    @ColumnInfo(name = "countryInfoLat")
    public String countryInfoLat;
    @ColumnInfo(name = "countryInfoLong")
    public String countryInfoLong;
    @ColumnInfo(name = "countryInfoIso3")
    public String countryInfoIso3;

    @ColumnInfo(name = "cases")
    public String cases;

    @ColumnInfo(name = "today_cases")
    public String todayCases;

    @ColumnInfo(name = "deaths")
    public String deaths;

    @ColumnInfo(name = "today_deaths")
    public String todayDeaths;

    @ColumnInfo(name = "recovered")
    public String recovered;

    @ColumnInfo(name = "today_recovered")
    public String todayRecovered;

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

    public Data(String country, String cases) {
        this.country = country;
        this.cases = cases;
    }

    public Data() {

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
                ", country='" + country + '\'' +
                ", cases='" + cases + '\'' +
                '}';
    }
}
