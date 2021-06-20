package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileSystem {

  class File {

    boolean directory;
    HashMap<String, File> files = new HashMap<>();
    String name;
    String content = null;

    public File(Boolean directory, HashMap<String, File> files, String name, String content) {
      this.directory = directory;
      this.files = files;
      this.name = name;
      this.content = content;
    }

    public File(String name, String content) {
      this.directory = false;
      this.files = new HashMap<>();
      this.content = content;
      this.name = name;
    }

    public File(String name, Boolean directory) {
      files = new HashMap<>();
      this.name = name;
      this.directory = directory;
    }

    public File() {

    }
  }

  File root;

  public FileSystem() {
    root = new File();
    root.name = "/";
    root.directory = true;
    root.files = new HashMap<>();
  }

  public List<String> ls(String path) {
    List<String> collector = new ArrayList<>();
    File temp = root;
    dfs(temp, collector, new StringBuffer(), path);
    Collections.sort(collector);
    return collector;
  }

  private void dfs(File root, List<String> collector, StringBuffer buff, String path) {
    if (root.name.equals("/")) {
      buff.append("/");
      if (buff.toString().equals(path)) {
        if (root.directory) {
          collector.addAll(root.files.keySet());
        } else {
          collector.add(buff.toString());
        }
      }
    } else {
      buff.append(root.name);
      if (buff.toString().equals(path)) {
        if (root.directory) {
          collector.addAll(root.files.keySet());
        } else {
          collector.add(buff.toString());
        }
        return;
      }
      buff.append("/");
    }
    for (String file : root.files.keySet()) {
      StringBuffer temp = new StringBuffer(buff);
      dfs(root.files.get(file), collector, buff, path);
      buff = temp;
    }
  }

  public void mkdir(String path) {
    File temp = root;
    mkdir(path, 1, temp);
  }

  private void mkdir(String path, int index, File file) {
    if (index > path.length()) {
      return;
    }
    int next = path.indexOf('/', index);
    String name;
    if (next < 0) {
      name = path.substring(index);
      if (!file.files.containsKey(name)) {
        File f = new File(name, true);
        file.files.put(name, f);
        return;
      }
    } else {
      name = path.substring(index, next);
    }
    if (file.files.containsKey(name)) {
      mkdir(path, next + 1, file.files.get(name));
    } else {
      File f = new File(name, true);
      file.files.put(name, f);
      mkdir(path, next + 1, file.files.get(name));
    }
  }

  private File get(File root, String path, int index) {
    if (index > path.length()) {
      return null;
    }
    int idx = path.indexOf('/', index);
    if (idx == -1 && root.files.containsKey(path.substring(index))) {
      return root.files.get(path.substring(index));
    }
    String name = path.substring(index, idx);
    if (root.files.containsKey(name)) {
      return get(root.files.get(name), path, idx + 1);
    } else {
      return null;
    }
  }

  public void addContentToFile(String filePath, String content) {
    int idx = filePath.lastIndexOf('/');
    if (idx == 0) {
      root.files.put(filePath.substring(1), new File(filePath.substring(1), content));
      return;
    }
    File file = get(root, filePath.substring(1, filePath.lastIndexOf('/')), 0);
    String name = filePath.substring(filePath.lastIndexOf('/') + 1);
    if (file.files.containsKey(name)) {
      File f = file.files.get(name);
      f.content = f.content + content;
      file.files.put(name, f);
    } else {
      File str = new File();
      str.directory = false;
      str.name = name;
      str.content = content;
      file.files.put(str.name, str);
    }

  }

  public String readContentFromFile(String filePath) {
    int idx = filePath.lastIndexOf('/');
    if (idx == 0) {
      return root.files.get(filePath.substring(1)).content;
    }
    File file = get(root, filePath.substring(1, filePath.lastIndexOf('/')), 0);
    return file.files.get(filePath.substring(filePath.lastIndexOf('/') + 1)).content;
  }

  public static void main(String args[]) {
    FileSystem fileSystem = new FileSystem();
    fileSystem.mkdir("/higrtfas");
    fileSystem.mkdir("/higrtfas/tekdylgz");
    fileSystem.mkdir("/higrtfas/tekdylgz/eblueagp");
    fileSystem.mkdir("/jfhkpmk");
    System.out.println(fileSystem.ls("/jfhkpmk"));
    fileSystem.addContentToFile("/hmztxfq", "ylvhyd");
    fileSystem.addContentToFile("/gpyt", "cpzf");
    System.out.println(fileSystem.ls("/higrtfas"));
    System.out.println(fileSystem.ls("/"));

  }
}
