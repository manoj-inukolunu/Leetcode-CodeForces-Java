package com.library;

import com.google.common.base.Stopwatch;

public class Strings {

    public static long power(long num, int pow, int mod) {
        long res = 1;
        num = num % mod;
        while (pow != 0) {
            if ((pow & 1) != 0) {
                res = (res * num) % mod;
            }
            pow >>= 1;
            num = (num * num) % mod;
        }
        return res;
    }

    public static long getHash(long[] hash, long[] inv, int start, int end, int mod) {
        if (start > 0) {
            return ((mod + (hash[end] - hash[start - 1])) * inv[start]) % mod;
        }
        return ((mod + (hash[end])) * inv[start]) % mod;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        int p = 31;
        int mod = (int) Math.pow(10, 9) + 7;
        String str = "codingasdfdingasdfasdfdingasdfasdfjahdlkjfhalsdkfhaskdjfhding;alksjdf";
        int len = (int) 1e5;
        System.out.println(len);
        long[] pow = new long[len];
        long[] inv = new long[len];
        inv[0] = 1;
        pow[0] = 1;
        for (int i = 1; i < str.length(); i++) {
            pow[i] = pow[i - 1] * p;
            pow[i] %= mod;
            inv[i] = power(pow[i], mod - 2, mod);/*BigInteger.valueOf(pow[i]).modInverse(BigInteger.valueOf(mod))
                    .longValue();*/
        }
        long hash = 0;
        long[] hashArr = new long[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hash = hash + (ch - 'a' + 1) * pow[i];
            hash %= mod;
            hashArr[i] = hash;
        }
        System.out.println("getHash(hashArr,inv,2,5,mod) = " + getHash(hashArr, inv, 2, 5, mod));
        System.out.println("getHash(hashArr,inv,10,13,mod) = " + getHash(hashArr, inv, 10, 13, mod));
        System.out.println("getHash(hashArr,inv,22,25,mod) = " + getHash(hashArr, inv, 22, 25, mod));
        System.out.println("getHash(hashArr,inv,22,25,mod) = " + getHash(hashArr, inv, 57, 60, mod));
        System.out.println(str.lastIndexOf("ding"));
    }
}






