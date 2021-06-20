package com.leetcode.bfs;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author manoji on 3/4/20.
 */
public class WordLadder {


  class Data {

    int dist;
    String word;

    public Data(int dist, String word) {
      this.dist = dist;
      this.word = word;
    }
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    int min = Integer.MAX_VALUE;
    LinkedList<Data> queue = new LinkedList();
    queue.add(new Data(Integer.MAX_VALUE, beginWord));
    HashSet<String> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Data currentWordData = queue.poll();
      if (currentWordData.word.equalsIgnoreCase(endWord) && currentWordData.dist < min) {
        min = currentWordData.dist + 1;
      }
      if (!visited.contains(currentWordData.word)) {
        visited.add(currentWordData.word);
        List<String> nextWord = getNext(currentWordData.word, wordList, visited);
        if (!nextWord.isEmpty()) {
          for (String str : nextWord) {
            if (currentWordData.dist == Integer.MAX_VALUE) {
              queue.add(new Data(1, str));
            } else {
              queue.add(new Data(currentWordData.dist + 1, str));
            }
          }
        }
      }
    }
    return min;
  }

  private List<String> getNext(String current, List<String> wordList, HashSet<String> visited) {
    List<String> words = new ArrayList();
    for (int i = 0; i < wordList.size(); i++) {
      if (!visited.contains(wordList.get(i)) && getDist(current, wordList.get(i)) == 1) {
        words.add(wordList.get(i));

      }
    }
    return words;
  }

  private int getDist(String word1, String word2) {
    int count = 0;
    for (int i = 0; i < word1.length(); i++) {
      if (word1.charAt(i) != word2.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    WordLadder wordLadder = new WordLadder();

    List<String> wordList = Lists.newArrayList("hot", "dog");

    System.out.println(wordLadder.ladderLength("hot", "dog", wordList));
  }

}
