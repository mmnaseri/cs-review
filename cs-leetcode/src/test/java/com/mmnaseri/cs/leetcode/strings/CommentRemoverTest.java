package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommentRemoverTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {
        new String[] {
          "/*Test program */",
          "int main()",
          "{ ",
          "  // variable declaration ",
          "int a, b, c;",
          "/* This is a test",
          "   multiline  ",
          "   comment for ",
          "   testing */",
          "a = b + c;",
          "}"
        },
        List.of("int main()", "{ ", "  ", "int a, b, c;", "a = b + c;", "}")
      },
      new Object[] {new String[] {"a/*comment", "line", "more_comment*/b"}, List.of("ab")},
      new Object[] {
        new String[] {
          "main() { ", "  int a = 1; /* Its comments here ", "", "  ", "  */ return 0;", "}"
        },
        List.of("main() { ", "  int a = 1;  return 0;", "}")
      },
      new Object[] {
        new String[] {
          "struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"
        },
        List.of("struct Node{", "    ", "    int size;", "    int val;", "};")
      },
      new Object[] {
        new String[] {
          "void func(int k) {",
          "// this function does nothing /*",
          "   k = k*2/4;",
          "   k = k/2;*/",
          "}"
        },
        List.of("void func(int k) {", "   k = k*2/4;", "   k = k/2;*/", "}")
      },
      new Object[] {
        new String[] {
          "main() {",
          "   func(1);",
          "   /** / more comments here",
          "   float f = 2.0",
          "   f += f;",
          "   cout << f; */",
          "}"
        },
        List.of("main() {", "   func(1);", "   ", "}")
      },
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String[] source, List<String> expected) {
    assertThat(new CommentRemover().removeComments(source), is(expected));
  }
}
