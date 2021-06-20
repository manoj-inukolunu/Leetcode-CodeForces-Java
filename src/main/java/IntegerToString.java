import java.util.HashMap;
import java.util.LinkedHashMap;

public class IntegerToString {
  // 1 4 8 2 3 5
  //10000000
  //12345678


  LinkedHashMap<Integer, String> divisorMap = new LinkedHashMap<>();
  HashMap<Integer, String> tensMap = new HashMap<>();
  HashMap<Integer, String> digitMap = new HashMap<>();

  public IntegerToString() {
    buildMap();
  }

  private void buildMap() {
    divisorMap.put(10000000, " crore ");
    divisorMap.put(100000, " lakh ");
    divisorMap.put(1000, " thousand ");
    divisorMap.put(100, " hundred ");
    tensMap.put(20, "twenty");
    tensMap.put(30, "thirty");
    tensMap.put(40, "forty");
    digitMap.put(1, "one");
    digitMap.put(2, "two");
    digitMap.put(3, "three");
    digitMap.put(4, "four");
    digitMap.put(5, "five");
    digitMap.put(6, "six");
    digitMap.put(7, "seven");
    digitMap.put(8, "eight");
    digitMap.put(9, "nine");
  }

  private String twoDigitConvert(long num) {
    if (num == 0) {
      return "";
    }
    return "" + num;
  }


  public String convert(long num) {
    StringBuffer buffer = new StringBuffer();
    for (int key : divisorMap.keySet()) {
      long val = num / key;
      if (val < 100) {
        buffer.append(twoDigitConvert(val));
      } else {
        String converted = convert(val);
        buffer.append(converted);
      }
      if (val != 0) {
        buffer.append(divisorMap.get(key));
      }
      num = num % key;
    }
    buffer.append(twoDigitConvert(num));
    return buffer.toString();
  }


  public static void main(String args[]) {
    IntegerToString i = new IntegerToString();
    java.lang.System.out.println(i.convert(12222345600L));
  }

}
