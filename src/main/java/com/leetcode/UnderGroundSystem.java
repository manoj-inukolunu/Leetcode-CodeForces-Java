package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 3/29/20.
 */
public class UnderGroundSystem {
	/*
	["underGroundSystem","checkIn","checkIn","checkOut","checkIn","getAverageTime","getAverageTime","checkOut","checkOut","checkIn","checkIn",
	"checkIn","checkOut","checkIn","getAverageTime","checkOut","getAverageTime","checkOut","checkIn"]
[[],[653777,"V3BOL9LF",71],[34036,"EFB2ARPR",86],[34036,"SEKKQ7KR",169],[476790,"U54HBTYV",223],["EFB2ARPR","SEKKQ7KR"],["EFB2ARPR","SEKKQ7KR"],
[476790,"SEKKQ7KR",226],[653777,"K2618O72",255],[680009,"U54HBTYV",342],[691876,"1DTINDTE",352],[779975,"0QIA9CN3",374],[691876,"WGN1M5GY",412],
[18036,"ZSBKMUIV",467],["V3BOL9LF","K2618O72"],[779975,"W858PECF",485],["U54HBTYV","SEKKQ7KR"],[18036,"V6QXVVHS",516],[141921,"9GUC0EYJ",533]]
	 */

  public static void main(String args[]) {
    UnderGroundSystem underGroundSystem = new UnderGroundSystem();
    underGroundSystem.checkIn(45, "Leyton", 3);
    underGroundSystem.checkIn(32, "Paradise", 8);
    underGroundSystem.checkIn(27, "Leyton", 10);
    underGroundSystem.checkOut(45, "Waterloo", 15);
    underGroundSystem.checkOut(27, "Waterloo", 20);
    underGroundSystem.checkOut(32, "Cambridge", 22);
    System.out.println(underGroundSystem.getAverageTime("Paradise", "Cambridge"));       // return 14.0. There was only one travel from "Paradise"
    // (at time 8) to "Cambridge" (at time 22)
    System.out.println(underGroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.0. There were two travels from "Leyton" to
    // "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) +
    // (20-10) ) / 2 = 11.0
    underGroundSystem.checkIn(10, "Leyton", 24);
    System.out.println(underGroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.0
    underGroundSystem.checkOut(10, "Waterloo", 38);
    System.out.println(underGroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 12.0
  }


  class Data {

    int id;
    int time;

    public Data(int id, int time) {
      this.id = id;
      this.time = time;
    }
  }

  HashMap<String, List<Data>> checkInMap = new HashMap();
  HashMap<String, List<Data>> checkOutMap = new HashMap();


  Set<Integer> checkIn = new HashSet<>();

  public UnderGroundSystem() {

  }

  public void checkIn(int id, String stationName, int t) {
    checkIn.add(id);
    if (checkInMap.containsKey(stationName)) {
      List<Data> ints = checkInMap.get(stationName);
      ints.add(new Data(id, t));
      checkInMap.put(stationName, ints);
    } else {
      List<Data> ints = new ArrayList();
      ints.add(new Data(id, t));
      checkInMap.put(stationName, ints);
    }
  }

  public void checkOut(int id, String stationName, int t) {
    if (checkIn.contains(id)) {
      if (checkOutMap.containsKey(stationName)) {
        List<Data> ints = checkOutMap.get(stationName);
        ints.add(new Data(id, t));
        checkOutMap.put(stationName, ints);
      } else {
        List<Data> ints = new ArrayList();
        ints.add(new Data(id, t));
        checkOutMap.put(stationName, ints);
      }
    }

  }

  public double getAverageTime(String startStation, String endStation) {
    List<Data> checkInList = checkInMap.get(startStation);
    List<Data> checkOutList = checkOutMap.get(endStation);
    double sum = 0;
    int count = 0;
    for (int i = 0; i < checkOutList.size(); i++) {
      Data checkOutData = checkOutList.get(i);
      for (int j = 0; j < checkInList.size(); j++) {
        Data checkInData = checkInList.get(j);
        if (checkOutData.id == checkInData.id && checkOutData.time > checkInData.time) {
          sum += (checkOutData.time - checkInData.time);
          count++;
        }
      }
    }
    return ((double) sum) / count;
  }
}

/**
 * Your underGroundSystem object will be instantiated and called as such: underGroundSystem obj = new underGroundSystem();
 * obj.checkIn(id,stationName,t); obj.checkOut(id,stationName,t); double param_3 = obj.getAverageTime(startStation,endStation);
 */
