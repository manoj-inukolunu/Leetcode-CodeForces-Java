package com.experiments.htmlparse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author manoji on 2/1/20.
 */
public class LeetCodeProblemExtractor {

  String tagBaseUrl = "https://leetcode.com/tag/";

  OkHttpClient okHttpClient = new OkHttpClient();

  String graphQl = "{\n"
      + "    \"operationName\": \"getTopicTag\",\n"
      + "    \"variables\": {\n"
      + "        \"slug\": \"%s\"\n"
      + "    },\n"
      + "    \"query\": \"query getTopicTag($slug: String!) {\\n  topicTag(slug: $slug) {\\n    name\\n    translatedName\\n    slug\\n    questions"
      + " {\\n      status\\n      questionId\\n      questionFrontendId\\n      title\\n      titleSlug\\n      translatedTitle\\n      stats\\n   "
      + "   difficulty\\n      isPaidOnly\\n      topicTags {\\n        name\\n        translatedName\\n        slug\\n        __typename\\n      "
      + "}\\n      companyTags {\\n        name\\n        translatedName\\n        slug\\n        __typename\\n      }\\n      __typename\\n    }\\n"
      + "    frequencies\\n    __typename\\n  }\\n  favoritesLists {\\n    publicFavorites {\\n      ...favoriteFields\\n      __typename\\n    }\\n"
      + "    privateFavorites {\\n      ...favoriteFields\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\\nfragment favoriteFields on "
      + "FavoriteNode {\\n  idHash\\n  id\\n  name\\n  isPublicFavorite\\n  viewCount\\n  creator\\n  isWatched\\n  questions {\\n    questionId\\n "
      + "   title\\n    titleSlug\\n    __typename\\n  }\\n  __typename\\n}\\n\"\n"
      + "}";

  private void extractMedium(String tag) throws Exception {
    Request request =
        new Request.Builder().url("https://leetcode.com/graphql").addHeader("referer", " https://leetcode.com/tag/dynamic-programming/").post
            (RequestBody.create(String.format(graphQl, tag),
                MediaType.parse(
                    "application/json"))).build();

    String jsonResponse = okHttpClient.newCall(request).execute().body().string();
    JsonNode node = new ObjectMapper().readValue(jsonResponse, JsonNode.class);
    node.get("data").get("topicTag").get("questions").forEach(jsonNode -> {
      if (jsonNode.get("difficulty").asText().equalsIgnoreCase("Medium")) {
        System.out.println(jsonNode.get("title").asText());
      }
    });
    String url = tagBaseUrl + tag;
    try {
      Document doc = Jsoup.connect(url).post();
      Elements elements = doc.select("tr");
      elements.forEach(element -> System.out.println(element));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String args[]) throws Exception {
    LeetCodeProblemExtractor leetCodeProblemExtractor = new LeetCodeProblemExtractor();
    leetCodeProblemExtractor.extractMedium("dynamic-programming");
  }


}
