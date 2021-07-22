package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC1912 {
    class MovieRentingSystem {

        TreeMap<Integer, TreeSet<Movie>> unRentedMap = new TreeMap<>();
        HashMap<Movie, Integer> priceMap = new HashMap<>();
        TreeSet<Movie> unRentedSet = new TreeSet<>((o1, o2) -> {
            if (o1.price == o2.price) {
                if (o1.shop == o2.shop) {
                    return Integer.compare(o1.movie, o2.movie);
                }
                return Integer.compare(o1.shop, o2.shop);
            }
            return Integer.compare(o1.price, o2.price);
        });
        TreeSet<Movie> rentedSet = new TreeSet<>((o1, o2) -> {
            if (o1.price == o2.price) {
                if (o1.shop == o2.shop) {
                    return Integer.compare(o1.movie, o2.movie);
                }
                return Integer.compare(o1.shop, o2.shop);
            }
            return Integer.compare(o1.price, o2.price);
        });

        class Movie {
            int movie;
            int shop;
            int price;

            public Movie(int movie, int shop, int price) {
                this.movie = movie;
                this.shop = shop;
                this.price = price;
            }

            public Movie(int movie, int shop) {
                this.movie = movie;
                this.shop = shop;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Movie movie1 = (Movie) o;
                return movie == movie1.movie && shop == movie1.shop;
            }

            @Override
            public int hashCode() {
                return Objects.hash(movie, shop);
            }
        }

        public MovieRentingSystem(int n, int[][] entries) {
            for (int[] entry : entries) {
                TreeSet<Movie> set = unRentedMap.getOrDefault(entry[1], new TreeSet<>((o1, o2) -> {
                    if (o1.price == o2.price) {
                        return Integer.compare(o1.shop, o2.shop);
                    }
                    return Integer.compare(o1.price, o2.price);
                }));
                Movie m = new Movie(entry[1], entry[0], entry[2]);
                set.add(m);
                unRentedMap.put(entry[1], set);
                unRentedSet.add(m);
                priceMap.put(m, entry[2]);
            }
        }

        public List<Integer> search(int movie) {
            TreeSet<Movie> set = unRentedMap.get(movie);
            List<Integer> ans = new ArrayList<>();
            if (set != null) {
                Iterator<Movie> it = set.iterator();
                while (it.hasNext() && ans.size() < 5) {
                    ans.add(it.next().shop);
                }
            }
            return ans;
        }

        public void rent(int shop, int movie) {
            Movie m = new Movie(movie, shop);
            unRentedSet.remove(new Movie(movie, shop, priceMap.get(m)));
            rentedSet.add(new Movie(movie, shop, priceMap.get(m)));
            unRentedMap.get(movie).remove(m);
        }

        public void drop(int shop, int movie) {
            Movie m = new Movie(movie, shop);
            unRentedSet.add(new Movie(movie, shop, priceMap.get(m)));
            rentedSet.remove(new Movie(movie, shop, priceMap.get(m)));
            unRentedMap.get(movie).add(m);
        }

        public List<List<Integer>> report() {
            if (rentedSet.isEmpty()) {
                return new ArrayList<>();
            }
            Iterator<Movie> iterator = rentedSet.iterator();
            List<List<Integer>> ans = new ArrayList<>();
            while (iterator.hasNext() && ans.size() < 5) {
                Movie m = iterator.next();
                List<Integer> list = new ArrayList<>();
                list.add(m.shop);
                list.add(m.movie);
                ans.add(list);
            }
            return ans;
        }
    }
}
