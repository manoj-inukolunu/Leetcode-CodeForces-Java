package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParallelBfs {

  ConcurrentHashMap<String, Boolean> ans = new ConcurrentHashMap();
  ExecutorService executor = Executors.newFixedThreadPool(1);
  ConcurrentHashMap<String, Boolean> visited = new ConcurrentHashMap<>();

  private void dfs(String url, HtmlParser htmlParser, String mainHostName) {
    System.out.println(url);
    ans.put(url, true);
    visited.put(url, true);
    for (String str : htmlParser.getUrls(url)) {
      if (!visited.containsKey(str) && getHostName(str).equalsIgnoreCase(mainHostName)) {
        Thread thread = new Thread(() -> dfs(str, htmlParser, mainHostName));
        thread.start();
        try {
          thread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public List<String> crawl(String startUrl, HtmlParser htmlParser) {
    String reqHostName = getHostName(startUrl);
    dfs(startUrl, htmlParser, reqHostName);
    executor.shutdown();
    System.out.println("Testing");
    return new ArrayList<>(ans.keySet());
  }

  public String getHostName(String url) {
    url = url.replace("http://", "");
    if (url.indexOf("/") != -1) {
      return url.substring(0, url.indexOf("/"));
    } else {
      return url;
    }
  }

  public static void main(String args[]) {
    ParallelBfs p = new ParallelBfs();

    List<String> str = Lists.newArrayList("http://sta.zizqt.xyz/naxi", "http://sta.zizqt.xyz/tcjk", "http://sta.zizqt.xyz/xurc", "http://sta.zizqt"
            + ".xyz/tyve", "http"
            + "://sta.zizqt.xyz/zely", "http://sta.zizqt.xyz/rgxw", "http://sta.zizqt.xyz/pqrg", "http://sta.zizqt.xyz/zity", "http://sta.zizqt"
            + ".xyz/byjq",
        "http://sta.zizqt.xyz/zmlw", "http://sta.zizqt.xyz/dqdi", "http://sta.zizqt.xyz/xwfa", "http://sta.zizqt.xyz/pqbc",
        "http://sta.zizqt.xyz/dchy"
        , "http://sta.zizqt.xyz/rmdq", "http://sta.zizqt.xyz/xyhu", "http://sta.zizqt.xyz/zqbw", "http://sta.zizqt.xyz/ncly",
        "http://sta.zizqt.xyz/lozw", "http://sta.zizqt.xyz/divm", "http://sta.zizqt.xyz/fqpa", "http://sta.zizqt.xyz/notq",
        "http://sta.zizqt.xyz/bgxq", "http://sta.zizqt.xyz/fmxq", "http://sta.zizqt.xyz/vezw", "http://sta.zizqt.xyz/hgxa",
        "http://sta.zizqt.xyz/vqra", "http://sta.zizqt.xyz/xmtm", "http://sta.zizqt.xyz/vgnq", "http://sta.zizqt.xyz/tepq",
        "http://sta.zizqt.xyz/vyre", "http://sta.zizqt.xyz/ruvm", "http://sta.zizqt.xyz/hmbu", "http://sta.zizqt.xyz/pmlk",
        "http://sta.zizqt.xyz/dqvu", "http://sta.zizqt.xyz/vkfs", "http://sta.zizqt.xyz/bgho", "http://sta.zizqt.xyz/vqbo",
        "http://sta.zizqt.xyz/tslu", "http://sta.zizqt.xyz/hmbg", "http://sta.zizqt.xyz/julk", "http://sta.zizqt.xyz/fmhq",
        "http://sta.zizqt.xyz/jwrc", "http://sta.zizqt.xyz/naju", "http://sta.zizqt.xyz/nyzk", "http://sta.zizqt.xyz/novi",
        "http://sta.zizqt.xyz/fcvo", "http://sta.zizqt.xyz/patg", "http://sta.zizqt.xyz/ngvk", "http://sta.zizqt.xyz/vkvi",
        "http://sta.zizqt.xyz/psza", "http://sta.zizqt.xyz/fybq", "http://sta.zizqt.xyz/xgje", "http://sta.zizqt.xyz/lcva",
        "http://sta.zizqt.xyz/pyva", "http://sta.zizqt.xyz/ripm", "http://sta.zizqt.xyz/zahs", "http://sta.zizqt.xyz/xmvy",
        "http://sta.zizqt.xyz/pkjg", "http://sta.zizqt.xyz/tebo", "http://sta.zizqt.xyz/turu", "http://sta.zizqt.xyz/fujg",
        "http://sta.zizqt.xyz/dihm", "http://sta.zizqt.xyz/hwhi", "http://sta.zizqt.xyz/pyvs", "http://sta.zizqt.xyz/lqpw",
        "http://sta.zizqt.xyz/hspq", "http://sta.zizqt.xyz/tmbo", "http://sta.zizqt.xyz/xojy", "http://sta.zizqt.xyz/fadm",
        "http://sta.zizqt.xyz/hufa", "http://sta.zizqt.xyz/pqdu", "http://sta.zizqt.xyz/nedc", "http://sta.zizqt.xyz/lcvs",
        "http://sta.zizqt.xyz/xevi", "http://sta.zizqt.xyz/tcna", "http://sta.zizqt.xyz/zgto", "http://sta.zizqt.xyz/bexa",
        "http://sta.zizqt.xyz/xoze", "http://sta.zizqt.xyz/rkta", "http://sta.zizqt.xyz/zgfc", "http://sta.zizqt.xyz/zers",
        "http://sta.zizqt.xyz/venm", "http://sta.zizqt.xyz/xapu", "http://sta.zizqt.xyz/zgfo", "http://sta.zizqt.xyz/tetu",
        "http://sta.zizqt.xyz/jwdw", "http://sta.zizqt.xyz/xapy", "http://sta.zizqt.xyz/hwjq", "http://sta.zizqt.xyz/fytu",
        "http://sta.zizqt.xyz/taly", "http://sta.zizqt.xyz/dqjo", "http://sta.zizqt.xyz/zgfy", "http://sta.zizqt.xyz/xkdq",
        "http://sta.zizqt.xyz/dwvy", "http://sta.zizqt.xyz/fuls", "http://sta.zizqt.xyz/bqre", "http://sta.zizqt.xyz/jsnk",
        "http://sta.zizqt.xyz/fyvg", "http://sta.zizqt.xyz/dilo", "http://sta.zizqt.xyz/lopo", "http://sta.zizqt.xyz/lgps",
        "http://sta.zizqt.xyz/jerw", "http://sta.zizqt.xyz/fafe", "http://sta.zizqt.xyz/jizs", "http://sta.zizqt.xyz/fiva",
        "http://sta.zizqt.xyz/pgvi", "http://sta.zizqt.xyz/rgpe", "http://sta.zizqt.xyz/pcni", "http://sta.zizqt.xyz/vahq",
        "http://sta.zizqt.xyz/tgzy", "http://sta.zizqt.xyz/dohw", "http://sta.zizqt.xyz/vyxm", "http://sta.zizqt.xyz/xono",
        "http://sta.zizqt.xyz/twzm", "http://sta.zizqt.xyz/xejs", "http://sta.zizqt.xyz/zwxy", "http://sta.zizqt.xyz/bcfy",
        "http://sta.zizqt.xyz/vgds", "http://sta.zizqt.xyz/pmrs", "http://sta.zizqt.xyz/jofs", "http://sta.zizqt.xyz/piju",
        "http://sta.zizqt.xyz/honm", "http://sta.zizqt.xyz/jmty", "http://sta.zizqt.xyz/twhs", "http://sta.zizqt.xyz/dsri",
        "http://sta.zizqt.xyz/vaxa", "http://sta.zizqt.xyz/vwbu", "http://sta.zizqt.xyz/nehq", "http://sta.zizqt.xyz/batq",
        "http://sta.zizqt.xyz/hezs", "http://sta.zizqt.xyz/vixg", "http://sta.zizqt.xyz/dana", "http://sta.zizqt.xyz/paja",
        "http://sta.zizqt.xyz/xirg", "http://sta.zizqt.xyz/rafa", "http://sta.zizqt.xyz/tipe", "http://sta.zizqt.xyz/xsha",
        "http://sta.zizqt.xyz/zuvm", "http://sta.zizqt.xyz/hsfu", "http://sta.zizqt.xyz/vurk", "http://sta.zizqt.xyz/lobw",
        "http://sta.zizqt.xyz/typg", "http://sta.zizqt.xyz/pedk", "http://sta.zizqt.xyz/rkjm", "http://sta.zizqt.xyz/vyzq",
        "http://sta.zizqt.xyz/xsxw", "http://sta.zizqt.xyz/fyhk", "http://sta.zizqt.xyz/vmbo", "http://sta.zizqt.xyz/rory",
        "http://sta.zizqt.xyz/juty", "http://sta.zizqt.xyz/johs", "http://sta.zizqt.xyz/judc", "http://sta.zizqt.xyz/rkjy",
        "http://sta.zizqt.xyz/xsfw", "http://sta.zizqt.xyz/tojy", "http://sta.zizqt.xyz/dglo", "http://sta.zizqt.xyz/fwva",
        "http://sta.zizqt.xyz/bczs", "http://sta.zizqt.xyz/pubq", "http://sta.zizqt.xyz/hchg", "http://sta.zizqt.xyz/lyxe",
        "http://sta.zizqt.xyz/dcts", "http://sta.zizqt.xyz/fajm", "http://sta.zizqt.xyz/bcja", "http://sta.zizqt.xyz/nuze",
        "http://sta.zizqt.xyz/nmzg", "http://sta.zizqt.xyz/vetq", "http://sta.zizqt.xyz/xqvg", "http://sta.zizqt.xyz/pcba",
        "http://sta.zizqt.xyz/xkjk", "http://sta.zizqt.xyz/tabg", "http://sta.zizqt.xyz/fwfu", "http://sta.zizqt.xyz/xsji",
        "http://sta.zizqt.xyz/zehy", "http://sta.zizqt.xyz/fubi", "http://sta.zizqt.xyz/bgbg", "http://sta.zizqt.xyz/bszs",
        "http://sta.zizqt.xyz/vudg", "http://sta.zizqt.xyz/dsde", "http://sta.zizqt.xyz/bsjg", "http://sta.zizqt.xyz/vohu",
        "http://sta.zizqt.xyz/pedy", "http://sta.zizqt.xyz/nyto", "http://sta.zizqt.xyz/bahq", "http://sta.zizqt.xyz/dcvs",
        "http://sta.zizqt.xyz/jktq", "http://sta.zizqt.xyz/jqpg", "http://sta.zizqt.xyz/nelq", "http://sta.zizqt.xyz/hgrk",
        "http://sta.zizqt.xyz/xafa", "http://sta.zizqt.xyz/fete", "http://sta.zizqt.xyz/diby", "http://sta.zizqt.xyz/telm",
        "http://sta.zizqt.xyz/zejw", "http://sta.zizqt.xyz/lofy", "http://sta.zizqt.xyz/byhc", "http://sta.zizqt.xyz/xahy",
        "http://sta.zizqt.xyz/xodm", "http://sta.zizqt.xyz/fudk", "http://sta.zizqt.xyz/donq", "http://sta.zizqt.xyz/nmlw",
        "http://sta.zizqt.xyz/berq", "http://sta.zizqt.xyz/xqfw", "http://sta.zizqt.xyz/jyrc", "http://sta.zizqt.xyz/lwxg",
        "http://sta.zizqt.xyz/lqla", "http://sta.zizqt.xyz/psbw", "http://sta.zizqt.xyz/hoti", "http://sta.zizqt.xyz/zgnc");

    System.out.println(str.stream().collect(Collectors.joining("|||")));
  }

}
