package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

public abstract class BaseLongestPalindromicSubstringFinderTest {

  protected abstract LongestPalindromicSubstringFinder getInstance();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {"babad", Set.of("bab", "aba")},
      new Object[] {"cbbd", Set.of("bb")},
      new Object[] {"a", Set.of("a")},
      new Object[] {"", Set.of("")},
      new Object[] {"ac", Set.of("a", "c")},
      new Object[] {"bb", Set.of("bb")},
      new Object[] {
        "kztakrekvefgchersuoiuatzlmwynzjhdqqftjcqmntoyckqfawikkdrnfgbwtdpbkymvwoumurjdzygyzsbmwzpcxcdmmpwzmeibligwiiqbecxwyxigikoewwrczkanwwqukszsbjukzumzladrvjefpegyicsgctdvldetuegxwihdtitqrdmygdrsweahfrepdcudvyvrggbkthztxwicyzazjyeztytwiyybqdsczozvtegodacdokczfmwqfmyuixbeeqluqcqwxpyrkpfcdosttzooykpvdykfxulttvvwnzftndvhsvpgrgdzsvfxdtzztdiswgwxzvbpsjlizlfrlgvlnwbjwbujafjaedivvgnbgwcdbzbdbprqrflfhahsvlcekeyqueyxjfetkxpapbeejoxwxlgepmxzowldsmqllpzeymakcshfzkvyykwljeltutdmrhxcbzizihzinywggzjctzasvefcxmhnusdvlderconvaisaetcdldeveeemhugipfzbhrwidcjpfrumshbdofchpgcsbkvaexfmenpsuodatxjavoszcitjewflejjmsuvyuyrkumednsfkbgvbqxfphfqeqozcnabmtedffvzwbgbzbfydiyaevoqtfmzxaujdydtjftapkpdhnbmrylcibzuqqynvnsihmyxdcrfftkuoymzoxpnashaderlosnkxbhamkkxfhwjsyehkmblhppbyspmcwuoguptliashefdklokjpggfiixozsrlwmeksmzdcvipgkwxwynzsvxnqtchgwwadqybkguscfyrbyxudzrxacoplmcqcsmkraimfwbauvytkxdnglwfuvehpxd",
        Set.of("dtzztd")
      },
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String str, Set<String> validAnswers) {
    String actual = getInstance().longestPalindrome(str);
    assertThat(actual, isIn(validAnswers));
  }
}
