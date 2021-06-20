package com.airbase;

import static org.junit.Assert.assertEquals;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.joda.time.DateTime;
import org.junit.Test;

public class PaymentAmortizerTest {


  private int getMonthsBetween(DateTime startTime, DateTime endTime) {
    if (endTime.year().get() == startTime.year().get()) {
      return endTime.getMonthOfYear() - startTime.getMonthOfYear() + 1;
    } else {
      return ((endTime.year().get() - startTime.year().get()) * 12) + (endTime.getMonthOfYear() - startTime.getMonthOfYear() + 1);
    }
  }

  @Test
  public void testWithRandomDateTimes() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();
    for (int i = 0; i < 10000; i++) {
      int startYear = getRandIntBetween(1970, 2020);
      int endYear = getRandIntBetween(1970, 2020);
      int startMonth = getRandIntBetween(1, 12);
      int endMonth = getRandIntBetween(1, 12);

      int cents = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
      if (endYear >= startYear) {
        DateTime start = new DateTime().withYear(startYear).withMonthOfYear(startMonth);
        start = start
            .withDayOfMonth(getRandIntBetween(start.dayOfMonth().getMinimumValue(), start.dayOfMonth().getMaximumValue()));
        DateTime end = new DateTime().withYear(endYear).withMonthOfYear(endMonth);
        end = end.withDayOfMonth(getRandIntBetween(end.dayOfMonth().getMinimumValue(), end.dayOfMonth().getMaximumValue()));
        if (end.isAfter(start)) {
          List<Integer> results = payMentAmortizer.amortize(cents, start, end);
          IntSummaryStatistics stats = results.stream().collect(Collectors.summarizingInt(value -> value));
          assertEquals(results.size(), getMonthsBetween(start, end));
          assertEquals(stats.getSum(), cents);
        }
      }
    }
  }

  private int getRandIntBetween(int i, int i2) {
    return ThreadLocalRandom.current().nextInt(i, i2 + 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEndTimeLessThanStartTime() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 1234567;
    DateTime startTime = new DateTime().withYear(2021).withMonthOfYear(1).withDayOfMonth(1);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(8).withDayOfMonth(31);
    payMentAmortizer.amortize(cents, startTime, endTime);

  }

  @Test
  public void testAmortizerWithStartAndEndFull() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 1234567;
    DateTime startTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(1);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(8).withDayOfMonth(31);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), 8);
    assertEquals(stats.getSum(), cents);
  }

  @Test
  public void testAmortizeWithStartFullAndEndPartial() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2015).withMonthOfYear(1).withDayOfMonth(1);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(8).withDayOfMonth(7);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));
    assertEquals(result.size(), 68);
    assertEquals(stats.getSum(), cents);

  }

  @Test
  public void testAmortizeWithStartPartialAndEndFull() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2010).withMonthOfYear(1).withDayOfMonth(15);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(8).withDayOfMonth(31);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), 128);
    assertEquals(stats.getSum(), cents);

  }


  @Test
  public void testAmortizeWithStartPartialAndEndFullTwoMonths() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(2);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(2).withDayOfMonth(26);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), 2);
    assertEquals(stats.getSum(), cents);

  }


  @Test
  public void testAmortizeWithStartPartialAndEndFullOneMonth() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(2);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(31);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), 1);
    assertEquals(stats.getSum(), cents);

  }

  // Additional tests

  @Test
  public void test2() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(1);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(2).withDayOfMonth(1);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), 2);
    assertEquals(stats.getSum(), cents);

  }

  @Test
  public void test3() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();
    DateTime startTime = new DateTime(476548810492L);
    DateTime endTime = new DateTime(483547210492L);
    int cents = 1372052798;
    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));

    assertEquals(result.size(), getMonthsBetween(startTime, endTime));
    assertEquals(stats.getSum(), cents);
  }

  @Test
  public void test4() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();
    DateTime startTime = new DateTime(83862467995L);
    DateTime endTime = new DateTime(1153580867995L);
    int cents = 1118645102;
    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));
    assertEquals(result.size(), getMonthsBetween(startTime, endTime));
    assertEquals(stats.getSum(), cents);
  }

  @Test
  public void test5() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();
    DateTime startTime = new DateTime().withDayOfMonth(15).withMonthOfYear(1);
    DateTime endTime = new DateTime().withDayOfMonth(15).withMonthOfYear(3);
    int cents = 120003;
    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));
    assertEquals(result.size(), getMonthsBetween(startTime, endTime));
    assertEquals(stats.getSum(), cents);
  }

  @Test
  public void test6() throws Exception {
    PaymentAmortizer payMentAmortizer = new PaymentAmortizer();

    int cents = 123456;
    DateTime startTime = new DateTime().withYear(2020).withMonthOfYear(1).withDayOfMonth(1);
    DateTime endTime = new DateTime().withYear(2020).withMonthOfYear(8).withDayOfMonth(31);

    List<Integer> result = payMentAmortizer.amortize(cents, startTime, endTime);
    IntSummaryStatistics stats = result.stream().collect(Collectors.summarizingInt(value -> value));
    //System.out.println(result);

    assertEquals(result.size(), 8);
    assertEquals(stats.getSum(), cents);

  }

}
