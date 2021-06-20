package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/28/20.
 */
public class GroupShiftedStrings {

  HashMap<String, Integer> count = new HashMap<>();

  public List<List<String>> groupStrings(String[] strings) {

    List<List<String>> ans = new ArrayList<>();
    Set<String> set = new HashSet<>();

    for (int i = 0; i < strings.length; i++) {
      set.add(strings[i]);
      count.put(strings[i], count.getOrDefault(strings[i], 0) + 1);
    }

    HashSet<String> visited = new HashSet<>();

    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    HashMap<Character, Character> map = new HashMap<>();
    for (int i = 0; i < alphabet.length(); i++) {
      if (i + 1 < alphabet.length()) {
        map.put(alphabet.charAt(i), alphabet.charAt(i + 1));
      } else {
        map.put(alphabet.charAt(i), 'a');
      }
    }

    HashMap<String, List<String>> hold = new HashMap<>();
    Set<String> seen = new HashSet<>();

    for (int i = 0; i < strings.length; i++) {

      if (!visited.contains(strings[i])) {
        hold.put(strings[i], new ArrayList<>());
        dfs(strings[i], strings[i], set, visited, hold, map);
      } else if (count.get(strings[i]) > 0) {
        for (List<String> list : hold.values()) {
          if (list.contains(strings[i])) {
            count.put(strings[i], count.get(strings[i]) - 1);
            list.add(strings[i]);
            break;
          }
        }
      }
      seen.add(strings[i]);
    }
    for (List<String> list : hold.values()) {
      ans.add(list);
    }
    return ans;
  }

  private void dfs(String start, String string, Set<String> set, HashSet<String> visited, HashMap<String, List<String>> hold,
      HashMap<Character, Character> map) {
    visited.add(string);
    StringBuffer buffer = new StringBuffer(string);
    for (int i = 0; i < buffer.length(); i++) {
      buffer.setCharAt(i, map.get(buffer.charAt(i)));
    }
    String s = buffer.toString();
    if (set.contains(s)) {
      int val = count.get(s);
      if (val - 1 >= 0) {
        count.put(s, val - 1);
      }
      hold.get(start).add(s);
    }
    if (!visited.contains(s)) {
      dfs(start, s, set, visited, hold, map);
    }
  }

  public static void main(String args[]) {
    GroupShiftedStrings g = new GroupShiftedStrings();
    g.groupStrings(
        new String[]{"xzv", "tvhmtraayghfabutim", "scruzaydxrapcuv", "xiznzqqjbetchvhndzz", "qmbkbjpkxzmnwvo", "tttwsszaetyzgsaprv",
            "xdhbxxxomhagohdvdyv", "khugdmdbbqjbeuxq", "xzzqnowcyplkdxgkbbb", "daxzqsdcfuymlyevyfnayb", "nrlalrdjqqckogfqlhfhg",
            "akpellndulbbbipbvatbu", "pfqeu", "dneng", "rjnvgxk", "eujibxcxetdjztn", "aufkyfjuruaq", "izeqjoftsfzjkujiubkrb", "dwzuyrospzjzuckk",
            "kcuxsetkmzkq", "lvej", "lzefktml", "varaxwjxf", "cwdqxivcxeyzhsvqccy", "kzxlrqbabvtvkfajbjatzyix", "husuheaiirzowtiadgtxwf", "swtja",
            "ddvxhgesuenqdgutm", "uaduv", "t", "yvwfcproumulvnbva", "ddjikfsnasyj", "tqxiqlqkxbmwuytempvg", "yihridsxajkiyehmdbru", "pbxvdoleubrdd",
            "smavxt", "azlrmjwmlsglgwrdakgn", "cf", "ohkaajphvrorgyx", "nll", "jlnlqzviffdmegnxig", "pqgkqrancnwaqrjovjlgqsfx",
            "azwaipyqlmgoeslujdkwuh", "jubooimlfis", "sosnjvqikiivlighyktd", "jhujjiiovhw", "jgbj", "qtrbilvqvy", "vlmkkilhsqlknvqpus", "omuucd",
            "ligpithabxjqtuaf", "mz", "ognmihotoqgprvbt", "nmgwkocmeezb", "qmiajlowymr", "xmlmolj", "cwciqqpbmzzcgmnrvgucezor", "ng",
            "lqglykvsxhobqrppeqrlp", "ywuytvrproahtgro", "etcnjqg", "bogjahrw", "rrrkddajfq", "nwcraujocpk", "yo", "nhasjwodqyququhhfkyv",
            "tpalgbgirzrl", "jjgcsekxxi", "r", "jgxxttnlhez", "ha", "jxklqfdizkgzusrzs", "hnjctughrsdiyezcqsmij", "tvpawacxepvq", "lwv",
            "rspgmumnbrzk", "gqkdxbkylirxxqrhh", "wsbvir", "fjktj", "rulvjpezzonnbr", "kxntnopkkjgliltrlklfgc", "h", "uplapswlksu",
            "cevzkpfcbviliulexyx", "mvbwfqqbeisdsjdnn", "giavjwaftarskv", "trlhxmqayeiero", "yfazdiayalzrnf", "qmolgaezbwuodbdhsxclhzi", "nskgjmut",
            "gccpj", "psmqmdceprjoiw", "dhvqyqvnh", "jcxq", "quvi", "pvqdzzwv", "yzksz", "vnicvmnfwwwmzhsdsm", "gn", "jcyfpawtcnuikudwuijpd",
            "mmncorzbnoawyo", "cwygpjjmrmslabafcmwdhpdo", "nikwaflxpepnkz", "qmylnzkhz", "oc", "jnyibizgmwjqjvhbg", "ajhaejn", "eulboowdyd",
            "vyjfniqjoepdbzulvpgjic", "oauvt", "vpnmjbnkf", "izk", "sanouetznnk", "truufmplrmsxgvrtl", "yuabbnxybbpfgblou", "yjyawflsvetbdpcgbjta",
            "cmozvlftrhtxmjqkorxfrql", "nbyvnbhjlofqdtilvplcdks", "nxmyafxvrfhbuzpzixt", "lxtoignhwtcbsi", "cycu", "rlzjrirwfhwdvolixa", "itle",
            "nmitv", "cno", "mnwjytgktowostuzvrcjxr", "etus", "othjqfpysicbdhfezxpvhjd", "nzypfebzbsaakoky", "uimwaktcywogwptihvrrh", "pgc",
            "njwftpurketzvjbzivanz", "olnrizftjnjixeysi", "k", "fbwkolpka", "vqcxxbcumypdbetrknavgm", "ivfnyhqqnjdmdwqslhk",
            "fusfadhqfruriiglvqzuns",
            "r", "sghqrdaelollqmczagojceys"}).forEach(strings -> {
      if (strings.size() > 1) {
        System.out.println(strings);
      }
    });
  }


}
