package net.coderodde.patternmatching;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements Aho-Corasick algorithm for multiple exact string matching problem.
 *
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 1, 2016)
 */
public class AhoCorasickMatcher extends AbstractMultipleExactStringMatcher {

  @Override
  public List<MatchingResult> match(String text, String... patterns) {
    if (patterns.length == 0) {
      throw new IllegalArgumentException("No patterns given.");
    }

    patterns = filterPatterns(patterns);

    Automaton data = constructACAutomaton(patterns);
    TrieNode v = data.root;
    int n = text.length();
    List<MatchingResult> resultList = new ArrayList<>();

    for (int j = 0; j < n; ++j) {
      while (v.getChild(text.charAt(j)) == null) {
        v = data.fail.get(v);
      }

      v = v.getChild(text.charAt(j));

      for (Integer i : data.patterns.get(v)) {
        resultList.add(new MatchingResult(i, j));
      }
    }

    return resultList;
  }

  private static final class TrieNode {

    private final Map<Character, TrieNode> children = new HashMap<>();

    void setChild(char character, TrieNode child) {
      children.put(character, child);
    }

    TrieNode getChild(char character) {
      return children.get(character);
    }
  }

  private Automaton constructACAutomaton(String[] patterns) {
    Automaton ret = new Automaton();
    constructTrie(ret, patterns);
    computeFailureFunction(ret);
    return ret;
  }

  private void constructTrie(Automaton automaton, String[] patterns) {
    TrieNode root = new TrieNode();
    int k = patterns.length;

    for (int i = 0; i < k; ++i) {
      TrieNode v = root;
      int j = 0;
      int patternLength = patterns[i].length();

      while (j < patternLength
          && v.getChild(patterns[i].charAt(j)) != null) {
        v = v.getChild(patterns[i].charAt(j));
        ++j;
      }

      while (j < patternLength) {
        TrieNode u = new TrieNode();
        v.setChild(patterns[i].charAt(j), u);
        v = u;
        ++j;
      }

      List<Integer> list = new ArrayList<>();
      list.add(i);
      automaton.patterns.put(v, list);
    }

    automaton.patterns.put(root, new ArrayList<>());
    automaton.root = root;
  }

  private void computeFailureFunction(Automaton automaton) {
    TrieNode fallbackNode = new TrieNode();

    for (char c = 'a'; c <= 'z'; ++c) {
      fallbackNode.setChild(c, automaton.root);
    }

    automaton.fail.put(automaton.root, fallbackNode);
    Deque<TrieNode> queue = new ArrayDeque<>();
    queue.addLast(automaton.root);

    while (!queue.isEmpty()) {
      TrieNode u = queue.removeFirst();

      for (char c = 'a'; c <= 'z'; ++c) {
        if (u.getChild(c) == null) {
          continue;
        }

        TrieNode v = u.getChild(c);
        TrieNode w = automaton.fail.get(u);

        while (w.getChild(c) == null) {
          w = automaton.fail.get(w);
        }

        automaton.fail.put(v, w.getChild(c));

        List<Integer> list =
            automaton.patterns.get(automaton.fail.get(v));

        if (automaton.patterns.get(v) == null) {
          automaton.patterns.put(v, list);
        } else {
          automaton.patterns.get(v).addAll(list);
        }

        queue.addLast(v);
      }
    }

    automaton.patterns.put(automaton.root, new ArrayList<>());
  }

  private static final class Automaton {

    TrieNode root;
    Map<TrieNode, TrieNode> fail = new HashMap<>();
    Map<TrieNode, List<Integer>> patterns = new HashMap<>();
  }


  public static void main(String args[]) {
    AhoCorasickMatcher ahoCorasickMatcher = new AhoCorasickMatcher();

    ahoCorasickMatcher.match("gcatcg", "acc", "atc", "cat", "gcg").forEach(matchingResult -> {
      System.out.println(matchingResult);
    });
  }
}
